package com.example.rapha.transpotsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

import dao.WayBill;
import domain.YunDan;
import httpReuest.RequestOkHttp;

public class TStartActivity extends AppCompatActivity {

    public static YunDan wayBill2;

    RequestOkHttp okHttp=new RequestOkHttp();

    TextView wayBillId;
    TextView vehiclePlate;
    TextView clientName;
    TextView startCity;
    TextView endCity;
    TextView address;
    TextView contacts;
    TextView transportTime;
    TextView tonnage;
    TextView cargoName;
    TextView price;
    TextView contacts_phone;
    TextView baochouleixing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<YunDan> wayBillList= DataSupport.findAll(YunDan.class);


        if(wayBillList.size()!=0){

            setContentView(R.layout.activity_tstart);


            wayBill2=wayBillList.get(0);

            wayBillId=(TextView)findViewById(R.id.wayBillId2);
            vehiclePlate=(TextView)findViewById(R.id.vehiclePlate2);
            clientName=(TextView)findViewById(R.id.clientName2);
            startCity=(TextView)findViewById(R.id.startCity2);
            endCity=(TextView)findViewById(R.id.endCity2);
            address=(TextView)findViewById(R.id.address2);
            contacts=(TextView)findViewById(R.id.contacts2);
            contacts_phone=(TextView)findViewById(R.id.contacts_phone2);
            transportTime=(TextView)findViewById(R.id.transportTime2);
            tonnage=(TextView)findViewById(R.id.tonnage2);
            cargoName=(TextView)findViewById(R.id.cargoName2);
            price=(TextView)findViewById(R.id.price2);
            baochouleixing=(TextView)findViewById(R.id.baochouleixing2);

            Button tstart=(Button)findViewById(R.id.tstart);



            wayBillId.setText(wayBill2.getDingdanhao());
            vehiclePlate.setText(wayBill2.getChepaihao());
            clientName.setText(wayBill2.getKehudanwei());
            startCity.setText(wayBill2.getQidian());
            endCity.setText(wayBill2.getZhongdian());
            address.setText(wayBill2.getDizhi());
            contacts.setText(wayBill2.getLianxiren());
            contacts_phone.setText(wayBill2.getDianhua());
            transportTime.setText(wayBill2.getYunshushijian());
            tonnage.setText(String.valueOf(wayBill2.getYujidunwei()));
            cargoName.setText(wayBill2.getHuowumingcheng());
            baochouleixing.setText(wayBill2.getBaochouleixing());
            price.setText(String.valueOf(wayBill2.getBaochoujine()));


            tstart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent=new Intent(TStartActivity.this,TEndActivity.class);
                    startActivity(intent);
                }
            });


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "正在联系客户...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    try{
                        Intent intent=new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+wayBill2.getDianhua()));
                        startActivity(intent);
                    }catch(SecurityException e){
                        e.printStackTrace();
                    }

                }
            });

        }else{
            setContentView(R.layout.activity_receipt_null);
        }



    }

    class StartTransporting extends AsyncTask<Void,Void,Void>{

        String wayBillId;
        String yundanzhuangtai;
        ProgressDialog dialog;

        public StartTransporting(String wayBillId,String yundanzhuangtai){
            this.wayBillId=wayBillId;
            this.yundanzhuangtai=yundanzhuangtai;
        }

        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(TStartActivity.this);
            dialog.setMessage("开始运输...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            okHttp.startTransporting(yundanzhuangtai,wayBillId);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            finish();
            Intent intent=new Intent(TStartActivity.this,TEndActivity.class);
            startActivity(intent);

        }
    }

}
