package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class PrimaryLockerRobot {
    private List<Locker> managedLockers;

    public PrimaryLockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public LockerTicket store(Bag bag) {
        Optional<Locker> firstAvailableLocker = managedLockers.stream().filter(Locker::canStoreBag).findFirst();
        if (!firstAvailableLocker.isPresent()) throw new LockerIsFullException();
        return firstAvailableLocker.get().storeBag(bag);
    }

    public Bag getBag(LockerTicket lockerTicket) {
        List<Locker> goalLocker = managedLockers.stream().filter(locker -> locker.getTicketBagMap().keySet().contains(lockerTicket)).collect(toList());
        if (goalLocker.isEmpty()) throw new InvalidTicketException();
        return goalLocker.get(0).getBag(lockerTicket);
    }
}
