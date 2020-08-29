package cn.xpbootcamp.locker;

public class LockerTicket {
    private int id;
    private boolean used;

    public LockerTicket(int id) {
        this.id = id;
        this.used = false;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

}
