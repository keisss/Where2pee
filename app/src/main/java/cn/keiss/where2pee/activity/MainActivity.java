package cn.keiss.where2pee.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.keiss.where2pee.R;
import cn.keiss.where2pee.util.SharePreferUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharePreferUtil.setHaveLogin(true);
    }
}
