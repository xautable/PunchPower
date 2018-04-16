package com.example.juanjo.punchpower;

import android.content.ContentValues;

public class Jugador {
    private int puntuacion;
    private String nombre;

    public Jugador(int puntuacion, String nombre) {
        this.puntuacion = puntuacion;
        this.nombre = nombre;
    }

    public int getPuntuacion() {

        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(JugadorContract.JugadorEntrada.Puntos, PantallaJuego.puntuacion);
        values.put(JugadorContract.JugadorEntrada.Name, PantallaDatos.nombre);
        return values;
    }
}
