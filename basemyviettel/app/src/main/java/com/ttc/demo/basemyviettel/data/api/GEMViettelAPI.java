package com.ttc.demo.basemyviettel.data.api;

import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.data.model.SearchResult;
import com.ttc.demo.basemyviettel.data.model.UtilitiesResult;
import io.reactivex.Observable;
import io.reactivex.Single;
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

  @FormUrlEncoded
  @POST("omiSearchSim")
  Call<SearchResult> getSearchResult(@Field("key_search") String text,
                                     @Field("page_size") int limit,
                                     @Field("page") int offset,
                                     @Field("type") int type,
                                     @Field("token") String token);


}