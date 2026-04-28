
package tarea.reque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DetalleDeCobro {
    private String transaccionId;
    private String clienteId;
    private String metodoPago;
    private double monto;
    private LocalDateTime fechaHora;
    private String parquimetroId;
    private int minutosEstacionado;
    
    //Metodos de pago
    public static final String TARJETA_CREDITO  = "Tarjeta de Crédito";
    public static final String TARJETA_DEBITO   = "Tarjeta de Débito";
    public static final String EFECTIVO         = "Efectivo";
    
    public DetalleDeCobro(String transaccionId, String clienteId, String metodoPago, double monto, LocalDateTime fechaHora, String parquimetroId, int minutosEstacionado) {
        this.transaccionId      = transaccionId;
        this.clienteId          = clienteId;
        this.metodoPago         = metodoPago;
        this.monto              = monto;
        this.fechaHora          = fechaHora;
        this.parquimetroId      = parquimetroId;
        this.minutosEstacionado = minutosEstacionado;
    }
    public boolean validarMetodoDePago(String metodoPagoEsperado) {
        if (metodoPagoEsperado == null || metodoPagoEsperado.isBlank()) {
            throw new IllegalArgumentException("El método de pago no puede ser nulo o vacío.");
        }
        return this.metodoPago != null && this.metodoPago.equalsIgnoreCase(metodoPagoEsperado.trim());
    }
    public static List<DetalleDeCobro> filtrarPorMetodoPago(List<DetalleDeCobro> detalles, String metodoPagoEsperado) {
        List<DetalleDeCobro> resultado = new ArrayList<>();
        if (detalles == null) return resultado;
 
        for (DetalleDeCobro detalle : detalles) {
            if (detalle.validarMetodoDePago(metodoPagoEsperado)) {
                resultado.add(detalle);
            }
        }
        return resultado;
    }
    public String getTransaccionId(){ 
        return transaccionId; 
    }
    public String getClienteId(){ 
        return clienteId; 
    }
    public String getMetodoPago(){ 
        return metodoPago; 
    }
    public double getMonto(){ 
        return monto; 
    }
    public LocalDateTime getFechaHora(){ 
        return fechaHora; 
    }
    public String getParquimetroId(){ 
        return parquimetroId; 
    }
    public int    getMinutosEstacionado(){
        return minutosEstacionado; 
    }
    @Override
    public String toString() {
        return String.format(
            "DetalleDeCobro{id='%s', cliente='%s', metodo='%s', monto=%.2f, " +
            "fechaHora=%s, parquimetro='%s', minutos=%d}",
            transaccionId, clienteId, metodoPago, monto,
            fechaHora, parquimetroId, minutosEstacionado
        );
    }
    
    public void generarDetalle(LocalDateTime horaLlegada){
        System.out.println("Detalle de cobro");
        System.out.println("Hora de llegada: " + horaLlegada);
    }
}
