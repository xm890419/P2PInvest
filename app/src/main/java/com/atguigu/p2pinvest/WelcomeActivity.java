package com.atguigu.p2pinvest;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2pinvest.activity.LoginActivity;
import com.atguigu.p2pinvest.base.BaseActivity;
import com.atguigu.p2pinvest.utils.AppManager;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @BindView(R.id.splash_tv_version)
    TextView splashTvVersion;
    @BindView(R.id.activity_welcome)
    RelativeLayout activityWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        AppManager.getInstance().addActivity(this);
        showAnimation();
        setVersion();

    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_welcome;
    }

    private void setVersion() {
        splashTvVersion.setText(getVersion());
    }

    private String getVersion() {

        try {
            //拿到包的管理器
            PackageManager packageManager = getPackageManager();
            //拿到包的信息
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            //versionCode每次发布新版本一定要加1
            int versionCode = packageInfo.versionCode;
            //当前的版本号
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void showAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /*//进入主页面
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();*/
                if(isLogin()) {
                    //登录过进入主界面
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }else {
                    //没有登录过进入登录界面
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        activityWelcome.setAnimation(animation);
    }

    private boolean isLogin() {
        String name = getUser().getData().getName();
        if(TextUtils.isEmpty(name)) {
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
    }
}
