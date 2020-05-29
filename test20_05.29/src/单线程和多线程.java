/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dell
 * Date: 2020-05-29
 * Time: 18:35
 **/
public class 单线程和多线程 {
    static final int COUNT = 10;
    static final long NUMBER = 10_0000_0000;

    /**
     * 一个求和的方法
      * @return
     */
    static long sum() {
        long r = 0;
        for (long i = 0; i < NUMBER; i++) {
            r += i;
        }
        return r;
    }

    /**
     * 单线程情况下求和十次 代码及时间
     */
    static void 单线程() {
        long b = System.nanoTime();//记录初始时间
        for (int i = 0; i < COUNT; i++) {//求和COUNT次
            System.out.println(sum());
            //打印出每次求和的值
        }
        long e = System.nanoTime();//记录最后的时间
        double time = (e-b)/1000_000_000.0;
        System.out.println("单线程运行的时间："+ time);
    }

    /**
     * 多线程情况下求和十次（开了COUNT个线程） 代码及时间
     */
    //内部类
    static class SumThread extends Thread {
        @Override
        public void run() {
            System.out.println(sum());
        }
    }

    static void 多线程() throws InterruptedException {
        long b = System.nanoTime();//记录初始时间
        //因为当前也处于一个线程中，所以只需要在创建COUNT-1个线程就够了
        Thread[] threads= new Thread[COUNT-1];
        for (int i = 0; i < COUNT-1; i++) {
            //最后执行的就是上面定义的类中的run方法
            threads[i] = new SumThread();
            threads[i].start();
        }

        //剩下的一次，用当前线程来计算
        System.out.println(sum());

        //当前线程结束了，但还需要等另外COUNT-1个线程结束才算计算完了
        for (int i = 0; i < COUNT-1; i++) {
            threads[i].join();
        }

        long e = System.nanoTime();//记录最后的时间
        double time = (e-b)/1000_000_000.0;
        System.out.println("多线程运行的时间："+ time);
    }

    public static void main(String[] args) throws InterruptedException {
        //一进main方法main就会有一个线程
        单线程();
        System.out.println("=====================");
        多线程();
    }
}
