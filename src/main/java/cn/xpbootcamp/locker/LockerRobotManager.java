package cn.xpbootcamp.locker;

import java.util.List;

public class LockerRobotManager extends LockerRobot {
    public LockerRobotManager(List<Locker> managedLockers, List<LockerRobot> managedRobots) {
        super(managedLockers);
    }

    @Override
    public LockerTicket store(Bag bag) {
        return managedLockers.get(0).storeBag(bag);
    }
}
