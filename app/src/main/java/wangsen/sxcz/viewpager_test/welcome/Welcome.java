package wangsen.sxcz.viewpager_test.welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wangsen.sxcz.viewpager_test.R;

/**
 * Created by 王森 on 2018/11/28.
 */
public class Welcome extends Fragment {
    private TextView welcome_text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome, container, false);
        initView(view);
        welcome_text.setText(getRadiusGradientSpan(welcome_text.getText().toString(),0xFF668A9D,0xFFF1A8AA));
        return view;
    }

    private void initView(View view) {
        welcome_text = (TextView) view.findViewById(R.id.welcome_text);
    }

    public static SpannableStringBuilder getRadiusGradientSpan(String string,int startColor, int endColor){
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        LinearGradientFontSpan span = new LinearGradientFontSpan(startColor,endColor);
        spannableStringBuilder.setSpan(span,0,spannableStringBuilder.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableStringBuilder;
    }
}
