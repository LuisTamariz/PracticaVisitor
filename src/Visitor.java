public interface Visitor {
    void visit(EquipoComputo equipo);
    void visit(Consumible consumible);
    void visit(LicenciaDigital licencia);
}