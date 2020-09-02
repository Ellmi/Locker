package cn.xpbootcamp.locker;

import java.util.HashMap;

public class Locker {
    private int lockerCapability;
    private HashMap<LockerTicket, Bag> ticketBagMap;

    public Locker(int lockerCapability) {
        this.lockerCapability = lockerCapability;
        this.ticketBagMap = new HashMap<>();
    }

    public HashMap<LockerTicket, Bag> getTicketBagMap() {
        return ticketBagMap;
    }

    public boolean canStoreBag() {
        return ticketBagMap.size() < lockerCapability;
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
