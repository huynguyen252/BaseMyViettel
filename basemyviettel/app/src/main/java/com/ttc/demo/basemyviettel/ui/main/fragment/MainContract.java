package com.ttc.demo.basemyviettel.ui.main.fragment;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.interact.ViettelCallback;
import com.ttc.demo.basemyviettel.ui.main.model.product.MVThemeProductResponse;
import com.ttc.demo.basemyviettel.ui.main.model.sim.ShopHomeResponse;

import java.util.List;

/**
 * The ChangeAccount Contract
 */
interface MainContract {

    interface Interactor extends IInteractor<Presenter> {
        void getCommonSetting(String token, ViettelCallback<GetCommonSettingResult> callback);
    }

    interface View extends PresentView<Presenter> {
        void setInformation(GetCommonSettingResult getCommonSettingResult);
//        void setShopHome(ShopHomeResponse shopHome);
//        void setThemeProduct(MVThemeProductResponse themeProduct);
        void setAllCategoryShopHome(List<Object> list);
    }

    interface Presenter extends IPresenter<View, Interactor> {
        void getCommonSetting(String token);
//        void getShopHomeResult();
//        void getThemeProductResult(String limit);
        void getAllCategoryShopHome(String limit);
    }

}



