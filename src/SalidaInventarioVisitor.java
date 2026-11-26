public class SalidaInventarioVisitor implements Visitor {
    private final String idObjetivo;
    private final int cantidadRetirar;
    private String mensaje = "";

    public SalidaInventarioVisitor(String idObjetivo, int cantidadRetirar) {
        this.idObjetivo = idObjetivo;
        this.cantidadRetirar = cantidadRetirar;
    }

    private void procesarSalida(ElementoInventario elemento) {
        if (elemento.getIdElemento().equals(idObjetivo)) {
            if (elemento.getCantidad() >= cantidadRetirar) {
                elemento.setCantidad(elemento.getCantidad() - cantidadRetirar);
                mensaje = "Retiro exitoso de " + cantidadRetirar + " unidades de '" 
                + elemento.getNombre() + "'. Stock restante: " + elemento.getCantidad();
            } else {
                mensaje = "Retiro RECHAZADO para '" + elemento.getNombre() + "'. Stock insuficiente (" + elemento.getCantidad()
                 + " disponibles).";
            }
        }
    }

    @Override public void visit(EquipoComputo equipo) { procesarSalida(equipo); }
    @Override public void visit(Consumible consumible) { procesarSalida(consumible); }
    @Override public void visit(LicenciaDigital licencia) { procesarSalida(licencia); }

    public String getMensaje() { return mensaje; }
}