package modelo.usuario;

import modelo.pedido.Direccion;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;

import java.util.ArrayList;

public class Empleado {
    private int id;
    private String nombre;
    private String usuario;
    private String correo;
    private String contraseña;

    // Constructor Predeterminado
    public Empleado(int id){
        this.id = id;
        this.nombre = null;
        this.usuario = null;
        this.correo = null;
        this.contraseña = null;
    }
    // Constructor completo
    public Empleado(int id, String nombre, String usuario, String correo, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.contraseña = contraseña;
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
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public boolean iniciarSesion(String usuario, String contraseña) {
        return (this.usuario.equals(usuario) && this.contraseña.equals(contraseña));
    }
    public void cerrarSesion() {
        System.out.println("Sesion cerrada para " + usuario);
    }

    public ArrayList<Producto> consultarProductosCatalogo(ArrayList<Producto> catalogo) {
        return catalogo;
    }

    public void agregarProductoCatalogo(Producto producto, ArrayList<Producto> catalogo) {
        catalogo.add(producto);
    }

    public void eliminarProductoCatalogo(int idProducto, ArrayList<Producto> catalogo) {
        catalogo.removeIf(p -> p.getId() == idProducto);
    }

    public void editarProductoCatalogo(int idProducto, ArrayList<Producto> catalogo, String nuevoNombre, String nuevaDescripcion, double nuevoPrecio, int nuevoInventario) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                p.setNombre(nuevoNombre);
                p.setDescripcion(nuevaDescripcion);
                p.setPrecio(nuevoPrecio);
                p.actualizarInventario(nuevoInventario);
            }
        }
    }

    public void agregarEtiquetaProducto(int idProducto, Etiqueta etiqueta, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                p.agregarEtiqueta(etiqueta);
            }
        }
    }

    public void eliminarEtiquetaProducto(int idProducto, Etiqueta etiqueta, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                p.eliminarEtiqueta(etiqueta);
            }
        }
    }

    public int consultarInventarioProducto(int idProducto, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                return p.getInventario();
            }
        }
        return -1;
    }

    public void registrarEntradaInventario(int idProducto, int cantidad, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                p.actualizarInventario(p.getInventario() + cantidad);
            }
        }
    }

    public ArrayList<Producto> recibirAlertasInventarioBajo(ArrayList<Producto> catalogo) {
        ArrayList<Producto> alertas = new ArrayList<>();
        for (Producto p : catalogo) {
            if (p.getInventario() < 5) {
                alertas.add(p);
            }
        }
        return alertas;
    }

    public void actualizarEstadoPedido(int idPedido, String nuevoEstado, ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                p.setEstado(nuevoEstado);
            }
        }
    }

    public void cancelarPedido(int idPedido, ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                p.setEstado("Cancelado");
            }
        }
    }

    public ArrayList<Pedido> verPedidosPendientes(ArrayList<Pedido> pedidos) {
        ArrayList<Pedido> pendientes = new ArrayList<>();
        for (Pedido p : pedidos) {
            if ("Pendiente".equalsIgnoreCase(p.getEstado())) {
                pendientes.add(p);
            }
        }
        return pendientes;
    }

    public boolean verificarEstadoPago(int idPago, ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPago) {
                return "Completado".equalsIgnoreCase(p.getEstado());
            }
        }
        return false;
    }

    public Direccion consultarDireccionPedido(int idPedido, ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                return p.getDireccion();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Id:" + id + ", Nombre:'" + nombre + "', Usuario:'" + usuario;
    }
}