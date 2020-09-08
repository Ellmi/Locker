package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Optional;

public class LockerRobotManager extends LockerRobot {
    public LockerRobotManager(List<Locker> managedLockers, List<LockerRobot> managedRobots) {
        super(managedLockers);
    }

    @Override
    public LockerTicket store(Bag bag) {
        Optional<Locker> goalLocker = managedLockers.stream().filter(Locker::canStoreBag).findFirst();
        if (goalLocker.isPresent()) {
            return goalLocker.get().storeBag(bag);
        }
        throw new LockerIsFullException();
    }
}
