package modelo.pedido;

/**
 * Clase que representa un método de pago en el sistema.
 */
public class Pago {
    // Atributos de la clase Pago
    private int id;
    private String tipo;
    private String datos;
    private String estado;

    /**
     * Constructor que inicializa un pago con un ID.
     * @param id Identificador del pago.
     */
    public Pago(int id) {
        this.id = id;
        this.tipo = null;
        this.datos = null;
        this.estado = null;
    }

    /**
     * Constructor que inicializa un pago con todos sus atributos.
     * @param id Identificador del pago.
     * @param tipo Tipo de pago.
     * @param datos Datos del pago.
     * @param estado Estado del pago.
     */
    public Pago(int id, String tipo, String datos, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.datos = datos;
        this.estado = estado;
    }

    /**
     * Obtiene el ID del pago.
     * @return El ID del pago.
     */
    public int getId() { return id; }

    /**
     * Obtiene el tipo de pago.
     * @return El tipo de pago.
     */
    public String getTipo() { return tipo; }

    /**
     * Obtiene los datos del pago.
     * @return Los datos del pago.
     */
    public String getDatos() { return datos; }

    /**
     * Obtiene el estado del pago.
     * @return El estado del pago.
     */
    public String getEstado() { return estado; }

    /**
     * Establece el ID del pago.
     * @param id El nuevo ID del pago.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Establece el tipo de pago.
     * @param tipo El nuevo tipo de pago.
     */
    public void setTipo(String tipo) { this.tipo = tipo; }

    /**
     * Establece los datos del pago.
     * @param datos Los nuevos datos del pago.
     */
    public void setDatos(String datos) { this.datos = datos; }

    /**
     * Establece el estado del pago.
     * @param estado El nuevo estado del pago.
     */
    public void setEstado(String estado) { this.estado = estado; }

    /**
     * Devuelve una representación en cadena del metodo de pago.
     * @return Cadena con los detalles del pago.
     */
    @Override
    public String toString() {
        return "Id:" + id + ", Tipo:'" + tipo + "', Estado:'" + estado + "', Datos:'" + datos + "'";
    }
}