package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Optional;

public class LockerRobotManager extends LockerRobot {
    private List<LockerRobot> managedRobots;

    public LockerRobotManager(List<Locker> managedLockers, List<LockerRobot> managedRobots) {
        super(managedLockers);
        this.managedRobots = managedRobots;
    }

    @Override
    public LockerTicket store(Bag bag) {

        if (!managedRobots.isEmpty()) {
            Optional<LockerRobot> goalRobot = managedRobots.stream().filter(LockerRobot::canStoreBag).findFirst();

            if (goalRobot.isPresent()) {
                return goalRobot.get().store(bag);
            }
        }

        return getAvailableLockerInSequence(managedLockers).storeBag(bag);

    }


    @Override
    public Bag getBag(LockerTicket lockerTicket) {
        if (!managedRobots.isEmpty()) {
            return managedRobots.get(0).getBag(lockerTicket);
        }
        return getGoalLocker(lockerTicket).getBag(lockerTicket);
    }

}
