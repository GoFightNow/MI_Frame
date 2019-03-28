package wangsen.sxcz.viewpager_test.sharebutton.popup_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import wangsen.sxcz.viewpager_test.R;
import wangsen.sxcz.viewpager_test.sharebutton.dialog_view.SaveImageUtil;

/**
 * Created by 王森 on 2018/12/11.
 */
public class MyPopupWindow extends PopupWindow {

    private Context context;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM_dd_HH_mm");

    private TextView baocun;
    private TextView quxiao;

    private Bitmap qrcode_logo;

    public MyPopupWindow(final Context context, final Bitmap qrcode_logo) {
        super(context);
        //将传入的context赋值给本类的context
        this.context = context;
        //将二维码传入
        this.qrcode_logo = qrcode_logo;
        //设置PopupWindow的高
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow的宽
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置外部点击
        setOutsideTouchable(true);
        //设置背景
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置PopupWindow是否获得焦点
        setFocusable(true);
        //设置PopupWindow的中心视图
        View view = View.inflate(context, R.layout.popup_layout, null);
//        view = LayoutInflater.from(context).inflate(R.layout.popup,null,false);
        setContentView(view);
        baocun = view.findViewById(R.id.baocun);
        quxiao = view.findViewById(R.id.quxiao);

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageName = "QR_" + simpleDateFormat.format(System.currentTimeMillis());
                Log.i("imageName", imageName);
                boolean b = SaveImageUtil.saveImageToGallery(context, qrcode_logo, imageName + ".jpeg");
                dismiss();
                if (b) {
                    Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //设置Window的背景透明度
    public void setWindowBackgroundAlpha(float alpha) {
        if ((AppCompatActivity) context == null) {
            return;
        }
        Window window = ((AppCompatActivity) context).getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = alpha;
        //不加这一行，就没用，而且会出现拖影
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setAttributes(layoutParams);
    }

    //预测Popup的高和宽的方法
    public static int makeDropDownMeasureSpect(int measureSpec) {
        int mode;
        if (measureSpec == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mode = View.MeasureSpec.UNSPECIFIED;
        } else {
            mode = View.MeasureSpec.EXACTLY;
        }
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(measureSpec), mode);
    }

}
