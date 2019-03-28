package wangsen.sxcz.viewpager_test.caidan;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import wangsen.sxcz.viewpager_test.R;

/**
 * Created by 王森 on 2018/11/27.
 */

/**
 * 菜单条目的适配器
 */
public class List_Adapter extends BaseAdapter {
    private Context context;
    private HashMap<String, Object> list;
    private String[] actionTexts;
    private int[] actionImages;

    public List_Adapter(Context context, String[] actionTexts, int[] actionImages) {
        this.context = context;
        this.actionTexts = actionTexts;
        this.actionImages = actionImages;
    }

    public List_Adapter(Context context, HashMap<String, Object> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return actionTexts.length;
    }

    @Override
    public Object getItem(int i) {
        return actionTexts[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = View.inflate(context, R.layout.listview_item, null);
            //设置一种字体的样式
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/FZJhei.otf");
            viewHolder = new ViewHolder(view);
            viewHolder.textview1.setText(actionTexts[i]);
            //将字体样式设置给文字
            viewHolder.textview1.setTypeface(typeface);
            viewHolder.textview1.getPaint().setFakeBoldText(true);
            viewHolder.item_image.setImageResource(actionImages[i]);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView textview1;
        public ImageView item_image;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.textview1 = (TextView) rootView.findViewById(R.id.textview1);
            this.item_image = (ImageView) rootView.findViewById(R.id.item_image);
        }

    }

}
