
# StudyProject

记录学习和测试的Project

***

### Git常用命令

`git clone xxxxxx.git`  拉取远程git仓库

`git add .`  添加文件到本地仓库

`git commit -m "xxxxxx"` 提交到本地仓库，注意提交信息备注使用""，而不是''

`git push`  提交到远程仓库

`git pull`  拉取远程仓库文件

`git branch`  查看本地仓库的分支

`git branch -a`  查看仓库中的所有分支（本地和远程）

`git branch -d branchName`  删除本地分支

`git push origin --delete branchName`  删除远程分支

.ignore忽略文件只能影响没有add过的文件，所以如果把不应该提交的文件push到远程仓库的分支上以后，只是通过配置.ignore文件是不生效的。如果想要删除远程仓库分支中提交错误的文件，使用如下命令：

`git rm --cache fileName` 删除本地文件的缓存，即移除该文件的版本控制  fileName表示文件名称

`git rm -r --cache directoryName`  删除本地目录的缓存，directoryName表示文件名

***

### 关于分包的管理

PBL（按层分包 Package By Layer） 不推荐

PBF（按功能分包 Package By Feature） 推荐

[Android 开发规范（完结版）](https://blankj.com/2017/03/08/android-standard-dev-final/#2-as-%E8%A7%84%E8%8C%83)

***

### 关于线程池和线程调度

首选RxJava，此处仅仅是作为学习研究线程池

线程是稀有资源，如果仅仅是通过`new Thread`的方式创建线程使用，虽然不好管理和维护，但是一两个还好一旦需要大量使用线程的时候就非常头痛。
这个时候可以考虑使用线程池

使用线程池的好处：
- `newCachedThreadPool` 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
- `newFixedThreadPool` 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
- `newScheduledThreadPool` 创建一个定长线程池，支持定时及周期性任务执行。
- `newSingleThreadExecutor` 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

具体参照实例

***

AQS 并发编程包的基础

继承方式使用

模板方法

state 属性的重要作用

CLH队列锁的原理

AQS基于CLH队列锁做的优化

JMM Java内存模型

CPU的高速缓存

工作内存和主内存

JMM 导致的并发安全问题

可见性、原子性和顺序性

volatile 的可见性</br>
volatile 的抑制指令重排</br>
volatile 的实现原理(CPU提供的Lock指令前缀)</br>
- 写操作完成以后强制将变量写回主存中
- 当前线程工作内存中的变量发生修改后会强制使其他线程工作内存中的变量副本地址失效，让其重新从主存中获取最新的变量副本
