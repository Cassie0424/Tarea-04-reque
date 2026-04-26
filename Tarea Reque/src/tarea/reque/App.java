
package tarea.reque;

import java.time.LocalDate;
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
}
