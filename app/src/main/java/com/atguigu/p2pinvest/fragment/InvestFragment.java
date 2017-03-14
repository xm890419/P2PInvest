package com.atguigu.p2pinvest.fragment;

import android.graphics.Color;
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
    @BindView(R.id.tv_all_invest)
    TextView tvAllInvest;
    @BindView(R.id.tv_recommend_invest)
    TextView tvRecommendInvest;
    @BindView(R.id.tv_hot_invest)
    TextView tvHotInvest;
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
        investVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tvAllInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(0);
            }
        });
        tvRecommendInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(1);
            }
        });
        tvHotInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investVp.setCurrentItem(2);
            }
        });
    }

    @Override
    public void initData(String json) {
        //设置标题
        initTitle();
        //初始化Fragmet
        initFragment();
        //初始化viewPager
        initViewPager();
        //设置默认选中的tab
        initTab();
    }

    private void initTab() {
        selectText(0);
    }

    private void selectText(int id) {
        //把所有的背景色还原成默认值
        hiddenTextState();
        switch (id) {
            case 0:
                //改变当前的背景色
                tvAllInvest.setBackgroundColor(Color.BLUE);
                tvAllInvest.setTextColor(Color.RED);
                break;
            case 1:
                tvRecommendInvest.setBackgroundColor(Color.BLUE);
                tvRecommendInvest.setTextColor(Color.RED);
                break;
            case 2:
                tvHotInvest.setBackgroundColor(Color.BLUE);
                tvHotInvest.setTextColor(Color.RED);
                break;
        }
    }

    private void hiddenTextState() {
        tvAllInvest.setBackgroundColor(Color.WHITE);
        tvAllInvest.setTextColor(Color.BLACK);
        tvRecommendInvest.setBackgroundColor(Color.WHITE);
        tvRecommendInvest.setTextColor(Color.BLACK);
        tvHotInvest.setBackgroundColor(Color.WHITE);
        tvHotInvest.setTextColor(Color.BLACK);
    }

    private void initViewPager() {
        investVp.setAdapter(new InvestAdapter(getChildFragmentManager(), fragments));
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
