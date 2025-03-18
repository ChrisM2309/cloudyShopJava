package modelo.pedido;
import modelo.producto.Producto;

import java.util.ArrayList;

public class Pedido {
    // Atributos de la clase Pedido
    private int id;
    private ArrayList<Producto> productos;
    private Direccion direccion;
    private Pago metodoPago;
    private String estado;
    private int clienteId;

    //Constructor Predeterminado
    public Pedido(int id, int clienteId) {
        this.id = id;
        this.productos = null;
        this.direccion = null;
        this.metodoPago = null;
        this.estado = null;
        this.clienteId = clienteId;
    }

    //Constructor Completo
    public Pedido(int id, ArrayList<Producto> productos, Direccion direccion, Pago metodoPago, String estado, int clienteId) {
        this.id = id;
        this.productos = productos;
        this.direccion = direccion;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.clienteId = clienteId;
    }
    // Metodos Getters
    public int getId() { return id; }
    public ArrayList<Producto> getProductos() { return productos; }
    public Direccion getDireccion() { return direccion; }
    public Pago getMetodoPago() { return metodoPago; }
    public String getEstado() { return estado; }
    public int getClienteId() { return clienteId; }

    // Metodos Setter
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
    public void setMetodoPago(Pago metodoPago) { this.metodoPago = metodoPago; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public void setId(int id) { this.id = id; }

    // Metodo toString para representar el objeto Pedido como una cadena de texto
    @Override
    public String toString() {
        return "Id:" + id + ", Estado:'" + estado + "', Cliente Id:" + clienteId + "}";
    }
}