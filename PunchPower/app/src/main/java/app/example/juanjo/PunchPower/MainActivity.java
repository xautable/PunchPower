package app.example.juanjo.PunchPower;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


/**
 * Clase MainActivity.
 * Clase principal, donde se gestionan todos los procesos de la aplicacion
 *
 * @author Juanjo
 * @version 1.1
 */
public class MainActivity extends Activity {
    /**
     * @param controlEscenas objeto con el que se gestionan las pantallas de la aplicacion
     * @param musicasonando booleano con ek que comprobamos si la musica esta sonando
     * @param cancion es la musica del juego
     * @param mSensorManager objeto que representa un manager de los sensores
     * @param sensorAcelerometro representa el sensor acelerometro
     */
    ControlEscenas controlEscenas;
    Boolean musicasonando = true;
    static MediaPlayer cancion;
    SensorManager mSensorManager;
    Sensor sensorAcelerometro;

    /**
     * Función que gestiona lo que ocurre cuando se abre nuestra aplicación
     *
     * @param savedInstanceState parametro que se usa para pasar informacion entre varios activities
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controlEscenas = new ControlEscenas(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        controlEscenas.setKeepScreenOn(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(controlEscenas);
        onDestroy();
        cancion = MediaPlayer.create(this, R.raw.musica);
        cancion.setLooping(true);
        cancion.start();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAcelerometro = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    /**
     * Función que gestiona lo que ocurre cuando se continua la aplicacion
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (cancion.isPlaying() == false) {
            cancion = MediaPlayer.create(this, R.raw.musica);
            cancion.start();
            cancion.setLooping(true);
            musicasonando = true;
            cancion.seekTo(cancion.getCurrentPosition());
        }
    }

    /**
     * Función que gestiona lo que ocurre cuando se para la aplicacion
     */
    @Override
    protected void onStop() {
        super.onStop();
        if (cancion != null) {
            cancion.stop();
        }
    }

    /**
     * Función que gestiona lo que ocurre cuando se destruye la aplicacion
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cancion != null)
            cancion.release();

    }

    /**
     * Función que gestiona lo que ocurre cuando se pausa la aplicacion
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (cancion != null) {
            cancion.stop();
        }


    }

    /**
     * Función que gestiona lo que ocurre cuando se pulsa el boton atras del telefono
     */
    @Override
    public void onBackPressed() {


        int pantallaAhora = controlEscenas.getPantallaActual();
        if (pantallaAhora == 0) super.onBackPressed();


        if (pantallaAhora == 1) {

            controlEscenas.setPantallaActual(pantallaAhora - 1);
            controlEscenas.gesCambio(pantallaAhora - 1);
        }
        if (pantallaAhora == 2) {
            controlEscenas.setPantallaActual(pantallaAhora - 2);
            controlEscenas.gesCambio(pantallaAhora - 2);
        }
        if (pantallaAhora == 3) {
            controlEscenas.setPantallaActual(pantallaAhora - 3);
            controlEscenas.gesCambio(pantallaAhora - 3);
        }
        if (pantallaAhora == 4) {
            controlEscenas.setPantallaActual(pantallaAhora - 4);
            controlEscenas.gesCambio(pantallaAhora - 4);
        }
        if (pantallaAhora == 5) {
            controlEscenas.setPantallaActual(pantallaAhora - 5);
            controlEscenas.gesCambio(pantallaAhora - 5);
        }

    }

}




