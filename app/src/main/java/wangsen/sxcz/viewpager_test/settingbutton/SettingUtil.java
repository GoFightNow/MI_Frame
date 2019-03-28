package wangsen.sxcz.viewpager_test.settingbutton;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 王森 on 2018/12/2.
 */
public class SettingUtil {
    public static void saveNETinfo(Context context, String url, String port){
        SharedPreferences sharedPreferences = context.getSharedPreferences("net",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("url",url);
        editor.putString("port",port);
        editor.commit();
    }

    public static NetInfo getNETinfo(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("net",Context.MODE_PRIVATE);
        String url = sharedPreferences.getString("url","");
        String port = sharedPreferences.getString("port","");
        return new NetInfo(url,port);
    }
}
