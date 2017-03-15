package com.atguigu.p2pinvest.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.atguigu.p2pinvest.bean.DataBean;
import com.atguigu.p2pinvest.bean.UserInfo;

import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/15.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutid());
        ButterKnife.bind(this);
        initTitle();
        initData();
        initListener();
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initTitle();

    public abstract int getLayoutid();

    //弹出吐司
    public void showToast(String context){
        Toast.makeText(this, context, Toast.LENGTH_SHORT).show();
    }
    //保存用户信息
    public void saveUser(UserInfo userInfo){
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("imageurl",userInfo.getData().getImageurl());
        edit.putString("iscredit",userInfo.getData().getIscredit());
        edit.putString("name",userInfo.getData().getName());
        edit.putString("phone",userInfo.getData().getPhone());
        edit.commit();
    }
    //获取用户信息
    public UserInfo getUser(){
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        String imageurl = sp.getString("imageurl", "");
        String iscredit = sp.getString("iscredit", "");
        String name = sp.getString("name", "");
        String phone = sp.getString("phone", "");
        UserInfo userInfo = new UserInfo();
        DataBean dataBean = new DataBean();
        dataBean.setImageurl(imageurl);
        dataBean.setIscredit(iscredit);
        dataBean.setName(name);
        dataBean.setPhone(phone);
        userInfo.setData(dataBean);
        return userInfo;
    }
}
