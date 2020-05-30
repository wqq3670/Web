import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dell
 * Date: 2020-05-30
 * Time: 15:13
 **/
public class 多线程实现斐波那契数列 {
    static class FibThread extends Thread{
        int n;
        FibThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            System.out.printf("fib(%d) = %d%n", n, fib(n));
        }
    }

    /**
     * 计算斐波那契数
     * @param n
     * @return
     */
    static long fib(int n) {
        if(n < 2) {
            return n;
        }
        return fib(n-1)+fib(n-2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("请输入要计算的数：");
            int n = sc.nextInt();
            sc.nextLine();
            new FibThread(n).start();
        }
    }
}
