import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeworkSynchronized {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService THREADPOOL = Executors.newCachedThreadPool();
        TestMethod01 tm = new TestMethod01();
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

class TestMethod01 {
    private Integer result = 0;

    public synchronized void add(){
        for(int i=0;i<10;i++){
            result++;
        }
        this.notify();
    }

    public synchronized Integer get() throws InterruptedException {
        this.wait();
        return result;
    }
}
