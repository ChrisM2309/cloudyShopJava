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

    // Metodos Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUsuario() { return usuario; }
    public String getCorreo() { return correo; }
    public String getContraseña() { return contraseña; }

    // Metodos Setters
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    // Verifica las credenciales del empleado para iniciar sesión
    public boolean iniciarSesion(String usuario, String contraseña) {
        return (this.usuario.equals(usuario) && this.contraseña.equals(contraseña));
    }

    // Cierra la sesión del empleado y muestra un mensaje
    public void cerrarSesion() {
        System.out.println("Sesion cerrada para " + usuario);
    }

    // Devuelve la lista completa del catálogo de productos
    public ArrayList<Producto> consultarProductosCatalogo(ArrayList<Producto> catalogo) {
        return catalogo;
    }

    // Añade un producto al catálogo
    public void agregarProductoCatalogo(Producto producto, ArrayList<Producto> catalogo) {
        catalogo.add(producto);
    }

    // Elimina un producto del catálogo por su ID
    public void eliminarProductoCatalogo(int idProducto, ArrayList<Producto> catalogo) {
        catalogo.removeIf(p -> p.getId() == idProducto);
    }

    // Edita la información de un producto en el catálogo
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

    // Asocia una etiqueta a un producto en el catálogo
    public void agregarEtiquetaProducto(int idProducto, Etiqueta etiqueta, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                p.agregarEtiqueta(etiqueta);
            }
        }
    }

    // Elimina una etiqueta de un producto en el catálogo
    public void eliminarEtiquetaProducto(int idProducto, Etiqueta etiqueta, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                p.eliminarEtiqueta(etiqueta);
            }
        }
    }

    // Consulta el inventario de un producto específico
    public int consultarInventarioProducto(int idProducto, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                return p.getInventario();
            }
        }
        return -1;
    }

    // Registra una entrada de inventario para un producto
    public void registrarEntradaInventario(int idProducto, int cantidad, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                p.actualizarInventario(p.getInventario() + cantidad);
            }
        }
    }

    // Devuelve una lista de productos con inventario bajo (menos de 5 unidades)
    public ArrayList<Producto> recibirAlertasInventarioBajo(ArrayList<Producto> catalogo) {
        ArrayList<Producto> alertas = new ArrayList<>();
        for (Producto p : catalogo) {
            if (p.getInventario() < 5) {
                alertas.add(p);
            }
        }
        return alertas;
    }

    // Actualiza el estado de un pedido específico
    public void actualizarEstadoPedido(int idPedido, String nuevoEstado, ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                p.setEstado(nuevoEstado);
            }
        }
    }

    // Cancela un pedido cambiando su estado a "Cancelado"
    public void cancelarPedido(int idPedido, ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                p.setEstado("Cancelado");
            }
        }
    }

    // Devuelve una lista de pedidos con estado "Pendiente"
    public ArrayList<Pedido> verPedidosPendientes(ArrayList<Pedido> pedidos) {
        ArrayList<Pedido> pendientes = new ArrayList<>();
        for (Pedido p : pedidos) {
            if ("Pendiente".equalsIgnoreCase(p.getEstado())) {
                pendientes.add(p);
            }
        }
        return pendientes;
    }

    // Verifica si un pedido ha sido completado
    public boolean verificarEstadoPago(int idPago, ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPago) {
                return "Completado".equalsIgnoreCase(p.getEstado());
            }
        }
        return false;
    }

    // Consulta la dirección asociada a un pedido específico
    public Direccion consultarDireccionPedido(int idPedido, ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                return p.getDireccion();
            }
        }
        return null;
    }

    // Sobrescribe el método toString para representar al empleado como cadena
    @Override
    public String toString() {
        return "Id:" + id + ", Nombre:'" + nombre + "', Usuario:'" + usuario;
    }
}