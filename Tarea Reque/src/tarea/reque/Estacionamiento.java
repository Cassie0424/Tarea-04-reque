package tarea.reque;

import java.time.LocalDateTime;

public class Estacionamiento {

    private String idEstacionamiento;
    private String idUsuario;
    private int tiempoRestanteMinutos;

    public Estacionamiento() {
        this.idEstacionamiento = "EST-001";
        this.idUsuario = "estudian@tec.com";
        this.tiempoRestanteMinutos = 5;
    }

    public boolean verificarDisponibilidad(int espacio) {
        
        boolean disponible = true;
        
        return disponible;
    }

    public LocalDateTime registrarHoraDeLlegada() {

        LocalDateTime horaLlegada = LocalDateTime.now();

        System.out.println("Hora de llegada registrada: " + horaLlegada);

        return horaLlegada;
    }

    public void consultarEstacionamiento() {}

    // Tiempo_Restante
    public int obtenerTiempoRestante(String idEstacionamiento) {
        System.out.println("Consultando tiempo restante para: " + idEstacionamiento);
        return tiempoRestanteMinutos;
    }

    // Solicita_Usuario
    public String solicitarDatosUsuario(String idEstacionamiento) {
        System.out.println("Obteniendo datos del usuario para: " + idEstacionamiento);
        return idUsuario;
    }
}
