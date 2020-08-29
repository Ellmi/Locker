package cn.xpbootcamp.locker;

public class StoreResult {
    public String getStatus() {
        return "Success";
    }

    public Object getTicket() {
        return new LockerTicket();
    }
}
