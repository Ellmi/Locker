package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Optional;

import static cn.xpbootcamp.locker.ErrorMessageConstant.NO_ROOM_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.FAILED;

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
}
