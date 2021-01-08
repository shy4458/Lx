package com.shy.lunbotu.contentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    //数据库名称
    private static final String DB_NAME = "user.db";
    //数据库版本号
    private static final int VERSION = 1;

    private MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                               int version) {
        super(context, name, factory, version);
    }

    public MySQLiteOpenHelper(Context context) {
        this(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_woman = "create table t_woman(id integer primary key, c_name varchar (10), c_phone varchar(20))";
        String sql_man = "create table t_man(id integer primary key, c_name varchar (10), c_phone varchar(20))";
        db.execSQL(sql_woman);
        db.execSQL(sql_man);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
