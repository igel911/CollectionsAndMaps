package ua.com.foxminded.collectionsandmaps.tasks;

import ua.com.foxminded.collectionsandmaps.TasksContract;
import ua.com.foxminded.collectionsandmaps.dto.BaseTaskParams;
import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;

public abstract class BaseTask implements Runnable {

    public static final int DIVIDER = 1_000_000;
    private BaseTaskParams mBaseTaskParams;

    public BaseTask(BaseTaskParams baseTaskParams) {
        mBaseTaskParams = baseTaskParams;
    }

    public TasksContract.GetCalculationCallback getCallback() {
        return mBaseTaskParams.getCallback();
    }

    public CalculationResult getCResult() {
        return mBaseTaskParams.getCResult();
    }

}
