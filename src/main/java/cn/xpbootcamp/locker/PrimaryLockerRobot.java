package cn.xpbootcamp.locker;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static cn.xpbootcamp.locker.ErrorMessageConstant.NO_ROOM_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.ErrorMessageConstant.TICKET_INVALID_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.FAILED;
import static java.util.stream.Collectors.toList;

public class PrimaryLockerRobot {
    private List<Locker> managedLockers;

    public void setManagedLockers(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public StoreBagResult store(Bag bag) {
        Optional<Locker> firstAvailableLocker = managedLockers.stream().filter(locker -> locker.getAvailableCapability() > 0).findFirst();
        if (!firstAvailableLocker.isPresent()) return new StoreBagResult(FAILED, NO_ROOM_ERROR_MESSAGE);
        return firstAvailableLocker.get().storeBag(bag);
    }

    public GetBagResult getBag(LockerTicket lockerTicket) {
        List<Locker> ticketContainedLocker = managedLockers.stream().filter(locker -> locker.getTicketBagMap().keySet().contains(lockerTicket)).collect(toList());
        if (ticketContainedLocker.size() != 1) return new GetBagResult(FAILED, TICKET_INVALID_ERROR_MESSAGE);
        return ticketContainedLocker.get(0).getBag(lockerTicket);
    }
}
