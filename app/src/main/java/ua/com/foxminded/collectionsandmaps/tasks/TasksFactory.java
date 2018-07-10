package ua.com.foxminded.collectionsandmaps.tasks;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import ua.com.foxminded.collectionsandmaps.TasksContract;
import ua.com.foxminded.collectionsandmaps.dto.*;
import ua.com.foxminded.collectionsandmaps.enums.*;
import ua.com.foxminded.collectionsandmaps.executors.ThreadPoolManager;
import ua.com.foxminded.collectionsandmaps.tasks.listtasks.*;
import ua.com.foxminded.collectionsandmaps.tasks.maptasks.*;

public class TasksFactory implements TasksContract.TasksDataSource {

    private static TasksFactory sInstance;
    private String[] mLists = Lists.types;
    private String[] mMaps = Maps.types;
    private String[] mListTasks = ListTasks.tasks;
    private String[] mMapTasks = MapTasks.tasks;
    private List<CalculationResult> mMapCalculationCash;
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
        mMapCalculationCash = new ArrayList<>();
        for (String taskType : mMapTasks) {
            for (String mapType : mMaps) {
                mMapCalculationCash.add(new MapResult(taskType, mapType, ""));
            }
        }
    }

    private TasksFactory() {}

    public static TasksFactory getInstance() {
        if (sInstance == null) {
            sInstance = new TasksFactory();
        }
        return sInstance;
    }

    @Override
    public List<CalculationResult> getCalculationCash(@Tabs String tabType) {
        switch (tabType) {
            case Tabs.MAP_TAB:
                return new ArrayList<>(mMapCalculationCash);
            case Tabs.LIST_TAB:
                return new ArrayList<>(mListCalculationCash);
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public void addToCalculationCash(CalculationResult cResult, @Tabs String tabType) {
        switch (tabType) {
            case Tabs.MAP_TAB:
                mMapCalculationCash.set(mMapCalculationCash.indexOf(cResult), cResult);
                break;
            case Tabs.LIST_TAB:
                mListCalculationCash.set(mListCalculationCash.indexOf(cResult), cResult);
                break;
            default:
                break;
        }
    }

    @Override
    public void stopAllTasks() {
        mThreadPoolManager.stopAllTasks();
    }

    @Override
    public void runCalculation(@Tabs String tabType,
                               int size,
                               TasksContract.GetCalculationCallback callback) {
        switch (tabType) {
            case Tabs.MAP_TAB:
                runMapCalculation(tabType, size, callback);
                break;
            case Tabs.LIST_TAB:
                runListCalculation(tabType, size, callback);
                break;
            default:
                break;
        }
    }

    private void runListCalculation(@Tabs String tabType,
                                    int size,
                                    TasksContract.GetCalculationCallback callback) {
        for (String taskType : mListTasks) {
            for (String listType : mLists) {
                mThreadPoolManager.runTask(getPreparedListTask(
                        new ListTaskParams(tabType,
                                size,
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
                return null;
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
                return null;
        }
    }

    private void runMapCalculation(@Tabs String tabType,
                                   int size,
                                   TasksContract.GetCalculationCallback callback) {
        for (String taskType : mMapTasks) {
            for (String mapType : mMaps) {
                mThreadPoolManager.runTask(getPreparedMapTask(
                        new MapTaskParams(tabType,
                                size,
                                getNewMap(mapType),
                                new MapResult(taskType, mapType, null),
                                callback)));
            }
        }
    }

    @SuppressLint("UseSparseArrays")
    private Map<Integer, Integer> getNewMap(@Maps String listType) {
        switch (listType) {
            case Maps.TREE_MAP:
                return new TreeMap<>();
            case Maps.HASH_MAP:
                return new HashMap<>();
            default:
                return null;
        }
    }

    private MapBaseTask getPreparedMapTask(MapTaskParams mapTaskParams) {
        switch (mapTaskParams.getMapResult().getTaskType()) {
            case MapTasks.ADD_NEW:
                return new MapAddNewTask(mapTaskParams);
            case MapTasks.SEARCH_KEY:
                return new MapSearchTask(mapTaskParams);
            case MapTasks.REMOVE:
                return new MapRemoveTask(mapTaskParams);
            default:
                return null;
        }
    }


}
