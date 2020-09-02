package cn.xpbootcamp.locker;

import org.junit.Test;

import static cn.xpbootcamp.locker.ErrorMessageConstant.TICKET_INVALID_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.FAILED;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.SUCCESS;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LockerTest {

//    1. Given Locker有空柜  When 存包 Then 存包成功，给小票
//    2. Given Locker柜已满  When 存包 Then 存包失败，提示用户柜已满
//    3. Given 有效小票               When 取包 Then 取包成功
//    4. Given 已使用过的小票          When 取包 Then 取包失败，提示票据无效
//    5. Given 假的小票               When 取包 Then 取包失败，提示票据无效

    private final int LOCKER_CAPABILITY_TEN = 10;
    private final int LOCKER_CAPABILITY_ONE = 1;

    @Test
    public void should_store_success_and_provide_ticket_when_store_bag_given_locker_not_full() {

        Locker locker = new Locker(LOCKER_CAPABILITY_TEN);

        LockerTicket lockerTicket = locker.storeBag(new Bag());

        assertNotNull(lockerTicket);

    }

    @Test(expected = LockerIsFullException.class)
    public void should_store_failed_and_show_no_room_message_when_store_bag_given_locker_has_no_room() {

        Locker locker = new Locker(LOCKER_CAPABILITY_ONE);
        locker.storeBag(new Bag());

        locker.storeBag(new Bag());

    }

    @Test
    public void should_claim_success_when_claim_bag_given_unused_ticket() {

        Locker locker = new Locker(LOCKER_CAPABILITY_TEN);
        Bag storeBag = new Bag();
        LockerTicket lockerTicket  = locker.storeBag(storeBag);

        GetBagResult result = locker.getBag(lockerTicket);

        assertEquals(SUCCESS, result.getStatus());
        assertSame(storeBag, result.getBag());

    }

    @Test
    public void should_claim_failed_when_claim_bag_given_used_ticket() {

        Locker locker = new Locker(LOCKER_CAPABILITY_TEN);
        Bag storeBag = new Bag();
        LockerTicket lockerTicket = locker.storeBag(storeBag);
        locker.getBag(lockerTicket);

        GetBagResult result = locker.getBag(lockerTicket);

        assertEquals(FAILED, result.getStatus());
        assertEquals(TICKET_INVALID_ERROR_MESSAGE, result.getErrorMessage());
    }


    @Test
    public void should_claim_failed_when_claim_bag_given_fake_ticket() {

        Locker locker = new Locker(LOCKER_CAPABILITY_TEN);
        Bag storeBag = new Bag();
        locker.storeBag(storeBag);

        GetBagResult result = locker.getBag(new LockerTicket());

        assertEquals(FAILED, result.getStatus());
        assertEquals(TICKET_INVALID_ERROR_MESSAGE, result.getErrorMessage());
    }

}
