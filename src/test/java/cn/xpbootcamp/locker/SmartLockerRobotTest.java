package cn.xpbootcamp.locker;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertSame;

public class SmartLockerRobotTest {

    //        1. Given SmartLockerRobot管理2个locker,locker1剩余容量为2,locker2剩余容量为1     When SmartLockerRobot存包     Then 成功存包到locker1,返回小票
    //        2. Given SmartLockerRobot管理2个locker,locker1剩余容量为1,locker2剩余容量为2     When SmartLockerRobot存包     Then 成功存包到locker2,返回小票
    //        3. Given SmartLockerRobot管理2个locker,剩余容量均为1                            When SmartLockerRobot存包     Then 成功存包到locker1,返回小票
    //        4. Given SmartLockerRobot管理2个locker,均已存满                                When SmartLockerRobot存包     Then 存包失败，提示储物柜已满
    //        5. Given 有效小票                                                             When SmartLockerRobot取包     Then 取包成功
    //        6. Given 假的小票                                                             When SmartLockerRobot取包     Then 取包失败，提示票据无效

    @Test
    public void should_return_ticket_and_store_into_locker1_when_store_bag_given_managed_locker1_free_capability_more_than_locker2() {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(List.of(locker1, locker2));
        Bag storingBag = new Bag();

        LockerTicket lockerTicket = smartLockerRobot.store(storingBag);

        assertNotNull(lockerTicket);
        assertSame(storingBag, locker1.getBag(lockerTicket));

    }

    @Test
    public void should_return_ticket_and_store_into_locker2_when_store_bag_given_managed_locker2_free_capability_more_than_locker1() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(List.of(locker1, locker2));
        Bag storingBag = new Bag();

        LockerTicket lockerTicket = smartLockerRobot.store(storingBag);

        assertNotNull(lockerTicket);
        assertSame(storingBag, locker2.getBag(lockerTicket));

    }

    @Test
    public void should_return_ticket_and_store_in_sequence_when_store_bag_given_managed_locker_has_same_not_zero_free_capability() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(List.of(locker1, locker2));
        Bag storingBag = new Bag();

        LockerTicket lockerTicket = smartLockerRobot.store(storingBag);

        assertNotNull(lockerTicket);
        assertSame(storingBag, locker1.getBag(lockerTicket));

    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_store_bag_given_managed_locker_all_full() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        locker1.storeBag(new Bag());
        locker2.storeBag(new Bag());
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(List.of(locker1, locker2));
        Bag storingBag = new Bag();

        smartLockerRobot.store(storingBag);

    }

    @Test
    public void should_return_correct_bag_when_get_bag_given_valid_ticket() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(List.of(locker1, locker2));
        smartLockerRobot.store(new Bag());
        Bag storingBag = new Bag();
        LockerTicket lockerTicket = smartLockerRobot.store(storingBag);

        Bag gettingBag = smartLockerRobot.getBag(lockerTicket);

        assertSame(storingBag, gettingBag);

    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_InvalidTicketException_when_get_bag_given_fake_ticket() {
        Locker locker1 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(List.of(locker1));

        smartLockerRobot.getBag(new LockerTicket());

    }
}
