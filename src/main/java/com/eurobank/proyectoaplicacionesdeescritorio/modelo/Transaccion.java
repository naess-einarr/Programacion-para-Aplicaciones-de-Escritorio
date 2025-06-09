package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDateTime;

public class Transaccion {
    
    private String idTransaccion;
    private double montoTransaccion;
    private LocalDateTime fechaHoraTransaccion;
    private String tipoTransaccion; 
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private Sucursal sucursal;
    private Empleado empleadoResponsable;
    
    public Transaccion() {
    }
    
    public Transaccion(String idTransaccion, double montoTransaccion, 
                      LocalDateTime fechaHoraTransaccion, String tipoTransaccion,
                      Cuenta cuentaOrigen, Sucursal sucursal,
                      Empleado empleadoResponsable) {
        
        this.idTransaccion = idTransaccion;
        this.montoTransaccion = montoTransaccion;
        this.fechaHoraTransaccion = fechaHoraTransaccion;
        this.tipoTransaccion = tipoTransaccion;
        this.cuentaOrigen = cuentaOrigen;
        this.sucursal = sucursal;
        this.empleadoResponsable = empleadoResponsable;
    }
    
    public Transaccion(String idTransaccion, double montoTransaccion,
                      LocalDateTime fechaHoraTransaccion, String tipoTransaccion,
                      Cuenta cuentaOrigen, Cuenta cuentaDestino,
                      Sucursal sucursal, Empleado empleadoResponsable) {
        
        this(idTransaccion, montoTransaccion, fechaHoraTransaccion, tipoTransaccion,
             cuentaOrigen, sucursal, empleadoResponsable);
        this.cuentaDestino = cuentaDestino;
    }
    
    public String getIdTransaccion() {
        return idTransaccion;
    }
    
    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public double getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(double montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public LocalDateTime getFechaHoraTransaccion() {
        return fechaHoraTransaccion;
    }

    public void setFechaHoraTransaccion(LocalDateTime fechaHoraTransaccion) {
        this.fechaHoraTransaccion = fechaHoraTransaccion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
    
    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Empleado getEmpleadoResponsable() {
        return empleadoResponsable;
    }

    public void setEmpleadoResponsable(Empleado empleadoResponsable) {
        this.empleadoResponsable = empleadoResponsable;
    }
    

    
    public boolean esTransferencia() {
        return "TRANSFERENCIA".equals(tipoTransaccion);
    }
    
    @Override
    public String toString() {
        return "Transaccion{" +
               "idTransaccion='" + idTransaccion + '\'' +
               ", montoTransaccion=" + montoTransaccion +
               ", tipoTransaccion='" + tipoTransaccion + '\'' +
               '}';
    }
}