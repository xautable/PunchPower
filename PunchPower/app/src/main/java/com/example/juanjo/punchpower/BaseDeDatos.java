package com.example.juanjo.punchpower;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class BaseDeDatos extends SQLiteOpenHelper {
    String sql="CREATE TABLE Record(Puntos INTEGER, Nombre TEXT)";
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

    public ArrayList llenar_lv(){
        ArrayList<String> nombres=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        String q="SELECT Nombre FROM Record";
        String nombre;
        Cursor registros=database.rawQuery(q,null);
        registros.moveToFirst();
            do{
                    nombre=registros.getString(registros.getColumnIndex("Nombre"));
                //nombres.add(registros.getString(registros.getColumnIndex("Nombre")));
                Log.v("ooo","sss"+nombre);

            }while(registros.moveToNext());


        return nombres;
    }
}
