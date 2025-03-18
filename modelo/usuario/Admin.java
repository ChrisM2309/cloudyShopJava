package modelo.usuario;

import modelo.pedido.Direccion;
import modelo.pedido.Pedido;
import modelo.producto.Etiqueta;
import modelo.producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Empleado {
    public Admin(int id, String nombre, String usuario, String correo, String contraseña) {
        super(id, nombre, usuario, correo, contraseña);
    }

    public void crearEtiqueta(String nombreEtiqueta, ArrayList<Etiqueta> etiquetas) {
        int nuevoId = etiquetas.size() + 1;
        Etiqueta etiqueta = new Etiqueta(nuevoId, nombreEtiqueta);
        etiquetas.add(etiqueta);
    }

    public void eliminarEtiqueta(int idEtiqueta, List<Etiqueta> etiquetas) {
        etiquetas.removeIf(e -> e.getId() == idEtiqueta);
    }

    public List<Producto> conocerInventario(List<Producto> catalogo) {
        return new ArrayList<>(catalogo);
    }

    public Empleado registrarEmpleado(String nombre, String usuario, String contraseña, List<Empleado> empleados) {
        int nuevoId = empleados.size() + 1;
        Empleado empleado = new Empleado(nuevoId, nombre, usuario, nombre + "@empresa.com", contraseña);
        empleados.add(empleado);
        return empleado;
    }

    public void eliminarCuentaEmpleado(int idEmpleado, ArrayList<Empleado> empleados) {
        empleados.removeIf(e -> e.getId() == idEmpleado);
    }

    public void editarInformacionEmpleado(int idEmpleado, ArrayList<Empleado> empleados, String nuevoNombre, String nuevoUsuario, String nuevoPassword) {
        for (Empleado e : empleados){
            if (e.getId() == idEmpleado){
                e.setNombre(nuevoNombre);
                e.setUsuario(nuevoUsuario);
            }
        }
    }

    public int consultarCantidadPedidos(List<Pedido> pedidos) {
        return pedidos.size();
    }

    public List<Pedido> consultarPagosRealizados(List<Pedido> pagos) {
        List<Pedido> realizados = new ArrayList<>();
        for (Pedido p : pagos) {
            if ("Completado".equalsIgnoreCase(p.getEstado())) {
                realizados.add(p);
            }
        }
        return realizados;
    }

    public Direccion agregarPuntoEntrega(String calle, String ciudad, List<Direccion> puntosEntrega) {
        int nuevoId = puntosEntrega.size() + 1;
        Direccion punto = new Direccion(nuevoId, calle, ciudad, true);
        puntosEntrega.add(punto);
        return punto;
    }

    public void editarPuntoEntrega(int idPunto, String calle, String ciudad, String codigoPostal, List<Direccion> puntosEntrega) {
        for (Direccion d : puntosEntrega) {
            if (d.getId() == idPunto) {
                d.setCalle(calle);
                d.setCiudad(ciudad);
            }
        }
    }

    public void eliminarPuntoEntrega(int idPunto, List<Direccion> puntosEntrega) {
        puntosEntrega.removeIf(d -> d.getId() == idPunto);
    }

    @Override
    public String toString() {
        return "Admin{" + super.toString() + "}";
    }
}