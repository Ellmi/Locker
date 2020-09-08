package cn.xpbootcamp.locker;

import java.util.List;

public class LockerRobotManager extends LockerRobot {
    private List<LockerRobot> managedRobots;

    public LockerRobotManager(List<Locker> managedLockers, List<LockerRobot> managedRobots) {
        super(managedLockers);
        this.managedRobots = managedRobots;
    }

    @Override
    public LockerTicket store(Bag bag) {

        if (!managedRobots.isEmpty()) {

            return getAvailableLockerInSequence(managedRobots.get(0).getManagedLockers()).storeBag(bag);
        }

        return getAvailableLockerInSequence(managedLockers).storeBag(bag);

    }
}
