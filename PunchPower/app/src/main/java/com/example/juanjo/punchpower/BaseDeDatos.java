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
    ArrayList<Jugador> nombres = new ArrayList<>();
    Jugador j;
    public BaseDeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       // sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Record");
        //sqLiteDatabase.execSQL(sql);
    }

    public ArrayList llenar_lv() {

        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM Record order by Puntos desc";
        int  pj=1;
        String nombre;
        Cursor registros = database.rawQuery(q,null);

            if (registros.moveToFirst()) {

                do {
                    j=new Jugador(pj,registros.getString(registros.getColumnIndex("Nombre")),registros.getInt((registros.getColumnIndex("Puntos"))));
                    nombres.add(j);
                    pj++;
                  //  Log.v("aaa",""+registros.getInt(registros.getColumnIndex("ID")));
                } while (registros.moveToNext());


                registros.close();
            }
        return nombres;
    }
}