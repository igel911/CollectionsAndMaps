package ua.com.foxminded.collectionsandmaps.tasks.maptasks;

import java.util.Map;

import ua.com.foxminded.collectionsandmaps.dto.MapTaskParams;
import ua.com.foxminded.collectionsandmaps.tasks.BaseTask;

public abstract class MapBaseTask extends BaseTask {

    private MapTaskParams mMapTaskParams;

    MapBaseTask(MapTaskParams mapTaskParams) {
        super(mapTaskParams);
        mMapTaskParams = mapTaskParams;
    }

    void prepareMap() {
        Map<Integer, Integer> integerMap = mMapTaskParams.getMap();
        for (int i = 0; i < getSize(); i++) {
            integerMap.put(i, i);
        }
    }

    int getSize() {
        return mMapTaskParams.getSize();
    }

    Map<Integer, Integer> getMap() {
        return mMapTaskParams.getMap();
    }
}
