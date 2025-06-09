package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class CuentaBancaria {
    
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoActual;
    private double limiteCredito;
    private LocalDateTime fechaApertura;
    private boolean cuentaActiva;
    private Cliente cliente;
    private Sucursal sucursal;
    private List<Transaccion> listaTransaccionesAsociadas;
    
    public CuentaBancaria() {
        
    }

    public CuentaBancaria(String numeroCuenta, String tipoCuenta, double saldoActual, double limiteCredito, LocalDateTime fechaApertura, boolean cuentaActiva, Cliente cliente, Sucursal sucursal, List<Transaccion> listaTransaccionesAsociadas) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoActual = saldoActual;
        this.limiteCredito = limiteCredito;
        this.fechaApertura = fechaApertura;
        this.cuentaActiva = cuentaActiva;
        this.cliente = cliente;
        this.sucursal = sucursal;
        this.listaTransaccionesAsociadas = listaTransaccionesAsociadas;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public boolean isCuentaActiva() {
        return cuentaActiva;
    }

    public void setCuentaActiva(boolean cuentaActiva) {
        this.cuentaActiva = cuentaActiva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<Transaccion> getListaTransaccionesAsociadas() {
        return listaTransaccionesAsociadas;
    }

    public void setListaTransaccionesAsociadas(List<Transaccion> listaTransaccionesAsociadas) {
        this.listaTransaccionesAsociadas = listaTransaccionesAsociadas;
    }

    @Override
    public String toString() {
        return numeroCuenta + " - " + cliente.getNombreCompleto() + " " + cliente.getApellidosCompletos();
    }
    
}