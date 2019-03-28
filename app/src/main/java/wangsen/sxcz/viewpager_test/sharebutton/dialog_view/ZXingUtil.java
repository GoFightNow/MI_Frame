package wangsen.sxcz.viewpager_test.sharebutton.dialog_view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

import static android.graphics.Paint.*;

/**
 * Created by 王森 on 2018/12/1.
 */
public class ZXingUtil {

    /**
     * @param content
     * @param width
     * @param height
     * @return
     */
    public static Bitmap createQRCodeBitmap(String content, int width, int height) {
        //content是内容
        if (content == null || content.equals("") || content.length() < 1) {
            return null;
        }
        //定义二维码内容参数
        Hashtable hints = new Hashtable();
        //设置字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //设置容错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置边框距
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            //通过encode方法将内容写入矩阵对象
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            //定义一个二维码像素点的数组，向每个像素点中填充颜色
            int pixs[] = new int[width * height];
            //往每一个像素点中填充颜色(像素有数据用黑色填充，没有用彩色填充，不过一般用白色)
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (bitMatrix.get(j, i)) {
                        pixs[i * width + j] = 0xff000000;
                    } else {
                        pixs[i * width + j] = 0xffffffff;
                    }
                }
            }
            //创建一个指定高度和宽度的空白Bitmap对象
            Bitmap QRcode = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            //将每个像素的颜色填充到Bitmap对象
            QRcode.setPixels(pixs, 0, width, 0, 0, width, height);
            return QRcode;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param QRcode
     * @param logo
     * @return
     */
    public static Bitmap createQRCodeBitmapWithLogo(Bitmap QRcode,Bitmap logo){
        if (QRcode == null){
            return null;
        }
        if (logo == null){
            return QRcode;
        }
        //获取二维码图片的宽高
        int QRcode_width = QRcode.getWidth();
        Log.i("TEST_QRcode_width",QRcode_width+"");
        int QRcode_height = QRcode.getHeight();
        Log.i("TEST_QRcode_height",QRcode_height+"");
        //获取图片的宽高
        int Logo_width = logo.getWidth();
        Log.i("TEST_Logo_width",Logo_width+"");
        int Logo_height = logo.getHeight();
        Log.i("TEST_Logo_height",Logo_height+"");

        //设置logo的大小为二维码大小的1/5
        float scale_logo = QRcode_width*1.0f/5/Logo_width;
        Bitmap QRcodeWithLogo = Bitmap.createBitmap(QRcode_width,QRcode_height,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(QRcodeWithLogo);
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        canvas.drawBitmap(QRcode,0,0,paint);
        //scale()方法前两个参数是所放的倍数，这里大概是0.22左右,后两个参数是所放的基准点，这里是图的中心
        canvas.scale(scale_logo,scale_logo,QRcode_width/2,QRcode_height/2);
        canvas.drawBitmap(logo,(QRcode_width-Logo_width)/2,(QRcode_height-Logo_height)/2,paint);
        canvas.save();
        canvas.restore();

        return QRcodeWithLogo;
    }
}
