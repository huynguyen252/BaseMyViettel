package com.ttc.demo.basemyviettel.ui.main;

import android.os.Bundle;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.base.ViettelBaseActivity;
import com.ttc.demo.basemyviettel.ui.main.fragment.MainPresenter;

public class MainActivity extends ViettelBaseActivity {

    @Override
    public ViewFragment onCreateFirstFragment() {
        return (ViewFragment) new MainPresenter(this).getFragment();
    }
}