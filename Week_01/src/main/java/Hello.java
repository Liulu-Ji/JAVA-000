public class Hello {
    public static void main(String[] args) {
        String name = "week";
        int num = 100;
        int divide = 1;
        int divideResult = 1;
        long mutilResult = 1L;
        int subResult = 1000;
        double dResult = 0.01D;

        boolean flag = true;

        for (int i = 0; i < 10; i++) {
            num += i;
            divideResult = i / divide;
            subResult -= i;
            mutilResult = mutilResult*i;
            dResult += dResult;
            if (flag) {
                System.out.println(num);
                System.out.println(divideResult);
                System.out.println(subResult);
                System.out.println(mutilResult);
                System.out.println(dResult);
            }
        }

    }
}
