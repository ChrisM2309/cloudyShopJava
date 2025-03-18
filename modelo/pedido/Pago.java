package modelo.pedido;

public class Pago {
    // Atributos de la clase Pago
    private int id;
    private String tipo;
    private String datos;
    private String estado;

    //Constructor Predeterminado
    public Pago(int id) {
        this.id = id;
        this.tipo = null;
        this.datos = null;
        this.estado = null;
    }

    //Constructor Completo
    public Pago(int id, String tipo, String datos, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.datos = datos;
        this.estado = estado;
    }

    //Metodos Getters
    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public String getDatos() { return datos; }
    public String getEstado() { return estado; }

    //Metodos Setters
    public void setId(int id) { this.id = id; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setDatos(String datos) { this.datos = datos; }
    public void setEstado(String estado) { this.estado = estado; }

    // Metodo toString para representar el objeto Pago como una cadena de texto
    @Override
    public String toString() {
        return "Id:" + id + ", Tipo:'" + tipo + "', Estado:'" + estado + "'}";
    }
}