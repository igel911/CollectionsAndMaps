package ua.com.foxminded.collectionsandmaps.tasks.listtasks;

import java.util.List;

import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;
import ua.com.foxminded.collectionsandmaps.dto.ListTaskParams;

public class ListRemoveMiddleTask extends ListBaseTask {

    public ListRemoveMiddleTask(ListTaskParams listTaskParams) {
        super(listTaskParams);
    }

    @Override
    public void run() {
        List<Integer> integerList = getList();
        prepareList();
        long startTime = System.nanoTime();
        for (int i = 0; i < getSize(); i++) {
            int middleIndex = integerList.size() / 2;
            integerList.remove(middleIndex);
        }
        long endTime = System.nanoTime();
        CalculationResult cResult = getCResult();
        cResult.setResult(String.valueOf((endTime - startTime) / DIVIDER));
        getCallback().onCalculationOver(cResult);
    }
}
