package cn.xpbootcamp.locker;

import static cn.xpbootcamp.locker.ErrorMessageConstant.NO_ROOM_ERROR_MESSAGE;

public class StoreResult extends OperateResult {

    public StoreResult(LockerOperateStatusEnum statusEnum) {
        this.status = statusEnum;
    }

    public LockerTicket getTicket() {
        return new LockerTicket(1);
    }

    public String getErrorMessage() {
        return NO_ROOM_ERROR_MESSAGE;
    }
}
