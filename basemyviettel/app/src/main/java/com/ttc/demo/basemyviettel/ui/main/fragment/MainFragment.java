package com.ttc.demo.basemyviettel.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.data.model.GetShopHomeResult;
import com.ttc.demo.basemyviettel.data.model.SimModel;
import com.ttc.demo.basemyviettel.ui.adapter.SimAdapter;

import java.util.ArrayList;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends ViewFragment<MainContract.Presenter>
        implements MainContract.View {

    @BindView(R.id.tv_result)
    AppCompatTextView tvResult;

    @BindView(R.id.listItems)
    RecyclerView listItems;

    private ArrayList<SimModel> listSim;
    private View mContentView;
    private Timer mTimer;
    private SimAdapter listSimAdapter;
    private MainContract.Presenter mainShopPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listSim = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.search_sim_item, container, false);
            ButterKnife.bind(this, mContentView);
            setup();
        }
        return mContentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    private void setup() {
        listItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems.setHasFixedSize(true);
        callApiShopHome();
    }

    private void callApiShopHome() {
        mainShopPresenter.getShopHomeResult();
    }

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initLayout() {
        super.initLayout();
        mPresenter.getCommonSetting("");
    }

    @Override
    public void setInformation(GetCommonSettingResult getCommonSettingResult) {
        tvResult.setText(getCommonSettingResult.getMessage());
    }

    @Override
    public void setShopHome(GetShopHomeResult shopHome) {
        listSim.clear();

        if (shopHome.getData().getSim() != null) {
            for (SimModel item : shopHome.getData().getSim()) {
                listSim.add(item);
            }
        }
        listItems.setAdapter(listSimAdapter);


    }
}