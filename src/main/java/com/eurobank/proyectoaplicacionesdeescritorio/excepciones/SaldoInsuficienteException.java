package com.eurobank.proyectoaplicacionesdeescritorio.excepciones;

public class SaldoInsuficienteException extends Exception {
    
    public SaldoInsuficienteException(String mensajeError) {
        super(mensajeError);
    }
    
    public SaldoInsuficienteException(String mensajeError, Throwable causaError) {
        super(mensajeError, causaError);
    }
}