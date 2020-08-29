package cn.xpbootcamp.locker;

public class StoreResult extends OperateResult {

    public StoreResult(LockerOperateStatusEnum status, String errorMessage) {
        super(status, errorMessage);
    }

    public LockerTicket getTicket() {
        return new LockerTicket(1);
    }

}
