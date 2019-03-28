package wangsen.sxcz.viewpager_test.sharebutton.dialog_view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 王森 on 2018/12/8.
 */
public class SaveImageUtil {
    @SuppressLint("LongLogTag")
    public static boolean saveImageToGallery(Context context, Bitmap bitmap, String imageName) {
        Log.i("File.separator", File.separator + "");
        Log.i("ExternalStorageDirectory", Environment.getExternalStorageDirectory().getAbsolutePath());
        //文件路径
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM/Camera";
        //创建文件
        File imageDir = new File(storePath);
        //如果文件路径不存在，则用mkdir()方法创建
        if (!imageDir.exists()) {
            imageDir.mkdir();
        }
        File imageFile = new File(imageDir, imageName);
        try {
//            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(imageFile));
            FileOutputStream fos = new FileOutputStream(imageFile);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

            //把文件插入到系统图库
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),image.getAbsolutePath(),bitmapName,null);

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(imageFile);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
