public class EquipoComputo extends ElementoInventario {
    private final String marca;

    public EquipoComputo(String idElemento, String nombre, double precioUnitario, int cantidad, String marca) {
        super(idElemento, nombre, precioUnitario, cantidad);
        this.marca = marca;
    }

    public String getMarca() { return marca; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}