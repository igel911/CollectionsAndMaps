package ua.com.foxminded.collectionsandmaps.tasks.maptasks;

import java.util.Map;

import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;
import ua.com.foxminded.collectionsandmaps.dto.MapTaskParams;

public class MapAddNewTask extends MapBaseTask {

    public MapAddNewTask(MapTaskParams mapTaskParams) {
    super(mapTaskParams);
    }

    @Override
    public void run() {
        Map<Integer, Integer> integerMap = getMap();
        long startTime = System.nanoTime();
        for (int i = 0; i < getSize(); i++) {
            integerMap.put(i, i);
        }
        long endTime = System.nanoTime();
        CalculationResult cResult = getCResult();
        cResult.setResult(String.valueOf((endTime - startTime) / DIVIDER));
        getCallback().onCalculationOver(cResult, getTabType());
    }
}
