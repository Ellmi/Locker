package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Optional;

public abstract class LockerRobot {
    protected List<Locker> managedLockers;

    protected LockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    protected boolean canStoreBag() {
        return managedLockers.stream().filter(Locker::canStoreBag).findFirst().isPresent();
    }

    protected boolean hasBag(LockerTicket lockerTicket) {
        return managedLockers.stream().filter(locker -> locker.hasBag(lockerTicket)).findAny().isPresent();
    }

    protected abstract LockerTicket store(Bag bag);

    public Bag getBag(LockerTicket lockerTicket) {
        return getGoalLocker(lockerTicket).getBag(lockerTicket);
    }

    protected Locker getGoalLocker(LockerTicket lockerTicket) {
        Optional<Locker> goalLocker = managedLockers.stream().filter(locker -> locker.hasBag(lockerTicket)).findAny();
        if (goalLocker.isPresent()) {
            return goalLocker.get();
        }
        throw new InvalidTicketException();
    }

    protected Locker getAvailableLockerInSequence(List<Locker> managedLockers) {
        Optional<Locker> goalLocker = managedLockers.stream().filter(Locker::canStoreBag).findFirst();

        if (goalLocker.isPresent()) {
            return goalLocker.get();
        }
        throw new LockerIsFullException();
    }
}
