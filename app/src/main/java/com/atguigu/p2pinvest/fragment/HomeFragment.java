package com.atguigu.p2pinvest.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.p2pinvest.base.BaseFragment;

/**
 * Created by 熊猛 on 2017/3/10.
 */

public class HomeFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "首页数据加载成功");
        textView.setText("首页");
    }
}
