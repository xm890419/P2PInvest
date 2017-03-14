package com.atguigu.p2pinvest.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.adapter.InvestAdapter;
import com.atguigu.p2pinvest.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 熊猛 on 2017/3/10.
 */

public class InvestFragment extends BaseFragment {
    @BindView(R.id.invest_vp)
    ViewPager investVp;
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    //private TextView textView;
   /* @Override
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
        //设置标题
        initTitle();
        //初始化Fragmet
        initFragment();
        //初始化viewPager
        initViewPager();
    }

    private void initViewPager() {
        investVp.setAdapter(new InvestAdapter(getChildFragmentManager(),fragments));
    }

    private List<BaseFragment> fragments = new ArrayList<>();
    private void initFragment() {
        fragments.add(new InvestAllFragment());
        fragments.add(new InvestRecommendFragment());
        fragments.add(new InvestHotFragment());
    }

    private void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        ivTitleSetting.setVisibility(View.GONE);
        tvTitle.setText("投资");
    }

    @Override
    protected int getLayoutid() {

        return R.layout.fragment_invest;
    }

}
