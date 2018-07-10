package ua.com.foxminded.collectionsandmaps.dto;

import ua.com.foxminded.collectionsandmaps.TasksContract;
import ua.com.foxminded.collectionsandmaps.enums.Tabs;

public abstract class BaseTaskParams {

    @Tabs
    private String mTabType;
    private CalculationResult mCResult;
    private TasksContract.GetCalculationCallback mCallback;

    BaseTaskParams(String tabType, CalculationResult cResult, TasksContract.GetCalculationCallback callback) {
        mTabType = tabType;
        mCResult = cResult;
        mCallback = callback;
    }

    public String getTabType() {
        return mTabType;
    }

    public CalculationResult getCResult() {
        return mCResult;
    }

    public TasksContract.GetCalculationCallback getCallback() {
        return mCallback;
    }
}
