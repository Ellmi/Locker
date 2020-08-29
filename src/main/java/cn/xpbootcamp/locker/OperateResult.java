package cn.xpbootcamp.locker;

public class OperateResult {
    protected LockerOperateStatusEnum status;
    protected String errorMessage;

    public OperateResult(LockerOperateStatusEnum status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public LockerOperateStatusEnum getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
