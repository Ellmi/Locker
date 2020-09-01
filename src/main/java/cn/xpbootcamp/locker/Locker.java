package cn.xpbootcamp.locker;

import java.util.List;

import static cn.xpbootcamp.locker.ErrorMessageConstant.NO_ROOM_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.ErrorMessageConstant.TICKET_INVALID_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.FAILED;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.SUCCESS;

public class Locker {
    private int lockerCapability;
    private int availableCapability;
    private List<LockerTicket> tickets;

    public Locker(int lockerCapability) {
        this.lockerCapability = lockerCapability;
        this.availableCapability = lockerCapability;
    }

    public StoreBagResult storeBag(Bag bag) {
        if (availableCapability > 0) {
            availableCapability--;
            return new StoreBagResult(SUCCESS, "");
        }
        return new StoreBagResult(FAILED, NO_ROOM_ERROR_MESSAGE);
    }

    public GetBagResult getBag(LockerTicket lockerTicket) {
        if (lockerTicket.isUsed() || !tickets.contains(lockerTicket))
            return new GetBagResult(FAILED, TICKET_INVALID_ERROR_MESSAGE);
        return new GetBagResult(SUCCESS, "");
    }

    public void setTickets(List<LockerTicket> tickets) {
        this.tickets = tickets;
    }
}
