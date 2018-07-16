package ua.com.foxminded.collectionsandmaps;

import java.util.List;

import butterknife.Unbinder;
import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;

interface TabContract {

    interface View {

        void showCalculationResult(CalculationResult cResult);
    }

    interface Presenter {
        void setUnbinder(Unbinder unbinder);

        void runCalculation(int quantity);

        void stop();

        List<CalculationResult> getCalculationCash();

        void unbind();

        void setView(View view);
    }
}
