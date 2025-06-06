
package com.eurobank.proyectoaplicacionesdeescritorio.excepciones;

public class ClienteNoEncontradoException extends Exception {
    
    public ClienteNoEncontradoException(String mensajeError) {
        super(mensajeError);
    }
    
    public ClienteNoEncontradoException(String mensajeError, Throwable causaError) {
        super(mensajeError, causaError);
    }
}