
package com.eurobank.proyectoaplicacionesdeescritorio.excepciones;

public class TransaccionFallidaException extends Exception {
    
    public TransaccionFallidaException(String mensajeError) {
        super(mensajeError);
    }
    
    public TransaccionFallidaException(String mensajeError, Throwable causaError) {
        super(mensajeError, causaError);
    }
}
