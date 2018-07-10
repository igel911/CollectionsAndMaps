package ua.com.foxminded.collectionsandmaps.dto;

public abstract class CalculationResult {

    private String mResult;

    CalculationResult(String result) {
        mResult = result;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String mResult) {
        this.mResult = mResult;
    }
}
