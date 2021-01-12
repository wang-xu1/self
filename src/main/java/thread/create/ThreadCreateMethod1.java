package thread.create;

/**
 * Created by xu on 2021-01-12
 */
public class ThreadCreateMethod1 implements Runnable {


    @Override
    public void run() {
        System.out.println("测试runnable");
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadCreateMethod1());
        // thread.start();
        Thread.sleep(1000);

        System.out.println("main 线程");
        // thread.run();
    }
}
