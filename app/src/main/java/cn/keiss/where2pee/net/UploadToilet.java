package cn.keiss.where2pee.net;



import com.amap.api.services.core.PoiItem;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.SaveListener;
import cn.keiss.where2pee.bmob.bean.PostToilet;
import cn.keiss.where2pee.bmob.bean.User;
import cn.keiss.where2pee.geohash.GeoHash;
import cn.keiss.where2pee.util.Fields;
import cn.keiss.where2pee.util.NetState;

/**
 * Created by hekai on 2017/7/19.
 *  上传卫生间
 */

public class UploadToilet {

    //用户上传单个卫生间
    public static void upLoad(PostToilet postToilet, final ResultCallback callback){
        if (NetState.networkConnected()){
            postToilet.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null){
                        callback.onSuccess();
                    }else
                        callback.onError(e);
                }
            });
        }else
            callback.onNetUnavailable();


    }

    //批量上传从高德地图处得到的卫生间
    public static void upload(List<PoiItem> poiItems, final ResultCallback callback){
        if (NetState.networkConnected()){

            List<BmobObject> toilets = new ArrayList<>();
                if (poiItems != null && poiItems.size() != 0){
                        User user = new User();
                        user.setObjectId(Fields.AMAP_USER_ID);

                    for (PoiItem poiItem :poiItems) {
                        double lat = poiItem.getLatLonPoint().getLatitude();
                        double lon = poiItem.getLatLonPoint().getLongitude();

                        PostToilet postToilet = new PostToilet();
                        postToilet.setAmapId(poiItem.getPoiId());
                        postToilet.setBadTimes(0);
                        postToilet.setDislikeNumber(0);
                        postToilet.setFree(true);
                        postToilet.setLatitude(lat);
                        postToilet.setLongitude(lon);
                        postToilet.setLikeNumber(0);
                        postToilet.setPaper(false);
                        postToilet.setRating(Integer.valueOf(poiItem.getPoiExtension().getmRating()));
                        postToilet.setOpenTime(poiItem.getPoiExtension().getOpentime());
                        postToilet.setLocationDescription(poiItem.getSnippet());
                        postToilet.setUsedTimes(0);
                        postToilet.setUseFul(true);
                        postToilet.setUser(user);
                        postToilet.setWater(false);

                        GeoHash geoHash = new GeoHash(lat,lon);
                        postToilet.setGeoHash(geoHash.getGeoHashBase32());
                        toilets.add(postToilet);
                    }
                    new BmobBatch().insertBatch(toilets).doBatch(new QueryListListener<BatchResult>() {
                        @Override
                        public void done(List<BatchResult> list, BmobException e) {
                            if (e == null){
                                callback.onSuccess();
                            }else {
                                callback.onError(e);
                            }
                        }
                    });

                }
        }else {
            callback.onNetUnavailable();
        }
    }

}
