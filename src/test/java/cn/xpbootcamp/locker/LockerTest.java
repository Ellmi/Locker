package cn.xpbootcamp.locker;

import org.junit.Test;

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

    private final int lockerCapbility = 10;
    private final int lockerStored = 5;

    @Test
    public void should_store_success_and_provide_ticket_when_store_bag_given_locker_has_room() {

        Locker locker = new Locker(lockerCapbility, lockerStored);

        StoreResult result = locker.store();

        assertEquals(SUCCESS, result.getStatus());
        assertThat(result.getTicket(), instanceOf(LockerTicket.class));

    }

}
