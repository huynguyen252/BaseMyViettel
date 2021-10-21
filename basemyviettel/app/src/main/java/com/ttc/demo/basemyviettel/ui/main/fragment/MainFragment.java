package com.ttc.demo.basemyviettel.ui.main.fragment;

import androidx.appcompat.widget.AppCompatTextView;
import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;

import butterknife.BindView;

/**
 * The ChangeAccount Fragment
 */
public class MainFragment extends ViewFragment<MainContract.Presenter>
        implements MainContract.View {

    @BindView(R.id.tv_result)
    AppCompatTextView tvResult;

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
}
