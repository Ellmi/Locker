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
    
12. Given LockerRobotManager未管理locker,并且管理多个robot,例如2个,以及一张有效小票
    When LockerRobotManager取包
    Then 取包成功

13. Given LockerRobotManager未管理locker,并且管理多个robot,例如2个,以及一张无效小票
    When LockerRobotManager取包
    Then 取包失败，提示票据无效

14. Given LockerRobotManager管理1个locker,并且管理1个robot,以及一张有效小票
    When LockerRobotManager取包
    Then 取包成功

15. Given LockerRobotManager管理1个locker,并且管理1个robot,以及一张无效小票
    When LockerRobotManager取包
    Then 取包失败，提示票据无效