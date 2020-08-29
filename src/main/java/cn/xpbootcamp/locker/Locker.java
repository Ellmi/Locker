package cn.xpbootcamp.locker;

import java.util.List;

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

    public StoreResult store() {
        if (lockerStored < lockerCapbility) return new StoreResult(SUCCESS);
        return new StoreResult(FAILED);
    }

    public ClaimResult claim(LockerTicket lockerTicket) {
        if (lockerTicket.isUsed() || !tickets.contains(lockerTicket)) return new ClaimResult(FAILED);
        return new ClaimResult(SUCCESS);
    }

    public void setTickets(List<LockerTicket> tickets) {
        this.tickets = tickets;
    }
}
