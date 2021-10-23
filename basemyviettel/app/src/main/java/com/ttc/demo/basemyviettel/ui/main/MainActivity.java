package com.ttc.demo.basemyviettel.ui.main;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.base.ViettelBaseActivity;
import com.ttc.demo.basemyviettel.ui.main.fragment.MainPresenter;
import com.ttc.demo.basemyviettel.widget.CustomHeaderView;

import butterknife.BindView;

public class MainActivity extends ViettelBaseActivity {
    @Override
    public ViewFragment onCreateFirstFragment() {
        return (ViewFragment) new MainPresenter(this).getFragment();
    }
}