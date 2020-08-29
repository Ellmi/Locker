package cn.xpbootcamp.locker;

public class StoreResult extends OperateResult {

    public StoreResult(LockerOperateStatusEnum status, String errorMessage) {
        super(status, errorMessage);
    }

    public LockerTicket getTicket() {
        int newTicketId = 1;
        return new LockerTicket(newTicketId);
    }

}
