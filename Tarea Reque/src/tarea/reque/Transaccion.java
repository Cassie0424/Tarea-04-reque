import java.util.Date;

public class Transaccion {
    private int idTransaccion;
    private int clienteId;
    private double monto;
    private Date fecha;
    private String metodoPago;
    private String tipo;

    public Transaccion(int idTransaccion, int clienteId, double monto, Date fecha, String metodoPago, String tipo) {
        this.idTransaccion = idTransaccion;
        this.clienteId = clienteId;
        this.monto = monto;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.tipo = tipo;
    }
    // Setters
    public void setidTransaccion(int idTransaccion){
        this.idTransaccion=idTransaccion;
    }
    public void setClienteId(int clienteId){
        this.clienteId=clienteId;
    }
    public void setMonto(double monto){
        this.monto=monto;
    }
    public void setFecha(Date fecha){
        this.fecha=fecha;
    }
    public void setMetodoPago(String metodoPago){
        this.metodoPago=metodoPago;
    }
    public void setTipo(String tipo){
        this.tipo=tipo;
    }
    // Getters
    public int getClienteId() { 
        return clienteId; 
    }
    public double getMonto() { 
        return monto; 
    }
    public Date getFecha() { 
        return fecha; 
    }
    public String getMetodoPago() { 
        return metodoPago; 
    }
    public String getTipo() { 
        return tipo; 
    }

    // Otros métodos
    public boolean esPagoConTarjeta() {
        return "Tarjeta de Crédito".equalsIgnoreCase(metodoPago);
    }

    public boolean esDeFecha(Date fecha) {
        return this.fecha.equals(fecha);
    }
    @Override
    public String toString() {
        return "Transaccion{" +
                "clienteId=" + clienteId +
                ", monto=" + monto +
                ", fecha=" + fecha +
                ", metodoPago='" + metodoPago + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}