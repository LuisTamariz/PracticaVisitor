public class LicenciaDigital extends ElementoInventario {
    private final int duracionMeses;

    public LicenciaDigital(String idElemento, String nombre, double precioUnitario, int cantidad, int duracionMeses) {
        super(idElemento, nombre, precioUnitario, cantidad);
        this.duracionMeses = duracionMeses;
    }

    public int getDuracionMeses() { return duracionMeses; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}