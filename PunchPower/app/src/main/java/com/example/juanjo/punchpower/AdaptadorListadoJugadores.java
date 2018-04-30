package com.example.juanjo.punchpower;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import java.util.ArrayList;

public class AdaptadorListadoJugadores extends CursorAdapter{
    ArrayList<String>nombres;

    public AdaptadorListadoJugadores(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(context);
        return inflater.inflate(R.layout.activity_adaptador_listado_jugadores,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
      //  String name=cursor.getString(cursor.getColumnIndex())
    }
}
