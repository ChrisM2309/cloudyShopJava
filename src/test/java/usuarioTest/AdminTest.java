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
import java.util.List;

/**
 * Clase de testeo unitario para {@link Admin}.
 * Verifica el comportamiento de los métodos de la clase Admin, incluyendo gestión
 * de empleados, etiquetas, inventario y puntos de entrega.
 */
public class AdminTest {
    /** Administrador utilizado en las pruebas */
    private Admin admin;
    /** Lista de empleados para pruebas */
    private ArrayList<Empleado> empleados;
    /** Lista de etiquetas para pruebas */
    private ArrayList<Etiqueta> etiquetas;
    /** Catálogo de productos para pruebas */
    private ArrayList<Producto> catalogo;
    /** Lista de pedidos para pruebas */
    private ArrayList<Pedido> pedidos;
    /** Puntos de entrega para pruebas */
    private ArrayList<Direccion> puntosEntrega;

    /**
     * Configura el entorno de prueba antes de cada test.
     * Inicializa el admin, empleados, etiquetas, catálogo, pedidos y puntos de entrega.
     */
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

    /**
     * Prueba el método {@link Admin#registrarEmpleado(String, String, String, List)}.
     * Verifica el registro correcto de empleados y manejo de casos inválidos.
     */
    @Test
    void registrarEmpleadoTest() {
        Empleado nuevoEmpleado = admin.registrarEmpleado("Ana Lucia", "empleado2", "emp123", empleados);
        // Agregar empleado de forma correcta
        assertNotNull(nuevoEmpleado, "El empleado creado no debe ser nulo");
        assertEquals(2, empleados.size(), "El empleado debe añadirse a la lista");
        assertEquals("empleado2", nuevoEmpleado.getUsuario(), "El usuario debe coincidir");
        // Usuario duplicado
        Empleado duplicado = admin.registrarEmpleado("Otro", "empleado2", "otro123", empleados);
        assertNull(duplicado, "No debe registrar empleado con usuario duplicado");
        assertEquals(2, empleados.size(), "La lista no debe cambiar");
        // Manejo de vacios y null
        Empleado vacio = admin.registrarEmpleado("", "emp2", "pass", empleados);
        assertNull(vacio, "No debe registrar empleado con nombre vacío");
        assertEquals(2, empleados.size(), "La lista no debe cambiar");

        Empleado nullNombre = admin.registrarEmpleado(null, "emp3", "pass", empleados);
        assertNull(nullNombre, "No debe registrar empleado con nombre null");
        assertEquals(2, empleados.size(), "La lista no debe cambiar");    }

    /**
     * Prueba el método {@link Admin#eliminarCuentaEmpleado(int, ArrayList)}.
     * Verifica la eliminación de cuentas de empleados.
     */
    @Test
    void eliminarCuentaEmpleadoTest() {
        Empleado empleado = new Empleado(2, "Maria", "empleado2", "mj@empresa.com", "emp123");
        empleados.add(empleado);
        admin.eliminarCuentaEmpleado(1, empleados);
        // El empleado se elimina correctamente
        assertTrue(empleados.size() == 1, "El empleado debe eliminarse");
        // Debe fallar si no existe
        int tamanoAntes = empleados.size();
        admin.eliminarCuentaEmpleado(99, empleados);
        assertEquals(tamanoAntes, empleados.size(), "No debe cambiar si el empleado no existe");
    }

    /**
     * Prueba el método {@link Admin#editarInformacionEmpleado(int, ArrayList, String, String, String)}.
     * Verifica la actualización de información de empleados.
     */
    @Test
    void editarInformacionEmpleadoTest() {
        admin.editarInformacionEmpleado(1, empleados, "Ana Actualizada", "anaActualizada", "nuevaPass");
        Empleado empleado = empleados.get(0);
        assertEquals("Ana Actualizada", empleado.getNombre(), "El nombre debe actualizarse");
        assertEquals("anaActualizada", empleado.getUsuario(), "El usuario debe actualizarse");
        // Si no existe
        int tamanoAntes = empleados.size();
        admin.editarInformacionEmpleado(99, empleados, "Nombre", "Usuario", "Pass");
        assertEquals(tamanoAntes, empleados.size(), "La lista no debe cambiar si el empleado no existe");
    }


    /**
     * Prueba el método {@link Admin#crearEtiqueta(String, ArrayList)}.
     * Verifica la creación de etiquetas y manejo de casos inválidos.
     */
    @Test
    void crearEtiquetaTest() {
        admin.crearEtiqueta("Accesorios", etiquetas);
        // Agregar etiqueta correctamente
        assertEquals(2, etiquetas.size(), "Debe haber dos etiquetas");
        assertEquals("Accesorios", etiquetas.get(1).getNombre(), "La nueva etiqueta debe ser Accesorios");

        // Fallar con duplicados
        admin.crearEtiqueta("Electrónica", etiquetas);
        assertEquals(2, etiquetas.size(), "No debe agregar etiqueta duplicada");
        // Fallar con nulls y empty
        admin.crearEtiqueta("", etiquetas);
        assertEquals(2, etiquetas.size(), "No debe agregar etiqueta con nombre vacío");

        admin.crearEtiqueta(null, etiquetas);
        assertEquals(2, etiquetas.size(), "No debe agregar etiqueta con nombre null");
    }

    /**
     * Prueba el método {@link Admin#eliminarEtiqueta(int, List)}.
     * Verifica la eliminación de etiquetas.
     */
    @Test
    void eliminarEtiquetaTest() {
        admin.eliminarEtiqueta(1, etiquetas);
        assertTrue(etiquetas.isEmpty(), "La etiqueta debe eliminarse");

        assertDoesNotThrow(() -> admin.eliminarEtiqueta(99, etiquetas), "No debe fallar si la etiqueta no existe");
    }

    /**
     * Prueba el método {@link Admin#conocerInventario(List)}.
     * Verifica la consulta del inventario de productos.
     */
    @Test
    void conocerInventarioTest() {
        ArrayList<Producto> inventario = admin.conocerInventario(catalogo);
        assertEquals(1, inventario.size(), "Debe devolver una copia del catálogo");
        assertEquals("Laptop", inventario.get(0).getNombre(), "El producto debe coincidir");
    }

    /**
     * Prueba el método {@link Admin#agregarPuntoEntrega(String, String, List)}.
     * Verifica la creación de puntos de entrega y manejo de casos inválidos.
     */
    @Test
    void agregarPuntoEntregaTest() {
        // Agregar punto de entrega
        Direccion nuevoPunto = admin.agregarPuntoEntrega("Nueva Calle", "Nueva Ciudad", puntosEntrega);
        //Se agrega de forma exitosa
        assertNotNull(nuevoPunto, "El punto de entrega creado no debe ser nulo");
        assertEquals(2, puntosEntrega.size(), "Debe haber dos puntos de entrega");
        assertEquals("Nueva Calle", nuevoPunto.getCalle(), "La calle debe coincidir");

        // Manejo de parametros vacios
        Direccion vacio = admin.agregarPuntoEntrega("", "Ciudad", puntosEntrega);
        assertNull(vacio, "No debe agregar punto con calle vacía");
        assertEquals(2, puntosEntrega.size(), "La lista no debe cambiar");

        Direccion vacioCiudad = admin.agregarPuntoEntrega("Calle", "", puntosEntrega);
        assertNull(vacioCiudad, "No debe agregar punto con ciudad vacía");
        assertEquals(2, puntosEntrega.size(), "La lista no debe cambiar");    }

    /**
     * Prueba el método {@link Admin#editarPuntoEntrega(int, String, String, String, List)}.
     * Verifica la edición de puntos de entrega.
     */
    @Test
    void editarPuntoEntregaTest() {
        admin.editarPuntoEntrega(1, "Calle Editada", "Ciudad Editada", "12345", puntosEntrega);
        Direccion punto = puntosEntrega.get(0);
        assertEquals("Calle Editada", punto.getCalle(), "La calle debe actualizarse");
        assertEquals("Ciudad Editada", punto.getCiudad(), "La ciudad debe actualizarse");

        assertDoesNotThrow(() -> admin.editarPuntoEntrega(99, "Calle", "Ciudad", "12345", puntosEntrega), "No debe fallar si el punto no existe");
    }

    /**
     * Prueba el método {@link Admin#eliminarPuntoEntrega(int, List)}.
     * Verifica la eliminación de puntos de entrega.
     */
    @Test
    void eliminarPuntoEntregaTest() {
        admin.eliminarPuntoEntrega(1, puntosEntrega);
        assertTrue(puntosEntrega.isEmpty(), "El punto de entrega debe eliminarse");

        assertDoesNotThrow(() -> admin.eliminarPuntoEntrega(99, puntosEntrega), "No debe fallar si el punto no existe");
    }
}