package httpReuest;

import httpResolve.ResolveGson;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Rapha on 2017/3/30.
 */

public class RequestOkHttp {

    ResolveGson resolveGson;

    boolean success;

    public RequestOkHttp(){

    }

    public boolean login(final String userName, final String passWord){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder().add("userName",userName).add("passWord",passWord).build();
                    Request request=new Request.Builder().url("http://172.20.1.150:8080/SoftWare/json_user.json").post(requestBody).build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    if(resolveGson.resolveUser(responseData)){
                        success=true;
                    }else{
                        success=false;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        return success;
    }
}
