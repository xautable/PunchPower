package com.example.juanjo.punchpower;


import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Clase DetectorDeGestos
 * Clase secundaria, donde se gestionan los gestos
 *
 * @author Juanjo
 * @version 1.0
 */
class DetectorDeGestos extends GestureDetector.SimpleOnGestureListener {
    /**
     * @param DEBUG_TAG cadena para identificar que se produce un gesto
     */
    private static final String DEBUG_TAG = "GESTO";
    /**
     * @param SWIPE_MIN_DISTANCE Swipe min distance.
     */
    private static final int SWIPE_MIN_DISTANCE = 100;
    /**
     * @param SWIPE_MAX_OFF_PATH Swipe max off path.
     */
    private static final int SWIPE_MAX_OFF_PATH = 2500;
    /**
     * @param SWIPE_THRESHOLD_VELOCITY Swipe threshold velocity.
     */
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;


    /**
     * Funcion que gestiona el gesto Single Tap
     *
     * @param e evento que recoge un single tap
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (ControlEscenas.pantallaActual == 1) {
            if (PantallaJuego.acaba == true) {

                ControlEscenas.irdatos=true;
            }
        }
        if (ControlEscenas.pantallaActual == 4) {
            if (ControlEscenas.pantallaActual == 4) {
                if (PantallaOpciones.estadomusica == true) {
                    PantallaOpciones.estadomusica = false;
                    MainActivity.cancion.setVolume(0, 0);
                } else {
                    PantallaOpciones.estadomusica = true;
                    MainActivity.cancion.setVolume(1, 1);
                }
            }
        }
        return true;
    }

    /**
     * Funcion que gestiona el gesto DoubleTap
     *
     * @param event evento que recoge un double tap
     */
    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.i(DEBUG_TAG, "Double tap");


        return false;
    }


    /**
     * Funcion que gestiona el gesto Fling
     *
     * @param e1        evento que recoge que un dedo toca la pantalla
     * @param e2        evento que recoge que el dedo se levanta de la pantalla
     * @param velocityX float que representa la velocidad de desplazamiento sobre el eje x
     * @param velocityY float que representa la velocidad de desplazamiento sobre el eje y
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH || Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH)
                return false;

            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                Log.i(DEBUG_TAG, "Fling Izquierda" + velocityX);
                if (ControlEscenas.pantallaActual == 1) {
                    PantallaJuego.ataqueizquierda = true;
                    if (PantallaOpciones.estadosonido == true) {
                        PantallaJuego.efectos.play(PantallaJuego.sonidopunetazo, 1, 1, 1, 0, 1);
                    } else {
                        PantallaJuego.efectos.play(PantallaJuego.sonidopunetazo, 0, 0, 1, 0, 1);
                    }
                }
            } else {
            }
            if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                Log.i(DEBUG_TAG, "Fling Derecha" + velocityX);
                if (ControlEscenas.pantallaActual == 1) {
                    PantallaJuego.ataquederecha = true;
                    if (PantallaOpciones.estadosonido == true) {
                        PantallaJuego.efectos.play(PantallaJuego.sonidopunetazo, 1, 1, 1, 0, 1);
                    } else {
                        PantallaJuego.efectos.play(PantallaJuego.sonidopunetazo, 0, 0, 1, 0, 1);
                    }
                }
            }

            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.i(DEBUG_TAG, "Fling Arriba");
                if (ControlEscenas.pantallaActual == 1) {
                    PantallaJuego.ataquearriba = true;
                    if (PantallaOpciones.estadosonido == true) {
                        PantallaJuego.efectos.play(PantallaJuego.sonidopunetazo, 1, 1, 1, 0, 1);
                    } else {
                        PantallaJuego.efectos.play(PantallaJuego.sonidopunetazo, 0, 0, 1, 0, 1);
                    }
                }
            } else {
                if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.i(DEBUG_TAG, "Fling Abajo");
                    if (ControlEscenas.pantallaActual == 1) {
                        PantallaJuego.ataqueabajo = true;
                        if (PantallaOpciones.estadosonido == true) {
                            PantallaJuego.efectos.play(PantallaJuego.sonidopunetazo, 1, 1, 1, 0, 1);
                        } else {
                            PantallaJuego.efectos.play(PantallaJuego.sonidopunetazo, 0, 0, 1, 0, 1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            // nothing
        }
        return true;
    }

    /**
     * Funcion que gestiona el gesto onDown
     *
     * @param e evento que recoge que el evento onDown
     */
    @Override
    public boolean onDown(MotionEvent e) {


        return false;
    }

    /**
     * Funcion que gestiona el gesto LongPress
     *
     * @param event evento que recoge el evento onLongPress
     */
    @Override
    public void onLongPress(MotionEvent event) {
        super.onLongPress(event);
        Log.i(DEBUG_TAG, "Long press: " + event.toString());
        if (ControlEscenas.pantallaActual == 4) {
            if (PantallaOpciones.estadosonido == true)
                PantallaOpciones.estadosonido = false;
            else
                PantallaOpciones.estadosonido = true;
        }

    }


}