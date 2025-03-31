package modelo.usuario;

import modelo.pedido.Direccion;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un administrador en el sistema, extendiendo las funcionalidades de Empleado.
 * Proporciona métodos adicionales para gestionar etiquetas, empleados, inventario y puntos de entrega.
 */
public class Admin extends Empleado {
    /**
     * Constructor que inicializa un administrador con sus datos básicos.
     * @param id Identificador único del administrador.
     * @param nombre Nombre completo del administrador.
     * @param usuario Nombre de usuario del administrador para iniciar sesión.
     * @param correo Correo electrónico del administrador.
     * @param contraseña Contraseña del administrador para autenticación.
     */
    public Admin(int id, String nombre, String usuario, String correo, String contraseña) {
        super(id, nombre, usuario, correo, contraseña);
    }

    /**
     * Crea una nueva etiqueta y la añade a la lista de etiquetas.
     * @param nombreEtiqueta Nombre de la nueva etiqueta.
     * @param etiquetas Lista de etiquetas en el sistema.
     */
    public void crearEtiqueta(String nombreEtiqueta, ArrayList<Etiqueta> etiquetas) {
        int nuevoId = etiquetas.size() + 1;
        Etiqueta etiqueta = new Etiqueta(nuevoId, nombreEtiqueta);
        etiquetas.add(etiqueta);
    }

    /**
     * Elimina una etiqueta de la lista basada en su ID.
     * @param idEtiqueta ID de la etiqueta a eliminar.
     * @param etiquetas Lista de etiquetas en el sistema.
     */
    public void eliminarEtiqueta(int idEtiqueta, List<Etiqueta> etiquetas) {
        etiquetas.removeIf(e -> e.getId() == idEtiqueta);
    }

    /**
     * Devuelve una copia de la lista de productos en el catálogo para conocer el inventario.
     * @param catalogo Lista de productos en el catálogo.
     * @return Copia de la lista de productos.
     */
    public List<Producto> conocerInventario(List<Producto> catalogo) {
        return new ArrayList<>(catalogo);
    }

    /**
     * Registra un nuevo empleado y lo añade a la lista de empleados.
     * @param nombre Nombre del nuevo empleado.
     * @param usuario Nombre de usuario del nuevo empleado.
     * @param contraseña Contraseña del nuevo empleado.
     * @param empleados Lista de empleados en el sistema.
     * @return El nuevo empleado registrado.
     */
    public Empleado registrarEmpleado(String nombre, String usuario, String contraseña, List<Empleado> empleados) {
        int nuevoId = empleados.size() + 1;
        Empleado empleado = new Empleado(nuevoId, nombre, usuario, nombre + "@empresa.com", contraseña);
        empleados.add(empleado);
        return empleado;
    }

    /**
     * Elimina un empleado de la lista basado en su ID.
     * @param idEmpleado ID del empleado a eliminar.
     * @param empleados Lista de empleados en el sistema.
     */
    public void eliminarCuentaEmpleado(int idEmpleado, ArrayList<Empleado> empleados) {
        empleados.removeIf(e -> e.getId() == idEmpleado);
    }

    /**
     * Edita la información de un empleado existente.
     * @param idEmpleado ID del empleado a editar.
     * @param empleados Lista de empleados en el sistema.
     * @param nuevoNombre Nuevo nombre del empleado.
     * @param nuevoUsuario Nuevo nombre de usuario del empleado.
     * @param nuevoPassword Nueva contraseña del empleado (no se usa en la implementación actual).
     */
    public void editarInformacionEmpleado(int idEmpleado, ArrayList<Empleado> empleados, String nuevoNombre, String nuevoUsuario, String nuevoPassword) {
        for (Empleado e : empleados) {
            if (e.getId() == idEmpleado) {
                e.setNombre(nuevoNombre);
                e.setUsuario(nuevoUsuario);
            }
        }
    }

    /**
     * Consulta la cantidad total de pedidos en el sistema.
     * @param pedidos Lista de pedidos en el sistema.
     * @return Número total de pedidos.
     */
    public int consultarCantidadPedidos(List<Pedido> pedidos) {
        return pedidos.size();
    }

    /**
     * Obtiene una lista de pedidos con estado "Completado".
     * @param pagos Lista de pedidos en el sistema.
     * @return Lista de pedidos completados.
     */
    public List<Pedido> consultarPagosRealizados(List<Pedido> pagos) {
        List<Pedido> realizados = new ArrayList<>();
        for (Pedido p : pagos) {
            if ("Completado".equalsIgnoreCase(p.getEstado())) {
                realizados.add(p);
            }
        }
        return realizados;
    }

    /**
     * Agrega un nuevo punto de entrega al sistema.
     * @param calle Calle del nuevo punto de entrega.
     * @param ciudad Ciudad del nuevo punto de entrega.
     * @param puntosEntrega Lista de puntos de entrega en el sistema.
     * @return El nuevo punto de entrega creado.
     */
    public Direccion agregarPuntoEntrega(String calle, String ciudad, List<Direccion> puntosEntrega) {
        int nuevoId = puntosEntrega.size() + 1;
        Direccion punto = new Direccion(nuevoId, calle, ciudad, true);
        puntosEntrega.add(punto);
        return punto;
    }

    /**
     * Edita un punto de entrega existente.
     * @param idPunto ID del punto de entrega a editar.
     * @param calle Nueva calle del punto de entrega.
     * @param ciudad Nueva ciudad del punto de entrega.
     * @param codigoPostal Nuevo código postal (no se usa en la implementación actual).
     * @param puntosEntrega Lista de puntos de entrega en el sistema.
     */
    public void editarPuntoEntrega(int idPunto, String calle, String ciudad, String codigoPostal, List<Direccion> puntosEntrega) {
        for (Direccion d : puntosEntrega) {
            if (d.getId() == idPunto) {
                d.setCalle(calle);
                d.setCiudad(ciudad);
            }
        }
    }

    /**
     * Elimina un punto de entrega basado en su ID.
     * @param idPunto ID del punto de entrega a eliminar.
     * @param puntosEntrega Lista de puntos de entrega en el sistema.
     */
    public void eliminarPuntoEntrega(int idPunto, List<Direccion> puntosEntrega) {
        puntosEntrega.removeIf(d -> d.getId() == idPunto);
    }

    /**
     * Devuelve una representación en cadena del administrador.
     * @return Cadena con los detalles del administrador, incluyendo los heredados de Empleado.
     */
    @Override
    public String toString() {
        return "Admin{" + super.toString() + "}";
    }
}