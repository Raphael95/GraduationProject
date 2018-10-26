package com.example.rapha.transpotsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
<<<<<<< HEAD
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
=======
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
<<<<<<< HEAD
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
=======
import android.view.MenuItem;
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f

import fragmnt.AccountFragment;
import fragmnt.EndTransportFragment;
import fragmnt.NullFragment;
import fragmnt.OrderFragment;
import httpResolve.ResolveGson;
import httpReuest.RequestOkHttp;

public class MainActivity2 extends FragmentActivity {

    AccountFragment  accountFragment;
    OrderFragment orderFragment;
    EndTransportFragment endTransportFragment;
    NullFragment nullFragment;

    FragmentManager fm;
    FragmentTransaction transaction;

    RequestOkHttp okHttp=new RequestOkHttp();




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            transaction=fm.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_orderTaking:
                    if(orderFragment==null){
                        orderFragment=new OrderFragment();
                    }
                    transaction.replace(R.id.content,orderFragment);
                    transaction.commit();
                    break;

                case R.id.navigation_endTransport:
                    new QueryOrderEnd(String.valueOf(ResolveGson.jiashiyuan.getJiashicheliang())).execute();
                    break;

                case R.id.navigation_receipt:
                    if(accountFragment==null){
                        accountFragment=new AccountFragment();
                    }
                    transaction.replace(R.id.content,accountFragment);
                    transaction.commit();
                    break;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        fm=getSupportFragmentManager();
        transaction=fm.beginTransaction();

        orderFragment=new OrderFragment();
        transaction.add(R.id.content,orderFragment);
        transaction.commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

<<<<<<< HEAD
        BottomNavigationMenuView menuView= (BottomNavigationMenuView)navigation.getChildAt(0);
        View tab = menuView.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;

// 设置主页面菜单导航栏显示消息小红点
//        View message = LayoutInflater.from(this).inflate(R.layout.sample_message, menuView, false);
//        itemView.addView(message);
//        TextView text_message = (TextView) message.findViewById(R.id.text_message);
//        text_message.setText(String.valueOf(5));

=======
>>>>>>> 9189f46ed660dffc72b2745f5e29f4409ca22d9f

    }

    class QueryOrderEnd extends AsyncTask<Void,Void,Boolean> {

        String driverId;
        ProgressDialog dialog;

        public  QueryOrderEnd(String driverId){
            this.driverId=driverId;
        }
        @Override
        protected Boolean doInBackground(Void... params) {

            if(okHttp.queryWayBillEnd(driverId)){
                return true;
            }else{
                return false;
            }
        }

        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(MainActivity2.this);         //创建等待进度条
            dialog.setMessage("查询结束的运单...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            dialog.dismiss();
            if(aVoid){
                if(endTransportFragment==null){
                    endTransportFragment=new EndTransportFragment();
                }
                transaction.replace(R.id.content,endTransportFragment);
                transaction.commit();
            }else{
                if(nullFragment==null){
                    nullFragment=new NullFragment();
                }
                transaction.replace(R.id.content,nullFragment);
                transaction.commit();
            }

        }
    }



}
