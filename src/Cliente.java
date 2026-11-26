public class Cliente implements Runnable {
    private final String nombre;
    private final String idProducto;
    private final int cantidad;
    private final String tipoOperacion; // "RETIRO" o "ENTRADA"

    public Cliente(String nombre, String idProducto, int cantidad, String tipoOperacion) {
        this.nombre = nombre;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.tipoOperacion = tipoOperacion;
    }

    @Override
    public void run() {
        Inventario inventario = Inventario.getInstancia();

        if ("RETIRO".equalsIgnoreCase(tipoOperacion)) {
            SalidaInventarioVisitor v = new SalidaInventarioVisitor(idProducto, cantidad);
            inventario.aplicarVisitor(v);
            System.out.println("[" + nombre + " (Retiro)] " + v.getMensaje());
        } else if ("ENTRADA".equalsIgnoreCase(tipoOperacion)) {
            EntradaInventarioVisitor v = new EntradaInventarioVisitor(idProducto, cantidad);
            inventario.aplicarVisitor(v);
            System.out.println("[" + nombre + " (Entrada)] Se agregaron " + cantidad + " unidades a " + idProducto + ".");
        }
    }
}