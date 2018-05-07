package com.example.juanjo.punchpower;


import android.content.Intent;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.view.MotionEvent;

import java.util.List;

import static android.app.Activity.*;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Clase ControlEscenas.
 * Clase secundaria, donde se gestiona lo que pasa en la aplicacion(control de escenas eventos touch...etc)
 *
 * @author Juanjo
 * @version 1.0
 */
public class ControlEscenas extends SurfaceView implements SurfaceHolder.Callback, SensorEventListener {
    /**
     * @param surfaceHolder permite controlar el tamaño y el formato de la superficie, editar los píxeles en la superficie y monitorear los cambios en la superficie.
     * @param context es el contexto de la aplicacion
     * @param bitmapfondo es la imagen que se abre como fondo nada mas abrir la app
     * @param anchoPantalla es el ancho de la pantalla
     * @param altoPantalla es el alto de la pantalla
     * @param hilo es un obejto que representa a la clase hilo
     * @param funcionando booleano que controla que se acabe la aplicacion
     * @param pantalla es un objeto de la clase escena que representa la pantalla
     * @param pantallaActual entero que representa la pantalla actual
     * @param detectorDeGestos objeto que representa a la clase que gestiona los gestos
     * @param esTitulo booleano que comprueba si estamos situadoz en la pantalla de inicio
     * @param SHAKE_THRESHOLD es el limite del agitado del acelerometro
     * @param SHAKE_WAIT_TIME_MS tiempo de espera para el agitado
     * @param mShakeTime tiempo de agitado
     */
    private SurfaceHolder surfaceHolder;
    private Context context;
    private Bitmap bitmapFondo;
    private int anchoPantalla = 1;
    private int altoPantalla = 1;
    private Hilo hilo;
    static boolean funcionando = false;
    Escenas pantalla;
    static int pantallaActual = 0;
    public GestureDetectorCompat detectorDeGestos;
    static boolean esTitulo = true;
    static boolean irdatos=false;
    private static final float SHAKE_THRESHOLD = 1.1f;
    private static final int SHAKE_WAIT_TIME_MS = 2500;
    private long mShakeTime = 0;

    /**
     * Clase Hilo.
     * Clase secundaria, donde se gestiona que la aplicacion funcione como un hilo
     *
     * @author Juanjo
     * @version 1.0
     */
    class Hilo extends Thread {
        /**
         * Constructor de la clase hilo
         */
        public Hilo() {


        }

        /**
         * Función que gestiona el canvas mientras funcione la app
         */
        public void run() {
            while (funcionando) {
                Canvas c = null;
                try {
                    c = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        pantalla.actualizarFisica();
                        pantalla.dibujar(c);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);

                    }
                }
            }
        }

        /**
         * Función que gestiona el tamaño del surface
         *
         * @param flag booleano que se le pasa para darle valor al funcionando
         */

        void setFuncionando(boolean flag) {
            funcionando = flag;
        }

        /**
         * Función que gestiona el tamaño del surface
         *
         * @param width  entero que representa el ancho
         * @param height entero que representa el alto
         */
        public void setSurfaceSize(int width, int height) {
            synchronized (surfaceHolder) {

            }
        }
    }

    /**
     * Función para obtener la pantalla actual
     */
    public int getPantallaActual() {
        return pantallaActual;
    }

    /**
     * Función para establecer la pantalla actual
     *
     * @param pantallaActual entero que representa la pantalla actual
     */
    public void setPantallaActual(int pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    /**
     * Constructor de la clase ControlEscenas
     *
     * @param context parametro que representa el contexto actual
     */
    public ControlEscenas(Context context) {
        super(context);
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.context = context;
        hilo = new Hilo();
        pantalla = new PantallaInicio(context);
        setFocusable(true);

        detectorDeGestos = new GestureDetectorCompat(context, new DetectorDeGestos());
    }

    /**
     * Función que gestiona lo que ocurre nada mas crearse el surface
     *
     * @param holder objeto de tipo SurfaceHolder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        hilo.setFuncionando(true);
        if (hilo.getState() == Thread.State.NEW)
            hilo.start();
        if (hilo.getState() == Thread.State.TERMINATED) {
            hilo = new Hilo();
            hilo.start();
        }
        SensorManager sm = (SensorManager) this.context.getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0) {
            sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);
        }

    }

    /**
     * Función que gestiona lo que ocurre cuando se cambia el surface
     *
     * @param holder objeto de tipo SurfaceHolder
     * @param format el nuevo formato de pixeles del surface
     * @param width  el nuevo ancho del surface
     * @param height el nuevo alto del surface
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla = width;
        altoPantalla = height;
        bitmapFondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondomenu);
        bitmapFondo = Bitmap.createScaledBitmap(bitmapFondo, anchoPantalla, altoPantalla, true);
        hilo.setSurfaceSize(width, height);

    }

    /**
     * Función que gestiona lo que ocurre antes de destruirse el surface
     *
     * @param holder objeto de tipo SurfaceHolder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hilo.setFuncionando(false);
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SensorManager sm = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        sm.unregisterListener(this);
    }

    /**
     * Función que gestiona los cambios de sensor
     *
     * @param sensorEvent evento realizado por un sensor
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {

        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if (pantallaActual == 5 || pantallaActual==1) {
                detectShake(sensorEvent);

            }


        }
    }

    /**
     * Función que gestiona el agitado del telefono
     *
     * @param event evento realizado por un sensor
     */
    private void detectShake(SensorEvent event) {
        long now = System.currentTimeMillis();

        if ((now - mShakeTime) > SHAKE_WAIT_TIME_MS) {
            mShakeTime = now;

            float gX = event.values[0] / SensorManager.GRAVITY_EARTH;
            float gY = event.values[1] / SensorManager.GRAVITY_EARTH;
            float gZ = event.values[2] / SensorManager.GRAVITY_EARTH;

            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > SHAKE_THRESHOLD) {

               /* Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.puntuacionactual) + "" + PantallaJuego.puntuacion);
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(context, sendIntent, null);
                */
//                Intent intentListado=new Intent(context,ListadoJugadores.class);
//                startActivity(context,intentListado,null);

                PantallaJuego.poderEspecialActivado=true;


            }

        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    /**
     * Función que gestiona las pantallas
     *
     * @param pantallaActual entero que representa la pantalla actual
     */
    public boolean gesCambio(int pantallaActual) {
        switch (pantallaActual) {

            case 0:
                if (pantalla == null || pantalla.getClass() != PantallaInicio.class)
                    pantalla = new PantallaInicio(context);
                esTitulo = true;

                return true;
            case 1:
                if (pantalla == null || pantalla.getClass() != PantallaJuego.class)
                    pantalla = new PantallaJuego(context);

                return true;
            case 2:
                if (pantalla == null || pantalla.getClass() != PantallaPersonajes.class)
                    pantalla = new PantallaPersonajes(context);
                return true;
            case 3:
                if (pantalla == null || pantalla.getClass() != PantallaAyuda.class)
                    pantalla = new PantallaAyuda(context);
                return true;
            case 4:
                if (pantalla == null || pantalla.getClass() != PantallaOpciones.class)
                    pantalla = new PantallaOpciones(context);
                return true;
            case 5:
                if (pantalla == null || pantalla.getClass() != PantallaRecord.class)
                    pantalla = new PantallaRecord(context);
                return true;

        }
        return false;

    }

    /**
     * Función que gestiona los toques en la pantalla
     *
     * @param event evento realizado por una interaccion del usuario con el dispositivo
     */
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (surfaceHolder) {

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (esTitulo) {
                    pantallaActual = pantalla.gestionaColisiones(event);
                    gesCambio(pantallaActual);
                    esTitulo = false;

                }
                if(irdatos){
                   gesCambio(0);
                   Intent intentdatos=new Intent(context,PantallaDatos.class);
                    startActivity(context, intentdatos, null);
                    irdatos=false;

                }
            }
            if (pantallaActual == 0) {

                esTitulo = true;
            }

            if (pantallaActual == 1) {

                detectorDeGestos.onTouchEvent(event);
            }
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (pantallaActual == 2) {
                    pantallaActual = pantalla.gestionCambioPersonaje(event);
                }


            }
            if (pantallaActual == 4) {

                if (detectorDeGestos.onTouchEvent(event)) {
                    return false;
                } else {
                    return true;
                }
            }


        }

        return true;

    }


}
