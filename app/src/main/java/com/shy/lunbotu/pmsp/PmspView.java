package com.shy.lunbotu.pmsp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shy.lunbotu.utils.Utils;


//
//

/**
 * 屏幕适配
 * 设计稿以 1280 *720 为模板
 * 如果实际屏幕尺寸为 1920*1080 就需要按比例缩放
 */
/**
 * 当我们要设置控件的宽高的时候，永远以这两个值为准，
 * 例如现在是720*1280 ， 我们希望控件是屏幕的一般 就需要给控件设置宽 360*640 但单位必须是像素px
 * 无论实际屏幕是多大 显示的控件都是屏幕的一半
 */

/**
 * 以一个特定宽高尺寸的设备为参考，在view的加载过程中，根据当前的设备的实际像素换算出目标像素，在做用于目标像素上
 */

public class PmspView extends RelativeLayout {

    private boolean flag;

    public PmspView(Context context) {
        super(context);
    }

    public PmspView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PmspView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!flag) { //onMeasure会多次调用，避免二次计算，需要判断
            float scaleX = Utils.getInstance(getContext()).getHorizontalScale();
            float scaleY = Utils.getInstance(getContext()).getVerticalScale();
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                params.width = (int) (params.width * scaleX);
                params.height = (int) (params.height * scaleY);
                params.leftMargin = (int) (params.leftMargin * scaleX);
                params.rightMargin = (int) (params.rightMargin * scaleX);
                params.topMargin = (int) (params.topMargin * scaleY);
                params.bottomMargin = (int) (params.bottomMargin * scaleY);
            }
            flag = true;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
