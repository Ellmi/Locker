package cn.xpbootcamp.locker;

import java.util.Comparator;
import java.util.List;

public class SmartLockerRobot extends LockerRobot {

    public SmartLockerRobot(List<Locker> managedLockers) {
        super(managedLockers);
    }

    public LockerTicket store(Bag bag) {
        return managedLockers.stream().sorted(Comparator.comparing(Locker::getAvailableCapability).reversed()).findFirst().get().storeBag(bag);
    }

}
