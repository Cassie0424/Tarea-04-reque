
package tarea.reque;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class GestorDeCobro {
    private List<DetalleDeCobro> listaTransacciones;

    public GestorDeCobro() {
        this.listaTransacciones = new ArrayList<>();
    }
    public GestorDeCobro(List<DetalleDeCobro> repositorio) {
        this.listaTransacciones = repositorio != null ? repositorio : new ArrayList<>();
    }
    public List<DetalleDeCobro> buscarPagosPorTarjeta(String clienteId, LocalDate fecha) {
        validarParametrosBusqueda(clienteId, fecha);
 
        List<DetalleDeCobro> porClienteYFecha = filtrarPorClienteYFecha(clienteId, fecha); //Filtrar por cliente y fecha
        List<DetalleDeCobro> listaTransacciones =
            DetalleDeCobro.filtrarPorMetodoPago(porClienteYFecha, DetalleDeCobro.TARJETA_CREDITO); //Delegar validación del método de pago en DetalleDeCobro
        return listaTransacciones;
    }
    public String formatearHistorial(List<DetalleDeCobro> listaTransacciones) {
        if (listaTransacciones == null || listaTransacciones.isEmpty()) {
            return "No se encontraron pagos con tarjeta de crédito para los criterios indicados.";
        }
 
        String historial = """
                           ========================================\n
                             HISTORIAL DE PAGOS - PARQUÍMETROS   \n
                             Método de pago: Tarjeta de Crédito  \n
                           ========================================\n\n""";
 
        double totalDia = 0.0;
 
        for (int i = 0; i < listaTransacciones.size(); i++) {
            DetalleDeCobro d = listaTransacciones.get(i);
            historial += String.format("Transacción #%d%n", i + 1);
            historial += String.format("  ID            : %s%n", d.getTransaccionId());
            historial += String.format("  Parquímetro   : %s%n", d.getParquimetroId());
            historial += String.format("  Fecha/Hora    : %s%n", d.getFechaHora());
            historial += String.format("  Minutos       : %d min%n", d.getMinutosEstacionado());
            historial += String.format("  Monto         : $%.2f%n", d.getMonto());
            historial += String.format("  Método de Pago: %s%n", d.getMetodoPago());
            historial += "----------------------------------------\n";
            totalDia  += d.getMonto();
        }
 
        historial += String.format("%nTotal del día   : $%.2f%n", totalDia);
        historial += String.format("Transacciones   : %d%n", listaTransacciones.size());
        historial += "========================================\n";
 
        return historial;
    }
    private List<DetalleDeCobro> filtrarPorClienteYFecha(String clienteId, LocalDate fecha) {
        List<DetalleDeCobro> resultado = new ArrayList<>();
        for (DetalleDeCobro d : listaTransacciones) {
            boolean mismoCliente = clienteId.equalsIgnoreCase(d.getClienteId());
            boolean mismaFecha   = d.getFechaHora().toLocalDate().equals(fecha);
            if (mismoCliente && mismaFecha) {
                resultado.add(d);
            }
        }
        return resultado;
    }
    private void validarParametrosBusqueda(String clienteId, LocalDate fecha) {
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("El clienteId no puede ser nulo o vacío.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
    }
    public int calcularMonto(){
        return 0;
    }
    
    public void consultarPagos(){}
    
    public void solicitarDatos(){}
    // La llmada que hace e temporizador
    public void verificarVencimientos() {
        System.out.println("\n[Temporizador] verificarVencimientos()");

        String idEstacionamiento = "EST-001";
        Estacionamiento estacionamiento = new Estacionamiento();

        int tiempoRestante = estacionamiento.obtenerTiempoRestante(idEstacionamiento);
        System.out.println("Tiempo restante: " + tiempoRestante + " minutos");

        if (tiempoRestante <= 5) {
            String infoUsuario = estacionamiento.solicitarDatosUsuario(idEstacionamiento);
            String mensaje = "¡¡¡¡Su tiempo vence en " + tiempoRestante + " minutos!!!!!";
            enviarNotificacion(infoUsuario, mensaje);
    }
}

// Enviarnotificacion
    public void enviarNotificacion(String infoUsuario, String mensaje) {
        System.out.println("Enviando notificación a: " + infoUsuario);
        App app = new App(this);
        app.mostrarAlerta(mensaje);
}
    
    
}
