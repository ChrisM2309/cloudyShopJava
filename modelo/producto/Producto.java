package modelo.producto;

import java.util.ArrayList;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private ArrayList<Etiqueta> etiquetas;
    private int inventario;

    public Producto(int id, String nombre, String descripcion, double precio, int inventario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.etiquetas = new ArrayList<Etiqueta>();
        this.inventario = inventario;
    }
    public Producto(int id, String nombre, String descripcion, double precio, ArrayList<Etiqueta> etiquetas, int inventario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.etiquetas = etiquetas;
        this.inventario = inventario;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public ArrayList<Etiqueta> getEtiquetas() { return etiquetas; }
    public int getInventario() { return inventario; }
    public void actualizarInventario(int cantidad) { this.inventario = cantidad; }


    public void agregarEtiqueta(Etiqueta etiqueta) {
        if (!etiquetas.contains(etiqueta)) {
            etiquetas.add(etiqueta);
        }
    }

    public void eliminarEtiqueta(Etiqueta etiqueta) {
        this.etiquetas.remove(etiqueta);
    }
    @Override
    public String toString() {
        return "Id:" + id + ", Nombre:'" + nombre + "', Precio:" + precio + ", Inventario:" + inventario + "}";
    }
}