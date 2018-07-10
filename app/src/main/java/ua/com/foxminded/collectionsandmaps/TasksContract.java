package ua.com.foxminded.collectionsandmaps;

import java.util.List;

import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;
import ua.com.foxminded.collectionsandmaps.enums.Tabs;

public interface TasksContract {

    interface TasksDataSource {
        void runCalculation(@Tabs String tabType, int quantity, GetCalculationCallback callback);

        List<CalculationResult> getCalculationCash(@Tabs String tabType);

        void addToCalculationCash(CalculationResult cResult, @Tabs String tabType);

        void stopAllTasks();
    }

    interface GetCalculationCallback {
        void onCalculationOver(CalculationResult cResult, @Tabs String tabType);

        void onCalculationStart(CalculationResult cResult, @Tabs String tabType);
    }
}
