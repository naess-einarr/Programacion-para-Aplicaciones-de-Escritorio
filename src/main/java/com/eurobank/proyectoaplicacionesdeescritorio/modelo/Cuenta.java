package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

public class Cuenta {
    
    private String numeroCuenta;
    private String tipo;
    private double saldoActual;
    private double limiteCredito;
    private Cliente cliente;
    
    public Cuenta() {
        
    }

    public Cuenta(String numeroCuenta, String tipo, double saldoActual, double limiteCredito, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.saldoActual = saldoActual;
        this.limiteCredito = limiteCredito;
        this.cliente = cliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

 
    @Override
    public String toString() {
        return numeroCuenta + " - " + cliente.getNombreCompleto() + " " + cliente.getApellidosCompletos();
    }
    
}