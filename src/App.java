public class App {
    public static void main(String[] args) throws Exception {
     Inventario inventario = Inventario.getInstancia();

        // Carga de inventario inicial
        inventario.agregarElemento(new EquipoComputo("EQ-01", "Laptop Pro", 15000.0, 10, "Dell"));
        inventario.agregarElemento(new Consumible("CS-01", "Tóner Negro", 800.0, 20, true));
        inventario.agregarElemento(new LicenciaDigital("LC-01", "Windows 11 Pro", 2500.0, 15, 12));

        System.out.println("=== CASO DE PRUEBA: COMPETENCIA POR UN RECURSO Y CONSULTAS CONCURRENTES ===");
        System.out.println("Estado Inicial: EQ-01 tiene 10 unidades disponibles.\n");

        // Hilos de retiro compitiendo por 7 unidades de EQ-01
        Thread h1 = new Thread(() -> {
            SalidaInventarioVisitor v = new SalidaInventarioVisitor("EQ-01", 7);
            inventario.aplicarVisitor(v);
            System.out.println("[Hilo-1 (Retiro)] " + v.getMensaje());
        });

        Thread h2 = new Thread(() -> {
            SalidaInventarioVisitor v = new SalidaInventarioVisitor("EQ-01", 7);
            inventario.aplicarVisitor(v);
            System.out.println("[Hilo-2 (Retiro)] " + v.getMensaje());
        });

        // Hilo de consulta / reporte
        Thread h3 = new Thread(() -> {
            ReporteInventarioVisitor v = new ReporteInventarioVisitor();
            inventario.aplicarVisitor(v);
            System.out.println("\n--- [Hilo-3] REPORTE DE INVENTARIO ---");
            System.out.println(v.obtenerReporte());
            System.out.println("----------------------------------------\n");
        });

        // Hilo de entrada de mercancía
        Thread h4 = new Thread(() -> {
            EntradaInventarioVisitor v = new EntradaInventarioVisitor("CS-01", 10);
            inventario.aplicarVisitor(v);
            System.out.println("[Hilo-4 (Entrada)] Entrada agregada a CS-01.");
        });

        // Inicio de hilos simultáneos
        h1.start();
        h2.start();
        h3.start();
        h4.start();

        // Espera de finalización
        h1.join();
        h2.join();
        h3.join();
        h4.join();

        // Reporte de Valuación final
        ValuacionVisitor valuacion = new ValuacionVisitor();
        inventario.aplicarVisitor(valuacion);
        System.out.printf("\n[Resultado Final] Valuación Total del Inventario: $%.2f%n", valuacion.getTotalValor());
    }
}
