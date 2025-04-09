package usuarioTest;

import modelo.pedido.Direccion;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;
import modelo.usuario.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * Clase de testeo unitario para {@link Empleado}.
 * Verifica el comportamiento de los métodos de la clase Empleado, incluyendo autenticación,
 * gestión de productos, pedidos y operaciones de inventario.
 */
public class EmpleadoTest {
    /** Empleado utilizado en las pruebas */
    private Empleado empleado;
    /** Catálogo de productos para pruebas */
    private ArrayList<Producto> catalogo;
    /** Lista de pedidos para pruebas */
    private ArrayList<Pedido> pedidos;
    /** Productos de prueba */
    private Producto producto1, producto2;
    /** Pedidos de prueba */
    private Pedido pedido1, pedido2;
    /** Etiqueta de prueba */
    private Etiqueta etiqueta1;

    /**
     * Configura el entorno de prueba antes de cada test.
     * Inicializa el empleado, catálogo, pedidos y productos con datos de prueba.
     */
    @BeforeEach
    void setUp() {
        empleado = new Empleado(1, "Ana Lucia", "empleado1", "ana@empresa.com", "emp123");
        catalogo = new ArrayList<>();
        pedidos = new ArrayList<>();

        // Inicializar productos
        producto1 = new Producto(1, "Laptop", "Laptop de alta gama", 1000.0, 10);
        producto2 = new Producto(2, "Mouse", "Mouse inalámbrico", 20.0, 3);
        catalogo.add(producto1);
        catalogo.add(producto2);

        // Inicializar pedidos
        pedido1 = new Pedido(1, new ArrayList<>(), new Direccion(1, "Calle 123", "Ciudad A", false), null, "Pendiente", 1);
        pedido2 = new Pedido(2, new ArrayList<>(), new Direccion(2, "Avenida 456", "Ciudad B", true), null, "Completado", 1);
        pedidos.add(pedido1);
        pedidos.add(pedido2);

        // Inicializar etiqueta
        etiqueta1 = new Etiqueta(1, "Electrónica");
    }

    /**
     * Prueba el método {@link Empleado#iniciarSesion(String, String)}.
     * Verifica el inicio de sesión con credenciales correctas e incorrectas.
     */
    @Test
    void iniciarSesionTest() {
        // Correcto
        assertTrue(empleado.iniciarSesion("empleado1", "emp123"), "Debe permitir inicio con credenciales correctas");
        // Incorrecto
        assertFalse(empleado.iniciarSesion("empleado1", "wrongpass"), "Debe fallar con contraseña incorrecta");
        assertFalse(empleado.iniciarSesion("wronguser", "emp123"), "Debe fallar con usuario incorrecto");
        // Nulos
        assertFalse(empleado.iniciarSesion(null, "emp123"), "Debe retornar false con usuario nulo");
        assertFalse(empleado.iniciarSesion("empleado1", null), "Debe retornar false con contraseña nula");
    }

    /**
     * Prueba el método {@link Empleado#cerrarSesion()}.
     * Verifica que no lance excepciones al cerrar sesión.
     */
    @Test
    void cerrarSesionTest() {
        assertDoesNotThrow(() -> empleado.cerrarSesion(), "Cerrar sesión no debe lanzar excepciones");
    }

    /**
     * Prueba el método {@link Empleado#consultarProductosCatalogo(ArrayList)}.
     * Verifica que devuelva todos los productos del catálogo.
     */
    @Test
    void consultarProductosCatalogoTest() {
        ArrayList<Producto> resultado = empleado.consultarProductosCatalogo(catalogo);
        assertEquals(2, resultado.size(), "Debe devolver todos los productos del catálogo");
        assertTrue(resultado.contains(producto1), "Debe contener el producto 1");
        assertTrue(resultado.contains(producto2), "Debe contener el producto 2");
    }

    /**
     * Prueba el método {@link Empleado#consultarProductosCatalogo(ArrayList)}.
     * Verifica que devuelva todos los productos del catálogo.
     */
    @Test
    void agregarProductoCatalogoTest() {
        // Agregar el producto
        Producto producto = new Producto(3, "Celular", "Celular de alta gama", 1000.0, 10);
        empleado.agregarProductoCatalogo(producto, catalogo);
        // Revisar catalogo actualizado
        assertEquals(3, catalogo.size(), "El producto debe añadirse al catálogo");
        assertEquals("Celular", catalogo.getLast().getNombre(), "El nombre del producto debe coincidir");
        // Manejar duplicados
        Producto productoDuplicado = new Producto(3, "Otro Celular", "Otra descripción", 1200.0, 5);
        empleado.agregarProductoCatalogo(productoDuplicado, catalogo);
        assertEquals(3, catalogo.size(), "No debe agregar producto con ID duplicado");
        // Manejar nulos
        empleado.agregarProductoCatalogo(null, catalogo);
        assertEquals(3, catalogo.size(), "No debe agregar producto nulo");
    }

    /**
     * Prueba el método {@link Empleado#editarProductoCatalogo(int, ArrayList, String, String, double, int)}.
     * Verifica la actualización de productos en el catálogo.
     */
    @Test
    void editarProductoCatalogoTest() {
        empleado.editarProductoCatalogo(1, catalogo, "Laptop Actualizada", "Nueva descripción", 1100.0, 15);
        assertEquals("Laptop Actualizada", producto1.getNombre(), "El nombre debe actualizarse");
        assertEquals("Nueva descripción", producto1.getDescripcion(), "La descripción debe actualizarse");
        assertEquals(1100.0, producto1.getPrecio(), "El precio debe actualizarse");
        assertEquals(15, producto1.getInventario(), "El inventario debe actualizarse");

        // Intentar editar con nombre vacío
        String nombreOriginal = producto1.getNombre();
        empleado.editarProductoCatalogo(1, catalogo, "", "Desc", 100.0, 10);
        assertEquals(nombreOriginal, producto1.getNombre(), "No debe actualizarse con nombre vacío");

        // Intentar editar producto no existente
        empleado.editarProductoCatalogo(99, catalogo, "Nombre", "Desc", 100.0, 10);
        // No se puede verificar directamente, pero se espera que no haya cambios    }
    }
    /**
     * Prueba el método {@link Empleado#eliminarProductoCatalogo(int, ArrayList)}.
     * Verifica la actualización de productos en el catálogo.
     */
    @Test
    void eliminarProductoCatalogoTest() {
        empleado.eliminarProductoCatalogo(1, catalogo);
        assertEquals(1, catalogo.size(), "Debe eliminar el producto con ID 1");
        assertFalse(catalogo.contains(producto1), "El producto 1 debe ser eliminado");

        // Intentar eliminar producto no existente
        int tamanoAntes = catalogo.size();
        empleado.eliminarProductoCatalogo(99, catalogo);
        assertEquals(tamanoAntes, catalogo.size(), "No debe cambiar si el producto no existe");
    }
    
    /**
     * Prueba el método {@link Empleado#agregarEtiquetaProducto(int, Etiqueta, ArrayList)}.
     * Verifica la adición de etiquetas a productos.
     */
    @Test
    void agregarEtiquetaProductoTest() {
        empleado.agregarEtiquetaProducto(1, etiqueta1, catalogo);
        assertTrue(producto1.getEtiquetas().contains(etiqueta1), "Debe agregar la etiqueta al producto");

        assertDoesNotThrow(() -> empleado.agregarEtiquetaProducto(99, etiqueta1, catalogo), "No debe lanzar excepción si el producto no existe");
    }

    /**
     * Prueba el método {@link Empleado#eliminarEtiquetaProducto(int, Etiqueta, ArrayList)}.
     * Verifica la eliminación de etiquetas de productos.
     */
    @Test
    void eliminarEtiquetaProductoTest() {
        producto1.agregarEtiqueta(etiqueta1);
        empleado.eliminarEtiquetaProducto(1, etiqueta1, catalogo);
        assertFalse(producto1.getEtiquetas().contains(etiqueta1), "Debe eliminar la etiqueta del producto");

        assertDoesNotThrow(() -> empleado.eliminarEtiquetaProducto(99, etiqueta1, catalogo), "No debe lanzar excepción si el producto no existe");
    }

    /**
     * Prueba el método {@link Empleado#consultarInventarioProducto(int, ArrayList)}.
     * Verifica la consulta de inventario de productos.
     */
    @Test
    void consultarInventarioProductoTest() {
        int inventario = empleado.consultarInventarioProducto(1, catalogo);
        assertEquals(10, inventario, "Debe devolver el inventario correcto");

        int inventarioNoExistente = empleado.consultarInventarioProducto(99, catalogo);
        assertEquals(-1, inventarioNoExistente, "Debe devolver -1 si el producto no existe");
    }

    /**
     * Prueba el método {@link Empleado#registrarEntradaInventario(int, int, ArrayList)}.
     * Verifica el registro de entradas de inventario.
     */
    @Test
    void registrarEntradaInventarioTest() {
        empleado.registrarEntradaInventario(1, 5, catalogo);
        assertEquals(15, producto1.getInventario(), "El inventario debe incrementarse en 5");

        assertDoesNotThrow(() -> empleado.registrarEntradaInventario(99, 10, catalogo), "No debe lanzar excepción si el producto no existe");
    }

    /**
     * Prueba el método {@link Empleado#recibirAlertasInventarioBajo(ArrayList)}.
     * Verifica que devuelva productos con inventario bajo.
     */
    @Test
    void recibirAlertasInventarioBajoTest() {
        ArrayList<Producto> alertas = empleado.recibirAlertasInventarioBajo(catalogo);
        assertEquals(1, alertas.size(), "Debe haber un producto con inventario bajo");
        assertEquals("Mouse", alertas.get(0).getNombre(), "El producto con inventario bajo debe ser Mouse");
    }

    /**
     * Prueba el método {@link Empleado#actualizarEstadoPedido(int, String, ArrayList)}.
     * Verifica la actualización del estado de pedidos.
     */
    @Test
    void actualizarEstadoPedidoTest() {
        empleado.actualizarEstadoPedido(1, "En proceso", pedidos);
        assertEquals("En proceso", pedido1.getEstado(), "El estado del pedido debe actualizarse");

        // Intentar actualizar con estado nulo
        String estadoOriginal = pedido1.getEstado();
        empleado.actualizarEstadoPedido(1, null, pedidos);
        assertEquals(estadoOriginal, pedido1.getEstado(), "No debe actualizarse con estado nulo");

        // Intentar actualizar pedido no existente
        assertDoesNotThrow(() -> empleado.actualizarEstadoPedido(99, "Completado", pedidos));
    }

    /**
     * Prueba el método {@link Empleado#cancelarPedido(int, ArrayList)}.
     * Verifica la cancelación de pedidos.
     */
    @Test
    void cancelarPedidoTest() {
        empleado.cancelarPedido(1, pedidos);
        assertEquals("Cancelado", pedido1.getEstado(), "El pedido debe estar cancelado");

        assertDoesNotThrow(() -> empleado.cancelarPedido(99, pedidos), "No debe lanzar excepción si el pedido no existe");
    }

    /**
     * Prueba el método {@link Empleado#verPedidosPendientes(ArrayList)}.
     * Verifica que devuelva solo pedidos pendientes.
     */
    @Test
    void verPedidosPendientesTest() {
        ArrayList<Pedido> pendientes = empleado.verPedidosPendientes(pedidos);
        assertEquals(1, pendientes.size(), "Debe haber un pedido pendiente");
        assertEquals("Pendiente", pendientes.get(0).getEstado(), "El pedido debe estar pendiente");
    }

    /**
     * Prueba el método {@link Empleado#verificarEstadoPago(int, ArrayList)}.
     * Verifica el estado de pago de los pedidos.
     */
    @Test
    void verificarEstadoPagoTest() {
        assertFalse(empleado.verificarEstadoPago(1, pedidos), "El pedido 1 no está completado");
        assertTrue(empleado.verificarEstadoPago(2, pedidos), "El pedido 2 está completado");

        assertFalse(empleado.verificarEstadoPago(99, pedidos), "Debe devolver false si el pedido no existe");
    }

    /**
     * Prueba el método {@link Empleado#consultarDireccionPedido(int, ArrayList)}.
     * Verifica la consulta de direcciones de pedidos.
     */
    @Test
    void consultarDireccionPedidoTest() {
        Direccion direccion = empleado.consultarDireccionPedido(1, pedidos);
        assertNotNull(direccion, "Debe devolver la dirección del pedido");
        assertEquals("Calle 123", direccion.getCalle(), "La calle debe coincidir");

        Direccion direccionNoExistente = empleado.consultarDireccionPedido(99, pedidos);
        assertNull(direccionNoExistente, "Debe devolver null si el pedido no existe");
    }
}