package wangsen.sxcz.viewpager_test;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import wangsen.sxcz.viewpager_test.caidan.List_Adapter;
import wangsen.sxcz.viewpager_test.caidan.PagerEnabledSlidingPaneLayout;
import wangsen.sxcz.viewpager_test.expandable.ExpandableDemo;
import wangsen.sxcz.viewpager_test.recycler.Recycler_Main;
import wangsen.sxcz.viewpager_test.setting_qrcode.QRcode_Setting;
import wangsen.sxcz.viewpager_test.settingbutton.SettingButton;
import wangsen.sxcz.viewpager_test.sharebutton.dialog_view.SharButton;
import wangsen.sxcz.viewpager_test.test_fragment.MainFragment;
import wangsen.sxcz.viewpager_test.welcome.Welcome;
import wangsen.sxcz.viewpager_test.xiaomeng.XiaoMeng;

public class MainActivity extends AppCompatActivity {

    private PagerEnabledSlidingPaneLayout slidingpane;
    private Toolbar toolbar;
    private RelativeLayout fragment;
    private ListView listview1;
    private TextView zuozhe;

    private List<HashMap<String, Object>> list;
    private List_Adapter adapter;
    private TextView list_title;
    private String[] actionTexts;
    private int[] actionImages;
    private ImageView image;
    private Dialog share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Get_Permissions(MainActivity.this);


        actionTexts = new String[]{
                getString(R.string.list_title01),
                getString(R.string.list_title02),
                getString(R.string.list_title03),
                getString(R.string.list_title04),
                getString(R.string.list_title05)
        };
        actionImages = new int[]{
                R.drawable.btn_l_windows,
                R.drawable.btn_l_star,
                R.drawable.btn_l_upload,
                R.drawable.btn_l_star,
                R.drawable.btn_l_windows
        };
//        HashMap<String,Object> item = new HashMap<>();
//        item.put("icon",actionImages);
//        item.put("name",actionTexts);
//

        adapter = new List_Adapter(this, actionTexts, actionImages);
        initView();
        listview1.setAdapter(adapter);
        share = new SharButton(MainActivity.this);


//      注意接下来的三行代码必须有先后顺序 ,通俗来说就是在放置标题之前的操作和放置之后的操作不能混
        setToolBarBefore();
        setSupportActionBar(toolbar);
        setToolBarAfter();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Welcome()).commit();
        toolbar.setVisibility(View.GONE);
    }

    private void setToolBarBefore() {
//        toolbar.setTitle("这是一个主标题");
//        toolbar.setSubtitle("这是一个副标题");
        toolbar.setTitle("");
    }

    private void setToolBarAfter() {
        //设置左边的小箭头
        toolbar.setNavigationIcon(R.drawable.change);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (slidingpane.isOpen()) {
                    slidingpane.closePane();
                } else {
                    slidingpane.openPane();
                }
            }
        });
        //设置程序图标
        toolbar.setLogo(R.drawable.touxiang);

        //菜单点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        System.exit(0);
                        break;
                    case R.id.share:
                        share.show();
                        break;
                    case R.id.setting:
                        Dialog setting = new SettingButton(MainActivity.this);
                        setting.show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (share.isShowing()){
            share.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //接下来设置item前面的图标，需要重写onPrepareOptionsPanel方法
    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    private void initView() {
        slidingpane = (PagerEnabledSlidingPaneLayout) findViewById(R.id.slidingpane);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        list_title = (TextView) findViewById(R.id.list_title);
        zuozhe = (TextView) findViewById(R.id.zuozhe);
        Typeface typeface = Typeface.createFromAsset(MainActivity.this.getAssets(), "fonts/akbar.ttf");
        zuozhe.setTypeface(typeface);
        image = (ImageView) findViewById(R.id.image);
        fragment = (RelativeLayout) findViewById(R.id.fragment);
        listview1 = (ListView) findViewById(R.id.listview1);
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new MainFragment()).commit();
                        list_title.setText(actionTexts[i]);
                        toolbar.setVisibility(View.VISIBLE);
                        slidingpane.closePane();
                        break;

                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ExpandableDemo()).commit();
                        list_title.setText(actionTexts[i]);
                        toolbar.setVisibility(View.VISIBLE);
                        slidingpane.closePane();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new XiaoMeng()).commit();
                        list_title.setText(actionTexts[i]);
                        toolbar.setVisibility(View.GONE);
                        slidingpane.closePane();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new QRcode_Setting()).commit();
                        list_title.setText(actionTexts[i]);
                        toolbar.setVisibility(View.VISIBLE);
                        slidingpane.closePane();
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Recycler_Main()).commit();
                        list_title.setText(actionTexts[i]);
                        toolbar.setVisibility(View.VISIBLE);
                        slidingpane.closePane();
                        break;
                }
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Welcome()).commit();
                toolbar.setVisibility(View.GONE);
                slidingpane.closePane();
            }
        });

    }
}
