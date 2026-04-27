package tarea.reque;

public class Estacionamiento {

    private String idEstacionamiento;
    private String idUsuario;
    private int tiempoRestanteMinutos;

    public Estacionamiento() {
        this.idEstacionamiento = "EST-001";
        this.idUsuario = "estudian@tec.com";
        this.tiempoRestanteMinutos = 5;
    }

    public boolean verificarDisponibilidad() {
        return true;
    }

    public void registrarHora(String tipo) {}

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
