package com.example.runtimepermissiontest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ProviderTest extends AppCompatActivity {

    private String newId;
    private String TAG= "ProviderTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_test);
        Button addButton = findViewById(R.id.add_data);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加数据
                Uri uri = Uri.parse("content://com.example.filepersistencetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name","王五的一生");
                values.put("author","王五");
                values.put("pages",2222);
                values.put("price",111);
                Uri newUri = getContentResolver().insert(uri,values);
                newId=newUri.getPathSegments().get(1);
            }
        });
        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.filepersistencetest.provider/book");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if (cursor!=null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                       double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "onClick: "+name);
                        Log.d(TAG, "onClick: "+author);
                        Log.d(TAG, "onClick: "+pages);
                        Log.d(TAG, "onClick: "+price);
                    }
                    cursor.close();
                }
            }
        });
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.filepersistencetest.provider/book"+newId);
                ContentValues values = new ContentValues();
                values.put("name","赵六的一生");
                values.put("author","赵六");
                values.put("price",240);
                getContentResolver().update(uri,values,null,null);

            }
        });
        Button deleteData =  findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.filepersistencetest.provider/book"+newId);
                getContentResolver().delete(uri,null,null  );
            }
        });
    }
}
