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
}
