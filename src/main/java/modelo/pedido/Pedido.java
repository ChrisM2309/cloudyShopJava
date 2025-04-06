package modelo.pedido;
import modelo.producto.Producto;

import java.util.ArrayList;

/**
 * Clase que representa un pedido en el sistema.
 */
public class Pedido {
    // Atributos de la clase Pedido
    private int id;
    private ArrayList<Producto> productos;
    private Direccion direccion;
    private Pago metodoPago;
    private String estado;
    private int clienteId;

    /**
     * Constructor que inicializa un pedido con ID y cliente.
     * @param id Identificador del pedido.
     * @param clienteId Identificador del cliente.
     */
    public Pedido(int id, int clienteId) {
        this.id = id;
        this.productos = null;
        this.direccion = null;
        this.metodoPago = null;
        this.estado = null;
        this.clienteId = clienteId;
    }

    /**
     * Constructor que inicializa un pedido con todos sus atributos.
     * @param id Identificador del pedido.
     * @param productos Lista de productos en el pedido.
     * @param direccion Dirección de entrega.
     * @param metodoPago Método de pago.
     * @param estado Estado del pedido.
     * @param clienteId Identificador del cliente.
     */
    public Pedido(int id, ArrayList<Producto> productos, Direccion direccion, Pago metodoPago, String estado, int clienteId) {
        this.id = id;
        this.productos = productos;
        this.direccion = direccion;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.clienteId = clienteId;
    }

    /**
     * Obtiene el ID del pedido.
     * @return El ID del pedido.
     */
    public int getId() { return id; }

    /**
     * Obtiene la lista de productos del pedido.
     * @return Lista de productos.
     */
    public ArrayList<Producto> getProductos() { return productos; }

    /**
     * Obtiene la dirección del pedido.
     * @return La dirección de entrega.
     */
    public Direccion getDireccion() { return direccion; }
    /**
     * Obtiene el método de pago del pedido.
     * @return El método de pago.
     */
    public Pago getMetodoPago() { return metodoPago; }
    /**
     * Obtiene el estado del pedido.
     * @return El estado del pedido.
     */
    public String getEstado() { return estado; }
    /**
     * Obtiene el ID del cliente.
     * @return El ID del cliente.
     */
    public int getClienteId() { return clienteId; }

    // Metodos Setter
    /**
     * Establece la dirección del pedido.
     * @param direccion La nueva dirección.
     */
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
    /**
     * Establece el método de pago del pedido.
     * @param metodoPago El nuevo método de pago.
     */
    public void setMetodoPago(Pago metodoPago) { this.metodoPago = metodoPago; }
    /**
     * Establece el estado del pedido.
     * @param estado El nuevo estado.
     */
    public void setEstado(String estado) { this.estado = estado; }
    /**
     * Establece el ID del cliente.
     * @param clienteId El nuevo ID del cliente.
     */
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    /**
     * Establece el ID del pedido.
     * @param id El nuevo ID del pedido.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Devuelve una representación en cadena del pedido.
     * @return Cadena con los detalles del pedido.
     */
    @Override
    public String toString() {
        return "Id:" + id + ", Estado:'" + estado + "', Cliente Id:" + clienteId + "}";
    }
}