package com.example.day3home.presenter;

import com.example.day3home.base.BasePresenter;
import com.example.day3home.bean.HomeBean;
import com.example.day3home.callback.MainCallBack;
import com.example.day3home.contract.MainContract;
import com.example.day3home.model.MainModel;
import com.example.day3home.utils.URLContstan;
import com.example.day3home.view.MainActivity;

public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter {
    private MainContract.IMainModel model;

    public MainPresenter() {
        model = new MainModel();
    }

    @Override
    public void getPre() {
        model.onModel(URLContstan.BASELIST, new MainCallBack<HomeBean>() {
            @Override
            public void onRueecss(HomeBean homeBean) {
//                view.onFail("kk");
                view.onRuccess(homeBean);
            }

            @Override
            public void onFail(String error) {
                view.onFail(error);
            }
        });
    }
}
