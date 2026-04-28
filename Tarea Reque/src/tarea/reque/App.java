
package tarea.reque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class App {
    private GestorDeCobro gestorDeCobro;
    public App() {
        this.gestorDeCobro = new GestorDeCobro();
    }
 
    public App(GestorDeCobro gestorDeCobro) {
        this.gestorDeCobro = gestorDeCobro;
    }
    public void solicitarReportePagos(String clienteId, LocalDate fecha) {
        System.out.println("Solicitud de reporte de pagos");
        System.out.printf("Cliente : %s%n", clienteId);
        System.out.printf("Fecha   : %s%n", fecha);
         
        List<DetalleDeCobro> transacciones = consultarTransacciones(clienteId, fecha); //Consultar transacciones al GestorDeCobro
 
        String datos = gestorDeCobro.formatearHistorial(transacciones); //Formatear el historial

        mostrarListaPagos(datos); //Mostrar la lista de pagos
        desplegarReporteDiario(); //Desplegar el reporte diario final
    }
    public List<DetalleDeCobro> consultarTransacciones(String clienteId, LocalDate fecha) {
        System.out.println("Consultando transacciones al GestorDeCobro...");
        List<DetalleDeCobro> resultado = gestorDeCobro.buscarPagosPorTarjeta(clienteId, fecha);
        System.out.printf("Transacciones encontradas: %d%n%n", resultado.size());
        return resultado;
    }
    public void mostrarListaPagos(String datos) {
        System.out.println("Mostrando lista de pagos al usuario:\n");
        System.out.println(datos);
    }
    public void desplegarReporteDiario() {
        System.out.println("Reporte diario desplegado correctamente.");
    }
    public void mostrarAlerta(String mensaje) {
    System.out.println("\n╔══════════════════════════════╗");
    System.out.println("║     !!!!!ALERTA AL USUARIO !!!!       ║");
    System.out.println("║  " + mensaje);
    System.out.println("╚══════════════════════════════╝");
    }
    
    public void estacionar(){
    
        Vehiculo vehiculo = obtenerVehiculo();
        
        Estacionamiento estacionamiento = new Estacionamiento();
        SensorParqueo sensor = new SensorParqueo();
        DetalleDeCobro detalle = new DetalleDeCobro("15", "40", "Tarjeta", 1000, LocalDateTime.now(), "4", 10);
        
        int espacio = 4;
        
        boolean disponible = estacionamiento.verificarDisponibilidad(espacio);
        
        if (disponible){
        
            boolean ocupado = sensor.detectarOcupación();
            if (ocupado){
            
                var horaLlegada = estacionamiento.registrarHoraDeLlegada();
                detalle.generarDetalle(horaLlegada);
            }
            System.out.println("Estacionando vehiculo: "  + vehiculo.getPlaca() + " en espacio " + espacio);
        }
            
        
    }
    
    public Vehiculo obtenerVehiculo(){
    
        String placa = "ABC123";
        
        Vehiculo vehiculo = new Vehiculo(placa);
        
        return vehiculo;
    }
    
    
}
