package com.atguigu.p2pinvest.fragment;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.adapter.InvestAllAdapter1;
import com.atguigu.p2pinvest.base.BaseFragment;
import com.atguigu.p2pinvest.bean.InvestAllBean;
import com.atguigu.p2pinvest.utils.AppNetConfig;

import butterknife.BindView;

/**
 * Created by 熊猛 on 2017/3/14.
 */

public class InvestAllFragment extends BaseFragment {
    @BindView(R.id.invest_all_lv)
    ListView investAllLv;

    @Override
    protected String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(String json) {
        //Log.e("TAG", "  =" + json);
        InvestAllBean investAllBean = JSON.parseObject(json, InvestAllBean.class);

        //InvestAllAdapter adapter = new InvestAllAdapter(investAllBean.getData());
        InvestAllAdapter1 adapter = new InvestAllAdapter1(investAllBean.getData());
        investAllLv.setAdapter(adapter);
    }

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_invest_all;
    }
}
