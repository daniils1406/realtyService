package dto.request.realty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdAndRealtyStatusRequest {
    private UUID id;
    private String newStatus;
}
