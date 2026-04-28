package tarea.reque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║       PRUEBAS - SISTEMA DE PARQUÍMETROS      ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        //datos de prueba
        LocalDate hoy   = LocalDate.now();
        LocalDateTime t = LocalDateTime.now().withHour(8).withMinute(0);

        List<DetalleDeCobro> datos = new ArrayList<>();
        //2 pagos con tarjeta de crédito hoy
        datos.add(new DetalleDeCobro("TXN-001", "CLI-100", DetalleDeCobro.TARJETA_CREDITO, 1500.0, t.plusHours(0), "PQ-CENTRAL-01", 30));
        datos.add(new DetalleDeCobro("TXN-002", "CLI-100", DetalleDeCobro.TARJETA_CREDITO, 3000.0, t.plusHours(2), "PQ-NORTE-05",   60));
        //1 pago con efectivo hoy
        datos.add(new DetalleDeCobro("TXN-003", "CLI-100", DetalleDeCobro.EFECTIVO,        1500.0, t.plusHours(4), "PQ-SUR-02",     30));
        //1 pago con tarjeta de crédito hoy
        datos.add(new DetalleDeCobro("TXN-004", "CLI-200", DetalleDeCobro.TARJETA_CREDITO, 4500.0, t.plusHours(1), "PQ-ESTE-03",    90));
        //1 pago con tarjeta de crédito AYER
        datos.add(new DetalleDeCobro("TXN-005", "CLI-100", DetalleDeCobro.TARJETA_CREDITO, 2000.0, t.minusDays(1),"PQ-OESTE-07",   40));

        GestorDeCobro gestor = new GestorDeCobro(datos);

        // ── PRUEBA 1 ──────────────────────────────────────────────────────────
        encabezado(1, "Cliente con pagos en tarjeta de crédito hoy (CLI-100)");
        try {
            List<DetalleDeCobro> resultado = gestor.buscarPagosPorTarjeta("CLI-100", hoy);
            if (resultado.size() == 2) {
                ok("Se encontraron exactamente 2 pagos con tarjeta de crédito");
            } else {
                fallo("Se esperaban 2 pagos, pero se obtuvieron: " + resultado.size());
            }
            System.out.println(gestor.formatearHistorial(resultado));
        } catch (Exception e) {
            fallo("Excepción inesperada: " + e.getMessage());
        }

        // ── PRUEBA 2 ──────────────────────────────────────────────────────────
        encabezado(2, "Pagos mixtos: solo deben filtrarse los de tarjeta de crédito (CLI-100)");
        try {
            List<DetalleDeCobro> resultado = gestor.buscarPagosPorTarjeta("CLI-100", hoy);
            boolean hayEfectivo = resultado.stream()
                .anyMatch(d -> d.getMetodoPago().equalsIgnoreCase(DetalleDeCobro.EFECTIVO));
            if (!hayEfectivo) {
                ok("Ningún pago en efectivo aparece en el resultado");
            } else {
                fallo("Se coló un pago en efectivo en el reporte");
            }
        } catch (Exception e) {
            fallo("Excepción inesperada: " + e.getMessage());
        }

        // ── PRUEBA 3 ──────────────────────────────────────────────────────────
        encabezado(3, "Cliente sin pagos registrados (CLI-999)");
        try {
            List<DetalleDeCobro> resultado = gestor.buscarPagosPorTarjeta("CLI-999", hoy);
            if (resultado.isEmpty()) {
                ok("Lista vacía correctamente para cliente inexistente");
            } else {
                fallo("Se esperaba lista vacía, pero se obtuvieron: " + resultado.size());
            }
            System.out.println(gestor.formatearHistorial(resultado));
        } catch (Exception e) {
            fallo("Excepción inesperada: " + e.getMessage());
        }
        // ── PRUEBA 4 ──────────────────────────────────────────────────────────
        encabezado(4, "Simulación del Temporizador - Notificación de vencimiento");
        gestor.verificarVencimientos();

        //H1: Estacionar vehiculo
        App app = new App();
        
        app.estacionar();
        
        
    }

    // ── Utilidades de salida ──────────────────────────────────────────────────

    static void encabezado(int num, String descripcion) {
        System.out.println("┌─────────────────────────────────────────────────");
        System.out.printf( "│ PRUEBA %d: %s%n", num, descripcion);
        System.out.println("└─────────────────────────────────────────────────");
    }

    static void ok(String mensaje) {
        System.out.println("EXISTOSO " + mensaje + "\n");
    }

    static void fallo(String mensaje) {
        System.out.println("FALLO " + mensaje + "\n");
    }
}
