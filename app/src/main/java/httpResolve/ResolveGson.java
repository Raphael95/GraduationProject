package httpResolve;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.rapha.transpotsystem.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import dao.User;
import dao.WayBill;
import dao.WayBill2;
import domain.JiaShiYuan;
import domain.YunDan;

/**
 * Created by Rapha on 2017/3/30.
 */

public class ResolveGson {

    public static User user;
    public static String checkStatus;
    public static JiaShiYuan jiashiyuan;


    public ResolveGson(){

    }



    public boolean resolveUser(String data_json){
        Log.e("ResolveGson",data_json);

        Gson gson=new Gson();
        String flag="false";
        String userJson;


        flag=new JsonParser().parse(data_json).getAsJsonArray().get(0).getAsJsonObject().get("flag").getAsString();
        JsonObject object=new JsonParser().parse(data_json).getAsJsonArray().get(0).getAsJsonObject().get("jiashiyuan").getAsJsonObject();
        userJson=object.toString();
        Log.e("ResolveGson",userJson);
        jiashiyuan=gson.fromJson(userJson,JiaShiYuan.class);
        checkStatus=jiashiyuan.getShenhezhuangtai();




        if(flag.equals("登录成功")){
                                                   //验证成功，创建用户数据库，并将用户信息保存下来

            List<JiaShiYuan> u= DataSupport.where("dianhua = ?",ResolveGson.jiashiyuan.getDianhua()).find(JiaShiYuan.class);
            if(u.size()==0){

                jiashiyuan.save();

            }
            if(u.size()!=0){

                DataSupport.deleteAll(JiaShiYuan.class,"dianhua= ? ",ResolveGson.jiashiyuan.getDianhua());
                jiashiyuan.save();
            }

            return true;
        }else{
            return false;
        }
    }

    public String isUpdatePassSuccess(String responseData){

        String flag="false";


        flag=new JsonParser().parse(responseData).getAsJsonArray().get(0).getAsJsonObject().get("flag").getAsString();

        return flag;


    }

    public String isUpload(String responseData){
        String flag="false";
        try{
            flag=new JSONObject(responseData).getString("flag");
            Log.e("ResolveGson",flag);
        }catch(JSONException e){
            e.printStackTrace();
        }
        Log.e("ResolveGson",flag);
        return flag;



    }

    public String checkUserByPhone(String responseData){

            String flag="false";
        try{
            flag=new JSONObject(responseData).getString("flag");
            Log.e("ResolveGson",flag);
        }catch(JSONException e){
            e.printStackTrace();
        }
            return flag;

    }


    public YunDan getMyWayBill(String responseData){
        Gson gson=new Gson();
        YunDan wayBill=null;
        Log.e("RequestOkHttp","执行到此步骤");


        try{
            String waybill=new JSONObject(responseData).getString("yundan");
            wayBill=gson.fromJson(waybill, YunDan.class);
        }catch(JSONException e){
            e.printStackTrace();
        }

            List<YunDan> lists=DataSupport.findAll(YunDan.class);
            if(lists.size() == 0){
                wayBill.save();
            }else{
                DataSupport.deleteAll(YunDan.class);
                wayBill.save();
            }

        return wayBill;

    }

    public String isAccept(String responseData){
        String flag="false";
        try{
            flag=new JSONObject(responseData).getString("flag");
        }catch(JSONException e){
            e.printStackTrace();
        }
        return flag;

    }


    public String endTransport(String responseData){
        String flag="false";
        try{
            flag=new JSONObject(responseData).getString("flag");
        }catch(JSONException e){
            e.printStackTrace();
        }
        return flag;
    }

    public String huizhiSubmit(String responseData){
        String flag="false";
        try{
            flag=new JSONObject(responseData).getString("flag");
        }catch(JSONException e){
            e.printStackTrace();
        }
        return flag;
    }

    public List getAllWayBill(String responseData){
        Log.e("RequestOkHttp",responseData);

        Gson gson=new Gson();
        List<YunDan> list=gson.fromJson(responseData,new TypeToken<List<YunDan>>(){}.getType());


        return list;

    }



}
