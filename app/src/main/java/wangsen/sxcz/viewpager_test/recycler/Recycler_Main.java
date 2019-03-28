package wangsen.sxcz.viewpager_test.recycler;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import wangsen.sxcz.viewpager_test.R;

/*
 * Created by 王森 on WangSen.
 */public class Recycler_Main extends Fragment implements View.OnClickListener {
    private Button btn_add;
    private Button btn_delete;
    private Button btn_list;
    private Button btn_grid;
    private Button btn_flow;
    private RecyclerView recycler1;

    private Context context;
    private List<String> list;
    private RecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_main, container, false);
        initView(view);
        context = getContext();
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("RecyclerView_" + i);
        }
        //设置Recycler的适配器
        adapter = new RecyclerAdapter(context,list);
        recycler1.setAdapter(adapter);
        recycler1.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        return view;


    }

    private void initView(View view) {
        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_delete = (Button) view.findViewById(R.id.btn_delete);
        btn_list = (Button) view.findViewById(R.id.btn_list);
        btn_grid = (Button) view.findViewById(R.id.btn_grid);
        btn_flow = (Button) view.findViewById(R.id.btn_flow);
        recycler1 = (RecyclerView) view.findViewById(R.id.recycler1);

        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_flow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:

                break;
            case R.id.btn_delete:

                break;
            case R.id.btn_list:

                break;
            case R.id.btn_grid:

                break;
            case R.id.btn_flow:

                break;
        }
    }
}
