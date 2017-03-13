package com.atguigu.p2pinvest.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.base.BaseFragment;
import com.atguigu.p2pinvest.utils.AppNetConfig;
import com.atguigu.p2pinvest.utils.LoadNet;
import com.atguigu.p2pinvest.utils.LoadNetHttp;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/10.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_home_product)
    TextView tvHomeProduct;
    @BindView(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;

    //private TextView textView;
    @Override
    public View initView() {
        /*textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);*/
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        /*Log.e("TAG", "首页数据加载成功");
        textView.setText("首页");*/
        /*
         * 二次封装
         * 为什么要二次封装
         *
         * 第一  调用的方便
         * 第二  修改和维护方便
         * */
        LoadNet.getDataPost(AppNetConfig.INDEX, new LoadNetHttp() {
            @Override
            public void success(String context) {
                Log.i("http", "success: " + context);
            }

            @Override
            public void failure(String error) {
                Log.i("http", "failure: " + error);
            }
        });
    }


}
