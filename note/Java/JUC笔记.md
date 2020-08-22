[TOC]

## JUC笔记

### 1. 多线程的几个概念

#### 1.1 进程/线程

> 进程（Process）是计算机中的程序关于某数据集合上的一次运行活动，是系统进行资源分配和调度的基本单位，是[操作系统](https://baike.baidu.com/item/操作系统)结构的基础。在早期面向进程设计的计算机结构中，进程是程序的基本执行实体；在当代面向线程设计的计算机结构中，进程是线程的容器。程序是指令、数据及其组织形式的描述，进程是程序的实体。

> **线程**（英语：thread）是[操作系统](https://baike.baidu.com/item/操作系统)能够进行运算[调度](https://baike.baidu.com/item/调度)的最小单位。它被包含在[进程](https://baike.baidu.com/item/进程)之中，是[进程](https://baike.baidu.com/item/进程)中的实际运作单位。一条线程指的是[进程](https://baike.baidu.com/item/进程)中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并行执行不同的任务。在Unix System V及[SunOS](https://baike.baidu.com/item/SunOS)中也被称为轻量进程（lightweight processes），但轻量进程更多指内核线程（kernel thread），而把用户线程（user thread）称为线程。

进程是应用执行的一次过程，线程是程序运行过程中的分支流程。

#### 1.2 并发/并行

> 并发，在[操作系统](https://baike.baidu.com/item/操作系统/192)中，是指一个时间段中有几个程序都处于已启动运行到运行完毕之间，且这几个程序都是在同一个[处理机](https://baike.baidu.com/item/处理机/128842)上运行，但任一个时刻点上只有一个程序在处理机上运行。

> 并行是指“并排行走”或“同时实行或实施”。在[操作系统](https://baike.baidu.com/item/操作系统/192)中是指，一组程序按独立异步的速度执行，不等于时间上的重叠（同一个时刻发生)。要区别并发。并发是指：在同一个时间段内，两个或多个程序执行，有时间上的重叠（[宏观](https://baike.baidu.com/item/宏观/10006213)上是同时，[微观](https://baike.baidu.com/item/微观/4542832)上仍是顺序执行）。

并发是同一时间段内同时发生，并行是同一时间点一起工作。

