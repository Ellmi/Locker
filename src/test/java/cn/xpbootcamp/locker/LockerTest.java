package cn.xpbootcamp.locker;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class LockerTest {

//    1. Given Locker有空柜  When 存包 Then 存包成功，给小票
//    2. Given Locker柜已满  When 存包 Then 存包失败，提示用户柜已满
//    3. Given 有效小票               When 取包 Then 取包成功
//    4. Given 已使用过的小票          When 取包 Then 取包失败，提示票据无效
//    5. Given 假的小票               When 取包 Then 取包失败，提示票据无效

    private final int LOCKER_CAPABILITY_TEN = 10;
    private final int LOCKER_CAPABILITY_ONE = 1;

    @Test
    public void should_return_ticket_when_store_bag_given_locker_not_full() {

        Locker locker = new Locker(LOCKER_CAPABILITY_TEN);

        LockerTicket lockerTicket = locker.storeBag(new Bag());

        assertNotNull(lockerTicket);

    }


    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_store_bag_given_locker_is_full() {

        Locker locker = new Locker(LOCKER_CAPABILITY_ONE);
        locker.storeBag(new Bag());

        locker.storeBag(new Bag());

    }


    @Test
    public void should_return_correct_bag_when_get_bag_given_valid_ticket() {

        Locker locker = new Locker(LOCKER_CAPABILITY_TEN);
        Bag storingBag = new Bag();
        LockerTicket lockerTicket = locker.storeBag(storingBag);

        Bag gettingBag = locker.getBag(lockerTicket);

        assertSame(storingBag, gettingBag);

    }


    @Test(expected = InvalidTicketException.class)
    public void should_throw_InvalidTicketException_when_get_bag_given_used_ticket() {

        Locker locker = new Locker(LOCKER_CAPABILITY_TEN);
        LockerTicket lockerTicket = locker.storeBag(new Bag());
        locker.getBag(lockerTicket);

        locker.getBag(lockerTicket);

    }


    @Test(expected = InvalidTicketException.class)
    public void should_throw_InvalidTicketException_when_get_bag_given_fake_ticket() {

        Locker locker = new Locker(LOCKER_CAPABILITY_TEN);
        locker.storeBag(new Bag());

        locker.getBag(new LockerTicket());
    }
}
