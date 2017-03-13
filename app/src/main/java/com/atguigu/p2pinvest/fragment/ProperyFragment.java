package com.atguigu.p2pinvest.fragment;

import com.atguigu.p2pinvest.base.BaseFragment;

/**
 * Created by 熊猛 on 2017/3/10.
 */

public class ProperyFragment extends BaseFragment {
    //private TextView textView;
    /*@Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }*/

    @Override
    protected String getChildUrl() {
        return null;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initData(String json) {
    }

    @Override
    protected int getLayoutid() {
        return 0;
    }
}
