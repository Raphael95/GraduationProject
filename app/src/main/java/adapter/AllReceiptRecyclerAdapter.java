package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rapha.transpotsystem.AllReceiptActivity;
import com.example.rapha.transpotsystem.DetailReceipt;
import com.example.rapha.transpotsystem.R;

import java.util.List;

import dao.WayBill2;
import domain.YunDan;

/**
 * Created by raphael on 2017/5/19.
 */

public class AllReceiptRecyclerAdapter extends RecyclerView.Adapter<AllReceiptRecyclerAdapter.ViewHolder>{

    private List<YunDan> wayBillList=null;
    private Context context=null;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView clientName;
        TextView wayBillId;
        TextView cargoName;
        TextView start;
        TextView end;

        View wayBillView;

        public ViewHolder(View v){
            super(v);
            wayBillView=v;
            clientName=(TextView)v.findViewById(R.id.receipt_clientName);
            wayBillId=(TextView)v.findViewById(R.id.receipt_wayBillId);
            cargoName=(TextView)v.findViewById(R.id.receipt_cargoName);
            start=(TextView)v.findViewById(R.id.receipt_start);
            end=(TextView)v.findViewById(R.id.receipt_end);
        }

    }

    public AllReceiptRecyclerAdapter(List<YunDan> wayBillList,Context context){
        this.wayBillList=wayBillList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(this.context).inflate(R.layout.all_receipt,null);
        final ViewHolder holder=new ViewHolder(v);
        holder.wayBillView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                YunDan wayBill=wayBillList.get(position);
                Intent intent=new Intent(context, DetailReceipt.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("MywayBill",wayBill);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        YunDan wayBill=wayBillList.get(position);
        if(wayBill!=null){
            holder.clientName.setText(wayBill.getKehudanwei());
            holder.wayBillId.setText(wayBill.getDingdanhao());
            holder.cargoName.setText(wayBill.getHuowumingcheng());
            holder.start.setText(wayBill.getQidian());
            holder.end.setText(wayBill.getZhongdian());
        }
    }

    @Override
    public int getItemCount() {
        return wayBillList.size();
    }
}
