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
}
