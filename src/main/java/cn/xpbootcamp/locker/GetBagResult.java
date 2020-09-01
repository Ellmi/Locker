package cn.xpbootcamp.locker;

public class GetBagResult extends OperateResult {

    public GetBagResult(LockerOperateStatusEnum status, String errorMessage) {
        super(status, errorMessage);
    }
}
