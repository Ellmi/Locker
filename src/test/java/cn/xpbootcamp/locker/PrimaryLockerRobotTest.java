package cn.xpbootcamp.locker;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PrimaryLockerRobotTest {

    //        1. Given primaryLockerRobot管理n>0个locker, n个locker都有空柜                     When primaryLockerRobot存包   Then 成功存包到第1个locker，返回小票
    //        2. Given primaryLockerRobot管理n>1个locker, 第1个locker已存满,第2个locker有空柜    When primaryLockerRobot存包   Then 成功存包到第2个locker，返回小票
    //        3. Given primaryLockerRobot管理n>0个locker, n个locker都已存满                     When primaryLockerRobot存包   Then 存包失败，提示储物柜已满
    //        4. Given primaryLockerRobot管理n>0个locker 以及有效小票                           When primaryLockerRobot取包   Then 取包成功
    //        5. Given 假的小票   When primaryLockerRobot取包  Then 取包失败，提示票据无效

    private final int LOCKER_CAPABILITY_TEN = 10;
    private final int LOCKER_CAPABILITY_ONE = 1;

    @Test
    public void should_return_ticket_and_store_bag_into_locker1_when_store_bag_given_managed_lockers_all_not_full() {

        Locker locker1 = new Locker(LOCKER_CAPABILITY_TEN);
        Locker locker2 = new Locker(LOCKER_CAPABILITY_TEN);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(locker1, locker2));

        Bag storingBag = new Bag();
        LockerTicket lockerTicket = primaryLockerRobot.store(storingBag);

        assertNotNull(lockerTicket);
        assertSame(storingBag, locker1.getBag(lockerTicket));

    }

    @Test
    public void should_return_ticket_and_store_bag_into_locker2_when_store_bag_given_managed_locker1_is_full_but_locker2_not() {

        Locker locker1 = new Locker(LOCKER_CAPABILITY_ONE);
        Locker locker2 = new Locker(LOCKER_CAPABILITY_TEN);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(locker1, locker2));
        primaryLockerRobot.store(new Bag());


        Bag storingBag = new Bag();
        LockerTicket lockerTicket = primaryLockerRobot.store(storingBag);

        assertNotNull(lockerTicket);
        assertSame(storingBag, locker2.getBag(lockerTicket));

    }


    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_store_bag_given_primaryLockerRobot_managed_lockers_all_full() {

        Locker locker1 = new Locker(LOCKER_CAPABILITY_ONE);
        Locker locker2 = new Locker(LOCKER_CAPABILITY_ONE);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(locker1, locker2));
        primaryLockerRobot.store(new Bag());
        primaryLockerRobot.store(new Bag());

        primaryLockerRobot.store(new Bag());

    }


    @Test
    public void should_return_bag_when_get_bag_given_a_valid_ticket() {

        Locker locker1 = new Locker(LOCKER_CAPABILITY_ONE);
        Locker locker2 = new Locker(LOCKER_CAPABILITY_ONE);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(locker1, locker2));
        primaryLockerRobot.store(new Bag());
        Bag storingBag = new Bag();
        LockerTicket lockerTicket = primaryLockerRobot.store(storingBag);

        Bag gettingBag = primaryLockerRobot.getBag(lockerTicket);

        assertSame(storingBag, gettingBag);

    }


    @Test(expected = InvalidTicketException.class)
    public void should_throw_InvalidTicketException_when_get_bag_given_fake_ticket() {

        Locker locker = new Locker(LOCKER_CAPABILITY_ONE);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(locker));
        primaryLockerRobot.store(new Bag());

        primaryLockerRobot.getBag(new LockerTicket());

    }
}
