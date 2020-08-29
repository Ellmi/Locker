package cn.xpbootcamp.locker;

import static cn.xpbootcamp.locker.ErrorMessageConstant.NO_ROOM_ERROR_MESSAGE;

public class StoreResult {

    private LockerOperateStatusEnum status;

    public StoreResult(LockerOperateStatusEnum statusEnum) {
        this.status = statusEnum;
    }

    public LockerOperateStatusEnum getStatus() {
        return status;
    }

    public LockerTicket getTicket() {
        return new LockerTicket(1);
    }

    public String getErrorMessage() {
        return NO_ROOM_ERROR_MESSAGE;
    }
}
