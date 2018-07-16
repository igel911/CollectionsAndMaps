package ua.com.foxminded.collectionsandmaps.dto;

import ua.com.foxminded.collectionsandmaps.TasksContract;

public abstract class BaseTaskParams {

    private CalculationResult mCResult;
    private TasksContract.GetCalculationCallback mCallback;

    BaseTaskParams(CalculationResult cResult, TasksContract.GetCalculationCallback callback) {
        mCResult = cResult;
        mCallback = callback;
    }

    public CalculationResult getCResult() {
        return mCResult;
    }

    public TasksContract.GetCalculationCallback getCallback() {
        return mCallback;
    }
}
