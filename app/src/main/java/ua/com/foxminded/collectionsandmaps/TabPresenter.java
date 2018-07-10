package ua.com.foxminded.collectionsandmaps;

import java.util.List;

import butterknife.Unbinder;
import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;
import ua.com.foxminded.collectionsandmaps.enums.Tabs;

public class TabPresenter implements TabContract.Presenter, TasksContract.GetCalculationCallback {

    private TabContract.View mView;
    private TasksContract.TasksDataSource mTasksDataSource;
    private Unbinder mUnbinder;

    TabPresenter(TabContract.View view, TasksContract.TasksDataSource tasksDataSource) {
        mTasksDataSource = tasksDataSource;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void onCalculationOver(CalculationResult cResult, @Tabs String tabType) {
        mTasksDataSource.addToCalculationCash(cResult, tabType);
        onCalculationStart(cResult, tabType);
    }

    @Override
    public void onCalculationStart(CalculationResult cResult, @Tabs String tabType) {
        mView.showCalculationResult(cResult);
    }

    @Override
    public void setUnbinder(Unbinder unbinder) {
        mUnbinder = unbinder;
    }

    public void runCalculation(@Tabs String tabType, int quantity) {
        mTasksDataSource.runCalculation(tabType, quantity, this);
    }

    @Override
    public void stop(@Tabs String tabType) {
        mTasksDataSource.stopAllTasks();
        for (CalculationResult cResult : mTasksDataSource.getCalculationCash(tabType)) {
            onCalculationStart(cResult, tabType);
        }
    }

    @Override
    public List<CalculationResult> getCalculationCash(@Tabs String tabType) {
        return mTasksDataSource.getCalculationCash(tabType);
    }

    @Override
    public void unbind() {
        mUnbinder.unbind();
    }
}
