package sistema;

import modelo.pedido.Direccion;
import modelo.pedido.Pago;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;
import modelo.usuario.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona el menú interactivo para los clientes.
 */
public class SistemaCliente {
    private Scanner sc;

    /**
     * Constructor que inicializa el sistema con un scanner.
     * @param sc El scanner para leer la entrada del usuario.
     */
    public SistemaCliente(Scanner sc){
        this.sc = sc;
    }
    /**
     * Muestra y gestiona el menú interactivo para el cliente.
     * @param cliente El cliente que utiliza el menú.
     * @param catalogo Lista de productos disponibles.
     * @param pedidos Lista de pedidos en el sistema.
     * @param etiquetas Lista de etiquetas para filtrar productos.
     * @param clientes Lista de clientes registrados.
     */
    public void menuCliente(Cliente cliente, ArrayList<Producto> catalogo, ArrayList<Pedido> pedidos,
                             ArrayList<Etiqueta> etiquetas, ArrayList<Cliente> clientes) {
        while (true) {
            // Muestra el menú organizado por categorías
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
                    List<Producto> productos = cliente.verProductos(catalogo);
                    System.out.println("Catálogo:\n ");
                    for (Producto p : productos){
                        System.out.println("- " + p);
                    }
                    break;

                case "12": // Filtrar productos por etiqueta
                    System.out.print("Etiquetas:\n");
                    for (Etiqueta p : etiquetas){
                        System.out.println("- " + p);
                    }
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
                        System.out.println("Productos filtrados: ");
                        for (Producto p : productosFiltrados){
                            System.out.println("- " + p);
                        }
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
