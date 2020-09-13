package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class LockerRobot implements Reportable {
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

    public String report() {
        return selfReporter.report("R", getFreeCapacity(), getAllCapacity()) + managedLockersReporter.report(managedLockers);
    }

    public Integer getAllCapacity() {
        return managedLockers.stream().map(locker -> locker.getAllCapacity())
                .reduce(0, Integer::sum);
    }

    public Integer getFreeCapacity() {
        return managedLockers.stream().map(locker -> locker.getFreeCapacity())
                .reduce(0, Integer::sum);
    }

}
