package cn.keiss.where2pee.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;

/**
 * Created by hekai on 2017/7/13.
 */

public class SharePreferUtil {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private static   SharedPreferences getUserPreferences(){
        if (preferences ==null){
            preferences = ContextGetter.getInstance().getApplicationContext().getSharedPreferences(Fields.SHARE_PREFERENCE_NAME,0);
        }
        return preferences;
    }

    private static   SharedPreferences.Editor getEditor(){
        if (editor ==null){
            editor = getUserPreferences().edit();
        }
        return editor;
    }

    /**
     * 设置是否登录过的参数
     * @param haveLogin
     */
    public static void setHaveLogin(boolean haveLogin){
        getEditor().putBoolean(Fields.IF_HAVE_LOGIN,haveLogin).apply();

    }


    public static boolean getIfHaveLogin(){
        return getUserPreferences().getBoolean(Fields.IF_HAVE_LOGIN,false);
    }
}
