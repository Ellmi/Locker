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


    @Test
    public void should_return_report_with_1_manager_and_its_2_locker_info_when_director_get_report_given_director_manage_1_manager_and_manager_manage_2_lockers() {

        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(3);
        locker1.storeBag(new Bag());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(locker1, locker2), List.of());
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(List.of(lockerRobotManager));

        String actualReport = lockerRobotDirector.getReport();

        String expectedReport = "M  4 5\n" +
                                "   L  1 2\n" +
                                "   L  3 3\n";

        assertEquals(expectedReport, actualReport);
    }

    @Test
    public void should_return_report_with_1_manager_and_its_robot_and_robot_locker_info_when_director_get_report_given_director_manage_1_manager_and_manager_manage_1_robot_with_1_locker() {

        Locker locker = new Locker(2);
        locker.storeBag(new Bag());
        PrimaryLockerRobot lockerRobot = new PrimaryLockerRobot(List.of(locker));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(), List.of(lockerRobot));
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(List.of(lockerRobotManager));

        String actualReport = lockerRobotDirector.getReport();

        String expectedReport = "M  1 2\n" +
                                "   R  1 2\n" +
                                "      L  1 2\n";

        assertEquals(expectedReport, actualReport);
    }


    @Test
    public void should_return_report_with_1_manager_and_its_2_robots_and_robot_locker_info_when_director_get_report_given_director_manage_1_manager_and_manager_manage_2_robot_and_each_robot_with_1_locker() {

        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        locker1.storeBag(new Bag());
        locker2.storeBag(new Bag());
        PrimaryLockerRobot lockerRobot1 = new PrimaryLockerRobot(List.of(locker1));
        SmartLockerRobot lockerRobot2 = new SmartLockerRobot(List.of(locker2));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(), List.of(lockerRobot1, lockerRobot2));
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(List.of(lockerRobotManager));

        String actualReport = lockerRobotDirector.getReport();

        String expectedReport = "M  2 4\n" +
                                "   R  1 2\n" +
                                "      L  1 2\n" +
                                "   R  1 2\n" +
                                "      L  1 2\n";

        assertEquals(expectedReport, actualReport);
    }


    @Test
    public void should_return_report_with_1_manager_and_its_1_robot_and_1_locker_info_when_director_get_report_given_director_manage_1_manager_and_manager_manage_1_robot_and_1_locker() {

        Locker locker = new Locker(2);
        Locker robotLocker = new Locker(1);
        robotLocker.storeBag(new Bag());
        PrimaryLockerRobot lockerRobot = new PrimaryLockerRobot(List.of(robotLocker));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(locker), List.of(lockerRobot));
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(List.of(lockerRobotManager));

        String actualReport = lockerRobotDirector.getReport();

        String expectedReport = "M  2 3\n" +
                                "   L  2 2\n" +
                                "   R  0 1\n" +
                                "      L  0 1\n";

        assertEquals(expectedReport, actualReport);
    }
}
