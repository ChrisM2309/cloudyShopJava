package modelo.usuario;

import modelo.pedido.Direccion;
import modelo.pedido.Pago;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un cliente en el sistema.
 * Gestiona información personal, pedidos, métodos de pago y direcciones de entrega del cliente.
 */
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

    /**
     * Constructor predeterminado que inicializa un cliente con un ID.
     * Los demás atributos se establecen como null o listas vacías.
     * @param id Identificador único del cliente.
     */
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

    /**
     * Constructor completo que inicializa un cliente con todos sus atributos básicos.
     * Las listas de direcciones, métodos de pago y pedidos se inicializan vacías.
     * @param id Identificador único del cliente.
     * @param nombre Nombre completo del cliente.
     * @param usuario Nombre de usuario del cliente para iniciar sesión.
     * @param correo Correo electrónico del cliente.
     * @param password Contraseña del cliente para autenticación.
     * @param telefono Número de teléfono del cliente.
     */
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

    /**
     * Obtiene el identificador del cliente.
     * @return El ID del cliente.
     */
    public int getId() { return id; }

    /**
     * Obtiene el nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene el nombre de usuario del cliente.
     * @return El nombre de usuario del cliente.
     */
    public String getUsuario() { return usuario; }

    /**
     * Obtiene el correo electrónico del cliente.
     * @return El correo electrónico del cliente.
     */
    public String getCorreo() { return correo; }

    /**
     * Obtiene la contraseña del cliente.
     * @return La contraseña del cliente.
     */
    public String getPassword() { return password; }

    /**
     * Obtiene el número de teléfono del cliente.
     * @return El número de teléfono del cliente.
     */
    public String getTelefono() { return telefono; }

    /**
     * Obtiene la lista de direcciones del cliente.
     * @return Lista de direcciones asociadas al cliente.
     */
    public ArrayList<Direccion> getDirecciones() { return direcciones; }

    /**
     * Obtiene la lista de métodos de pago del cliente.
     * @return Lista de métodos de pago asociados al cliente.
     */
    public ArrayList<Pago> getMetodosPago() { return metodosPago; }

    /**
     * Obtiene la lista de pedidos del cliente.
     * @return Lista de pedidos realizados por el cliente.
     */
    public ArrayList<Pedido> getPedidos() { return pedidos; }

    /**
     * Establece el identificador del cliente.
     * @param id Nuevo identificador del cliente.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Establece el nombre del cliente.
     * @param nombre Nuevo nombre del cliente.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Establece el nombre de usuario del cliente.
     * @param usuario Nuevo nombre de usuario del cliente.
     */
    public void setUsuario(String usuario) { this.usuario = usuario; }

    /**
     * Establece el correo electrónico del cliente.
     * @param correo Nuevo correo electrónico del cliente.
     */
    public void setCorreo(String correo) { this.correo = correo; }

    /**
     * Establece la contraseña del cliente.
     * @param password Nueva contraseña del cliente.
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Establece el número de teléfono del cliente.
     * @param telefono Nuevo número de teléfono del cliente.
     */
    public void setTelefono(String telefono) { this.telefono = telefono; }

    /**
     * Verifica las credenciales del cliente para iniciar sesión.
     * @param usuario Nombre de usuario a verificar.
     * @param password Contraseña a verificar.
     * @return true si las credenciales coinciden, false en caso contrario.
     */
    public boolean iniciarSesion(String usuario, String password) {
        if (usuario == null || password == null) {
            System.out.println("Usuario o contraseña no pueden ser nulos");
            return false;
        }
        return this.usuario.equals(usuario) && this.password.equals(password);
    }

    /**
     * Cierra la sesión del cliente y muestra un mensaje en consola.
     */
    public void cerrarSesion() {
        System.out.println("Sesión cerrada para " + usuario);
    }

    /**
     * Actualiza los datos personales del cliente.
     * @param nuevoNombre Nuevo nombre del cliente.
     * @param nuevoCorreo Nuevo correo electrónico del cliente.
     * @param nuevoTelefono Nuevo número de teléfono del cliente.
     */
    public void editarDatos(String nuevoNombre, String nuevoCorreo, String nuevoTelefono) {
        if (nuevoNombre == null || nuevoNombre.isEmpty()) {
            System.out.println("Nombre no puede ser nulo o vacío");
            return;
        }
        if (nuevoCorreo == null || nuevoCorreo.isEmpty()) {
            System.out.println("Correo no puede ser nulo o vacío");
            return;
        }
        if (nuevoTelefono == null || nuevoTelefono.isEmpty()) {
            System.out.println("Teléfono no puede ser nulo o vacío");
            return;
        }
        this.nombre = nuevoNombre;
        this.correo = nuevoCorreo;
        this.telefono = nuevoTelefono;
    }

    /**
     * Elimina la cuenta del cliente de la lista proporcionada tras verificar la contraseña.
     * @param clientes Lista de clientes en el sistema.
     */
    public void eliminarCuenta(ArrayList<Cliente> clientes) {
        System.out.println("Estas seguro de eliminar?\nIngresa tu contraseña para confirmar.");
        Scanner scanner = new Scanner(System.in);
        String passwordInput = scanner.nextLine();
        if (passwordInput == null) {
            System.out.println("La contraseña no puede ser nula");
            return;
        }
        if (passwordInput.equals(password)) {
            clientes.remove(this);
            System.out.println("Cuenta eliminada exitosamente");
        } else {
            System.out.println("Contraseña incorrecta. No se puede eliminar la cuenta.");
        }
        scanner.close();
    }

    /**
     * Devuelve la lista completa de pedidos pasados del cliente.
     * @return Lista de todos los pedidos realizados por el cliente.
     */
    public ArrayList<Pedido> consultarPedidosPasados() {
        return this.pedidos;
    }

    /**
     * Filtra y devuelve los pedidos completados del cliente.
     * @return Lista de pedidos con estado "Completado".
     */
    public ArrayList<Pedido> consultarHistorialCompras() {
        ArrayList<Pedido> historial = new ArrayList<>();
        for (Pedido p : pedidos) {
            if ("Completado".equals(p.getEstado())) historial.add(p);
        }
        return historial;
    }

    /**
     * Devuelve una lista de productos disponibles (con inventario mayor a 0).
     * @param catalogo Lista de productos en el catálogo.
     * @return Lista de productos disponibles.
     */
    public ArrayList<Producto> verProductos(ArrayList<Producto> catalogo) {
        ArrayList<Producto> disponible = new ArrayList<>();
        for (Producto p : catalogo) {
            if (p.getInventario() > 0) {
                disponible.add(p);
            }
        }
        return disponible;
    }

    /**
     * Agrega un producto a un pedido existente y actualiza el inventario.
     * @param idProducto ID del producto a agregar.
     * @param cantidad Cantidad del producto a agregar.
     * @param idPedido ID del pedido al que se agrega el producto.
     * @param catalogo Lista de productos en el catálogo.
     */
    public void agregarProductoPedido(int idProducto, int cantidad, int idPedido, ArrayList<Producto> catalogo) {
        Pedido pedido = null;
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                pedido = p;
                break;
            }
        }
        if (pedido == null) {
            System.out.println("Pedido no encontrado");
            return;
        }
        Producto producto = null;
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                producto = p;
                break;
            }
        }
        if (producto == null) {
            System.out.println("Producto no encontrado");
            return;
        }
        if (producto.getInventario() < cantidad) {
            System.out.println("Inventario insuficiente");
            return;
        }
        pedido.getProductos().add(producto);
        producto.actualizarInventario(producto.getInventario() - cantidad);
    }

    /**
     * Filtra productos por una etiqueta específica.
     * @param etiqueta Etiqueta por la cual filtrar.
     * @param catalogo Lista de productos en el catálogo.
     * @return Lista de productos que contienen la etiqueta especificada.
     */
    public List<Producto> filtrarProductosPorEtiqueta(Etiqueta etiqueta, List<Producto> catalogo) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : catalogo) {
            if (p.getEtiquetas().contains(etiqueta)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Consulta la disponibilidad de un producto en el inventario.
     * @param idProducto ID del producto a consultar.
     * @param catalogo Lista de productos en el catálogo.
     * @return Cantidad en inventario del producto, o 0 si no se encuentra.
     */
    public int consultarInventarioProducto(int idProducto, List<Producto> catalogo) {
        for (Producto p : catalogo) {
            if (p.getId() == idProducto) {
                return p.getInventario();
            }
        }
        return 0;
    }

    /**
     * Crea un nuevo pedido y lo agrega a las listas del cliente y del sistema.
     * @param pedidosSistema Lista de pedidos en el sistema.
     * @return El nuevo pedido creado.
     */
    public Pedido crearPedido(List<Pedido> pedidosSistema) {
        if (pedidosSistema == null){
            System.out.println("La lista de pedidos no puede ser nula");
            return null;
        }
        int nuevoId = pedidosSistema.size() + 1;
        Pedido pedido = new Pedido(nuevoId, new ArrayList<>(), null, null, "Pendiente", this.id);
        this.pedidos.add(pedido);
        pedidosSistema.add(pedido);
        return pedido;
    }

    /**
     * Asocia una dirección de entrega a un pedido específico.
     * @param idDireccion ID de la dirección a asociar.
     * @param idPedido ID del pedido al que se asociará la dirección.
     */
    public void agregarDireccionEntrega(int idDireccion, int idPedido) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                for (Direccion d : direcciones) {
                    if (d.getId() == idDireccion) {
                        p.setDireccion(d);
                        return;
                    }
                }
                System.out.println("Dirección no encontrada");
                return;
            }
        }
        System.out.println("Pedido no encontrado");
    }

    /**
     * Asocia un método de pago a un pedido específico.
     * @param idPago ID del método de pago a asociar.
     * @param idPedido ID del pedido al que se asociará el método de pago.
     */
    public void agregarMetodoPago(int idPago, int idPedido) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                for (Pago curPago : metodosPago) {
                    if (curPago.getId() == idPago) {
                        p.setMetodoPago(curPago);
                        return;
                    }
                }
                System.out.println("Método de pago no encontrado");
                return;
            }
        }
        System.out.println("Pedido no encontrado");
    }

    /**
     * Consulta el estado de todos los pedidos del cliente.
     * @return Lista de pedidos del cliente.
     */
    public ArrayList<Pedido> consultarEstadoPedidos() {
        return getPedidos();
    }

    /**
     * Cancela un pedido cambiando su estado a "Cancelado".
     * @param idPedido ID del pedido a cancelar.
     */
    public void cancelarPedido(int idPedido) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                p.setEstado("Cancelado");
                return;
            }
        }
        System.out.println("Pedido no encontrado");
    }

    /**
     * Agrega un nuevo método de pago al sistema del cliente.
     * @param datosPago Objeto con los datos del método de pago a agregar.
     */
    public void agregarMetodoPagoSistema(Pago datosPago) {
        metodosPago.add(datosPago);
    }

    /**
     * Edita un método de pago existente.
     * @param idPago Índice del método de pago a editar en la lista.
     * @param nuevosDatos Nuevos datos para el método de pago.
     */
    public void editarMetodoPago(int idPago, Pago nuevosDatos) {
        if (idPago >= 0 && idPago < metodosPago.size()) {
            metodosPago.get(idPago).setDatos(nuevosDatos.getDatos());
        } else {
            System.out.println("Id de método de pago inválido");
        }
    }

    /**
     * Elimina un método de pago marcándolo como "Eliminado".
     * @param idPago Índice del método de pago a eliminar en la lista.
     */
    public void eliminarMetodoPago(int idPago) {
        if (idPago >= 0 && idPago < metodosPago.size()) {
            metodosPago.get(idPago).setEstado("Eliminado");
        } else {
            System.out.println("Id de método de pago inválido");
        }    }

    /**
     * Devuelve la lista de métodos de pago almacenados.
     * @return Lista de métodos de pago del cliente.
     */
    public ArrayList<Pago> verMetodosPagoGuardados() {
        return this.metodosPago;
    }

    /**
     * Devuelve la lista de direcciones de entrega almacenadas.
     * @return Lista de direcciones del cliente.
     */
    public ArrayList<Direccion> verDireccionesEntrega() {
        return this.direcciones;
    }

    /**
     * Agrega una nueva dirección de entrega al cliente.
     * @param calle Calle de la nueva dirección.
     * @param ciudad Ciudad de la nueva dirección.
     */
    public void agregarNuevaDireccion(String calle, String ciudad) {
        int nuevoId = direcciones.size() + 1;
        direcciones.add(new Direccion(nuevoId, calle, ciudad, false));
    }

    /**
     * Edita una dirección de entrega existente.
     * @param idDireccion ID de la dirección a editar.
     * @param calle Nueva calle de la dirección.
     * @param ciudad Nueva ciudad de la dirección.
     */
    public void editarDireccion(int idDireccion, String calle, String ciudad) {
        for (Direccion d : direcciones) {
            if (d.getId() == idDireccion) {
                d.setCalle(calle);
                d.setCiudad(ciudad);
                return;
            }
        }
        System.out.println("Dirección no encontrada");
    }

    /**
     * Elimina una dirección de entrega del cliente.
     * @param idDireccion ID de la dirección a eliminar.
     */
    public void eliminarDireccion(int idDireccion) {
        for (Direccion d : direcciones) {
            if (d.getId() == idDireccion) {
                direcciones.remove(d);
                return;
            }
        }
        System.out.println("Dirección no encontrada");
    }

    /**
     * Devuelve una representación en cadena del cliente.
     * @return Cadena con el ID, nombre y usuario del cliente.
     */
    @Override
    public String toString() {
        return "Id:" + id + ", Nombre:'" + nombre + "', Usuario:'" + usuario;
    }
}