
package com.eurobank.proyectoaplicacionesdeescritorio.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CuentaDatosUtil {
    
    public static final String PREFIJO_CUENTA = "CTA";
    public static final String CORRIENTE = "CORRIENTE";
    public static final String AHORROS = "AHORROS";
    public static final String EMPRESARIAL = "EMPRESARIAL";
    
    private CuentaDatosUtil() {
        throw new IllegalStateException("Clase de utileria.");
    }

    public static final ObservableList<String> listaTipo() {
        return FXCollections.observableArrayList(
                CORRIENTE,
                AHORROS,
                EMPRESARIAL
        );
    }
}
