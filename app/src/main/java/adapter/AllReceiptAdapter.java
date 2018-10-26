package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rapha.transpotsystem.R;

import java.util.ArrayList;
import java.util.List;

import dao.WayBill;
import dao.WayBill2;

/**
 * Created by raphael on 2017/5/17.
 */

public class AllReceiptAdapter extends BaseAdapter{

    private List<WayBill2> wayBillList=null;
    private Context context=null;

    public AllReceiptAdapter(List<WayBill2> wayBillList,Context context){
        this.wayBillList=wayBillList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return wayBillList==null?0:wayBillList.size();
    }

    @Override
    public Object getItem(int position) {
        return wayBillList==null?0:wayBillList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(this.context);
        View v=layoutInflater.inflate(R.layout.all_receipt,null);

        TextView clientName=(TextView)v.findViewById(R.id.receipt_clientName);
        TextView wayBillId=(TextView)v.findViewById(R.id.receipt_wayBillId);
        TextView cargoName=(TextView)v.findViewById(R.id.receipt_cargoName);
        TextView start=(TextView)v.findViewById(R.id.receipt_start);
        TextView end=(TextView)v.findViewById(R.id.receipt_end);

        WayBill2 wayBill=(WayBill2)getItem(position);
        if(wayBill!=null){
            clientName.setText(wayBill.getClient().getClientBriefName());
            wayBillId.setText(wayBill.getWayBillId());
            cargoName.setText(wayBill.getDangerouscargo().getCargoName());
            start.setText(wayBill.getStart());
            end.setText(wayBill.getEnd());
        }

        return v;

    }
}
