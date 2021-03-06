/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dell
 * Date: 2020-06-15
 * Time: 20:42
 **/
public class Main {
    private static class A extends Thread {
        @Override
        public void run() {
            System.out.println("我是A");
        }
    }

    private static class B extends Thread{
        @Override
        public void run() {
            System.out.println("我是B");
        }
    }
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        //给b设置较高的优先级,
        // 但是优先级只是对系统的一个建议，而不是强制
        b.setPriority(Thread.MAX_PRIORITY);

        a.start();
        b.start();
        //上面两行执行完后，将a b放入了就绪队列中

        System.out.println("我是main");

    }
}
