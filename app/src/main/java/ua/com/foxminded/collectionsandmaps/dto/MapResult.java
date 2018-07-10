package ua.com.foxminded.collectionsandmaps.dto;

import java.util.Objects;

import ua.com.foxminded.collectionsandmaps.enums.Maps;
import ua.com.foxminded.collectionsandmaps.enums.MapTasks;

public class MapResult extends CalculationResult {

    private @MapTasks String mTaskType;
    private @Maps String mMapType;

    public MapResult(@MapTasks String taskType, @Maps String mapType, String result) {
        super(result);
        mTaskType = taskType;
        mMapType = mapType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapResult mapResult = (MapResult) o;
        return mTaskType.equals(mapResult.mTaskType) && mMapType.equals(mapResult.mMapType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mTaskType, mMapType);
    }

    @Override
    public String toString() {
        return "MapResult{" +
                "mTaskType=" + mTaskType +
                ", mMapType=" + mMapType +
                '}';
    }

    public @MapTasks String getTaskType() {
        return mTaskType;
    }
}
