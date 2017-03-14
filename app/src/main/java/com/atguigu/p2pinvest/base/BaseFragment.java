package com.atguigu.p2pinvest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.p2pinvest.ui.LoadingPager;

import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/10.
 */

public abstract class BaseFragment extends Fragment {
    /*public Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    public abstract View initView() ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void initData() {

    }*/
    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*View view = View.inflate(getActivity(), getLayoutid(), null);
        ButterKnife.bind(this, view);
        return view;*/
        loadingPager = new LoadingPager(getActivity()) {
            @Override
            protected void onSuccess(ResultState resultState, View sucessView) {

                ButterKnife.bind(BaseFragment.this,sucessView);
                initData(resultState.getJson());
                //初始化监听  因为LoadingPager加载布局可能不成功，所以监听写这
                initListener();
            }

            @Override
            protected String getUrl() {
                return getChildUrl();
            }

            @Override
            public int getViewId() {
                return getLayoutid();
            }
        };
        return loadingPager;
    }

    protected abstract String getChildUrl();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据



        loadingPager.loadData();
    }

    protected abstract void initListener();

    protected abstract void initData(String json);

    protected abstract int getLayoutid();
}
