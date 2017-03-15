package com.atguigu.p2pinvest.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.base.BaseActivity;
import com.atguigu.p2pinvest.utils.AppNetConfig;
import com.atguigu.p2pinvest.utils.LoadNet;
import com.atguigu.p2pinvest.utils.LoadNetHttp;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class RegitsterActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.et_register_number)
    EditText etRegisterNumber;
    @BindView(R.id.et_register_name)
    EditText etRegisterName;
    @BindView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @BindView(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void initListener() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //校验
                String name = etRegisterName.getText().toString().trim();
                String number = etRegisterNumber.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwdAgain = etRegisterPwdagain.getText().toString().trim();
                String phone = getUser().getData().getPhone();
                String name1 = getUser().getData().getName();
                if(TextUtils.isEmpty(name)) {
                    showToast("用户名不能为空");
                    return;
                }
                if(name.equals(name1)) {
                    showToast("用户名已经存在");
                    return;
                }
                if(TextUtils.isEmpty(number)) {
                    showToast("账号不能为空");
                    return;
                }
                if(number.equals(phone)) {
                    showToast("此号码已注册过");
                    return;
                }
                if(TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwdAgain)) {
                    showToast("密码不能为空");
                    return;
                }
                if(!pwd.equals(pwdAgain)) {
                    showToast("两次输入密码不同，请重新输入");
                    return;
                }
                //请求服务器
                Map<String,String> map = new HashMap<String, String>();
                map.put("name",name);
                map.put("password",pwd);
                map.put("phone",number);

                LoadNet.getDataPost(AppNetConfig.REGISTER, map, new LoadNetHttp() {
                    @Override
                    public void success(String context) {
                        Log.e("TAG", "=="+context);
                        JSONObject jsonObject = JSON.parseObject(context);
                        Boolean isExist = jsonObject.getBoolean("isExist");
                        if(isExist) {
                            showToast("此号码已注册过");

                        }else {
                            showToast("注册成功");
                            finish();
                        }
                    }

                    @Override
                    public void failure(String error) {

                    }
                });
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivTitleSetting.setVisibility(View.GONE);
        tvTitle.setText("注册");

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_regitster;
    }
}
