package com.shy.lunbotu.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyProvider extends ContentProvider {
    private SQLiteDatabase database;
    //创建一个 Uri 匹配器，用于区分用户使用的 uri 种类，比如区分用户到底想操作哪张表，
    //以及哪个表中的哪条数据
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int T_WOMAN = 1;
    private static final int T_MAN = 2;
    private static final int T_WOMAN_ID = 3;

    //添加匹配项
    static {
        String authority = "com.shy.lunbotu.contentProvider.MyProvider";
        /*** 参数 1：内容提供者的主机名，类似于我们隐式意图中 intent-filter 中 action，
         用于区分内容提供者，
         * 毕竟对于一个 Android 系统来讲，可能内容提供者不止一个
         * 参数 2：表名或者表名/#，其中#用于指定表中数据的 id
         * 参数 3：如果匹配成功了的返回值
         */
        sURIMatcher.addURI(authority, "t_woman", 1);
        sURIMatcher.addURI(authority, "t_man", 2);
        sURIMatcher.addURI(authority, "t_woman/#", 3);
    }

    /*** 在该内容提供者注册以后，当该应用第一次运行起来的时候该方法会被回调
     * 用来做一些初始化工作，比如初始化数据库对象
     * 如果初始化成功则返回 true，否则返回 false。
     */
    @Override
    public boolean onCreate() {
        //在 ContentProvider 中可以直接通过 getContext()方法获取 Context 对象
        MySQLiteOpenHelper sqLiteOpenHelper = new MySQLiteOpenHelper(getContext());
        //获取一个 SQLiteDatabase 对象，并声明为成员变量，方便其他方法中使用
        database = sqLiteOpenHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int match = sURIMatcher.match(uri);
        switch (match) {
            case T_MAN:
                return database.query("t_man", projection, selection, selectionArgs, null, null, null);
            case T_WOMAN:
                return database.query("t_woman", projection, selection, selectionArgs, null, null, null);
            case T_WOMAN_ID:
                //ContentUris 是解析 Uri 的一个工具类，可以将 Uri 中的 id 给解析出来
                long id = ContentUris.parseId(uri);
                return database.query("t_woman", null, "id=?",
                        new String[]{id + ""}, null, null, null);
            case UriMatcher.NO_MATCH:
                Log.d("tag", "匹配失败");
                break;
            default:
                break;
        }
        return null;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = sURIMatcher.match(uri);
        switch (match) {
            case T_WOMAN:
                database.insert("t_woman", null, values);
                break;
            case T_MAN:
                database.insert("t_man", null, values);
                break;
            case UriMatcher.NO_MATCH:
                Log.d("tag", "匹配不正确");
                break;
            default:
                break;
        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[]
            selectionArgs) {
        //获取用户传递进来的 uri 的匹配码，如果匹配不成功在，则返回 UriMatcher.NO_MATCH
        int match = sURIMatcher.match(uri);
        switch (match) {
            case T_WOMAN:
                Log.d("tag", "匹配 t_woman 表");
                return database.delete("t_woman", selection, selectionArgs);
            case T_MAN:
                Log.d("tag", "匹配 t_man 表");
                return database.delete("t_man", selection, selectionArgs);
            case UriMatcher.NO_MATCH:
                Log.d("tag", "没有匹配项");
                return 0;
            default:

                break;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String
            selection, @Nullable String[] selectionArgs) {
        int match = sURIMatcher.match(uri);
        switch (match) {
            case T_MAN:
                return database.update("t_man", values, selection, selectionArgs);
            case T_WOMAN:
                return database.update("t_woman", values, selection, selectionArgs);
            case UriMatcher.NO_MATCH:
                Log.d("tag", "匹配失败");
                break;
            default:
                break;
        }
        return 0;
    }
}
