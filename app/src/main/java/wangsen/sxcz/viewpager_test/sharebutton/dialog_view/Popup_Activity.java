package wangsen.sxcz.viewpager_test.sharebutton.dialog_view;

import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import wangsen.sxcz.viewpager_test.MainActivity;
import wangsen.sxcz.viewpager_test.R;
import wangsen.sxcz.viewpager_test.sharebutton.popup_view.MyPopupWindow;

/**
 * Created by 王森 on 2018/12/11.
 */
public class Popup_Activity extends AppCompatActivity {

    private ImageView image_pop;
    private Bitmap qrcode_logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_popup);
        Intent intent = getIntent();
        byte[] bitmaps = intent.getByteArrayExtra("bitmap");
        qrcode_logo = BitmapFactory.decodeByteArray(bitmaps,0,bitmaps.length);
        initView();
        image_pop.setImageBitmap(qrcode_logo);
        initEvent();

    }

    private void initView() {
        image_pop = (ImageView) findViewById(R.id.image_pop);
    }

    private void initEvent(){
        image_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Instrumentation inst = new Instrumentation();
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        image_pop.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                //新建一个PopupWindow
                MyPopupWindow popupWindow = new MyPopupWindow(Popup_Activity.this,qrcode_logo);
                //给PopupWindow设置一个动画
                popupWindow.setAnimationStyle(R.style.popup);
                //调用MyPopupWindow中的方法来设置PopupWindow弹出时的背景
                popupWindow.setWindowBackgroundAlpha(0.6f);
                //当PopupWindow关闭时，背景变回原样
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Window window = Popup_Activity.this.getWindow();
                        WindowManager.LayoutParams layoutParams = window.getAttributes();
                        layoutParams.alpha = 1.0f;
                        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        window.setAttributes(layoutParams);
                    }
                });

                View popup_view = popupWindow.getContentView();
                Log.i("popup_width",popupWindow.getWidth()+"");
                Log.i("popup_heigth",popupWindow.getHeight()+"");
                popup_view.measure(MyPopupWindow.makeDropDownMeasureSpect(popupWindow.getWidth()), MyPopupWindow.makeDropDownMeasureSpect(popupWindow.getHeight()));

                Rect rect = new Rect();
                Window window = Popup_Activity.this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                //这句好像是获取虚拟按键的高度
                int winHeight = window.getDecorView().getHeight();
                //winHeight-rect.bottom
                popupWindow.showAtLocation(findViewById(R.id.image_pop),Gravity.BOTTOM|Gravity.CENTER,0,winHeight-rect.bottom);


                return false;
                //水平：居左；竖直：居下
                //向左偏移popupWindow的宽度
//                int Xoff = popupWindow.getContentView().getMeasuredWidth();
//                int Yoff = 0;
//                popupWindow.showAsDropDown(button1,-Xoff,Yoff,Gravity.START);

                //水平：居中；竖直：居下
                //向右偏移(view宽度 - PopupWindow宽度)/2
                //Math.abs()方法获取该值的绝对值    x = |x|
//                int Xoff = Math.abs((popupWindow.getContentView().getMeasuredWidth()-button1.getWidth())/2);
//                int Yoff = 0;
//                popupWindow.showAsDropDown(button1,Xoff,Yoff,Gravity.START);

                //水平：右对齐；竖直：居下
                //向左偏移PopupWindow宽度 - view宽度
//                int Xoff = button1.getWidth() - popupWindow.getContentView().getMeasuredWidth();
//                int Yoff = 0;
//                popupWindow.showAsDropDown(button1,Xoff,Yoff,Gravity.START);

                //水平：居中；竖直：居上
                //向右偏移(view宽度 - PopupWindow宽度)/2
                //向上偏移view高度 + PopupWindow高度
//                int Xoff = Math.abs((popupWindow.getContentView().getMeasuredWidth() - button1.getWidth()) / 2);
//                int Yoff = popupWindow.getContentView().getMeasuredHeight() + button1.getHeight();
//                popupWindow.showAsDropDown(button1,Xoff,-Yoff,Gravity.START);

                //水平：居左；竖直：居中
                //向左偏移PopupWindow的宽度
                //向上偏移(PopupWindow高度 + View高度)/2
//                int Xoff = popupWindow.getContentView().getMeasuredWidth();
//                int Yoff = (popupWindow.getContentView().getMeasuredHeight()+button1.getHeight())/2;
//                popupWindow.showAsDropDown(button1,Xoff,0,Gravity.START);


//                String imageName = "QR_" + simpleDateFormat.format(System.currentTimeMillis());
//                Log.i("imageName", imageName);
//                boolean b = SaveImageUtil.saveImageToGallery(context, qrcode_logo, imageName + ".jpeg");
//                if (b) {
//                    Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
//                }

            }
        });
    }
}
