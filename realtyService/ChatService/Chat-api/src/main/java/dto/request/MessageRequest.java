package dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageRequest {
    private UUID senderId;
    private String recipientId;
    private String senderName;
    private String content;
//    private String status;
}
