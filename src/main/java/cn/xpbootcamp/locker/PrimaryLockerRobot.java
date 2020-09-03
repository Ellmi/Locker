package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot {
    protected List<Locker> managedLockers;

    public PrimaryLockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public LockerTicket store(Bag bag) {
        Optional<Locker> goalLocker = managedLockers.stream().filter(Locker::canStoreBag).findFirst();
        if (goalLocker.isPresent()) return goalLocker.get().storeBag(bag);
        throw new LockerIsFullException();
    }

    public Bag getBag(LockerTicket lockerTicket) {
        Optional<Locker> goalLocker = managedLockers.stream().filter(locker -> locker.hasBag(lockerTicket)).findAny();
        if (goalLocker.isPresent()) return goalLocker.get().getBag(lockerTicket);
        throw new InvalidTicketException();
    }
}
