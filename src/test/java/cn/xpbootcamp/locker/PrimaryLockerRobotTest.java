package cn.xpbootcamp.locker;

import org.junit.Test;

import java.util.ArrayList;

import static cn.xpbootcamp.locker.LockerOperateStatusEnum.SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PrimaryLockerRobotTest {

    //        1. Given primaryLockerRobot管理n>0个locker, n个locker都有空柜                     When primaryLockerRobot存包   Then 成功存包到第1个locker，返回小票
    //        2. Given primaryLockerRobot管理n>1个locker, 第1个locker已存满,第2个locker有空柜    When primaryLockerRobot存包   Then 成功存包到第2个locker，返回小票
    //        3. Given primaryLockerRobot管理n>0个locker, n个locker都已存满                     When primaryLockerRobot存包   Then 存包失败，提示储物柜已满
    //        3. Given 有效小票   When primaryLockerRobot取包  Then 取包成功
    //        5. Given 假的小票   When primaryLockerRobot取包  Then 取包失败，提示票据无效

    private final int LOCKER_CAPABILITY_TEN = 10;
    private final int LOCKER_CAPABILITY_ONE = 1;
    private final int STORE_BAG_SIZE_ONE = 1;
    private final int STORE_BAG_SIZE_ZERO = 0;

    @Test
    public void should_store_success_into_locker_no1_and_provide_ticket_when_store_bag_given_primaryLockerRobot_manage_more_then_0_locker_which_all_not_full() {

        Locker locker1 = new Locker(LOCKER_CAPABILITY_TEN);
        Locker locker2 = new Locker(LOCKER_CAPABILITY_TEN);
        ArrayList<Locker> managedLockers = new ArrayList<>();
        managedLockers.add(locker1);
        managedLockers.add(locker2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot();
        primaryLockerRobot.setManagedLockers(managedLockers);


        StoreBagResult storeBagResult = primaryLockerRobot.store(new Bag());

        assertEquals(SUCCESS, storeBagResult.getStatus());
        assertNotNull(storeBagResult.getLockerTicket());
        assertEquals(STORE_BAG_SIZE_ONE, locker1.getTicketBagMap().size());
        assertEquals(STORE_BAG_SIZE_ZERO, locker2.getTicketBagMap().size());

    }

    @Test
    public void should_store_success_into_locker_no2_and_provide_ticket_when_store_bag_given_primaryLockerRobot_manage_more_then_1_locker_which_no1_is_full_but_no2_not() {

        Locker locker1 = new Locker(LOCKER_CAPABILITY_ONE);
        Locker locker2 = new Locker(LOCKER_CAPABILITY_TEN);
        ArrayList<Locker> managedLockers = new ArrayList<>();
        managedLockers.add(locker1);
        managedLockers.add(locker2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot();
        primaryLockerRobot.setManagedLockers(managedLockers);
        primaryLockerRobot.store(new Bag());


        StoreBagResult storeBagResult = primaryLockerRobot.store(new Bag());

        assertEquals(SUCCESS, storeBagResult.getStatus());
        assertNotNull(storeBagResult.getLockerTicket());
        assertEquals(STORE_BAG_SIZE_ONE, locker1.getTicketBagMap().size());
        assertEquals(STORE_BAG_SIZE_ONE, locker2.getTicketBagMap().size());

    }
}
