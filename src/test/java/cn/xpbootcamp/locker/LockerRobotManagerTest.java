package cn.xpbootcamp.locker;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class LockerRobotManagerTest {

    @Test
    public void should_return_ticket_and_store_into_locker1_when_store_bag_given_robot_only_manage_lockers_which_all_have_free_capacity() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(locker1, locker2), List.of());
        Bag storedBag = new Bag();

        LockerTicket lockerTicket = lockerRobotManager.store(storedBag);

        assertNotNull(lockerTicket);
        assertSame(storedBag, locker1.getBag(lockerTicket));

    }


    @Test
    public void should_return_ticket_and_store_into_locker2_when_store_bag_given_robot_only_manage_lockers_locker1_is_full_but_locker2_not() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(locker1, locker2), List.of());
        lockerRobotManager.store(new Bag());
        Bag storedBag = new Bag();

        LockerTicket lockerTicket = lockerRobotManager.store(storedBag);

        assertNotNull(lockerTicket);
        assertSame(storedBag, locker2.getBag(lockerTicket));

    }


    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_store_bag_given_robot_only_manage_lockers_which_are_all_full() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(locker1, locker2), List.of());
        lockerRobotManager.store(new Bag());
        lockerRobotManager.store(new Bag());

        lockerRobotManager.store(new Bag());

    }


    @Test
    public void should_return_ticket_and_store_into_robot_locker_when_store_bag_given_robot_manage_one_locker_not_full_and_one_robot_whose_locker_not_full_too() {
        Locker locker = new Locker(1);
        Locker robotLocker = new Locker(1);
        LockerRobot lockerRobot = new PrimaryLockerRobot(List.of(robotLocker));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(locker), List.of(lockerRobot));
        Bag storedBag = new Bag();

        LockerTicket lockerTicket = lockerRobotManager.store(storedBag);

        assertNotNull(lockerTicket);
        assertSame(storedBag, robotLocker.getBag(lockerTicket));

    }


    @Test
    public void should_return_ticket_and_store_into_manager_locker_when_store_bag_given_robot_manage_one_locker_not_full_and_one_robot_whose_locker_already_full() {
        Locker managerLocker = new Locker(1);
        Locker robotLocker = new Locker(1);
        LockerRobot lockerRobot = new PrimaryLockerRobot(List.of(robotLocker));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(managerLocker), List.of(lockerRobot));
        lockerRobotManager.store(new Bag());
        Bag storedBag = new Bag();

        LockerTicket lockerTicket = lockerRobotManager.store(storedBag);

        assertNotNull(lockerTicket);
        assertSame(storedBag, managerLocker.getBag(lockerTicket));

    }


    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_store_bag_given_robot_manage_one_full_locker_and_one_robot_whose_locker_full_too() {
        Locker managerLocker = new Locker(1);
        Locker robotLocker = new Locker(1);
        LockerRobot lockerRobot = new PrimaryLockerRobot(List.of(robotLocker));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(managerLocker), List.of(lockerRobot));
        lockerRobotManager.store(new Bag());
        lockerRobotManager.store(new Bag());

        lockerRobotManager.store(new Bag());

    }


    @Test
    public void should_return_ticket_and_store_into_first_robot_locker_when_store_bag_given_robot_only_manage_two_robots_and_their_lockers_all_available() {
        Locker firstRobotLocker = new Locker(1);
        Locker secondRobotLocker = new Locker(1);
        LockerRobot firstRobot = new PrimaryLockerRobot(List.of(firstRobotLocker));
        LockerRobot secondRobot = new SmartLockerRobot(List.of(secondRobotLocker));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(), List.of(firstRobot, secondRobot));
        Bag storedBag = new Bag();

        LockerTicket lockerTicket = lockerRobotManager.store(storedBag);

        assertNotNull(lockerTicket);
        assertSame(storedBag, firstRobotLocker.getBag(lockerTicket));

    }


    @Test
    public void should_return_ticket_and_store_into_second_robot_locker_when_store_bag_given_robot_only_manage_two_robots_and_the_locker_of_first_is_full_but_second_not() {
        Locker firstRobotLocker = new Locker(1);
        Locker secondRobotLocker = new Locker(1);
        LockerRobot firstRobot = new PrimaryLockerRobot(List.of(firstRobotLocker));
        LockerRobot secondRobot = new SmartLockerRobot(List.of(secondRobotLocker));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(), List.of(firstRobot, secondRobot));
        lockerRobotManager.store(new Bag());
        Bag storedBag = new Bag();

        LockerTicket lockerTicket = lockerRobotManager.store(storedBag);

        assertNotNull(lockerTicket);
        assertSame(storedBag, secondRobotLocker.getBag(lockerTicket));

    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_store_bag_given_robot_only_manage_two_robots_and_their_lockers_all_full() {
        Locker firstRobotLocker = new Locker(1);
        Locker secondRobotLocker = new Locker(1);
        LockerRobot firstRobot = new PrimaryLockerRobot(List.of(firstRobotLocker));
        LockerRobot secondRobot = new SmartLockerRobot(List.of(secondRobotLocker));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(), List.of(firstRobot, secondRobot));
        lockerRobotManager.store(new Bag());
        lockerRobotManager.store(new Bag());
        Bag storedBag = new Bag();

        lockerRobotManager.store(storedBag);

    }
}
