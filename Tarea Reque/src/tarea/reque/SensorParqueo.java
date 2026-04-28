package tarea.reque;

public class SensorParqueo {

    int id;

    // Detecta si hubo un cambio en el espacio
    public void detectarCambio() {
        System.out.println("[Sensor " + id + "] Cambio detectado en el espacio.");
        notificar();
    }

    // Notifica al gestor cuando hay cambio
    public void notificar() {
        System.out.println("[Sensor " + id + "] Notificando al sistema...");
        GestorDeCobro gestor = new GestorDeCobro();
        gestor.verificarVencimientos();
    }
    
    public boolean detectarOcupación(){
    
        boolean ocupado = false;
        
        return ocupado;   
    }
    
    
}
