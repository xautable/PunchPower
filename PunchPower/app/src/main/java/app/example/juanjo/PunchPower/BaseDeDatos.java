package app.example.juanjo.PunchPower;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Clase  BaseDeDatos.
 * Clase secundaria, donde se gestiona la base de datos de records
 *
 * @author Juanjo
 * @version 1.1
 */
public class BaseDeDatos extends SQLiteOpenHelper {
    /**
     * @param sql cadena que contiene la creacion de lka base de datos.
     * @param nombres coleccion de los jugadores registrados.
     * @param j objeto de tipo jugador
     */
    String sql = "CREATE TABLE Record(ID INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT,Puntos INTEGER)";
    ArrayList<Jugador> nombres = new ArrayList<>();
    Jugador j;


    /**
     * Constructor de la clase BaseDeDatos
     */
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

    /**
     * Funcion que devuelve una coleccion con los jugadores registrados
     */
    public ArrayList llenar_lv() {

        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM Record order by Puntos desc";
        int pj = 1;
        String nombre;
        Cursor registros = database.rawQuery(q, null);

        if (registros.moveToFirst()) {

            do {
                j = new Jugador(pj, registros.getString(registros.getColumnIndex("Nombre")), registros.getInt((registros.getColumnIndex("Puntos"))));
                nombres.add(j);
                pj++;
                //  Log.v("aaa",""+registros.getInt(registros.getColumnIndex("ID")));
            } while (registros.moveToNext());


            registros.close();
        }
        return nombres;
    }
}