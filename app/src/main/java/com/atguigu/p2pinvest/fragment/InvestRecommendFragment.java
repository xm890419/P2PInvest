package com.atguigu.p2pinvest.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.base.BaseFragment;
import com.atguigu.p2pinvest.ui.randomLayout.StellarMap;
import com.atguigu.p2pinvest.utils.UiUtils;

import java.util.Random;

import butterknife.BindView;

/**
 * Created by 熊猛 on 2017/3/14.
 */

public class InvestRecommendFragment extends BaseFragment {
    @BindView(R.id.invest_rec_sm)
    StellarMap investRecSm;

    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

    private String[] oneGroup = new String[datas.length / 2];
    private String[] twoGroup = new String[datas.length - datas.length / 2];

    private Random random = new Random();

    @Override
    protected String getChildUrl() {
        return null;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(String json) {

        for (int i = 0; i < datas.length; i++) {
            //第一个组
            if (i < datas.length / 2) {
                oneGroup[i] = datas[i];
            } else {
                //第二个组
                twoGroup[i - datas.length / 2] = datas[i];
            }
        }
        investRecSm.setAdapter(new RecommendAdapter());

        //必须调用如下的两个方法，否则stellarMap不能显示数据
        //设置显示的数据在x轴、y轴方向上的稀疏度
        investRecSm.setRegularity(5, 7);
        //设置初始化显示的组别，以及是否需要使用动画
        investRecSm.setGroup(0, true);
        investRecSm.setInnerPadding(UiUtils.dp2px(15), UiUtils.dp2px(15),
                UiUtils.dp2px(15), UiUtils.dp2px(15));
    }

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_invest_recommend;
    }

    class RecommendAdapter implements StellarMap.Adapter {

        /*
        * 有几个组
        * */
        @Override
        public int getGroupCount() {
            return 2;
        }

        /*
        * 每组有多少数量  根据不同的组返回不同的数量
        * */
        @Override
        public int getCount(int group) {
            if (group == 0) {
                return datas.length / 2;
            } else {
                return datas.length - datas.length / 2;
            }
        }

        //返回view 根据不同的组返回不同的view
        //每个组都是从0开始
        @Override
        public View getView(int group, int position, View convertView) {

            TextView tv = new TextView(getActivity());
            if (group == 0) {
                tv.setText(oneGroup[position]);
            } else {
                tv.setText(twoGroup[position]);
            }
            int red = random.nextInt(220-50)+50;//0-255 颜色值  50-220随机出来的值
            int green = random.nextInt(220-50)+50;
            int blue = random.nextInt(220-50)+50;
            tv.setTextColor(Color.rgb(red,green,blue));

            return tv;
        }

        //预留方法不用实现
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        //返回下一组的组号
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (group == 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
