package modelo.usuario;
import modelo.pedido.Direccion;
import modelo.pedido.Pago;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    //  Variables a utilizar, de tipo privado
    private int id;
    private String nombre;
    private String usuario;
    private String correo;
    private String password;
    private String telefono;
    private ArrayList<Direccion> direcciones;
    private ArrayList<Pago> metodosPago;
    private ArrayList<Pedido> pedidos;

    //  Constructor Predeterminado
    public Cliente(int id) {
        this.id = id;
        this.nombre = null;
        this.usuario = null;
        this.correo = null;
        this.password = null;
        this.telefono = null;
        this.direcciones = new ArrayList<>();
        this.metodosPago = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

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

    // Metodos Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUsuario() { return usuario; }
    public String getCorreo() { return correo; }
    public String getPassword() { return password; }
    public String getTelefono() { return telefono; }
    public ArrayList<Direccion> getDirecciones() { return direcciones;}
    public ArrayList<Pago> getMetodosPago() { return metodosPago; }
    public ArrayList<Pedido> getPedidos() { return pedidos; }

    // Metodos Setter
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setPassword(String password) { this.password = password; }
    public void setTelefono(String telefono) { this.telefono = telefono; }


    // Metodos

    // Verifica las credenciales del cliente para iniciar sesión
    public boolean iniciarSesion(String usuario, String password) {
        return this.usuario.equals(usuario) && this.password.equals(password);
    }

    // Cierra la sesión del cliente y muestra un mensaje
    public void cerrarSesion() {
        System.out.println("Sesión cerrada para " + usuario);
    }

    // Actualiza los datos personales del cliente
    public void editarDatos(String nuevoNombre, String nuevoCorreo, String nuevoTelefono) {
        this.nombre = nuevoNombre;
        this.correo = nuevoCorreo;
        this.telefono = nuevoTelefono;
    }

    // Elimina la cuenta del cliente de la lista proporcionada
    public void eliminarCuenta(ArrayList<Cliente> clientes) {
        clientes.remove(this);
    }

    // Devuelve la lista completa de pedidos pasados del cliente
    public ArrayList<Pedido> consultarPedidosPasados() {
        return this.pedidos;
    }

    // Filtra y devuelve los pedidos completados del cliente
    public ArrayList<Pedido> consultarHistorialCompras() {
        ArrayList<Pedido> historial = new ArrayList<>();
        for (Pedido p : pedidos){
            if("Completado".equals(p.getEstado())) historial.add(p);
        }
        return historial;
    }

    // Devuelve una lista de productos disponibles (con inventario mayor a 0)
    public ArrayList<Producto> verProductos(ArrayList<Producto> catalogo) {
        ArrayList<Producto> disponible = new ArrayList<>();
        for (Producto p : catalogo){
            if (p.getInventario() > 0){
                disponible.add(p);
            }
        }
        return disponible;
    }

    // Agrega un producto a un pedido existente y actualiza el inventario
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

    //  Permite buscar productos según sus etiquetas características
    public List<Producto> filtrarProductosPorEtiqueta(Etiqueta etiqueta, List<Producto> catalogo) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : catalogo) {
            if (p.getEtiquetas().contains(etiqueta)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    //  Consultar la disponibilidad de un producto
    public int consultarInventarioProducto(int idProducto, List<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                return p.getInventario();
            }
        }
        return 0;
    }

    //  Crear un nuevo pedido
    public Pedido crearPedido(List<Pedido> pedidosSistema) {
        int nuevoId = pedidosSistema.size() + 1;
        Pedido pedido = new Pedido(nuevoId, new ArrayList<>(), null, null, "Pendiente", this.id);
        this.pedidos.add(pedido);
        pedidosSistema.add(pedido);
        return pedido;
    }

    //  Definir la dirección en la que se entregará un pedido
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

    //  Agregar el método de pago que se utilizará
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

    //  Consultar el estado de los pedidos
    public ArrayList<Pedido> consultarEstadoPedidos() {

        return getPedidos();
    }

    //  Cancelar un pedido realizado
    public void cancelarPedido(int idPedido) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                p.setEstado("Cancelado");
            }
        }
    }

    //  Agregar el método de pago al sistema
    public void agregarMetodoPagoSistema(Pago datosPago) {
        metodosPago.add(datosPago);
    }

    //  Editar un método de pago ya ingresado
    public void editarMetodoPago(int idPago, Pago nuevosDatos) {
        metodosPago.get(idPago).setDatos(nuevosDatos.getDatos());
    }

    //  Eliminar un método de pago ya ingresado
    public void eliminarMetodoPago(int idPago) {
        metodosPago.get(idPago).setEstado("Eliminado");
    }

    //  Visualizar los métodos de pago almacenados
    public ArrayList<Pago> verMetodosPagoGuardados() {
        return this.metodosPago;
    }

    //  Visualizar las direcciones de entrega almacenadas
    public ArrayList<Direccion> verDireccionesEntrega() {
        return this.direcciones;
    }

    //  Agregar una nueva dirección de entrega
    public void agregarNuevaDireccion(String calle, String ciudad) {
        int nuevoId = direcciones.size() + 1;
        direcciones.add(new Direccion(nuevoId, calle, ciudad, false));
    }

    //  Editar una dirección de entrega ya almacenada
    public void editarDireccion(int idDireccion, String calle, String ciudad) {
        for (Direccion d : direcciones) {
            if (d.getId() == idDireccion) {
                d.setCalle(calle);
                d.setCiudad(ciudad);
            }
        }
    }

    //  Eliminar una dirección de entrega ya almacenada
    public void eliminarDireccion(int idDireccion) {
        for (Direccion d : direcciones){
            if(d.getId() == idDireccion){
                direcciones.remove(d);
            }
        }
    }

    //  Formato de impresión
    @Override
    public String toString() {
        return "Id:" + id + ", Nombre:'" + nombre + "', Usuario:'" + usuario;
    }
}
