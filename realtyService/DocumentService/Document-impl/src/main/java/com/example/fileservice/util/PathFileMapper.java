package com.example.fileservice.util;

import com.example.fileservice.model.jooq.schema.enums.EntityType;
import com.example.fileservice.model.jooq.schema.enums.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PathFileMapper {
    private static final String DOCUMENT_DIRECTORY = "documents";

    private static final String IMAGE_DIRECTORY = "images";

    public static String getFilePath(EntityType entity, FileType fileType, boolean isDocument) {
        String path;
        if (isDocument) {
            path = DOCUMENT_DIRECTORY;
        } else {
            path = IMAGE_DIRECTORY;
        }
        switch (entity) {
            case BUILDER -> {
                path = path.concat("/builder");
            }
            case AGENCY -> {
                path = path.concat("/agency");
            }
            case OWNER -> {
                path = path.concat("/owner");
            }
            case AGENT -> {
                path = path.concat("/agent");
            }
            case USER -> {
                path = path.concat("/user");
            }
            case RESIDENTIAL_COMPLEX -> {
                path = path.concat("/residential_complex");
            }
            case HOUSE -> {
                path = path.concat("/house");
            }
            case FLAT -> {
                path = path.concat("/flat");
            }
        }
        switch (fileType) {
            case REGULATION -> {
                path = path.concat("/regulation");
            }
            case OGRN -> {
                path = path.concat("/ogrn");
            }
            case EGRUL -> {
                path = path.concat("/egrul");
            }
            case ORGNIP -> {
                path = path.concat("/orgnip");
            }
            case EGRIP -> {
                path = path.concat("/egrip");
            }
            case INN -> {
                path = path.concat("/inn");
            }
            case EGRN -> {
                path = path.concat("/egrn");
            }
            case PERSON -> {
                path = path.concat("/person");
            }
            case LOGO -> {
                path = path.concat("/logo");
            }
            case HEAT -> {
                path = path.concat("/heat");
            }
            case VIEW -> {
                path = path.concat("/view");
            }
        }
        return path;
    }
}
