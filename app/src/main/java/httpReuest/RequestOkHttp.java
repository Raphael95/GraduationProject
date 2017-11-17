package httpReuest;

import android.util.Log;

import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dao.Driver;
import dao.User;
import domain.IpAddress;
import domain.JiaShiYuan;
import domain.YunDan;
import httpResolve.ResolveGson;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Rapha on 2017/3/30.
 */

public class RequestOkHttp {

    private static IpAddress ip;

    public static String ip_address;
    public static String ip_port;

    private static final MediaType MEDIA_TYPE_JPG=MediaType.parse("image/jpg");
    private static String url;

    ResolveGson resolveGson=new ResolveGson();



    public RequestOkHttp(){
        if(DataSupport.findAll(IpAddress.class).size()!=0){
            ip=DataSupport.findAll(IpAddress.class).get(0);
            ip_address=ip.getIp_address();
            ip_port=ip.getIp_port();
            url="http://"+ip_address+":"+ip_port+"/"+"transportmanager/";
        }

    }

    public boolean login(final String userName, final String passWord) {
        boolean success=false;
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder().add("dianhua",userName).add("mima",passWord).build();
            Request request = new Request.Builder().url(url+"jiaShiYuanDengLu").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
           if (resolveGson.resolveUser(responseData)) {
                success = true;
            }
        } catch (Exception e) {
            success=false;
            e.printStackTrace();
        }
        return success;
    }

    public boolean update_password(final JiaShiYuan user, final String new_pass){

                boolean success=false;

                try{
                    OkHttpClient client = new OkHttpClient();
                    Gson gson=new Gson();
                    user.setMima(new_pass);
                    String datas=gson.toJson(user);
                    RequestBody requestBody = new FormBody.Builder().add("data", datas).build();
                    Request request = new Request.Builder().url(url+"updateJiaShiYuanMiMa").post(requestBody).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    if(resolveGson.isUpdatePassSuccess(responseData).equals("true")){
                        success=true;
                    }
                }catch(Exception e){
                    success=false;
                    e.printStackTrace();
                }

                return success;

    }

    public boolean verify(User user, Driver driver){

        boolean success=false;
        try{
            OkHttpClient client=new OkHttpClient();
            Gson gson=new Gson();
            String users=gson.toJson(user);
            String drivers=gson.toJson(driver);
            RequestBody requestBody = new FormBody.Builder().add("user", users).add("driver",drivers).build();
            Request request = new Request.Builder().url("http://10.10.12.99:8080/TransportSystem/addDriver.driver").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            success=true;

            Log.e("RegisterActivity","调用方法执行！");

        }catch(Exception e){
            e.printStackTrace();
            success=false;
        }
        return success;

    }

    public Boolean uploadImage(String photoPath,String licensePath,String qualificationPath,String identificationPath,JiaShiYuan jiashiyuan){

        boolean success=false;
        try{
            Gson gson=new Gson();
            String driver=gson.toJson(jiashiyuan);
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody=new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("jiashiyuantupian",photoPath,RequestBody.create(MEDIA_TYPE_JPG,new File(photoPath)))
                    .addFormDataPart("jiashizhengtupian",licensePath,RequestBody.create(MEDIA_TYPE_JPG,new File(licensePath)))
                    .addFormDataPart("congyezigezhengtupian",qualificationPath,RequestBody.create(MEDIA_TYPE_JPG,new File(qualificationPath)))
                    .addFormDataPart("shenfenzhengtupian",identificationPath,RequestBody.create(MEDIA_TYPE_JPG,new File(identificationPath)))
                    .addFormDataPart("jiashiyuan",driver)
                    .build();
            Request request=new Request.Builder()
                    .url(url+"androidJiaShiYuanZhuCe")
                    .post(requestBody)
                    .build();
            Response response=client.newCall(request).execute();
            String responseData=response.body().string();
            if(resolveGson.isUpload(responseData).equals("true")){
                success=true;
            }

        }catch(Exception e){
            //success=false;
            e.printStackTrace();
        }

        return success;
    }

    public Boolean queryUserByPhone(String phone){
        boolean success=false;
        try{
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder().add("dianhua",phone).build();
            Request request = new Request.Builder().url(url+"androidJiaShiYuanDianHua").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            if(resolveGson.checkUserByPhone(responseData).equals("true")){
                success=true;
            }

        }catch(Exception e){
            success=false;
            e.printStackTrace();
        }
        return success;
    }

    public Boolean queryWayBill(String userId){
        boolean success=false;
        YunDan wayBill=new YunDan();
        try{
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder().add("jiashicheliang", userId).build();
            Request request = new Request.Builder().url(url+"androidYunDan").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            wayBill=resolveGson.getMyWayBill(responseData);
            success=true;

        }catch(Exception e){
            e.printStackTrace();
        }
        Log.e("RequestOkHttp","获取的运单号是："+wayBill.getDingdanhao());
        return success;

    }

    public Boolean queryWayBillIng(String userId){
        boolean success=false;
        YunDan wayBill=new YunDan();
        try{
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder().add("jiashicheliang", userId).build();
            Request request = new Request.Builder().url(url+"androidYunDanYiJieDan").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            Log.e("RequestOkHttp",responseData);
            wayBill=resolveGson.getMyWayBill(responseData);

        }catch(Exception e){
            e.printStackTrace();
        }
        Log.e("RequestOkHttp","获取的运单号是："+wayBill.getDingdanhao());
        return success;

    }

    public Boolean IsAcceptReceipt(String isAccept,String wayBillId){
        boolean success=false;
        try{
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder().add("yundanzhuangtai",isAccept).add("dingdanhao",wayBillId).build();
            Request request = new Request.Builder().url(url+"yunDanJieShou").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            if(resolveGson.isAccept(responseData).equals("true")){
                success=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return success;
    }

    public void startTransporting(String yundanzhuangtai,String wayBillId){
        try{
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder().add("yundanzhuangtai",yundanzhuangtai).add("dingdanhao",wayBillId).build();
            Request request = new Request.Builder().url(url+"androidYunShuZhong").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Boolean endTransport(String wayBillId,String yundanzhuangtai){
        boolean success=false;
        try{
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder().add("dingdanhao",wayBillId).add("yundanzhuangtai",yundanzhuangtai).build();
            Request request = new Request.Builder().url(url+"androidYunShuJieShu").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            Log.e("RequestOkHttp",responseData);
            if(resolveGson.endTransport(responseData).equals("true")){
                success=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return success;
    }

    public Boolean queryWayBillEnd(String userId){
        boolean success=false;
        try{
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder().add("jiashicheliang", userId).build();
            Request request = new Request.Builder().url(url+"androidQueryYunShuJieShu").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            Log.e("RequestOkHttp",responseData);
            resolveGson.getMyWayBill(responseData);
            if(DataSupport.findAll(YunDan.class).size()!=0){
                success=true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return success;

    }

    public Boolean HuizhiSubmit(String dingdanhao,String shifadunwei,String shishoudunwei,String licheng,String daidianfei){
        boolean success=false;
        try{
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody=new FormBody.Builder().add("dingdanhao",dingdanhao).add("shifadunwei",shifadunwei).add("shishoudunwei",shishoudunwei).add("licheng",licheng).add("daidianfei",daidianfei).build();
            Request request = new Request.Builder().url(url+"androidYunDanWanCheng").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            if(resolveGson.huizhiSubmit(responseData).equals("true")){
                success=true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return success;
    }

    public Boolean HuizhiSubmit1(String returnData){
        boolean success=false;
        try{
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody=new FormBody.Builder().add("huizhidan",returnData).build();
            Request request = new Request.Builder().url(url+"androidYunDanWanCheng").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            if(resolveGson.huizhiSubmit(responseData).equals("true")){
                success=true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return success;
    }

    public List queryAllWayBill(String userId){
        List<YunDan> lists=new ArrayList<YunDan>();
        try{

            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder().add("jiashicheliang", userId).build();
            Request request = new Request.Builder().url(url+"androidYunDanLiShi").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            Log.e("RequestOkHttp",responseData);
            lists=resolveGson.getAllWayBill(responseData);


        }catch(Exception e){
            e.printStackTrace();
        }
        return lists;

    }

    public void deleteDriver(String phone){
        try{
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder().add("dianhua", phone).build();
            Request request = new Request.Builder().url(url+"androidJiaShiYuanShanChu").post(requestBody).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
        }catch(Exception e){
            e.printStackTrace();
        }

    }




}

