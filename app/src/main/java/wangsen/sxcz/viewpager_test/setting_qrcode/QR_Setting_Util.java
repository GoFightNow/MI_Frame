package wangsen.sxcz.viewpager_test.setting_qrcode;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 王森 on 2018/12/3.
 */
public class QR_Setting_Util {
    public static void saveQRcode_content(Context context, String content){
        SharedPreferences sharedPreferences = context.getSharedPreferences("qrcont",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("content",content);
        editor.commit();
    }

    public static String getQRcode_content(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("qrcont",Context.MODE_PRIVATE);
        return sharedPreferences.getString("content","这个是一空空如也的二维码");
    }
}
