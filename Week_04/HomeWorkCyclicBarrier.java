import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过CyclicBarrier控制
 */
public class HomeWorkCyclicBarrier {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        AtomicInteger sum = new AtomicInteger();
        CyclicBarrier barrier = new CyclicBarrier(2);

        Thread s1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                for (int i = 0; i < 10000; i++) {
                    sum.getAndAdd(1);
                }
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        s1.start();
        barrier.await();
        System.out.println("主程序结束了，结果是" + sum.get());

    }
}
