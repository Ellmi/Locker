package cn.xpbootcamp.locker;

public class StoreBagResult extends OperateResult {

    public StoreBagResult(LockerOperateStatusEnum status, String errorMessage) {
        super(status, errorMessage);
    }

    public LockerTicket getTicket() {
        int newTicketId = 1;
        return new LockerTicket(newTicketId);
    }

}
