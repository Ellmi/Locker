package cn.xpbootcamp.locker;

import static cn.xpbootcamp.locker.LockerOperateStatusEnum.SUCCESS;

public class StoreResult {
    public LockerOperateStatusEnum getStatus() {
        return SUCCESS;
    }

    public LockerTicket getTicket() {
        return new LockerTicket();
    }
}
