package com.example.juanjo.punchpower;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.SensorEvent;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;


/**
 * Clase PantallaRecord.
 * Clase secundaria, donde se gestiona la pantalla de mejor puntuacion y tu puntuacion actual
 *
 * @author Juanjo
 * @version 1.0
 */
public class PantallaRecord extends Escenas {
    /**
     * @param fondoRecord imagen de fondo para la pantalla record
     * @param fuente objeto typeface para cambia la fuente
     * @param txtRecord cadena que indica el titulo de la pantalla record
     * @param txtMejorPuntuacion cadena que contiene la mejor puntuacion y es guardada en un sharedpreferences
     * @param txtEnviarPuntuacion cadena que indica como enviar tu puntuacion
     * @param txtPuntuacionActual cadena que contiene tu puntuacion actual
     * @param carpetafuente cadena que localiza la fuente
     * @param editor objeto tipo sharedpreferences.editor usado para editar las preferencias
     */
    Bitmap fondoRecord, btnLista;
    Rect rbotonLista;
    Typeface fuente;
    String txtRecord, txtMejorPuntuacion, txtEnviarPuntuacion, txtPuntuacionActual, carpetafuente = "fonts/Avara.otf";
    static int mejorpuntuacion = 0, puntuacionactual;
    public static SharedPreferences.Editor editor;
    /**
     * Constructor de la clase Pantallarecord
     *
     * @param context representa el contexto de la aplicacion
     */
    public PantallaRecord(Context context) {
        super(context);
        fondoRecord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondorecords), ancho, alto, true);
        btnLista=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.botonlistacompleta), (int) (ancho / 7), alto / 13, true);
        rbotonLista= new Rect((int) (ancho / 1.5), (int) (alto / 1.1), (int) (ancho / 1.5) + btnLista.getWidth(), (int) (alto / 1.1) + btnLista.getHeight());
        fuente = Typeface.createFromAsset(context.getApplicationContext().getAssets(), carpetafuente);
        txtRecord = context.getString(R.string.titulorecord);
        txtMejorPuntuacion = context.getString(R.string.mejorpuntuacion);
        txtEnviarPuntuacion = context.getString(R.string.agitarparaenviar);
        txtPuntuacionActual = context.getString(R.string.puntuacionactual);
        mejorpuntuacion = preferencias.getInt("enemigos", mejorpuntuacion);
        this.context=context;
        if (mejorpuntuacion < puntuacionactual) {
            mejorpuntuacion = puntuacionactual;

        } else {
            if (mejorpuntuacion == puntuacionactual) {

            }
        }


    }
    /**
     * Funcion que actualiza la fisica
     */
    @Override
    public void actualizarFisica() {

        super.actualizarFisica();



    }
    /**
     * Funcion que nos permite dibujar sobre un lienzo
     *
     * @param c representa el lienzo sobre el que se va a dibujar
     */
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(fondoRecord, 0, 0, null);
        c.drawBitmap(btnLista,rbotonLista.left,rbotonLista.top,null);
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setTextSize(ancho / 10);
        p.setTypeface(fuente);
        p.setTextAlign(Paint.Align.CENTER);
        c.drawText(txtRecord, (int) (ancho / 2), alto / 6, p);
        p.setTextSize(ancho / 20);
        c.drawText(txtMejorPuntuacion + mejorpuntuacion, (int) (ancho / 2), alto / 3, p);
        p.setTextSize(ancho / 20);
        c.drawText(txtPuntuacionActual + puntuacionactual, (int) (ancho / 2), (int) (alto / 1.5), p);
        p.setTextSize(ancho / 20);
        c.drawText(txtEnviarPuntuacion, (int) (ancho / 2), (int) (alto / 1.2), p);
        editor = preferencias.edit();
        editor.putInt("enemigos", mejorpuntuacion);
        editor.commit();
    }

    @Override
    public int gestionBotonLista(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Rect pulsa = new Rect(x, y, x + 10, y + 10);
        if (pulsa.intersect(rbotonLista)) {
            Intent intentListado=new Intent(context,ListadoJugadores.class);
               context.startActivity(intentListado);

        }
        return 5;
    }
}
