package modelo.usuario;

import modelo.pedido.Direccion;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase que representa a un empleado en el sistema.
 * Gestiona información personal del empleado y funciones relacionadas con productos, inventario y pedidos.
 */
public class Empleado {
    private int id;
    private String nombre;
    private String usuario;
    private String correo;
    private String contraseña;

    /**
     * Constructor predeterminado que inicializa un empleado con un ID.
     * Los demás atributos se establecen como null.
     * @param id Identificador único del empleado.
     */
    public Empleado(int id) {
        this.id = id;
        this.nombre = null;
        this.usuario = null;
        this.correo = null;
        this.contraseña = null;
    }

    /**
     * Constructor completo que inicializa un empleado con todos sus atributos.
     * @param id Identificador único del empleado.
     * @param nombre Nombre completo del empleado.
     * @param usuario Nombre de usuario del empleado para iniciar sesión.
     * @param correo Correo electrónico del empleado.
     * @param contraseña Contraseña del empleado para autenticación.
     */
    public Empleado(int id, String nombre, String usuario, String correo, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    /**
     * Obtiene el identificador del empleado.
     * @return El ID del empleado.
     */
    public int getId() { return id; }

    /**
     * Obtiene el nombre del empleado.
     * @return El nombre del empleado.
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene el nombre de usuario del empleado.
     * @return El nombre de usuario del empleado.
     */
    public String getUsuario() { return usuario; }

    /**
     * Obtiene el correo electrónico del empleado.
     * @return El correo electrónico del empleado.
     */
    public String getCorreo() { return correo; }

    /**
     * Obtiene la contraseña del empleado.
     * @return La contraseña del empleado.
     */
    public String getContraseña() { return contraseña; }

    /**
     * Establece el identificador del empleado.
     * @param id Nuevo identificador del empleado.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Establece el nombre del empleado.
     * @param nombre Nuevo nombre del empleado.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Establece el nombre de usuario del empleado.
     * @param usuario Nuevo nombre de usuario del empleado.
     */
    public void setUsuario(String usuario) { this.usuario = usuario; }

    /**
     * Establece el correo electrónico del empleado.
     * @param correo Nuevo correo electrónico del empleado.
     */
    public void setCorreo(String correo) { this.correo = correo; }

    /**
     * Establece la contraseña del empleado.
     * @param contraseña Nueva contraseña del empleado.
     */
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    /**
     * Verifica las credenciales del empleado para iniciar sesión.
     * @param usuario Nombre de usuario a verificar.
     * @param password Contraseña a verificar.
     * @return true si las credenciales coinciden, false en caso contrario.
     */
    public boolean iniciarSesion(String usuario, String password) {
        if (usuario == null || password == null){
            System.out.println("Usuario o contraseña no pueden ser nulos");
            return false;
        }
        return (this.usuario.equals(usuario) && this.contraseña.equals(password));
    }

    /**
     * Cierra la sesión del empleado y muestra un mensaje en consola.
     */
    public void cerrarSesion() {
        System.out.println("Sesion cerrada para " + usuario);
    }

    /**
     * Consulta y devuelve la lista completa del catálogo de productos.
     * @param catalogo Lista de productos en el catálogo.
     * @return La lista completa de productos.
     */
    public ArrayList<Producto> consultarProductosCatalogo(ArrayList<Producto> catalogo) {
        return catalogo;
    }

    /**
     * Agrega un producto al catálogo.
     * @param producto Producto a agregar.
     * @param catalogo Lista de productos en el catálogo.
     */
    public void agregarProductoCatalogo(Producto producto, ArrayList<Producto> catalogo) {
        if (producto == null){
            System.out.println("El producto no puede ser nulo");
            return;
        }
        for (Producto p : catalogo) {
            if (p.getId() == producto.getId()) {
                System.out.println("El producto con ID " + producto.getId() + " ya existe");
                return;
            }
        }
        catalogo.add(producto);
    }

    /**
     * Elimina un producto del catálogo basado en su ID.
     * @param idProducto ID del producto a eliminar.
     * @param catalogo Lista de productos en el catálogo.
     */
    public void eliminarProductoCatalogo(int idProducto, ArrayList<Producto> catalogo) {
        boolean removed = catalogo.removeIf(p -> p.getId() == idProducto);
        if (!removed) {
            System.out.println("Producto con ID " + idProducto + " no encontrado");
        }
    }

    /**
     * Edita la información de un producto en el catálogo.
     * @param idProducto ID del producto a editar.
     * @param catalogo Lista de productos en el catálogo.
     * @param nuevoNombre Nuevo nombre del producto.
     * @param nuevaDescripcion Nueva descripción del producto.
     * @param nuevoPrecio Nuevo precio del producto.
     * @param nuevoInventario Nueva cantidad en inventario del producto.
     */
    public void editarProductoCatalogo(int idProducto, ArrayList<Producto> catalogo, String nuevoNombre, String nuevaDescripcion, double nuevoPrecio, int nuevoInventario) {
        if (nuevoNombre == null || nuevoNombre.isEmpty()){
            System.out.println("El nombre no puede ser nulo o vacío");
            return;
        }
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                p.setNombre(nuevoNombre);
                p.setDescripcion(nuevaDescripcion);
                p.setPrecio(nuevoPrecio);
                p.actualizarInventario(nuevoInventario);
                return;
            }
        }
        System.out.println("Producto con ID " + idProducto + " no encontrado");
    }

    /**
     * Asocia una etiqueta a un producto específico en el catálogo.
     * @param idProducto ID del producto al que se le añadirá la etiqueta.
     * @param etiqueta Etiqueta a asociar.
     * @param catalogo Lista de productos en el catálogo.
     */
    public void agregarEtiquetaProducto(int idProducto, Etiqueta etiqueta, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                p.agregarEtiqueta(etiqueta);
                return;
            }
        }
        System.out.println("Producto con ID " + idProducto + " no encontrado");
    }

    /**
     * Elimina una etiqueta de un producto específico en el catálogo.
     * @param idProducto ID del producto del que se eliminará la etiqueta.
     * @param etiqueta Etiqueta a eliminar.
     * @param catalogo Lista de productos en el catálogo.
     */
    public void eliminarEtiquetaProducto(int idProducto, Etiqueta etiqueta, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                p.eliminarEtiqueta(etiqueta);
                return;
            }
        }
        System.out.println("Producto con ID " + idProducto + " no encontrado");
    }

    /**
     * Consulta el inventario de un producto específico.
     * @param idProducto ID del producto a consultar.
     * @param catalogo Lista de productos en el catálogo.
     * @return Cantidad en inventario del producto, o -1 si no se encuentra.
     */
    public int consultarInventarioProducto(int idProducto, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                return p.getInventario();
            }
        }
        return -1;
    }

    /**
     * Registra una entrada de inventario para un producto, aumentando su cantidad.
     * @param idProducto ID del producto a actualizar.
     * @param cantidad Cantidad a agregar al inventario.
     * @param catalogo Lista de productos en el catálogo.
     */
    public void registrarEntradaInventario(int idProducto, int cantidad, ArrayList<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                p.actualizarInventario(p.getInventario() + cantidad);
                return;
            }
        }
        System.out.println("Producto con ID " + idProducto + " no encontrado");
    }

    /**
     * Devuelve una lista de productos con inventario bajo (menos de 5 unidades).
     * @param catalogo Lista de productos en el catálogo.
     * @return Lista de productos con inventario bajo.
     */
    public ArrayList<Producto> recibirAlertasInventarioBajo(ArrayList<Producto> catalogo) {
        ArrayList<Producto> alertas = new ArrayList<>();
        for (Producto p : catalogo) {
            if (p.getInventario() < 5) {
                alertas.add(p);
            }
        }
        return alertas;
    }

    /**
     * Actualiza el estado de un pedido específico.
     * @param idPedido ID del pedido a actualizar.
     * @param nuevoEstado Nuevo estado del pedido.
     * @param pedidos Lista de pedidos en el sistema.
     */
    public void actualizarEstadoPedido(int idPedido, String nuevoEstado, ArrayList<Pedido> pedidos) {
        if (nuevoEstado == null) {
            System.out.println("El nuevo estado no puede ser nulo");
            return;
        }
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                p.setEstado(nuevoEstado);
                return;
            }
        }
        System.out.println("Pedido con ID " + idPedido + " no encontrado");
    }

    /**
     * Cancela un pedido cambiando su estado a "Cancelado".
     * @param idPedido ID del pedido a cancelar.
     * @param pedidos Lista de pedidos en el sistema.
     */
    public void cancelarPedido(int idPedido, ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                p.setEstado("Cancelado");
                return;
            }
        }
        System.out.println("Pedido con ID " + idPedido + " no encontrado");
    }

    /**
     * Devuelve una lista de pedidos con estado "Pendiente".
     * @param pedidos Lista de pedidos en el sistema.
     * @return Lista de pedidos pendientes.
     */
    public ArrayList<Pedido> verPedidosPendientes(ArrayList<Pedido> pedidos) {
        ArrayList<Pedido> pendientes = new ArrayList<>();
        for (Pedido p : pedidos) {
            if ("Pendiente".equalsIgnoreCase(p.getEstado())) {
                pendientes.add(p);
            }
        }
        return pendientes;
    }

    /**
     * Verifica si un pedido ha sido completado.
     * @param idPago ID del pedido a verificar (se asume que es el ID del pedido).
     * @param pedidos Lista de pedidos en el sistema.
     * @return true si el pedido está "Completado", false en caso contrario.
     */
    public boolean verificarEstadoPago(int idPago, ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPago) {
                return "Completado".equalsIgnoreCase(p.getEstado());
            }
        }
        return false;
    }

    /**
     * Consulta la dirección asociada a un pedido específico.
     * @param idPedido ID del pedido a consultar.
     * @param pedidos Lista de pedidos en el sistema.
     * @return La dirección del pedido, o null si no se encuentra.
     */
    public Direccion consultarDireccionPedido(int idPedido, ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                return p.getDireccion();
            }
        }
        return null;
    }

    /**
     * Devuelve una representación en cadena del empleado.
     * @return Cadena con el ID, nombre y usuario del empleado.
     */
    @Override
    public String toString() {
        return "Id:" + id + ", Nombre:'" + nombre + "', Usuario:'" + usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Empleado empleado)) return false;
        return Objects.equals(usuario, empleado.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(usuario);
    }
}