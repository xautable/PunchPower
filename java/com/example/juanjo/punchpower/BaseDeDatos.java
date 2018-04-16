package com.example.juanjo.punchpower;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDatos extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Records.db";

    public BaseDeDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ JugadorContract.JugadorEntrada.TABLE_NAME+" ("+ JugadorContract.JugadorEntrada._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                JugadorContract.JugadorEntrada.Puntos+" Integer NOT NULL,"+ JugadorContract.JugadorEntrada.Name+ "TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
    public long saveJugador(Jugador jugador){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                JugadorContract.JugadorEntrada.TABLE_NAME,
                null,
                jugador.toContentValues());

    }

}
