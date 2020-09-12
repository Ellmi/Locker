package cn.xpbootcamp.locker;

import java.util.HashMap;

class Locker {
    private final int capacity;
    private HashMap<LockerTicket, Bag> ticketBagMap;

    Locker(int lockerCapability) {
        this.capacity = lockerCapability;
        this.ticketBagMap = new HashMap<>();
    }

    boolean hasBag(LockerTicket lockerTicket) {
        return ticketBagMap.keySet().contains(lockerTicket);
    }

    boolean canStoreBag() {
        return ticketBagMap.size() < capacity;
    }

    int getAvailableCapability() {
        return capacity - ticketBagMap.size();
    }

    int getCapacity() {
        return capacity;
    }

    LockerTicket storeBag(Bag bag) {

        if (canStoreBag()) {
            LockerTicket lockerTicket = new LockerTicket();

            ticketBagMap.put(lockerTicket, bag);
            return lockerTicket;
        }

        throw new LockerIsFullException();
    }

    Bag getBag(LockerTicket lockerTicket) {

        Bag bag = ticketBagMap.remove(lockerTicket);

        if (bag == null) throw new InvalidTicketException();

        return bag;
    }

    String report() {
        return "L" + "  " + getAvailableCapability() + " " + capacity + "\n";
    }
}
