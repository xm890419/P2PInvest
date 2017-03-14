package com.atguigu.p2pinvest.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.bean.InvestAllBean;
import com.atguigu.p2pinvest.ui.MyProgress;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/14.
 */

public class InvestAllAdapter extends BaseAdapter {

    private final List<InvestAllBean.DataBean> data = new ArrayList<>();

    public InvestAllAdapter(List<InvestAllBean.DataBean> data) {
        if (data != null && data.size() > 0) {
            this.data.clear();
            this.data.addAll(data);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_all_invest, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        InvestAllBean.DataBean dataBean = data.get(position);
        viewHolder.pName.setText(dataBean.getName());
        viewHolder.pMoney.setText(dataBean.getMoney());
        viewHolder.pMinnum.setText(dataBean.getMemberNum());
        viewHolder.pMinzouzi.setText(dataBean.getMinTouMoney());
        viewHolder.pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));
        viewHolder.pYearlv.setText(dataBean.getYearRate());
        viewHolder.pSuodingdays.setText(dataBean.getSuodingDays());
        return convertView;
    }

    static class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
