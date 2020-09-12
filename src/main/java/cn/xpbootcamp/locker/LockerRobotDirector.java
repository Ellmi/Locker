package cn.xpbootcamp.locker;

import java.util.List;
import java.util.stream.Collectors;

public class LockerRobotDirector {
    List<LockerRobotManager> managedLockerRobotManager;

    public LockerRobotDirector(List<LockerRobotManager> managedlockerRobotManager) {
        this.managedLockerRobotManager = managedlockerRobotManager;
    }

    public String getReport() {
        return managedLockerRobotManager.stream().map(manager -> manager.report()).collect(Collectors.joining());
    }

}
