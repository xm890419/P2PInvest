package com.atguigu.p2pinvest.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2pinvest.MainActivity;
import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.activity.ColumnActivity;
import com.atguigu.p2pinvest.activity.LineChartActivity;
import com.atguigu.p2pinvest.activity.PieActivity;
import com.atguigu.p2pinvest.base.BaseFragment;
import com.atguigu.p2pinvest.bean.UserInfo;
import com.atguigu.p2pinvest.utils.AppNetConfig;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by 熊猛 on 2017/3/10.
 */

public class ProperyFragment extends BaseFragment {
    @BindView(R.id.tv_settings)
    TextView tvSettings;
    @BindView(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @BindView(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @BindView(R.id.tv_me_name)
    TextView tvMeName;
    @BindView(R.id.rl_me)
    RelativeLayout rlMe;
    @BindView(R.id.recharge)
    ImageView recharge;
    @BindView(R.id.withdraw)
    ImageView withdraw;
    @BindView(R.id.ll_touzi)
    TextView llTouzi;
    @BindView(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @BindView(R.id.ll_zichan)
    TextView llZichan;
    //private TextView textView;
    /*@Override
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
        llTouzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LineChartActivity.class));
            }
        });
        llTouziZhiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ColumnActivity.class));
            }
        });
        llZichan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),PieActivity.class));
            }
        });
    }

    @Override
    public void initData(String json) {
        MainActivity activity = (MainActivity) getActivity();
        UserInfo user = activity.getUser();
        //设置用户名
        tvMeName.setText(user.getData().getName());
        //设置头像
        /*Picasso.with(getActivity()).load(AppNetConfig.BASE_URL+"/images/tx.png")
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap bitmap) {
                        Bitmap circleBitmap = BitmapUtils.circleBitmap(bitmap);

                        bitmap.recycle(); //必须把原来的释放掉
                        return circleBitmap;
                    }

                    @Override
                    public String key() {
                        return "";//不能为空否则会报错
                    }
                }).into(ivMeIcon);*/
        Picasso.with(getActivity()).load(AppNetConfig.BASE_URL+"/images/tx.png")
                .transform(new CropCircleTransformation()).into(ivMeIcon);
    }

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_propery;
    }
}
