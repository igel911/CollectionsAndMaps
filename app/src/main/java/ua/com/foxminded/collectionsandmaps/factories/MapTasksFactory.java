package ua.com.foxminded.collectionsandmaps.factories;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ua.com.foxminded.collectionsandmaps.TasksContract;
import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;
import ua.com.foxminded.collectionsandmaps.dto.MapResult;
import ua.com.foxminded.collectionsandmaps.dto.MapTaskParams;
import ua.com.foxminded.collectionsandmaps.enums.MapTasks;
import ua.com.foxminded.collectionsandmaps.enums.Maps;
import ua.com.foxminded.collectionsandmaps.executors.ThreadPoolManager;
import ua.com.foxminded.collectionsandmaps.tasks.maptasks.MapAddNewTask;
import ua.com.foxminded.collectionsandmaps.tasks.maptasks.MapBaseTask;
import ua.com.foxminded.collectionsandmaps.tasks.maptasks.MapRemoveTask;
import ua.com.foxminded.collectionsandmaps.tasks.maptasks.MapSearchTask;

public class MapTasksFactory implements TasksContract.TasksDataSource {

    private String[] mMaps = Maps.types;
    private String[] mMapTasks = MapTasks.tasks;
    private List<CalculationResult> mMapCalculationCash;
    private ThreadPoolManager mThreadPoolManager;

    {
        mThreadPoolManager = ThreadPoolManager.getThreadPoolManager();

        mMapCalculationCash = new ArrayList<>();
        for (String taskType : mMapTasks) {
            for (String mapType : mMaps) {
                mMapCalculationCash.add(new MapResult(taskType, mapType, ""));
            }
        }
    }

    @Override
    public List<CalculationResult> getCalculationCash() {
        return new ArrayList<>(mMapCalculationCash);
    }

    @Override
    public void addToCalculationCash(CalculationResult cResult) {
        mMapCalculationCash.set(mMapCalculationCash.indexOf(cResult), cResult);
    }

    @Override
    public void stopAllTasks() {
        mThreadPoolManager.stopAllTasks();
    }

    @Override
    public void runCalculation(int size, TasksContract.GetCalculationCallback callback) {
        for (String taskType : mMapTasks) {
            for (String mapType : mMaps) {
                mThreadPoolManager.runTask(getPreparedMapTask(
                        new MapTaskParams(size,
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
                return new HashMap<>();
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
                return new MapRemoveTask(mapTaskParams);
        }
    }

}
