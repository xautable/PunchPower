package com.example.juanjo.punchpower;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class AdaptadorListaRecord extends BaseAdapter {
    ArrayList<Jugador>lista;
    LayoutInflater inflater;
    int alto,ancho;
    public AdaptadorListaRecord(Context context,ArrayList<Jugador>lista){
        this.inflater=LayoutInflater.from(context);
        this.lista=lista;
        BaseDeDatos db=new BaseDeDatos(context,"Record",null,1);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        alto = metrics.heightPixels;
        ancho = metrics.widthPixels;

    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Contenedor contenedor=null;
        if(view==null){
            view=inflater.inflate(R.layout.activity_adaptador_lista_record,null);
            contenedor=new Contenedor();
            contenedor.vusu=(LottieAnimationView)view.findViewById(R.id.view_user);
            contenedor.vmedal=(LottieAnimationView)view.findViewById(R.id.view_medal);
            contenedor.vtrofeo=(LottieAnimationView)view.findViewById(R.id.view_trofeo);
           contenedor.txtPosicion=(TextView)view.findViewById(R.id.txtPosicion);
           contenedor.txtNombre=(TextView)view.findViewById(R.id.txtNombre);
           contenedor.txtPuntos=(TextView)view.findViewById(R.id.txtPuntos);
            contenedor.divider=(ImageView)view.findViewById(R.id.divider);

            contenedor.vtrofeo.enableMergePathsForKitKatAndAbove(true);
            contenedor.vmedal.enableMergePathsForKitKatAndAbove(true);
            contenedor.vmedal.enableMergePathsForKitKatAndAbove(true);
            view.setTag(contenedor);
        }else contenedor=(Contenedor)view.getTag();

        Jugador j=(Jugador)getItem(position);
        contenedor.vusu.setAnimation("outline_user.json");
        contenedor.vmedal.setAnimation("medal.json");
        contenedor.vtrofeo.setAnimation("rewards.json");
        contenedor.txtPosicion.setText("Posición: "+j.getId());
        contenedor.txtNombre.setText("Nombre: "+j.getNombre());
        contenedor.txtPuntos.setText("Puntos: "+j.getPuntos());
       contenedor.divider.setImageResource(R.drawable.divider);

        return view;
    }
    class Contenedor{
        LottieAnimationView vusu,vmedal,vtrofeo;
        ImageView divider;
        TextView txtPosicion, txtNombre,txtPuntos;

    }
}
