package usuarioTest;

import modelo.pedido.Pedido;
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

    @BeforeEach
    void setUp() {
        empleado = new Empleado(1, "Ana Lucia", "empleado1", "ana@empresa.com", "emp123");
        catalogo = new ArrayList<>();
        pedidos = new ArrayList<>();
    }

    @Test
    void agregarProductoCatalogoTest() {
        Producto producto = new Producto(1, "Laptop", "Laptop de alta gama", 1000.0, 10);
        empleado.agregarProductoCatalogo(producto, catalogo);
        assertEquals(1, catalogo.size(), "El producto debe añadirse al catálogo");
        assertEquals("Laptop", catalogo.get(0).getNombre(), "El nombre del producto debe coincidir");
        Producto productoDuplicado = new Producto(1, "Otra Laptop", "Otra descripción", 1200.0, 5);
        assertThrows(IllegalArgumentException.class, () -> empleado.agregarProductoCatalogo(productoDuplicado, catalogo), "Debe fallar con ID duplicado");
        assertThrows(IllegalArgumentException.class, () -> empleado.agregarProductoCatalogo(null, catalogo), "Debe fallar con producto nulo");
    }

    @Test
    void editarProductoCatalogoTest() {
        Producto producto = new Producto(1, "Laptop", "Laptop de alta gama", 1000.0, 10);
        catalogo.add(producto);
        empleado.editarProductoCatalogo(1, catalogo, "Laptop Actualizada", "Nueva descripción", 1100.0, 15);
        assertEquals("Laptop Actualizada", producto.getNombre(), "El nombre debe actualizarse");
        assertEquals("Nueva descripción", producto.getDescripcion(), "La descripción debe actualizarse");
        assertEquals(1100.0, producto.getPrecio(), "El precio debe actualizarse");
        assertEquals(15, producto.getInventario(), "El inventario debe actualizarse");
        assertThrows(IllegalArgumentException.class, () -> empleado.editarProductoCatalogo(99, catalogo, "Nombre", "Desc", 100.0, 10), "Debe fallar si el producto no existe");
        assertThrows(IllegalArgumentException.class, () -> empleado.editarProductoCatalogo(1, catalogo, "", "Desc", 100.0, 10), "Debe fallar con nombre vacío");
    }

    @Test
    void actualizarEstadoPedidoTest() {
        Pedido pedido = new Pedido(1, new ArrayList<>(), null, null, "Pendiente", 1);
        pedidos.add(pedido);
        empleado.actualizarEstadoPedido(1, "Completado", pedidos);
        assertEquals("Completado", pedido.getEstado(), "El estado del pedido debe actualizarse");
        assertThrows(IllegalArgumentException.class, () -> empleado.actualizarEstadoPedido(99, "Completado", pedidos), "Debe fallar si el pedido no existe");
        assertThrows(IllegalArgumentException.class, () -> empleado.actualizarEstadoPedido(1, null, pedidos), "Debe fallar con estado nulo");
    }
}