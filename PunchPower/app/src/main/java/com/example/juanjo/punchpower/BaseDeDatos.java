package com.example.juanjo.punchpower;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class BaseDeDatos extends SQLiteOpenHelper {
    String sql = "CREATE TABLE Record(ID INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT,Puntos INTEGER)";

    public BaseDeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Record");
        sqLiteDatabase.execSQL(sql);
    }

    public ArrayList llenar_lv() {
        ArrayList<String> nombres = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM Record";
        String nombre;
        Cursor registros = database.rawQuery(q, null);

        //Log.v("ooo","sss"+registros.getColumnName(registros.getPosition()+1),null);


        try {
            if (registros.moveToFirst())
                Log.v("ooo", "sss" + registros.getCount(), null);
            do {


            } while (registros.moveToNext());


            registros.close();




        } catch (
                Exception s)

        {
            s.getMessage();
        }
        return nombres;
    }
}