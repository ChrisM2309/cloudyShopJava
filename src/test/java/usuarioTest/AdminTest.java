package usuarioTest;

import modelo.pedido.Direccion;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;
import modelo.usuario.Admin;
import modelo.usuario.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * Clase de testeo unitario para Admin
 */
public class AdminTest {
    private Admin admin;
    private ArrayList<Empleado> empleados;
    private ArrayList<Etiqueta> etiquetas;
    private ArrayList<Producto> catalogo;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Direccion> puntosEntrega;

    @BeforeEach
    void setUp() {
        admin = new Admin(1, "Juan Admin", "admin1", "admin1@empresa.com", "admin123");
        empleados = new ArrayList<>();
        etiquetas = new ArrayList<>();
        catalogo = new ArrayList<>();
        pedidos = new ArrayList<>();
        puntosEntrega = new ArrayList<>();

        // Datos iniciales para pruebas
        empleados.add(new Empleado(1, "Ana Lucia", "empleado1", "ana@empresa.com", "emp123"));
        etiquetas.add(new Etiqueta(1, "Electrónica"));
        catalogo.add(new Producto(1, "Laptop", "Laptop de alta gama", 1000.0, 10));
        pedidos.add(new Pedido(1, new ArrayList<>(), new Direccion(1, "Calle 123", "Ciudad A", false), null, "Pendiente", 1));
        pedidos.add(new Pedido(2, new ArrayList<>(), new Direccion(2, "Avenida 456", "Ciudad B", true), null, "Completado", 1));
        puntosEntrega.add(new Direccion(1, "Punto Central", "Ciudad A", true));
    }

    // Pruebas de Empleados
    @Test
    void registrarEmpleadoTest() {
        Empleado nuevoEmpleado = admin.registrarEmpleado("Ana Lucia", "empleado2", "emp123", empleados);
        // Agregar empleado de forma correcta
        assertNotNull(nuevoEmpleado, "El empleado creado no debe ser nulo");
        assertEquals(2, empleados.size(), "El empleado debe añadirse a la lista");
        assertEquals("empleado2", nuevoEmpleado.getUsuario(), "El usuario debe coincidir");
        // Usuario duplicado
        assertThrows(IllegalArgumentException.class, () -> admin.registrarEmpleado("Otro", "empleado2", "otro123", empleados), "Debe fallar con usuario duplicado");
        // Manejo de vacios
        assertThrows(IllegalArgumentException.class, () -> admin.registrarEmpleado("", "emp2", "pass", empleados), "Debe fallar con nombre vacío");
        assertThrows(IllegalArgumentException.class, () -> admin.registrarEmpleado(null, "emp3", "pass", empleados), "Debe fallar con nombre null");    }

    @Test
    void eliminarCuentaEmpleadoTest() {
        Empleado empleado = new Empleado(1, "Ana Lucia", "empleado1", "ana@empresa.com", "emp123");
        empleados.add(empleado);
        admin.eliminarCuentaEmpleado(1, empleados);
        // El empleado se elimina correctamente
        assertTrue(empleados.size() == 1, "El empleado debe eliminarse");
        // Debe fallar si no existe
        assertThrows(IllegalArgumentException.class, () -> admin.eliminarCuentaEmpleado(99, empleados), "Debe fallar si el empleado no existe");
    }
    @Test
    void editarInformacionEmpleadoTest() {
        admin.editarInformacionEmpleado(1, empleados, "Ana Actualizada", "anaActualizada", "nuevaPass");
        Empleado empleado = empleados.get(0);
        assertEquals("Ana Actualizada", empleado.getNombre(), "El nombre debe actualizarse");
        assertEquals("anaActualizada", empleado.getUsuario(), "El usuario debe actualizarse");

        assertDoesNotThrow(() -> admin.editarInformacionEmpleado(99, empleados, "Nombre", "Usuario", "Pass"), "No debe fallar si el empleado no existe");
    }


    // Pruebas de manejo de etiquetas
    @Test
    void crearEtiquetaTest() {
        admin.crearEtiqueta("Accesorios", etiquetas);
        // Agregar etiqueta correctamente
        assertEquals(2, etiquetas.size(), "Debe haber dos etiquetas");
        assertEquals("Accesorios", etiquetas.get(1).getNombre(), "La nueva etiqueta debe ser Accesorios");

        // Fallar con duplicados
        assertThrows(IllegalArgumentException.class, () -> admin.crearEtiqueta("Electrónica", etiquetas), "Debe fallar con etiqueta duplicada");
        // Fallar con nulls
        assertThrows(IllegalArgumentException.class, () -> admin.crearEtiqueta("", etiquetas), "Debe fallar con nombre vacío");
        assertThrows(IllegalArgumentException.class, () -> admin.crearEtiqueta(null, etiquetas), "Debe fallar con nombre null");
    }

    @Test
    void eliminarEtiquetaTest() {
        admin.eliminarEtiqueta(1, etiquetas);
        assertTrue(etiquetas.isEmpty(), "La etiqueta debe eliminarse");

        assertDoesNotThrow(() -> admin.eliminarEtiqueta(99, etiquetas), "No debe fallar si la etiqueta no existe");
    }

    // Prueba de inventario
    @Test
    void conocerInventarioTest() {
        ArrayList<Producto> inventario = admin.conocerInventario(catalogo);
        assertEquals(1, inventario.size(), "Debe devolver una copia del catálogo");
        assertEquals("Laptop", inventario.get(0).getNombre(), "El producto debe coincidir");
    }

    // Prueba de puntos de entrega
    @Test
    void agregarPuntoEntregaTest() {
        // Agregar punto de entrega
        Direccion nuevoPunto = admin.agregarPuntoEntrega("Nueva Calle", "Nueva Ciudad", puntosEntrega);
        //Se agrega de forma exitosa
        assertNotNull(nuevoPunto, "El punto de entrega creado no debe ser nulo");
        assertEquals(2, puntosEntrega.size(), "Debe haber dos puntos de entrega");
        assertEquals("Nueva Calle", nuevoPunto.getCalle(), "La calle debe coincidir");

        // Manejo de parametros vacios
        assertThrows(IllegalArgumentException.class, () -> admin.agregarPuntoEntrega("", "Ciudad", puntosEntrega), "Debe fallar con calle vacía");
        assertThrows(IllegalArgumentException.class, () -> admin.agregarPuntoEntrega("Calle", "", puntosEntrega), "Debe fallar con ciudad vacía");
    }

    @Test
    void editarPuntoEntregaTest() {
        admin.editarPuntoEntrega(1, "Calle Editada", "Ciudad Editada", "12345", puntosEntrega);
        Direccion punto = puntosEntrega.get(0);
        assertEquals("Calle Editada", punto.getCalle(), "La calle debe actualizarse");
        assertEquals("Ciudad Editada", punto.getCiudad(), "La ciudad debe actualizarse");

        assertDoesNotThrow(() -> admin.editarPuntoEntrega(99, "Calle", "Ciudad", "12345", puntosEntrega), "No debe fallar si el punto no existe");
    }

    @Test
    void eliminarPuntoEntregaTest() {
        admin.eliminarPuntoEntrega(1, puntosEntrega);
        assertTrue(puntosEntrega.isEmpty(), "El punto de entrega debe eliminarse");

        assertDoesNotThrow(() -> admin.eliminarPuntoEntrega(99, puntosEntrega), "No debe fallar si el punto no existe");
    }
}