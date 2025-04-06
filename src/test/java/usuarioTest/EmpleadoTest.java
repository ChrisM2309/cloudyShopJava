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
 * Clase de testeo unitario para Empleado
 */
public class EmpleadoTest {
    private Empleado empleado;
    private ArrayList<Producto> catalogo;
    private ArrayList<Pedido> pedidos;
    private Producto producto1, producto2;
    private Pedido pedido1, pedido2;
    private Etiqueta etiqueta1;

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

    @Test
    void iniciarSesionTest() {
        // Correcto
        assertTrue(empleado.iniciarSesion("empleado1", "emp123"), "Debe permitir inicio con credenciales correctas");
        // Incorrecto
        assertFalse(empleado.iniciarSesion("empleado1", "wrongpass"), "Debe fallar con contraseña incorrecta");
        assertFalse(empleado.iniciarSesion("wronguser", "emp123"), "Debe fallar con usuario incorrecto");
        // Nulos
        assertThrows(IllegalArgumentException.class, () -> empleado.iniciarSesion(null, "emp123"), "Debe fallar con usuario nulo");
        assertThrows(IllegalArgumentException.class, () -> empleado.iniciarSesion("empleado1", null), "Debe fallar con contraseña nula");
    }

    @Test
    void cerrarSesionTest() {
        assertDoesNotThrow(() -> empleado.cerrarSesion(), "Cerrar sesión no debe lanzar excepciones");
    }

    @Test
    void consultarProductosCatalogoTest() {
        ArrayList<Producto> resultado = empleado.consultarProductosCatalogo(catalogo);
        assertEquals(2, resultado.size(), "Debe devolver todos los productos del catálogo");
        assertTrue(resultado.contains(producto1), "Debe contener el producto 1");
        assertTrue(resultado.contains(producto2), "Debe contener el producto 2");
    }

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
        assertThrows(IllegalArgumentException.class, () -> empleado.agregarProductoCatalogo(productoDuplicado, catalogo), "Debe fallar con ID duplicado");
        // Manejar nulos
        assertThrows(IllegalArgumentException.class, () -> empleado.agregarProductoCatalogo(null, catalogo), "Debe fallar con producto nulo");
    }

    @Test
    void editarProductoCatalogoTest() {
        Producto producto = new Producto(1, "Laptop", "Laptop de alta gama", 1000.0, 10);
        catalogo.add(producto);
        empleado.editarProductoCatalogo(1, catalogo, "Laptop Actualizada", "Nueva descripción", 1100.0, 15);
        // Probar que se actualicen los atributos
        assertEquals("Laptop Actualizada", producto.getNombre(), "El nombre debe actualizarse");
        assertEquals("Nueva descripción", producto.getDescripcion(), "La descripción debe actualizarse");
        assertEquals(1100.0, producto.getPrecio(), "El precio debe actualizarse");
        assertEquals(15, producto.getInventario(), "El inventario debe actualizarse");
        // No existe o nombre vacio
        assertThrows(IllegalArgumentException.class, () -> empleado.editarProductoCatalogo(99, catalogo, "Nombre", "Desc", 100.0, 10), "Debe fallar si el producto no existe");
        assertThrows(IllegalArgumentException.class, () -> empleado.editarProductoCatalogo(1, catalogo, "", "Desc", 100.0, 10), "Debe fallar con nombre vacío");
    }

    @Test
    void agregarEtiquetaProductoTest() {
        empleado.agregarEtiquetaProducto(1, etiqueta1, catalogo);
        assertTrue(producto1.getEtiquetas().contains(etiqueta1), "Debe agregar la etiqueta al producto");

        assertDoesNotThrow(() -> empleado.agregarEtiquetaProducto(99, etiqueta1, catalogo), "No debe lanzar excepción si el producto no existe");
    }

    @Test
    void eliminarEtiquetaProductoTest() {
        producto1.agregarEtiqueta(etiqueta1);
        empleado.eliminarEtiquetaProducto(1, etiqueta1, catalogo);
        assertFalse(producto1.getEtiquetas().contains(etiqueta1), "Debe eliminar la etiqueta del producto");

        assertDoesNotThrow(() -> empleado.eliminarEtiquetaProducto(99, etiqueta1, catalogo), "No debe lanzar excepción si el producto no existe");
    }

    @Test
    void consultarInventarioProductoTest() {
        int inventario = empleado.consultarInventarioProducto(1, catalogo);
        assertEquals(10, inventario, "Debe devolver el inventario correcto");

        int inventarioNoExistente = empleado.consultarInventarioProducto(99, catalogo);
        assertEquals(-1, inventarioNoExistente, "Debe devolver -1 si el producto no existe");
    }

    @Test
    void registrarEntradaInventarioTest() {
        empleado.registrarEntradaInventario(1, 5, catalogo);
        assertEquals(15, producto1.getInventario(), "El inventario debe incrementarse en 5");

        assertDoesNotThrow(() -> empleado.registrarEntradaInventario(99, 10, catalogo), "No debe lanzar excepción si el producto no existe");
    }

    @Test
    void recibirAlertasInventarioBajoTest() {
        ArrayList<Producto> alertas = empleado.recibirAlertasInventarioBajo(catalogo);
        assertEquals(1, alertas.size(), "Debe haber un producto con inventario bajo");
        assertEquals("Mouse", alertas.get(0).getNombre(), "El producto con inventario bajo debe ser Mouse");
    }
    // Pruebas para métodos relacionados con pedidos
    @Test
    void actualizarEstadoPedidoTest() {
        empleado.actualizarEstadoPedido(1, "En proceso", pedidos);
        assertEquals("En proceso", pedido1.getEstado(), "El estado del pedido debe actualizarse");

        assertDoesNotThrow(() -> empleado.actualizarEstadoPedido(99, "Completado", pedidos), "No debe lanzar excepción si el pedido no existe");

        assertThrows(IllegalArgumentException.class, () -> empleado.actualizarEstadoPedido(1, null, pedidos), "Debe fallar con estado nulo");
    }

    @Test
    void cancelarPedidoTest() {
        empleado.cancelarPedido(1, pedidos);
        assertEquals("Cancelado", pedido1.getEstado(), "El pedido debe estar cancelado");

        assertDoesNotThrow(() -> empleado.cancelarPedido(99, pedidos), "No debe lanzar excepción si el pedido no existe");
    }

    @Test
    void verPedidosPendientesTest() {
        ArrayList<Pedido> pendientes = empleado.verPedidosPendientes(pedidos);
        assertEquals(1, pendientes.size(), "Debe haber un pedido pendiente");
        assertEquals("Pendiente", pendientes.get(0).getEstado(), "El pedido debe estar pendiente");
    }

    @Test
    void verificarEstadoPagoTest() {
        assertFalse(empleado.verificarEstadoPago(1, pedidos), "El pedido 1 no está completado");
        assertTrue(empleado.verificarEstadoPago(2, pedidos), "El pedido 2 está completado");

        assertFalse(empleado.verificarEstadoPago(99, pedidos), "Debe devolver false si el pedido no existe");
    }

    @Test
    void consultarDireccionPedidoTest() {
        Direccion direccion = empleado.consultarDireccionPedido(1, pedidos);
        assertNotNull(direccion, "Debe devolver la dirección del pedido");
        assertEquals("Calle 123", direccion.getCalle(), "La calle debe coincidir");

        Direccion direccionNoExistente = empleado.consultarDireccionPedido(99, pedidos);
        assertNull(direccionNoExistente, "Debe devolver null si el pedido no existe");
    }
}