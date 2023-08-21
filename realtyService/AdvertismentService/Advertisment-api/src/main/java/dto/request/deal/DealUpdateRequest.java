package dto.request.deal;

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
public class DealUpdateRequest {
    @NotNull
    private UUID id;
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
    private Date transactionDate;
}
