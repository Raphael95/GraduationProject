package com.example.rapha.transpotsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;


import dao.WayBill;
import domain.YunDan;
import httpResolve.ResolveGson;
import httpReuest.RequestOkHttp;

public class HuizhiActivity extends AppCompatActivity {
    TextView wayBillId;
    TextView cargoName;

    EditText inTonnage;
    EditText outTonnage;
    EditText meters;
    EditText substitutePay;
    EditText substitudePayDes;

    Button huizhi_submit;

    RequestOkHttp okHttp = new RequestOkHttp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huizhi);

        wayBillId = (TextView) findViewById(R.id.huizhi_wayBillId2);
        cargoName = (TextView) findViewById(R.id.huizhi_cargoName2);

        inTonnage = (EditText) findViewById(R.id.inTonnage);
        outTonnage = (EditText) findViewById(R.id.outTonnage);
        meters = (EditText) findViewById(R.id.meters);
        substitutePay = (EditText) findViewById(R.id.substitutePay);
        substitudePayDes=(EditText) findViewById(R.id.substitudePayDes);

        huizhi_submit = (Button) findViewById(R.id.huizhi_submit);

        final YunDan wayBill = DataSupport.findFirst(YunDan.class);
        wayBillId.setText(wayBill.getDingdanhao());
        cargoName.setText(wayBill.getHuowumingcheng());

        huizhi_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject json = new JSONObject();
                    json.put("dingdanhao", wayBill.getDingdanhao());
                    json.put("shifadunwei", outTonnage.getText().toString());
                    json.put("shishoudunwei", inTonnage.getText().toString());
                    json.put("licheng", meters.getText().toString());
                    json.put("daidianfei", substitutePay.getText().toString());
                    json.put("feiyongmiaoshu",substitudePayDes.getText().toString());
                    String huizhidan = json.toString();
                    new HuizhiTask1(huizhidan).execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //new HuizhiTask(wayBill.getDingdanhao(), outTonnage.getText().toString(), inTonnage.getText().toString(), meters.getText().toString(), substitutePay.getText().toString()).execute();


            }
        });

    }

    class HuizhiTask extends AsyncTask<Void, Void, Boolean> {
        private String dingdanhao;
        private String shifadunwei;
        private String shishoudunwei;
        private String licheng;
        private String daidianfei;
        ProgressDialog dialog;

        public HuizhiTask(String dingdanhao, String shifadunwei, String shishoudunwei, String licheng, String daidianfei) {
            this.dingdanhao = dingdanhao;
            this.shifadunwei = shifadunwei;
            this.shishoudunwei = shishoudunwei;
            this.licheng = licheng;
            this.daidianfei = daidianfei;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(HuizhiActivity.this);         //创建等待进度条
            dialog.setMessage("提交回执单中...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if (okHttp.HuizhiSubmit(dingdanhao, shifadunwei, shishoudunwei, licheng, daidianfei)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            dialog.dismiss();
            if (aBoolean) {
                finish();
                Intent intent = new Intent(HuizhiActivity.this, MainActivity2.class);
                startActivity(intent);
            } else {
                final AlertDialog alertDialog = new AlertDialog.Builder(HuizhiActivity.this).create();
                alertDialog.setMessage("提交失败");
                alertDialog.show();

                Window window = alertDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();

                // 设置透明度为0.3
                lp.alpha = 0.8f;
                lp.width = 300;
                lp.height = 180;
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
    class HuizhiTask1 extends AsyncTask<Void, Void, Boolean> {
        private String returnData;

        ProgressDialog dialog;

        public HuizhiTask1(String returnData) {

            this.returnData = returnData;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(HuizhiActivity.this);         //创建等待进度条
            dialog.setMessage("提交回执单中...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if (okHttp.HuizhiSubmit1(returnData)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            dialog.dismiss();
            if (aBoolean) {
                finish();
                Intent intent = new Intent(HuizhiActivity.this, MainActivity2.class);
                startActivity(intent);
            } else {
                final AlertDialog alertDialog = new AlertDialog.Builder(HuizhiActivity.this).create();
                alertDialog.setMessage("提交失败");
                alertDialog.show();

                Window window = alertDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();

                // 设置透明度为0.3
                lp.alpha = 0.8f;
                lp.width = 300;
                lp.height = 180;
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
}
