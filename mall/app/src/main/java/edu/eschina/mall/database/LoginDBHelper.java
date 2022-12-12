package edu.eschina.mall.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
@Deprecated
public class LoginDBHelper extends SQLiteOpenHelper {
    private static LoginDBHelper mLoginDBHelper;

    /**
     * 利用单例模式获取数据库帮助器的唯一实例
     *
     * @param context
     * @return
     */
    public static LoginDBHelper getInstance(Context context) {
        if (mLoginDBHelper != null) {
            mLoginDBHelper = new LoginDBHelper(context);
        }
        return mLoginDBHelper;
    }

    public LoginDBHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (`id` integer primary key autoincrement,`createTime` datatime,`mobile`  integer ,`valid` boolean,`nickName` varchar,`headpic` varchar,`auth_token` varchar);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
