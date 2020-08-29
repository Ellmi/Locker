package cn.xpbootcamp.locker;

public class ClaimResult {
    private LockerOperateStatusEnum status;

    public ClaimResult(LockerOperateStatusEnum statusEnum) {
        this.status = statusEnum;
    }

    public LockerOperateStatusEnum getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return "Locker ticket is invalid";
    }
}
