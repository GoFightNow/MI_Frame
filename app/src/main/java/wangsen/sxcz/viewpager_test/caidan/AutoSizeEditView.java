package wangsen.sxcz.viewpager_test.caidan;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by 王森 on 2018/11/30.
 */

/**
 * 根据文字长度，自动更改文字大小
 */
public class AutoSizeEditView extends android.support.v7.widget.AppCompatEditText {
    private static final float DEFAULT_MIN_TEXT_SIZE = 8.0f;
    private static final float DEFAULT_MAX_TEXT_SIZE = 16.0f;

    private Paint paint;
    private float minTextsize = DEFAULT_MIN_TEXT_SIZE;
    private float maxTextsize = DEFAULT_MAX_TEXT_SIZE;

    public AutoSizeEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initialise() {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        if (this.paint == null) {
            this.paint = new Paint();
            this.paint.set(this.getPaint());
        }
        this.maxTextsize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.maxTextsize, displayMetrics);
        if (DEFAULT_MIN_TEXT_SIZE >= maxTextsize) {
            this.maxTextsize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.maxTextsize, displayMetrics);
        }
        this.maxTextsize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.maxTextsize, displayMetrics);
        this.minTextsize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.minTextsize, displayMetrics);

    }

    private void fitText(String text, int textWidth) {

        if (textWidth > 0) {
            // 单行可见文字宽度
            int availableWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();
            float trySize = maxTextsize;
            // 先用最大字体写字
            paint.setTextSize(trySize);
            // 如果最大字体>最小字体 && 最大字体画出字的宽度>单行可见文字宽度
            while ((trySize > minTextsize) && (paint.measureText(text) > availableWidth)) {
                // 最大字体小一号
                trySize -= 1;
                // 保证大于最小字体
                if (trySize <= minTextsize) {
                    trySize = minTextsize;
                    break;
                }
                // 再次用新字体写字
                paint.setTextSize(trySize);
            }
            this.setTextSize(trySize);
        }
    }


    /**
     * 重写setText
     * 每次setText的时候
     *
     * @param text
     * @param type
     */
    @Override
    public void setText(CharSequence text, BufferType type) {
        this.initialise();
        String textString = text.toString();
        float trySize = maxTextsize;
        if (this.paint == null) {
            this.paint = new Paint();
            this.paint.set(this.getPaint());
        }
        this.paint.setTextSize(trySize);
        // 计算设置内容前 内容占据的宽度
        int textWidth = (int) this.paint.measureText(textString);
        // 拿到宽度和内容，进行调整
        this.fitText(textString, textWidth);
        super.setText(text, type);
    }



    @Override
    protected void onTextChanged(CharSequence text, int start, int before, int after) {
        super.onTextChanged(text, start, before, after);
        this.fitText(text.toString(), this.getWidth());
    }


    /**
     * This is called during layout when the size of this view has changed. If
     * you were just added to the view hierarchy, you're called with the old
     * values of 0.
     *
     * @param w    Current width of this view.
     * @param h    Current height of this view.
     * @param oldw Old width of this view.
     * @param oldh Old height of this view.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // 如果当前view的宽度 != 原来view的宽度
        if (w != oldw) this.fitText(this.getText().toString(), w);
    }
}

