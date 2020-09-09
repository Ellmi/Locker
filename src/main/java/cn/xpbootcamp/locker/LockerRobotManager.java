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

        Optional<LockerRobot> goalRobot = managedRobots.stream().filter(LockerRobot::canStoreBag).findFirst();

        if (goalRobot.isPresent()) {
            return goalRobot.get().store(bag);
        }

        return getAvailableLockerInSequence(managedLockers).storeBag(bag);

    }


    @Override
    public Bag getBag(LockerTicket lockerTicket) {

        Optional<LockerRobot> goalRobot = managedRobots.stream().filter(lockerRobot -> lockerRobot.hasBag(lockerTicket)).findAny();

        if (goalRobot.isPresent()) {
            return goalRobot.get().getBag(lockerTicket);
        }

        return getGoalLocker(lockerTicket).getBag(lockerTicket);
    }

}
