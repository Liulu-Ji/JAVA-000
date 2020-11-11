import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用CountDownLatch控制
 */
public class HomeWorkCountDownLatch {

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger sum = new AtomicInteger();
        CountDownLatch cdl = new CountDownLatch(1);

        Thread s1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                for (int i = 0; i < 10000; i++) {
                    sum.getAndAdd(1);
                }
                cdl.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        s1.start();
        cdl.await();
        System.out.println("主程序结束了，结果是" + sum.get());

    }
}
