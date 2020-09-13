package cn.xpbootcamp.locker;


public interface Reportable {
    SelfReporter selfReporter = new SelfReporter();
    ManagedLockersReporter managedLockersReporter = new ManagedLockersReporter();

    String report();

    Integer getAllCapacity();

    Integer getFreeCapacity();

}
