package ua.com.foxminded.collectionsandmaps.executors;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ua.com.foxminded.collectionsandmaps.tasks.BaseTask;

public class ThreadPoolManager {

    private final ThreadPoolExecutor mExecutor;
    private LinkedBlockingQueue<Runnable> taskQueue;
    private static ThreadPoolManager sManager;

    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_TIME = 50;

    static {
        sManager = new ThreadPoolManager();
    }

    private ThreadPoolManager() {
        taskQueue = new LinkedBlockingQueue<>();
        mExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, CORE_POOL_SIZE * 2,
                KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, taskQueue);
    }

    public static ThreadPoolManager getThreadPoolManager() {
        return sManager;
    }

    public void runTask(BaseTask task){
        task.getCallback().onCalculationStart(task.getCResult(), task.getTabType());
        mExecutor.execute(task);
    }

    public void stopAllTasks() {
        taskQueue.clear();
        mExecutor.shutdownNow();
    }
}
