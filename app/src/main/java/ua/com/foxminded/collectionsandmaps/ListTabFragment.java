package ua.com.foxminded.collectionsandmaps;


import android.content.Context;
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

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.com.foxminded.collectionsandmaps.adapters.RecyclerAdapter;
import ua.com.foxminded.collectionsandmaps.dto.CalculationResult;
import ua.com.foxminded.collectionsandmaps.enums.Lists;

public class ListTabFragment extends Fragment implements TabContract.View {

    @Named("List") @Inject TabContract.Presenter mPresenter;
    private RecyclerAdapter mAdapter;

    @BindView(R.id.recyclerViewListTab) RecyclerView mRecyclerView;
    @BindView(R.id.etQuantityListTab) EditText etQuantity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DaggerActivityComponent.create().injectListTabFragment(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_tab, container, false);
        mPresenter.setUnbinder(ButterKnife.bind(this, view));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), Lists.size));
        mAdapter = new RecyclerAdapter(mPresenter.getCalculationCash());
        mRecyclerView.setAdapter(mAdapter);

        setRetainInstance(true);
    }

    @OnClick(R.id.btnCalculateListTab)
    public void onButtonCalculateClick() {
        String etQuantityText = etQuantity.getText().toString();
        if (!etQuantityText.isEmpty()) {
            final int quantity = Integer.parseInt(etQuantityText);
            mPresenter.runCalculation(quantity);
        }
    }

    @Override
    public void showCalculationResult(final CalculationResult cResult) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(() -> mAdapter.updateItem(cResult));
        }
    }

    @Inject
    void setView() {
        mPresenter.setView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stop();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unbind();
    }
}
