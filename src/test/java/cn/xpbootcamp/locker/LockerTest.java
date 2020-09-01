package cn.xpbootcamp.locker;

import org.junit.Test;

import java.util.ArrayList;

import static cn.xpbootcamp.locker.ErrorMessageConstant.NO_ROOM_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.ErrorMessageConstant.TICKET_INVALID_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.FAILED;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.SUCCESS;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LockerTest {

//    1. Given Locker有空柜  When 存包 Then 存包成功，给小票
//    2. Given Locker柜已满  When 存包 Then 存包失败，提示用户柜已满
//    3. Given 有效小票               When 取包 Then 取包成功
//    4. Given 已使用过的小票          When 取包 Then 取包失败，提示票据无效
//    5. Given 假的小票               When 取包 Then 取包失败，提示票据无效

    private final int LOCKER_CAPABILITY_TEN = 10;
    private final int LOCKER_CAPABILITY_ONE = 1;
    private final int existedTicketId = 1;
    private final int fakeTicketId = 100;

    @Test
    public void should_store_success_and_provide_ticket_when_store_bag_given_locker_has_room() {

        Locker locker = new Locker(LOCKER_CAPABILITY_TEN);

        StoreBagResult result = locker.storeBag(new Bag());

        assertEquals(SUCCESS, result.getStatus());
        assertThat(result.getTicket(), instanceOf(LockerTicket.class));

    }

    @Test
    public void should_store_failed_and_show_no_room_message_when_store_bag_given_locker_has_no_room() {

        Locker locker = new Locker(LOCKER_CAPABILITY_ONE);
        locker.storeBag(new Bag());

        StoreBagResult result = locker.storeBag(new Bag());

        assertEquals(FAILED, result.getStatus());
        assertEquals(NO_ROOM_ERROR_MESSAGE, result.getErrorMessage());

    }

    @Test
    public void should_claim_success_when_claim_bag_given_unused_ticket() {

        LockerTicket ticket = new LockerTicket(existedTicketId);
        Locker locker = buildLockerWithOneTicket(ticket);

        GetBagResult result = locker.getBag(ticket);

        assertEquals(SUCCESS, result.getStatus());

    }

    @Test
    public void should_claim_failed_when_claim_bag_given_used_ticket() {

        LockerTicket lockerTicket = new LockerTicket(existedTicketId);
        lockerTicket.setUsed(true);
        Locker locker = buildLockerWithOneTicket(lockerTicket);

        GetBagResult result = locker.getBag(lockerTicket);

        assertEquals(FAILED, result.getStatus());
        assertEquals(TICKET_INVALID_ERROR_MESSAGE, result.getErrorMessage());
    }


    @Test
    public void should_claim_failed_when_claim_bag_given_fake_ticket() {

        Locker locker = buildLockerWithOneTicket(new LockerTicket(existedTicketId));
        LockerTicket lockerTicket = new LockerTicket(fakeTicketId);

        GetBagResult result = locker.getBag(lockerTicket);

        assertEquals(FAILED, result.getStatus());
        assertEquals(TICKET_INVALID_ERROR_MESSAGE, result.getErrorMessage());
    }

    private Locker buildLockerWithOneTicket(LockerTicket lockerTicket) {
        Locker locker = new Locker(LOCKER_CAPABILITY_TEN);

        ArrayList<LockerTicket> tickets = new ArrayList<>();
        tickets.add(lockerTicket);
        locker.setTickets(tickets);

        return locker;
    }

}
