package com.example.myshop.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "products";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE = "image";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table products " +
                        "(name text,price text,type text,description text, image text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }

    public boolean insertProduct (String name, String price, String type, String description, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("type", type);
        contentValues.put("description", description);
        contentValues.put("image", image);
        db.insert("products", null, contentValues);
        return true;
    }



    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }


    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("products",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> array_list = new ArrayList<Product>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from products", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(new Product(
                    res.getString(res.getColumnIndex(COLUMN_NAME))
                    ,res.getString(res.getColumnIndex(COLUMN_PRICE))
                    , res.getString(res.getColumnIndex(COLUMN_TYPE))
                    , res.getString(res.getColumnIndex(COLUMN_DESCRIPTION))
                    , Uri.parse(res.getString(res.getColumnIndex(COLUMN_IMAGE)))
            ));
            res.moveToNext();
        }
        return array_list;
    }
}