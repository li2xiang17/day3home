package com.example.day3home.utils;

import android.util.Log;

import com.example.day3home.api.ApiService;
import com.example.day3home.callback.MainCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrorfitUilts implements INetWorkInterface{
    private static volatile RetrorfitUilts retrorfitUilts;
    private final ApiService service;

    public RetrorfitUilts() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLContstan.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);
    }

    public static RetrorfitUilts getRetrorfitUilts() {
        if (retrorfitUilts==null){
            synchronized (RetrorfitUilts.class){
                if (retrorfitUilts==null){
                    retrorfitUilts = new RetrorfitUilts();
                }
            }
        }
        return retrorfitUilts;
    }


    @Override
    public <T> void get(String url, MainCallBack<T> callBack) {
        service.get(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            Gson gson = new Gson();
                            Type[] interfaces = callBack.getClass().getGenericInterfaces();
                            Type[] arguments = ((ParameterizedType) interfaces[0]).getActualTypeArguments();
                            Type  type =  arguments[0];
                            T result = gson.fromJson(json, type);

                            callBack.onRueecss(result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG", "网络异常: "+e.getMessage() );
                        callBack.onFail("网络异常"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
