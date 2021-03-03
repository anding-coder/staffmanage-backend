package com.kilogod.code.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anding
 * @describe
 */
public enum ThreadPoolUtil {

    THREAD_POOL;

    private final int COMPUTER_CORE_SIZE = Runtime.getRuntime().availableProcessors();
    private final int CORE_POOL_SIZE = COMPUTER_CORE_SIZE + 1;
    private final int MAX_IMUM_POOL_SIZE = COMPUTER_CORE_SIZE < 2 ? 2 : COMPUTER_CORE_SIZE * 2 + 1;
    private final long KEEP_ALIVE_TIME = 0L;
    private final TimeUnit UNIT = TimeUnit.MILLISECONDS;
    private final BlockingQueue<Runnable> BLOCKING_QUEUE = new ArrayBlockingQueue<>(20);
    private final SelfThreadFactory FACTORY = new SelfThreadFactory("kilogodPool");

    private final int HEIGHTIO_CORE_POOL_SIZE = COMPUTER_CORE_SIZE * 2;
    private final int HEIGHTIO_MAX_IMUM_POOL_SIZE =  HEIGHTIO_CORE_POOL_SIZE * 2 + 1;

    /**
     * 定时任务线程池
     */
    private ExecutorService timer = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_IMUM_POOL_SIZE, KEEP_ALIVE_TIME, UNIT, BLOCKING_QUEUE, FACTORY, new ThreadPoolExecutor.AbortPolicy());

    /**
     * 高IO 线程池
     */
    private ExecutorService heightIO = new ThreadPoolExecutor(HEIGHTIO_CORE_POOL_SIZE, HEIGHTIO_MAX_IMUM_POOL_SIZE, KEEP_ALIVE_TIME, UNIT, BLOCKING_QUEUE, FACTORY, new ThreadPoolExecutor.AbortPolicy());


    public ExecutorService getTimer() {
        return timer;
    }

    public ExecutorService getHeightIO() {
        return heightIO;
    }

    static class SelfThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        SelfThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = name + "-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
