package com.example.rapha.transpotsystem;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import domain.IpAddress;
import httpReuest.RequestOkHttp;

public class MainActivity extends AppCompatActivity {

    IpAddress ip;

    TextView ip_address;       //192.168.16.53
    TextView ip_port;          //8090
    Button update_server;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip_address=(TextView)findViewById(R.id.ip_address);
        ip_port=(TextView)findViewById(R.id.ip_port);
        update_server=(Button)findViewById(R.id.update_server);


        if(DataSupport.findAll(IpAddress.class).size()!=0){
            ip=DataSupport.findAll(IpAddress.class).get(0);
            ip_address.setText(ip.getIp_address());
            ip_port.setText(ip.getIp_port());
        }


        LayoutInflater inflater=getLayoutInflater();
        final View layout=inflater.inflate(R.layout.editdialog,null);

        ip_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this).setTitle("服务器地址").setView(
                       layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText ipAddress=(EditText)layout.findViewById(R.id.inputDialog);
                        ip_address.setText(ipAddress.getText().toString());
                        ((ViewGroup)layout.getParent()).removeView(layout);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((ViewGroup)layout.getParent()).removeView(layout);
                    }
                }).setCancelable(false).show();
            }
        });

        ip_port.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle("端口号").setView(
                        layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText ipPort=(EditText)layout.findViewById(R.id.inputDialog);
                        ip_port.setText(ipPort.getText().toString());
                        ((ViewGroup)layout.getParent()).removeView(layout);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((ViewGroup)layout.getParent()).removeView(layout);
                    }
                }).setCancelable(false).show();
            }
        });
        update_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataSupport.findAll(IpAddress.class).size()!=0){
                    DataSupport.deleteAll(IpAddress.class);
                }
                ip=new IpAddress();
                ip.setIp_address(ip_address.getText().toString());
                ip.setIp_port(ip_port.getText().toString());
                ip.save();
                finish();
            }
        });
    }
}
