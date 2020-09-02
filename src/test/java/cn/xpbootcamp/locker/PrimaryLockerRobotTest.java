package cn.xpbootcamp.locker;

import org.junit.Test;

import java.util.List;

import static cn.xpbootcamp.locker.ErrorMessageConstant.TICKET_INVALID_ERROR_MESSAGE;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.FAILED;
import static cn.xpbootcamp.locker.LockerOperateStatusEnum.SUCCESS;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

public class PrimaryLockerRobotTest {

    //        1. Given primaryLockerRobot管理n>0个locker, n个locker都有空柜                     When primaryLockerRobot存包   Then 成功存包到第1个locker，返回小票
    //        2. Given primaryLockerRobot管理n>1个locker, 第1个locker已存满,第2个locker有空柜    When primaryLockerRobot存包   Then 成功存包到第2个locker，返回小票
    //        3. Given primaryLockerRobot管理n>0个locker, n个locker都已存满                     When primaryLockerRobot存包   Then 存包失败，提示储物柜已满
    //        4. Given primaryLockerRobot管理n>0个locker 以及有效小票                           When primaryLockerRobot取包   Then 取包成功
    //        5. Given 假的小票   When primaryLockerRobot取包  Then 取包失败，提示票据无效

    private final int LOCKER_CAPABILITY_TEN = 10;
    private final int LOCKER_CAPABILITY_ONE = 1;
    private final int STORE_BAG_SIZE_ONE = 1;
    private final int STORE_BAG_SIZE_ZERO = 0;

    @Test
    public void should_store_success_into_locker_no1_and_provide_ticket_when_store_bag_given_primaryLockerRobot_manage_more_then_0_locker_which_all_not_full() {

        Locker locker1 = new Locker(LOCKER_CAPABILITY_TEN);
        Locker locker2 = new Locker(LOCKER_CAPABILITY_TEN);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(locker1, locker2));


        LockerTicket lockerTicket = primaryLockerRobot.store(new Bag());

        assertNotNull(lockerTicket);
        assertEquals(STORE_BAG_SIZE_ONE, locker1.getTicketBagMap().size());
        assertEquals(STORE_BAG_SIZE_ZERO, locker2.getTicketBagMap().size());

    }

    @Test
    public void should_store_success_into_locker_no2_and_provide_ticket_when_store_bag_given_primaryLockerRobot_manage_more_then_1_locker_which_no1_is_full_but_no2_not() {

        Locker locker1 = new Locker(LOCKER_CAPABILITY_ONE);
        Locker locker2 = new Locker(LOCKER_CAPABILITY_TEN);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(locker1, locker2));
        primaryLockerRobot.store(new Bag());


        LockerTicket lockerTicket = primaryLockerRobot.store(new Bag());

        assertNotNull(lockerTicket);
        assertEquals(STORE_BAG_SIZE_ONE, locker1.getTicketBagMap().size());
        assertEquals(STORE_BAG_SIZE_ONE, locker2.getTicketBagMap().size());

    }


    @Test(expected = LockerIsFullException.class)
    public void should_store_failed_and_show_no_room_message__when_store_bag_given_primaryLockerRobot_manage_more_then_0_but_all_full() {

        Locker locker1 = new Locker(LOCKER_CAPABILITY_ONE);
        Locker locker2 = new Locker(LOCKER_CAPABILITY_ONE);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(locker1, locker2));
        primaryLockerRobot.store(new Bag());
        primaryLockerRobot.store(new Bag());


        primaryLockerRobot.store(new Bag());

    }


    @Test
    public void should_get_bag_successful_when_get_bag_given_primaryLockerRobot_manage_multiple_locker_and_valid_ticket() {

        Locker locker1 = new Locker(LOCKER_CAPABILITY_ONE);
        Locker locker2 = new Locker(LOCKER_CAPABILITY_ONE);
        Locker locker3 = new Locker(LOCKER_CAPABILITY_ONE);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(locker1, locker2, locker3));
        primaryLockerRobot.store(new Bag());
        Bag storeBag2 = new Bag();
        LockerTicket lockerTicket = primaryLockerRobot.store(storeBag2);
        primaryLockerRobot.store(new Bag());

        GetBagResult getBagResult = primaryLockerRobot.getBag(lockerTicket);


        assertSame(storeBag2, getBagResult.getBag());
        assertEquals(SUCCESS, getBagResult.getStatus());
        assertEquals(STORE_BAG_SIZE_ZERO, locker2.getTicketBagMap().size());
    }


    @Test
    public void should_get_bag_failed_and_show_invalid_ticket_message_when_get_bag_given_fake_ticket() {

        Locker locker1 = new Locker(LOCKER_CAPABILITY_ONE);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(locker1));
        primaryLockerRobot.store(new Bag());

        GetBagResult getBagResult = primaryLockerRobot.getBag(new LockerTicket());


        assertEquals(FAILED, getBagResult.getStatus());
        assertEquals(TICKET_INVALID_ERROR_MESSAGE, getBagResult.getErrorMessage());
        assertNull(getBagResult.getBag());
    }
}
