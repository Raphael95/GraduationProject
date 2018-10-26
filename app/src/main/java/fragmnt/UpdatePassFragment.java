package fragmnt;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.rapha.transpotsystem.R;
import com.google.gson.Gson;
import com.google.gson.internal.Excluder;

import org.litepal.crud.DataSupport;

import java.util.List;

import dao.User;
import domain.JiaShiYuan;
import httpResolve.ResolveGson;
import httpReuest.RequestOkHttp;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by raphael on 2017/4/28.
 */

public class UpdatePassFragment extends Fragment{

    FragmentManager fm;
    FragmentTransaction transaction;

    AccountFragment accountFragment;

    RequestOkHttp okHttp=new RequestOkHttp();

    Button update_pass;
    Button return_action;
    EditText pass;
    EditText pass2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.update_password,container,false);

        update_pass=(Button)v.findViewById(R.id.update_pass);
        return_action=(Button)v.findViewById(R.id.return_action);

        pass=(EditText)v.findViewById(R.id.new_password);
        pass2=(EditText)v.findViewById(R.id.new_password2);



        update_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pass.getText().toString().equals(pass2.getText().toString())){
                    pass2.setError("两次密码不相同！");
                }else if(TextUtils.isEmpty(pass.getText().toString())){
                    pass.setError("请输入新的密码！");
                    pass.setHint("不能为空");
                }else if(TextUtils.isEmpty(pass2.getText().toString())){
                    pass2.setError("请输入新的密码！");
                    pass2.setHint("不能为空");
                }else{
                    List<JiaShiYuan> list= DataSupport.where("dianhua = ? ", ResolveGson.jiashiyuan.getDianhua()).find(JiaShiYuan.class);
                    JiaShiYuan u=list.get(0);
                    Log.e("UpdatePassFragment","传输的json是 ： "+u.getJiashiyuanxingming());

                    new UpdatePasswordTask(v.getContext(),u,pass.getText().toString()).execute("");
                }


            }
        });

        return_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm=getFragmentManager();
                transaction=fm.beginTransaction();

                accountFragment=new AccountFragment();
                transaction.replace(R.id.content,accountFragment);
                transaction.commit();

            }
        });

        return v;
    }

    public class UpdatePasswordTask extends AsyncTask<String,Integer,String>{

        ProgressDialog dialog;
        private JiaShiYuan user;
        private String password;
        private Context context;

        public UpdatePasswordTask(Context context,JiaShiYuan user,String password){

                this.context=context;
                this.user=user;
                this.password=password;
                dialog=new ProgressDialog(context);         //创建等待进度条
                dialog.setMessage("修改中，请稍后");
                dialog.setCancelable(false);
                dialog.show();

        }

        @Override
        protected String doInBackground(String... params) {     //后台进程处理与服务器联系进行密码更新

            if(okHttp.update_password(user,password)){
                JiaShiYuan update_user=new JiaShiYuan();
                update_user.setMima(password);
                update_user.updateAll("dianhua = ?",user.getDianhua());
                return "success";
            }else{
                return "false";
            }

        }

        @Override
        protected void onPostExecute(String s) {            //修改后显示提示信息

            dialog.dismiss();

            if(s.equals("success")){
                final AlertDialog alertDialog=new AlertDialog.Builder(context).create();
                alertDialog.setMessage("修改成功");
                alertDialog.show();

                Window window = alertDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();

                // 设置透明度为0.3
                lp.alpha = 0.8f;
                lp.width=300;
                lp.height=180;
                window.setAttributes(lp);

                Handler handler = new Handler();           //设置修改密码成功确认提醒框自动消失
                handler.postDelayed(new Runnable() {

                    public void run() {
                        alertDialog.dismiss();
                    }
                }, 800);
            }else{

                final AlertDialog alertDialog=new AlertDialog.Builder(context).create();
                alertDialog.setMessage("修改失败");
                alertDialog.show();

                Window window = alertDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();

                // 设置透明度为0.3
                lp.alpha = 0.8f;
                lp.width=300;
                lp.height=180;
                window.setAttributes(lp);

                Handler handler = new Handler();           //设置修改密码成功确认提醒框自动消失
                handler.postDelayed(new Runnable() {

                    public void run() {
                        alertDialog.dismiss();
                    }
                }, 800);
            }

        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }


}
