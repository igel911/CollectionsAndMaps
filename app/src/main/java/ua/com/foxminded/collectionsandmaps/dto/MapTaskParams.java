package ua.com.foxminded.collectionsandmaps.dto;

import java.util.Map;

import ua.com.foxminded.collectionsandmaps.TasksContract;
import ua.com.foxminded.collectionsandmaps.enums.Tabs;

public class MapTaskParams extends BaseTaskParams {

    private int mSize;
    private Map<Integer, Integer> mIntegerMap;
    private MapResult mMapResult;

    public MapTaskParams(@Tabs String tabType,
                         int size,
                         Map<Integer, Integer> integerMap,
                         MapResult mapResult,
                         TasksContract.GetCalculationCallback callback) {
        super(tabType, mapResult, callback);
        mSize = size;
        mIntegerMap = integerMap;
        mMapResult = mapResult;
    }

    public int getSize() {
        return mSize;
    }

    public Map<Integer, Integer> getMap() {
        return mIntegerMap;
    }

    public MapResult getMapResult() {
        return mMapResult;
    }
}
