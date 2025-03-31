package modelo.pedido;

/**
 * Clase que representa una dirección en el sistema.
 */
public class Direccion {
    private int id;
    private String calle;
    private String ciudad;
    private boolean esPuntoDeEntrega;

    /**
     * Constructor que inicializa una dirección con un ID.
     * @param id Identificador de la dirección.
     */
    public Direccion(int id) {
        this.id = id;
        this.calle = null;
        this.ciudad = null;
        this.esPuntoDeEntrega = false;
    }

    /**
     * Constructor que inicializa una dirección con todos sus atributos.
     * @param id Identificador de la dirección.
     * @param calle Nombre de la calle.
     * @param ciudad Nombre de la ciudad.
     * @param esPuntoDeEntrega Indica si es un punto de entrega.
     */
    public Direccion(int id, String calle, String ciudad, boolean esPuntoDeEntrega) {
        this.id = id;
        this.calle = calle;
        this.ciudad = ciudad;
        this.esPuntoDeEntrega = esPuntoDeEntrega;
    }

    /**
     * Obtiene el ID de la dirección.
     * @return El ID de la dirección.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene la calle de la dirección.
     * @return La calle.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Obtiene la ciudad de la dirección.
     * @return La ciudad.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Indica si la dirección es un punto de entrega.
     * @return Verdadero si es un punto de entrega, falso si no.
     */
    public boolean getEsPuntoDeEntrega() {
        return esPuntoDeEntrega;
    }

    /**
     * Establece la calle de la dirección.
     * @param nueva La nueva calle.
     */
    public void setCalle(String nueva) {
        this.calle = nueva;
    }

    /**
     * Establece la ciudad de la dirección.
     * @param nueva La nueva ciudad.
     */
    public void setCiudad(String nueva) {
        this.ciudad = nueva;
    }

    /**
     * Establece si la dirección es un punto de entrega.
     * @param estado El nuevo estado.
     */
    public void setEsPuntoDeEntrega(boolean estado) {
        this.esPuntoDeEntrega = estado;
    }

    /**
     * Devuelve una representación en cadena de la dirección.
     * @return Cadena con los detalles de la dirección.
     */
    @Override
    public String toString() {
        return "Id:" + id + ", Calle:'" + calle + "', Ciudad:'" + ciudad + "'}";
    }
}