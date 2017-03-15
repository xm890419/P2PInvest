package com.atguigu.p2pinvest.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by shkstart on 2016/12/6 0006.
 * 提供单一背景和选择背景的工具类
 */
public class DrawUtils {

    public static Drawable getDrawable(int rgb,float radius){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(rgb);  //填充颜色
        gradientDrawable.setGradientType(GradientDrawable.RECTANGLE); //shape矩形
        gradientDrawable.setCornerRadius(radius);  //四周圆角半径
        gradientDrawable.setStroke(UiUtils.dp2px(1), rgb); //边框厚度与颜色

        return gradientDrawable;
    }

    public static StateListDrawable getSelector(Drawable normalDrawable, Drawable pressDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        //给当前的颜色选择器添加选中图片指向状态，未选中图片指向状态
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, pressDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normalDrawable);
        //设置默认状态
        stateListDrawable.addState(new int[]{}, normalDrawable);

        return stateListDrawable;
    }
}
