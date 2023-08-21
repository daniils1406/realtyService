package com.example.fileservice.service.impl;

import com.example.fileservice.exception.*;
import com.example.fileservice.grpc.GrpcAdvertisementServiceImpl;
import com.example.fileservice.grpc.GrpcAuthenticationServiceImpl;
import com.example.fileservice.grpc.GrpcChatServiceImpl;
import com.example.fileservice.mapper.FileInfoMapper;
import com.example.fileservice.model.jooq.schema.enums.EntityType;
import com.example.fileservice.model.jooq.schema.enums.FileStatus;
import com.example.fileservice.model.jooq.schema.enums.FileType;
import com.example.fileservice.model.jooq.schema.tables.File;
import com.example.fileservice.model.jooq.schema.tables.pojos.FileEntity;
import com.example.fileservice.rabbit.RabbitProducer;
import com.example.fileservice.repository.FilesRepository;
import com.example.fileservice.service.FileService;
import com.example.fileservice.util.PathFileMapper;
import dto.FileInfo;
import dto.response.FileResponse;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FilesRepository filesRepository;

    private final GrpcAuthenticationServiceImpl grpcAuthenticationService;

    private final GrpcAdvertisementServiceImpl grpcAdvertisementService;

    private final GrpcChatServiceImpl grpcChatService;

    private final RabbitProducer rabbitProducer;

    private final FileInfoMapper mapper;

    private final MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${imageExtension}")
    private final String[] imageExtensionsList;

    @Value("${necessary-files.builder-or-agency}")
    private final String[] necessaryFilesOrganisation;

    @Value("${necessary-files.agent}")
    private final String[] necessaryFilesAgent;

    @Value("${necessary-files.owner}")
    private final String[] necessaryFilesOwner;

    @Value("${necessary-files.user}")
    private final String[] necessaryFilesUser;

    @Value("${necessary-files.flat}")
    private final String[] necessaryFilesFlat;

    @Value("${necessary-files.house}")
    private final String[] necessaryFilesHouse;

    private int putViewOfRealty(UUID id, String entity, MultipartFile file, InputStream inputStream) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String extention=FileNameUtils.getExtension(file.getOriginalFilename());
        if(extention.equals("jpg")){
            extention="jpeg";
        }
        int currentNumberOfViews = filesRepository.numberOfViewImagesOfRealtyById(id);
        currentNumberOfViews++;
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(PathFileMapper.getFilePath(EntityType.valueOf(entity),
                                FileType.VIEW, false) + "/"
                                + id + "_" + currentNumberOfViews + "."
//                                + FileNameUtils.getExtension(file.getOriginalFilename()))
                                + extention)
                        .stream(inputStream, inputStream.available(), -1)
                        .contentType("image/png")
                        .build());
        return currentNumberOfViews;
    }

    private void putViewOfSubject(UUID id, String entity, MultipartFile file, InputStream inputStream, String fileType, boolean isDocument) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String extention=FileNameUtils.getExtension(file.getOriginalFilename());
        if(extention.equals("jpg")){
            extention="jpeg";
        }
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(PathFileMapper.getFilePath(EntityType.valueOf(entity),
                                FileType.valueOf(fileType), isDocument) + "/"
                                + id + "_" + fileType + "."
//                                + FileNameUtils.getExtension(file.getOriginalFilename()))
                                + extention)
                        .stream(inputStream, inputStream.available(), -1)
                        .contentType("image/png")
                        .build());
    }

    @Override
    public void upload(UUID id, String entity, MultipartFile file, String fileType, boolean isDocument) {
        List<String> imageExtensions = List.of(imageExtensionsList);


        boolean found;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            throw new FindBucketException(e.getMessage());
        }
        if (!found) {
            try {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } catch (Exception e) {
                throw new BucketCreationException(e.getMessage());
            }
        }

        FileInfo fileInfo = FileInfo.builder()
                .id(id)
                .originalFileName(file.getOriginalFilename())
                .size((int) file.getSize())
                .mimeType(file.getContentType())
                .insertDate(Date.valueOf(LocalDate.now()))
                .updateDate(Date.valueOf(LocalDate.now()))
                .path(PathFileMapper.getFilePath(EntityType.valueOf(entity), FileType.valueOf(fileType), isDocument))
                .fileType(fileType)
                .entityType(entity)
                .fileStatus("INSERT")
                .build();


        try (InputStream inputStream = file.getInputStream()) {
            if (imageExtensions.contains(FileNameUtils.getExtension(file.getOriginalFilename()))) {
                String extention=FileNameUtils.getExtension(file.getOriginalFilename());
                if(extention.equals("jpg")){
                    extention="jpeg";
                }
                if (FileType.valueOf(fileType).equals(FileType.VIEW)) {
                    int currentNumbersOfViews = putViewOfRealty(id, entity, file, inputStream);
                    fileInfo.setStorageFileName(id + "_" + currentNumbersOfViews + "." + extention);
                } else {
                    putViewOfSubject(id, entity, file, inputStream, fileType, isDocument);
                    fileInfo.setStorageFileName(id + "_" + fileType + "." + extention);
                }
            } else {
                fileInfo.setStorageFileName(id+"_"+fileType+"."+FileNameUtils.getExtension(file.getOriginalFilename()));
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(PathFileMapper.getFilePath(EntityType.valueOf(entity),
                                        FileType.valueOf(fileType), isDocument) + "/" +
                                        id + "_" + fileType + "." + FileNameUtils.getExtension(file.getOriginalFilename()))
                                .stream(inputStream, inputStream.available(), -1)
                                .build());
            }
        } catch (Exception e) {
            throw new FileInsertingException(e.getMessage());
        }


        if (!filesRepository.existByIdAndType(fileInfo.getId(), fileInfo.getFileType()) ||
                FileType.valueOf(fileInfo.getFileType()).equals(FileType.VIEW)) {
            filesRepository.save(mapper.fromInfoToEntity(fileInfo));
        } else {
            filesRepository.updateByIdAndType(mapper.fromInfoToEntity(fileInfo));
        }

    }


    @Override
    public ResponseEntity<ByteArrayResource> downloadView(String storageName) {
        FileEntity fileInfo = filesRepository.findViewByStorageName(storageName);

        return getFileFromMinio(fileInfo);
    }

    @Override
    public boolean checkNecessaryFiles(UUID id) {
        filesRepository.existById(id);
        if (filesRepository.existById(id)) {
            EntityType entityType = filesRepository.findOutEntityOfId(id);
            List<FileType> allFilesOfEntity = filesRepository.findAllSaveDocumentsOfEntity(id);
            System.out.println(entityType);
            System.out.println(allFilesOfEntity);
            switch (entityType) {
                case AGENCY, BUILDER -> {
                    for (String necessaryFileType : necessaryFilesOrganisation) {
                        if (!allFilesOfEntity.contains(FileType.valueOf(necessaryFileType))) {
                            return false;
                        }
                    }
                }
                case AGENT -> {
                    for (String necessaryFileType : necessaryFilesAgent) {
                        if (!allFilesOfEntity.contains(FileType.valueOf(necessaryFileType))) {
                            return false;
                        }
                    }
                }
                case OWNER -> {
                    for (String necessaryFileType : necessaryFilesOwner) {
                        if (!allFilesOfEntity.contains(FileType.valueOf(necessaryFileType))) {
                            return false;
                        }
                    }
                }
                case USER -> {
                    for (String necessaryFileType : necessaryFilesUser) {
                        if (!allFilesOfEntity.contains(FileType.valueOf(necessaryFileType))) {
                            return false;
                        }
                    }
                }
                case HOUSE,FLAT -> {
                    for (String necessaryFileType : necessaryFilesFlat) {
                        if (!allFilesOfEntity.contains(FileType.valueOf(necessaryFileType))) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }else{
            throw new EntityNotFoundException(id.toString());
        }
    }

    @Override
    public void setStatusOnSomeFile(UUID id, String fileType, String fileStatus) {
        rabbitProducer.sendNotification("SERVICE","You document "+fileType+" switched status to "+fileStatus+"!",id);
        if (FileStatus.DELETE.equals(FileStatus.valueOf(fileStatus))) {
            if (!checkNecessaryFiles(id)) {
                //GRPC МЕТОД КОТОРЫЙ ОТПРАВИТ В СЕРВИС АУТЕНТИФИКАЦИ ЗАПРОС НА БЛОКИРОВКУ ПОЛЬЗАКА
                grpcAuthenticationService.bannedById(id);
                //GRPC МЕТОД КОТОРЫЙ ОТПРАВИТ В СЕРВИС ОБЬЯВЛЕНИЙ ЗАПРОС НА БЛОКИРОВКУ ВСЕХ ОБЬЯВЛЕНИЙ ПОЛЬЗАКА
                grpcAdvertisementService.bannedAllRealtyOfUser(id);
            }
        } else if (FileStatus.VERIFIED.equals(FileStatus.valueOf(fileStatus))) {//GRPC МЕТОД ПОДТВЕРЖДЕНИЯ ПОЛЬЗАКА И ЕГО ОБЬЕКТОВ
            if (checkNecessaryFiles(id)) {
                grpcAuthenticationService.verifiedById(id);
                grpcAdvertisementService.verifyAllRealtyOfUser(id);
            }
        }
        filesRepository.setSomeStatusOnFileByIdAndType(id, fileType, fileStatus);
    }

    @Override
    public FileEntity findFileByStorageFileName(String name) {
        return filesRepository.findViewByStorageName(name);
    }

    @Override
    public List<String> getViewOfRealty(UUID realtyId) {
        return filesRepository.findViewOfRealty(realtyId);
    }

    @Override
    public void setNewEntityTypeById(UUID id, String newType) {
        filesRepository.setNewEntityTypeById(id,newType);
    }

    private ResponseEntity<ByteArrayResource> getFileFromMinio(FileEntity fileInfo) {
        try (InputStream in = minioClient.getObject(GetObjectArgs
                .builder()
                .bucket(bucketName)
                .object(fileInfo.getPath() + "/" + fileInfo.getStorageFileName())
                .build())) {
            byte[] serializeFile = IOUtils.toByteArray(in);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(serializeFile.length)
                    .header("Content-disposition", "attachment; filename=\"" +
                            fileInfo.getOriginalFileName() +
                            "\"")
                    .body(new ByteArrayResource(serializeFile));
        } catch (Exception e) {
            throw new FileDownloadingException(e.getMessage());
        }
    }


    @Override
    public void deleteView(String storageName) {
        FileEntity fileInfo = filesRepository.findViewByStorageName(storageName);
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileInfo.getPath() + "/" + fileInfo.getStorageFileName()).build());
        } catch (Exception e) {
            throw new FileDeletingException(e.getMessage());
        }
        filesRepository.deleteViewByStorageName(storageName);
    }
}