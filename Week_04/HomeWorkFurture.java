import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HomeWorkFurture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

        Future<Integer> future = singleThreadPool.submit(new Callable<Integer>(){
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for(int i=0;i<10000;i++){
                    sum++;
                }
                return sum;
            }
        });

        System.out.println("主程序结束了，结果是" + future.get());
    }
}
