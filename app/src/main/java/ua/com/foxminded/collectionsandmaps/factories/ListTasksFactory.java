package ua.com.foxminded.collectionsandmaps.factories;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ua.com.foxminded.collectionsandmaps.TasksContract;
import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;
import ua.com.foxminded.collectionsandmaps.dto.ListResult;
import ua.com.foxminded.collectionsandmaps.dto.ListTaskParams;
import ua.com.foxminded.collectionsandmaps.enums.ListTasks;
import ua.com.foxminded.collectionsandmaps.enums.Lists;
import ua.com.foxminded.collectionsandmaps.executors.ThreadPoolManager;
import ua.com.foxminded.collectionsandmaps.tasks.listtasks.ListAddBeginTask;
import ua.com.foxminded.collectionsandmaps.tasks.listtasks.ListAddEndTask;
import ua.com.foxminded.collectionsandmaps.tasks.listtasks.ListAddMiddleTask;
import ua.com.foxminded.collectionsandmaps.tasks.listtasks.ListBaseTask;
import ua.com.foxminded.collectionsandmaps.tasks.listtasks.ListRemoveBeginTask;
import ua.com.foxminded.collectionsandmaps.tasks.listtasks.ListRemoveEndTask;
import ua.com.foxminded.collectionsandmaps.tasks.listtasks.ListRemoveMiddleTask;
import ua.com.foxminded.collectionsandmaps.tasks.listtasks.ListSearchByValue;

public class ListTasksFactory implements TasksContract.TasksDataSource {

    private String[] mLists = Lists.types;
    private String[] mListTasks = ListTasks.tasks;
    private List<CalculationResult> mListCalculationCash;
    private ThreadPoolManager mThreadPoolManager;

    {
        mThreadPoolManager = ThreadPoolManager.getThreadPoolManager();

        mListCalculationCash = new ArrayList<>();
        for (String taskType : mListTasks) {
            for (String listType : mLists) {
                mListCalculationCash.add(new ListResult(taskType, listType, ""));
            }
        }
    }

    @Override
    public List<CalculationResult> getCalculationCash() {
        return new ArrayList<>(mListCalculationCash);
    }

    @Override
    public void addToCalculationCash(CalculationResult cResult) {
        mListCalculationCash.set(mListCalculationCash.indexOf(cResult), cResult);
    }

    @Override
    public void stopAllTasks() {
        mThreadPoolManager.stopAllTasks();
    }

    @Override
    public void runCalculation(int size, TasksContract.GetCalculationCallback callback) {
        for (String taskType : mListTasks) {
            for (String listType : mLists) {
                mThreadPoolManager.runTask(getPreparedListTask(
                        new ListTaskParams(size,
                                getNewList(listType),
                                new ListResult(taskType, listType, null),
                                callback)));
            }
        }
    }

    private List<Integer> getNewList(@Lists String listType) {
        switch (listType) {
            case Lists.ARRAY_LIST:
                return new ArrayList<>();
            case Lists.LINKED_LIST:
                return new LinkedList<>();
            case Lists.COPY_ON_WRITE_ARRAY_LIST:
                return new CopyOnWriteArrayList<>();
            default:
                return new CopyOnWriteArrayList<>();
        }
    }

    private ListBaseTask getPreparedListTask(ListTaskParams listTaskParams) {
        switch (listTaskParams.getListResult().getTaskType()) {
            case ListTasks.ADD_BEGIN:
                return new ListAddBeginTask(listTaskParams);
            case ListTasks.ADD_MIDDLE:
                return new ListAddMiddleTask(listTaskParams);
            case ListTasks.ADD_END:
                return new ListAddEndTask(listTaskParams);
            case ListTasks.SEARCH_VALUE:
                return new ListSearchByValue(listTaskParams);
            case ListTasks.REMOVE_BEGIN:
                return new ListRemoveBeginTask(listTaskParams);
            case ListTasks.REMOVE_MIDDLE:
                return new ListRemoveMiddleTask(listTaskParams);
            case ListTasks.REMOVE_END:
                return new ListRemoveEndTask(listTaskParams);
            default:
                return new ListRemoveEndTask(listTaskParams);
        }
    }
}
