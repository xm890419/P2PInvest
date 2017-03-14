package com.atguigu.p2pinvest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.p2pinvest.base.BaseFragment;

import java.util.List;


/**
 * Created by 熊猛 on 2017/3/14.
 */

/**
 * FragmentPagerAdapter
 * 会在内存里进行保存 但是不适合fragment较多的情况下
 * FragmentStatePagerAdapter
 * 在内存里会定期回收掉 所以适合较多的fragment
 */

public class InvestAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;

    public InvestAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        if(fragments != null && fragments.size()>0) {
            this.fragments = fragments;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
