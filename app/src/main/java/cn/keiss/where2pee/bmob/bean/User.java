package cn.keiss.where2pee.bmob.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by hekai on 2017/7/12.
 */

public class User extends BmobUser {
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
