package modelo.pedido;
import modelo.producto.Producto;

import java.util.ArrayList;

public class Pedido {
    private int id;
    private ArrayList<Producto> productos;
    private Direccion direccion;
    private Pago metodoPago;
    private String estado;
    private int clienteId;

    public Pedido(int id, int clienteId) {
        this.id = id;
        this.productos = null;
        this.direccion = null;
        this.metodoPago = null;
        this.estado = null;
        this.clienteId = clienteId;
    }
    public Pedido(int id, ArrayList<Producto> productos, Direccion direccion, Pago metodoPago, String estado, int clienteId) {
        this.id = id;
        this.productos = productos;
        this.direccion = direccion;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.clienteId = clienteId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public ArrayList<Producto> getProductos() { return productos; }
    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
    public Pago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(Pago metodoPago) { this.metodoPago = metodoPago; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    @Override
    public String toString() {
        return "Id:" + id + ", Estado:'" + estado + "', Cliente Id:" + clienteId + "}";
    }
}