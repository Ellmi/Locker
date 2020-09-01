package cn.xpbootcamp.locker;

import java.util.List;

import static cn.xpbootcamp.locker.ErrorMessageConstant.NO_ROOM_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.ErrorMessageConstant.TICKET_INVALID_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.FAILED;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.SUCCESS;

public class Locker {
    private int lockerCapbility;
    private int lockerStored;
    private List<LockerTicket> tickets;

    public Locker(int lockerCapbility, int lockerStored) {
        this.lockerCapbility = lockerCapbility;
        this.lockerStored = lockerStored;
    }

    public StoreBagResult storeBag() {
        if (lockerStored < lockerCapbility) return new StoreBagResult(SUCCESS, "");
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
