public class HomeWorkJoin {
    public static void main(String[] args) throws InterruptedException {
        TestMethod03 tm = new TestMethod03();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                tm.add();
            }
        });
        t.start();
        t.join();
        System.out.println("主程序结束了，结果是"+tm.get());
    }
}

class TestMethod03 {
    private Integer result = 0;

    public void add(){
        for (int i = 0; i < 100000; i++) {
            result++;
        }
    }

    public Integer get() {
        return result;
    }
}
