package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Optional;

public class LockerRobotManager extends LockerRobot {
    private List<LockerRobot> managedRobots;

    public LockerRobotManager(List<Locker> managedLockers, List<LockerRobot> managedRobots) {
        super(managedLockers);
        this.managedRobots = managedRobots;
    }

    @Override
    public LockerTicket store(Bag bag) {

        if (!managedRobots.isEmpty()) {
            Optional<Locker> goalLocker = managedRobots.get(0).getManagedLockers().stream().filter(Locker::canStoreBag).findFirst();

            if (goalLocker.isPresent()) {
                return goalLocker.get().storeBag(bag);
            }
        }

        return getAvailableLockerInSequence(managedLockers).storeBag(bag);

    }
}
