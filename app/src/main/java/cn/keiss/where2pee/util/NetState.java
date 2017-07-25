package cn.keiss.where2pee.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by hekai on 2017/7/18.
 *
 */

public class NetState {
    // 检查是否连接到网络
    public static boolean networkConnected() {


        ConnectivityManager manager = (ConnectivityManager) ContextGetter.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isAvailable();


    }

}
