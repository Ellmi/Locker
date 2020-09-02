package cn.xpbootcamp.locker;

import java.util.HashMap;

import static cn.xpbootcamp.locker.ErrorMessageConstant.TICKET_INVALID_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.FAILED;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.SUCCESS;

public class Locker {
    private int lockerCapability;
    private int availableCapability;
    private HashMap<LockerTicket, Bag> ticketBagMap;

    public Locker(int lockerCapability) {
        this.lockerCapability = lockerCapability;
        this.availableCapability = lockerCapability;
        this.ticketBagMap = new HashMap<>();
    }

    public HashMap<LockerTicket, Bag> getTicketBagMap() {
        return ticketBagMap;
    }

    public int getAvailableCapability() {
        return availableCapability;
    }

    public LockerTicket storeBag(Bag bag) {
        if (availableCapability > 0) {
            availableCapability--;

            LockerTicket lockerTicket = new LockerTicket();
            ticketBagMap.put(lockerTicket, bag);
            return lockerTicket;
        }
        throw new LockerIsFullException();
    }

    public GetBagResult getBag(LockerTicket lockerTicket) {
        if (!ticketBagMap.keySet().contains(lockerTicket)) return new GetBagResult(FAILED, TICKET_INVALID_ERROR_MESSAGE);

        GetBagResult getBagSuccessResult = new GetBagResult(SUCCESS, "");
        getBagSuccessResult.setBag(ticketBagMap.get(lockerTicket));
        ticketBagMap.remove(lockerTicket);
        return getBagSuccessResult;
    }
}
