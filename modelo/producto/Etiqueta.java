package modelo.producto;

public class Etiqueta {
    private int id;
    private String nombre;

    public Etiqueta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "Id:" + id + ", Nombre:'" + nombre;
    }
}