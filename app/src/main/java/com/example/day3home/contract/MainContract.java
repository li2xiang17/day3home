package com.example.day3home.contract;

import com.example.day3home.base.BaseView;
import com.example.day3home.bean.HomeBean;
import com.example.day3home.callback.MainCallBack;

public class MainContract {
    public interface IMainView extends BaseView {
        void onRuccess(HomeBean homeBean);
        void onFail(String error);
    }

    public interface IMainPresenter{
        void getPre();
    }

    public interface IMainModel{
        <T> void  onModel(String url, MainCallBack<T> callBack);
    }
}
