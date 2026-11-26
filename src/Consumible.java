public class Consumible extends ElementoInventario {
    private final boolean reordenable;

    public Consumible(String idElemento, String nombre, double precioUnitario, int cantidad, boolean reordenable) {
        super(idElemento, nombre, precioUnitario, cantidad);
        this.reordenable = reordenable;
    }

    public boolean isReordenable() { return reordenable; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}