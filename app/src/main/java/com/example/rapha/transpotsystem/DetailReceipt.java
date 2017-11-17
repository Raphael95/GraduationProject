package com.example.rapha.transpotsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import dao.WayBill2;
import domain.YunDan;

/**
 * Created by raphael on 2017/5/19.
 */

public class DetailReceipt extends AppCompatActivity{


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
    TextView jijiafangshi;
    TextView contacts_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiptdetail);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        YunDan wayBill=(YunDan) bundle.getSerializable("MywayBill");

        wayBillId=(TextView)findViewById(R.id.wayBillId3);
        vehiclePlate=(TextView)findViewById(R.id.vehiclePlate3);
        clientName=(TextView)findViewById(R.id.clientName3);
        startCity=(TextView)findViewById(R.id.startCity3);
        endCity=(TextView)findViewById(R.id.endCity3);
        address=(TextView)findViewById(R.id.address3);
        contacts=(TextView)findViewById(R.id.contacts3);
        contacts_phone=(TextView)findViewById(R.id.contacts_phone3);
        transportTime=(TextView)findViewById(R.id.transportTime3);
        tonnage=(TextView)findViewById(R.id.tonnage3);
        cargoName=(TextView)findViewById(R.id.cargoName3);
        price=(TextView)findViewById(R.id.price3);
        jijiafangshi=(TextView)findViewById(R.id.jijiafangshi);

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
        jijiafangshi.setText(wayBill.getBaochouleixing());
        price.setText(String.valueOf(wayBill.getBaochoujine()));
    }
}
