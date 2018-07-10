package ua.com.foxminded.collectionsandmaps.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.foxminded.collectionsandmaps.R;
import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<CalculationResult> mData;

    public RecyclerAdapter(List<CalculationResult> dataSet) {
        mData = dataSet;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_custom_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        CalculationResult result = mData.get(position);
        String resultText = result.getResult();
        if (resultText == null) {
            holder.mProgressBar.setVisibility(ProgressBar.VISIBLE);
            holder.mTextView.setText("");
        } else {
            holder.mTextView.setText(resultText);
            holder.mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateItem(CalculationResult cResult) {
        int index = mData.indexOf(cResult);
        mData.set(index, cResult);
        notifyItemChanged(index);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvRecyclerItem) TextView mTextView;

        @BindView(R.id.pbRecyclerItem) ProgressBar mProgressBar;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
