public class EntradaInventarioVisitor implements Visitor {
    private final String idObjetivo;
    private final int cantidadIncrementar;

    public EntradaInventarioVisitor(String idObjetivo, int cantidadIncrementar) {
        this.idObjetivo = idObjetivo;
        this.cantidadIncrementar = cantidadIncrementar;
    }

    private void procesarEntrada(ElementoInventario elemento) {
        if (elemento.getIdElemento().equals(idObjetivo)) {
            elemento.setCantidad(elemento.getCantidad() + cantidadIncrementar);
        }
    }

    @Override public void visit(EquipoComputo equipo) { procesarEntrada(equipo); }
    @Override public void visit(Consumible consumible) { procesarEntrada(consumible); }
    @Override public void visit(LicenciaDigital licencia) { procesarEntrada(licencia); }
}