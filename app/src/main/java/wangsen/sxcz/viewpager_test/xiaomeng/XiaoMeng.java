package wangsen.sxcz.viewpager_test.xiaomeng;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import wangsen.sxcz.viewpager_test.MainActivity;
import wangsen.sxcz.viewpager_test.R;
import wangsen.sxcz.viewpager_test.welcome.LinearGradientFontSpan;
import wangsen.sxcz.viewpager_test.welcome.Welcome;

/**
 * Created by 王森 on 2018/12/3.
 */
public class XiaoMeng extends Fragment {

    private TextView ok;
    private TextView zhongyang;
    private TextView keai;
    private int i = 1;
    private String TEXT_STRING = "o点我点我o";
    private String TEXT_KEY = "o点我点我o";

    private Context context;

    private long lastClickTime = 0L;
    private static final int FAST_CLICK_DELAY_TIME = 2100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xiaomeng,container,false);
        context = getContext();
        ok = (TextView) view.findViewById(R.id.ok);
        keai = (TextView) view.findViewById(R.id.keai);
        keai.setText(getRadiusGradientSpan(keai.getText().toString(),0xFF00FFE1,0xFF37FF00));

//          0xFF00FFE1,0xFF37FF00
//        keai.getPaint().setStrikeThruText(true);
        zhongyang = (TextView) view.findViewById(R.id.zhongyang);
        zhongyang.setText("ヾ(≧▽≦*)o");
        zhongyang.setTextColor(Color.BLUE);
        final SpannableStringBuilder builder = new SpannableStringBuilder(TEXT_STRING);
        final int startIndex = TEXT_STRING.indexOf(TEXT_KEY);
        final int endIndex = startIndex + TEXT_KEY.length();


        class MyClickableSpan extends ClickableSpan {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }

            @Override
            public void onClick(@NonNull View widget) {

                if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME) {
                    return;
                }
                lastClickTime = System.currentTimeMillis();
                if (i == 1) {
                    builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF14A69C")), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Toast.makeText(context, "点一下很可爱", Toast.LENGTH_SHORT).show();
                    zhongyang.setText("(*/ω＼*)");
                    zhongyang.setTextColor(Color.parseColor("#FF14A69C"));
                } else if (i == 2) {
                    builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FFC41983")), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Toast.makeText(context, "点两下很舒服", Toast.LENGTH_SHORT).show();
                    zhongyang.setText("o(*￣▽￣*)ブ");
                    zhongyang.setTextColor(Color.parseColor("#FFC41983"));
                } else if (i == 3) {
                    builder.setSpan(new ForegroundColorSpan(Color.parseColor("#ed8b14")), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Toast.makeText(context, "再点我就生气了", Toast.LENGTH_SHORT).show();
                    zhongyang.setText("(。>︿<)_θ");
                    zhongyang.setTextColor(Color.parseColor("#ed8b14"));
                } else if (i >= 4 && i < 6) {
                    builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFF0900")), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Toast.makeText(context, "啊啊啊，不要再点了啊!!", Toast.LENGTH_SHORT).show();
                    zhongyang.setText("(╯‵□′)╯︵┻━┻");
                    zhongyang.setTextColor(Color.parseColor("#FFFF0900"));
                } else if (i == 6) {
                    builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF14701C")), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    Toast.makeText(context, "你好无聊哦，不陪你玩了", Toast.LENGTH_SHORT).show();
                    zhongyang.setText("╮（╯＿╰）╭");
                    zhongyang.setTextColor(Color.parseColor("#FF14701C"));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "我要走了，拜拜咯", Toast.LENGTH_SHORT).show();
                            zhongyang.setText("ε=ε=┏(゜ロ゜;)┛");
                        }
                    }, 2700);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getFragmentManager().beginTransaction().replace(R.id.fragment,new Welcome()).commit();
                        }
                    }, 4200);

                }

                ok.setHighlightColor(Color.parseColor("#00FFFFFF"));
                ok.setText(builder);
                i++;

            }
        }


        //设置文本点击事件
        ok.setMovementMethod(LinkMovementMethod.getInstance());
        builder.setSpan(new MyClickableSpan(), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ok.setText(builder);


        return view;
    }


    public static SpannableStringBuilder getRadiusGradientSpan(String string, int startColor, int endColor){
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        LinearGradientFontSpan span = new LinearGradientFontSpan(startColor,endColor);
//        spannableStringBuilder.setSpan(new StrikethroughSpan(),0,string.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(span,0,spannableStringBuilder.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableStringBuilder;
    }


}
