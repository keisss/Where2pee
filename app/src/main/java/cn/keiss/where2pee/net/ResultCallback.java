package cn.keiss.where2pee.net;


import cn.bmob.v3.exception.BmobException;

/**
 * Created by hekai on 2017/7/19.
 *
 */

//泛型处理类
public abstract class ResultCallback {


    public abstract void onError(BmobException e);

    public abstract void onSuccess();

    public abstract void onNetUnavailable();
}