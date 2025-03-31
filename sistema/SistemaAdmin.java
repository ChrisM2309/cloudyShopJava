package sistema;

import modelo.pedido.Direccion;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;
import modelo.usuario.Admin;
import modelo.usuario.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona el menú interactivo para los administradores.
 */
public class SistemaAdmin {
    private Scanner sc;
    /**
     * Constructor que inicializa el sistema con un scanner.
     * @param sc El scanner para leer la entrada del usuario.
     */
    public SistemaAdmin(Scanner sc){
        this.sc = sc;
    }
    /**
     * Muestra y gestiona el menú interactivo para el administrador.
     * @param admin El administrador que utiliza el menú.
     * @param etiquetas Lista de etiquetas en el sistema.
     * @param empleados Lista de empleados registrados.
     * @param catalogo Lista de productos disponibles.
     * @param pedidos Lista de pedidos en el sistema.
     * @param puntosEntrega Lista de puntos de entrega.
     */
    public void menuAdmin(Admin admin, ArrayList<Etiqueta> etiquetas, ArrayList<Empleado> empleados,
                           ArrayList<Producto> catalogo, ArrayList<Pedido> pedidos,
                           ArrayList<Direccion> puntosEntrega) {
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

}
