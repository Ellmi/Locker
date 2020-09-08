package cn.xpbootcamp.locker;

import java.util.List;

public class LockerRobotManager extends LockerRobot {
    public LockerRobotManager(List<Locker> managedLockers, List<LockerRobot> managedRobots) {
        super(managedLockers);
    }

    @Override
    public LockerTicket store(Bag bag) {
        return managedLockers.stream().filter(Locker::canStoreBag).findFirst().get().storeBag(bag);
    }
}
