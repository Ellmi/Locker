package cn.xpbootcamp.locker;

public class Locker {
    private int lockerCapbility;
    private int lockerStored;

    public Locker(int lockerCapbility, int lockerStored) {
        this.lockerCapbility = lockerCapbility;
        this.lockerStored = lockerStored;
    }

    public StoreResult store() {
        return new StoreResult();
    }
}
