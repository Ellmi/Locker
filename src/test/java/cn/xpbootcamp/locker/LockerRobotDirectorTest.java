package cn.xpbootcamp.locker;

import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LockerRobotDirectorTest {

    @Test
    public void should_return_report_with_1_manager_and_its_1_locker_info_when_director_get_report_given_director_manage_1_manager_and_manager_manage_1_locker() {

        Locker locker = new Locker(1);
        locker.storeBag(new Bag());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(locker), List.of());
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(List.of(lockerRobotManager));

        String actualReport = lockerRobotDirector.getReport();

        String expectedReport = "M  0 1\n" +
                                "   L  0 1\n";

        assertEquals(expectedReport, actualReport);
    }
}
