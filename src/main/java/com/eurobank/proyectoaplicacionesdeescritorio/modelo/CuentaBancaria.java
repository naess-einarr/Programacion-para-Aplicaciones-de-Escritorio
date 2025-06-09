package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class CuentaBancaria {
    
    private String numeroCuenta;
    private String tipoCuenta; // "CORRIENTE", "AHORROS", "EMPRESARIAL"
    private double saldoActual;
    private double limiteCredito;
    private LocalDateTime fechaApertura;
    private String idClienteAsociado;
    private String idSucursalAsociada;
    private boolean cuentaActiva;
    private List<String> listaTransaccionesAsociadas;
    
    public CuentaBancaria() {
    }
    
    public CuentaBancaria(String numeroCuenta, String tipoCuenta, double saldoActual,
                         double limiteCredito, LocalDateTime fechaApertura, 
                         String idClienteAsociado, String idSucursalAsociada) {
        
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoActual = saldoActual;
        this.limiteCredito = limiteCredito;
        this.fechaApertura = fechaApertura;
        this.idClienteAsociado = idClienteAsociado;
        this.idSucursalAsociada = idSucursalAsociada;
        this.cuentaActiva = true;
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

    public String getIdClienteAsociado() {
        return idClienteAsociado;
    }

    public void setIdClienteAsociado(String idClienteAsociado) {
        this.idClienteAsociado = idClienteAsociado;
    }

    public String getIdSucursalAsociada() {
        return idSucursalAsociada;
    }

    public void setIdSucursalAsociada(String idSucursalAsociada) {
        this.idSucursalAsociada = idSucursalAsociada;
    }

    public boolean isCuentaActiva() {
        return cuentaActiva;
    }

    public void setCuentaActiva(boolean cuentaActiva) {
        this.cuentaActiva = cuentaActiva;
    }

    public List<String> getListaTransaccionesAsociadas() {
        return listaTransaccionesAsociadas;
    }

    public void setListaTransaccionesAsociadas(List<String> listaTransaccionesAsociadas) {
        this.listaTransaccionesAsociadas = listaTransaccionesAsociadas;
    }
    
    public boolean tieneSaldoSuficiente(double montoRequerido) {
        return (saldoActual + limiteCredito) >= montoRequerido;
    }
    
    public void realizarDeposito(double montoDeposito) {
        if (montoDeposito > 0) {
            this.saldoActual += montoDeposito;
        }
    }
    
    public boolean realizarRetiro(double montoRetiro) {
        if (tieneSaldoSuficiente(montoRetiro)) {
            this.saldoActual -= montoRetiro;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "CuentaBancaria{" +
               "numeroCuenta='" + numeroCuenta + '\'' +
               ", tipoCuenta='" + tipoCuenta + '\'' +
               ", saldoActual=" + saldoActual +
               ", cuentaActiva=" + cuentaActiva +
               '}';
    }
}