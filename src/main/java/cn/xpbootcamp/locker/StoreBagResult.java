package cn.xpbootcamp.locker;

public class StoreBagResult extends OperateResult {
    private LockerTicket lockerTicket;

    public StoreBagResult(LockerOperateStatusEnum status, String errorMessage) {
        super(status, errorMessage);
    }

    public LockerTicket getLockerTicket() {
        return lockerTicket;
    }

    public void setLockerTicket(LockerTicket lockerTicket) {
        this.lockerTicket = lockerTicket;
    }
}
