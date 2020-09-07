package com.shy.lunbotu.pmsp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shy.lunbotu.R;

public class BfbPercentLayout extends RelativeLayout {
    public BfbPercentLayout(Context context) {
        super(context);
    }

    public BfbPercentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BfbPercentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BfbPercentLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);

    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取父容器的尺寸
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heigth = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            //如果是百分比布局
            if (checkLayoutParams(layoutParams)){
                float widthPercent = layoutParams.widthPercent;
                float heightPercent = layoutParams.heightPercent;
                float marginLeftPercent = layoutParams.marginLeftPercent;
                float marginRightPercent = layoutParams.marginRightPercent;
                float marginTopPercent = layoutParams.marginTopPercent;
                float marginBottomPercent = layoutParams.marginBottomPercent;

                if (widthPercent > 0 ){
                    layoutParams.width = (int) (width * widthPercent);
                }
                if (heightPercent > 0){
                    layoutParams.height = (int) (heigth * heightPercent);
                }
                if (marginLeftPercent > 0){
                    layoutParams.leftMargin = (int) (width * marginLeftPercent);
                }
                if (marginRightPercent > 0){
                    layoutParams.rightMargin = (int) (width*marginRightPercent);
                }
                if (marginTopPercent > 0){
                    layoutParams.topMargin = (int) (heigth * marginTopPercent);
                }
                if (marginBottomPercent > 0 ){
                    layoutParams.bottomMargin = (int) (heigth * marginBottomPercent);
                }

            }

        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams{

        private float widthPercent;
        private float heightPercent;
        private float marginLeftPercent;
        private float marginRightPercent;
        private float marginTopPercent;
        private float marginBottomPercent;


        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            //解析属性
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.BfbPercentLayout);
            widthPercent = a.getFloat(R.styleable.BfbPercentLayout_widthPercent,0);
            heightPercent = a.getFloat(R.styleable.BfbPercentLayout_heightPercent,0);
            marginLeftPercent = a.getFloat(R.styleable.BfbPercentLayout_marginLeftPercent,0);
            marginRightPercent = a.getFloat(R.styleable.BfbPercentLayout_marginRightPercent,0);
            marginTopPercent = a.getFloat(R.styleable.BfbPercentLayout_marginTopPercent,0);
            marginBottomPercent = a.getFloat(R.styleable.BfbPercentLayout_marginBottomPercent,0);
            a.recycle();
        }
    }
}
