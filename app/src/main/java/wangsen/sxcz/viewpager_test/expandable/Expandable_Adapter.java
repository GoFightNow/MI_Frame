package wangsen.sxcz.viewpager_test.expandable;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wangsen.sxcz.viewpager_test.R;

/**
 * Created by 王森 on 2018/11/29.
 */

/**
 * Expandable的适配器
 */
public class Expandable_Adapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> group_list;
    private List<List<ChildrenBean>> children_list;

    public Expandable_Adapter(Context context, List<String> group_list, List<List<ChildrenBean>> children_list) {
        this.context = context;
        this.group_list = group_list;
        this.children_list = children_list;
    }

    @Override
    public int getGroupCount() {
        return group_list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return children_list.get(i).size();
    }

    @Override
    public String getGroup(int i) {
        return group_list.get(i);
    }

    @Override
    public ChildrenBean getChild(int i, int i1) {
        return children_list.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (true) {
            view = View.inflate(context, R.layout.group_item, null);
            viewHolder = new ViewHolder(view);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/FZZhei.ttf");
            viewHolder.group_name.setTypeface(typeface);
            viewHolder.group_name.setText(group_list.get(i) + "");
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        if (b){
            //条目展开，设置向下箭头
            viewHolder.group_image.setImageResource(R.drawable.ex_open);
        }else{
            //条目闭合，设置向上箭头
            viewHolder.group_image.setImageResource(R.drawable.ex_close);
        }


        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (true){
            view = View.inflate(context, R.layout.children_item, null);
            viewHolder = new ViewHolder(view);
            viewHolder.children_id.setText("我是"+group_list.get(i)+"的数据，"+"我的序号是"+children_list.get(i).get(i1).getId() + "  我随机到的值是  "+children_list.get(i).get(i1).getName());
//            viewHolder.children_name.setText(children_list.get(i).get(i1).getName() + "");
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }

    @Override
    //设置子条目是否可呗选中or点击,默认为false
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView children_id;
        public TextView children_name;
        public TextView group_name;
        public ImageView group_image;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.children_id = (TextView) rootView.findViewById(R.id.children_id);
            this.children_name = (TextView) rootView.findViewById(R.id.children_name);
            this.group_name = (TextView) rootView.findViewById(R.id.group_name);
            this.group_image = (ImageView) rootView.findViewById(R.id.group_image);
        }

    }
}
