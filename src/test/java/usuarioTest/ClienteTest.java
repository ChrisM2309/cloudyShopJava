package usuarioTest;

import modelo.pedido.Pedido;
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

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1, "Juan Perez", "jperez", "jperez@mail.com", "pass123", "123456789");
        pedidosSistema = new ArrayList<>();
        catalogo = new ArrayList<>();
        catalogo.add(new Producto(1, "Laptop", "Laptop de alta gama", 1000.0, 10));
        clientes = new ArrayList<>();
        clientes.add(cliente);
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
        // El pedido se agrega correctamente
        assertEquals(1, pedidosSistema.size(), "El pedido debe añadirse a la lista del sistema");
        assertEquals(1, cliente.getPedidos().size(), "El pedido debe añadirse a los pedidos del cliente");
        assertThrows(IllegalArgumentException.class, () -> cliente.crearPedido(null), "Debe lanzar excepción con lista nula");
    }

    @Test
    void agregarProductoPedidoTest() {
        Pedido pedido = cliente.crearPedido(pedidosSistema);
        cliente.agregarProductoPedido(1, 2, pedido.getId(), catalogo);
        assertEquals(1, pedido.getProductos().size(), "Debe agregar el producto al pedido");
        assertEquals(8, catalogo.get(0).getInventario(), "El inventario debe reducirse en la cantidad solicitada");
        assertThrows(IllegalArgumentException.class, () -> cliente.agregarProductoPedido(99, 1, pedido.getId(), catalogo), "Debe fallar si el producto no existe");
        assertThrows(IllegalArgumentException.class, () -> cliente.agregarProductoPedido(1, 1, 99, catalogo), "Debe fallar si el pedido no existe");
        assertThrows(IllegalArgumentException.class, () -> cliente.agregarProductoPedido(1, 11, pedido.getId(), catalogo), "Debe fallar si no hay inventario suficiente");
    }

    @Test
    void editarDatosTest() {
        cliente.editarDatos("Juan Perez Nuevo", "jperez_nuevo@mail.com", "987654321");
        assertEquals("Juan Perez Nuevo", cliente.getNombre(), "El nombre debe actualizarse");
        assertEquals("jperez_nuevo@mail.com", cliente.getCorreo(), "El correo debe actualizarse");
        assertEquals("987654321", cliente.getTelefono(), "El teléfono debe actualizarse");
        assertThrows(IllegalArgumentException.class, () -> cliente.editarDatos("", "jperez@mail.com", "123456789"), "Debe fallar con nombre vacío");
        assertThrows(IllegalArgumentException.class, () -> cliente.editarDatos("Juan", null, "123456789"), "Debe fallar con correo nulo");
        assertThrows(IllegalArgumentException.class, () -> cliente.editarDatos("Juan", "jperez@mail.com", ""), "Debe fallar con teléfono vacío");
    }

    @Test
    void eliminarCuentaTest() {
        System.setIn(new java.io.ByteArrayInputStream("pass123\n".getBytes()));
        cliente.eliminarCuenta(clientes);
        assertTrue(clientes.isEmpty(), "La cuenta debe eliminarse con contraseña correcta");
        clientes.add(cliente); // Restaurar para otra prueba
        System.setIn(new java.io.ByteArrayInputStream("wrongpass\n".getBytes()));
        assertThrows(SecurityException.class, () -> cliente.eliminarCuenta(clientes), "Debe fallar con contraseña incorrecta");
    }
}