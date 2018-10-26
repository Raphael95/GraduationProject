package fragmnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rapha.transpotsystem.HuizhiActivity;
import com.example.rapha.transpotsystem.R;

import org.litepal.crud.DataSupport;

import dao.WayBill;
import domain.YunDan;

/**
 * Created by raphael on 2017/4/28.
 */

public class EndTransportFragment extends Fragment{

    TextView clientName;
    TextView wayBillId;
    TextView cargoName;
    TextView start;
    TextView end;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.endtransport,container,false);

        clientName=(TextView)v.findViewById(R.id.huizhi_clientName);
        wayBillId=(TextView)v.findViewById(R.id.huizhi_wayBillId);
        cargoName=(TextView)v.findViewById(R.id.huizhi_cargoName);
        start=(TextView)v.findViewById(R.id.huizhi_start);
        end=(TextView)v.findViewById(R.id.huizhi_end);

        YunDan wayBill= DataSupport.findFirst(YunDan.class);
        clientName.setText(wayBill.getKehudanwei());
        wayBillId.setText(wayBill.getDingdanhao());
        cargoName.setText(wayBill.getHuowumingcheng());
        start.setText(wayBill.getQidian());
        end.setText(wayBill.getZhongdian());

        ImageView huiZhi=(ImageView)v.findViewById(R.id.fill_huizhi);
        huiZhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HuizhiActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }
}
