package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rapha.transpotsystem.R;

import java.util.ArrayList;

public class AccountAdapter extends BaseAdapter {
    private ArrayList<String> mAccounts = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemClickListener monItemClickListener;
    private OnDelBtnClickListener monDelBtnClickListener;

    public AccountAdapter(Context context, ArrayList<String> items)
    {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mAccounts = items;
    }

    @Override
    public int getCount()
    {
        return mAccounts.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mAccounts.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.option_item, null);
            viewHolder.mItem = (RelativeLayout) convertView.findViewById(R.id.input_selectitem_item);
            viewHolder.mAvatar = (ImageView) convertView.findViewById(R.id.input_selectitem_avatar);
            viewHolder.mAccount = (TextView) convertView.findViewById(R.id.input_selectitem_account);
            viewHolder.mDelete = (ImageButton) convertView.findViewById(R.id.input_selectitem_delete);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == mAccounts.size() - 1)
            viewHolder.mItem.setBackgroundResource(R.drawable.popselect);
        else
            viewHolder.mItem.setBackgroundResource(R.drawable.popselect);

        viewHolder.mAccount.setText(mAccounts.get(position) + "");
        viewHolder.mAvatar.setImageResource(R.drawable.avatar);

        viewHolder.mItem.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                monItemClickListener.onItemClicked(position);
            }
        });

        viewHolder.mDelete.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                monDelBtnClickListener.onDelBtnClicked(position);
            }
        });

        return convertView;
    }

    private static class ViewHolder
    {
        RelativeLayout mItem;
        ImageView mAvatar;
        TextView mAccount;
        ImageButton mDelete;
    }

    public interface OnDelBtnClickListener
    {
        public void onDelBtnClicked(int position);
    }
    public void setOnDelBtnClickListener(OnDelBtnClickListener onDeleteBtnClickListener)
    {
        monDelBtnClickListener = onDeleteBtnClickListener;
    }

    public interface OnItemClickListener
    {
        public void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        monItemClickListener = onItemClickListener;
    }



}


