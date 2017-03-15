package com.atguigu.p2pinvest.viewpager;

import android.view.View;
import android.widget.TextView;

import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.bean.InvestAllBean;
import com.atguigu.p2pinvest.ui.MyProgress;
import com.atguigu.p2pinvest.utils.UiUtils;

import butterknife.BindView;

/**
 * Created by 熊猛 on 2017/3/14.
 */

public class InvestHolder extends BaseHolder<InvestAllBean.DataBean> {
    @BindView(R.id.p_name)
    TextView pName;
    @BindView(R.id.p_money)
    TextView pMoney;
    @BindView(R.id.p_yearlv)
    TextView pYearlv;
    @BindView(R.id.p_suodingdays)
    TextView pSuodingdays;
    @BindView(R.id.p_minzouzi)
    TextView pMinzouzi;
    @BindView(R.id.p_minnum)
    TextView pMinnum;
    @BindView(R.id.p_progresss)
    MyProgress pProgresss;

    @Override
    public View initView() {
        return UiUtils.getView(R.layout.adapter_all_invest);
    }

    @Override
    public void setChildData() {
        InvestAllBean.DataBean dataBean = getT();
        pName.setText(dataBean.getName());
        pMoney.setText(dataBean.getMoney());
        pMinnum.setText(dataBean.getMemberNum());
        pMinzouzi.setText(dataBean.getMinTouMoney());
        pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));
        pYearlv.setText(dataBean.getYearRate());
        pSuodingdays.setText(dataBean.getSuodingDays());
    }
}
