package com.shy.lunbotu.materialDesign;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shy.lunbotu.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MaterialDesignActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;
    private TextView textView;
    private CircleImageView circleImageView;

    private int heigth;
    private int mCurrentPosition; //下一个item

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        recyclerView = findViewById(R.id.rv);
        relativeLayout = findViewById(R.id.rl);
        textView = findViewById(R.id.tv_nickname);
        circleImageView = findViewById(R.id.iv_avater);


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RvAdapter rvAdapter = new RvAdapter();
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setHasFixedSize(true);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获取悬浮条的高度
                heigth = relativeLayout.getHeight();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //对悬浮条的位置进行调整
                //找到下个itemVIew
                // 0 + 1 = 1 返回第2个item
                View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);   //返回整个列表中对应为的itemview
                if (view != null) {
                    if (view.getTop() <= heigth) {
                        //下一个view距离顶部小于悬浮条的高度
                        /**
                         * 当下一个头部移动到现有的头部时，就把布局中的头部对着移动的距离向上移动，（显示出把头部顶上去的效果），
                         */
                        Log.d("///////", mCurrentPosition + "");
                        relativeLayout.setY(-(heigth - view.getTop()));
                        //setY设置视图的可视位置 相当于平移画布的操作
                    } else {
                        /**
                         * 回到原有的位置
                         */
                        relativeLayout.setY(0);
                    }
                }
                /**
                 * .findFirstVisibleItemPosition() 当列表滑动时，当顶部每滑过一个item边界时，都会返回一个数值 返回第一个可见的位置的item索引
                 *  如果不是就设置第一个
                 */
                if (linearLayoutManager.findFirstVisibleItemPosition() != mCurrentPosition) {
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();

                    Log.d("////////位置" , mCurrentPosition + "" );
                    updataSuspensionBar();
                }

            }
        });
        updataSuspensionBar();
    }

    private void updataSuspensionBar() {
        Picasso.get().load(getAvatarResId(mCurrentPosition)).centerInside().fit().into(circleImageView);
        textView.setText("NetEase " + mCurrentPosition);
    }

    private int getAvatarResId(int p) {
        switch (p % 4) {
            case 0:

                return R.mipmap.bg;
            case 1:
                return R.mipmap.z;
            case 2:
                return R.mipmap.h;
            case 3:
                return R.mipmap.bg;
        }
        return 0;
    }
}
