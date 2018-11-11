package com.wy.schooltakenout.LoginPage;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wy.schooltakenout.R;
import com.wy.schooltakenout.Tool.IOTool;

import java.util.ArrayList;
import java.util.List;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {
    EditText userNameEdit;
    EditText userPhoneEdit;
    EditText passwordEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    // 初始化
    private void init() {
        // 获取控件
        userNameEdit = findViewById(R.id.edit_name);
        userPhoneEdit = findViewById(R.id.edit_userPhone);
        passwordEdit = findViewById(R.id.edit_password);
        Button signinButton = findViewById(R.id.button_signin);

        // 设置监听
        signinButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_signin:
                // 获取注册信息
                String name = userNameEdit.getText().toString();
                String phone = userPhoneEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                // 判断登录信息完整性
                if(!phone.equals("") && !password.equals("")) {
                    // 得到url
                    String url = IOTool.ip + "read/user/register.do";
                    // 将数据封装
                    List<String> list = new ArrayList<>();
                    list.add("name_"+name);
                    list.add("phone_"+phone);
                    list.add("password_"+password);
                    // 发送往服务器
                    String result = IOTool.upAndDown(url, list);
                    // 对服务器返回内容进行处理
                    if(result == null) {
                        Toast.makeText(SigninActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    } else if(result.equals("0")) {
                        Toast.makeText(SigninActivity.this, "注册失败", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SigninActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else
                    Toast.makeText(SigninActivity.this, "请填写完整", Toast.LENGTH_LONG).show();
        }
    }
}