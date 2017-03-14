package com.atguigu.p2pinvest.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.utils.UiUtils;

/**
 * Created by 熊猛 on 2017/3/13.
 * <p>
 * 自定义控件
 * 第一步 分析 继承 view / viewgroup / 其它控件
 * 一般来说一个功能的实现(一个控件)就继承view
 * 一般来说二个控件组合以上继承viewGroup
 * 对某些控件进行扩展的时候就继承该控件
 */

public class MyProgress extends View {
    private int measureWidth;
    private int measuredHeight;
    private Paint paint;
    //圆环的颜色
    private int roundColor;
    //圆环的宽度
    private int roundWidth = UiUtils.dp2px(5);
    private int sweepArc = 60;
    //进度的颜色
    private int sweepColor = Color.RED;

    public MyProgress(Context context) {
        super(context);
        init();  //当我们new一个控件的时候会调用该构造器
    }

    /**
     * 1.什么是自定义属性？
     * 定义可以在布局文件的标签中使用的属性。
     * 2.为什么要自定义视图属性?
     * 这样就可以通过布局的方式给视图对象指定特定的属性值, 而不用动态的设置
     * 3.理解属性值的类型(format)
     * 1. reference 引用类型值 :@id/...
     * 2. color 颜色类型值  #ff00ff
     * 3. boolean 布尔类型值 true false
     * 4. dimension 尺寸类型值 dp / px /sp
     * 5. integer 整数类型值  weight  progress max
     * 6. float 小数类型值  0.5f
     * 7. string 字符串类型值  ""
     * 8. <enum> 枚举类型值 ：水平/垂直
     */
    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(); //在布局配置该控件的时候会调用该构造器
        //自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.progress);
        //第一个参数获取attrs里面的配置属性名，第二个参数设置默认值
        roundColor = typedArray.getColor(R.styleable.progress_roundColor, Color.GRAY);
        sweepColor = typedArray.getColor(R.styleable.progress_sweepColor, Color.RED);
        sweepArc = typedArray.getInteger(R.styleable.progress_sweepArc, 0);
        typedArray.recycle();
    }

    /**
     * 进度条
     * 第一步 画圆
     * 第二步 画弧
     * 第三步 画文字
     */
    private void init() {

        //画笔
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        //抗锯齿
        paint.setAntiAlias(true);
    }

    /**
     * 注意 构造器只会调用一次 onDraw方法会调用多闪
     * 在构造器里初始化的数据 一定要是通用的（不变的）
     * 否则会在第二次onDraw的时候进行覆盖
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cx = measureWidth / 2;
        int cy = measuredHeight / 2;
        //控件的宽度 - 圆环宽度的一半
        int radius = cx - roundWidth / 2;
        //设置圆环的颜色
        paint.setColor(roundColor);
        //设置圆环的宽度
        paint.setStrokeWidth(roundWidth);
        //前两个参数是圆心坐标  第三个参数半径 第四个参数是画笔
        canvas.drawCircle(cx, cy, radius, paint);

        //画弧
        /**
         * RectF里面存放的是float的类型
         * Rect里面存放的是int值
         */
        //存放了圆环中间矩形的左上顶点和右下顶点
        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2,
                measureWidth - roundWidth / 2, measuredHeight - roundWidth / 2);
        paint.setColor(sweepColor);
        //第二个参数是起始角 第三个参数多少度
        canvas.drawArc(rectF, 0, sweepArc * 360 / 100, false, paint);

        //画文字
        String text = sweepArc + "%";

        Rect rect = new Rect();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(0);
        paint.setTextSize(UiUtils.dp2px(15));
        paint.getTextBounds(text, 0, text.length(), rect);
        float tx = measureWidth / 2 - rect.width() / 2;
        float ty = measuredHeight / 2 + rect.height() / 2;

        //第一个参数是文本 第二个到第三个参数是文字的截取的长度，第四个参数是存放测量结果的容器
        canvas.drawText(text, tx, ty, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureWidth = getMeasuredWidth();//控件的宽
        measuredHeight = getMeasuredHeight(); //控件的高
    }

    public void setProgress(int progress) {
        sweepArc = progress;
        /**
         * invalidate 是在主线程强制刷新
         * postinvalidate 是在分线程强制刷新
         */
        postInvalidate();
    }
}
