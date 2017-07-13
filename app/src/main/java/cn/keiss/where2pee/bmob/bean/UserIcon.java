package cn.keiss.where2pee.bmob.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by hekai on 2017/7/12.
 */

public class UserIcon extends BmobObject {
    private User user;
    private BmobFile iconFile;


    public BmobFile getIconFile() {
        return iconFile;
    }

    public void setIconFile(BmobFile iconFile) {
        this.iconFile = iconFile;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
