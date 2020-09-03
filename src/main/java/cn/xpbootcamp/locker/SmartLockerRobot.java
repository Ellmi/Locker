package cn.xpbootcamp.locker;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartLockerRobot {
    private List<Locker> managedLockers;

    public SmartLockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public LockerTicket store(Bag bag) {
        return managedLockers.stream().sorted(Comparator.comparing(Locker::getAvailableCapability).reversed()).findFirst().get().storeBag(bag);
    }

    public Bag getBag(LockerTicket lockerTicket) {
        Optional<Locker> goalLocker = managedLockers.stream().filter(locker -> locker.hasBag(lockerTicket)).findAny();
        if (goalLocker.isPresent()) return goalLocker.get().getBag(lockerTicket);
        throw new InvalidTicketException();
    }
}
