import modelo.pedido.Direccion;
import modelo.pedido.Pago;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;
import modelo.usuario.Admin;
import modelo.usuario.Cliente;
import modelo.usuario.Empleado;
import sistema.SistemaAdmin;
import sistema.SistemaCliente;
import sistema.SistemaEmpleado;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal que coordina la ejecución del sistema CloudyShop.
 * Gestiona la inicialización de datos, la interacción con el usuario a través de un menú interactivo,
 * y delega las operaciones a las clases correspondientes según la opción seleccionada por el usuario.
 */
public class Main {

    private static ArrayList<Empleado> empleados = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Producto> catalogo = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();
    private static ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    private static ArrayList<Direccion> puntosEntrega = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    /**
     * Constructor por defecto de la clase Main.
     * Inicializa la clase sin realizar operaciones adicionales.
     */
    public Main() {
        // No se necesitan operaciones aquí
    }

    /**
     * Método principal que inicia el sistema CloudyShop.
     * Presenta un menú interactivo que permite al usuario iniciar sesión como administrador,
     * empleado o cliente, registrarse como cliente, o salir del sistema. Los argumentos de la
     * línea de comandos no se utilizan en esta implementación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        inicializarDatos();
        while (true) {
            System.out.println("\nBienvenido al sistema de CloudyShop");
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

    /**
     * Inicializa datos de prueba para el sistema.
     * Crea y configura instancias de administradores, empleados, clientes, etiquetas, productos,
     * puntos de entrega y pedidos para simular un entorno con datos preexistentes.
     */
    public static void inicializarDatos() {
        // 1. INICIALIZACIÓN DE USUARIOS

        // Crear administrador
        admins.add(new Admin(1, "Juan Admin", "admin1", "admin1@empresa.com", "admin123"));

        // Crear empleado
        empleados.add(new Empleado(1, "Ana Lucia", "empleado1", "ana@empresa.com", "emp123"));

        // Crear cliente con datos básicos
        clientes.add(new Cliente(1, "María Jose", "cliente1", "maria@cliente.com", "cli123", "555-1234"));

        // 2. CONFIGURACIÓN DE ETIQUETAS

        // Agregar etiquetas para categorizar productos
        etiquetas.add(new Etiqueta(1, "Electrónica"));
        etiquetas.add(new Etiqueta(2, "Hogar"));

        // 3. CONFIGURACIÓN DEL CATÁLOGO DE PRODUCTOS

        // Producto 1: Laptop (Electrónica)
        catalogo.add(new Producto(1, "Laptop", "Laptop de alta gama", 1000.0, 10));
        catalogo.getLast().agregarEtiqueta(etiquetas.get(0)); // Asignar etiqueta "Electrónica"

        // Producto 2: Silla (Hogar)
        catalogo.add(new Producto(2, "Silla", "Silla ergonómica", 150.0, 20));
        catalogo.getLast().agregarEtiqueta(etiquetas.getLast()); // Asignar etiqueta "Hogar"

        // Producto 3: Teléfono (Electrónica)
        catalogo.add(new Producto(3, "Teléfono", "Smartphone moderno", 500.0, 15));
        catalogo.getLast().agregarEtiqueta(etiquetas.get(0)); // Asignar etiqueta "Electrónica"

        // 4. CONFIGURACIÓN DEL CLIENTE

        // Agregar dirección al cliente
        clientes.get(0).agregarNuevaDireccion("Calle 123", "San Salvador");

        // Agregar método de pago al cliente
        Pago pago1 = new Pago(clientes.get(0).getMetodosPago().size() + 1, "Tarjeta", "123", "Activo");
        clientes.get(0).agregarMetodoPagoSistema(pago1);

        // CONFIGURACIÓN DE PUNTOS DE ENTREGA

        // Agregar punto de entrega predeterminado
        puntosEntrega.add(new Direccion(1, "Punto Central", "Ciudad C", true));

        // CREACIÓN DE PEDIDOS DE EJEMPLO

        // Pedido 1
        clientes.get(0).crearPedido(pedidos);
        clientes.get(0).agregarProductoPedido(1, 1, 1, catalogo); // Agregar Laptop
        clientes.get(0).agregarProductoPedido(2, 1, 1, catalogo); // Agregar Teléfono
        clientes.get(0).agregarDireccionEntrega(1, 1); // Asignar dirección
        clientes.get(0).agregarMetodoPago(1, 1); // Asignar método de pago

        // Pedido 2
        clientes.get(0).crearPedido(pedidos);
        clientes.get(0).agregarProductoPedido(1, 1, 2, catalogo); // Agregar Laptop
        clientes.get(0).agregarDireccionEntrega(1, 2); // Asignar dirección
        clientes.get(0).agregarMetodoPago(1, 2); // Asignar método de pago

        // Actualizar estado del segundo pedido a "Completado"
        empleados.get(0).actualizarEstadoPedido(2, "Completado", pedidos);
    }

    /**
     * Maneja el proceso de inicio de sesión y el menú para un administrador.
     * Solicita las credenciales al usuario, las verifica y, si son correctas, inicia la sesión
     * y muestra el menú correspondiente al administrador.
     */
    public static void manejarAdmin() {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();
        Admin admin = buscarAdmin(usuario, password);
        if (admin != null) {
            System.out.println("Sesión iniciada como administrador");
            SistemaAdmin sistemaAdmin = new SistemaAdmin(sc);
            sistemaAdmin.menuAdmin(admin, etiquetas, empleados, catalogo, pedidos, puntosEntrega);
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

    /**
     * Maneja el proceso de inicio de sesión y el menú para un empleado.
     * Solicita las credenciales al usuario, las verifica y, si son correctas, inicia la sesión
     * y muestra el menú correspondiente al empleado.
     */
    public static void manejarEmpleado() {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine();
        Empleado empleado = buscarEmpleado(usuario, contraseña);
        if (empleado != null) {
            System.out.println("Sesión iniciada como empleado");
            SistemaEmpleado sistemaEmpleado = new SistemaEmpleado(sc);
            sistemaEmpleado.menuEmpleado(empleado, catalogo, pedidos, etiquetas);
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

    /**
     * Maneja el proceso de inicio de sesión y el menú para un cliente.
     * Solicita las credenciales al usuario, las verifica y, si son correctas, inicia la sesión
     * y muestra el menú correspondiente al cliente.
     */
    public static void manejarCliente() {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();
        Cliente cliente = buscarCliente(usuario, password);
        if (cliente != null) {
            System.out.println("Sesión iniciada como cliente");
            SistemaCliente sistemaCliente = new SistemaCliente(sc);
            sistemaCliente.menuCliente(cliente, catalogo, pedidos, etiquetas, clientes);
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

    /**
     * Registra un nuevo cliente en el sistema.
     * Solicita al usuario los datos necesarios (nombre, usuario, correo, contraseña y teléfono)
     * para crear una nueva cuenta de cliente y la agrega a la lista de clientes.
     */
    public static void registrarCliente() {
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

    /**
     * Busca un administrador en la lista de administradores según su usuario y contraseña.
     *
     * @param usuario El nombre de usuario del administrador.
     * @param contraseña La contraseña del administrador.
     * @return El objeto {@code Admin} si las credenciales son correctas, o {@code null} si no se encuentra.
     */
    private static Admin buscarAdmin(String usuario, String contraseña) {
        for (Admin admin : admins) {
            if (admin.iniciarSesion(usuario, contraseña)) {
                return admin;
            }
        }
        return null;
    }

    /**
     * Busca un empleado en la lista de empleados según su usuario y contraseña.
     *
     * @param usuario El nombre de usuario del empleado.
     * @param contraseña La contraseña del empleado.
     * @return El objeto {@code Empleado} si las credenciales son correctas, o {@code null} si no se encuentra.
     */
    private static Empleado buscarEmpleado(String usuario, String contraseña) {
        for (Empleado empleado : empleados) {
            if (empleado.iniciarSesion(usuario, contraseña)) {
                return empleado;
            }
        }
        return null;
    }

    /**
     * Busca un cliente en la lista de clientes según su usuario y contraseña.
     *
     * @param usuario El nombre de usuario del cliente.
     * @param contraseña La contraseña del cliente.
     * @return El objeto {@code Cliente} si las credenciales son correctas, o {@code null} si no se encuentra.
     */
    private static Cliente buscarCliente(String usuario, String contraseña) {
        for (Cliente cliente : clientes) {
            if (cliente.iniciarSesion(usuario, contraseña)) {
                return cliente;
            }
        }
        return null;
    }
}