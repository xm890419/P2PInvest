package com.atguigu.p2pinvest.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.base.BaseFragment;
import com.atguigu.p2pinvest.ui.FlowLayout;
import com.atguigu.p2pinvest.utils.DrawUtils;
import com.atguigu.p2pinvest.utils.UiUtils;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/14.
 */

public class InvestHotFragment extends BaseFragment {


    @BindView(R.id.ivest_hot_fl)
    FlowLayout ivestHotFl;
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
    };
    private Random random = new Random();
    private TextView tv;

    @Override
    protected String getChildUrl() {
        return null;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(String json) {

        /*ivestHotFl.setAdapter(new TagAdapter<String>(datas) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = new TextView(getActivity());
                tv.setText(s);
                //设置shape
                tv.setBackgroundDrawable(
                        getResources().getDrawable(R.drawable.hot_shape));
                //获取shapeDrawable
                GradientDrawable drawable = (GradientDrawable) tv.getBackground();

                int red = random.nextInt(200 - 50) + 50;
                int green = random.nextInt(200-50)+50;
                int blue = random.nextInt(200-50)+50;
                //设置shape的背景色
                drawable.setColor(Color.rgb(red, green, blue));
                //tv.setBackgroundDrawable(DrawableUtils.getDrawable());
                return tv;
            }
        });*/
        for (int i = 0; i < datas.length; i++) {
            tv = new TextView(getContext());

            //设置属性
            tv.setText(datas[i]);
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UiUtils.dp2px(5);
            mp.rightMargin = UiUtils.dp2px(5);
            mp.topMargin = UiUtils.dp2px(10);
            mp.bottomMargin = UiUtils.dp2px(10);
            tv.setLayoutParams(mp);//设置边距

            int padding = UiUtils.dp2px(5);
            tv.setPadding(padding, padding, padding, padding);//设置内边距

            tv.setTextSize(UiUtils.dp2px(7));

            Random random = new Random();
            int red = random.nextInt(150) + 100;
            int green = random.nextInt(150) + 100;
            int blue = random.nextInt(150) + 100;

            //设置具有选择器功能的背景
            tv.setBackground(DrawUtils.getSelector(DrawUtils.getDrawable(Color.rgb(red, green, blue), UiUtils.dp2px(5)), DrawUtils.getDrawable(Color.WHITE, UiUtils.dp2px(5))));

            ivestHotFl.addView(tv);
        }

    }

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_invest_hot;
    }
}
