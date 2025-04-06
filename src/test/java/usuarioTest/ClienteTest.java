package usuarioTest;

import modelo.pedido.Direccion;
import modelo.pedido.Pago;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;
import modelo.usuario.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de testeo unitario para Cliente
 */
public class ClienteTest {
    private Cliente cliente;
    private List<Pedido> pedidosSistema;
    private List<Producto> catalogo;
    private ArrayList<Cliente> clientes;
    private Direccion dir1, dir2;
    private Pago pago1, pago2;
    private Pedido pedido1, pedido2;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1, "Juan Perez", "jperez", "jperez@mail.com", "pass123", "123456789");
        pedidosSistema = new ArrayList<>();
        catalogo = new ArrayList<>();
        catalogo.add(new Producto(1, "Laptop", "Laptop de alta gama", 1000.0, 10));
        catalogo.add(new Producto(2, "Mouse", "Mouse inalámbrico", 20.0, 0)); // Sin inventario
        clientes = new ArrayList<>();
        clientes.add(cliente);

        // Agregar direcciones
        dir1 = new Direccion(1, "Calle 123", "Ciudad A", false);
        dir2 = new Direccion(2, "Avenida 456", "Ciudad B", true);
        cliente.getDirecciones().add(dir1);
        cliente.getDirecciones().add(dir2);

        // Agregar métodos de pago
        pago1 = new Pago(1, "Tarjeta de crédito", "1234-5678-9012-3456", "Disponible");
        pago2 = new Pago(2, "PayPal", "jperez@paypal.com", "Disponible");
        cliente.getMetodosPago().add(pago1);
        cliente.getMetodosPago().add(pago2);

        // Agregar pedidos
        pedido1 = new Pedido(1, new ArrayList<>(), dir1, pago1, "Pendiente", cliente.getId());
        pedido2 = new Pedido(2, new ArrayList<>(), dir2, pago2, "Completado", cliente.getId());
        cliente.getPedidos().add(pedido1);
        cliente.getPedidos().add(pedido2);
        pedidosSistema.add(pedido1);
        pedidosSistema.add(pedido2);

        // Agregar etiquetas a productos
        Etiqueta etiqueta1 = new Etiqueta(1, "Electrónica");
        Etiqueta etiqueta2 = new Etiqueta(2, "Accesorios");
        catalogo.get(0).getEtiquetas().add(etiqueta1); // Laptop tiene Electrónica
        Producto teclado = new Producto(3, "Teclado", "Teclado mecánico", 50.0, 5);
        teclado.getEtiquetas().add(etiqueta2); // Teclado tiene Accesorios
        catalogo.add(teclado);
    }

    @Test
    void iniciarSesionTest() {
        // Inicio exitoso
        assertTrue(cliente.iniciarSesion("jperez", "pass123"), "Debe permitir inicio con credenciales correctas");
        // Password incorrecta
        assertFalse(cliente.iniciarSesion("jperez", "wrongpass"), "Debe fallar con contraseña incorrecta");
        // Usuario incorrecto
        assertFalse(cliente.iniciarSesion("wronguser", "pass123"), "Debe fallar con usuario incorrecto");
        // Null usuario o password
        assertThrows(IllegalArgumentException.class, () -> cliente.iniciarSesion(null, "pass123"), "Debe lanzar excepción con usuario nulo");
        assertThrows(IllegalArgumentException.class, () -> cliente.iniciarSesion("jperez", null), "Debe lanzar excepción con contraseña nula");
    }

    @Test
    void crearPedidoTest() {
        Pedido pedido = cliente.crearPedido(pedidosSistema);
        // El pedido existe
        assertNotNull(pedido, "El pedido creado no debe ser nulo");
        // El pedido se agrega correctamente al sistema y al cliente
        assertEquals(3, pedidosSistema.size(), "El pedido debe añadirse a la lista del sistema");
        assertEquals(3, cliente.getPedidos().size(), "El pedido debe añadirse a los pedidos del cliente");
        // Error al pedir null
        assertThrows(IllegalArgumentException.class, () -> cliente.crearPedido(null), "Debe lanzar excepción con lista nula");
    }

    @Test
    void agregarProductoPedidoTest() {
        Pedido pedido = cliente.crearPedido(pedidosSistema);
        cliente.agregarProductoPedido(1, 2, pedido.getId(), catalogo);
        // Agregar producto
        assertEquals(1, pedido.getProductos().size(), "Debe agregar el producto al pedido");
        // El inventario debe de ajustarse correctamente
        assertEquals(8, catalogo.get(0).getInventario(), "El inventario debe reducirse en la cantidad solicitada");
        // Producto no existe
        assertThrows(IllegalArgumentException.class, () -> cliente.agregarProductoPedido(99, 1, pedido.getId(), catalogo), "Debe fallar si el producto no existe");
        // Pedido no existe
        assertThrows(IllegalArgumentException.class, () -> cliente.agregarProductoPedido(1, 1, 99, catalogo), "Debe fallar si el pedido no existe");
        // No hay inventario suficiente
        assertThrows(IllegalArgumentException.class, () -> cliente.agregarProductoPedido(1, 11, pedido.getId(), catalogo), "Debe fallar si no hay inventario suficiente");
    }

    @Test
    void editarDatosTest() {
        cliente.editarDatos("Juan Perez Nuevo", "jperez_nuevo@mail.com", "987654321");
        // Probar los 3 cambios
        assertEquals("Juan Perez Nuevo", cliente.getNombre(), "El nombre debe actualizarse");
        assertEquals("jperez_nuevo@mail.com", cliente.getCorreo(), "El correo debe actualizarse");
        assertEquals("987654321", cliente.getTelefono(), "El teléfono debe actualizarse");
        // Fallos null
        assertThrows(IllegalArgumentException.class, () -> cliente.editarDatos("", "jperez@mail.com", "123456789"), "Debe fallar con nombre vacío");
        assertThrows(IllegalArgumentException.class, () -> cliente.editarDatos("Juan", null, "123456789"), "Debe fallar con correo nulo");
        assertThrows(IllegalArgumentException.class, () -> cliente.editarDatos("Juan", "jperez@mail.com", ""), "Debe fallar con teléfono vacío");
    }

    @Test
    void eliminarCuentaTest() {
        // Eliminar de forma correcta
        System.setIn(new java.io.ByteArrayInputStream("pass123\n".getBytes()));
        cliente.eliminarCuenta(clientes);
        assertTrue(clientes.isEmpty(), "La cuenta debe eliminarse con contraseña correcta");
        // Eliminar de forma incorrecta
        clientes.add(cliente); // Restaurar para otra prueba
        System.setIn(new java.io.ByteArrayInputStream("wrongpass\n".getBytes()));
        assertThrows(IllegalArgumentException.class, () -> cliente.eliminarCuenta(clientes), "Debe fallar con contraseña incorrecta");
    }

    @Test
    void cerrarSesionTest() {
        // Este método solo imprime un mensaje, por lo que no requiere lógica compleja para probar
        // Podríamos capturar la salida de System.out, pero para simplicidad lo omitimos
        assertDoesNotThrow(() -> cliente.cerrarSesion(), "Cerrar sesión no debe lanzar excepciones");
    }

    @Test
    void consultarPedidosPasadosTest() {
        ArrayList<Pedido> pedidosPasados = cliente.consultarPedidosPasados();
        assertEquals(2, pedidosPasados.size(), "Debe devolver todos los pedidos del cliente");
        assertTrue(pedidosPasados.contains(pedido1), "Debe contener el pedido 1");
        assertTrue(pedidosPasados.contains(pedido2), "Debe contener el pedido 2");
    }

    @Test
    void consultarHistorialComprasTest() {
        ArrayList<Pedido> historial = cliente.consultarHistorialCompras();
        assertEquals(1, historial.size(), "Debe devolver solo los pedidos completados");
        assertEquals("Completado", historial.get(0).getEstado(), "El pedido debe estar completado");
    }

    @Test
    void verProductosTest() {
        ArrayList<Producto> disponibles = cliente.verProductos((ArrayList<Producto>) catalogo);
        assertEquals(2, disponibles.size(), "Debe devolver productos con inventario > 0");
        assertEquals("Laptop", disponibles.get(0).getNombre(), "Primer producto disponible");
        assertEquals("Teclado", disponibles.get(1).getNombre(), "Segundo producto disponible");
    }

    @Test
    void filtrarProductosPorEtiquetaTest() {
        Etiqueta etiqueta1 = new Etiqueta(1, "Electrónica");
        List<Producto> filtrados = cliente.filtrarProductosPorEtiqueta(etiqueta1, catalogo);
        assertEquals(1, filtrados.size(), "Debe devolver un producto con la etiqueta Electrónica");
        assertEquals("Laptop", filtrados.get(0).getNombre(), "El producto debe ser Laptop");

        Etiqueta etiqueta2 = new Etiqueta(2, "Accesorios");
        List<Producto> filtradosAccesorios = cliente.filtrarProductosPorEtiqueta(etiqueta2, catalogo);
        assertEquals(1, filtradosAccesorios.size(), "Debe devolver un producto con la etiqueta Accesorios");
        assertEquals("Teclado", filtradosAccesorios.get(0).getNombre(), "El producto debe ser Teclado");
    }

    @Test
    void consultarInventarioProductoTest() {
        int inventario = cliente.consultarInventarioProducto(1, catalogo);
        assertEquals(10, inventario, "Debe devolver el inventario correcto para Laptop");
        int inventarioNoExistente = cliente.consultarInventarioProducto(99, catalogo);
        assertEquals(0, inventarioNoExistente, "Debe devolver 0 si el producto no existe");
    }

    @Test
    void agregarDireccionEntregaTest() {
        Pedido nuevoPedido = cliente.crearPedido(pedidosSistema);
        cliente.agregarDireccionEntrega(1, nuevoPedido.getId());
        assertEquals("Calle 123", nuevoPedido.getDireccion().getCalle(), "La dirección debe asociarse al pedido");
        cliente.agregarDireccionEntrega(99, nuevoPedido.getId()); // Dirección no existente
        assertEquals("Calle 123", nuevoPedido.getDireccion().getCalle(), "No debe cambiar si la ID no existe");
    }

    @Test
    void agregarMetodoPagoTest() {
        Pedido nuevoPedido = cliente.crearPedido(pedidosSistema);
        cliente.agregarMetodoPago(2, nuevoPedido.getId());
        assertEquals("PayPal", nuevoPedido.getMetodoPago().getTipo(), "El método de pago debe asociarse al pedido");
        cliente.agregarMetodoPago(99, nuevoPedido.getId()); // Método no existente
        assertEquals("PayPal", nuevoPedido.getMetodoPago().getTipo(), "No debe cambiar si la ID no existe");
    }

    @Test
    void consultarEstadoPedidosTest() {
        ArrayList<Pedido> estadoPedidos = cliente.consultarEstadoPedidos();
        assertEquals(2, estadoPedidos.size(), "Debe devolver todos los pedidos del cliente");
    }

    @Test
    void cancelarPedidoTest() {
        cliente.cancelarPedido(1);
        assertEquals("Cancelado", pedido1.getEstado(), "El pedido 1 debe estar cancelado");
        assertEquals("Completado", pedido2.getEstado(), "El pedido 2 no debe cambiar");
    }

    @Test
    void agregarMetodoPagoSistemaTest() {
        Pago nuevoPago = new Pago(3, "Efectivo", "N/A", "Disponible");
        cliente.agregarMetodoPagoSistema(nuevoPago);
        assertEquals(3, cliente.getMetodosPago().size(), "Debe haber un nuevo método de pago");
        assertEquals("Efectivo", cliente.getMetodosPago().get(2).getTipo(), "El nuevo método debe ser Efectivo");
    }

    @Test
    void editarMetodoPagoTest() {
        Pago nuevosDatos = new Pago(1, "Tarjeta de débito", "9876-5432-1098-7654", "Disponible");
        cliente.editarMetodoPago(0, nuevosDatos);
        assertEquals("9876-5432-1098-7654", cliente.getMetodosPago().get(0).getDatos(), "Los datos deben actualizarse");
    }

    @Test
    void eliminarMetodoPagoTest() {
        cliente.eliminarMetodoPago(0);
        assertEquals("Eliminado", cliente.getMetodosPago().get(0).getEstado(), "El método de pago debe estar marcado como Eliminado");
    }

    @Test
    void verMetodosPagoGuardadosTest() {
        ArrayList<Pago> metodos = cliente.verMetodosPagoGuardados();
        assertEquals(2, metodos.size(), "Debe devolver los dos métodos de pago");
        assertEquals("Tarjeta de crédito", metodos.get(0).getTipo(), "El primer método debe ser Tarjeta de crédito");
    }

    @Test
    void verDireccionesEntregaTest() {
        ArrayList<Direccion> direcciones = cliente.verDireccionesEntrega();
        assertEquals(2, direcciones.size(), "Debe devolver las dos direcciones");
        assertEquals("Calle 123", direcciones.get(0).getCalle(), "La primera dirección debe ser Calle 123");
    }

    @Test
    void agregarNuevaDireccionTest() {
        cliente.agregarNuevaDireccion("Nueva Calle", "Nueva Ciudad");
        assertEquals(3, cliente.getDirecciones().size(), "Debe haber una nueva dirección");
        assertEquals("Nueva Calle", cliente.getDirecciones().get(2).getCalle(), "La nueva dirección debe tener la calle correcta");
    }

    @Test
    void editarDireccionTest() {
        cliente.editarDireccion(1, "Calle Editada", "Ciudad Editada");
        assertEquals("Calle Editada", cliente.getDirecciones().get(0).getCalle(), "La calle debe actualizarse");
        assertEquals("Ciudad Editada", cliente.getDirecciones().get(0).getCiudad(), "La ciudad debe actualizarse");
    }

    @Test
    void eliminarDireccionTest() {
        cliente.eliminarDireccion(1);
        assertEquals(1, cliente.getDirecciones().size(), "Debe quedar solo una dirección");
        assertEquals(2, cliente.getDirecciones().get(0).getId(), "La dirección restante debe ser la ID 2");
    }
}