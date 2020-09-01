package cn.xpbootcamp.locker;

import java.util.UUID;

public class LockerTicket {
    private UUID id;

    public LockerTicket() {
        this.id = UUID.randomUUID();
    }

}
