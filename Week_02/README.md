常见的内存溢出的情况
1.OutOfMemoryError：Java heap space 创建新的对象时，堆内存空间不够存放新的创建的对象
可能得问题：
1）流量峰值  超过了系统容量
2）存在内存泄漏。占用内存的对象越来越多

2.OutOfMemoryError:PermGen space/ OutOfMemoryError:Metaspace
加载的class数量太多或者体积太大，调大即可。
1.8以后这块从堆中拿出来，移到了非堆。

3.OutOfMemoryError：Unable to create new native thread
创建的线程数量已经达到上限值报的异常
解决方法：
    1.调整最大线程数
    2.降低xss参数  xss是线程栈的大小。
    3.调整创建线程的方式

调优分析-怎么判断GC的效果

1.高分配速率（High Allocation Rate）
分配速率表示单位时间内分配的内存量，通常使用MB/sec作为单位。上次垃圾收集之后，与下次GC开始时年轻代的使用量，两者的差值除以时间，就是分配速率。
分配速率过高会严重影响程序的性能，在JVM中可能会导致巨大的GC开销。

2.过早提升
单位时间内从年轻代提升到老年代的数据量，使用MB/sec作为单位。过早的提升会导致老年代中存在存活时间较短的对象，影响GC效率
过早提升的表现：
1).短时间频繁full GC
2).每次full gc使用率很低
3).提升速率接近分配速率
解决方法：
1).增大年轻代的大小
2).减少每次批处理的数量
