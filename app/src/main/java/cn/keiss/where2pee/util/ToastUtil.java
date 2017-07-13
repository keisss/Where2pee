package cn.keiss.where2pee.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hekai on 2017/7/12.
 */

public class ToastUtil {
    private static Toast toast;

    public static void showToast(Context context,
                                 String content) {
        if (toast == null && context !=null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
