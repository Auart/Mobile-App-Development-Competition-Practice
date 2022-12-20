package edu.eschina.market.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class ShoppingDBHelper extends SQLiteOpenHelper {
    public ShoppingDBHelper(@Nullable Context context) {
        super(context, "shopping.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists shopping (_id integer primary key autoincrement not null," +
                "product_id integer not null," +
                "name varchar not null," +
                "description varchar not null," +
                "price varchar not null," +
                "pic varchar not null);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
