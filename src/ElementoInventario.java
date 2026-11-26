public abstract class ElementoInventario {
    private final String idElemento;
    private final String nombre;
    private final double precioUnitario;
    private int cantidad;

    public ElementoInventario(String idElemento, String nombre, double precioUnitario, int cantidad) {
        this.idElemento = idElemento;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public String getIdElemento() {
         return idElemento; 
        }
    public String getNombre() {
         return nombre; 
        }
    public double getPrecioUnitario() {
         return precioUnitario; 
        }
    public int getCantidad() {
         return cantidad;
         }
    public void setCantidad(int cantidad) {
         this.cantidad = cantidad; 
        }
// Método de entrada para el patrón Visitor
    public abstract void accept(Visitor visitor);
}