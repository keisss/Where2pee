package cn.keiss.where2pee.bmob;

import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.keiss.where2pee.util.FinalNumber;

/**
 * Created by hekai on 2017/7/12.
 */

public class BmobControl {



    public void initializeBmob(Context context){
        Bmob.initialize(context, FinalNumber.APP_ID);
    }


}
