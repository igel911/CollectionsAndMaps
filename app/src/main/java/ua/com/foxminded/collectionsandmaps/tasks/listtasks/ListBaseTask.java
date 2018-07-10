package ua.com.foxminded.collectionsandmaps.tasks.listtasks;

import java.util.List;

import ua.com.foxminded.collectionsandmaps.dto.ListTaskParams;
import ua.com.foxminded.collectionsandmaps.tasks.BaseTask;

public abstract class ListBaseTask extends BaseTask {

    private ListTaskParams mListTaskParams;

    ListBaseTask(ListTaskParams listTaskParams) {
        super(listTaskParams);
        mListTaskParams = listTaskParams;
    }

    void prepareList() {
        List<Integer> integerList = getList();
        for (int i = 0; i < getSize(); i++) {
            integerList.add(i);
        }
    }

    int getSize() {
        return mListTaskParams.getSize();
    }

    List<Integer> getList() {
        return mListTaskParams.getIntegerList();
    }
}
