package modelo.producto;

import java.util.Objects;

/**
 * Clase que representa una etiqueta para categorizar productos.
 */
public class Etiqueta {
    private int id;
    private String nombre;

    /**
     * Constructor que inicializa una etiqueta con un ID.
     * @param id Identificador de la etiqueta.
     */
    public Etiqueta(int id) {
        this.id = id;
        this.nombre = null;
    }

    /**
     * Constructor que inicializa una etiqueta con ID y nombre.
     * @param id Identificador de la etiqueta.
     * @param nombre Nombre de la etiqueta.
     */
    public Etiqueta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el ID de la etiqueta.
     * @return El ID de la etiqueta.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la etiqueta.
     * @param id El nuevo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la etiqueta.
     * @return El nombre de la etiqueta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la etiqueta.
     * @param nombre El nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve una representación en cadena de la etiqueta.
     * @return Cadena con los detalles de la etiqueta.
     */
    @Override
    public String toString() {
        return "Id:" + id + ", Nombre:'" + nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Etiqueta etiqueta)) return false;
        return id == etiqueta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}