package com.atguigu.p2pinvest.fragment;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.base.BaseFragment;
import com.atguigu.p2pinvest.bean.HomeBean;
import com.atguigu.p2pinvest.ui.MyProgress;
import com.atguigu.p2pinvest.utils.AppNetConfig;
import com.atguigu.p2pinvest.utils.ThreadPool;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 熊猛 on 2017/3/10.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_home_product)
    TextView tvHomeProduct;
    @BindView(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @BindView(R.id.home_progress)
    MyProgress homeProgress;

    //private TextView textView;
    /*@Override
    public View initView() {
        *//*textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);*//*
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }*/

    @Override
    public int getLayoutid() {
        return R.layout.fragment_home;
    }

    @Override
    public String getChildUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    public void initListener() {

        tvTitle.setText("首页");
        ivTitleBack.setVisibility(View.GONE);
        ivTitleSetting.setVisibility(View.GONE);

    }


    @Override
    public void initData(String json) {
        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
        //Log.i("http", "success: "+homeBean.getImageArr().size());
        tvHomeYearrate.setText(homeBean.getProInfo().getYearRate() + "%");
        tvHomeProduct.setText(homeBean.getProInfo().getName());
        //注意：展示UI一定要判断是不是主线程

        initProgress(homeBean.getProInfo());
        initBanner(homeBean);

       /* *//*Log.e("TAG", "首页数据加载成功");
        textView.setText("首页");*//*

        *//*
         * 二次封装
         * 为什么要二次封装
         *
         * 第一  调用的方便
         * 第二  修改和维护方便
         * *//*
        LoadNet.getDataPost(AppNetConfig.INDEX, new LoadNetHttp() {
            @Override
            public void success(String context) {
                Log.i("http", "success: " + context);
                HomeBean homeBean = JSON.parseObject(context, HomeBean.class);
                //Log.i("http", "success: "+homeBean.getImageArr().size());
                tvHomeYearrate.setText(homeBean.getProInfo().getYearRate() + "%");
                tvHomeProduct.setText(homeBean.getProInfo().getName());
                //注意：展示UI一定要判断是不是主线程

                initProgress(homeBean.getProInfo());
                initBanner(homeBean);
            }

            @Override
            public void failure(String error) {
                Log.i("http", "failure: " + error);
            }
        });*/
    }


    private void initProgress(final HomeBean.ProInfoBean proInfo) {
        ThreadPool.getInstance().getGlobalThread().execute(new Runnable() {
            @Override
            public void run() {
                int progress = Integer.parseInt(proInfo.getProgress());
                for (int i = 0; i <= progress; i++) {
                    SystemClock.sleep(30);
                    homeProgress.setProgress(i);
                }
            }
        });
    }

    private void initBanner(HomeBean homeBean) {

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //转化成url集合
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < homeBean.getImageArr().size(); i++) {
            urls.add(AppNetConfig.BASE_URL + homeBean.getImageArr().get(i).getIMAURL());
        }
        //设置图片集合
        banner.setImages(urls);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        String[] titles = new String[]{"分享返学费1111元", "人脉总动员", "想不到你是这样的APP", "11月兑物节"};
        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);

        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }

}
