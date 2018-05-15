package app.example.juanjo.PunchPower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;


/**
 * Clase PantallaAyuda.
 * Clase secundaria, donde se gestionan la pantalla ayuda
 *
 * @author Juanjo
 * @version 1.1
 */
public class PantallaAyuda extends Escenas {
    /**
     * @param tituloAyuda cadena que representa el titulo de esta escena
     * @param explicaAtaqueAbajo cadena que explica que tienes que hacer para atacar hacia abajo
     * @param explicaAtaqueArriba cadena que explica que tienes que hacer para atacar hacia arriba
     * @param explicaAtaqueDerecha cadena que explica que tienes que hacer para atacar hacia derecha
     * @param explicaAtaqueIzquierda cadena que explica que tienes que hacer para atacar hacia izquierda
     * @param creditos cadena que representa la cadena creditos
     * @param nombrecreditos cadena que representa a quien estan dirigidos los creditos
     * @param carpetafuente cadena que representa donde esta la fuente personalizada
     * @param comojugar cadena que titulo el como jugar
     * @param fuente objeto tipo typeface para asignar un tipo de fuente
     * @param txtAutorMusica cadena que contiene el nombre del autor de la cancion
     * @param txtMusica cadena que indica Musica:
     * @param explicaporquegolpear cadena que explica la finalidad de golpear a un enemigo
     * @param txtAutoresFondos cadena que contiene los autores de los fondos
     * @param txtAutorFuente cadena que contiene el autor de la fuente
     * @param txtFuente cadena que indica Fuente hecha por...
     * @param txtFondos cadena que indica las pantallas con fondos de libre distribucion.
     * @param txtMiAportacion cadena que contien lo que he diseñado yo graficamente.
     * @param deslizaabajo imagen que indica un deslizamiento abajo
     * @param deslizaarriba imagen que indica un deslizamiento arriba
     * @param deslizaderecha imagen que indica un deslizamiento a la derecha
     * @param deslizaizquierda imagen que indica un deslizamiento a la izquierda
     */
    Bitmap fondoayuda, deslizaDerecha, deslizaIzquiera, deslizaAbajo, deslizaArriba;
    String tituloAyuda, txtFondos, txtAutoresFondos, txtMiAportacion, txtAutorFuente, txtFuente, explicaporquegolpear, explicaAtaqueArriba, explicaAtaqueAbajo, explicaAtaqueDerecha, explicaAtaqueIzquierda, creditos, carpetafuente = "fonts/Avara.otf", comojugar, nombrecreditos, txtMusica, txtAutorMusica, txtAutorPunetazo, txtSonidoPunetazo, txtAutorPersonajes, txtPersonajesAutor;
    Typeface fuente;

    /**
     * Constructor de la clase PantallaAyuda
     *
     * @param context representa el contexto actual de la aplicacion
     */
    public PantallaAyuda(Context context) {
        super(context);
        fondoayuda = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondoayuda), ancho, alto, true);
        deslizaArriba = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.deslizaarriba), (int) (ancho / 10), alto / 15, true);
        deslizaAbajo = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.deslizaabajo), (int) (ancho / 10), alto / 15, true);
        deslizaDerecha = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.deslizaderecha), (int) (ancho / 10), alto / 15, true);
        deslizaIzquiera = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.deslizaizquierda), (int) (ancho / 10), alto / 15, true);
        tituloAyuda = context.getString(R.string.tituloAyuda);
        comojugar = context.getString(R.string.comojugar);
        explicaAtaqueArriba = context.getString(R.string.explicaAtaqueArriba);
        explicaAtaqueDerecha = context.getString(R.string.explicaAtaqueDerecha);
        explicaAtaqueIzquierda = context.getString(R.string.explicaAtaqueIzquierda);
        explicaAtaqueAbajo = context.getString(R.string.explicaAtaqueAbajo);
        explicaporquegolpear = context.getString(R.string.explicaporquegolpear);
        creditos = context.getString(R.string.creditos);
        txtMiAportacion = context.getString(R.string.miscreditos);
        nombrecreditos = "Juan José Porto Ferreiro";
        fuente = Typeface.createFromAsset(context.getApplicationContext().getAssets(), carpetafuente);
        txtMusica = context.getString(R.string.autormusica);
        txtAutorMusica = "skrjablin";
        txtAutorPunetazo = "CGEffex";
        txtSonidoPunetazo = context.getString(R.string.punchsound);
        txtAutorPersonajes = "Dazz";
        txtPersonajesAutor = context.getString(R.string.txtautorluchadores);
        txtFuente = context.getString(R.string.fuente);
        txtAutorFuente = "Raphaël Bastide";
        txtFondos = context.getString(R.string.fondos);
        txtAutoresFondos = "Park Chu El, Isabelle Schniermanni, Monika";
    }

    /**
     * Funcion que actualiza la fisica
     */
    @Override
    public void actualizarFisica() {
        super.actualizarFisica();
    }

    /**
     * Función que nos permite dibujar
     *
     * @param c representa el lienzo sobre el que se va a dibujar
     */
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(fondoayuda, 0, 0, null);
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setTextSize(ancho / 5);
        p.setTypeface(fuente);
        p.setTextAlign(Paint.Align.CENTER);
        c.drawText(tituloAyuda, (int) (ancho / 2), alto / 6, p);
        p.setTextSize(ancho / 20);
        c.drawText(comojugar, (int) (ancho / 2), (int) (alto / 3.5), p);
        p.setTextSize(ancho / 25);
        c.drawBitmap(deslizaArriba, (int) (ancho / 30), (int) (alto / 4.3), null);
        c.drawBitmap(deslizaDerecha, (int) (ancho / 6), (int) (alto / 4.3), null);
        c.drawBitmap(deslizaIzquiera, (int) (ancho / 1.4), (int) (alto / 4.3), null);
        c.drawBitmap(deslizaAbajo, (int) (ancho / 1.17), (int) (alto / 4.3), null);
        c.drawText(explicaAtaqueArriba, (int) (ancho / 2), (int) (alto / 3), p);
        p.setTextSize(ancho / 25);
        c.drawText(explicaAtaqueDerecha, (int) (ancho / 2), (int) (alto / 2.5), p);
        p.setTextSize(ancho / 25);
        c.drawText(explicaAtaqueIzquierda, (int) (ancho / 2), (int) (alto / 2.15), p);
        p.setTextSize(ancho / 25);
        c.drawText(explicaAtaqueIzquierda, (int) (ancho / 2), (int) (alto / 1.9), p);
        p.setTextSize(ancho / 26);
        c.drawText(explicaporquegolpear, (int) (ancho / 2), (int) (alto / 1.75), p);
        p.setTextSize(ancho / 20);
        c.drawText(creditos, (int) (ancho / 2), (int) (alto / 1.6), p);
        p.setTextSize(ancho / 25);
        c.drawText(txtMiAportacion + nombrecreditos, (int) (ancho / 2), (int) (alto / 1.5), p);
        p.setTextSize(ancho / 25);
        c.drawText(txtMusica + txtAutorMusica, (int) (ancho / 2), (int) (alto / 1.4), p);
        p.setTextSize(ancho / 25);
        c.drawText(txtSonidoPunetazo + txtAutorPunetazo, (int) (ancho / 2), (int) (alto / 1.3), p);
        p.setTextSize(ancho / 25);
        c.drawText(txtPersonajesAutor + txtAutorPersonajes, (int) (ancho / 2), (int) (alto / 1.2), p);
        p.setTextSize(ancho / 25);
        c.drawText(txtFondos, (int) (ancho / 2), (int) (alto / 1.145), p);
        p.setTextSize(ancho / 25);
        c.drawText(txtAutoresFondos, (int) (ancho / 2), (int) (alto / 1.08), p);
        p.setTextSize(ancho / 25);
        c.drawText(txtFuente + txtAutorFuente, (int) (ancho / 2), (int) (alto / 1.035), p);
    }

    /**
     * Función que gestiona los eventos touch de esta escena
     *
     * @param event evento que recoge una interaccion del usuario con la pantalla
     */
    @Override
    public int gestionaColisiones(MotionEvent event) {
        return super.gestionaColisiones(event);
    }
}
