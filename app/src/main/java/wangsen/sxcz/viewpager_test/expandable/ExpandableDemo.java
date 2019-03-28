package wangsen.sxcz.viewpager_test.expandable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import wangsen.sxcz.viewpager_test.R;

/**
 * Created by 王森 on 2018/11/29.
 */
public class ExpandableDemo extends Fragment {
    private ExpandableListView expandable;

    private Context context;
    private Timer timer;

    private List<String> group_list;
    private List<List<ChildrenBean>> children_list;
    private Expandable_Adapter adapter;

    //因为子线程中不能更改UI，所以传到Handler进行操作
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //适配器刷新
            adapter.notifyDataSetChanged();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.expandable, container, false);
        context = getContext();
        //timer是计时器，例如规定时间为3000毫秒，则timer中的代码每3000毫秒执行一次
        timer = new Timer();
        //group_list是存放" 父"的内容
        group_list = new ArrayList<>();
        group_list.add("第1栏");
        group_list.add("第2栏");
        group_list.add("第3栏");
        children_list = new ArrayList<>();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                addInfo();
            }
        },0,3000);
        //初始化适配器
        adapter = new Expandable_Adapter(context, group_list, children_list);
        //初始化视图
        initView(view);
        //给expandable设置适配器
        expandable.setAdapter(adapter);
        return view;
    }

    private void initView(View view) {
        expandable = (ExpandableListView) view.findViewById(R.id.expandable);
        //点击" 父"时的响应事件
        expandable.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Toast.makeText(context,group_list.get(i)+"", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //点击" 子"时的响应事件
        expandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(context, children_list.get(i).get(i1).getId()+"", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
    //为适配器中添加数据

    /**
     * 对于父与子的解释，请看R.drawable.Explain.explain_expandable
     */
    private void addInfo() {
        //Random可以生成随机的东西，比如int,long,boolean,double,float等
        Random random = new Random();
        //生成之前清除子中的内容
        children_list.clear();

        for (int i = 0; i < 3; i++) {
            List<ChildrenBean> children = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                //random.nextInt(10) 生成0-9的随机数    +1 是为了让随机数变为1-10       + "" 是为了将int转为String
                children.add(new ChildrenBean(j + 1, random.nextInt(10) + 1+ ""));
            }
            //数据添加完成后，放到大组里面
            children_list.add(children);
        }
        handler.sendEmptyMessage(0);
    }
    //关闭视图时，进行摧毁，这里关闭了timer
    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
