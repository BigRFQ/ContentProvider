package com.example.runtimepermissiontest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;

public class MyProvider extends ContentProvider {
    public static final int TABBLE1_DIR=0;//定义一个公共的、静态的、不可改变的整型常量。
    public static final int TABBLE1_ITEM=1;
    public static final int TABBLE2_DIR=2;
    public static final int TABBLE2_ITEM=3;
    private  static UriMatcher uriMatcher;
    static{//静态块 只在程序加载时执行一次，后续无论创建多少该类，都不执行。
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.app.provider","table1",TABBLE1_DIR);
        uriMatcher.addURI("com.example.app.provider","table1/#",TABBLE1_ITEM);
        uriMatcher.addURI("com.example.app.provider","table2",TABBLE2_DIR);
        uriMatcher.addURI("com.example.app.provider","table2/#",TABBLE2_ITEM);
    }
    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs,String sortOrder) {
        switch (uriMatcher.match(uri)){
            case TABBLE1_DIR:
                //查询table1中所有数据
                break;
            case TABBLE1_ITEM:
                //查询table1中单条数据
                break;
            case TABBLE2_DIR:
                //查询table2中所有数据
                break;
            case TABBLE2_ITEM:
                //查询table2中单条数据
                break;
            default:
                    break;
        }
        return null;
    }

     @Override
    public Uri insert(Uri uri,ContentValues values) {
        return null;
    }

    @Override
    public int update(Uri uri,ContentValues values,String selection,String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABBLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
            case TABBLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table1";
            case TABBLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table2";
            case TABBLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table2";
            default:
                break;
        }
        return null;
    }
}
