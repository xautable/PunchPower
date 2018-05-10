package com.example.juanjo.punchpower;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.hardware.SensorEvent;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

/**
 * Clase Enemigo
 * Clase secundaria, de la que heredan las pantallas
 *
 * @author Juanjo
 * @version 1.0
 */
public class Escenas {
    /**
     * @param alto alto de la pantalla
     * @param ancho ancho de la pantalla
     * @param context contexto de la escena
     * @param preferencias objeto que representa un sharedpreferences
     */
    static int alto, ancho;
     Context context=null;
    public static SharedPreferences preferencias;

    /**
     * Constructor de la clase escenas
     *
     * @param context representa el contexto actual de la aplicacion
     */
    public Escenas(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        alto = metrics.heightPixels;
        ancho = metrics.widthPixels;
        preferencias = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);

    }

    /**
     * Funcion que actualiza la fisica de la app
     */
    public void actualizarFisica() {

    }

    /**
     * Funcion que nos permite dibujar sobre un lienzo
     *
     * @param c representa el lienzo sobre el que se va a dibujar
     */
    public void dibujar(Canvas c) {
        try {
        } catch (Exception e) {
        }
    }

    /**
     * Funcion que gestiona los eventos touch
     *
     * @param event evento que recoge una interaccion del usuario con la pantalla
     */
    public int gestionaColisiones(MotionEvent event) {
        return 0;
    }

    /**
     * Funcion que gestiona los touch de los botones de la pantalla personajes
     *
     * @param event evento que recoge una interaccion del usuario con la pantalla
     */
    public int gestionCambioPersonaje(MotionEvent event) {
        return 0;
    }

    /**
     * Funcion que  gestiona los eventos de sensor de la escena record
     *
     * @param event evento que recoge una accion realizada sobre un sensor del dispositivo
     */
    public int onSensorChanged(SensorEvent event) {
        return 0;
    }

    public int gestionaColDatos(MotionEvent event) {
        return 0;
    }
    public int gestionBotonLista(MotionEvent event) {
        return 0;
    }
}
