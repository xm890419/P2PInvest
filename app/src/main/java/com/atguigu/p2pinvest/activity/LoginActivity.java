package com.atguigu.p2pinvest.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.p2pinvest.MainActivity;
import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.base.BaseActivity;
import com.atguigu.p2pinvest.bean.UserInfo;
import com.atguigu.p2pinvest.utils.AppNetConfig;
import com.atguigu.p2pinvest.utils.LoadNet;
import com.atguigu.p2pinvest.utils.LoadNetHttp;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.tv_login_number)
    TextView tvLoginNumber;
    @BindView(R.id.et_login_number)
    EditText etLoginNumber;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.regitster_tv)
    TextView regitsterTv;

    @Override
    protected void initListener() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        regitsterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegitsterActivity.class));
            }
        });
    }

    private void Login() {
        //校验
        String phone = etLoginNumber.getText().toString().trim();
        String pwd = etLoginPwd.getText().toString().trim();
        if(TextUtils.isEmpty(phone)) {
            showToast("账号不能为空！！！");
            return;
        }
        if(TextUtils.isEmpty(pwd)) {
            showToast("密码不能为空！！！");
            return;
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("phone",phone);
        map.put("password",pwd);
        //去服务器登录
        LoadNet.getDataPost(AppNetConfig.LOGIN, map, new LoadNetHttp() {
            @Override
            public void success(String context) {
                //Log.e("TAG", "=="+context);
                JSONObject jsonObject = JSON.parseObject(context);
                Boolean success = jsonObject.getBoolean("success");
                if(success) {
                    //解析数据
                    UserInfo userInfo = JSON.parseObject(context, UserInfo.class);
                    //保存数据到sp
                    saveUser(userInfo);
                    //跳转
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else {
                    showToast("账号不存在或密码不正确");
                }
            }

            @Override
            public void failure(String error) {
                Log.e("TAG", "=="+error);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {

        ivTitleBack.setVisibility(View.GONE);
        ivTitleSetting.setVisibility(View.GONE);
        tvTitle.setText("登录");
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_login;
    }
}
