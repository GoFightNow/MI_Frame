package wangsen.sxcz.viewpager_test.setting_qrcode;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import wangsen.sxcz.viewpager_test.R;

/**
 * Created by 王森 on 2018/12/3.
 */
public class QRcode_Setting extends Fragment implements View.OnClickListener {

    public static String QRCODE_CONTENT = "";
    private EditText edi_psw;
    private Button btn_yanzheng;
    private EditText qr_content;
    private Button btn_save;

    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_qrcode_content, container, false);
        context = getContext();
        initView(view);
        qr_content.setEnabled(false);
        btn_save.setEnabled(false);
        return view;
    }

    private void initView(View view) {
        edi_psw = (EditText) view.findViewById(R.id.edi_psw);
        btn_yanzheng = (Button) view.findViewById(R.id.btn_yanzheng);
        qr_content = (EditText) view.findViewById(R.id.qr_content);
        btn_save = (Button) view.findViewById(R.id.btn_save);

        btn_yanzheng.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yanzheng:
                String psw = edi_psw.getText().toString().trim();
                if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(getContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (psw.equals("521ws")){
                    Toast.makeText(context, "验证成功", Toast.LENGTH_SHORT).show();
                    qr_content.setEnabled(true);
                    btn_save.setEnabled(true);
                    qr_content.setText(QR_Setting_Util.getQRcode_content(context)+"");
                    break;
                }else{
                    Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.btn_save:
                String content = qr_content.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(getContext(), "内容不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                else{
                    QR_Setting_Util.saveQRcode_content(context,qr_content.getText().toString());
                    Toast.makeText(context, "二维码内容更改成功", Toast.LENGTH_SHORT).show();
                    break;
                }

        }
    }

}
