package httpResolve;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import dao.User;

/**
 * Created by Rapha on 2017/3/30.
 */

public class ResolveGson {

    public ResolveGson(){

    }

    public boolean resolveUser(String data_json){
        Gson gson=new Gson();
        List<User> lists=gson.fromJson(data_json, new TypeToken<List<User>>(){}.getType());
        if(lists.get(0).getFlag().equals("true")){
            return true;
        }else{
            return false;
        }
    }

}
