import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class HomeWorkSemaphore {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService THREADPOOL = Executors.newCachedThreadPool();
        TestMethod tm = new TestMethod();
        tm.semaphore.acquire();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                tm.add();
            }
        });
        THREADPOOL.submit(t);
        tm.semaphore.acquire();
        System.out.println("主程序结束了，结果是"+tm.get());
        THREADPOOL.shutdown();
    }
}

class TestMethod04 {
    private Integer result = 0;
    Semaphore semaphore = new Semaphore(1);
    public void add() {
        try {
            for (int i = 0; i < 100000; i++) {
                result++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

    }

    public Integer get() throws InterruptedException {
        return result;
    }
}
