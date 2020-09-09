package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot extends LockerRobot {

    public PrimaryLockerRobot(List<Locker> managedLockers) {
        super(managedLockers);
    }

    public LockerTicket store(Bag bag) {
        Optional<Locker> goalLocker = managedLockers.stream().filter(Locker::canStoreBag).findFirst();

        if (goalLocker.isPresent()) {
            return goalLocker.get().storeBag(bag);
        }
        throw new LockerIsFullException();
    }

}
