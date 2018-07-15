package com.example.android.restaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context,Database.DATABASE,null, Database.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Database.UserInfo.CREATE);
        sqLiteDatabase.execSQL(Database.UserInfo.DUMMY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Database.UserInfo.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
