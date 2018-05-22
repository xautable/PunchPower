package app.example.juanjo.PunchPower;

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

/**
 * Clase  AdaptadorListaRecord.
 * Clase secundaria, donde se rellena el listview de los records
 *
 * @author Juanjo
 * @version 1.15
 */
public class AdaptadorListaRecord extends BaseAdapter {
    /**
     * @param lista coleccion de los jugadores que existen en la base de datos.
     * @param inflater inflador de la vista
     * @param alto alto de la pantalla
     * @param ancho ancho de la pantalla
     */
    ArrayList<Jugador> lista;
    LayoutInflater inflater;
    int alto, ancho;

    /**
     * Constructor de la clase AdaptadorListaRecord
     *
     * @param context es el contexto de la aplicacion
     * @param lista   coleccion de jugadores
     */
    public AdaptadorListaRecord(Context context, ArrayList<Jugador> lista) {
        this.inflater = LayoutInflater.from(context);
        this.lista = lista;
        BaseDeDatos db = new BaseDeDatos(context, "Record", null, 1);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        alto = metrics.heightPixels;
        ancho = metrics.widthPixels;

    }

    /**
     * Funcion que devuelve el tamaño de la lista
     */
    @Override
    public int getCount() {
        return lista.size();
    }

    /**
     * Funcion que devuelve el item de la lista en la posicion pasada como parametro
     *
     * @param position numero entero que señala a una posicion de la lista
     */
    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    /**
     * Funcion que devuelve la posicion de un item
     *
     * @param position numero entero que señala a una posicion de la lista
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Funcion que devuelve la vista
     *
     * @param position numero entero que señala a una posicion de la lista
     * @param view     objeto que representa la vista
     * @param parent   objeto de tipo ViewGroup
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Contenedor contenedor = null;
        if (view == null) {
            view = inflater.inflate(R.layout.activity_adaptador_lista_record, null);
            contenedor = new Contenedor();
            contenedor.vusu = (LottieAnimationView) view.findViewById(R.id.view_user);
            contenedor.vmedal = (LottieAnimationView) view.findViewById(R.id.view_medal);
            contenedor.vtrofeo = (LottieAnimationView) view.findViewById(R.id.view_trofeo);
            contenedor.txtPosicion = (TextView) view.findViewById(R.id.txtPosicion);
            contenedor.txtNombre = (TextView) view.findViewById(R.id.txtNombre);
            contenedor.txtPuntos = (TextView) view.findViewById(R.id.txtPuntos);
            contenedor.divider = (ImageView) view.findViewById(R.id.divider);

            contenedor.vtrofeo.enableMergePathsForKitKatAndAbove(true);
            contenedor.vmedal.enableMergePathsForKitKatAndAbove(true);
            contenedor.vmedal.enableMergePathsForKitKatAndAbove(true);
            contenedor.txtPuntos.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            view.setTag(contenedor);
        } else contenedor = (Contenedor) view.getTag();

        Jugador j = (Jugador) getItem(position);
        contenedor.vusu.setAnimation("outline_user.json");
        contenedor.vmedal.setAnimation("medal.json");
        int posi = j.getId();
        if (posi == 1 || posi == 2 || posi == 3) {
            contenedor.vtrofeo.setAnimation("rewards.json");
        } else {
            contenedor.vtrofeo.setAnimation("patatas.json");
        }
        contenedor.txtPosicion.setText("Posición: " + j.getId());
        contenedor.txtNombre.setText("Nombre: " + j.getNombre());
        contenedor.txtPuntos.setText("" + j.getPuntos());
        contenedor.divider.setImageResource(R.drawable.divider);


        return view;
    }

    /**
     * Clase secundara que contiene los componentes
     */
    class Contenedor {
        /**
         * @param vusu animacion de una figura de usuario
         * @param vmedal animacion de una medalla
         * @param vtrofeo animacion  de un trofeo para los 3 primeros y de unas patatas para el resto de jugadores
         * @param divider es una imagen que sirve como separador entre los usuarios de los records
         * @param txtNombre textview que contiene el nombre
         * @param txtPosicion textview que contiene la posicion
         * @param txtPuntos textview que contiene los puntos
         */
        LottieAnimationView vusu, vmedal, vtrofeo;
        ImageView divider;
        TextView txtPosicion, txtNombre, txtPuntos;

    }
}
