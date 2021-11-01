package com.ttc.demo.basemyviettel.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ttc.demo.basemyviettel.R;

import java.util.ArrayList;

public
class ViewUtils {
    public static void hideShowFragment(Fragment fragmentCheck, Fragment fragment, ArrayList<Fragment> fragments, Fragment fragmentCurrent) {
        if (fragmentCheck == null) {
            if(!fragment.isAdded()) {
                controlAddFragment(fragment, fragmentCurrent, fragments);
            }
            else{
                showFragment(fragment,fragments,fragmentCurrent);
            }
        } else {
            showFragment(fragmentCheck,fragments, fragmentCurrent);
        }
    }

    private static void controlAddFragment(Fragment fragment, Fragment fragmentCurrent, ArrayList<Fragment> fragments) {
        FragmentTransaction ft = fragmentCurrent.getChildFragmentManager().beginTransaction();
        ft.add(R.id.ll_content, fragment, fragment.getClass().getSimpleName());
        fragments.add(fragment);
        ft.commitAllowingStateLoss();
    }

    private static void showFragment(Fragment fragment, ArrayList<Fragment> fragments, Fragment fragmentCurrent)
    {
        for (Fragment item : fragments) {
            FragmentTransaction ft = fragmentCurrent.getChildFragmentManager().beginTransaction();
            ft.hide(item);
            ft.commit();
        }
        FragmentTransaction ft = fragmentCurrent.getChildFragmentManager().beginTransaction();
        ft.show(fragment);
        ft.setCustomAnimations(R.anim.push_left_in, R.anim.push_right_in);
        ft.commit();
        fragmentCurrent.getChildFragmentManager().executePendingTransactions();
    }
}
