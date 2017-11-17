package fragmnt;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rapha.transpotsystem.AllReceiptActivity;
import com.example.rapha.transpotsystem.R;

import org.litepal.crud.DataSupport;

import java.util.List;

import dao.User;
import dao.WayBill;
import dao.WayBill2;
import domain.JiaShiYuan;
import httpResolve.ResolveGson;
import httpReuest.RequestOkHttp;

/**
 * Created by raphael on 2017/4/28.
 */

public class AccountFragment extends Fragment{

    UpdatePassFragment updatePassFragment;

    FragmentManager fm;
    FragmentTransaction transaction;

    RequestOkHttp okHttp=new RequestOkHttp();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.myaccount,container,false);

        TextView userName=(TextView)v.findViewById(R.id.user_name);
        TextView phone=(TextView)v.findViewById(R.id.user_phone);
        TextView password=(TextView)v.findViewById(R.id.user_password);
        TextView check_allReceipt=(TextView)v.findViewById(R.id.check_allReceipt);

        List<JiaShiYuan> user= DataSupport.where("dianhua= ? ",ResolveGson.jiashiyuan.getDianhua()).find(JiaShiYuan.class);

        userName.setText(user.get(0).getJiashiyuanxingming());  //初始化用户信息，并显示
        phone.setText(user.get(0).getDianhua());

        password.setOnClickListener(new View.OnClickListener() { //点击密码切换到修改密码Fragment
            @Override
            public void onClick(View v) {
                fm=getFragmentManager();
                transaction=fm.beginTransaction();
                updatePassFragment=new UpdatePassFragment();
                transaction.replace(R.id.content,updatePassFragment);
                transaction.commit();
            }
        });

        check_allReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AllReceiptActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }


}
