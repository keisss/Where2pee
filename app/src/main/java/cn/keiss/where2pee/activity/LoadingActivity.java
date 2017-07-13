package cn.keiss.where2pee.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.bmob.v3.BmobUser;
import cn.keiss.where2pee.R;
import cn.keiss.where2pee.bmob.bean.User;
import cn.keiss.where2pee.util.SharePreferUtil;
import cn.keiss.where2pee.util.ToastUtil;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        setNextActivity();
    }


    private void setNextActivity(){
        boolean ifHaveLogin = SharePreferUtil.getIfHaveLogin();
        User user = BmobUser.getCurrentUser(User.class);
        if (!ifHaveLogin){
            startLoginActivity();
        }else {
            if (user ==null){
                ToastUtil.showToast(this,getString(R.string.login_out_of_date));
                startLoginActivity();
            }else {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                this.onDestroy();
            }
        }

    }

    private void startLoginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        this.onDestroy();
    }
}
