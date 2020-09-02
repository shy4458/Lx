package com.shy.lunbotu.myView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.shy.lunbotu.R;

//canvas相关
public class TransfromView extends View {
    private Paint paint;

    public TransfromView(Context context) {
        this(context,null);
    }

    public TransfromView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TransfromView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public TransfromView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        //1.平移操作
//        canvas.drawRect(0,0,600,600,paint);
//        //canvas平移操作的时候，实际上是将画布进行平移操作的
//        canvas.translate(50,50);
//        paint.setColor(Color.GRAY);
//        canvas.drawRect(0,0,600,600,paint);
//        canvas.drawLine(0,0,400,400,paint);


//        //2.缩放操作
//        canvas.drawRect(200,200,700,700,paint);
//        //scale两个参数的构造函数只定义了xy缩放的比例，而四个参数的构造函数是，前两个参数是xy的缩放比例，后两个参数是缩放后的原点
//        //四个参数实际上是先translate（200,200)在缩放scale（0.5f,0.5f）在反向translate（-200，-200）
////        canvas.scale(0.5f,0.5f)；
//        canvas.scale(0.5f,0.5f,200,200);
//        paint.setColor(Color.BLUE);
//        canvas.drawRect(200,200,700,700,paint);

//        //3.1 旋转操作
//        canvas.drawRect(200,200,800,800,paint);
//        //传入度数 旋转的中心点事画布的圆心
//        canvas.rotate(45);
//        paint.setColor(Color.BLUE);
//        canvas.drawRect(200,200,800,800,paint);

//        //3.2 旋转操作
//        canvas.drawRect(200,200,800,800,paint);
//        //传入度数 传入中心坐标
//        canvas.rotate(45,200,200);
//        paint.setColor(Color.BLUE);
//        canvas.drawRect(200,200,800,800,paint);

//        //4.倾斜操作
//        canvas.drawRect(0,0,800,800,paint);
////        canvas.skew(1,0); //在x方向倾斜45度
//        canvas.skew(0,1); //在y方向倾斜45度
//        paint.setColor(Color.BLUE);
//        canvas.drawRect(0,0,800,800,paint);

//        //5.切割
//        canvas.drawRect(200,200,600,600,paint);
//        paint.setColor(Color.BLUE);
//        //裁剪
////        canvas.clipRect(200,400,700,900);
//        //反向裁剪
//        canvas.clipOutRect(200,400,700,900);
//        //画布被切割后 只显示切割后剩下的画布中的内容
//        canvas.drawCircle(400,400,200,paint);

//        //矩阵
//        canvas.drawRect(0,0,700,700,paint);
//        Matrix matrix = new Matrix();
//        matrix.setScale(0.5f,0.5f);
//        canvas.setMatrix(matrix);
//        paint.setColor(Color.GRAY);
//        canvas.drawRect(0,0,700,700,paint);


        //Canvas的状态保存
        //Canvas调用平移 缩放 旋转 倾斜 切割 矩阵后 后续的操作都是基于变换后的canvas 都会受到影响，对于后续操作不方便，Canvas提供了save等方法来保存canvas的保存和恢复状态。
        //
        canvas.drawRect(0,0,400,400,paint);
        canvas.save();//保存当前canvas的状态
        canvas.translate(50,50);
        paint.setColor(Color.BLUE);
        canvas.drawRect(0,0,400,400,paint);

        canvas.save();
        canvas.translate(50,50);
        paint.setColor(Color.GRAY);
        canvas.drawRect(0,0,400,400,paint);

        canvas.restore(); //恢复保存的状态
        canvas.restore();
        canvas.drawLine(0,0,300,300,paint);

        //sava 和 restore 都可多次调用 内维护了一个 每一次调用restore方法都会恢复上一个保存的状态 其内部维护了一个栈 通过canvas.getSaveCount方法可以返回一个状态的个数
//        int save = canvas.save();
//        canvas.restoreToCount(save);
        //通过canvas。save返回一个int数， 使用restoreToCount方法可以直接恢复状态

        /**
         * int saveLayer = canvas.saveLayer(0, 0, 400, 400, paint);
         * //离屏绘制 这两行代码之间的操作先绘制到图层上 再将最后的图层绘制到canvas上
         *
         * canvas.restoreToCount(saveLayer);
         */

    }
}
