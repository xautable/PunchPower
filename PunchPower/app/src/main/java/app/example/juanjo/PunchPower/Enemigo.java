package app.example.juanjo.PunchPower;


import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;

import java.util.Random;

/**
 * Clase Enemigo
 * Clase secundaria, donde se gestionan los enemigos
 *
 * @author Juanjo
 * @version 1.1
 */

public class Enemigo {
    /**
     * @param posicion posicion que contiene el valor x e y
     * @param imagen es la imagen del enemigo
     * @param rectangulo es el rectangulo de colision
     * @param activo booleano que comprueba si se mata o no a un enemigo
     */
    public PointF posicion;
    public Bitmap imagen;
    public Rect rectangulo = new Rect();
    boolean activo = true;

    public int getLado() {
        return lado;
    }

    public void setLado(int lado) {
        this.lado = lado;
    }

    public int lado;

    /**
     * Constructor de la clase Enemigo
     *
     * @param imagen es la imagen que muestra al enemigo
     * @param x      posicion sobre el eje x del enemigo
     * @param y      posicion sobre el eje y del enemigo
     */
    public Enemigo(Bitmap imagen, float x, float y, int lado) {
        this.imagen = imagen;
        this.posicion = new PointF(x, y);
        this.setRectangulos();
        this.lado = lado;
    }

    /**
     * Funcion que asigna los rectangulos a los enemigos
     */
    public void setRectangulos() {
        int anchoEnemigo = imagen.getWidth();
        int altoEnemigo = imagen.getHeight();
        float x = posicion.x;
        float y = posicion.y;
        rectangulo.set((int) (posicion.x), (int) (posicion.y), (int) (posicion.x) + imagen.getWidth(), (int) (posicion.y) + imagen.getHeight());


    }

    /**
     * Funcion que establece el movimiento de un enemigo en una pantalla definida por alto y ancho y cierta velocidad
     *
     * @param alto      alto de la pantalla
     * @param ancho     ancho de la pantalla
     * @param velocidad velocidad del movimiento
     */
    public void moverEnemigo(int alto, int ancho, int velocidad) {

        if (lado == 0) {
            posicion.y += velocidad;
            if (posicion.y > alto / 2.5) {
                posicion.y = alto * 0;

            }
        }
        if (lado == 1) {
            posicion.x += velocidad;
            if (posicion.x > ancho / 2.5) {
                posicion.x = ancho * 0;

            }
        }

        if (lado == 2) {
            posicion.y -= velocidad;
            if (posicion.y < alto / 2.5) {
                posicion.y = alto;

            }
        }
        if (lado == 3) {
            posicion.x -= velocidad;
            if (posicion.x < ancho / 2.5) {
                posicion.x = ancho;

            }
        }
        this.setRectangulos();
    }

    /**
     * Getter del booleano is activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Setter del booleano is activo
     *
     * @param activo booleano con el que se le da valor a la propiedad activo del enemigo
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}