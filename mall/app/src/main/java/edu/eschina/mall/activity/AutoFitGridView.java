package edu.eschina.mall.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import androidx.viewbinding.ViewBinding;

public abstract class AutoFitGridView extends GridView implements ViewBinding {
    public AutoFitGridView(Context context) {
        super(context);
    }

    public AutoFitGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoFitGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AutoFitGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 一个activity有多个GridView时，自适应GridView的高度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}