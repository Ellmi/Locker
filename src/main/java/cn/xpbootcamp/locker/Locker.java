package cn.xpbootcamp.locker;

import static cn.xpbootcamp.locker.LockerOperateStatusEnum.FAILED;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.SUCCESS;

public class Locker {
    private int lockerCapbility;
    private int lockerStored;

    public Locker(int lockerCapbility, int lockerStored) {
        this.lockerCapbility = lockerCapbility;
        this.lockerStored = lockerStored;
    }

    public StoreResult store() {
        if (lockerStored < lockerCapbility) return new StoreResult(SUCCESS);
        return new StoreResult(FAILED);
    }
}
