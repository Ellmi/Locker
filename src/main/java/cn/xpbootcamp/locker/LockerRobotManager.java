package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LockerRobotManager {
    private List<Locker> managedLockers;
    private List<LockerRobot> managedRobots;

    public LockerRobotManager(List<Locker> managedLockers, List<LockerRobot> managedRobots) {
        this.managedLockers = managedLockers;
        this.managedRobots = managedRobots;
    }


    public LockerTicket store(Bag bag) {

        Optional<LockerRobot> goalRobot = managedRobots.stream().filter(LockerRobot::canStoreBag).findFirst();

        if (goalRobot.isPresent()) {
            return goalRobot.get().store(bag);
        }

        Optional<Locker> goalLocker = managedLockers.stream().filter(Locker::canStoreBag).findFirst();

        if (goalLocker.isPresent()) {
            return goalLocker.get().storeBag(bag);
        }

        throw new LockerIsFullException();
    }


    public Bag getBag(LockerTicket lockerTicket) {

        Optional<LockerRobot> goalRobot = managedRobots.stream().filter(lockerRobot -> lockerRobot.hasBag(lockerTicket)).findAny();

        if (goalRobot.isPresent()) {
            return goalRobot.get().getBag(lockerTicket);
        }

        Optional<Locker> goalLocker = managedLockers.stream().filter(locker -> locker.hasBag(lockerTicket)).findAny();

        if (goalLocker.isPresent()) {
            return goalLocker.get().getBag(lockerTicket);
        }
        throw new InvalidTicketException();
    }

    String report() {
        return "M" + "  " + getFreeCapacity() + " " + getAllCapacity() + "\n" + managedLockers.stream().map(locker -> "   " + locker.report()).collect(Collectors.joining())
                + managedRobots.get(0).report().lines().map(item -> "   " + item + "\n").collect(Collectors.joining());
    }

    private Integer getAllCapacity() {
        return managedLockers.stream().map(locker -> locker.getCapacity())
                .reduce(0, Integer::sum) + managedRobots.stream().map(robot -> robot.getAllCapacity())
                .reduce(0, Integer::sum);
    }

    private Integer getFreeCapacity() {
        return managedLockers.stream().map(locker -> locker.getAvailableCapability())
                .reduce(0, Integer::sum) + managedRobots.stream().map(robot -> robot.getFreeCapacity())
                .reduce(0, Integer::sum);
    }
}
