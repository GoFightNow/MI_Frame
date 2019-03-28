package wangsen.sxcz.viewpager_test.test_fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import wangsen.sxcz.viewpager_test.R;

/**
 * Created by 王森 on 2018/11/26.
 */
public class ZiDing extends LinearLayout {
    private Context context;
    private Integer num;
    private ImageView imageView;

    public ZiDing(Context context) {
        super(context, null);
    }

    public ZiDing(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setGravity(Gravity.CENTER);
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void drawPage(int page_num) {
        removeAllViews();
        for (int i = 0; i < num; i++) {
            imageView = new ImageView(context);
            if (page_num == i) {
                imageView.setBackgroundResource(R.drawable.bigyuan);
            } else {
                imageView.setBackgroundResource(R.drawable.smallyuan);
            }
//            imageView.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    drawPage(page_num);
//                }
//            });
            addView(imageView);
            LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
            layoutParams.rightMargin = 10;
            layoutParams.leftMargin = 10;
            layoutParams.bottomMargin = 15;
            imageView.setLayoutParams(layoutParams);

        }
    }
}
