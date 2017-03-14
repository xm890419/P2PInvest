package com.atguigu.p2pinvest.viewpager;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/14.
 */

public abstract class BaseHolder<T> {
    private final View rootView;

    public BaseHolder() {
        rootView = initView();
        ButterKnife.bind(this,rootView);
        rootView.setTag(this);
    }

    public View getView() {
        return rootView;
    }

    private T t;
    public void setData(T t) {
        this.t = t;
        setChildData();
    }



    public T getT(){
        return t;
    }

    public abstract View initView();
    protected abstract void setChildData();
}
