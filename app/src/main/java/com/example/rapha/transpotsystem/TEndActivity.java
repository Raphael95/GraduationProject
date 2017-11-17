package com.example.rapha.transpotsystem;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;
import com.baidu.trace.api.track.DistanceRequest;
import com.baidu.trace.api.track.DistanceResponse;
import com.baidu.trace.api.track.HistoryTrackRequest;
import com.baidu.trace.api.track.HistoryTrackResponse;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.api.track.SupplementMode;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.ProcessOption;
import com.baidu.trace.model.PushMessage;
import com.baidu.trace.model.TransportMode;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.Location;
import dao.WayBill;
import domain.YunDan;
import httpResolve.ResolveGson;
import httpReuest.RequestOkHttp;

public class TEndActivity extends AppCompatActivity {

    private static final int msgKey1 = 1;

    public LocationClient mLocationClient;
    public LBSTraceClient mTraceClient;

    private TextView mlocation;
    private TextView mlocation1;
    private TextView mlocation2;

    private MapView mapView;
    private BaiduMap baiduMap;

    private boolean isFirstLocate=true;

    private static BDLocation location;

    private static LatLng startLocation;
    private static LatLng endLocation;
    private static double sum;

    private int errorTime=0;

    private RequestOkHttp okHttp=new RequestOkHttp();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());

        initTrace();


        setContentView(R.layout.activity_tend);

        mlocation=(TextView) findViewById(R.id.location);
        mapView=(MapView)findViewById(R.id.mapView);
        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);

        new TimeThread().start();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EndTransportTask().execute();
                Snackbar.make(view, "结束运输...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }   /*   StringBuilder endPosition=new StringBuilder();
                endPosition.append("纬度：").append(location.getLatitude()).append("\n");
                endPosition.append("经度：").append(location.getLongitude()).append("\n");
                endPosition.append("定位方式：");
                if(location.getLocType() == BDLocation.TypeGpsLocation){
                   endPosition.append("GPS");
                }else if(location.getLocType() == BDLocation.TypeNetWorkLocation){
                   endPosition.append("终点位置");
                }
                mlocation1.setText(endPosition);*/
        });



        List<String> permissionList=new ArrayList<>();
        if(ContextCompat.checkSelfPermission(TEndActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(TEndActivity.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(TEndActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String [] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(TEndActivity.this,permissions,1);
        }else{
            requestLocation();
        }

    }


    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(9000);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result:grantResults){
                        if(result !=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(TEndActivity.this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            return ;
                        }
                    }
                    requestLocation();
                }else{
                    Toast.makeText(TEndActivity.this,"发生未知错误！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    public class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {



            new ShowGPSLocation(bdLocation).execute();

           /* if(bdLocation.getLocType()==BDLocation.TypeNetWorkLocation||bdLocation.getLocType()==BDLocation.TypeGpsLocation){
                navigateTo(bdLocation);
            }*/
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    class ShowGPSLocation extends AsyncTask<Void,Void,Boolean> {

        ProgressDialog dialog;

        public ShowGPSLocation(BDLocation locat){
            location=locat;

        }


        @Override
        protected void onPreExecute() {
            if(isFirstLocate){


                dialog=new ProgressDialog(TEndActivity.this);
                dialog.setMessage("正在定位中，马上就好...");
                dialog.setCancelable(false);
                dialog.show();

            }

        }

        @Override
        protected Boolean doInBackground(Void... params) {

                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
                baiduMap.animateMapStatus(update);
                update = MapStatusUpdateFactory.zoomTo(17f);
                baiduMap.animateMapStatus(update);


                MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
               /* locationBuilder.latitude(33.557276);
                locationBuilder.longitude(119.048496);*/
                locationBuilder.latitude(location.getLatitude());
                locationBuilder.longitude(location.getLongitude());
                MyLocationData locationData=locationBuilder.build();
                baiduMap.setMyLocationData(locationData);
                return true;


        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){

                if(isFirstLocate){
                    errorTime=1;

                    startLocation=new LatLng(location.getLatitude(),location.getLongitude());       //若是第一次定位，则初始化起始位置和终点位置均为location
                    endLocation=new LatLng(location.getLatitude(),location.getLongitude());

                 /*   StringBuilder currentPosition=new StringBuilder();
                    currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
                    currentPosition.append("经度：").append(location.getLongitude()).append("\n");
                    currentPosition.append("定位方式：");
                    if(location.getLocType() == BDLocation.TypeGpsLocation){
                        currentPosition.append("GPS");
                    }else if(location.getLocType() == BDLocation.TypeNetWorkLocation){
                        currentPosition.append("初始位置");
                    }
                    mlocation.setText(currentPosition);*/


                    dialog.dismiss();
                    Toast.makeText(TEndActivity.this,"定位成功",Toast.LENGTH_SHORT).show();
                    isFirstLocate=false;
                    errorTime++;

                }
                else{
                    endLocation=new LatLng(location.getLatitude(),location.getLongitude());
                    sum+= DistanceUtil.getDistance(startLocation,endLocation);
                    if(errorTime==2){
                        sum-=DistanceUtil.getDistance(startLocation,endLocation);
                    }
                    startLocation=endLocation;

                    DecimalFormat df=new DecimalFormat("0.00");


                    mlocation.setText(String.valueOf(df.format(sum)));

                    errorTime++;
                }



            }else{
                Toast.makeText(TEndActivity.this,"定位失败",Toast.LENGTH_SHORT).show();
            }



        }
    }

    public void initTrace(){
        long serviceId=140252;
        String entityName="MyTrace";
        boolean isNeedObjectStorage=false;
        Trace mTrace=new Trace(serviceId,entityName,isNeedObjectStorage);
        mTraceClient=new LBSTraceClient(getApplicationContext());

        int gatherInterval=2;
        int packInterval=10;

        mTraceClient.setInterval(gatherInterval,packInterval);

        mTraceClient.startTrace(mTrace,mTraceListener);
        mTraceClient.startGather(mTraceListener);


    }


    OnTraceListener mTraceListener=new OnTraceListener() {
        @Override
        public void onStartTraceCallback(int i, String s) {

        }

        @Override
        public void onStopTraceCallback(int i, String s) {

        }

        @Override
        public void onStartGatherCallback(int i, String s) {

        }

        @Override
        public void onStopGatherCallback(int i, String s) {

        }

        @Override
        public void onPushCallback(byte b, PushMessage pushMessage) {

        }
    };

    class EndTransportTask extends AsyncTask<Void,Void,Boolean>{

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(TEndActivity.this);         //创建等待进度条
            dialog.setMessage("正在结束，请稍后...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            YunDan wayBill=DataSupport.findFirst(YunDan.class);
            if(okHttp.endTransport(wayBill.getDingdanhao(),"运输结束")&&okHttp.queryWayBillEnd(ResolveGson.jiashiyuan.getJiashicheliang())){
                return true;
            }else{
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            dialog.dismiss();
            if(aVoid){
                finish();
                Intent intent=new Intent(TEndActivity.this,HuizhiActivity.class);
                startActivity(intent);
            }else{
                final AlertDialog alertDialog=new AlertDialog.Builder(TEndActivity.this).create();
                alertDialog.setMessage("操作失败");
                alertDialog.show();

                Window window = alertDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();

                // 设置透明度为0.3
                lp.alpha = 0.8f;
                lp.width=300;
                lp.height=180;
                window.setAttributes(lp);

                Handler handler = new Handler();           //设置修改密码成功确认提醒框自动消失
                handler.postDelayed(new Runnable() {

                    public void run() {
                        alertDialog.dismiss();
                    }
                }, 800);
            }
        }
    }

    public class TimeThread extends  Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case msgKey1:
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                    mlocation.setText(format.format(date));
                    break;
                default:
                    break;
            }
        }
    };

}
