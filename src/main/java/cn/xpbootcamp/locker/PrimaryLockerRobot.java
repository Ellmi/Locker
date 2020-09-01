package cn.xpbootcamp.locker;

import java.util.List;

public class PrimaryLockerRobot {
    private List<Locker> managedLockers;

    public void setManagedLockers(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public StoreBagResult store(Bag bag) {
        return managedLockers.get(0).storeBag(bag);
    }
}
