package cn.xpbootcamp.locker;

import static cn.xpbootcamp.locker.ErrorMessageConstant.TICKET_INVALID_ERROR_MESSAGE;

public class ClaimResult extends OperateResult {

    public ClaimResult(LockerOperateStatusEnum statusEnum) {
        this.status = statusEnum;
    }

    public String getErrorMessage() {
        return TICKET_INVALID_ERROR_MESSAGE;
    }
}
