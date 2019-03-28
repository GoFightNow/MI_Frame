package wangsen.sxcz.viewpager_test.welcome;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.style.ReplacementSpan;

/**
 * Created by 王森 on 2018/11/30.
 */

/**
 * 通过重写ReplacementSpan来实现text渐变色
 */
public class LinearGradientFontSpan extends ReplacementSpan {

    private int mSize;
    private int startColor;
    private int endColor;

    public LinearGradientFontSpan(int startColor, int endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    @Override
    public int getSize(Paint paint, CharSequence charSequence, int start, int end, Paint.FontMetricsInt fontMetricsInt) {
        //charSequence指传入的文字
        mSize = (int) (paint.measureText(charSequence,start,end));

        //这段不可以去掉，字体高度没设置，可能出现draw()方法没有被调用，如果你调用SpannableStringBuilder后append一个字符串，效果也是会出来，下面这段就不需要了
        //原因详见https://stackoverflow.com/questions/20069537/replacementspans-draw-method-isnt-called
        Paint.FontMetricsInt metrics = paint.getFontMetricsInt();
        if (fontMetricsInt != null){
            fontMetricsInt.top = metrics.top;
            fontMetricsInt.bottom = metrics.bottom;
            fontMetricsInt.ascent = metrics.ascent;
            fontMetricsInt.descent = metrics.descent;
        }
        return mSize;
    }

    @Override
    public void draw(Canvas canvas, CharSequence charSequence, int start, int end, float v, int x, int y, int bottom, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(charSequence.toString(),0,charSequence.length(),rect);
        int w = rect.width();
        LinearGradient lg = new LinearGradient(0,0,0,paint.descent() - paint.ascent(),startColor,endColor,Shader.TileMode.REPEAT);
        paint.setShader(lg);
        canvas.drawText(charSequence,start,end,x,y,paint);
    }
}
