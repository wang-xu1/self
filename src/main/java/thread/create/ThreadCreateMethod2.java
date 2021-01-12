package thread.create;

/**
 * Created by xu on 2021-01-12
 */
public class ThreadCreateMethod2 extends Thread {


    @Override
    public void run() {
        System.out.println("测试Thread");
    }


    public static void main(String[] args) {
        Thread thread = new ThreadCreateMethod2();
        thread.start();
        thread.interrupt();


    }
}
