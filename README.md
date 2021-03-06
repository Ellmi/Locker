# Locker

## 开发环境
 - JDK1.8+
 
## 业务需求

需求描述：储物柜(Locker)可以存包、取包
![locker](./locker.png)

评分标准：参考Classroom中的评分标准Excel文档

### 需求澄清总结：
1. 储物柜没有容量限制
2. 储物柜没有尺寸限制，默认多大的包都能存
3. 硬件系统功能不需要考虑（开门/关门/按钮/停电/没票纸）
4. 存包失败，需要提示用户是因为储物柜满了
5. 取包失败，需要提示用户是因为票据无效
6. 存包的位置是随机，没有顺序
7. 不要脑补需求，及时和PO确认
8. 不考虑并发

### TASKING
1. Given Locker有空柜和待存包包  When 存包 Then 存包成功，给小票
2. Given Locker柜已满和待存包包  When 存包 Then 存包失败，提示用户柜已满
3. Given 有效小票               When 取包 Then 取包成功
4. Given 已使用过的小票          When 取包 Then 取包失败，提示票据无效
5. Given 假的小票               When 取包 Then 取包失败，提示票据无效

### Primary Locker Robot TASKING
1. Given primaryLockerRobot管理n>0个locker, n个locker都有空柜                     When primaryLockerRobot存包   Then 成功存包到第1个locker，返回小票
2. Given primaryLockerRobot管理n>1个locker, 第1个locker已存满,第2个locker有空柜    When primaryLockerRobot存包   Then 成功存包到第2个locker，返回小票
3. Given primaryLockerRobot管理n>0个locker, n个locker都已存满                     When primaryLockerRobot存包   Then 存包失败，提示储物柜已满
4. Given primaryLockerRobot管理n>0个locker 以及有效小票                           When primaryLockerRobot取包   Then 取包成功
5. Given 假的小票   When primaryLockerRobot取包  Then 取包失败，提示票据无效


### Smart Locker Robot TASKING
1. Given SmartLockerRobot管理2个locker,locker1剩余容量为2,locker2剩余容量为1     When SmartLockerRobot存包     Then 成功存包到locker1,返回小票
2. Given SmartLockerRobot管理2个locker,locker1剩余容量为1,locker2剩余容量为2     When SmartLockerRobot存包     Then 成功存包到locker2,返回小票
3. Given SmartLockerRobot管理2个locker,剩余容量均为1                            When SmartLockerRobot存包     Then 成功存包到locker1,返回小票
4. Given SmartLockerRobot管理2个locker,均已存满                                When SmartLockerRobot存包     Then 存包失败，提示储物柜已满
5. Given 有效小票                                                             When SmartLockerRobot取包     Then 取包成功
6. Given 假的小票                                                             When SmartLockerRobot取包     Then 取包失败，提示票据无效

### Locker Robot Manager TASKING
#### 存包
1.  Given LockerRobotManager管理多个locker,例如2个,均有剩余容量,且不管理robot
    When LockerRobotManager存包
    Then 成功存包到第1个locker, 返回小票

2.  Given LockerRobotManager管理多个locker,例如2个,第1个locker已存满,第2个locker有剩余容量,且不管理robot
    When LockerRobotManager存包
    Then 成功存包到第2个locker, 返回小票

3.  Given LockerRobotManager管理多个locker,例如2个,2个locker均已存满,且不管理robot
    When LockerRobotManager存包
    Then 存包失败，提示储物柜已满

4.  Given LockerRobotManager管理1个locker,locker有剩余容量,并且管理1个robot,robot管理的locker有剩余容量
    When LockerRobotManager存包
    Then 成功存包到robot管理的locker, 返回小票

5.  Given LockerRobotManager管理1个locker,locker有剩余容量,并且管理1个robot,robot管理的locker已存满
    When LockerRobotManager存包
    Then 成功存包到LockerRobotManager自己管理的locker, 返回小票

6.  Given LockerRobotManager管理1个locker,locker已存满,并且管理1个robot,robot管理的locker已存满
    When LockerRobotManager存包
    Then 存包失败，提示储物柜已满

7.  Given LockerRobotManager管理0个locker,并且管理多个robot,例如2个,2个robot管理的locker均有剩余容量
    When LockerRobotManager存包
    Then 成功存包到第1个robot管理的locker, 返回小票

8.  Given LockerRobotManager管理0个locker,并且管理多个robot,例如2个,第1个robot管理的locker已存满,第2个robot管理的locker有剩余容量
    When LockerRobotManager存包
    Then 成功存包到第2个robot管理的locker, 返回小票

9.  Given LockerRobotManager管理0个locker,并且管理多个robot,例如2个,2个robot管理的locker均已存满
    When LockerRobotManager存包
    Then 存包失败，提示储物柜已满

#### 取包

10. Given LockerRobotManager管理多个locker,例如2个,并且未管理robot,以及一张有效小票
    When LockerRobotManager取包
    Then 取包成功

11. Given LockerRobotManager管理多个locker,例如2个,并且未管理robot,以及一张无效小票
    When LockerRobotManager取包
    Then 取包失败，提示票据无效

12. Given LockerRobotManager管理1个locker,并且管理1个robot,包存于robot的locker中,以及一张有效小票
   When LockerRobotManager取包
   Then 取包成功

13. Given LockerRobotManager管理1个locker,并且管理1个robot,包存于locker中,以及一张有效小票
   When LockerRobotManager取包
   Then 取包成功

14. Given LockerRobotManager管理1个locker,并且管理1个robot,以及一张无效小票
   When LockerRobotManager取包
   Then 取包失败，提示票据无效

15. Given LockerRobotManager未管理locker,并且管理多个robot,例如2个,以及一张有效小票
    When LockerRobotManager取包
    Then 取包成功

16. Given LockerRobotManager未管理locker,并且管理多个robot,例如2个,以及一张无效小票
    When LockerRobotManager取包
    Then 取包失败，提示票据无效

### Locker Robot Director TASKING
1. Given LockerRobotDirector管理1个LockerRobotManager,该LockerRobotManager管理1个Locker,该Locker可用容量和容量为：0，1
   When LockerRobotDirector统计报表
   Then 报表内容为：
        M 0,1
          L 0,1


2. Given LockerRobotDirector管理1个LockerRobotManager,该LockerRobotManager管理2个Locker,Locker可用容量和容量分别为：1，2；3，3
   When LockerRobotDirector统计报表
   Then 报表内容为：
        M 4,5
          L 1,2
          L 3,3


3. Given LockerRobotDirector管理1个LockerRobotManager,该LockerRobotManager管理1个LockerRobot,该LockerRobot管理一个Locker,该Locker可用容量和容量为：1，2
   When LockerRobotDirector统计报表
   Then 报表内容为：
        M 1,2
          R 1,2
            L 1,2


4. Given LockerRobotDirector管理1个LockerRobotManager,该LockerRobotManager管理1个LockerRobot,该LockerRobot管理2个Locker,且Locker可用容量和容量均为：3，3
   When LockerRobotDirector统计报表
   Then 报表内容为：
        M 6,6
          R 6,6
            L 3,3
            L 3,3


5. Given LockerRobotDirector管理1个LockerRobotManager,该LockerRobotManager管理2个LockerRobot,2个LockerRobot各管理一个Locker,Locker可用容量和容量均为：1，2
   When LockerRobotDirector统计报表
   Then 报表内容为：
        M 2,4
          R 1,2
            L 1,2
          R 1,2
            L 1,2


6. Given LockerRobotDirector管理1个LockerRobotManager,该LockerRobotManager管理1个Locker和1个LockerRobot,该LockerRobot管理一个Locker,
   该Locker可用容量和容量为：2，2，LockerRobotManager直接管理的Locker可用容量和容量为：0，1
   When LockerRobotDirector统计报表
   Then 报表内容为：
        M 2,3
          L 2,2
          R 0,1
            L 0,1


7. Given LockerRobotDirector管理2个LockerRobotManager,每个LockerRobotManager各管理1个Locker，Locker的可用容量和容量分别为：1，1；2，2
   When LockerRobotDirector统计报表
   Then 报表内容为：
        M 1,1
          L 1,1
        M 2,2
          L 2,2


8. Given LockerRobotDirector管理1个LockerRobotManager,该LockerRobotManager管理1个Locker,该Locker可用容量和容量为：0，1;
   存在一个不被LockerRobotManager管理的Locker，可用容量和容量为：2，2;存在一个不被LockerRobotManager管理的Robot，该Robot管理一个Locker,可用容量和容量为：4，6;
   When LockerRobotDirector统计报表
   Then 报表内容为：
        M 0,1
          L 0,1