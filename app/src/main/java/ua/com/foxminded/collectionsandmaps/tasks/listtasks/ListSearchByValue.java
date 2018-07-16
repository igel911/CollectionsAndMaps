package ua.com.foxminded.collectionsandmaps.tasks.listtasks;

import java.util.List;

import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;
import ua.com.foxminded.collectionsandmaps.dto.ListTaskParams;

public class ListSearchByValue extends ListBaseTask {

    public ListSearchByValue(ListTaskParams listTaskParams) {
        super(listTaskParams);
    }

    @Override
    public void run() {
        List<Integer> integerList = getList();
        prepareList();
        long startTime = System.nanoTime();
        //noinspection ResultOfMethodCallIgnored
        integerList.contains(getSize() - 1);
        long endTime = System.nanoTime();
        CalculationResult cResult = getCResult();
        cResult.setResult(String.valueOf((endTime - startTime) / DIVIDER));
        getCallback().onCalculationOver(cResult);
    }
}
