学习笔记
字节码（了解）

类加载机制
双亲委派

为什么会出现  xxx  cannot be cast to xxx ？
因为这两个xxx可能是不同的classloader加载的。使用instanceof进行判断也是false。


JMM java内存模型

Java内存参数

常用的命令

1.jps -mlv  主要查看pid
2.jinfo <pid> 可以查看详细的jvm的参数配置
3.jstat gc以及内存的使用信息
4.jmap 堆的信息
jmap -heap heap区中各个区域的大小比率等详细信息，gc的线程配置等
jmap -histo  各种对象实例的数量以及大小
5.jstack 查看线程的信息
6.jcmd  是一个整合命令，通过 jcmd <pid> help 可以查看所有可以执行的命令。
最有用的命令 jcmd <pid> VM.info  可以很全的查看信息（但是好像有的jdk不能用）
7.jrunscript 可以执行一些js脚本



可视化工具
1.jconsole
2.jvisualvm  更高级
3.jmc    动态 飞行记录器

jps无法查看进程？
原因可能是没有相关文件的读写权限。
jps命令读取是C:\Users\L\AppData\Local\Temp\hsperfdata_{userName}文件夹下的文件，如果没有权限写入，则不会再这个文件下生成对应文件，也就jps读不到对应的进程信息



GC
1.引用计数法  如果形成一个环，计数永远不会变成零，发生内存泄漏
2.引用跟踪


1.标记-清除-整理
清除 整理的时候会Stop the world

堆分为年轻代，老年代
年轻代中分为eden区，S1 S0，这三个区的比例为8:1:1


TLAB 线程本地缓冲区
TLAB，线程分配缓冲区，当你多个线程去操作内存的时候，如果操作同一块内存，需要保证线程之间分配内存不互相影响，这样会带来额外的开销。 但是使用TLAB就会给每个线程一部分内存


串行GC
并行GC  多线程  jdk8以下默认的gc策略
CMS GC  gc线程和业务一起并发执行，
好处就是并发收集，低停顿。全部暂停的时间较短
缺点就是gc线程会和业务线程抢占cpu资源



