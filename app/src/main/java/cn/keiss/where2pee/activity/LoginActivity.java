package cn.keiss.where2pee.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.keiss.where2pee.R;
import cn.keiss.where2pee.bmob.bean.User;
import cn.keiss.where2pee.util.ToastUtil;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout tilLoginUsername;
    private TextInputLayout tilLoginPassword;
    private Button btnLoginRegister;
    private Button btnLoginForgetPassword;

    private View.OnClickListener clickListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }



    private void initViews(){
        tilLoginUsername = (TextInputLayout) findViewById(R.id.til_login_username);
        tilLoginPassword = (TextInputLayout) findViewById(R.id.til_login_password);
        btnLoginRegister = (Button) findViewById(R.id.btn_login_register);
        btnLoginForgetPassword = (Button) findViewById(R.id.btn_login_forget_password);
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_login_register:
                        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_login_forget_password:

                        break;
                    case R.id.btn_login_exec_login:
                        startLogin();
                        break;
                }
            }
        };
    }

    private void startLogin(){
        if (checkInput()){
            BmobUser.loginByAccount(getTextFromTil(tilLoginUsername), getTextFromTil(tilLoginPassword), new LogInListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    if (e == null){
                        ToastUtil.showToast(LoginActivity.this,getString(R.string.login_success));
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.onDestroy();
                    }else
                        ToastUtil.showToast(LoginActivity.this,getString(R.string.login_failed)+e.toString());
                }
            });
        }
    }


    private boolean checkInput(){
        tilLoginPassword.setErrorEnabled(false);
        tilLoginUsername.setErrorEnabled(false);
        Editable username = tilLoginUsername.getEditText().getText();
        Editable password = tilLoginPassword.getEditText().getText();
        if (TextUtils.isEmpty(username)){
            tilLoginUsername.setError(getString(R.string.phone_number_cant_empty));
            return false;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6){
            tilLoginPassword.setError(getString(R.string.password_must_longer_than_6));
            return false;
        }
        return true;
    }


    private String getTextFromTil(TextInputLayout textInputLayout){
        Editable editable = textInputLayout.getEditText().getText();
        if (TextUtils.isEmpty(editable)){
            return "";
        }else {
            return editable.toString();
        }
    }
}
