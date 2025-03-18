package modelo.pedido;

public class Direccion {
    private int id;
    private String calle;
    private String ciudad;
    private boolean esPuntoDeEntrega;
    public Direccion(int id) {
        this.id = id;
        this.calle = null;
        this.ciudad = null;
        this.esPuntoDeEntrega = false;
    }
    public Direccion(int id, String calle, String ciudad, boolean esPuntoDeEntrega) {
        this.id = id;
        this.calle = calle;
        this.ciudad = ciudad;
        this.esPuntoDeEntrega = esPuntoDeEntrega;
    }

    public int getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public boolean getEsPuntoDeEntrega() {
        return esPuntoDeEntrega;
    }

    public void setCalle(String nueva) {
        this.calle = nueva;
    }

    public void setCiudad(String nueva) {
        this.ciudad = nueva;
    }

    public void setEsPuntoDeEntrega(boolean estado) {
        this.esPuntoDeEntrega = estado;
    }

    @Override
    public String toString() {
        return "Id:" + id + ", Calle:'" + calle + "', Ciudad:'" + ciudad + "', Punto de entrega=" + esPuntoDeEntrega + "}";
    }
}