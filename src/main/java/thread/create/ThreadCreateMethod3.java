package thread.create;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xu on 2021-01-12
 */
public class ThreadCreateMethod3 {


    static class DefaultThreadFactory implements ThreadFactory {

        static final AtomicInteger poolNumber = new AtomicInteger(1);
        static final AtomicInteger threadNumber = new AtomicInteger(1);

        private ThreadGroup group;

        private String namePrefix;


        public DefaultThreadFactory() {
            SecurityManager securityManager = System.getSecurityManager();
            group = (securityManager != null) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group, r, namePrefix + threadNumber.getAndIncrement());
            if (thread.isDaemon()) thread.setDaemon(false);
            if (thread.getPriority() != Thread.NORM_PRIORITY) thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }



    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println("线程池创建");
        }
    }


    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i <10 ; i++) {
            pool.submit(new Task());
        }

        pool.shutdown();

    }




}
