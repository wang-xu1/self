package thread.sync;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xu on 2021-01-14
 */
public class WaitTest1 {

    Queue<String> buffer = new LinkedList<String>();


    public synchronized void give(String data) {
        buffer.add(data);
        notify();

    }


    public synchronized String take() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        return buffer.remove();
    }




    public static void main(String[] args) {

        WaitTest waitTest = new WaitTest();


        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    waitTest.give("1");
                }
            }).start();

        }


        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        waitTest.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }
}
