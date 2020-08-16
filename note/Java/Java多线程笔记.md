[TOC]

## Java多线程笔记

### 1. 一个Java程序启动时至少有3个线程：`main` 方法线程、`gc` 线程以及异常处理线程

### 2. Java线程状态转换图

![Java线程状态转换图](https://gitee.com/cakeralter/images/raw/master/20200727210059.png)

### 3. `Thread` 使用几点总结：

1. 不能直接调用 `run()` 来启动线程

> 调用 `Thread` 的 `start()` 方法会启动 `Thread` 线程执行 `run()`，直接调用 `run()` 则只是在当前线程执行

2. 不能多次调用线程对象的 `start()` 方法，会报 `IllegalThreadStateException` 异常

### 4. 线程常用方法：

- `Thread` 类中的方法

1. **start()** ：启动 `Thread` 线程；通知JVM调用线程的 `run()`
2. **run()** ：定义当前线程具体的执行逻辑
3. **currentThread()** ：获取当前线程对象
4. **setName(String name)** ：设置线程名
5. **getName()** ：获取线程名
6. **sleep(long millis)** ：使调用线程阻塞指定毫秒时间
7. **join()** ：在线程A中调用线程B的 `join()` ，则线程A进入阻塞状态直到线程B执行完毕，之后退出阻塞重新等待CPU调度
8. **yield()** ：使调用线程释放CPU执行权，CPU重新进行调度
9. **setPriority(int newPriority)** ：设置线程优先级
10. **getPriority()** ：获取线程优先级
11. **isAlive()** ：测试线程是否存活
12. **interrupt()** ：中断线程
13. **interrupted()** ：测试线程是否已中断
14. **setDaemon(boolean on)** ：是否将线程设置为守护线程
15. **isDaemon()** ：测试线程是否为守护线程
16.  **setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh)** ：设置默认异常处理器
17.  **setUncaughtExceptionHandler(UncaughtExceptionHandler eh)** ：设置调用线程异常处理器

- `Object` 类中的方法

1. **wait() **：使调用线程进入等待状态
2. **notify()** ：通知一个等待状态的线程继续执行
3. **notifyAll()** ：通知所有等待线程继续执行

### 5. `wait()`、 `notify()` 和 `notifyAll()`

1. `wait()`、 `notify()`、`notifyAll()` 这三个方法必须在同步代码块或同步方法中调用

   ```java
   // 同步方法
   public void synchronized method() {
       // wait() / notify() / notifyAll()
   }
   // 同步代码块
   synchronized(this) {
       // wait() / notify() / notifyAll()
   }
   ```

2. `wait()`、 `notify()`、`notifyAll()` 必须由同步监视器对象调用，否则会抛异常

### 6. `sleep()` 和 `wait()`

- 相同：`sleep()` 和 `wait()` 都会使调用线程进入阻塞状态
- 不同：
  1. `sleep()` 是在 `Thread` 类中定义，`wait()` 是在 `Object` 类中定义
  2. `sleep()` 可以在任何地方调用，`wait()` 只能在同步方法或同步代码块中调用
  3. 线程调用 `wait()` 后会释放自己当前持有的锁对象，而调用 `sleep()` 则不会

### 7. `synchronized` 和 `Lock`

### 8. 创建线程的几种方式

1. 继承 `Thread` 类重写 `run()` 方法
2. 实现 `Runnable` 接口实现 `run()` 方法
3. 实现 `Callable` 接口实现 `call()` 方法
4. 使用线程池：`ExecutorService` 接口和 `ThreadPoolExecutor` 类