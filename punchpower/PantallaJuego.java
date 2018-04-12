package com.example.juanjo.punchpower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;

import java.util.Random;

/**
 * Clase PantallaJuego.
 * Clase secundaria, donde se gestiona la pantalla juego
 *
 * @author Juanjo
 * @version 1.0
 */
public class PantallaJuego extends Escenas {
    /**
     * @param fondojuego imagen del fondo de esta pantalla
     * @param generador generador de numeros enteros aleatorios
     * @param malos imagen del enemigo
     * @param sakura imagen del personaje sakura
     * @param sakuragolpeizquierda imagen de sakura atacando a la izquierda
     * @param sakuragolpeabajo imagen de sakura atacando abajo
     * @param sakuragolpearriba imagen de sakura golpeando arriba
     * @param sakuragolpederecha imagen de sakura golpeando derecha
     * @param akame imagen del personaje akame
     * @param akamegolpeizquierda imagen de akame atacando a la izquierda
     * @param akamegolpeabajo imagen de akame atacando abajo
     * @param akamegolpearriba imagen de akame golpeando arriba
     * @param akamegolpederecha imagen de akame golpeando derecha
     * @param cuadradoHeroe cuadrado de colision del personaje estando normal
     * @param cuadradoHeroeAbajo cuadrado de colision del heroe atacando abajo
     * @param cuadradoHeroeArriba cuadrado de colision del heroe atacando arriba
     * @param cuadradoHeroeDerecha cuadrado de colision del heroe atacando a la derecha
     * @param cuadradoHeroeIzquierda cuadrado de colision del heroe atacando a la izquierda
     * @param ataquederecha booleano que comprueba si deslizas hacia la derecha para hacer la animacion de atacar
     * @param ataqueabajo booleano que comprueba si deslizas hacia abajo para hacer la animacion de atacar
     * @param ataquearriba booleano que comprueba si deslizas hacia arriba para hacer la animacion de atacar
     * @param ataqueizquierda booleano que comprueba si deslizas hacia la izquierda para hacer la animacion de atacar
     * @param detectorDeGestos objeto que representa a la clase detector de gestos
     * @param malo objeto de la clase enemigo
     * @param efectos es un objeto de la clase soundpool
     * @param sonidopunetazo entero para indicar el puñetazo
     * @param maxSonidosSimultaneos entero que limita la cantidad de sonidos simultaneos
     * @param carpetafuente cadena que indica el directorio de la fuente
     * @param fuente objeto tipo typeface para cambiar la fuente
     * @param tiempoCambio entero que indica el tiempo que esta dibujada la imagen del puñetazo
     * @param tickCambio tiempo del tick de cambio
     * @param lado entero que indica el lado por donde va a aparecer el enemigo
     * @param random generador de enteros aleatorios
     * @param puntuacion cantidad de enemigos eliminados
     * @param acaba booleano que indica si se acaba el juego
     * @param txtLose cadena que indica que pierdes
     */
    Bitmap fondojuego;
    Random generador = new Random();
    Bitmap maloi;
    Bitmap sakura, sakuragolpederecha, sakuragolpeizquierda, sakuragolpeabajo, sakuragolpearriba, akame, akamegolpearriba, akamegolpeabajo, akamegolpederecha, akamegolpeizquierda;
    Rect cuadradoHeroe, cuadradoHeroeIzquierda, cuadradoHeroeDerecha, cuadradoHeroeArriba, cuadradoHeroeAbajo;
    static boolean ataquederecha = false;
    static boolean ataqueizquierda = false;
    static boolean ataquearriba = false;
    static boolean ataqueabajo = false;
    public GestureDetectorCompat detectorDeGestos;
    Enemigo malo;
    static SoundPool efectos;
    static public int sonidopunetazo;
    final private int maxSonidosSimultaneos = 10;
    String carpetafuente = "fonts/Avara.otf";
    Typeface fuente;
    int tiempoCambio = 1000;
    long tickCambio;
    static int lado;
    Random random;
    static int puntuacion;
    static boolean acaba;
    String txtLose;
    /**
     * Constructor de la clase pantalla juego
     *
     * @param context representa el contexto de la aplicacion
     */
    public PantallaJuego(Context context) {
        super(context);
        this.context = context;
        acaba = false;
        puntuacion = 0;
        fuente = Typeface.createFromAsset(context.getApplicationContext().getAssets(), carpetafuente);
        tickCambio = System.currentTimeMillis();
        txtLose = context.getString(R.string.lose);
        int resultado = generador.nextInt(2);
        sakura = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sakuranormal), (int) (ancho / 10), alto / 10, true);
        sakuragolpederecha = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sakuragolpederecha), (int) (ancho / 10), alto / 10, true);
        sakuragolpeizquierda = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sakuragolpeizquierda), (int) (ancho / 10), alto / 10, true);
        sakuragolpeabajo = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sakuragolpeabajo), (int) (ancho / 10), alto / 10, true);
        sakuragolpearriba = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.sakuragolpearriba), (int) (ancho / 10), alto / 10, true);

        akame = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.akamenormal), (int) (ancho / 10), alto / 10, true);
        akamegolpederecha = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.akamegolpederecha), (int) (ancho / 10), alto / 10, true);
        akamegolpeizquierda = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.akamegolpeizquierda), (int) (ancho / 10), alto / 10, true);
        akamegolpeabajo = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.akamegolpeabajo), (int) (ancho / 10), alto / 10, true);
        akamegolpearriba = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.akamegolpearriba), (int) (ancho / 10), alto / 10, true);


        cuadradoHeroe = new Rect((int) (ancho / 2.2), (int) (alto / 2.5), (int) (ancho / 2.2) + sakura.getWidth(), (int) (alto / 2.5) + sakura.getHeight());


        cuadradoHeroeIzquierda = new Rect((int) (ancho / 3.5), (int) (alto / 2.3), (int) (ancho / 2.2) + sakura.getWidth(), (int) (alto / 2.7) + sakura.getHeight());
        cuadradoHeroeDerecha = new Rect((int) (ancho / 2.2), (int) (alto / 2.3), (int) (ancho / 1.5) + sakura.getWidth(), (int) (alto / 2.7) + sakura.getHeight());
        cuadradoHeroeArriba = new Rect((int) (ancho / 2.2), (int) (alto / 2.9), (int) (ancho / 2.2) + sakura.getWidth(), (int) (alto / 2.7) + sakura.getHeight());
        cuadradoHeroeAbajo = new Rect((int) (ancho / 2.2), (int) (alto / 2.3), (int) (ancho / 2.2) + sakura.getWidth(), (int) (alto / 2) + sakura.getHeight());

        if (resultado == 0)
            fondojuego = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondojuego1), ancho, alto, true);
        if (resultado == 1)
            fondojuego = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fondojuego2), ancho, alto, true);

        detectorDeGestos = new GestureDetectorCompat(context, new DetectorDeGestos());


        if ((android.os.Build.VERSION.SDK_INT) >= 21) {
            SoundPool.Builder spb = new SoundPool.Builder();
            spb.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build());
            spb.setMaxStreams(maxSonidosSimultaneos);
            this.efectos = spb.build();
        } else {
            this.efectos = new SoundPool(maxSonidosSimultaneos, AudioManager.STREAM_MUSIC, 0);
        }
        sonidopunetazo = efectos.load(context, R.raw.punch, 1);

        crea();

    }
    /**
     * Función que gestiona la creacion de los enemigos
     */
    public void crea() {
        random = new Random();
        lado = random.nextInt(4);

        if (lado == 0) {
            maloi = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.maloabajo), (int) (ancho / 10), alto / 10, true);
            malo = new Enemigo(maloi, (int) (ancho / 2.2), (int) (alto / 2.5));

        }
        if (lado == 1) {
            maloi = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.maloderecha), (int) (ancho / 10), alto / 10, true);
            malo = new Enemigo(maloi, ancho / 2, (int) (alto / 2.65));
        }
        if (lado == 2) {
            maloi = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.maloarriba), (int) (ancho / 10), alto / 10, true);
            malo = new Enemigo(maloi, (int) (ancho / 2.2), (int) (alto / 1.15));
        }
        if (lado == 3) {
            maloi = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.maloizq), (int) (ancho / 10), alto / 10, true);
            malo = new Enemigo(maloi, (int) (ancho / 1), (int) (alto / 2.65));
        }


    }

    /**
     * Función que actualiza la fisica
     */
    @Override
    public void actualizarFisica() {
        super.actualizarFisica();

        if (lado == 0)
            malo.moverEnemigo(alto, ancho, 4);
        if (lado == 1)
            malo.moverEnemigo(alto, ancho, 2);
        if (lado == 2)
            malo.moverEnemigo(alto, ancho, 4);
        if (lado == 3)
            malo.moverEnemigo(alto, ancho, 2);

        if (malo.isActivo() && cuadradoHeroeArriba.intersect(malo.rectangulo) && ataquearriba == true) {
            Log.v("aaa", "Colisiona Arriba");
            malo.setActivo(false);
            puntuacion++;
        }
        if (malo.isActivo() && cuadradoHeroeAbajo.intersect(malo.rectangulo) && ataqueabajo == true) {
            Log.v("aaa", "Colisiona Abajo");
            malo.setActivo(false);
            puntuacion++;
        }
        if (malo.isActivo() && cuadradoHeroeIzquierda.intersect(malo.rectangulo) && ataqueizquierda == true) {
            Log.v("aaa", "Colisiona Izquierda");
            malo.setActivo(false);
            puntuacion++;
        }
        if (malo.isActivo() && cuadradoHeroeDerecha.intersect(malo.rectangulo) && ataquederecha == true) {
            Log.v("aaa", "Colisiona Derecha");
            malo.setActivo(false);
            puntuacion++;
        }
        if (malo.isActivo() && cuadradoHeroe.intersect(malo.rectangulo)) {
            Log.v("aaa", "Fin");
            malo.setActivo(false);
            PantallaRecord.puntuacionactual = puntuacion;
            acaba = true;
        }

        if (!malo.isActivo() && acaba == false) crea();


    }
    /**
     * Función que nos permite dibujar
     *
     * @param c representa el lienzo sobre el que se va a dibujar
     */
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(fondojuego, 0, 0, null);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setTextSize(ancho / 10);
        p.setTypeface(fuente);
        c.drawText(""+puntuacion,(int)(ancho/1.55),alto/9,p);

        if (ataquederecha == false && ataqueizquierda == false && ataqueabajo == false && ataquearriba == false) {
            if (PantallaPersonajes.personajeEsSakura == true)
                c.drawBitmap(sakura, cuadradoHeroe.left, (int) (cuadradoHeroe.top), null);
            else
                c.drawBitmap(akame, cuadradoHeroe.left, cuadradoHeroe.top, null);
        }
        if (acaba == true) {
            p.setTextAlign(Paint.Align.CENTER);
            c.drawText(txtLose, (int) (ancho / 2), alto / 6, p);
        } else {
            if (malo.isActivo()) {
                if (lado == 0) {
                    c.drawBitmap(malo.imagen, malo.posicion.x, malo.posicion.y, null);
                }
                if (lado == 1) {
                    c.drawBitmap(malo.imagen, malo.posicion.x, malo.posicion.y, null);

                }
                if (lado == 2) {
                    c.drawBitmap(malo.imagen, malo.posicion.x, malo.posicion.y, null);
                }
                if (lado == 3) {
                    c.drawBitmap(malo.imagen, malo.posicion.x, malo.posicion.y, null);
                }
            }


            if (ataquederecha == true) {
                if (PantallaPersonajes.personajeEsSakura == true)
                    c.drawBitmap(sakuragolpederecha, cuadradoHeroe.left, cuadradoHeroe.top, null);
                else
                    c.drawBitmap(akamegolpederecha, cuadradoHeroe.left, cuadradoHeroe.top, null);
                if (System.currentTimeMillis() - tickCambio > tiempoCambio) {
                    tickCambio = System.currentTimeMillis();
                    ataquederecha = false;
                }


            }

            if (ataqueizquierda == true) {
                if (PantallaPersonajes.personajeEsSakura == true)
                    c.drawBitmap(sakuragolpeizquierda, cuadradoHeroe.left, cuadradoHeroe.top, null);
                else
                    c.drawBitmap(akamegolpeizquierda, cuadradoHeroe.left, cuadradoHeroe.top, null);
                if (System.currentTimeMillis() - tickCambio > tiempoCambio) {
                    tickCambio = System.currentTimeMillis();
                    ataqueizquierda = false;
                }

            }
            if (ataqueabajo == true) {
                if (PantallaPersonajes.personajeEsSakura == true)
                    c.drawBitmap(sakuragolpeabajo, cuadradoHeroe.left, cuadradoHeroe.top, null);
                else
                    c.drawBitmap(akamegolpeabajo, cuadradoHeroe.left, cuadradoHeroe.top, null);
                if (System.currentTimeMillis() - tickCambio > tiempoCambio) {
                    tickCambio = System.currentTimeMillis();
                    ataqueabajo = false;
                }


            }
            if (ataquearriba == true) {
                if (PantallaPersonajes.personajeEsSakura == true)
                    c.drawBitmap(sakuragolpearriba, cuadradoHeroe.left, cuadradoHeroe.top, null);
                else
                    c.drawBitmap(akamegolpearriba, cuadradoHeroe.left, cuadradoHeroe.top, null);
                if (System.currentTimeMillis() - tickCambio > tiempoCambio) {
                    tickCambio = System.currentTimeMillis();
                    ataquearriba = false;
                }


            }
        }


    }

}
