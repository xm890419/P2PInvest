package com.atguigu.p2pinvest.adapter;

import com.atguigu.p2pinvest.bean.InvestAllBean;
import com.atguigu.p2pinvest.viewpager.BaseHolder;
import com.atguigu.p2pinvest.viewpager.InvestHolder;

import java.util.List;

/**
 * Created by 熊猛 on 2017/3/14.
 */

public class InvestAllAdapter1 extends BaseInvestAllAdapter<InvestAllBean.DataBean> {
    public InvestAllAdapter1(List<InvestAllBean.DataBean> data) {
        super(data);
    }

    @Override
    public BaseHolder getHolder() {
        return new InvestHolder();
    }
}
