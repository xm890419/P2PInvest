package com.atguigu.p2pinvest.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by 熊猛 on 2017/3/11.
 */

public class AppManager {

    /**
     * 统一应用程序中所有的Activity的栈管理（单例）
     * 涉及到activity的添加、删除指定、删除当前、删除所有、返回栈大小的方法
     */

    private AppManager(){};

    private static AppManager manager= new AppManager();

    public static AppManager getInstance(){
        return manager;
    }

    private static Stack<Activity> stack= new Stack<>();

    public void addActivity(Activity activity){

        if (activity != null){
            stack.add(activity);
        }
    }

    public void removeActivity(Activity activity){

        if (activity != null){
            for (int i = stack.size()-1; i >= 0; i--) {
                Activity currentActivity = stack.get(i);
                if (currentActivity.getClass()
                        .equals(activity.getClass())){
                    currentActivity.finish();
                    stack.remove(currentActivity);
                }
            }
        }
    }

    public void removeAll(){

        for (int i = stack.size()-1; i >=0; i--) {

            Activity currentActivity = stack.get(i);
            if (currentActivity != null){
                currentActivity.finish();
                stack.remove(currentActivity);
            }

        }
    }
    public void removeCurrentActivity(){
        Activity activity = stack.get(stack.size() - 1);
        activity.finish();
        stack.remove(activity);
    }
    public int getStackSize(){
        return stack.size();
    }


}
