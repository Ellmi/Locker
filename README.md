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