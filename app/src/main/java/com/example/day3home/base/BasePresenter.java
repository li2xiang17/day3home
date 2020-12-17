package com.example.day3home.base;

public class BasePresenter<V extends BaseView> {
    public V view;
    public void attachView(V v){
        view = v;
    }
}
