package thread.生产者与消费者;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by xu on 2021-01-14
 * blockQueue
 */
public class Method1 {

    public static void main(String[] args) {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        Runnable runnable = () -> {
            while (true) {
                try {
                    if(queue.size()>0 &&  queue.size()%10 ==0) System.out.println("------------");
                    queue.put(String.valueOf(System.currentTimeMillis()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Runnable runnable1 = () -> {
            while (true) {
                try {
                    String take = queue.take();
                    System.out.println("take=" + take + ",date=" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        new Thread(runnable).start();


        new Thread(runnable1).start();


    }
}
