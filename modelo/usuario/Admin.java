package modelo.usuario;

import modelo.pedido.Direccion;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;

import java.util.ArrayList;
import java.util.List;

// Clase Admin que extiende de Empleado, añadiendo funcionalidades específicas para administradores
public class Admin extends Empleado {
    // Constructor que inicializa un administrador con sus datos básicos
    public Admin(int id, String nombre, String usuario, String correo, String contraseña) {
        super(id, nombre, usuario, correo, contraseña);
    }
    // Método para crear una nueva etiqueta y añadirla a la lista de etiquetas
    public void crearEtiqueta(String nombreEtiqueta, ArrayList<Etiqueta> etiquetas) {
        int nuevoId = etiquetas.size() + 1;
        Etiqueta etiqueta = new Etiqueta(nuevoId, nombreEtiqueta);
        etiquetas.add(etiqueta);
    }
    // Método para eliminar una etiqueta de la lista por su ID
    public void eliminarEtiqueta(int idEtiqueta, List<Etiqueta> etiquetas) {
        // Usa removeIf para eliminar la etiqueta con el ID especificado
        etiquetas.removeIf(e -> e.getId() == idEtiqueta);
    }

    public List<Producto> conocerInventario(List<Producto> catalogo) {
        return new ArrayList<>(catalogo);
    }

    // Método para registrar un nuevo empleado y añadirlo a la lista de empleados
    public Empleado registrarEmpleado(String nombre, String usuario, String contraseña, List<Empleado> empleados) {
        int nuevoId = empleados.size() + 1;
        Empleado empleado = new Empleado(nuevoId, nombre, usuario, nombre + "@empresa.com", contraseña);
        empleados.add(empleado);
        return empleado;
    }

    // Método para eliminar un empleado de la lista por su ID
    public void eliminarCuentaEmpleado(int idEmpleado, ArrayList<Empleado> empleados) {
        empleados.removeIf(e -> e.getId() == idEmpleado);
    }

    // Método para editar la información de un empleado existente
    public void editarInformacionEmpleado(int idEmpleado, ArrayList<Empleado> empleados, String nuevoNombre, String nuevoUsuario, String nuevoPassword) {
        for (Empleado e : empleados){
            if (e.getId() == idEmpleado){
                e.setNombre(nuevoNombre);
                e.setUsuario(nuevoUsuario);
            }
        }
    }
    // Método para consultar la cantidad total de pedidos en el sistema
    public int consultarCantidadPedidos(List<Pedido> pedidos) {
        return pedidos.size();
    }

    //Método para obtener una lista de pedidos completados.
    public List<Pedido> consultarPagosRealizados(List<Pedido> pagos) {
        List<Pedido> realizados = new ArrayList<>();
        for (Pedido p : pagos) {
            if ("Completado".equalsIgnoreCase(p.getEstado())) {
                realizados.add(p);
            }
        }
        return realizados;
    }
// Método para agregar un nuevo punto de entrega a la lista de puntos
    public Direccion agregarPuntoEntrega(String calle, String ciudad, List<Direccion> puntosEntrega) {
        int nuevoId = puntosEntrega.size() + 1;
        Direccion punto = new Direccion(nuevoId, calle, ciudad, true);
        puntosEntrega.add(punto);
        return punto;
    }
// Método para editar un punto de entrega existente por su ID
    public void editarPuntoEntrega(int idPunto, String calle, String ciudad, String codigoPostal, List<Direccion> puntosEntrega) {
        for (Direccion d : puntosEntrega) {
            if (d.getId() == idPunto) {
                d.setCalle(calle);
                d.setCiudad(ciudad);
            }
        }
    }
// Método para eliminar un punto de entrega de la lista por su ID
    public void eliminarPuntoEntrega(int idPunto, List<Direccion> puntosEntrega) {
        puntosEntrega.removeIf(d -> d.getId() == idPunto);
    }
// Sobrescribe el método toString para representar al administrador como cadena
    @Override
    public String toString() {
        return "Admin{" + super.toString() + "}";
    }
}