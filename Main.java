import modelo.pedido.Direccion;
import modelo.pedido.Pago;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;
import modelo.usuario.Admin;
import modelo.usuario.Cliente;
import modelo.usuario.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ArrayList<Empleado> empleados = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Producto> catalogo = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();
    private static ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    private static ArrayList<Direccion> puntosEntrega = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Menú principal
        while (true) {

            inicializarDatos();

            System.out.println("\nBienvenido al sistema");
            System.out.println("1. Iniciar sesión como administrador");
            System.out.println("2. Iniciar sesión como empleado");
            System.out.println("3. Iniciar sesión como cliente");
            System.out.println("4. Registrarse como cliente");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    manejarAdmin();
                    break;
                case "2":
                    manejarEmpleado();
                    break;
                case "3":
                    manejarCliente();
                    break;
                case "4": 
                    registrarCliente();
                    break; 
                case "5":
                    System.out.println("Saliendo del sistema...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }

    private static void inicializarDatos() {
        // Administradores
        admins.add(new Admin(1, "Juan Admin", "admin1", "admin1@empresa.com", "admin123"));

        // Empleados
        empleados.add(new Empleado(1, "Ana Lucia", "empleado1", "ana@empresa.com", "emp123"));

        // Clientes
        clientes.add(new Cliente(1, "María Jose", "cliente1", "maria@cliente.com", "cli123", "555-1234"));

        // Etiquetas
        etiquetas.add(new Etiqueta(1, "Electrónica"));
        etiquetas.add(new Etiqueta(2, "Hogar"));

        // Productos
        catalogo.add(new Producto(1, "Laptop", "Laptop de alta gama", 1000.0, 10));
        catalogo.getLast().agregarEtiqueta(etiquetas.get(0));

        catalogo.add(new Producto(2, "Silla", "Silla ergonómica", 150.0, 20));
        catalogo.getLast().agregarEtiqueta(etiquetas.getLast());

        catalogo.add(new Producto(3, "Teléfono", "Smartphone moderno", 500.0, 15));
        catalogo.getLast().agregarEtiqueta(etiquetas.get(0));

        //Direcciones
        clientes.get(0).agregarNuevaDireccion("Calle 123", "San Salvador");

        //Pagos
        Pago pago1 = new Pago(clientes.get(0).getMetodosPago().size()+1, "Tarjeta", "123", "Activo");
        clientes.get(0).agregarMetodoPagoSistema(pago1);
        // Puntos de Entrega
        puntosEntrega.add(new Direccion(1, "Punto Central", "Ciudad C", true));

        // Pedidos
        // Agregar dos pedidos, uno completado y otro pendiente 
        // Pedido pendiente
        clientes.get(0).crearPedido(pedidos);
        clientes.get(0).agregarProductoPedido(0, 1, 1, catalogo);
        clientes.get(0).agregarProductoPedido(2, 1, 1, catalogo);
        clientes.get(0).agregarDireccionEntrega(1, 1);
        clientes.get(0).agregarMetodoPago(1, 1);

        //Pedido Pagado 
        clientes.get(0).crearPedido(pedidos);
        clientes.get(0).agregarProductoPedido(1, 1, 2, catalogo);
        clientes.get(0).agregarDireccionEntrega(1, 2);
        clientes.get(0).agregarMetodoPago(1, 2);
        // Marcar como completado
        empleados.get(0).actualizarEstadoPedido(2, "Completado", pedidos);

    }

    private static void manejarAdmin() {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        Admin admin = buscarAdmin(usuario, password);
        if (admin != null) {
            System.out.println("Sesión iniciada como administrador");
            menuAdmin(admin);
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

    private static void manejarEmpleado() {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine();

        Empleado empleado = buscarEmpleado(usuario, contraseña);
        if (empleado != null) {
            System.out.println("Sesión iniciada como empleado");
            menuEmpleado(empleado);
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

    private static void registrarCliente() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        int nuevoId = clientes.size() + 1;
        Cliente nuevoCliente = new Cliente(nuevoId, nombre, usuario, correo, password, telefono);
        clientes.add(nuevoCliente);

        System.out.println("Cliente registrado exitosamente.");
    }

    private static void manejarCliente() {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        Cliente cliente = buscarCliente(usuario, password);
        if (cliente != null) {
            System.out.println("Sesión iniciada como cliente");
            menuCliente(cliente);
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

    private static Admin buscarAdmin(String usuario, String contraseña) {
        for (Admin admin : admins) {
            if (admin.iniciarSesion(usuario, contraseña)) {
                return admin;
            }
        }
        return null;
    }

    private static Empleado buscarEmpleado(String usuario, String contraseña) {
        for (Empleado empleado : empleados) {
            if (empleado.iniciarSesion(usuario, contraseña)) {
                return empleado;
            }
        }
        return null;
    }

    private static Cliente buscarCliente(String usuario, String contraseña) {
        for (Cliente cliente : clientes) {
            if (cliente.iniciarSesion(usuario, contraseña)) {
                return cliente;
            }
        }
        return null;
    }

    private static void menuAdmin(Admin admin) {
        while (true) {
            // Mostrar el menú con todas las opciones organizadas por categorías
            System.out.println("\nMenú para Administrador");
            System.out.println("-- Gestión de Etiquetas --");
            System.out.println("1. Crear etiqueta");
            System.out.println("2. Eliminar etiqueta");
            System.out.println("-- Gestión de Empleados --");
            System.out.println("3. Registrar empleado");
            System.out.println("4. Eliminar cuenta de empleado");
            System.out.println("5. Editar información de empleado");
            System.out.println("-- Gestión de Inventario --");
            System.out.println("6. Conocer inventario");
            System.out.println("-- Gestión de Pedidos --");
            System.out.println("7. Consultar cantidad de pedidos");
            System.out.println("8. Consultar pagos realizados");
            System.out.println("-- Gestión de Puntos de Entrega --");
            System.out.println("9. Agregar punto de entrega");
            System.out.println("10. Editar punto de entrega");
            System.out.println("11. Eliminar punto de entrega");
            System.out.println("-- Sistema --");
            System.out.println("12. Cerrar sesión");

            // Solicitar la opción al usuario
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            // Procesar la opción seleccionada con un switch
            switch (opcion) {
                case "1": // Crear etiqueta
                    System.out.print("Nombre de la etiqueta: ");
                    String nombreEtiqueta = sc.nextLine();
                    admin.crearEtiqueta(nombreEtiqueta, etiquetas);
                    System.out.println("Etiqueta creada");
                    break;

                case "2": // Eliminar etiqueta
                    for(Etiqueta etiqueta: etiquetas){
                        System.out.println(etiqueta);
                    }
                    System.out.print("ID de la etiqueta a eliminar: ");
                    int idEtiquetaEliminar = Integer.parseInt(sc.nextLine());
                    admin.eliminarEtiqueta(idEtiquetaEliminar, etiquetas);
                    System.out.println("Etiqueta eliminada");
                    break;

                case "3": // Registrar empleado
                    System.out.print("Nombre del empleado: ");
                    String nombreEmpleado = sc.nextLine();
                    System.out.print("Usuario: ");
                    String usuarioEmpleado = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String contraseñaEmpleado = sc.nextLine();
                    admin.registrarEmpleado(nombreEmpleado, usuarioEmpleado, contraseñaEmpleado, empleados);
                    System.out.println("Empleado registrado");
                    break;

                case "4": // Eliminar cuenta de empleado
                    for(Empleado empleado: empleados){
                        System.out.println(empleado);
                    }
                    System.out.print("ID del empleado a eliminar: ");
                    int idEmpleadoEliminar = Integer.parseInt(sc.nextLine());
                    admin.eliminarCuentaEmpleado(idEmpleadoEliminar, empleados);
                    System.out.println("Cuenta de empleado eliminada");
                    break;

                case "5": // Editar información de empleado
                    System.out.print("ID del empleado a editar: ");
                    int idEmpleadoEditar = Integer.parseInt(sc.nextLine());
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nuevo usuario: ");
                    String nuevoUsuario = sc.nextLine();
                    System.out.print("Nueva contraseña: ");
                    String nuevoPassword = sc.nextLine();
                    admin.editarInformacionEmpleado(idEmpleadoEditar, empleados, nuevoNombre, nuevoUsuario, nuevoPassword);
                    System.out.println("Información del empleado actualizada");
                    break;

                case "6": // Conocer inventario
                    List<Producto> inventario = admin.conocerInventario(catalogo);
                    System.out.println("Inventario:");
                    for (Producto p : inventario){
                        System.out.println(p);
                    }
                    break;

                case "7": // Consultar cantidad de pedidos
                    int cantidadPedidos = admin.consultarCantidadPedidos(pedidos);
                    System.out.println("Cantidad de pedidos: " + cantidadPedidos);
                    break;

                case "8": // Consultar pagos realizados -- Pedidos Completados
                    List<Pedido> pagosRealizados = admin.consultarPagosRealizados(pedidos);
                    System.out.println("Pagos realizados: " + pagosRealizados);
                    break;

                case "9": // Agregar punto de entrega
                    System.out.print("Calle: ");
                    String callePunto = sc.nextLine();
                    System.out.print("Ciudad: ");
                    String ciudadPunto = sc.nextLine();
                    admin.agregarPuntoEntrega(callePunto, ciudadPunto, puntosEntrega);
                    System.out.println("Punto de entrega agregado");
                    break;

                case "10": // Editar punto de entrega
                    System.out.print("ID del punto de entrega a editar: ");
                    int idPuntoEditar = Integer.parseInt(sc.nextLine());
                    System.out.print("Nueva calle: ");
                    String nuevaCalle = sc.nextLine();
                    System.out.print("Nueva ciudad: ");
                    String nuevaCiudad = sc.nextLine();
                    System.out.print("Nuevo código postal: ");
                    String nuevoCodigoPostal = sc.nextLine();
                    admin.editarPuntoEntrega(idPuntoEditar, nuevaCalle, nuevaCiudad, nuevoCodigoPostal, puntosEntrega);
                    System.out.println("Punto de entrega actualizado");
                    break;

                case "11": // Eliminar punto de entrega
                    for(Direccion direccion: puntosEntrega){
                        System.out.println(direccion);
                    }
                    System.out.print("ID del punto de entrega a eliminar: ");
                    int idPuntoEliminar = Integer.parseInt(sc.nextLine());
                    admin.eliminarPuntoEntrega(idPuntoEliminar, puntosEntrega);
                    System.out.println("Punto de entrega eliminado");
                    break;

                case "12": // Cerrar sesión
                    admin.cerrarSesion();
                    return;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void menuEmpleado(Empleado empleado) {
        while (true) {
            // Mostrar el menú con todas las opciones organizadas por categorías
            System.out.println("\nMenú para Empleado");
            System.out.println("-- Gestión de Productos --");
            System.out.println("1. Consultar productos del catálogo");
            System.out.println("2. Agregar producto al catálogo");
            System.out.println("3. Eliminar producto del catálogo");
            System.out.println("4. Editar producto del catálogo");
            System.out.println("5. Agregar etiqueta a producto");
            System.out.println("6. Eliminar etiqueta de producto");
            System.out.println("-- Gestión de Inventario --");
            System.out.println("7. Consultar inventario de un producto");
            System.out.println("8. Registrar entrada de inventario");
            System.out.println("9. Recibir alertas de inventario bajo");
            System.out.println("-- Gestión de Pedidos --");
            System.out.println("10. Actualizar estado de pedido");
            System.out.println("11. Cancelar pedido");
            System.out.println("12. Ver pedidos pendientes");
            System.out.println("13. Verificar estado de pago");
            System.out.println("14. Consultar dirección de pedido");
            System.out.println("-- Sistema --");
            System.out.println("15. Cerrar sesión");
    
            // Solicitar la opción al usuario
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();
    
            // Procesar la opción seleccionada con un switch
            switch (opcion) {
                case "1": // Consultar productos del catálogo
                    List<Producto> productos = empleado.consultarProductosCatalogo(catalogo);
                    System.out.println("Catálogo: ");
                    for (Producto p : productos){
                        System.out.println(p);
                    }
                    break;
    
                case "2": // Agregar producto al catálogo
                    int idProducto = catalogo.size()+1;
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(sc.nextLine());
                    System.out.print("Inventario: ");
                    int inventario = Integer.parseInt(sc.nextLine());
                    Producto nuevoProducto = new Producto(idProducto, nombre, descripcion, precio, inventario);
                    empleado.agregarProductoCatalogo(nuevoProducto, catalogo);
                    System.out.println("Producto agregado");
                    break;
    
                case "3": // Eliminar producto del catálogo
                    for( Producto producto: catalogo){
                        System.out.println(producto);
                    }
                    System.out.print("ID del producto a eliminar: ");
                    int idEliminar = Integer.parseInt(sc.nextLine());
                    empleado.eliminarProductoCatalogo(idEliminar, catalogo);
                    System.out.println("Producto eliminado");
                    break;
    
                case "4": // Editar producto del catálogo
                    System.out.print("ID del producto a editar: ");
                    int idEditar = Integer.parseInt(sc.nextLine());
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nueva descripción: ");
                    String nuevaDescripcion = sc.nextLine();
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = Double.parseDouble(sc.nextLine());
                    System.out.print("Nuevo inventario: ");
                    int nuevoInventario = Integer.parseInt(sc.nextLine());
                    
                    empleado.editarProductoCatalogo(idEditar, catalogo, nuevoNombre, nuevaDescripcion, nuevoPrecio, nuevoInventario);
                    
                    System.out.println("Producto actualizado");
                    break;
    
                case "5": // Agregar etiqueta a producto
                    int idProdEtiqueta = etiquetas.size()+1;
                    System.out.print("ID de la etiqueta: ");
                    int idEtiqueta = Integer.parseInt(sc.nextLine());
                    // Buscar la etiqueta en la lista de etiquetas (asumiendo que existe una lista estática 'etiquetas')
                    Etiqueta etiqueta = Main.etiquetas.stream()
                            .filter(e -> e.getId() == idEtiqueta)
                            .findFirst()
                            .orElse(null);
                    if (etiqueta != null) {
                        empleado.agregarEtiquetaProducto(idProdEtiqueta, etiqueta, catalogo);
                        System.out.println("Etiqueta agregada al producto");
                    } else {
                        System.out.println("Etiqueta no encontrada");
                    }
                    break;
                case "6": // Eliminar etiqueta de producto
                    for( Producto producto: catalogo){
                        System.out.println(producto);
                    }
                    System.out.print("ID del producto: ");
                    int idProdEliminarEtiqueta = Integer.parseInt(sc.nextLine());
                    System.out.print("ID de la etiqueta: ");
                    int idEtiquetaEliminar = Integer.parseInt(sc.nextLine());
                    Etiqueta etiquetaEliminar = Main.etiquetas.stream()
                            .filter(e -> e.getId() == idEtiquetaEliminar)
                            .findFirst()
                            .orElse(null);
                    if (etiquetaEliminar != null) {
                        empleado.eliminarEtiquetaProducto(idProdEliminarEtiqueta, etiquetaEliminar, catalogo);
                        System.out.println("Etiqueta eliminada del producto");
                    } else {
                        System.out.println("Etiqueta no encontrada");
                    }
                    break;
    
                case "7": // Consultar inventario de un producto
                    System.out.print("ID del producto: ");
                    int idConsultarInventario = Integer.parseInt(sc.nextLine());
                    int cantidad = empleado.consultarInventarioProducto(idConsultarInventario, catalogo);
                    System.out.println("Inventario del producto: " + cantidad);
                    break;
    
                case "8": // Registrar entrada de inventario
                    System.out.print("ID del producto: ");
                    int idRegistrarEntrada = Integer.parseInt(sc.nextLine());
                    System.out.print("Cantidad a agregar: ");
                    int cantidadAgregar = Integer.parseInt(sc.nextLine());
                    empleado.registrarEntradaInventario(idRegistrarEntrada, cantidadAgregar, catalogo);
                    System.out.println("Entrada de inventario registrada");
                    break;
    
                case "9": // Recibir alertas de inventario bajo
                    List<Producto> alertas = empleado.recibirAlertasInventarioBajo(catalogo);
                    System.out.println("Productos con inventario bajo: " + alertas);
                    break;
    
                case "10": // Actualizar estado de pedido
                    System.out.print("ID del pedido: ");
                    int idPedidoActualizar = Integer.parseInt(sc.nextLine());
                    System.out.print("Nuevo estado: ");
                    String nuevoEstado = sc.nextLine();
                    empleado.actualizarEstadoPedido(idPedidoActualizar, nuevoEstado, Main.pedidos);
                    System.out.println("Estado del pedido actualizado");
                    break;
    
                case "11": // Cancelar pedido
                    System.out.print("ID del pedido a cancelar: ");
                    int idPedidoCancelar = Integer.parseInt(sc.nextLine());
                    empleado.cancelarPedido(idPedidoCancelar, Main.pedidos);
                    System.out.println("Pedido cancelado");
                    break;
    
                case "12": // Ver pedidos pendientes
                    List<Pedido> pendientes = empleado.verPedidosPendientes(Main.pedidos);
                    System.out.println("Pedidos pendientes: " + pendientes);
                    break;
    
                case "13": // Verificar estado de pago
                    System.out.print("ID del pago: ");
                    int idPagoVerificar = Integer.parseInt(sc.nextLine());
                    boolean pagoProcesado = empleado.verificarEstadoPago(idPagoVerificar, Main.pedidos);
                    System.out.println("Estado del pago: " + (pagoProcesado ? "Procesado" : "No procesado"));
                    break;
    
                case "14": // Consultar dirección de pedido
                    System.out.print("ID del pedido: ");
                    int idPedidoDireccion = Integer.parseInt(sc.nextLine());
                    Direccion direccion = empleado.consultarDireccionPedido(idPedidoDireccion, Main.pedidos);
                    if (direccion != null) {
                        System.out.println("Dirección del pedido: " + direccion);
                    } else {
                        System.out.println("Pedido no encontrado o sin dirección");
                    }
                    break;
    
                case "15": // Cerrar sesión
                    empleado.cerrarSesion();
                    return;
    
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void menuCliente(Cliente cliente) {
        while (true) {
            System.out.println("\nMenú para Cliente");
            System.out.println("-- Mi Cuenta --");
            System.out.println("1. Editar mis datos");
            System.out.println("2. Eliminar mi cuenta");
            System.out.println("3. Ver métodos de pago guardados");
            System.out.println("4. Agregar método de pago al sistema");
            System.out.println("5. Editar método de pago");
            System.out.println("6. Eliminar método de pago");
            System.out.println("7. Ver direcciones de entrega");
            System.out.println("8. Agregar nueva dirección");
            System.out.println("9. Editar dirección");
            System.out.println("10. Eliminar dirección");
            System.out.println("-- Productos --");
            System.out.println("11. Ver productos");
            System.out.println("12. Filtrar productos por etiqueta");
            System.out.println("13. Consultar inventario de un producto");
            System.out.println("14. Comprar producto (agregar al pedido)");
            System.out.println("-- Mis Pedidos --");
            System.out.println("15. Crear pedido");
            System.out.println("16. Consultar estado de mis pedidos");
            System.out.println("17. Ver todos mis pedidos");
            System.out.println("18. Ver historial de compras");
            System.out.println("19. Agregar dirección a pedido");
            System.out.println("20. Agregar método de pago a pedido");
            System.out.println("21. Cancelar pedido");
            System.out.println("-- Sistema --");
            System.out.println("22. Cerrar sesión");
            String opcion = sc.nextLine();

            switch (opcion) {
                // -- Mi Cuenta --
                case "1": // Editar mis datos
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nuevo correo: ");
                    String nuevoCorreo = sc.nextLine();
                    System.out.print("Nuevo teléfono: ");
                    String nuevoTelefono = sc.nextLine();
                    cliente.editarDatos(nuevoNombre, nuevoCorreo, nuevoTelefono);
                    System.out.println("Datos actualizados");
                    break;
    
                case "2": // Eliminar mi cuenta
                    cliente.eliminarCuenta(clientes);
                    System.out.println("Cuenta eliminada. Sesión cerrada.");
                    return;
    
                case "3": // Ver métodos de pago guardados
                    List<Pago> metodosPago = cliente.verMetodosPagoGuardados();
                    System.out.println("Métodos de pago: " + metodosPago);
                    break;
    
                case "4": // Agregar método de pago al sistema
                    System.out.print("Tipo de pago (e.g., Tarjeta): ");
                    String tipo = sc.nextLine();
                    System.out.print("Datos (e.g., número de tarjeta): ");
                    String datos = sc.nextLine();
                    Pago nuevoPago = new Pago(cliente.getMetodosPago().size() + 1, tipo, datos, "Activo");
                    cliente.agregarMetodoPagoSistema(nuevoPago);
                    //pagos.add(nuevoPago);
                    System.out.println("Método de pago agregado");
                    break;
    
                case "5": // Editar método de pago
                    System.out.print("ID del método de pago a editar: ");
                    int idPagoEditar = Integer.parseInt(sc.nextLine());
                    System.out.print("Nuevos datos (e.g., número de tarjeta): ");
                    String nuevosDatosPago = sc.nextLine();
                    Pago pagoEditado = new Pago(idPagoEditar - 1, "Tarjeta", nuevosDatosPago, "Pendiente");
                    cliente.editarMetodoPago(idPagoEditar - 1, pagoEditado);
                    System.out.println("Método de pago actualizado");
                    break;
    
                case "6": // Eliminar método de pago

                    System.out.print("ID del método de pago a eliminar: ");
                    int idPagoEliminar = Integer.parseInt(sc.nextLine());
                    cliente.eliminarMetodoPago(idPagoEliminar);
                    System.out.println("Método de pago eliminado");
                    break;
    
                case "7": // Ver direcciones de entrega
                    List<Direccion> direccionesEntrega = cliente.verDireccionesEntrega();
                    System.out.println("Direcciones: " + direccionesEntrega);
                    break;
    
                case "8": // Agregar nueva dirección
                    System.out.print("Calle: ");
                    String calle = sc.nextLine();
                    System.out.print("Ciudad: ");
                    String ciudad = sc.nextLine();
                    cliente.agregarNuevaDireccion(calle, ciudad);
                    System.out.println("Dirección agregada");
                    break;
    
                case "9": // Editar dirección
                    System.out.print("ID de la dirección a editar: ");
                    int idDireccionEditar = Integer.parseInt(sc.nextLine());
                    System.out.print("Nueva calle: ");
                    String nuevaCalle = sc.nextLine();
                    System.out.print("Nueva ciudad: ");
                    String nuevaCiudad = sc.nextLine();
                    cliente.editarDireccion(idDireccionEditar, nuevaCalle, nuevaCiudad);
                    System.out.println("Dirección actualizada");
                    break;
    
                case "10": // Eliminar dirección
                    System.out.print("ID de la dirección a eliminar: ");
                    int idDireccionEliminar = Integer.parseInt(sc.nextLine());
                    cliente.eliminarDireccion(idDireccionEliminar);
                    System.out.println("Dirección eliminada");
                    break;
    
                // -- Productos --
                case "11": // Ver productos
                    //List<Producto> productos = cliente.consultarProductosCatalogo(catalogo);
                    System.out.println("Catálogo:\n " + catalogo);
                    break;
    
                case "12": // Filtrar productos por etiqueta
                    System.out.print("Etiquetas:\n" + etiquetas);
                    System.out.print("Nombre de la etiqueta (e.g., Electrónica): ");
                    String nombreEtiqueta = sc.nextLine();
                    Etiqueta etiqueta = null;
                    for (Etiqueta e : etiquetas) {
                        if (e.getNombre().equalsIgnoreCase(nombreEtiqueta)) {
                            etiqueta = e;
                            break;
                        }
                    }
                    if (etiqueta != null) {
                        List<Producto> productosFiltrados = cliente.filtrarProductosPorEtiqueta(etiqueta, catalogo);
                        System.out.println("Productos filtrados: " + productosFiltrados);
                    } else {
                        System.out.println("Etiqueta no encontrada");
                    }
                    break;
    
                case "13": // Consultar inventario de un producto
                    System.out.print("ID del producto: ");
                    int idProductoConsulta = Integer.parseInt(sc.nextLine());
                    int cantidadDisponible = cliente.consultarInventarioProducto(idProductoConsulta, catalogo);
                    System.out.println("Existen " +  cantidadDisponible + "unidades disponibles");
                    break;
    
                case "14": // Comprar producto (agregar al pedido)
                    System.out.print("ID del pedido: ");
                    int idPedido = Integer.parseInt(sc.nextLine());
                    System.out.print("ID del producto: ");
                    int idProducto = Integer.parseInt(sc.nextLine());
                    System.out.print("Cantidad: ");
                    int cantidad = Integer.parseInt(sc.nextLine());
                    cliente.agregarProductoPedido(idProducto, cantidad, idPedido, catalogo);
                    System.out.println("Producto agregado al pedido " + idPedido);
                    break;
    
                // -- Mis Pedidos --
                case "15": // Crear pedido
                    Pedido nuevoPedido = cliente.crearPedido(pedidos);
                    System.out.println("Pedido creado con ID: " + nuevoPedido.getId());
                    break;
    
                case "16": // Consultar estado de mis pedidos
                    List<Pedido> estadoPedidos = cliente.consultarEstadoPedidos();
                    System.out.println("Estado de mis pedidos: " + estadoPedidos);
                    break;
    
                case "17": // Ver todos mis pedidos
                    List<Pedido> pedidosPasados = cliente.consultarPedidosPasados();
                    System.out.println("Todos mis pedidos: " + pedidosPasados);
                    break;
    
                case "18": // Ver historial de compras
                    List<Pedido> historial = cliente.consultarHistorialCompras();
                    System.out.println("Historial de compras: " + historial);
                    break;
    
                case "19": // Agregar dirección a pedido
                    System.out.print("ID del pedido: ");
                    int idPedidoDir = Integer.parseInt(sc.nextLine());
                    System.out.print("ID de la dirección: ");
                    int idDireccion = Integer.parseInt(sc.nextLine());
                    cliente.agregarDireccionEntrega(idDireccion, idPedidoDir);
                    System.out.println("Dirección agregada al pedido " + idPedidoDir);
                    break;
    
                case "20": // Agregar método de pago a pedido
                    System.out.print("ID del pedido: ");
                    int idPedidoPago = Integer.parseInt(sc.nextLine());
                    System.out.print("ID del método de pago: ");
                    int idPago = Integer.parseInt(sc.nextLine());
                    Pago metodoPago = cliente.getMetodosPago().stream().filter(p -> p.getId() == idPago).findFirst().orElse(null); // Buscar método de pago, usar stream para filtrar y obtener
                    if (metodoPago != null) {
                        cliente.agregarMetodoPago(metodoPago.getId(), idPedidoPago);
                        System.out.println("Método de pago agregado al pedido " + idPedidoPago);
                    } else {
                        System.out.println("Método de pago no encontrado");
                    }
                    break;
    
                case "21": // Cancelar pedido
                    System.out.print("ID del pedido a cancelar: ");
                    int idPedidoCancelar = Integer.parseInt(sc.nextLine());
                    cliente.cancelarPedido(idPedidoCancelar);
                    System.out.println("Pedido " + idPedidoCancelar + " cancelado");
                    break;
    
                // -- Sistema --
                case "22": // Cerrar sesión
                    cliente.cerrarSesion();
                    return;
    
                default:
                    System.out.println("Opción no válida");
            }    
        }
    }  
}
