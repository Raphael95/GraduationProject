package com.example.rapha.transpotsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import adapter.AllReceiptAdapter;
import adapter.AllReceiptRecyclerAdapter;
import dao.Client;
import dao.WayBill;
import dao.WayBill2;
import domain.YunDan;
import httpResolve.ResolveGson;
import httpReuest.RequestOkHttp;
import util.SpaceItemDecoration;

public class AllReceiptActivity extends AppCompatActivity {

    RequestOkHttp okHttp=new RequestOkHttp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_receipt);

        new QueryAllWayBill().execute();

        final SwipeRefreshLayout swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Refresh(swipeRefresh).execute();
            }
        });



       /* Log.e("RequestOkHttp","转换得到的货物名称是："+wayBillList.get(0).getDangerouscargo().getCargoName());
        Log.e("RequestOkHttp","转换得到的客户名称是："+wayBillList.get(0).getClient().getClientBriefName());*/
     /*   WayBill wayBill=new WayBill();
        wayBill.setClientName("天机单位");
        wayBill.setWayBillId("123456");
        wayBill.setDangerousCargoName("硝酸盐");
        wayBill.setStart("济南");
        wayBill.setEnd("北京");

        wayBillList.add(wayBill);*/


    }

    class QueryAllWayBill extends AsyncTask<Void,Void,Boolean> {
        ProgressDialog dialog;
        List<YunDan> wayBillList;
        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(AllReceiptActivity.this);
            dialog.setMessage("查询您的历史运单...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            this.wayBillList=okHttp.queryAllWayBill(String.valueOf(ResolveGson.jiashiyuan.getJiashicheliang()));


            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            dialog.dismiss();
            if(aBoolean){
               /* AllReceiptAdapter allReceiptAdapter=new AllReceiptAdapter(wayBillList,AllReceiptActivity.this);
                ListView listView=(ListView)findViewById(R.id.allReceipt_listView);
                listView.setAdapter(allReceiptAdapter);*/
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.allReceipt_listView);
                recyclerView.setLayoutManager(new LinearLayoutManager(AllReceiptActivity.this));
                recyclerView.addItemDecoration(new SpaceItemDecoration(15));
                AllReceiptRecyclerAdapter adapter=new AllReceiptRecyclerAdapter(wayBillList,AllReceiptActivity.this);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    class Refresh extends AsyncTask<Void,Void,Boolean> {
        SwipeRefreshLayout swipeRefresh;
        List<YunDan> wayBillList;

        public Refresh( SwipeRefreshLayout swipeRefresh){
            this.swipeRefresh=swipeRefresh;
        }
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            this.wayBillList=okHttp.queryAllWayBill(String.valueOf(ResolveGson.jiashiyuan.getJiashicheliang()));


            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){

                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.allReceipt_listView);
                recyclerView.setLayoutManager(new LinearLayoutManager(AllReceiptActivity.this));
                AllReceiptRecyclerAdapter adapter=new AllReceiptRecyclerAdapter(wayBillList,AllReceiptActivity.this);
                //recyclerView.setAdapter(adapter);
                recyclerView.setAdapter(adapter);
                swipeRefresh.setRefreshing(false);
                Toast.makeText(AllReceiptActivity.this,"刷新成功！",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(AllReceiptActivity.this,"刷新失败，请重试！",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
