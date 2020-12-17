package com.example.day3home.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day3home.R;
import com.example.day3home.adapter.HomeAdapter;
import com.example.day3home.base.BaseActivity;
import com.example.day3home.base.BaseView;
import com.example.day3home.bean.HomeBean;
import com.example.day3home.contract.MainContract;
import com.example.day3home.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {

    private RecyclerView recycler;
    private ArrayList<HomeBean.ResultsBean> list;
    private HomeAdapter homeAdapter;

    @Override
    protected void initData() {
        presenter.getPre();
    }

    @Override
    protected void initView() {

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        homeAdapter = new HomeAdapter(this,list);
        recycler.setAdapter(homeAdapter);
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onRuccess(HomeBean homeBean) {
        List<HomeBean.ResultsBean> results = homeBean.getResults();
        list.addAll(results);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}