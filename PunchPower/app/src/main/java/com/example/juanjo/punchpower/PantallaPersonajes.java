package com.example.juanjo.punchpower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

/**
 * Clase PantallaPersonajes.
 * Clase secundaria, donde se gestiona la pantalla de seleccion de personajes
 *
 * @author Juanjo
 * @version 1.0
 */
public class PantallaPersonajes extends Escenas {
    /**
     * @param fondopersonajes imagen del fondo de la pantalla personajes
     * @param btnAnterior imagen que representa el boton de seleccion anterior
     * @param btnSiguiente imagen que representa el boton de seleccion siguiente
     * @param akame imagen del personaje akame
     * @param sakura imagen del personaje sakura
     * @param rBotonAnterior cuadrado de colision con la imagen de boton anterior
     * @param rBotonSiguiente cuadrado de colision con la imagen de boton siguiente
     * @param fuente objeto typeface para cambia la fuente
     * @param txtEscogerPersonaje cadena que indica que selecciones personaje
     * @param carpetafuente cadena que localiza la fuente
     * @param personajeEsSakura booleano que comprueba si el personaje es sakura
     */
    Bitmap fondopersonajes, btnAnterior, btnSiguiente, akame, sakura;
    Rect rBotonAnterior, rBotonSiguiente;
    String txtEscogerPersonaje, carpetafuente = "fonts/Avara.otf";
    Typeface fuente;
    static boolean personajeEsSakura = true;
    /**
     * Constructor de la clase PantallaPersonajes
     *
     * @param context representa el contexto de la aplicacion
     */
    public PantallaPersonajes(Context context) {
        super(context);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        alto = metrics.heightPixels;
        ancho = metrics.widthPixels;
        fondopersonajes = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondopersonajes), ancho, alto, true);
        btnAnterior = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.botonpjanterior), (int) (ancho / 5), alto / 10, true);
        btnSiguiente = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.botonpjsiguiente), (int) (ancho / 5), alto / 10, true);
        akame = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.akamenormal), (int) (ancho / 5), alto / 6, true);
        sakura = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sakuranormal), (int) (ancho / 5), alto / 6, true);
        rBotonAnterior = new Rect(ancho / 12, (int) (alto / 1.85), ancho / 12 + btnAnterior.getWidth(), (int) (alto / 1.85) + btnAnterior.getHeight());
        rBotonSiguiente = new Rect((int) (ancho / 1.4), (int) (alto / 1.85), (int) (ancho / 1.4) + btnSiguiente.getWidth(), (int) (alto / 1.85) + btnSiguiente.getHeight());
        fuente = Typeface.createFromAsset(context.getApplicationContext().getAssets(), carpetafuente);
        txtEscogerPersonaje = context.getString(R.string.escogerpj);

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
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setTextSize(ancho / 15);
        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.STROKE);

        p.setTypeface(fuente);
        c.drawBitmap(fondopersonajes, 0, 0, null);
        c.drawText(txtEscogerPersonaje, (int) (ancho / 4.5), alto / 3, p);
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.FILL);
        c.drawText(txtEscogerPersonaje, (int) (ancho / 4.5), alto / 3, p);
        c.drawBitmap(btnAnterior, rBotonAnterior.left, rBotonAnterior.top, null);
        c.drawBitmap(btnSiguiente, rBotonSiguiente.left, rBotonSiguiente.top, null);
        if (personajeEsSakura == true) {
            c.drawBitmap(sakura, (int) (ancho / 2.5), (int) (alto / 1.95), null);
        } else {
            c.drawBitmap(akame, (int) (ancho / 2.5), (int) (alto / 1.95), null);
        }

        actualizarFisica();
    }
    /**
     * Funcion que gestiona las colisiones con los botones para cambiar el personaje
     *
     *
     * @param event evento que recoge una interaccion del usuario con la pantalla
     */
    @Override
    public int gestionCambioPersonaje(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Rect pulsa = new Rect(x, y, x + 10, y + 10);
        if (pulsa.intersect(rBotonSiguiente)) {
            personajeEsSakura = false;
            return 2;
        }
        if (pulsa.intersect(rBotonAnterior)) {
            personajeEsSakura = true;
            return 2;
        }


        return 2;
    }
}
