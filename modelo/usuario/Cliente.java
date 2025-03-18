package modelo.usuario;
import modelo.pedido.Direccion;
import modelo.pedido.Pago;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nombre;
    private String usuario;
    private String correo;
    private String password;
    private String telefono;
    private ArrayList<Direccion> direcciones;
    private ArrayList<Pago> metodosPago;
    private ArrayList<Pedido> pedidos;

    //  Constructor completo
    public Cliente(int id, String nombre, String usuario, String correo, String password, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.direcciones = new ArrayList<>();
        this.metodosPago = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public ArrayList<Direccion> getDirecciones() { return direcciones;}
    public ArrayList<Pago> getMetodosPago() { return metodosPago; }
    public ArrayList<Pedido> getPedidos() { return pedidos; }


    // Metodos
    public boolean iniciarSesion(String usuario, String password) {
        return this.usuario.equals(usuario) && this.password.equals(password);
    }

    public void cerrarSesion() {
        System.out.println("Sesión cerrada para " + usuario);
    }

    public void editarDatos(String nuevoNombre, String nuevoCorreo, String nuevoTelefono) {
        this.nombre = nuevoNombre;
        this.correo = nuevoCorreo;
        this.telefono = nuevoTelefono;
    }

    public void eliminarCuenta(ArrayList<Cliente> clientes) {
        // Implementar lógica para eliminar cuenta
        clientes.remove(this);
    }

    public ArrayList<Pedido> consultarPedidosPasados() {
        return this.pedidos;
    }

    public ArrayList<Pedido> consultarHistorialCompras() {
        ArrayList<Pedido> historial = new ArrayList<>();
        for (Pedido p : pedidos){
            if("Completado".equals(p.getEstado())) historial.add(p);
        }
        return historial;
    }

    public ArrayList<Producto> verProductos(ArrayList<Producto> catalogo) {
        // Implementar lógica para obtener productos disponibles
        ArrayList<Producto> disponible = new ArrayList<>();
        for (Producto p : catalogo){
            if (p.getInventario() > 0){
                disponible.add(p);
            }
        }
        return disponible;
    }

    public void agregarProductoPedido(int idProducto, int cantidad, int idPedido, List<Producto> catalogo) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                for (Producto prod : catalogo) {
                    if (prod.getId() == idProducto && prod.getInventario() >= cantidad) {
                        p.getProductos().add(prod);
                        prod.actualizarInventario(prod.getInventario() - cantidad);
                    }
                }
            }
        }
    }

    public ArrayList<Etiqueta> verEtiquetas() {
        // Implementar lógica para obtener etiquetas disponibles
        return new ArrayList<>();
    }

    public List<Producto> filtrarProductosPorEtiqueta(Etiqueta etiqueta, List<Producto> catalogo) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : catalogo) {
            if (p.getEtiquetas().contains(etiqueta)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public int consultarInventarioProducto(int idProducto, List<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                return p.getInventario();
            }
        }
        return 0;
    }

    public Pedido crearPedido(List<Pedido> pedidosSistema) {
        int nuevoId = pedidosSistema.size() + 1;
        Pedido pedido = new Pedido(nuevoId, new ArrayList<>(), null, null, "Pendiente", this.id);
        this.pedidos.add(pedido);
        pedidosSistema.add(pedido);
        return pedido;
    }

    public void agregarDireccionEntrega(int idDireccion, int idPedido) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                for (Direccion d : direcciones) {
                    if (d.getId() == idDireccion) {
                        p.setDireccion(d);
                        break;
                    }
                }
            }
        }
    }

    public void agregarMetodoPago(int idPago, int idPedido) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                for (Pago curPago : metodosPago) {
                    if (curPago.getId() == idPago) {
                        p.setMetodoPago(curPago);
                        break;
                    }
                }
            }
        }
    }

    public ArrayList<Pedido> consultarEstadoPedidos() {
        // Implementar lógica para obtener estado de pedidos
        return new ArrayList<>();
    }

    public void cancelarPedido(int idPedido) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                p.setEstado("Cancelado");
            }
        }
    }

    public void agregarMetodoPagoSistema(Pago datosPago) {
        this.metodosPago.add(datosPago);
    }

    public void editarMetodoPago(int idPago, Pago nuevosDatos) {
        // Implementar lógica para actualizar método de pago
    }

    public void eliminarMetodoPago(int idPago) {
        // Implementar lógica para eliminar método de pago
    }

    public ArrayList<Pago> verMetodosPagoGuardados() {
        return this.metodosPago;
    }

    public ArrayList<Direccion> verDireccionesEntrega() {
        return this.direcciones;
    }

    public void agregarNuevaDireccion(String calle, String ciudad) {
        int nuevoId = direcciones.size() + 1;
        direcciones.add(new Direccion(nuevoId, calle, ciudad, false));
    }

    public void editarDireccion(int idDireccion, String calle, String ciudad) {
        for (Direccion d : direcciones) {
            if (d.getId() == idDireccion) {
                d.setCalle(calle);
                d.setCiudad(ciudad);
            }
        }
    }

    public void eliminarDireccion(int idDireccion) {
        for (Direccion d : direcciones){
            if(d.getId() == idDireccion){
                direcciones.remove(d);
            }
        }
    }
    @Override
    public String toString() {
        return "Id:" + id + ", Nombre:'" + nombre + "', Usuario:'" + usuario;
    }
}
