package modelo.pedido;

public class Direccion {
    // Atributos de la clase Direccion
    private int id;
    private String calle;
    private String ciudad;
    private boolean esPuntoDeEntrega;

    //Constructor Predeterminado
    public Direccion(int id) {
        this.id = id;
        this.calle = null;
        this.ciudad = null;
        this.esPuntoDeEntrega = false;
    }

    //Constructor Completo
    public Direccion(int id, String calle, String ciudad, boolean esPuntoDeEntrega) {
        this.id = id;
        this.calle = calle;
        this.ciudad = ciudad;
        this.esPuntoDeEntrega = esPuntoDeEntrega;
    }

    //Metodos Getters
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

    // Métodos Setters
    public void setCalle(String nueva) {
        this.calle = nueva;
    }

    public void setCiudad(String nueva) {
        this.ciudad = nueva;
    }

    public void setEsPuntoDeEntrega(boolean estado) {
        this.esPuntoDeEntrega = estado;
    }

    // Metodo toString para representar el objeto Direccion como una cadena de texto
    @Override
    public String toString() {
        return "Id:" + id + ", Calle:'" + calle + "', Ciudad:'" + ciudad + "', Punto de entrega=" + esPuntoDeEntrega + "}";
    }
}