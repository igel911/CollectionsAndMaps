package ua.com.foxminded.collectionsandmaps.dto;

import java.util.Map;

import ua.com.foxminded.collectionsandmaps.TasksContract;

public class MapTaskParams extends BaseTaskParams {

    private int mSize;
    private Map<Integer, Integer> mIntegerMap;
    private MapResult mMapResult;

    public MapTaskParams(int size,
                         Map<Integer, Integer> integerMap,
                         MapResult mapResult,
                         TasksContract.GetCalculationCallback callback) {
        super(mapResult, callback);
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
