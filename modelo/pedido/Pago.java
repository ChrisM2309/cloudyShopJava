package modelo.pedido;

public class Pago {
    private int id;
    private String tipo;
    private String datos;
    private String estado;

    public Pago(int id, String tipo, String datos, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.datos = datos;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDatos() { return datos; }
    public void setDatos(String datos) { this.datos = datos; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Id:" + id + ", Tipo:'" + tipo + "', Estado:'" + estado + "'}";
    }
}