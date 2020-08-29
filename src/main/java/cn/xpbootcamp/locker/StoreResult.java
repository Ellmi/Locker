package cn.xpbootcamp.locker;

public class StoreResult {

    private LockerOperateStatusEnum status;

    public StoreResult(LockerOperateStatusEnum statusEnum) {
        this.status = statusEnum;

    }

    public LockerOperateStatusEnum getStatus() {
        return status;
    }

    public LockerTicket getTicket() {
        return new LockerTicket();
    }

    public String getErrorMessage() {
        return "There is no room";
    }
}
