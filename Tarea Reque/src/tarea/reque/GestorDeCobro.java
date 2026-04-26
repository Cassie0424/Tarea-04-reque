
package tarea.reque;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorDeCobro {
    private List<Transaccion> listaTransacciones;
    private DetalleDeCobro detalleDeCobro;

    public GestorDeCobro() {
        this.listaTransacciones = new ArrayList<>();
        this.detalleDeCobro = new DetalleDeCobro();
    }
    
    public int calcularMonto(){
        return 0;
    }
    
    public void consultarPagos(){}
    
    public void solicitarDatos(){}
    
    
    
}
