package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDateTime;

public class Transaccion {
    
    private String idTransaccion;
    private double montoTransaccion;
    private LocalDateTime fechaHoraTransaccion;
    private String tipoTransaccion; 
    private CuentaBancaria cuentaOrigen;
    private CuentaBancaria cuentaDestino;
    private Sucursal sucursal;
    private Empleado empleadoResponsable;
    private String referenciaTransaccion;
    
    public Transaccion() {
    }
    
    public Transaccion(String idTransaccion, double montoTransaccion, 
                      LocalDateTime fechaHoraTransaccion, String tipoTransaccion,
                      CuentaBancaria cuentaOrigen, Sucursal sucursal,
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
                      CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino,
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

    public String getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }

    public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }

    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    public void setNumeroCuentaDestino(String numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

    public String getIdSucursalTransaccion() {
        return idSucursalTransaccion;
    }

    public void setIdSucursalTransaccion(String idSucursalTransaccion) {
        this.idSucursalTransaccion = idSucursalTransaccion;
    }

    public String getIdEmpleadoResponsable() {
        return idEmpleadoResponsable;
    }

    public void setIdEmpleadoResponsable(String idEmpleadoResponsable) {
        this.idEmpleadoResponsable = idEmpleadoResponsable;
    }

    public String getReferenciaTransaccion() {
        return referenciaTransaccion;
    }

    public void setReferenciaTransaccion(String referenciaTransaccion) {
        this.referenciaTransaccion = referenciaTransaccion;
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