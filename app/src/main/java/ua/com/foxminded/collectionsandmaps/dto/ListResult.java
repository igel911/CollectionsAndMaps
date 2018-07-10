package ua.com.foxminded.collectionsandmaps.dto;

import java.util.Objects;

import ua.com.foxminded.collectionsandmaps.enums.ListTasks;
import ua.com.foxminded.collectionsandmaps.enums.Lists;

public class ListResult extends CalculationResult {

    private @ListTasks String mTaskType;
    private @Lists String mListType;

    public ListResult(@ListTasks String taskType, @Lists String listType, String result) {
        super(result);
        mTaskType = taskType;
        mListType = listType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListResult that = (ListResult) o;
        return mTaskType.equals(that.mTaskType) && mListType.equals(that.mListType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mTaskType, mListType);
    }

    @Override
    public String toString() {
        return "ListResult{" +
                "mTaskType=" + mTaskType +
                ", mListType=" + mListType +
                '}';
    }

    public @ListTasks String getTaskType() {
        return mTaskType;
    }
}
