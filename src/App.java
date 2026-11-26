public class App {
    public static void main(String[] args) throws Exception {
        Inventario inventario = Inventario.getInstancia();

        // Carga de inventario inicial (Consumible ya no lleva el valor booleano 'true')
        inventario.agregarElemento(new EquipoComputo("EQ-01", "Laptop Pro", 15000.0, 10, "Dell"));
        inventario.agregarElemento(new Consumible("CS-01", "Tóner Negro", 800.0, 20));
        inventario.agregarElemento(new LicenciaDigital("LC-01", "Windows 11 Pro", 2500.0, 15, 12));

        System.out.println("=== CASO DE PRUEBA: COMPETENCIA POR UN RECURSO Y CONSULTAS CONCURRENTES ===");
        System.out.println("Estado Inicial: EQ-01 tiene 10 unidades disponibles.\n");

        // Instanciamos los clientes que realizarán las peticiones
        Cliente cliente1 = new Cliente("Cliente 1 (José)", "CS-01", 7, "RETIRO");
        Cliente cliente2 = new Cliente("Cliente 2 (María)", "EQ-01", 7, "RETIRO");
        Cliente proveedor = new Cliente("Proveedor (HP)", "CS-01", 10, "ENTRADA");

        // Hilos para ejecutar las peticiones de los clientes concurrentemente
        Thread h1 = new Thread(cliente1);
        Thread h2 = new Thread(cliente2);
        Thread h4 = new Thread(proveedor);

        // Hilo de consulta / reporte del sistema
        Thread h3 = new Thread(() -> {
            ReporteInventarioVisitor v = new ReporteInventarioVisitor();
            inventario.aplicarVisitor(v);
            System.out.println("\n--- [Sistema] REPORTE DE INVENTARIO ---");
            System.out.println(v.obtenerReporte());
            System.out.println("----------------------------------------\n");
        });

        // Inicio de hilos simultáneos
        h1.start();
        h2.start();
        h3.start();
        h4.start();

        // Espera a que finalicen todos los hilos
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