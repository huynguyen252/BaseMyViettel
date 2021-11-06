package com.ttc.demo.basemyviettel.ui.main.detail.simstore;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemvietnam.base.viper.ViewFragment;
import com.ttc.demo.basemyviettel.R;
import com.ttc.demo.basemyviettel.ui.main.detail.MVShopResultActivity;
import com.ttc.demo.basemyviettel.ui.main.detail.adapter.StepStoreSimAdapter;
import com.ttc.demo.basemyviettel.ui.main.detail.eventbus.FragmentIndexChangeEventBus;
import com.ttc.demo.basemyviettel.ui.main.detail.eventbus.StepIndexEventBus;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.enter_info.InfoFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.payment.PaymentFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_mobile_package.MobilePackageFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.select_sim.SelectSimFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.fragment.shipment.ShipmentFragment;
import com.ttc.demo.basemyviettel.ui.main.detail.model.StepModel;
import com.ttc.demo.basemyviettel.ui.main.detail.eventbus.TitleEventBus;
import com.ttc.demo.basemyviettel.ui.main.listener.ItemClickListener;
import com.ttc.demo.basemyviettel.ui.main.listener.OnItemCountChangeListener;
import com.ttc.demo.basemyviettel.ui.main.listener.OnStepDoneListener;
import com.ttc.demo.basemyviettel.utils.ViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public
class NewSimFragment extends ViewFragment<NewSimContract.Presenter> implements NewSimContract.View, ItemClickListener, OnItemCountChangeListener {

    @BindView(R.id.recycler_step)
    RecyclerView recyclerViewStepStoreSim;
    @BindView(R.id.img_back)
    ImageView btnBack;
    @BindView(R.id.tv_header)
    TextView tvHeaderSimStore;
    @BindView(R.id.tv_number_order)
    TextView tvNumberOrderSimStore;
    @BindView(R.id.img_shop)
    ImageView layoutOrder;

    private List<StepModel> mListStoreSim;
    private ArrayList<Fragment> mListFragment;
    private
    StepStoreSimAdapter stepAdapter;
    private int mPositionCurrent = 0;
    public static final int NUMBER_CHON_SO = 0;
    public static final int NUMBER_CHON_GOI_CUOC = 1;
    public static final int NUMBER_NHAP_THONG_TIN = 2;
    public static final int NUMBER_GIAO_HANG = 3;
    public static final int NUMBER_THANH_TOAN = 4;
    private void controlerTitle(int position) {
        if (position == NUMBER_CHON_SO) {
            EventBus.getDefault().post(new TitleEventBus(getString(R.string.chon_so)));
        } else if (position == NUMBER_CHON_GOI_CUOC) {
            EventBus.getDefault().post(new TitleEventBus(getString(R.string.chon_goi_cuoc)));
        } else if (position == NUMBER_NHAP_THONG_TIN) {
            EventBus.getDefault().post(new TitleEventBus(getString(R.string.nhap_thong_tin)));
        } else if (position == NUMBER_GIAO_HANG) {
            EventBus.getDefault().post(new TitleEventBus(getString(R.string.giao_hang)));
        } else if (position == NUMBER_THANH_TOAN) {
            EventBus.getDefault().post(new TitleEventBus(getString(R.string.thanh_toan)));
        }
    }

    public static NewSimFragment getInstance() {
        return new NewSimFragment();
    }

    @Override
    public
    void initLayout() {
        super.initLayout();
        EventBus.getDefault().register(this);
        ButterKnife.bind(getActivity());
        init();
    }

    private void init(){
        layoutOrder.setVisibility(View.VISIBLE);
        tvHeaderSimStore.setText(R.string.thue_bao_moi);
        mListStoreSim = new ArrayList<>();
        mListFragment = new ArrayList<>();
        tvNumberOrderSimStore.setVisibility(View.GONE);
        initStepBar();
        stepAdapter = new StepStoreSimAdapter(mListStoreSim, getActivity());
        recyclerViewStepStoreSim.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewStepStoreSim.setItemAnimator(null);
        recyclerViewStepStoreSim.setHasFixedSize(false);
        stepAdapter.setOnStepClickListener(this);
        recyclerViewStepStoreSim.setAdapter(stepAdapter);
    }

    private void initStepBar(){
        mListStoreSim.add(new StepModel(getResources().getString(R.string.chon_so), false, false, false, false, false));
        mListStoreSim.add(new StepModel(getResources().getString(R.string.chon_goi_cuoc), false, false, false, false, false));
        mListStoreSim.add(new StepModel(getResources().getString(R.string.nhap_thong_tin), false, false, false, false, false));
        mListStoreSim.add(new StepModel(getResources().getString(R.string.giao_hang), false, false, false, false, false));
        mListStoreSim.add(new StepModel(getResources().getString(R.string.thanh_toan), false, false, false, false, false));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPositionEvent(StepIndexEventBus event) {
        if (event != null) {
            //even de bat su kien click trên seekbar
            stepAdapter.notifyItemChangedData(event.getPosition(), event.isShowed());
            recyclerViewStepStoreSim.smoothScrollToPosition(event.getPosition());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFragmentIndexEvent(FragmentIndexChangeEventBus event){
        int position = event.getPosition();
        Fragment frmFindSimSoPreToPos = null;
        if (position == NUMBER_CHON_SO) {
            frmFindSimSoPreToPos = getChildFragmentManager().findFragmentByTag(SelectSimFragment.class.getSimpleName());
        } else if (position == NUMBER_CHON_GOI_CUOC) {
            frmFindSimSoPreToPos = getChildFragmentManager().findFragmentByTag(MobilePackageFragment.class.getSimpleName());
        } else if (position == NUMBER_NHAP_THONG_TIN) {
            frmFindSimSoPreToPos = getChildFragmentManager().findFragmentByTag(InfoFragment.class.getSimpleName());
        } else if (position == NUMBER_GIAO_HANG) {
            frmFindSimSoPreToPos = getChildFragmentManager().findFragmentByTag(ShipmentFragment.class.getSimpleName());
        } else if (position == NUMBER_THANH_TOAN) {
            frmFindSimSoPreToPos = getChildFragmentManager().findFragmentByTag(PaymentFragment.class.getSimpleName());
        }
        if (frmFindSimSoPreToPos != null) {
            for (Fragment item : mListFragment) {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.hide(item);
                ft.commit();
            }
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.show(frmFindSimSoPreToPos);
            ft.commit();
            getChildFragmentManager().executePendingTransactions();
            controlerTitle(position);
            ((ViewFragment) frmFindSimSoPreToPos).getPresenter().start();
        } else {
            mPresenter.getInitFragmentStoreSim(position);
        }
    }

    @OnClick(R.id.img_back)
    public void onViewClick(){
        if(mPositionCurrent == 0){
            requireActivity().finish();
        }
        else{
            if(mPresenter != null){
                mPresenter.getSwitchFragmentStoreSim(mPositionCurrent - 1);
            }
        }
    }


    @Override
    protected
    int getLayoutId() {
        return R.layout.fragment_shop_result;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsInitialized = false;
        EventBus.getDefault().unregister(this);
    }

    @Override
    public
    void onItemClick(View view, Object o, int position) {
        mPresenter.getSwitchFragmentStoreSim(position);
    }


    @Override
    public
    void setSwitchFragmentStoreSim(Fragment fragment, int position) {
        mPositionCurrent = position;
        controlerTitle(position);
        Fragment frmFindStoreSim = getChildFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName());
        ViewUtils.hideShowFragment(frmFindStoreSim, fragment, mListFragment, this);
        if (frmFindStoreSim != null) {
            if (position == NUMBER_CHON_SO) {
                ((SelectSimFragment) frmFindStoreSim).getPresenter().start();
            } else if (position == NUMBER_CHON_GOI_CUOC) {
                ((MobilePackageFragment) frmFindStoreSim).getPresenter().start();
            } else if (position == NUMBER_NHAP_THONG_TIN) {
                ((InfoFragment) frmFindStoreSim).getPresenter().start();
            } else if (position == NUMBER_GIAO_HANG) {
                ((ShipmentFragment) frmFindStoreSim).getPresenter().start();
            } else if (position == NUMBER_THANH_TOAN) {
                ((PaymentFragment) frmFindStoreSim).getPresenter().start();
            }
            onPositionEvent(new StepIndexEventBus(position, true));
        }else {
            onPositionEvent(new StepIndexEventBus(position, false));
        }

    }

    @Override
    public
    void OnItemCountChange() {

    }
}
