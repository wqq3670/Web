/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dell
 * Date: 2020-06-17
 * Time: 21:08
 **/
public class 创建线程对象示例 {
    //定义一个 线程 或 目标 类
    private static class MyThread extends Thread{
        //让线程去执行的代码
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.print(i+" ");
            }
        }
    }

    //创建的一个目标类
    private static class MyRunnable implements Runnable{
        @Override
        public void run() {
            for (int i = 100; i < 110; i++) {
                System.out.print(i+" ");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread a = new MyThread();//创建一个线程对象，该线程要干的工作就在该线程类中，已被覆写
        a.start();   // 把 a 线程放入就绪队列
        /*
        关于join()要说的一些点
        1.主线程放弃CPU
        2.直到 a 结束之前，主线程承诺再也不抢CPU
        3.主线程会阻塞在这里，直到 a 线程执行结束，才接着往下执行
         */
        a.join();
        System.out.println("a一定结束了");


        Runnable target = new MyRunnable();//创建一个目标对象，具体要做的内容见run方法
        Thread b = new Thread(target);//拿着目标对象，去创建对象，这个线程要干的活就是目标对象中所指定的
        b.start();
        b.join();
        System.out.println("b一定结束了");


        Runnable target2 = new MyThread();//创建了一个Thread对象,因为Thread实现了Runnable接口,
        // 所以可以看作创建了目标对象(可以用Runnable来接收Thread的对象)
        Thread c = new Thread(target2);
        c.start();
        c.join();
        System.out.println("c一定结束了");
    }

    private static void 使用匿名类创建线程对象() {
        //等同于第一种直接创建线程的方式
        Thread a = new Thread(){
            @Override
            public void run() {
                //这里指定线程要干的活
            }
        };

        //等同于第二种方式，先创建目标对象，再创建线程对象
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                //这里指定线程要干的活
            }
        });

        //b的变形写法，使用lambda表达式
        //完全等同于b的写法
        Thread c = new Thread(() -> {
            //这里指定线程要干的活
        });
    }

}
