package modelo.producto;

import java.util.ArrayList;

/**
 * Clase que representa un producto en el catálogo.
 */
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private ArrayList<Etiqueta> etiquetas;
    private int inventario;

    /**
     * Constructor que inicializa un producto con un ID.
     * @param id Identificador del producto.
     */
    public Producto(int id) {
        this.id = id;
        this.nombre = null;
        this.descripcion = null;
        this.precio = 0.0;
        this.etiquetas = new ArrayList<Etiqueta>();
        this.inventario = 0;
    }

    /**
     * Constructor que inicializa un producto con atributos básicos.
     * @param id Identificador del producto.
     * @param nombre Nombre del producto.
     * @param descripcion Descripción del producto.
     * @param precio Precio del producto.
     * @param inventario Cantidad en inventario.
     */
    public Producto(int id, String nombre, String descripcion, double precio, int inventario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.etiquetas = new ArrayList<Etiqueta>();
        this.inventario = inventario;
    }

    /**
     * Constructor que inicializa un producto con todos sus atributos.
     * @param id Identificador del producto.
     * @param nombre Nombre del producto.
     * @param descripcion Descripción del producto.
     * @param precio Precio del producto.
     * @param etiquetas Lista de etiquetas del producto.
     * @param inventario Cantidad en inventario.
     */
    public Producto(int id, String nombre, String descripcion, double precio, ArrayList<Etiqueta> etiquetas, int inventario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.etiquetas = etiquetas;
        this.inventario = inventario;
    }

    /**
     * Obtiene el ID del producto.
     * @return El ID del producto.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del producto.
     * @param id El nuevo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * @param nombre El nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     * @return La descripción del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     * @param descripcion La nueva descripción.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * @param precio El nuevo precio.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la lista de etiquetas del producto.
     * @return Lista de etiquetas.
     */
    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    /**
     * Obtiene la cantidad en inventario del producto.
     * @return La cantidad en inventario.
     */
    public int getInventario() {
        return inventario;
    }

    /**
     * Actualiza la cantidad en inventario del producto.
     * @param cantidad La nueva cantidad.
     */
    public void actualizarInventario(int cantidad) {
        this.inventario = cantidad;
    }

    /**
     * Agrega una etiqueta al producto.
     * @param etiqueta La etiqueta a agregar.
     */
    public void agregarEtiqueta(Etiqueta etiqueta) {
        if (!etiquetas.contains(etiqueta)) {
            etiquetas.add(etiqueta);
        }
    }

    /**
     * Elimina una etiqueta del producto.
     * @param etiqueta La etiqueta a eliminar.
     */
    public void eliminarEtiqueta(Etiqueta etiqueta) {
        this.etiquetas.remove(etiqueta);
    }

    /**
     * Devuelve una representación en cadena del producto.
     * @return Cadena con los detalles del producto.
     */
    @Override
    public String toString() {
        return "Id:" + id + ", Nombre:'" + nombre + "', Precio:" + precio + ", Inventario:" + inventario + "}";
    }
}