import java.util.ArrayList;
import java.util.List;

public class ReporteInventarioVisitor implements Visitor {
    
    private final List<String> lineasReporte = new ArrayList<>();
// Implementación concreta de la operación Visitor sobre cada tipo de elemento
    @Override
    public void visit(EquipoComputo equipo) {
        String linea = String.format("[EQUIPO DE CÓMPUTO] ID: %s | Nombre: %s | Marca: %s | Cantidad: %d | Precio U.: $%.2f",
                equipo.getIdElemento(),
                equipo.getNombre(),
                equipo.getMarca(),
                equipo.getCantidad(),
                equipo.getPrecioUnitario());
        lineasReporte.add(linea);
    }

    @Override
    public void visit(Consumible consumible) {
        String linea = String.format("[CONSUMIBLE] ID: %s | Nombre: %s | Reordenable: %s | Cantidad: %d | Precio U.: $%.2f",
                consumible.getIdElemento(),
                consumible.getNombre(),
                consumible.isReordenable() ? "Sí" : "No",
                consumible.getCantidad(),
                consumible.getPrecioUnitario());
        lineasReporte.add(linea);
    }

    @Override
    public void visit(LicenciaDigital licencia) {
        String linea = String.format("[LICENCIA DIGITAL] ID: %s | Nombre: %s | Duración: %d meses | Cantidad: %d | Precio U.: $%.2f",
                licencia.getIdElemento(),
                licencia.getNombre(),
                licencia.getDuracionMeses(),
                licencia.getCantidad(),
                licencia.getPrecioUnitario());
        lineasReporte.add(linea);
    }

    // Método para obtener el reporte completo formateado en una cadena
    public String obtenerReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE GENERAL DE INVENTARIO ===\n");
        if (lineasReporte.isEmpty()) {
            sb.append("El inventario está vacío.\n");
        } else {
            for (String linea : lineasReporte) {
                sb.append(linea).append("\n");
            }
        }
        sb.append("=====================================");
        return sb.toString();
    }
}