package api;

import dto.UpdateStatusRequest;
import dto.request.CheckRequest;
import dto.request.FileNameRequest;
import dto.response.CheckResponse;
import dto.response.FileResponse;
import jakarta.annotation.security.PermitAll;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;


@RequestMapping("/file")
public interface FileApi {

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/uploadImage")
    @ResponseStatus(HttpStatus.OK)
    FileResponse uploadImage(HttpServletRequest request,
                             @RequestParam("id") UUID id,
                             @RequestParam("entity") String entity,
                             @RequestParam("file") MultipartFile[] file,
                             @RequestParam("fileType") String[] fileType);

    @PermitAll
    @PostMapping("/uploadDocument")
    @ResponseStatus(HttpStatus.OK)
    FileResponse uploadDocument(HttpServletRequest request,
                                @RequestParam("id") UUID id,
                                @RequestParam("entity") String entity,
                                @RequestParam("file") MultipartFile[] file,
                                @RequestParam("fileType") String[] fileType);


    @PreAuthorize("isAuthenticated()")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    FileResponse deleteView(HttpServletRequest request,
                            @RequestBody FileNameRequest fileNameRequest);


    @PermitAll
    @GetMapping("/downloadFile")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ByteArrayResource> downloadView(@RequestBody FileNameRequest fileNameRequest);

    @PermitAll
    @PostMapping("/checkFiles")
    @ResponseStatus(HttpStatus.OK)
    CheckResponse checkForTheNecessaryFiles(@RequestBody CheckRequest idOfEntity);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    void setNewStatusOfSomeDocument(@RequestBody UpdateStatusRequest updateStatusRequest);

    @PermitAll
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<String> getViewOfRealty(@RequestParam("id") UUID realtyId);
}
