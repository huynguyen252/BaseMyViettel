package com.ttc.demo.basemyviettel.data.api;

import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.data.model.UtilitiesResult;
import com.ttc.demo.basemyviettel.ui.main.detail.response.AreaDetailResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.AreaResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.MobilePackageResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.NumberResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.ServicePackageResponse;
import com.ttc.demo.basemyviettel.ui.main.detail.response.ShopAreaResponse;
import com.ttc.demo.basemyviettel.ui.main.model.product.MVThemeProductResponse;
import com.ttc.demo.basemyviettel.ui.main.model.sim.ShopHomeResponse;

import java.util.Objects;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by HungNX on 6/30/16.
 */
public interface GEMViettelAPI {

  //Sử dụng RxJava
//  @FormUrlEncoded
//  @POST("getCommonSetting")
//  Observable<GetCommonSettingResult> getCommonSetting(@Field("token") String token);

  @FormUrlEncoded
  @POST("getCommonSetting")
  Call<GetCommonSettingResult> getCommonSetting(@Field("token") String token);

  @POST("getShopHome")
  Observable<ShopHomeResponse> getShopHome1();

  @FormUrlEncoded
  @POST("getThemeProducts")
  Observable<MVThemeProductResponse> getThemeProduct1(@Field("isLimit") String limit);

  @POST("omiSearchSim")
  Observable<NumberResponse> getListNumber();

  @POST("omiGetProductPackage")
  Observable<MobilePackageResponse> getListMobilePackage();

  @POST("omiGetVasPackage")
  Observable<ServicePackageResponse> getListVasPackage();

  @POST("omiGetAreaByParentCode")
  Observable<AreaResponse> getListArea();

  @POST("omiGetAllProvinces")
  Observable<AreaDetailResponse> getListProvinceDetail();

  @FormUrlEncoded
  @POST("findShopByAreaCode")
  Observable<ShopAreaResponse> getShopAreaByCode(@Field("area") String area);
}