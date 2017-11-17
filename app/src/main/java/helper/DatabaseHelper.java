package helper;

import android.content.ContentProvider;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by raphael on 2017/4/27.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private Context mContext;

    public static final String CREATE_USER="create table User(userId integer primary key autoincrement,account text,password text,real_name text,gender integer,phone text,email text,userRank integer,deptId integer)";

    public DatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User");
        Toast.makeText(mContext,"Delete succeeded",Toast.LENGTH_SHORT).show();
    }
}
