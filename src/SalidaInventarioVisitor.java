public class SalidaInventarioVisitor implements Visitor {
    private final String idObjetivo;
    private final int cantidadRetirar;
    private boolean exito = false;
    private String mensaje = "";

    public SalidaInventarioVisitor(String idObjetivo, int cantidadRetirar) {
        this.idObjetivo = idObjetivo;
        this.cantidadRetirar = cantidadRetirar;
    }

    private void procesarSalida(ElementoInventario elemento) {
        if (elemento.getIdElemento().equals(idObjetivo)) {
            // Verificación y modificación dentro de la región crítica atómica
            if (elemento.getCantidad() >= cantidadRetirar) {
                try {
                    Thread.sleep(10); // Simulación de procesamiento
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                elemento.setCantidad(elemento.getCantidad() - cantidadRetirar);
                this.exito = true;
                this.mensaje = String.format("Retiro exitoso de %d unidades. Quedan: %d", cantidadRetirar, elemento.getCantidad());
            } else {
                this.exito = false;
                this.mensaje = String.format("Retiro fallido. Stock insuficiente (%d disponibles, %d solicitadas).", elemento.getCantidad(), cantidadRetirar);
            }
        }
    }

    @Override public void visit(EquipoComputo equipo) { procesarSalida(equipo); }
    @Override public void visit(Consumible consumible) { procesarSalida(consumible); }
    @Override public void visit(LicenciaDigital licencia) { procesarSalida(licencia); }

    public String getMensaje() { return mensaje; }
    public boolean isExito() { return exito; }
}