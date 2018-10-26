package com.example.rapha.transpotsystem;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.litepal.crud.DataSupport;

import java.util.List;

import dao.WayBill;
import domain.YunDan;
import httpReuest.RequestOkHttp;

public class ReceiptActivity extends AppCompatActivity {


    public static YunDan  wayBill;

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
    TextView safeCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<YunDan> wayBillList= DataSupport.findAll(YunDan.class);
        Log.e("ReceiptActivity",String.valueOf(wayBillList));



        if(wayBillList.size()!=0){

            setContentView(R.layout.activity_receipt);


            wayBill=wayBillList.get(0);

            wayBillId=(TextView)findViewById(R.id.wayBillId);
            vehiclePlate=(TextView)findViewById(R.id.vehiclePlate);
            clientName=(TextView)findViewById(R.id.clientName);
            startCity=(TextView)findViewById(R.id.startCity);
            endCity=(TextView)findViewById(R.id.endCity);
            address=(TextView)findViewById(R.id.address);
            contacts=(TextView)findViewById(R.id.contacts);
            contacts_phone=(TextView)findViewById(R.id.contacts_phone);
            transportTime=(TextView)findViewById(R.id.transportTime);
            tonnage=(TextView)findViewById(R.id.tonnage);
            cargoName=(TextView)findViewById(R.id.cargoName);
            safeCard = (TextView)findViewById(R.id.safeCard);
            price=(TextView)findViewById(R.id.price);
            baochouleixing=(TextView)findViewById(R.id.baochouleixing);

            Button accept=(Button)findViewById(R.id.accept);
            Button reject=(Button)findViewById(R.id.reject);



            wayBillId.setText(wayBill.getDingdanhao());
            vehiclePlate.setText(wayBill.getChepaihao());
            clientName.setText(wayBill.getKehudanwei());
            startCity.setText(wayBill.getQidian());
            endCity.setText(wayBill.getZhongdian());
            address.setText(wayBill.getDizhi());
            contacts.setText(wayBill.getLianxiren());
            contacts_phone.setText(wayBill.getDianhua());
            transportTime.setText(wayBill.getYunshushijian());
            tonnage.setText(String.valueOf(wayBill.getYujidunwei()));
            cargoName.setText(wayBill.getHuowumingcheng());
            safeCard.setText(wayBill.getAnquanka());
            baochouleixing.setText(wayBill.getBaochouleixing());
            price.setText(String.valueOf(wayBill.getBaochoujine()));


            safeCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent wordIntent = new Intent(ReceiptActivity.this,WordActivity.class);
                    wordIntent.putExtra("path",wayBill.getAnquankapath());
                    startActivity(wordIntent);
                }
            });

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AcceptReceipt(wayBill.getDingdanhao(),"true").execute();
                }
            });
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AcceptReceipt(wayBill.getDingdanhao(),"false").execute();
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
                        intent.setData(Uri.parse("tel:"+wayBill.getDianhua()));
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

    class AcceptReceipt extends AsyncTask<Void,Void,Boolean>{
        String wayBillId;
        String isAccept;
        ProgressDialog dialog;

        public AcceptReceipt(String wayBillId,String isAccept){
            this.wayBillId=wayBillId;
            this.isAccept=isAccept;
        }

        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(ReceiptActivity.this);
            if(isAccept.equals("true")){
                dialog.setMessage("正在锁定订单信息");
            }else{
                dialog.setMessage("正在拒绝该运单");
            }

            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if(okHttp.IsAcceptReceipt(isAccept,wayBillId)){
                return true;
            }else{
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            dialog.dismiss();
            if(!aBoolean){
                Dialog dialog=new AlertDialog.Builder(ReceiptActivity.this)
                        .setTitle("抱歉")
                        .setMessage("接单失败，请重新接单！")
                        .setCancelable(false)
                        .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                dialog.show();
            }else{
                if(isAccept.equals("true")){
                    finish();
                    Intent intent=new Intent(ReceiptActivity.this,TStartActivity.class);
                    startActivity(intent);
                }else{
                    finish();
                  /*  Intent intent=new Intent(ReceiptActivity.this,MainActivity2.class);
                    startActivity(intent);*/
                }

            }
        }
    }


}
