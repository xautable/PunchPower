package app.example.juanjo.PunchPower;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;


/**
 * Clase PantallaRecord.
 * Clase secundaria, donde se gestiona la pantalla de mejor puntuacion y tu puntuacion actual
 *
 * @author Juanjo
 * @version 1.1
 */
public class PantallaRecord extends Escenas {
    /**
     * @param fondoRecord imagen de fondo para la pantalla record
     * @param fuente objeto typeface para cambia la fuente
     * @param txtRecord cadena que indica el titulo de la pantalla record
     * @param txtMejorPuntuacion cadena que contiene la mejor puntuacion y es guardada en un sharedpreferences
     * @param txtPuntuacionActual cadena que contiene tu puntuacion actual
     * @param carpetafuente cadena que localiza la fuente
     * @param editor objeto tipo sharedpreferences.editor usado para editar las preferencias
     */
    Bitmap fondoRecord, btnLista;
    Rect rbotonLista;
    Typeface fuente;
    String txtRecord, txtMejorPuntuacion, txtPuntuacionActual, carpetafuente = "fonts/Avara.otf";
    LottieAnimationView lottieGlobos, lottieLista;
    View v;
    static int mejorpuntuacion = 0, puntuacionactual;
    public static SharedPreferences.Editor editor;

    /**
     * Constructor de la clase Pantallarecord
     *
     * @param context representa el contexto de la aplicacion
     */
    public PantallaRecord(final Context context) {
        super(context);
        this.context = context;
        fondoRecord = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondorecords), ancho, alto, true);
        btnLista = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.botonlistacompleta), (int) (ancho / 3), alto / 6, true);
        rbotonLista = new Rect((int) (ancho / 1.65), (int) (alto / 1.21), (int) (ancho / 1.65) + btnLista.getWidth(), (int) (alto / 1.21) + btnLista.getHeight());
        fuente = Typeface.createFromAsset(context.getApplicationContext().getAssets(), carpetafuente);
        txtRecord = context.getString(R.string.titulorecord);
        txtMejorPuntuacion = context.getString(R.string.mejorpuntuacion);
        txtPuntuacionActual = context.getString(R.string.puntuacionactual);
        lottieGlobos = new LottieAnimationView(context);
        lottieGlobos.setAnimation("globos.json");
        lottieGlobos.layout(0, 1, ancho, alto);
        lottieGlobos.setRepeatCount(100);
        lottieGlobos.playAnimation();
        lottieLista = new LottieAnimationView(context);
        lottieLista.setAnimation("listado.json");
        lottieLista.setRepeatCount(1000);
        lottieLista.layout(0, 0, (int) (ancho / 0.65), (int) (alto / 0.55));
        lottieLista.playAnimation();


        mejorpuntuacion = preferencias.getInt("enemigos", mejorpuntuacion);

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
        c.drawBitmap(btnLista, rbotonLista.left, rbotonLista.top, null);
        lottieGlobos.draw(c);
        lottieLista.draw(c);
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
        editor = preferencias.edit();
        editor.putInt("enemigos", mejorpuntuacion);
        editor.commit();
    }

    /**
     * Funcion que gestiona la pulsacion del boton
     *
     * @param event evento producido al pulsar el boton
     */
    @Override
    public int gestionBotonLista(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Rect pulsa = new Rect(x, y, x + 10, y + 10);
        if (pulsa.intersect(rbotonLista)) {
            Log.v("escas", "Entra");
            Intent intentListado = new Intent(context, ListadoJugadores.class);
            context.startActivity(intentListado);
        }
        return 5;
    }

}
