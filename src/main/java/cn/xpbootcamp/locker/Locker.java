package cn.xpbootcamp.locker;

import java.util.HashMap;

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

    public Bag getBag(LockerTicket lockerTicket) {
        if (!ticketBagMap.keySet().contains(lockerTicket)) throw new InvalidTicketException();

        Bag bag = ticketBagMap.get(lockerTicket);
        ticketBagMap.remove(lockerTicket);
        return bag;
    }
}
