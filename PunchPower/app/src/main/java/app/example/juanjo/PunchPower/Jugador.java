package app.example.juanjo.PunchPower;

/**
 * Clase  Jugador.
 * Clase secundaria, donde se crea la estructura para los objetos de tipo jugador
 *
 * @author Juanjo
 * @version 1.1
 */
public class Jugador {
    /**
     * @param id entero que representa el id del jugador en la base de datos
     * @param nombre cadena que contiene el nombre del jugador
     * @param puntos entero que contiene la cantidad de puntos del jugador
     */
    private int id;
    private String nombre;
    private int puntos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Jugador(int id, String nombre, int puntos) {
        this.id = id;
        this.nombre = nombre;
        this.puntos = puntos;
    }
}
