package app.example.juanjo.PunchPower;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import java.util.List;

/**
 * Clase PantallaInicio.
 * Clase secundaria, donde se gestionan la pantalla menu de inicio
 *
 * @author Juanjo
 * @version 1.15
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

    Bitmap btnJugar, fondo, btnPersonajes, btnLogros, btnAyuda, btnOpciones, btnTwitter, btnFacebook;
    Rect rBotonJugar, rBotonPersonajes, rBotonLogros, rBotonAyuda, rBotonOpciones, rBotonTwitter, rBotonFacebook;


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
        btnTwitter = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.twitter), (int) (ancho / 7), alto / 16, true);
        btnFacebook = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.facebook), (int) (ancho / 7), alto / 13, true);


        rBotonJugar = new Rect(ancho / 6, (int) (alto / 2.5), ancho / 6 + btnJugar.getWidth(), (int) (alto / 2.5) + btnJugar.getHeight());
        rBotonPersonajes = new Rect(ancho / 6, (int) (alto / 1.71), ancho / 6 + btnPersonajes.getWidth(), (int) (alto / 1.71) + btnPersonajes.getHeight());
        rBotonLogros = new Rect(ancho / 6, (int) (alto / 1.26), ancho / 6 + btnLogros.getWidth(), (int) (alto / 1.26) + btnLogros.getHeight());
        rBotonAyuda = new Rect((int) (ancho / 1.57), (int) (alto / 1.71), (int) (ancho / 1.57) + btnAyuda.getWidth(), (int) (alto / 1.71) + btnAyuda.getHeight());
        rBotonOpciones = new Rect((int) (ancho / 1.57), (int) (alto / 1.26), (int) (ancho / 1.57) + btnOpciones.getWidth(), (int) (alto / 1.26) + btnOpciones.getHeight());
        rBotonTwitter = new Rect((int) (ancho / 1.2), (int) (alto / 1.09), (int) (ancho / 1.2) + btnOpciones.getWidth(), (int) (alto / 1.09) + btnOpciones.getHeight());
        rBotonFacebook = new Rect((int) (ancho / 1.5), (int) (alto / 1.1), (int) (ancho / 1.5) + btnOpciones.getWidth(), (int) (alto / 1.1) + btnOpciones.getHeight());


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
     *
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
        if (pulsa.intersect(rBotonTwitter)) {
            //Aqui en la url pondre el link de descarga de mi juego para que al tweetearlo salga y asi pues publicitarlo un poco
            String url = "http://www.twitter.com/intent/tweet?url=&text=Punch Power is the best game ever!!!";
            Intent i = new Intent(Intent.ACTION_VIEW);

            i.setData(Uri.parse(url));
            context.startActivity(i);
            return 0;
        }
        if (pulsa.intersect(rBotonFacebook)) {
            //Aqui ira el link de mi juego en la play store
            String urlToShare = "";
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
// intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no effect!
            intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

// See if official Facebook app is found
            boolean facebookAppFound = false;
            List<ResolveInfo> matches = context.getPackageManager().queryIntentActivities(intent, 0);
            for (ResolveInfo info : matches) {
                if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                    intent.setPackage(info.activityInfo.packageName);
                    facebookAppFound = true;
                    break;
                }
            }

// As fallback, launch sharer.php in a browser
            if (!facebookAppFound) {
                String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
            }

            context.startActivity(intent);


            return 0;
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
        c.drawBitmap(btnFacebook, (int) (ancho / 1.5), (int) (alto / 1.1), null);
        c.drawBitmap(btnTwitter, rBotonTwitter.left, rBotonTwitter.top, null);
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
