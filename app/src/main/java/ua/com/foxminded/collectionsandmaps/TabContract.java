package ua.com.foxminded.collectionsandmaps;

import java.util.List;

import butterknife.Unbinder;
import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;
import ua.com.foxminded.collectionsandmaps.enums.Tabs;

interface TabContract {

    interface View {
        void setPresenter(Presenter presenter);

        void showCalculationResult(CalculationResult cResult);
    }

    interface Presenter {
        void setUnbinder(Unbinder unbinder);

        void runCalculation(@Tabs String tabType, int quantity);

        void stop(@Tabs String tabType);

        List<CalculationResult> getCalculationCash(@Tabs String tabType);

        void unbind();
    }
}
