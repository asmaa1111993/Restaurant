package com.example.android.restaurant;

import android.provider.BaseColumns;

public class Database  {
    public static final int VERSION = 2;
    public static final String DATABASE ="user.db";
    class UserInfo implements BaseColumns {
        public static final String TABLE = "users";
        public static final String NAME = "name";
        public static final String PAEEWORD = "password";
        public static final String TYPE = "type";
        public static final String CREATE = " CREATE TABLE " + TABLE + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT NOT NULL, " +
                PAEEWORD + " TEXT NOT NULL,"  + TYPE + " INTEGER NOT NULL );";

        public static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE;

        public static final String DUMMY = " INSERT INTO " + TABLE + " (" + NAME + "," + PAEEWORD + "," + TYPE +")  VALUES " +
                "('ahmed ','500a',1), " +
                "( 'ali','an45',0), " +
                "( 'maged','jdj56',2) ";
    }

}
