package sistema;

import modelo.pedido.Direccion;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;
import modelo.usuario.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona el menú interactivo para los empleados.
 */
public class SistemaEmpleado {
    private Scanner sc;

    /**
     * Constructor que inicializa el sistema con un scanner.
     * @param sc El scanner para leer la entrada del usuario.
     */
    public SistemaEmpleado(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Muestra y gestiona el menú interactivo para el empleado.
     * @param empleado El empleado que utiliza el menú.
     * @param catalogo Lista de productos disponibles.
     * @param pedidos Lista de pedidos en el sistema.
     * @param etiquetas Lista de etiquetas para productos.
     */
    public void menuEmpleado(Empleado empleado, ArrayList<Producto> catalogo, ArrayList<Pedido> pedidos,
                             ArrayList<Etiqueta> etiquetas) {
        while (true) {
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
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    List<Producto> productos = empleado.consultarProductosCatalogo(catalogo);
                    System.out.println("Catálogo: ");
                    for (Producto p : productos) {
                        System.out.println(p);
                    }
                    break;

                case "2":
                    int idProducto = catalogo.size() + 1;
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

                case "3":
                    for (Producto producto : catalogo) {
                        System.out.println(producto);
                    }
                    System.out.print("ID del producto a eliminar: ");
                    int idEliminar = Integer.parseInt(sc.nextLine());
                    empleado.eliminarProductoCatalogo(idEliminar, catalogo);
                    System.out.println("Producto eliminado");
                    break;

                case "4":
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

                case "5":
                    int idProdEtiqueta = etiquetas.size() + 1;
                    System.out.print("ID de la etiqueta: ");
                    int idEtiqueta = Integer.parseInt(sc.nextLine());
                    Etiqueta etiqueta = etiquetas.stream()
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

                case "6":
                    for (Producto producto : catalogo) {
                        System.out.println(producto);
                    }
                    System.out.print("ID del producto: ");
                    int idProdEliminarEtiqueta = Integer.parseInt(sc.nextLine());
                    System.out.print("ID de la etiqueta: ");
                    int idEtiquetaEliminar = Integer.parseInt(sc.nextLine());
                    Etiqueta etiquetaEliminar = etiquetas.stream()
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

                case "7":
                    System.out.print("ID del producto: ");
                    int idConsultarInventario = Integer.parseInt(sc.nextLine());
                    int cantidad = empleado.consultarInventarioProducto(idConsultarInventario, catalogo);
                    System.out.println("Inventario del producto: " + cantidad);
                    break;

                case "8":
                    System.out.print("ID del producto: ");
                    int idRegistrarEntrada = Integer.parseInt(sc.nextLine());
                    System.out.print("Cantidad a agregar: ");
                    int cantidadAgregar = Integer.parseInt(sc.nextLine());
                    empleado.registrarEntradaInventario(idRegistrarEntrada, cantidadAgregar, catalogo);
                    System.out.println("Entrada de inventario registrada");
                    break;

                case "9":
                    List<Producto> alertas = empleado.recibirAlertasInventarioBajo(catalogo);
                    System.out.println("Productos con inventario bajo: " + alertas);
                    break;

                case "10":
                    System.out.print("ID del pedido: ");
                    int idPedidoActualizar = Integer.parseInt(sc.nextLine());
                    System.out.print("Nuevo estado: ");
                    String nuevoEstado = sc.nextLine();
                    empleado.actualizarEstadoPedido(idPedidoActualizar, nuevoEstado, pedidos);
                    System.out.println("Estado del pedido actualizado");
                    break;

                case "11":
                    System.out.print("ID del pedido a cancelar: ");
                    int idPedidoCancelar = Integer.parseInt(sc.nextLine());
                    empleado.cancelarPedido(idPedidoCancelar, pedidos);
                    System.out.println("Pedido cancelado");
                    break;

                case "12":
                    List<Pedido> pendientes = empleado.verPedidosPendientes(pedidos);
                    System.out.println("Pedidos pendientes: " + pendientes);
                    break;

                case "13":
                    System.out.print("ID del pago: ");
                    int idPagoVerificar = Integer.parseInt(sc.nextLine());
                    boolean pagoProcesado = empleado.verificarEstadoPago(idPagoVerificar, pedidos);
                    System.out.println("Estado del pago: " + (pagoProcesado ? "Procesado" : "No procesado"));
                    break;

                case "14":
                    System.out.print("ID del pedido: ");
                    int idPedidoDireccion = Integer.parseInt(sc.nextLine());
                    Direccion direccion = empleado.consultarDireccionPedido(idPedidoDireccion, pedidos);
                    if (direccion != null) {
                        System.out.println("Dirección del pedido: " + direccion);
                    } else {
                        System.out.println("Pedido no encontrado o sin dirección");
                    }
                    break;

                case "15":
                    empleado.cerrarSesion();
                    return;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}