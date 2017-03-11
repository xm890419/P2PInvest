package com.atguigu.p2pinvest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.p2pinvest.base.BaseFragment;
import com.atguigu.p2pinvest.fragment.HomeFragment;
import com.atguigu.p2pinvest.fragment.InvestFragment;
import com.atguigu.p2pinvest.fragment.MoreFragment;
import com.atguigu.p2pinvest.fragment.ProperyFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private Fragment tempFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initListener();
        //法一
        switchFragment (0);
//        rgMain.check(R.id.rb_home);
    }
    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int postion = 0;
                switch (checkedId){
                    case R.id.rb_home:
                        postion = 0;
                        break;
                    case R.id.rb_invest:
                        postion = 1;
                        break;
                    case R.id.rb_propert:
                        postion = 2;
                        break;
                    case R.id.rb_more:
                        postion = 3;
                        break;
                }
                switchFragment(postion);
            }
        });
    }
    private void switchFragment(int postion) {
        Fragment currentFragment = fragments.get(postion);
        if(tempFragment != currentFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(!currentFragment.isAdded()) {
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_main,currentFragment);
            }else {
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            }
            ft.commit();
            tempFragment = currentFragment;
        }
    }
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new InvestFragment());
        fragments.add(new ProperyFragment());
        fragments.add(new MoreFragment());
    }
    private boolean isDouble = false;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(isDouble) {
                finish();
            }else {
                Toast.makeText(this, "再点击一次，退出应用", Toast.LENGTH_SHORT).show();
                isDouble = true;
                //超过2s改isDouble
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isDouble = false;
                    }
                },2000);
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
