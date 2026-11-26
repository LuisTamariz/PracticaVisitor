import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Inventario {
    
    private static volatile Inventario instancia;
    private static final ReentrantLock lockSingleton = new ReentrantLock();

    private final List<ElementoInventario> elementos = new ArrayList<>();
    private final ReentrantLock lockInventario = new ReentrantLock();

    private Inventario() {}

    public static Inventario getInstancia() {
        Inventario resultado = instancia;
        if (resultado == null) {
            lockSingleton.lock();
            try {
                resultado = instancia;
                if (resultado == null) {
                    instancia = resultado = new Inventario();
                }
            } finally {
                lockSingleton.unlock();
            }
        }
        return resultado;
    }

    public void agregarElemento(ElementoInventario elemento) {
        lockInventario.lock();
        try {
            elementos.add(elemento);
        } finally {
            lockInventario.unlock();
        }
    }
// Garantiza acceso exclusivo al aplicar el visitante sobre la colecció
    public void aplicarVisitor(Visitor visitor) {
        lockInventario.lock();
        try {
            for (ElementoInventario elemento : elementos) {
                elemento.accept(visitor);
            }
        } finally {
            lockInventario.unlock();
        }
    }
}