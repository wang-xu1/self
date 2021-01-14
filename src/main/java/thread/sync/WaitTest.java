package thread.sync;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xu on 2021-01-13
 */
public class WaitTest {

    Queue<String> buffer = new LinkedList<String>();


    public void give(String data) {
        buffer.add(data);
        notify();

    }


    public String take() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        return buffer.remove();
    }


    public static void main(String[] args) {

        WaitTest1 waitTest = new WaitTest1();


        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    waitTest.give(String.valueOf(System.currentTimeMillis()));
                }
            }).start();

        }


        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String take = waitTest.take();
                        System.out.println("take="+take+",date="+String.valueOf(System.currentTimeMillis()));


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }


}
