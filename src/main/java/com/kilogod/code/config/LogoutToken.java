package com.kilogod.code.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogoutToken {
    private static Logger log = LoggerFactory.getLogger(LogoutToken.class);
    /**
     * 队列大小
     */
    private static final int QUEUE_LENGTH = 1000;
    /**
     * 基于内存的阻塞队列
     */
    private static BlockingQueue<String> queue = new LinkedBlockingQueue(QUEUE_LENGTH);

    public synchronized static void addQueue(String token) throws InterruptedException {
        if (queue.size() + 100 >= QUEUE_LENGTH) {
            queue.take();
        }
        queue.offer(token);
    }

    public synchronized static boolean searchQueue(String token){
        long count = queue.stream().filter(item->item.equals(token)).count();
        if(count>=1){
            return false;
        }
        return true;
    }
}
