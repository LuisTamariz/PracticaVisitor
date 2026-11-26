public class ValuacionVisitor implements Visitor {
    private double totalValor = 0.0;
    @Override
    public void visit(EquipoComputo equipo) {
        totalValor += equipo.getPrecioUnitario() * equipo.getCantidad();
    }
    @Override
    public void visit(Consumible consumible) {
        totalValor += consumible.getPrecioUnitario() * consumible.getCantidad();
    }
    @Override
    public void visit(LicenciaDigital licencia) {
        totalValor += licencia.getPrecioUnitario() * licencia.getCantidad();
    }
    public double getTotalValor() {
        return totalValor;
    }
}