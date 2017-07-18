package cn.keiss.where2pee.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import cn.keiss.where2pee.R;
import cn.keiss.where2pee.util.Fields;
import cn.keiss.where2pee.util.SharePreferUtil;

public class MainActivity extends AppCompatActivity implements AMapLocationListener {
    private LinearLayout layoutFilterBar;
    private Button btnMainFilterDistance;
    private Button btnMainFilterFilter;
    private Button btnMainFilterSort;
    private RecyclerView rvToiletList;
    private FloatingActionButton fabAddNewToilet;

    private AMapLocationClientOption mapLocationClientOption =null;
    private AMapLocationClient mapLocationClient;
    private int getLocationCount = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置已经登录过的标记
        SharePreferUtil.setHaveLogin(true);
        initViews();



    }

    private void initViews(){
        layoutFilterBar = (LinearLayout) findViewById(R.id.layout_filter_bar);
        btnMainFilterDistance = (Button) findViewById(R.id.btn_main_filter_distance);
        btnMainFilterFilter = (Button) findViewById(R.id.btn_main_filter_filter);
        btnMainFilterSort = (Button) findViewById(R.id.btn_main_filter_sort);
        rvToiletList = (RecyclerView) findViewById(R.id.rv_toilet_list);
        fabAddNewToilet = (FloatingActionButton) findViewById(R.id.fab_add_new_toilet);
    }

    private void prepareForLocation(){
        mapLocationClient = new AMapLocationClient(this);
        mapLocationClientOption = new AMapLocationClientOption();
        mapLocationClient.setLocationListener(this);
        mapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mapLocationClientOption.setHttpTimeOut(8000);
        mapLocationClient.setLocationOption(mapLocationClientOption);

    }

    private void startLocation(){
        mapLocationClient.startLocation();
        getLocationCount = 0;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        getLocationCount++;
        if (aMapLocation !=null){
            if (aMapLocation.getErrorCode() !=0){
                if (getLocationCount >= Fields.MORE_TRY_TIMES){
                    getLocationFailed();
                }
            }else {
                mapLocationClient.stopLocation();
                getLocationSuccess();
            }
        }
    }

    private void getLocationFailed(){

    }

    private void getLocationSuccess(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapLocationClient.onDestroy();
    }
}
