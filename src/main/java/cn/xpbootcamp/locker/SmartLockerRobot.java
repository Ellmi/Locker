package cn.xpbootcamp.locker;

import java.util.Comparator;
import java.util.List;

public class SmartLockerRobot {
    private List<Locker> managedLockers;

    public SmartLockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public LockerTicket store(Bag bag) {
        return managedLockers.stream().sorted(Comparator.comparing(Locker::getAvailableCapability).reversed()).findFirst().get().storeBag(bag);
    }

    public Bag getBag(LockerTicket lockerTicket) {
        return managedLockers.stream().filter(locker -> locker.hasBag(lockerTicket)).findAny().get().getBag(lockerTicket);
    }
}
