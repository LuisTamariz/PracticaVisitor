public class Consumible extends ElementoInventario {

    public Consumible(String idElemento, String nombre, double precioUnitario, int cantidad) {
        super(idElemento, nombre, precioUnitario, cantidad);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}