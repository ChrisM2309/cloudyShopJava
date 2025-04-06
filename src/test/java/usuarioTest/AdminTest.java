package usuarioTest;

import modelo.producto.Etiqueta;
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

    @BeforeEach
    void setUp() {
        admin = new Admin(1, "Juan Admin", "admin1", "admin1@empresa.com", "admin123");
        empleados = new ArrayList<>();
        etiquetas = new ArrayList<>();
    }

    @Test
    void registrarEmpleadoTest() {
        Empleado nuevoEmpleado = admin.registrarEmpleado("Ana Lucia", "empleado1", "emp123", empleados);
        assertNotNull(nuevoEmpleado, "El empleado creado no debe ser nulo");
        assertEquals(1, empleados.size(), "El empleado debe añadirse a la lista");
        assertEquals("empleado1", nuevoEmpleado.getUsuario(), "El usuario debe coincidir");
        assertThrows(IllegalArgumentException.class, () -> admin.registrarEmpleado("Otro", "empleado1", "otro123", empleados), "Debe fallar con usuario duplicado");
        assertThrows(IllegalArgumentException.class, () -> admin.registrarEmpleado("", "emp2", "pass", empleados), "Debe fallar con nombre vacío");
    }

    @Test
    void eliminarCuentaEmpleadoTest() {
        Empleado empleado = new Empleado(1, "Ana Lucia", "empleado1", "ana@empresa.com", "emp123");
        empleados.add(empleado);
        admin.eliminarCuentaEmpleado(1, empleados);
        assertTrue(empleados.isEmpty(), "El empleado debe eliminarse");
        assertThrows(IllegalArgumentException.class, () -> admin.eliminarCuentaEmpleado(99, empleados), "Debe fallar si el empleado no existe");
    }

    @Test
    void crearEtiquetaTest() {
        admin.crearEtiqueta("Electrónica", etiquetas);
        assertEquals(1, etiquetas.size(), "La etiqueta debe añadirse");
        assertEquals("Electrónica", etiquetas.get(0).getNombre(), "El nombre de la etiqueta debe coincidir");
        assertThrows(IllegalArgumentException.class, () -> admin.crearEtiqueta("Electrónica", etiquetas), "Debe fallar con etiqueta duplicada");
        assertThrows(IllegalArgumentException.class, () -> admin.crearEtiqueta("", etiquetas), "Debe fallar con nombre vacío");
    }
}