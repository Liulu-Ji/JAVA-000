import java.util.concurrent.*;

public class HomeWorkFutureTask {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            int sum = 0;
            Thread.sleep(3000);
            for(int i=0;i<10000;i++){
                sum++;
            }
            return sum;
        });
        singleThreadPool.submit(futureTask);

        System.out.println("主程序结束了，结果是" + futureTask.get());
    }
}
