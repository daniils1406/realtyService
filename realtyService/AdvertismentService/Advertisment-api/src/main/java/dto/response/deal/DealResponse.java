package dto.response.deal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DealResponse {
    private UUID id;
    private UUID clientId;
    private UUID brokerId;
    private Date periodOfDeal;
    private UUID realtyId;
    private int transactionAmount;
    private String status;
    private Date insertDate;
    private Date transactionDate;
}
