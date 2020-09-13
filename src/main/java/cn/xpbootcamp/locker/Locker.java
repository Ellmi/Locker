package cn.xpbootcamp.locker;

import java.util.HashMap;

class Locker implements Reportable {
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

    public String report() {
        return selfReporter.report("L", getFreeCapacity(), capacity);
    }

    public Integer getAllCapacity() {
        return capacity;
    }

    public Integer getFreeCapacity() {
        return capacity - ticketBagMap.size();
    }
}
