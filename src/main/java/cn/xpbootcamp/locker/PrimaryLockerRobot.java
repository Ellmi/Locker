package cn.xpbootcamp.locker;

import java.util.List;

public class PrimaryLockerRobot extends LockerRobot {

    public PrimaryLockerRobot(List<Locker> managedLockers) {
        super(managedLockers);
    }

    public LockerTicket store(Bag bag) {
        return getAvailableLockerInSequence(managedLockers).storeBag(bag);
    }

}
