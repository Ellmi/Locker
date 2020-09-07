package cn.xpbootcamp.locker;

import java.util.HashMap;

public class Locker {
    private final int capacity;
    private HashMap<LockerTicket, Bag> ticketBagMap;

    public Locker(int lockerCapability) {
        this.capacity = lockerCapability;
        this.ticketBagMap = new HashMap<>();
    }

    public boolean hasBag(LockerTicket lockerTicket) {
        return ticketBagMap.keySet().contains(lockerTicket);
    }

    public boolean canStoreBag() {
        return ticketBagMap.size() < capacity;
    }

    public int getAvailableCapability() {
        return capacity - ticketBagMap.size();
    }

    public LockerTicket storeBag(Bag bag) {

        if (canStoreBag()) {
            LockerTicket lockerTicket = new LockerTicket();

            ticketBagMap.put(lockerTicket, bag);
            return lockerTicket;
        }

        throw new LockerIsFullException();
    }

    public Bag getBag(LockerTicket lockerTicket) {

        Bag bag = ticketBagMap.remove(lockerTicket);

        if (bag == null) throw new InvalidTicketException();

        return bag;
    }
}
