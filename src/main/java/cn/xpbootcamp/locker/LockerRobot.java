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

        Optional<Locker> goalLocker = managedLockers.stream().filter(locker -> locker.hasBag(lockerTicket)).findAny();

        if (goalLocker.isPresent()) {
            return goalLocker.get().getBag(lockerTicket);
        }
        throw new InvalidTicketException();
    }

    String report() {
        return "R" + "  " + getFreeCapacity() + " " + getAllCapacity() + "\n   " + managedLockers.get(0).report();
    }

    Integer getAllCapacity() {
        return managedLockers.stream().map(locker -> locker.getCapacity())
                .reduce(0, Integer::sum);
    }

    Integer getFreeCapacity() {
        return managedLockers.stream().map(locker -> locker.getAvailableCapability())
                .reduce(0, Integer::sum);
    }

}
