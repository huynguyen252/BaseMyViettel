package com.ttc.demo.basemyviettel.data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.tbruyelle.rxpermissions.BuildConfig;
import com.ttc.demo.basemyviettel.data.api.GEMViettelAPI;
import com.ttc.demo.basemyviettel.data.model.GetCommonSettingResult;
import com.ttc.demo.basemyviettel.data.model.SimpleResult;
import com.ttc.demo.basemyviettel.utils.Constants;
import com.ttc.demo.basemyviettel.utils.Utilities;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HungNX on 6/30/16.
 */
public class NetWorkController {

    private static volatile GEMViettelAPI apiBuilder;
    private static volatile GEMViettelAPI apiBuilderRxJava;

    public static OkHttpClient okHttpClient() {
        return okHttpClientWithTimeOut(30);
    }


    public static OkHttpClient okHttpClientWithTimeOut(int timeout) {

        final String deviceName = android.os.Build.MODEL;
        final String versionApp = BuildConfig.VERSION_NAME;
        final String build_code = String.valueOf(BuildConfig.VERSION_CODE);

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext ssContext = SSLContext.getInstance("TLS");
            ssContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = ssContext
                    .getSocketFactory();
            HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            final OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                    .certificatePinner(getCertificatePinner())
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            HttpUrl httpUrl = original.url();
                            HttpUrl newHttpUrl = httpUrl.newBuilder().addQueryParameter("device_name", deviceName)
                                    .addQueryParameter("version_app", versionApp)
                                    .addQueryParameter("build_code", build_code)
                                    .addQueryParameter("os_type", "android")
                                    .addQueryParameter("os_version", android.os.Build.VERSION.SDK_INT + "")
//                            .addQueryParameter("device_id", Utilities.getIMEI(mContext))
//                            .addQueryParameter("address_mac", Utilities.getMac(mContext))
                                    .build();
                            Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl);
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    }).addInterceptor(loggingInterceptor)
                    .readTimeout(timeout, TimeUnit.SECONDS)
                    .connectTimeout(timeout, TimeUnit.SECONDS);

            final OkHttpClient okHttpClient1 = builder.build();

            return okHttpClient1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static CertificatePinner getCertificatePinner() {
        if (Constants.CERT_INFO != null) {
            CertificatePinner.Builder builder = new CertificatePinner.Builder();
            for (String[] item : Constants.CERT_INFO) {
                builder.add(item[0], item[1]);
            }
            return builder.build();
        }

        return null;
    }

    private static GEMViettelAPI getAPIBuilder() {
        if (apiBuilder == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .registerTypeAdapter(Long.class, new LongTypeAdapter())
                    .registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
                    .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient())
                    .build();
            apiBuilder = retrofit.create(GEMViettelAPI.class);
        }
        return apiBuilder;
    }

    //=====================================================================

    private static GEMViettelAPI getApiBuilderRxJava() {
        if (apiBuilderRxJava == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .registerTypeAdapter(Long.class, new LongTypeAdapter())
                    .registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
                    .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient())
                    .build();
            apiBuilderRxJava = retrofit.create(GEMViettelAPI.class);
        }
        return apiBuilderRxJava;
    }

    public static class LongTypeAdapter extends TypeAdapter<Long> {
        @Override
        public Long read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            String stringValue = reader.nextString();
            try {
                Long value = Long.valueOf(stringValue);
                return value;
            } catch (NumberFormatException e) {
                return null;
            }
        }

        @Override
        public void write(JsonWriter writer, Long value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static class IntegerTypeAdapter extends TypeAdapter<Integer> {
        @Override
        public Integer read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            String stringValue = reader.nextString();
            try {
                Integer value = Integer.valueOf(stringValue);
                return value;
            } catch (NumberFormatException e) {
                return null;
            }
        }

        @Override
        public void write(JsonWriter writer, Integer value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static class DoubleTypeAdapter extends TypeAdapter<Double> {
        @Override
        public Double read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            String stringValue = reader.nextString();
            try {
                Double value = Double.valueOf(stringValue);
                return value;
            } catch (NumberFormatException e) {
                return null;
            }
        }

        @Override
        public void write(JsonWriter writer, Double value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    public static void getCommonSettingResult(String token, Observer<GetCommonSettingResult> observer) {
        getApiBuilderRxJava().getCommonSetting(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}