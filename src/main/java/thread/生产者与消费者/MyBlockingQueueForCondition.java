package thread.生产者与消费者;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xu on 2021-01-14
 */
public class MyBlockingQueueForCondition {

    Queue queue;
    int size = 16;

    ReentrantLock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();


    public MyBlockingQueueForCondition(int size) {
        this.size = size;
        queue = new LinkedList<String>();
    }


    public void put(String data) {
        lock.lock();
        try {
            //队列满等待
            while (queue.size() == size) {
                notFull.await();
            }
            queue.add(data);
            notEmpty.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public String take() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            notFull.signalAll();
            return (String) queue.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }


    public static void main(String[] args) {

        MyBlockingQueueForCondition myBlockingQueueForCondition = new MyBlockingQueueForCondition(10);

        new Thread(() -> {
            while (true) {
                myBlockingQueueForCondition.put(String.valueOf(System.currentTimeMillis()));
            }
        }).start();

        new Thread(() -> {
            while (true) {
                String take = myBlockingQueueForCondition.take();
                System.out.println("take=" + take + ",date=" + String.valueOf(System.currentTimeMillis()));
            }
        }).start();


    }


}
