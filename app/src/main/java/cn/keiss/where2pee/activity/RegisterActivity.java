package cn.keiss.where2pee.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.keiss.where2pee.R;
import cn.keiss.where2pee.bmob.bean.User;
import cn.keiss.where2pee.util.FinalNumber;
import cn.keiss.where2pee.util.ToastUtil;


public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout tilRegisterNickname;
    private TextInputLayout tilRegisterUsername;
    private TextInputLayout tilRegisterCheckCode;
    private Button btnRegisterGetCheckCode;
    private TextInputLayout tilRegisterPassword;
    private Button btnRegisterExecRegister;

    private View.OnClickListener clickListener;
    private boolean haveCheckedCode;
    private String phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

        btnRegisterGetCheckCode.setOnClickListener(clickListener);
        btnRegisterExecRegister.setOnClickListener(clickListener);
    }

    private void initViews(){
        tilRegisterNickname = (TextInputLayout) findViewById(R.id.til_register_nickname);
        tilRegisterUsername = (TextInputLayout) findViewById(R.id.til_register_username);
        tilRegisterCheckCode = (TextInputLayout) findViewById(R.id.til_register_check_code);
        btnRegisterGetCheckCode = (Button) findViewById(R.id.btn_register_get_check_code);
        tilRegisterPassword = (TextInputLayout) findViewById(R.id.til_register_password);
        btnRegisterExecRegister = (Button) findViewById(R.id.btn_register_exec_register);
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_register_get_check_code:
                        getCheckCode();
                        break;
                    case R.id.btn_register_exec_register:
                        if (isInputLegally()){
                            execRegister();
                        }
                        break;

                }
            }
        };
    }

    //获取验证码逻辑
    private void getCheckCode(){
        tilRegisterUsername.setErrorEnabled(false);
        Editable phoneNum = tilRegisterUsername.getEditText().getText();

        if (TextUtils.isEmpty(phoneNum)){
            tilRegisterUsername.setError("电话号不能为空");
        }else {
         phoneNumber = phoneNum.toString();
            if (!phoneNumber.matches(FinalNumber.REGEX_MOBILE)){
                tilRegisterUsername.setError("电话号码不合法");
            }else {
                setCheckCodeBtnCountDown();
                requestCheckCode(phoneNumber);
            }
        }
    }

    //按下获取验证码后延时60秒
    private void setCheckCodeBtnCountDown(){
        btnRegisterGetCheckCode.setClickable(false);
        final Timer timer = new Timer(true);

        btnRegisterGetCheckCode.setText(60+R.string.seconds_after_get);



        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String btnText = btnRegisterGetCheckCode.getText().toString();
                int second = Integer.parseInt(btnText.split("秒")[0]);

                if (second >0){
                    second--;
                    btnRegisterGetCheckCode.setText(second + R.string.seconds_after_get);
                }else {
                    btnRegisterGetCheckCode.setText("获取验证码");
                    btnRegisterGetCheckCode.setClickable(true);
                    timer.cancel();
                }
            }
        };

        timer.schedule(timerTask,1000);
    }

    //请求验证码
    private void requestCheckCode(String phoneNumber){
        BmobSMS.requestSMSCode(phoneNumber,FinalNumber.SMS_MODE,new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e ==null){
                    ToastUtil.showToast(RegisterActivity.this,"验证码发送成功");
                }else {
                    ToastUtil.showToast(RegisterActivity.this,"验证码发送失败,请检查网络后重试");
                    haveCheckedCode = true;
                }
            }
        });
    }

    private boolean isInputLegally() {
        clearErrHint();
        if(!haveCheckedCode){
            tilRegisterCheckCode.setError(getString(R.string.please_click_request_check_code));
            return false;
        }
        if (TextUtils.isEmpty(tilRegisterNickname.getEditText().getText())){
            tilRegisterNickname.setError("昵称不能为空");
            return false;
        }
        if (TextUtils.isEmpty(tilRegisterCheckCode.getEditText().getText())){
            tilRegisterCheckCode.setError("请输入验证码");
            return false;
        }
        if (TextUtils.isEmpty(tilRegisterPassword.getEditText().getText())
                || tilRegisterPassword.getEditText().getText().length()<6 ){
            tilRegisterPassword.setError("密码必须大于6位");
            return false;
        }
        return true;
    }

    private void clearErrHint(){
        tilRegisterNickname.setErrorEnabled(false);
        tilRegisterUsername.setErrorEnabled(false);
        tilRegisterCheckCode.setErrorEnabled(false);
        tilRegisterPassword.setErrorEnabled(false);
    }

    //执行注册
    private void execRegister(){
        User user = new User();
        user.setMobilePhoneNumber(phoneNumber);
        user.setUsername(phoneNumber);
        user.setNickName(getTextFromTil(tilRegisterNickname));
        user.setPassword(getTextFromTil(tilRegisterPassword));
        user.signOrLogin(getTextFromTil(tilRegisterCheckCode), new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null){
                    registerSuccess();
                }else {
                    ToastUtil.showToast(RegisterActivity.this,e.toString());
                }
            }
        });
    }

    //注册成功的回调
    private void registerSuccess(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
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
