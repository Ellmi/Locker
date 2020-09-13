package cn.xpbootcamp.locker;


public interface Reportable {
    SelfReporter selfReporter = new SelfReporter();

    String report();

    Integer getAllCapacity();

    Integer getFreeCapacity();

}
