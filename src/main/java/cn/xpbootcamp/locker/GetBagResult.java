package cn.xpbootcamp.locker;

public class GetBagResult extends OperateResult {
    private Bag bag;

    public GetBagResult(LockerOperateStatusEnum status, String errorMessage) {
        super(status, errorMessage);
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }
}
