package cn.xpbootcamp.locker;

import static cn.xpbootcamp.locker.ErrorMessageConstant.TICKET_INVALID_ERROR_MESSAGE;

public class ClaimResult {
    private LockerOperateStatusEnum status;

    public ClaimResult(LockerOperateStatusEnum statusEnum) {
        this.status = statusEnum;
    }

    public LockerOperateStatusEnum getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return TICKET_INVALID_ERROR_MESSAGE;
    }
}
