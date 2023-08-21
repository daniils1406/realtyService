package validation.validator.deal;

import dto.request.deal.DealRequest;
import validation.constraint.deal.DealConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DealConstraintValidator implements ConstraintValidator<DealConstraint, DealRequest> {

    private final String WRONG_PERIOD_OF_DEAL = "Period of deal can not be earlier than a transaction date";
    private final String WRONG_STATUS = "Transaction date can not be define in process status";

    @Override
    public boolean isValid(DealRequest value, ConstraintValidatorContext context) {
        if (value.getPeriodOfDeal() != null && value.getPeriodOfDeal().compareTo(value.getTransactionDate()) == -1) {
            buildConstraintViolationWithTemplate(context, WRONG_PERIOD_OF_DEAL, "PeriodOfDeal");
            return false;
        }
        if (value.getStatus().equals("IN_PROCESS") && value.getTransactionDate() != null) {
            buildConstraintViolationWithTemplate(context, WRONG_STATUS, "TransactionDate");
            return false;
        }
        return true;
    }

    private void buildConstraintViolationWithTemplate(ConstraintValidatorContext context, String message, String fieldName) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
