package com.atguigu.p2pinvest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.p2pinvest.base.BaseActivity;
import com.atguigu.p2pinvest.base.BaseFragment;
import com.atguigu.p2pinvest.fragment.HomeFragment;
import com.atguigu.p2pinvest.fragment.InvestFragment;
import com.atguigu.p2pinvest.fragment.MoreFragment;
import com.atguigu.p2pinvest.fragment.ProperyFragment;
import com.atguigu.p2pinvest.utils.AppManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private Fragment tempFragment;

    public void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int postion = 0;
                switch (checkedId) {
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

    @Override
    protected void initData() {
        //添加到APPManager
        AppManager.getInstance().addActivity(this);

        initFragment();
        switchFragment(0);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutid() {

        return R.layout.activity_main;

    }

    private void switchFragment(int postion) {
        Fragment currentFragment = fragments.get(postion);
        if (tempFragment != currentFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!currentFragment.isAdded()) {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_main, currentFragment);
            } else {
                if (tempFragment != null) {
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
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isDouble) {
                finish();
            } else {
                Toast.makeText(this, "再点击一次，退出应用", Toast.LENGTH_SHORT).show();
                isDouble = true;
                //超过2s改isDouble
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isDouble = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
