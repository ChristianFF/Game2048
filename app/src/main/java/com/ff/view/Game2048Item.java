package com.ff.view;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.ff.R;

/**
 * 2048的每个字块
 * Created by FF on 2015/11/5.
 */
public class Game2048Item extends View {
    /**
     * 该View上的数字
     */
    private int number;
    private String numberString;
    private Paint paint;
    /**
     * 绘制文字的区域
     */
    private Rect textBound;

    private static RectF itemBound;

    public Game2048Item(Context context) {
        this(context, null);
    }

    public Game2048Item(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Game2048Item(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    public void setNumber(int number) {
        this.number = number;
        numberString = this.number + "";
        paint.setTextSize(30.0f);
        textBound = new Rect();
        paint.getTextBounds(numberString, 0, numberString.length(), textBound);
        invalidate();
    }


    public int getNumber() {
        return number;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int backgroundColor;
        switch (number) {
            case 0:
                backgroundColor = android.R.color.white;
                break;
            case 2:
                backgroundColor = R.color.light_green_500;
                break;
            case 4:
                backgroundColor = R.color.green_500;
                break;
            case 8:
                backgroundColor = R.color.lime_500;
                break;
            case 16:
                backgroundColor = R.color.amber_500;
                break;
            case 32:
                backgroundColor = R.color.orange_500;
                break;
            case 64:
                backgroundColor = R.color.cyan_500;
                break;
            case 128:
                backgroundColor = R.color.blue_500;
                break;
            case 256:
                backgroundColor = R.color.indigo_500;
                break;
            case 512:
                backgroundColor = R.color.deep_purple_500;
                break;
            case 1024:
                backgroundColor = R.color.brown_500;
                break;
            case 2048:
                backgroundColor = R.color.pink_500;
                break;
            case 4096:
                backgroundColor = R.color.red_500;
                break;
            default:
                backgroundColor = android.R.color.white;
                break;
        }

        paint.setColor(getResources().getColor(backgroundColor));
        itemBound = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(itemBound, 8, 8, paint);

        if (number != 0) {
            drawText(canvas);
        }
    }

    /**
     * 绘制文字
     */
    private void drawText(Canvas canvas) {
        paint.setColor(Color.WHITE);
        float x = (getWidth() - textBound.width()) / 2;
        float y = getHeight() / 2 + textBound.height() / 2;
        canvas.drawText(numberString, x, y, paint);
    }

    /**
     * 创建方块时的动画
     */
    public void showEnterAnimation() {
        PropertyValuesHolder animA = PropertyValuesHolder.ofFloat("alpha", 0.5f, 1f);
        PropertyValuesHolder animX = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
        PropertyValuesHolder animY = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(this, animA, animX, animY).setDuration(256).start();
    }
}
