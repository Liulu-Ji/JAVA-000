import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HomeworkLock {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService THREADPOOL = Executors.newCachedThreadPool();
        TestMethod02 tm = new TestMethod02();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                tm.add();
            }
        });
        THREADPOOL.submit(t);
        System.out.println("主程序结束了，结果是"+tm.get());
        THREADPOOL.shutdown();
    }
}

class TestMethod02 {
    Lock lock = new ReentrantLock(true);
    Condition condition = lock.newCondition();
    private Integer result = 0;

    public void add(){
        lock.lock();
        try {
            for (int i = 0; i < 100000; i++) {
                result++;
            }
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public Integer get() throws InterruptedException {
        lock.lock();
        try {
            condition.await();
        }finally {
            lock.unlock();
        }
        return result;
    }
}
