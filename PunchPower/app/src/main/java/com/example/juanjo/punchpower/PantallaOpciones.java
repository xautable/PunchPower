package com.example.juanjo.punchpower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.GestureDetectorCompat;

/**
 * Clase PantallaOpciones.
 * Clase secundaria, donde se gestiona la pantalla opciones
 *
 * @author Juanjo
 * @version 1.0
 */
public class PantallaOpciones extends Escenas {
    /**
     * @param fondoopciones imagen del fondo de la pantalla opciones
     * @param sonidoOff imagen que indica que se ha silenciado el sonido
     * @param sonidoOn imagen que indica que hay sonido
     * @param musicaoff imagen que indica que se ha silenciado la musica
     * @param musicaon imagen que indica que hay musica
     * @param rMusica cuadrado de colision de la imagen musica
     * @param rSonido cuadrado de colision del la imagen de sonido
     * @param detectorDeGestos objeto de la clase DetectorDeGestos
     * @param fuente objeto typeface para cambia la fuente
     * @param txtOpciones cadena que explica como quitar la musica
     * @param txtOpciones2 cadena que explica como quitar el sonido
     * @param carpetafuente cadena que localiza la fuente
     */
    Bitmap fondoopciones, sonidoOn, sonidoOff, musicaon, musicaoff;
    Rect rMusica, rSonido;
    static Boolean estadomusica = true, estadosonido = true;
    public GestureDetectorCompat detectorDeGestos;
    Typeface fuente;
    String txtOpciones, txtOpciones2, carpetafuente = "fonts/Avara.otf";

    /**
     * Constructor de la clase PantallaOpciones
     *
     * @param context representa el contexto de la aplicacion
     */
    public PantallaOpciones(Context context) {
        super(context);
        fondoopciones = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondoopciones), ancho, alto, true);

        musicaon = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.musicon), (int) (ancho / 3), alto / 5, true);

        musicaoff = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.musicoff), (int) (ancho / 3), alto / 5, true);

        sonidoOn = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.soundon), (int) (ancho / 3), alto / 5, true);

        sonidoOff = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.soundoff), (int) (ancho / 3), alto / 5, true);
        rMusica = new Rect(ancho / 3, (int) (alto / 3), ancho / 3 + musicaon.getWidth(), (int) (alto / 3) + musicaon.getHeight());
        rSonido = new Rect(ancho / 3, (int) (alto / 1.5), ancho / 3 + sonidoOn.getWidth(), (int) (alto / 1.5) + sonidoOn.getHeight());
        detectorDeGestos = new GestureDetectorCompat(context, new DetectorDeGestos());
        fuente = Typeface.createFromAsset(context.getApplicationContext().getAssets(), carpetafuente);
        txtOpciones = context.getString(R.string.explicacionmusica);
        txtOpciones2 = context.getString(R.string.explicacionsonido);
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
        p.setColor(Color.GREEN);
        p.setTextSize(ancho / 30);
        p.setTypeface(fuente);
        p.setTextAlign(Paint.Align.CENTER);

        c.drawBitmap(fondoopciones, 0, 0, null);
        c.drawText(txtOpciones, (int) (ancho / 2), alto / 6, p);
        c.drawText(txtOpciones2, (int) (ancho / 2), alto / 4, p);
        if (estadomusica)
            c.drawBitmap(musicaon, rMusica.left, rMusica.top, null);
        else
            c.drawBitmap(musicaoff, rMusica.left, rMusica.top, null);
        if (estadosonido)
            c.drawBitmap(sonidoOn, rSonido.left, rSonido.top, null);
        else
            c.drawBitmap(sonidoOff, rSonido.left, rSonido.top, null);
    }

}
