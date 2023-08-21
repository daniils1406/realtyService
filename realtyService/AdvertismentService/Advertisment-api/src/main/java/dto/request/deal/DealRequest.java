package dto.request.deal;

import dto.response.deal.DealResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validation.constraint.deal.DealConstraint;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DealConstraint
public class DealRequest {
    @NotNull
    private UUID clientId;
    @NotNull
    private UUID brokerId;
    @NotNull
    private Date periodOfDeal;
    @NotNull
    private UUID realtyId;
    @NotNull
    private int transactionAmount;
    @NotNull
    private String status;
    @NotNull
    private Date insertDate;
    private Date transactionDate;
}
