package cn.lhzs.common.support.thread;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description: ES搜索引擎线程池
 * @author ZHX
 */
public class JestThreadPool implements InitializingBean, DisposableBean {

    Logger logger = Logger.getLogger(JestThreadPool.class);

    private int threadSize = 20;
    ExecutorService threadPool;

    public void addTask(Runnable run) {
        threadPool.execute(run);
    }

    @Override
    public void destroy() throws Exception {
        if (threadPool != null) {
            threadPool.shutdown();
            try {
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    threadPool.shutdownNow();
                    if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                        logger.error("处理消息线程池没有正常停止.");
                    }
                }
            } catch (InterruptedException ie) {
                threadPool.shutdownNow();
                Thread.currentThread().interrupt();
            }
            logger.info("处理消息线程池正常停止.");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        threadPool = Executors.newFixedThreadPool(threadSize);
    }

    public int getThreadSize() {
        return threadSize;
    }

    public void setThreadSize(int threadSize) {
        this.threadSize = threadSize;
    }

}
