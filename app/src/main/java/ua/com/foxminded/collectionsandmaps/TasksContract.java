package ua.com.foxminded.collectionsandmaps;

import java.util.List;

import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;

public interface TasksContract {

    interface TasksDataSource {
        void runCalculation(int quantity, GetCalculationCallback callback);

        List<CalculationResult> getCalculationCash();

        void addToCalculationCash(CalculationResult cResult);

        void stopAllTasks();
    }

    interface GetCalculationCallback {
        void onCalculationOver(CalculationResult cResult);

        void onCalculationStart(CalculationResult cResult);
    }
}
