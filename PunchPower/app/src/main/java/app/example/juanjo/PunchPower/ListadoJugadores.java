package app.example.juanjo.PunchPower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

/**
 * Clase  ListadoJugadores.
 * Clase secundaria, donde se muestra un listado con los jugadores registrados
 *
 * @author Juanjo
 * @version 1.1
 */
public class ListadoJugadores extends AppCompatActivity {
    /**
     * @param lv es el listview que se rellenara con los datos de los records
     * @param nombres es la coleccion de jugadores
     * @param adaptador es el adaptador personalizado para el listview
     * @param lottieAnimationView view que contiene una animacion de la libreria lottie
     */
    ListView lv;
    ArrayList<Jugador> nombres;
    AdaptadorListaRecord adaptador;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_jugadores);
        lv = (ListView) findViewById(R.id.lv);
        BaseDeDatos db = new BaseDeDatos(getApplicationContext(), "Record", null, 1);
        nombres = db.llenar_lv();
        //for(int i=0;i<nombres.size();i++){

        //usuario="Posición: "+nombres.get(i).getId()+"\nNombre: "+nombres.get(i).getNombre()+"\nPuntos: "+nombres.get(i).getPuntos()+"\n";
        //lista.add(usuario);
        //}
        adaptador = new AdaptadorListaRecord(this, nombres);

        lv.setAdapter(adaptador);

    }
}
