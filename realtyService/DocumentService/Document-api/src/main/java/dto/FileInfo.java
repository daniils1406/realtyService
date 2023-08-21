package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileInfo {
    private UUID id;
    private String originalFileName;
    private String storageFileName;
    private int size;
    private String mimeType;
    private Date insertDate;
    private Date updateDate;
    private String path;
    private String fileType;
    private String entityType;
    private String fileStatus;
}
