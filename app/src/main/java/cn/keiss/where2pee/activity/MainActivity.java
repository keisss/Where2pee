package cn.keiss.where2pee.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.nearby.UploadInfo;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.keiss.where2pee.R;
import cn.keiss.where2pee.adapter.ToiletListRecyclerViewAdapter;
import cn.keiss.where2pee.bean.ToiletListItem;
import cn.keiss.where2pee.bmob.bean.PostToilet;
import cn.keiss.where2pee.listener.RecyclerViewItemClickListener;
import cn.keiss.where2pee.net.ResultCallback;
import cn.keiss.where2pee.net.UploadToilet;
import cn.keiss.where2pee.util.Fields;
import cn.keiss.where2pee.util.NetState;
import cn.keiss.where2pee.util.SharePreferUtil;
import cn.keiss.where2pee.view.BaseRecyclerView;

public class MainActivity extends AppCompatActivity implements AMapLocationListener, PoiSearch.OnPoiSearchListener, RecyclerViewItemClickListener {
    private LinearLayout layoutFilterBar;
    private Button btnMainFilterDistance;
    private Button btnMainFilterFilter;
    private Button btnMainFilterSort;
    private BaseRecyclerView rvToiletList;
    private FloatingActionButton fabAddNewToilet;
    private View emptyView;

    private AMapLocationClientOption mapLocationClientOption =null;
    private AMapLocationClient mapLocationClient;
    private int getLocationCount = 0;
    //附近搜索距离
    private int boundDistance = 500;

    private ToiletListRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置已经登录过的标记
        SharePreferUtil.setHaveLogin(true);
        initViews();
        rvToiletList.setEmptyView(emptyView);
        adapter =new ToiletListRecyclerViewAdapter(this,this);

        rvToiletList.setLayoutManager(new LinearLayoutManager(this));
        rvToiletList.setAdapter(adapter);
        prepareForLocation();
        startLocation();


    }

    private void initViews(){
        layoutFilterBar = (LinearLayout) findViewById(R.id.layout_filter_bar);
        btnMainFilterDistance = (Button) findViewById(R.id.btn_main_filter_distance);
        btnMainFilterFilter = (Button) findViewById(R.id.btn_main_filter_filter);
        btnMainFilterSort = (Button) findViewById(R.id.btn_main_filter_sort);
        rvToiletList = (BaseRecyclerView) findViewById(R.id.rv_toilet_list);
        fabAddNewToilet = (FloatingActionButton) findViewById(R.id.fab_add_new_toilet);
        emptyView = findViewById(R.id.layout_empty_view);
    }


    //定位前的准备
    private void prepareForLocation(){
        mapLocationClient = new AMapLocationClient(this);
        mapLocationClientOption = new AMapLocationClientOption();
        mapLocationClient.setLocationListener(this);
        mapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mapLocationClientOption.setHttpTimeOut(8000);
        mapLocationClient.setLocationOption(mapLocationClientOption);

    }


    //开始定位
    private void startLocation(){
        rvToiletList.setEmptyViewHint(Fields.GETTING_POSITION);

        if (NetState.networkConnected()){
            mapLocationClient.startLocation();
            getLocationCount = 0;
        }else {
            rvToiletList.setEmptyViewHint(Fields.NET_UNAVAILABLE);
        }

    }

    //接收定位信息
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        getLocationCount++;
        if (aMapLocation !=null){
            if (aMapLocation.getErrorCode() !=0){
                if (getLocationCount >= Fields.MORE_TRY_TIMES){
                    getLocationFailed();
                }
                Log.d("定位错误",aMapLocation.getErrorInfo());
            }else {
                mapLocationClient.stopLocation();
                getLocationSuccess(aMapLocation);
            }
        }
    }

    //超过规定次数定位失败 默认15次
    private void getLocationFailed(){
        mapLocationClient.stopLocation();
        rvToiletList.setEmptyViewHint(Fields.GET_POSITION_FAILED);
    }

    //定位成功
    private void getLocationSuccess(AMapLocation aMapLocation){
        rvToiletList.setEmptyViewHint(Fields.SEARCHING);
        searchToiletFromAMap(aMapLocation.getLatitude(),aMapLocation.getLongitude());
    }

    //从高德地图处搜索附近卫生间
    private void searchToiletFromAMap(double latitude, double longitude){
        PoiSearch.Query query = new PoiSearch.Query("","200300");
        query.setPageSize(1000);
        PoiSearch poiSearch = new PoiSearch(this,query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude,longitude),boundDistance));
        poiSearch.searchPOIAsyn();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapLocationClient.onDestroy();
    }

    // 从高德地图处搜索结果
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
          List<PoiItem> poiItems = poiResult.getPois();
        if (poiItems.size() == 0){
            rvToiletList.setEmptyViewHint(Fields.SEARCH_RESULT_EMPTY);
        }else {
            List<ToiletListItem> toilets = new ArrayList<>();
            for (PoiItem item: poiItems) {
                ToiletListItem toilet = new ToiletListItem();
                toilet.setDistance(item.getDistance());
                toilet.setFree(true);
                toilet.setHavePaper(false);
                toilet.setRatingNum(0);
                toilet.setThumbDownNum(0);
                toilet.setThumbUpNum(0);
                toilet.setWash(false);
                List<Photo> photos = item.getPhotos();
                if (photos.size() == 0){
                    toilet.setPicUrl(null);
                }
                else
                    toilet.setPicUrl(photos.get(0).getUrl());
                toilets.add(toilet);
            }
            adapter.setItems(toilets);
            //上传高德地图搜索到的数据
            UploadToilet.upload(poiItems, new ResultCallback() {
                @Override
                public void onError(BmobException e) {

                }

                @Override
                public void onSuccess() {

                }

                @Override
                public void onNetUnavailable() {

                }
            });
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onClick(View view, int position) {

    }


}
