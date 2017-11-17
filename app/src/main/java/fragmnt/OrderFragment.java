package fragmnt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.rapha.transpotsystem.EmptyVehicleActivity;
import com.example.rapha.transpotsystem.R;
import com.example.rapha.transpotsystem.ReceiptActivity;
import com.example.rapha.transpotsystem.TStartActivity;

import java.util.ArrayList;
import java.util.List;

import dao.WayBill;
import httpResolve.ResolveGson;
import httpReuest.RequestOkHttp;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;


/**
 * Created by raphael on 2017/4/28.
 */

public class OrderFragment extends Fragment{

    public static WayBill wayBill;

    RequestOkHttp okHttp=new RequestOkHttp();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.ordertaking,container,false);

        Button receiptAcceptButton=(Button)v.findViewById(R.id.receipt_accept);
        Button receiptIng=(Button)v.findViewById(R.id.receipt_ing);




        receiptAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ResolveGson.jiashiyuan.getJiashicheliang()==""){
                    Intent i=new Intent(getActivity(), EmptyVehicleActivity.class);
                    startActivity(i);
                }else{
                    new QueryOrder(String.valueOf(ResolveGson.jiashiyuan.getJiashicheliang())).execute();     //执行后台连接服务器查询待接运单列表任务
                }

            }
        });


        receiptIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ResolveGson.jiashiyuan.getJiashicheliang()==""){
                    Intent i=new Intent(getActivity(), EmptyVehicleActivity.class);
                    startActivity(i);
                }else{
                    new QueryOrderIng(String.valueOf(ResolveGson.jiashiyuan.getJiashicheliang())).execute();     //执行后台连接服务器查询正在进行运单列表任务
                }




            }
        });


        return v;
    }

    class QueryOrder extends AsyncTask<Void,Void,Void> {

        String driverId;
        ProgressDialog dialog;

        public  QueryOrder(String driverId){
            this.driverId=driverId;
        }
        @Override
        protected Void doInBackground(Void... params) {

            okHttp.queryWayBill(driverId);
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(getActivity());         //创建等待进度条
            dialog.setMessage("查询待接订单，请稍后...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            Intent intent=new Intent(getActivity(), ReceiptActivity.class);
            startActivity(intent);
        }
    }

    class QueryOrderIng extends AsyncTask<Void,Void,Void> {

        String driverId;
        ProgressDialog dialog;

        public  QueryOrderIng(String driverId){
            this.driverId=driverId;
        }
        @Override
        protected Void doInBackground(Void... params) {

            okHttp.queryWayBillIng(driverId);
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(getActivity());         //创建等待进度条
            dialog.setMessage("查询正在进行的订单，请稍后...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            Intent intent=new Intent(getActivity(), TStartActivity.class);
            startActivity(intent);
        }
    }

}

