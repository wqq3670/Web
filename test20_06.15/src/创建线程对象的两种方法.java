/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dell
 * Date: 2020-06-16
 * Time: 14:17
 **/
public class 创建线程对象的两种方法 {

    /**
     * 方法1
     */
    static class MyThread extends Thread{
        @Override
        public void run() {
            //希望线程执行的代码写这里
        }
    }

    public static void main1(String[] args) {
        Thread t = new MyThread();
    }

    /**
     * 方法2
     */
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            //希望线程执行的代码写这里
        }
    }

    public static void main(String[] args) {
        Runnable target = new MyRunnable();
        Thread t = new Thread(target);
    }
}
