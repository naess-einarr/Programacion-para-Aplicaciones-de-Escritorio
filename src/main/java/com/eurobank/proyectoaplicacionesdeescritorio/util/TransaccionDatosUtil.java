
package com.eurobank.proyectoaplicacionesdeescritorio.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TransaccionDatosUtil {
    public static final String TIPO_DEPOSITO = "DEPOSITO";
    public static final String TIPO_RETIRO = "RETIRO";
    public static final String TIPO_TRANSFERENCIA = "TRANSFERENCIA";
    
    private TransaccionDatosUtil(){
        throw new IllegalStateException(ConstantesUtil.ALERTA_CLASE_UTILERIA);
    }
    
    public static final ObservableList<String> listaTipoTransaccion (){
        return FXCollections.observableArrayList(
                TIPO_DEPOSITO,
                TIPO_RETIRO,
                TIPO_TRANSFERENCIA
            );
    }
}
