package com.rhw.learning.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rhw.learning.R;
import com.rhw.learning.manager.DialogManager;
import com.rhw.learning.manager.UserManager;
import com.rhw.learning.module.user.User;
import com.rhw.learning.okhttp.RequestCenter;
import com.rhw.learning.okhttp.listener.DisposeDataListener;
import com.rhw.learning.service.MinaService;
import com.rhw.learning.view.associatemail.MailBoxAssociateTokenizer;
import com.rhw.learning.view.associatemail.MailBoxAssociateView;

/**
 * Date:2017/12/4 on 13:07
 * @author Simon
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 自定义登陆广播Action
     */
    public static final String LOGIN_ACTION = "com.rhw.LOGIN_ACTION";
    /**
     * UI
     */
    private MailBoxAssociateView mUserNameAssociateView;
    private EditText mPasswordView;
    private TextView mLoginView;

    /**
     * data 是否从推送到此页面
     */
    private boolean fromPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        fromPush = intent.getBooleanExtra("fromPush", false);
    }

    private void initView() {

        mUserNameAssociateView = (MailBoxAssociateView) findViewById(R.id.associate_email_input);
        mPasswordView = (EditText) findViewById(R.id.login_input_password);
        mLoginView = (TextView) findViewById(R.id.login_button);
        mLoginView.setOnClickListener(this);

        mUserNameAssociateView = (MailBoxAssociateView) findViewById(R.id.associate_email_input);
        String[] recommendMailBox = getResources().getStringArray(R.array.recommend_mailbox);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_associate_mail_list,
                R.id.tv_recommend_mail, recommendMailBox);
        mUserNameAssociateView.setAdapter(adapter);
        mUserNameAssociateView.setTokenizer(new MailBoxAssociateTokenizer());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                login();
                break;
            default:
                break;
        }
    }

    /**
     * 发送登陆请求
     */
    private void login() {
        String userName = mUserNameAssociateView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        DialogManager.getInstnce().showProgressDialog(this);
        RequestCenter.login(userName, password, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogManager.getInstnce().dismissProgressDialog();
                User user = (User) responseObj;
                UserManager.getInstance().setUser(user);
                connectToSever();
                sendLoginBroadcast();
                finish();
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogManager.getInstnce().dismissProgressDialog();
            }
        });
    }

    /**
     * 启动长连接
     */
    private void connectToSever() {
        startService(new Intent(LoginActivity.this, MinaService.class));
    }

    /**
     * 向整个应用发送登陆广播事件
     */
    private void sendLoginBroadcast() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LOGIN_ACTION));
    }

}
