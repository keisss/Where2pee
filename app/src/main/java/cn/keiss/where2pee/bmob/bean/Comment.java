package cn.keiss.where2pee.bmob.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by hekai on 2017/7/12.
 */

public class Comment extends BmobObject {
    private User user;
    private PostToilet toilet;
    private String commentContent;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PostToilet getToilet() {
        return toilet;
    }

    public void setToilet(PostToilet toilet) {
        this.toilet = toilet;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
