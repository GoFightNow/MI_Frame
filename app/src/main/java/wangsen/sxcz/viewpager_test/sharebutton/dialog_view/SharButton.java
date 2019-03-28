package wangsen.sxcz.viewpager_test.sharebutton.dialog_view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

import wangsen.sxcz.viewpager_test.MainActivity;
import wangsen.sxcz.viewpager_test.R;
import wangsen.sxcz.viewpager_test.setting_qrcode.QR_Setting_Util;
import wangsen.sxcz.viewpager_test.sharebutton.popup_view.MyPopupWindow;

/**
 * Created by 王森 on 2018/12/1.
 */

/**
 * 分享按钮，这是自定义一个并对话框，并在需要的地方调用,这个对话框用在了toolbar中分享按钮被点击时
 */
public class SharButton extends Dialog {
    private ImageView z_image;
    private TextView z_text;

    private Bitmap qrcode;
    private Bitmap qrcode_logo;

    private Context context;

    public SharButton(Context context) {
        super(context);
        this.context = context;
    }

    public SharButton(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除对话框的标题，必须在setContentView()前
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.shardialog);
        //设置对话框的背景色
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        initView();
        //创建一个二维码
        qrcode = ZXingUtil.createQRCodeBitmap(QR_Setting_Util.getQRcode_content(context), 800, 800);
        //创建一个bitmap对象作为logo
        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.zuozhetouxiang);
        //创建一个带logo的二维码
        qrcode_logo = ZXingUtil.createQRCodeBitmapWithLogo(qrcode, logo);
        //将图片放入ImageView
        z_image.setImageBitmap(qrcode_logo);
    }

    private void initView() {
        z_image = (ImageView) findViewById(R.id.z_image);
        z_text = (TextView) findViewById(R.id.z_text);

        z_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Popup_Activity.class);
                intent.putExtra("bitmap",Bitmap2Bytes(qrcode_logo));
                context.startActivity(intent);
            }
        });

        z_image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
    }


    private byte[] Bitmap2Bytes(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        return baos.toByteArray();
    }


}
