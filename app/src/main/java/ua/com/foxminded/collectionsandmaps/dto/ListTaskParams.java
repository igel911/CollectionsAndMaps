package ua.com.foxminded.collectionsandmaps.dto;

import java.util.List;

import ua.com.foxminded.collectionsandmaps.TasksContract;
import ua.com.foxminded.collectionsandmaps.enums.Tabs;

public class ListTaskParams extends BaseTaskParams {

    private int mSize;
    private List<Integer> mIntegerList;
    private ListResult mListResult;

    public ListTaskParams(@Tabs String tabType,
                          int size,
                          List<Integer> integerList,
                          ListResult listResult,
                          TasksContract.GetCalculationCallback callback) {
        super(tabType, listResult, callback);
        mSize = size;
        mIntegerList = integerList;
        mListResult = listResult;
    }

    public int getSize() {
        return mSize;
    }

    public List<Integer> getIntegerList() {
        return mIntegerList;
    }

    public ListResult getListResult() {
        return mListResult;
    }
}
