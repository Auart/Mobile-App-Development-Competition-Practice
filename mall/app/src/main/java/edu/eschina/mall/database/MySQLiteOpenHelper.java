package edu.eschina.mall.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
@Deprecated
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static MySQLiteOpenHelper mMySQLiteOpenHelper;
    private SQLiteDatabase mRDB=null;
    private SQLiteDatabase mWDB=null;

    public static MySQLiteOpenHelper getInstance(Context context) {
        if (mMySQLiteOpenHelper != null) {
            mMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        }
        return mMySQLiteOpenHelper;
    }


    public MySQLiteOpenHelper(@Nullable Context context) {
        super(context, "mall.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (`id` integer primary key autoincrement,`createTime` datatime,`mobile`  integer ,`valid` boolean,`nickName` varchar,`headpic` varchar,`auth_token` varchar);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
