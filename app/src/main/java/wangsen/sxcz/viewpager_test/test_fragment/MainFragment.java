package wangsen.sxcz.viewpager_test.test_fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wangsen.sxcz.viewpager_test.R;

/**
 * Created by 王森 on 2018/11/27.
 */
public class MainFragment extends Fragment {
    private ViewPager viewpager1;
    private ZiDing ziding;

    private List<Fragment> fragment_list;
    private Context context;
    private ViewPager_Adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        //向List<Fragment>里面放Fragment
        fragment_list = new ArrayList<>();
        fragment_list.add(new Fragment01());
        fragment_list.add(new Fragment02());
        fragment_list.add(new Fragment03());
        //初始化Fragment滑动的适配器
        adapter = new ViewPager_Adapter(getFragmentManager(), fragment_list);
    }

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        if (viewpager1!=null){
            viewpager1 = null;
        }
        initView(view);
        viewpager1.setAdapter(adapter);
        ziding.setNum(fragment_list.size());
        ziding.drawPage(0);

        viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ziding.drawPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adapter.notifyDataSetChanged();

        return view;
    }

    private void initView(View view) {
        viewpager1 = (ViewPager) view.findViewById(R.id.viewpager1);
        ziding = (ZiDing) view.findViewById(R.id.ziding);

    }

}
