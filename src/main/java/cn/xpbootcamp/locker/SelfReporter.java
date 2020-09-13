package cn.xpbootcamp.locker;

public class SelfReporter {

    public String report(String reportLevel, int freeCapacity, int allCapacity) {
        return reportLevel + "  " + freeCapacity + " " + allCapacity + "\n";
    }
}
