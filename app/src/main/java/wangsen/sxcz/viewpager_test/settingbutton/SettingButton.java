package wangsen.sxcz.viewpager_test.settingbutton;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import wangsen.sxcz.viewpager_test.MainActivity;
import wangsen.sxcz.viewpager_test.R;

/**
 * Created by 王森 on 2018/12/2.
 */
public class SettingButton extends Dialog {
    private Context context;
    private TextView tx_ip;
    private EditText edi_ip;
    private TextView tx_port;
    private EditText edi_port;
    private Button btn_save;

    private NetInfo netInfo;

    public SettingButton(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除对话框的标题，必须在setContentView()前
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settingdialog);
        netInfo = SettingUtil.getNETinfo(context);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        initview();
        if (netInfo.getUrl().equals("")) {
            edi_ip.setHint("null");
        } else {
            edi_ip.setText(netInfo.getUrl() + "");
        }
        if (netInfo.getPort().equals("")) {
            edi_port.setHint("null");
        } else {
            edi_port.setText(netInfo.getPort() + "");
        }
    }

    private void initview() {
        tx_ip = (TextView) findViewById(R.id.tx_ip);
        tx_port = (TextView) findViewById(R.id.tx_port);
        edi_ip = (EditText) findViewById(R.id.edi_ip);
        edi_port = (EditText) findViewById(R.id.edi_port);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    private void submit() {
        String ip = edi_ip.getText().toString().trim();
        if (TextUtils.isEmpty(ip)) {
            Toast.makeText(context, "IP不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String port = edi_port.getText().toString().trim();
        if (TextUtils.isEmpty(port)) {
            Toast.makeText(context, "Port不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            SettingUtil.saveNETinfo(context, ip, port);
            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
            dismiss();
        }
    }
}
