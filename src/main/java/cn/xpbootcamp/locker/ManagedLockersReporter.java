package cn.xpbootcamp.locker;

import java.util.List;
import java.util.stream.Collectors;

public class ManagedLockersReporter {

    public String report(List<Locker> managedLockers) {
        return managedLockers.stream().map(locker -> "   " + locker.report()).collect(Collectors.joining());
    }
}
