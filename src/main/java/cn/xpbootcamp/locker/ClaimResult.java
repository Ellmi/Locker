package cn.xpbootcamp.locker;

public class ClaimResult extends OperateResult {

    public ClaimResult(LockerOperateStatusEnum status, String errorMessage) {
        super(status, errorMessage);
    }
}
