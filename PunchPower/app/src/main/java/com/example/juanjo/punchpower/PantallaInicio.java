package com.example.juanjo.punchpower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

/**
 * Clase PantallaInicio.
 * Clase secundaria, donde se gestionan la pantalla menu de inicio
 *
 * @author Juanjo
 * @version 1.0
 */
public class PantallaInicio extends Escenas {
    /**
     * @param btnJugar imagen para el boton jugar
     * @param fondo imagen de fondo del menu
     * @param btnPersonajes imagen que representa el boton personajes
     * @param btnLogros imagen para el boton logros
     * @param btnAyuda imagen que representa el boton ayuda
     * @param btnOpciones imagen que representa el boton opciones
     * @param rBotonJugar cuadrado de colision del boton jugar
     * @param rBotonPersonajes cuadrado de colision del boton personajes
     * @param rBotonOpciones cuadrado de colision del boton opciones
     * @param rBotonAyuda cuadrado de colision del boton ayuda
     * @param rBotonLogros cuadrado de colision del boton logros
     */

    Bitmap btnJugar, fondo, btnPersonajes, btnLogros, btnAyuda, btnOpciones;
    Rect rBotonJugar, rBotonPersonajes, rBotonLogros, rBotonAyuda, rBotonOpciones;

    /**
     * Constructor de la clase Pantalla Inicio
     *
     * @param context representa el contexto actual de la aplicacion
     */
    public PantallaInicio(Context context) {
        super(context);
        this.context = context;

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        alto = metrics.heightPixels;
        ancho = metrics.widthPixels;
        fondo = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondomenu), ancho, alto, true);
        btnJugar = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.play), (int) (ancho / 1.5), alto / 6, true);
        btnPersonajes = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.personajes), (int) (ancho / 5), alto / 10, true);
        btnLogros = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.logros), (int) (ancho / 5), alto / 10, true);
        btnAyuda = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ayuda), (int) (ancho / 5), alto / 10, true);
        btnOpciones = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.opciones), (int) (ancho / 5), alto / 10, true);


        rBotonJugar = new Rect(ancho / 6, (int) (alto / 2.5), ancho / 6 + btnJugar.getWidth(), (int) (alto / 2.5) + btnJugar.getHeight());
        rBotonPersonajes = new Rect(ancho / 6, (int) (alto / 1.71), ancho / 6 + btnPersonajes.getWidth(), (int) (alto / 1.71) + btnPersonajes.getHeight());
        rBotonLogros = new Rect(ancho / 6, (int) (alto / 1.26), ancho / 6 + btnLogros.getWidth(), (int) (alto / 1.26) + btnLogros.getHeight());
        rBotonAyuda = new Rect((int) (ancho / 1.57), (int) (alto / 1.71), (int) (ancho / 1.57) + btnAyuda.getWidth(), (int) (alto / 1.71) + btnAyuda.getHeight());
        rBotonOpciones = new Rect((int) (ancho / 1.57), (int) (alto / 1.26), (int) (ancho / 1.57) + btnOpciones.getWidth(), (int) (alto / 1.26) + btnOpciones.getHeight());

    }
    /**
     * Funcion que actualiza la fisica
     */
    @Override
    public void actualizarFisica() {
        super.actualizarFisica();
    }
    /**
     * Función que gestiona los eventos touch de esta escena
     * @param event evento que recoge una interaccion del usuario con la pantalla
     */
    @Override
    public int gestionaColisiones(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        Rect pulsa = new Rect(x, y, x + 10, y + 10);
        if (pulsa.intersect(rBotonJugar)) {
            vibracionMovil();
            return 1;
        }
        if (pulsa.intersect(rBotonPersonajes)) {
            return 2;
        }
        if (pulsa.intersect(rBotonAyuda)) {
            return 3;
        }
        if (pulsa.intersect(rBotonOpciones)) {
            return 4;
        }
        if (pulsa.intersect(rBotonLogros)) {
            return 5;
        }


        return 0;
    }
    /**
     * Función que nos permite dibujar
     *
     * @param c representa el lienzo sobre el que se va a dibujar
     */
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(fondo, 0, 0, null);
        c.drawBitmap(btnJugar, rBotonJugar.left, rBotonJugar.top, null);
        c.drawBitmap(btnPersonajes, rBotonPersonajes.left, rBotonPersonajes.top, null);
        c.drawBitmap(btnLogros, rBotonLogros.left, rBotonLogros.top, null);
        c.drawBitmap(btnAyuda, rBotonAyuda.left, rBotonAyuda.top, null);
        c.drawBitmap(btnOpciones, rBotonOpciones.left, rBotonOpciones.top, null);
    }
    /**
     * Función que gestiona la vibracion
     */
    public void vibracionMovil() {
        Vibrator mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mVibrator.vibrate(300);
    }
}
