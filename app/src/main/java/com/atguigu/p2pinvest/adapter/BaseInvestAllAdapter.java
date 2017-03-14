package com.atguigu.p2pinvest.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.atguigu.p2pinvest.viewpager.BaseHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 熊猛 on 2017/3/14.
 */

public abstract class BaseInvestAllAdapter<T> extends BaseAdapter {

    private final List<T> data = new ArrayList<>();

    public BaseInvestAllAdapter(List<T> data) {
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
        BaseHolder baseHolder;
        if (convertView == null) {
            baseHolder = getHolder();
        }else {
            baseHolder = (BaseHolder) convertView.getTag();
        }

        T t = data.get(position);

        baseHolder.setData(t);

        return baseHolder.getView();
    }

    public abstract BaseHolder getHolder() ;
}
