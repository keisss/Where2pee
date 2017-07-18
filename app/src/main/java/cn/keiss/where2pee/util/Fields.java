package cn.keiss.where2pee.util;

/**
 * Created by hekai on 2017/7/12.
 */

public class Fields {
    public static final String APP_ID = "a0267f87134c7bb86901d824646b83b3";
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    public static final String SMS_MODE = "KeiSuMode";


    //sharePreference参数
    static final String SHARE_PREFERENCE_NAME = "app_preference";
    static final String IF_HAVE_LOGIN = "have_login";

    //连续定位最多尝试次数
    public static final int MORE_TRY_TIMES = 15;


    //emptyView的status
    public static final int GETING_POSITION = 0;
    public static final int GET_POSITION_FAILED = 1;
    public static final int SEARCH_RESULT_EMPTY = 2;
    public static final int NET_UNAVAILABLE = 3;

}
