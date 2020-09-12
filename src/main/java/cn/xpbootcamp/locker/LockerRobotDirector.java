package cn.xpbootcamp.locker;

import java.util.List;

public class LockerRobotDirector {
    List<LockerRobotManager> managedLockerRobotManager;

    public LockerRobotDirector(List<LockerRobotManager> managedlockerRobotManager) {
        this.managedLockerRobotManager = managedlockerRobotManager;
    }

    public String getReport() {
        return managedLockerRobotManager.get(0).report();
    }

}
