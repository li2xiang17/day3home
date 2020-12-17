package com.example.day3home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day3home.R;
import com.example.day3home.bean.HomeBean;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<HomeBean.ResultsBean> list;

    public HomeAdapter(Context context, ArrayList<HomeBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.home_item,parent,false);
        return new HomeViewHomder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeViewHomder homeViewHomder = (HomeViewHomder) holder;
        HomeBean.ResultsBean resultsBean = list.get(position);
        Glide.with(context).load(resultsBean.getUrl()).into(homeViewHomder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class HomeViewHomder extends RecyclerView.ViewHolder {
        ImageView img;
        public HomeViewHomder(View root) {
            super(root);
            img = root.findViewById(R.id.iv_img);
        }
    }
}
