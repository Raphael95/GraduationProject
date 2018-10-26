package com.example.rapha.transpotsystem;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;


import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dao.Driver;
import dao.User;
import domain.JiaShiYuan;
import httpReuest.RequestOkHttp;
import util.UploadUtil;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG="RegisterActivity";
    public static final int CHOOSE_PHOTO=2;
    public static final int CHOOSE_LICENSE=3;
    public static final int CHOOSE_QUALIFICATION=4;
    public static final int CHOOSE_IDENTIFIACTION=5;

    private ImageView picture;
    private ImageButton choosePhoto;
    private ImageButton choose_license;
    private ImageButton choose_qualification;
    private ImageButton choose_identification;

    private EditText name;
    private EditText phone;
    private EditText place;
    private EditText address;
    private EditText identification;
    private Spinner vehicle;

    private String gender;
    private String ve;

    private String photo;
    private String license_path;
    private String qualification_path;
    private String identification_path;


    private TextView dateBorn;
    private TextView certificateStart;
    private TextView certificateEnd;

    private RequestOkHttp okHttp=new RequestOkHttp();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG,"onCreate() called");

        name=(EditText) findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.telephone);
        place=(EditText)findViewById(R.id.place);
        address=(EditText)findViewById(R.id.address);
        identification=(EditText)findViewById(R.id.identification);
        vehicle=(Spinner)findViewById(R.id.vehicle);


        dateBorn=(TextView) findViewById(R.id.dateBorn);
        certificateStart=(TextView)findViewById(R.id.certificateStart);
        certificateEnd=(TextView)findViewById(R.id.certificateEnd);

        choosePhoto=(ImageButton)findViewById(R.id.choose_photo);
        choose_license=(ImageButton)findViewById(R.id.choose_license);
        choose_qualification=(ImageButton)findViewById(R.id.choose_qualification);
        choose_identification=(ImageButton)findViewById(R.id.choose_identification);

        ve=(String)vehicle.getSelectedItem();


        Button submit=(Button)findViewById(R.id.VerifySubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JiaShiYuan jiashiyuan=new JiaShiYuan();

                jiashiyuan.setJiashiyuanxingming(name.getText().toString());
                jiashiyuan.setDianhua(phone.getText().toString());
                jiashiyuan.setJiguan(place.getText().toString());
                jiashiyuan.setDizhi(address.getText().toString());
                jiashiyuan.setShenfenzhenghao(identification.getText().toString());
                jiashiyuan.setZhunjiachexing(ve);
                jiashiyuan.setChushengriqi(dateBorn.getText().toString());
                jiashiyuan.setChucilingzhengriqi(certificateStart.getText().toString());
                jiashiyuan.setJiashizhengnianshenriqi(certificateEnd.getText().toString());

                if(TextUtils.isEmpty(name.getText().toString())){
                    name.setError("姓名不能空");
                }else if(TextUtils.isEmpty(phone.getText().toString())){
                    phone.setError("电话不能空");
                }else if(TextUtils.isEmpty(place.getText().toString())){
                    place.setError("籍贯不能空");
                }else if(TextUtils.isEmpty(address.getText().toString())){
                    address.setError("地址不能空");
                }else if(TextUtils.isEmpty(identification.getText().toString())){
                    identification.setError("身份证不能空");
                }else if(TextUtils.isEmpty(dateBorn.getText().toString())){
                    dateBorn.setError("选择");
                }else if(TextUtils.isEmpty(certificateStart.getText().toString())){
                    certificateStart.setError("选择");
                }else if(TextUtils.isEmpty(certificateEnd.getText().toString())){
                    certificateEnd.setError("选择");
                }else {

                    new VerifyTask(jiashiyuan).execute();         //提交验证信息

                }




            }
        });

        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                   new QueryUser(phone.getText().toString()).execute();
                }
            }
        });

        identification.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    dateBorn.setText(identification.getText().toString().substring(6,10)+"-"+identification.getText().toString().substring(10,12)+"-"+identification.getText().toString().substring(12,14));
                }
            }
        });



       /* dateBorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();

                new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                int month=monthOfYear+1;
                                dateBorn.setText(year+"-"+month+"-"+dayOfMonth);
                            }
                        },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/

        certificateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();

                new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                int month=monthOfYear+1;
                                certificateStart.setText(year+"-"+month+"-"+dayOfMonth);
                            }
                        },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        certificateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();

                new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                int month=monthOfYear+1;
                                certificateEnd.setText(year+"-"+month+"-"+dayOfMonth);
                            }
                        },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum(CHOOSE_PHOTO);
                }
            }
        });

        choose_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum(CHOOSE_LICENSE);
                }
            }
        });

        choose_qualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum(CHOOSE_QUALIFICATION);
                }
            }
        });

        choose_identification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum(CHOOSE_IDENTIFIACTION);
                }
            }
        });

    }

    private void openAlbum(int requestCode){
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum(requestCode);
                }else{
                    Toast.makeText(RegisterActivity.this,"你拒绝了访问相册",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(Build.VERSION.SDK_INT>=19){
                handleImageOnKitKat(requestCode,data);
            }else{
                handleImageBeforeKitKat(requestCode,data);
            }
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(int requestCode,Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id=docId.split(":")[1];
                String selection= MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath=getImagePath(uri,null);
        } else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath=uri.getPath();
        }
        displayImage(requestCode,imagePath);

    }

    private void handleImageBeforeKitKat(int requestCode,Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(requestCode,imagePath);
    }

    private String getImagePath(Uri uri,String selection){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


    private void displayImage(int requestCode,String imagePath){
        if(imagePath!=null){

            Bitmap bitmap= BitmapFactory.decodeFile(imagePath);
            //Bitmap bitmap1=Bitmap.createScaledBitmap(bitmap,2000,2000,true);
            switch(requestCode){
                case CHOOSE_PHOTO:
                    choosePhoto.setImageBitmap(bitmap);
                    photo=imagePath;
                    break;
                case CHOOSE_LICENSE:
                    choose_license.setImageBitmap(bitmap);
                    license_path=imagePath;
                    break;
                case CHOOSE_QUALIFICATION:
                    choose_qualification.setImageBitmap(bitmap);
                    qualification_path=imagePath;
                    break;
                case CHOOSE_IDENTIFIACTION:
                    choose_identification.setImageBitmap(bitmap);
                    identification_path=imagePath;
                    break;
                default:
            }

            Log.d(TAG,imagePath);

        }else{
            Toast.makeText(RegisterActivity.this,"显示失败",Toast.LENGTH_SHORT).show();
        }
    }

    class VerifyTask extends AsyncTask<Void,Void,Boolean>{

       JiaShiYuan jiashiyuan;

        private ProgressDialog dialog;

        public VerifyTask(JiaShiYuan jiashiyuan){
           this.jiashiyuan=jiashiyuan;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            //okHttp.verify(u,d);
            if(okHttp.uploadImage(photo,license_path,qualification_path,identification_path,jiashiyuan)){
                return true;
            }else{
                return false;
            }
        }

        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(RegisterActivity.this);
            dialog.setMessage("正在提交您的信息...");
            dialog.setCancelable(false);
            dialog.show();

        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                dialog.dismiss();
                finish();
                Intent intent=new Intent(RegisterActivity.this,VerifySuccessActivity.class);
                startActivity(intent);
            }else{
                dialog.dismiss();
                final AlertDialog alertDialog=new AlertDialog.Builder(RegisterActivity.this).create();
                alertDialog.setMessage("图片上传失败，请重新提交！");
                alertDialog.show();

                Window window = alertDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();

                // 设置透明度为0.3
                lp.alpha = 0.8f;
                lp.width=900;
                lp.height=200;
                window.setAttributes(lp);

                Handler handler = new Handler();           //设置上传失败提醒框自动消失
                handler.postDelayed(new Runnable() {

                    public void run() {
                        alertDialog.dismiss();
                    }
                }, 3000);
            }
        }
    }

    class QueryUser extends AsyncTask<Void,Void,Boolean>{

        String phone;

        ProgressDialog dialog;

        public QueryUser(String phone){
            this.phone=phone;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
           if(okHttp.queryUserByPhone(phone)){
               return true;

           }else{

               return false;
           }
        }

        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(RegisterActivity.this);         //创建等待进度条
            dialog.setMessage("验证账户，请稍后...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                dialog.dismiss();
                Dialog dialog=new AlertDialog.Builder(RegisterActivity.this)
                        .setTitle("提醒")
                        .setMessage("该账户已存在，请直接登陆")
                        .setCancelable(false)
                        .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .create();
                dialog.show();

            }else{
                dialog.dismiss();
                Toast.makeText(RegisterActivity.this,"该用户可以注册",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
