package com.example.juanjo.punchpower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoJugadores extends AppCompatActivity {

    ListView lv;
    ArrayList<String> nombres;
    ArrayAdapter adaptador;
    String n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_jugadores);
        lv=(ListView)findViewById(R.id.lv);
        BaseDeDatos db=new BaseDeDatos(getApplicationContext(),null,null,1);
        nombres=db.llenar_lv();
        adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,nombres);

        lv.setAdapter(adaptador);

    }
}
