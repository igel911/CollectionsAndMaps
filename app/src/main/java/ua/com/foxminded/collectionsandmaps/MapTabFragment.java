package ua.com.foxminded.collectionsandmaps;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.com.foxminded.collectionsandmaps.adapters.RecyclerAdapter;
import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;
import ua.com.foxminded.collectionsandmaps.enums.Maps;
import ua.com.foxminded.collectionsandmaps.enums.Tabs;

public class MapTabFragment extends Fragment implements TabContract.View {

    private TabContract.Presenter mPresenter;
    private RecyclerAdapter mAdapter;

    @BindView(R.id.recyclerViewMapTab) RecyclerView mRecyclerView;
    @BindView(R.id.etQuantityMapTab) EditText etQuantity;

    @Override
    public void setPresenter(TabContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_tab, container, false);
        mPresenter.setUnbinder(ButterKnife.bind(this, view));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), Maps.size));
        mAdapter = new RecyclerAdapter(mPresenter.getCalculationCash(Tabs.MAP_TAB));
        mRecyclerView.setAdapter(mAdapter);

        setRetainInstance(true);
    }

    @OnClick(R.id.btnCalculateMapTab)
    public void onButtonCalculateClick() {
        String etQuantityText = etQuantity.getText().toString();
        if (!etQuantityText.isEmpty()) {
            final int quantity = Integer.parseInt(etQuantityText);
            mPresenter.runCalculation(Tabs.MAP_TAB, quantity);
        }
    }

    @Override
    public void showCalculationResult(final CalculationResult cResult) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.updateItem(cResult);
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stop(Tabs.MAP_TAB);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unbind();
    }
}
