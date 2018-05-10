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
    ArrayList<Jugador> nombres;
    AdaptadorListaRecord adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_jugadores);
        lv=(ListView)findViewById(R.id.lv);
        BaseDeDatos db=new BaseDeDatos(getApplicationContext(),"Record",null,1);
        nombres=db.llenar_lv();
        //for(int i=0;i<nombres.size();i++){

            //usuario="Posición: "+nombres.get(i).getId()+"\nNombre: "+nombres.get(i).getNombre()+"\nPuntos: "+nombres.get(i).getPuntos()+"\n";
            //lista.add(usuario);
        //}
        adaptador=new AdaptadorListaRecord(this,nombres);

        lv.setAdapter(adaptador);

    }
}
