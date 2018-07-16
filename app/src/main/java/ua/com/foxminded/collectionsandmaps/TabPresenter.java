package ua.com.foxminded.collectionsandmaps;

import java.util.List;

import butterknife.Unbinder;
import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;

public class TabPresenter implements TabContract.Presenter, TasksContract.GetCalculationCallback {

    private TabContract.View mView;
    private TasksContract.TasksDataSource mTasksDataSource;
    private Unbinder mUnbinder;

    TabPresenter(TasksContract.TasksDataSource tasksDataSource) {
        mTasksDataSource = tasksDataSource;
    }

    @Override
    public void onCalculationOver(CalculationResult cResult) {
        mTasksDataSource.addToCalculationCash(cResult);
        onCalculationStart(cResult);
    }

    @Override
    public void onCalculationStart(CalculationResult cResult) {
        mView.showCalculationResult(cResult);
    }

    @Override
    public void setUnbinder(Unbinder unbinder) {
        mUnbinder = unbinder;
    }

    @Override
    public void runCalculation(int quantity) {
        mTasksDataSource.runCalculation(quantity, this);
    }

    @Override
    public void stop() {
        mTasksDataSource.stopAllTasks();
        for (CalculationResult cResult : mTasksDataSource.getCalculationCash()) {
            onCalculationStart(cResult);
        }
    }

    @Override
    public List<CalculationResult> getCalculationCash() {
        return mTasksDataSource.getCalculationCash();
    }

    @Override
    public void unbind() {
        mUnbinder.unbind();
    }

    @Override
    public void setView(TabContract.View view) {
        mView = view;
    }
}
