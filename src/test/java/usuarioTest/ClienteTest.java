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
 * Clase de pruebas unitarias para la clase {@link Cliente}.
 * Verifica el comportamiento de los métodos de la clase Cliente, incluyendo autenticación,
 * gestión de pedidos, productos, direcciones y métodos de pago.
 *
 */
public class ClienteTest {
    /** Clase de Test para {@link Cliente}*/
    public ClienteTest(){}
    /** Cliente utilizado en las pruebas. */
    private Cliente cliente;
    /** Lista de pedidos en el sistema para las pruebas. */
    private ArrayList<Pedido> pedidosSistema;
    /** Catálogo de productos disponibles para las pruebas. */
    private ArrayList<Producto> catalogo;
    /** Lista de clientes de prueba registrados en el sistema. */
    private ArrayList<Cliente> clientes;
    /** Direcciones de entrega para pruebas. */
    private Direccion dir1, dir2;
    /** Métodos de pago para pruebas. */
    private Pago pago1, pago2;
    /** Pedidos asociados al cliente para pruebas. */
    private Pedido pedido1, pedido2;

    /**
     * Configura el entorno de prueba antes de cada test.
     * Inicializa el cliente, catálogo, pedidos, direcciones y métodos de pago con datos de prueba.
     */
    @BeforeEach
    void setUp() {
        // Crear cliente y catalogo de prueba
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

        // Agregar pedidos a al cliente y sistema
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
    /**
     * Prueba el método {@link Cliente#iniciarSesion(String, String)}.
     * Verifica el inicio de sesión con credenciales correctas e incorrectas,
     * así como casos con valores nulos.
     */
    @Test
    void iniciarSesionTest() {
        // Caso 1: Inicio exitoso
        assertTrue(cliente.iniciarSesion("jperez", "pass123"), "Debe permitir inicio con credenciales correctas");
        // Caso 2: Contraseña incorrecta
        assertFalse(cliente.iniciarSesion("jperez", "wrongpass"), "Debe fallar con contraseña incorrecta");
        // Caso 3: Usuario incorrecto
        assertFalse(cliente.iniciarSesion("wronguser", "pass123"), "Debe fallar con usuario incorrecto");
        // Caso 4: Usuario nulo
        assertFalse(cliente.iniciarSesion(null, "pass123"), "Debe retornar false con usuario nulo");
        // Caso 5: Contraseña nula
        assertFalse(cliente.iniciarSesion("jperez", null), "Debe retornar false con contraseña nula");    }

    /**
     * Prueba el método {@link Cliente#crearPedido(List)}.
     * Verifica que se cree un nuevo pedido y se añada correctamente al sistema
     * y a la lista de pedidos del cliente.
     */
    @Test
    void crearPedidoTest() {
        Pedido pedido = cliente.crearPedido(pedidosSistema);
        // El pedido existe
        assertNotNull(pedido, "El pedido creado no debe ser nulo");
        // El pedido se agrega correctamente al sistema y al cliente
        assertEquals(3, pedidosSistema.size(), "El pedido debe añadirse a la lista del sistema");
        assertEquals(3, cliente.getPedidos().size(), "El pedido debe añadirse a los pedidos del cliente");
        // Error al pedir null
        assertNull(cliente.crearPedido(null), "Debe retornar null con lista nula");    }

    /**
     * Prueba el método {@link Cliente#agregarProductoPedido(int, int, int, ArrayList)}.
     * Verifica la adición de productos a un pedido y el ajuste del inventario.
     * Verifica excepciones por valores nulos
     */
    @Test
    void agregarProductoPedidoTest() {
        Pedido pedido = cliente.crearPedido(pedidosSistema);
        cliente.agregarProductoPedido(1, 2, pedido.getId(), catalogo);
        assertEquals(1, pedido.getProductos().size(), "Debe agregar el producto al pedido");
        assertEquals(8, catalogo.get(0).getInventario(), "El inventario debe reducirse en 2");

        // Manejo de nulls
        int productosAntes = pedido.getProductos().size();
        cliente.agregarProductoPedido(99, 1, pedido.getId(), catalogo);
        assertEquals(productosAntes, pedido.getProductos().size(), "No debe agregar producto inexistente");

        cliente.agregarProductoPedido(1, 1, 99, catalogo);
        assertEquals(productosAntes, pedido.getProductos().size(), "No debe agregar a pedido inexistente");

        cliente.agregarProductoPedido(1, 11, pedido.getId(), catalogo);
        assertEquals(productosAntes, pedido.getProductos().size(), "No debe agregar si inventario es insuficiente");    }

    /**
     * Prueba el método {@link Cliente#editarDatos(String, String, String)}.
     * Verifica la actualización correcta de los datos del cliente.
     */
    @Test
    void editarDatosTest() {
        cliente.editarDatos("Juan Perez Nuevo", "jperez_nuevo@mail.com", "987654321");
        // Probar los 3 cambios
        assertEquals("Juan Perez Nuevo", cliente.getNombre(), "El nombre debe actualizarse");
        assertEquals("jperez_nuevo@mail.com", cliente.getCorreo(), "El correo debe actualizarse");
        assertEquals("987654321", cliente.getTelefono(), "El teléfono debe actualizarse");
        // Fallos null
        String nombreOriginal = cliente.getNombre();
        cliente.editarDatos("", "jperez@mail.com", "123456789");
        assertEquals(nombreOriginal, cliente.getNombre(), "No debe actualizarse con nombre vacío");

        String correoOriginal = cliente.getCorreo();
        cliente.editarDatos("Juan", null, "123456789");
        assertEquals(correoOriginal, cliente.getCorreo(), "No debe actualizarse con correo nulo");

        String telefonoOriginal = cliente.getTelefono();
        cliente.editarDatos("Juan", "jperez@mail.com", "");
        assertEquals(telefonoOriginal, cliente.getTelefono(), "No debe actualizarse con teléfono vacío");    }

    /**
     * Prueba el método {@link Cliente#eliminarCuenta(ArrayList)}.
     * Verifica la eliminación de la cuenta del cliente con contraseña correcta.
     */
    @Test
    void eliminarCuentaTest() {
        // Eliminar de forma correcta
        System.setIn(new java.io.ByteArrayInputStream("pass123\n".getBytes()));
        cliente.eliminarCuenta(clientes);
        assertTrue(clientes.isEmpty(), "La cuenta debe eliminarse con contraseña correcta");
        // Eliminar de forma incorrecta
        clientes.add(cliente); // Restaurar para otra prueba
        System.setIn(new java.io.ByteArrayInputStream("wrongpass\n".getBytes()));
        cliente.eliminarCuenta(clientes);
        int tamanoAntes = clientes.size();
        assertEquals(tamanoAntes, clientes.size(), "No debe eliminarse con contraseña incorrecta");
    }
    /**
     * Prueba el método {@link Cliente#cerrarSesion()}.
     * Verifica que no lance excepciones al cerrar sesión.
     */
    @Test
    void cerrarSesionTest() {
        // Este método solo imprime un mensaje, por lo que no requiere lógica compleja para probar
        // Podríamos capturar la salida de System.out, pero para simplicidad lo omitimos
        assertDoesNotThrow(() -> cliente.cerrarSesion(), "Cerrar sesión no debe lanzar excepciones");
    }
    /**
     * Prueba el método {@link Cliente#consultarPedidosPasados()}.
     * Verifica que devuelva todos los pedidos del cliente.
     */
    @Test
    void consultarPedidosPasadosTest() {
        ArrayList<Pedido> pedidosPasados = cliente.consultarPedidosPasados();
        assertEquals(2, pedidosPasados.size(), "Debe devolver todos los pedidos del cliente");
        assertTrue(pedidosPasados.contains(pedido1), "Debe contener el pedido 1");
        assertTrue(pedidosPasados.contains(pedido2), "Debe contener el pedido 2");
    }
    /**
     * Prueba el método {@link Cliente#consultarHistorialCompras()}.
     * Verifica que devuelva solo los pedidos completados.
     */
    @Test
    void consultarHistorialComprasTest() {
        ArrayList<Pedido> historial = cliente.consultarHistorialCompras();
        assertEquals(1, historial.size(), "Debe devolver solo los pedidos completados");
        assertEquals("Completado", historial.get(0).getEstado(), "El pedido debe estar completado");
    }
    /**
     * Prueba el método {@link Cliente#verProductos(ArrayList)}.
     * Verifica que devuelva solo productos con inventario disponible.
     */
    @Test
    void verProductosTest() {
        ArrayList<Producto> disponibles = cliente.verProductos((ArrayList<Producto>) catalogo);
        assertEquals(2, disponibles.size(), "Debe devolver productos con inventario > 0");
        assertEquals("Laptop", disponibles.get(0).getNombre(), "Primer producto disponible");
        assertEquals("Teclado", disponibles.get(1).getNombre(), "Segundo producto disponible");
    }

    /**
     * Prueba el método {@link Cliente#filtrarProductosPorEtiqueta(Etiqueta, List)}.
     * Verifica el filtrado de productos por etiqueta.
     */
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

    /**
     * Prueba el método {@link Cliente#consultarInventarioProducto(int, List)}.
     * Verifica la consulta del inventario de un producto.
     */
    @Test
    void consultarInventarioProductoTest() {
        int inventario = cliente.consultarInventarioProducto(1, catalogo);
        assertEquals(10, inventario, "Debe devolver el inventario correcto para Laptop");
        int inventarioNoExistente = cliente.consultarInventarioProducto(99, catalogo);
        assertEquals(0, inventarioNoExistente, "Debe devolver 0 si el producto no existe");
    }

    /**
     * Prueba el método {@link Cliente#agregarDireccionEntrega(int, int)}.
     * Verifica la asociación de una dirección a un pedido.
     */
    @Test
    void agregarDireccionEntregaTest() {
        Pedido nuevoPedido = cliente.crearPedido(pedidosSistema);
        cliente.agregarDireccionEntrega(1, nuevoPedido.getId());
        assertEquals("Calle 123", nuevoPedido.getDireccion().getCalle(), "La dirección debe asociarse al pedido");
        cliente.agregarDireccionEntrega(99, nuevoPedido.getId()); // Dirección no existente
        assertEquals("Calle 123", nuevoPedido.getDireccion().getCalle(), "No debe cambiar si la ID no existe");
    }

    /**
     * Prueba el método {@link Cliente#agregarMetodoPago(int, int)}.
     * Verifica la asociación de un método de pago a un pedido.
     */
    @Test
    void agregarMetodoPagoTest() {
        Pedido nuevoPedido = cliente.crearPedido(pedidosSistema);
        cliente.agregarMetodoPago(2, nuevoPedido.getId());
        assertEquals("PayPal", nuevoPedido.getMetodoPago().getTipo(), "El método de pago debe asociarse al pedido");
        cliente.agregarMetodoPago(99, nuevoPedido.getId()); // Método no existente
        assertEquals("PayPal", nuevoPedido.getMetodoPago().getTipo(), "No debe cambiar si la ID no existe");
    }

    /**
     * Prueba el método {@link Cliente#consultarEstadoPedidos()}.
     * Verifica que devuelva todos los pedidos del cliente.
     */
    @Test
    void consultarEstadoPedidosTest() {
        ArrayList<Pedido> estadoPedidos = cliente.consultarEstadoPedidos();
        assertEquals(2, estadoPedidos.size(), "Debe devolver todos los pedidos del cliente");
    }

    /**
     * Prueba el método {@link Cliente#cancelarPedido(int)}.
     * Verifica la cancelación de un pedido.
     */
    @Test
    void cancelarPedidoTest() {
        cliente.cancelarPedido(1);
        assertEquals("Cancelado", pedido1.getEstado(), "El pedido 1 debe estar cancelado");
        assertEquals("Completado", pedido2.getEstado(), "El pedido 2 no debe cambiar");
    }

    /**
     * Prueba el método {@link Cliente#agregarMetodoPagoSistema(Pago)}.
     * Verifica la adición de un nuevo método de pago al cliente.
     */
    @Test
    void agregarMetodoPagoSistemaTest() {
        Pago nuevoPago = new Pago(3, "Efectivo", "N/A", "Disponible");
        cliente.agregarMetodoPagoSistema(nuevoPago);
        assertEquals(3, cliente.getMetodosPago().size(), "Debe haber un nuevo método de pago");
        assertEquals("Efectivo", cliente.getMetodosPago().get(2).getTipo(), "El nuevo método debe ser Efectivo");
    }

    /**
     * Prueba el método {@link Cliente#editarMetodoPago(int, Pago)}.
     * Verifica la actualización de un método de pago existente.
     */
    @Test
    void editarMetodoPagoTest() {
        Pago nuevosDatos = new Pago(1, "Tarjeta de débito", "9876-5432-1098-7654", "Disponible");
        cliente.editarMetodoPago(0, nuevosDatos);
        assertEquals("9876-5432-1098-7654", cliente.getMetodosPago().get(0).getDatos(), "Los datos deben actualizarse");
    }

    /**
     * Prueba el método {@link Cliente#eliminarMetodoPago(int)}.
     * Verifica la eliminación (marcado como eliminado) de un método de pago.
     */
    @Test
    void eliminarMetodoPagoTest() {
        cliente.eliminarMetodoPago(0);
        assertEquals("Eliminado", cliente.getMetodosPago().get(0).getEstado(), "El método de pago debe estar marcado como Eliminado");
    }

    /**
     * Prueba el método {@link Cliente#verMetodosPagoGuardados()}.
     * Verifica que devuelva los métodos de pago del cliente.
     */
    @Test
    void verMetodosPagoGuardadosTest() {
        ArrayList<Pago> metodos = cliente.verMetodosPagoGuardados();
        assertEquals(2, metodos.size(), "Debe devolver los dos métodos de pago");
        assertEquals("Tarjeta de crédito", metodos.get(0).getTipo(), "El primer método debe ser Tarjeta de crédito");
    }

    /**
     * Prueba el método {@link Cliente#verDireccionesEntrega()}.
     * Verifica que devuelva las direcciones del cliente.
     */
    @Test
    void verDireccionesEntregaTest() {
        ArrayList<Direccion> direcciones = cliente.verDireccionesEntrega();
        assertEquals(2, direcciones.size(), "Debe devolver las dos direcciones");
        assertEquals("Calle 123", direcciones.get(0).getCalle(), "La primera dirección debe ser Calle 123");
    }

    /**
     * Prueba el método {@link Cliente#agregarNuevaDireccion(String, String)}.
     * Verifica la adición de una nueva dirección al cliente.
     */
    @Test
    void agregarNuevaDireccionTest() {
        cliente.agregarNuevaDireccion("Nueva Calle", "Nueva Ciudad");
        assertEquals(3, cliente.getDirecciones().size(), "Debe haber una nueva dirección");
        assertEquals("Nueva Calle", cliente.getDirecciones().get(2).getCalle(), "La nueva dirección debe tener la calle correcta");
    }


    /**
     * Prueba el método {@link Cliente#editarDireccion(int, String, String)}.
     * Verifica la actualización de una dirección existente.
     */
    @Test
    void editarDireccionTest() {
        cliente.editarDireccion(1, "Calle Editada", "Ciudad Editada");
        assertEquals("Calle Editada", cliente.getDirecciones().get(0).getCalle(), "La calle debe actualizarse");
        assertEquals("Ciudad Editada", cliente.getDirecciones().get(0).getCiudad(), "La ciudad debe actualizarse");
    }

    /**
     * Prueba el método {@link Cliente#eliminarDireccion(int)}.
     * Verifica la eliminación de una dirección del cliente.
     */
    @Test
    void eliminarDireccionTest() {
        cliente.eliminarDireccion(1);
        assertEquals(1, cliente.getDirecciones().size(), "Debe quedar solo una dirección");
        assertEquals(2, cliente.getDirecciones().get(0).getId(), "La dirección restante debe ser la ID 2");
    }
}