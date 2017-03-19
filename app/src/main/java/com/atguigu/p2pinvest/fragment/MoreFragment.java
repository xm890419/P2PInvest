package com.atguigu.p2pinvest.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.activity.GestureEditActivity;
import com.atguigu.p2pinvest.activity.RegitsterActivity;
import com.atguigu.p2pinvest.base.BaseFragment;
import com.atguigu.p2pinvest.utils.AppNetConfig;
import com.atguigu.p2pinvest.utils.LoadNet;
import com.atguigu.p2pinvest.utils.LoadNetHttp;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by 熊猛 on 2017/3/10.
 */

public class MoreFragment extends BaseFragment {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.tv_more_regist)
    TextView tvMoreRegist;
    @BindView(R.id.toggle_more_secret)
    ToggleButton toggleMoreSecret;
    @BindView(R.id.tv_more_reset)
    TextView tvMoreReset;
    @BindView(R.id.tv_more_number)
    TextView tvMoreNumber;
    @BindView(R.id.rl_more_contact)
    RelativeLayout rlMoreContact;
    @BindView(R.id.tv_more_sms)
    TextView tvMoreSms;
    @BindView(R.id.tv_more_share)
    TextView tvMoreShare;
    @BindView(R.id.tv_more_about)
    TextView tvMoreAbout;
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

        tvMoreRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegitsterActivity.class));
            }
        });
        toggleMoreSecret.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {//打开手势密码
                    //存储当前的状态 用来记录是否打开手势密码
                    saveState(isChecked);
                    if (!getIsSetting()){//判断是否设置过
                        setSetting(true);//已经设置手势适别器
                        startActivity(new Intent(getActivity(),GestureEditActivity.class));
                    }
                }else {//关闭手势密码
                    //存储当前的状态 用来记录是否打开手势密码
                    saveState(isChecked);
                }
            }
        });
        tvMoreReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重置手势密码
                //设置手势密码
                startActivity(new Intent(getActivity(),GestureEditActivity.class));
            }
        });
        tvMoreNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:010-56253825");
                intent.setData(uri);
            }
        });
        tvMoreSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(getActivity(),R.layout.dialog_fankui,null);
                new AlertDialog.Builder(getActivity()).setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Map<String,String> map = new HashMap<String, String>();
                                map.put("department","");
                                map.put("content","");
                                LoadNet.getDataPost(AppNetConfig.FEEDBACK, map, new LoadNetHttp() {
                                    @Override
                                    public void success(String context) {
                                        //提交是否成功
                                    }

                                    @Override
                                    public void failure(String error) {

                                    }
                                });
                            }
                        })
                        .setNegativeButton("取消",null).show();
            }
        });
        tvMoreShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("来自尚硅谷");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://atguigu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("三生三世十里桃花");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://atguigu.com");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("三生三世十里桃花");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("尚硅谷");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://atguigu.com");

// 启动分享GUI
        oks.show(getActivity());
    }

    private void setSetting(boolean setting) {
        SharedPreferences sp = getActivity().getSharedPreferences("setting",
                Context.MODE_PRIVATE);
        sp.edit().putBoolean("isSetting",setting).commit();
    }

    private boolean getIsSetting() {
        SharedPreferences sp
                = getActivity().getSharedPreferences("setting",
                Context.MODE_PRIVATE);
        return sp.getBoolean("isSetting",false);
    }

    public void saveState(boolean isOpen){
        SharedPreferences sp = getActivity().getSharedPreferences("tog_state", Context.MODE_PRIVATE);
        sp.edit().putBoolean("isOpen",isOpen).commit();
    }

    public boolean getState(){
        SharedPreferences sp = getActivity().getSharedPreferences("tog_state", Context.MODE_PRIVATE);
        return sp.getBoolean("isOpen",false);
    }

    @Override
    public void initData(String json) {
        initTitle();
        /*//设置手势密码
        startActivity(new Intent(getActivity(), GestureEditActivity.class));*/
        setTogState();//设置选择状态
        /*//设置手势密码
        startActivity(new Intent(getActivity(), GestureEditActivity.class));*/
    }

    //设置button状态
    private void setTogState() {
        SharedPreferences sp = getActivity().getSharedPreferences("tog_state", Context.MODE_PRIVATE);
        boolean isOpen = sp.getBoolean("isOpen", false);
        toggleMoreSecret.setChecked(isOpen);
    }

    private void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        ivTitleSetting.setVisibility(View.GONE);
        tvTitle.setText("更多");
    }

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_more;
    }
}
