package com.example.day3home.utils;

import com.example.day3home.callback.MainCallBack;

public interface INetWorkInterface {
    public <T> void get(String url,MainCallBack<T> callBack);
}
