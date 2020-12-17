package com.example.day3home.model;

import com.example.day3home.callback.MainCallBack;
import com.example.day3home.contract.MainContract;
import com.example.day3home.utils.RetrorfitUilts;

public class MainModel implements MainContract.IMainModel {
    @Override
    public <T> void onModel(String url, MainCallBack<T> callBack) {
        RetrorfitUilts.getRetrorfitUilts().get(url,callBack);
    }
}
