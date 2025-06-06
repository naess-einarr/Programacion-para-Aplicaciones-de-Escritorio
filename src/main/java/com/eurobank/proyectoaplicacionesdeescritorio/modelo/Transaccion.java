import java.time.LocalDateTime;

/**
 * Clase que representa una transacción bancaria en el sistema EuroBank.
 * Contiene información sobre el tipo, monto y cuentas involucradas.
 */
public class Transaccion {
    
    private String idTransaccion;
    private double montoTransaccion;
    private LocalDateTime fechaHoraTransaccion;
    private String tipoTransaccion; // "DEPOSITO", "RETIRO", "TRANSFERENCIA"
    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;
    private String idSucursalTransaccion;
    private String idEmpleadoResponsable;
    private String descripcionTransaccion;
    private String estadoTransaccion; // "COMPLETADA", "PENDIENTE", "FALLIDA"
    private String referenciaTransaccion;
    
    public Transaccion() {
    }
    
    public Transaccion(String idTransaccion, double montoTransaccion, 
                      LocalDateTime fechaHoraTransaccion, String tipoTransaccion,
                      String numeroCuentaOrigen, String idSucursalTransaccion,
                      String idEmpleadoResponsable) {
        
        this.idTransaccion = idTransaccion;
        this.montoTransaccion = montoTransaccion;
        this.fechaHoraTransaccion = fechaHoraTransaccion;
        this.tipoTransaccion = tipoTransaccion;
        this.numeroCuentaOrigen = numeroCuentaOrigen;
        this.idSucursalTransaccion = idSucursalTransaccion;
        this.idEmpleadoResponsable = idEmpleadoResponsable;
        this.estadoTransaccion = "PENDIENTE";
    }
    
    // Constructor para transferencias (incluye cuenta destino)
    public Transaccion(String idTransaccion, double montoTransaccion,
                      LocalDateTime fechaHoraTransaccion, String tipoTransaccion,
                      String numeroCuentaOrigen, String numeroCuentaDestino,
                      String idSucursalTransaccion, String idEmpleadoResponsable) {
        
        this(idTransaccion, montoTransaccion, fechaHoraTransaccion, tipoTransaccion,
             numeroCuentaOrigen, idSucursalTransaccion, idEmpleadoResponsable);
        this.numeroCuentaDestino = numeroCuentaDestino;
    }
    
    // Getters y Setters
    public String obtenerIdTransaccion() {
        return idTransaccion;
    }
    
    public void establecerIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }
    
    public double obtenerMontoTransaccion() {
        return montoTransaccion;
    }
    
    public void establecerMontoTransaccion(double montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }
    
    public LocalDateTime obtenerFechaHoraTransaccion() {
        return fechaHoraTransaccion;
    }
    
    public void establecerFechaHoraTransaccion(LocalDateTime fechaHoraTransaccion) {
        this.fechaHoraTransaccion = fechaHoraTransaccion;
    }
    
    public String obtenerTipoTransaccion() {
        return tipoTransaccion;
    }
    
    public void establecerTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
    
    public String obtenerNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }
    
    public void establecerNumeroCuentaOrigen(String numeroCuentaOrigen) {
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }
    
    public String obtenerNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }
    
    public void establecerNumeroCuentaDestino(String numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
    }
    
    public String obtenerIdSucursalTransaccion() {
        return idSucursalTransaccion;
    }
    
    public void establecerIdSucursalTransaccion(String idSucursalTransaccion) {
        this.idSucursalTransaccion = idSucursalTransaccion;
    }
    
    public String obtenerIdEmpleadoResponsable() {
        return idEmpleadoResponsable;
    }
    
    public void establecerIdEmpleadoResponsable(String idEmpleadoResponsable) {
        this.idEmpleadoResponsable = idEmpleadoResponsable;
    }
    
    public String obtenerDescripcionTransaccion() {
        return descripcionTransaccion;
    }
    
    public void establecerDescripcionTransaccion(String descripcionTransaccion) {
        this.descripcionTransaccion = descripcionTransaccion;
    }
    
    public String obtenerEstadoTransaccion() {
        return estadoTransaccion;
    }
    
    public void establecerEstadoTransaccion(String estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }
    
    public String obtenerReferenciaTransaccion() {
        return referenciaTransaccion;
    }
    
    public void establecerReferenciaTransaccion(String referenciaTransaccion) {
        this.referenciaTransaccion = referenciaTransaccion;
    }
    
    /**
     * Método para verificar si la transacción es una transferencia.
     * @return true si es transferencia, false en caso contrario
     */
    public boolean esTransferencia() {
        return "TRANSFERENCIA".equals(tipoTransaccion);
    }
    
    /**
     * Método para marcar la transacción como completada.
     */
    public void marcarComoCompletada() {
        this.estadoTransaccion = "COMPLETADA";
    }
    
    /**
     * Método para marcar la transacción como fallida.
     */
    public void marcarComoFallida() {
        this.estadoTransaccion = "FALLIDA";
    }
    
    @Override
    public String toString() {
        return "Transaccion{" +
               "idTransaccion='" + idTransaccion + '\'' +
               ", montoTransaccion=" + montoTransaccion +
               ", tipoTransaccion='" + tipoTransaccion + '\'' +
               ", estadoTransaccion='" + estadoTransaccion + '\'' +
               '}';
    }
}