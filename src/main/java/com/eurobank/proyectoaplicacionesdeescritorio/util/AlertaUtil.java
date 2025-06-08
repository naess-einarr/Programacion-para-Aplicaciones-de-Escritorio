
package com.eurobank.proyectoaplicacionesdeescritorio.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertaUtil {
    
    public static final String ADVERTENCIA = "ADVERTENCIA";
    public static final String ERROR = "ERROR";
    public static final String INFORMACION = "INFORMACION";
    public static final String CONFIRMACION = "CONFIRMACION";

    
    private AlertaUtil(){
        
        throw new UnsupportedOperationException(ConstantesUtil.ALERTA_CLASE_UTILERIA);
    }
    
    public static void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    public static boolean mostrarAlertaCancelarGuardado(){
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle(CONFIRMACION);
        confirmacion.setHeaderText("¿Está seguro que desea cancelar?");
        confirmacion.setContentText("Se perderán todos los datos ingresados.");
        confirmacion.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        return confirmacion.showAndWait().orElse(ButtonType.NO) == ButtonType.YES;
    }
    
    public static boolean mostrarAlertaEliminar(){
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle(CONFIRMACION);
        confirmacion.setHeaderText("¿Está seguro que desea eliminar este registro?");
        confirmacion.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        return confirmacion.showAndWait().orElse(ButtonType.NO) == ButtonType.YES;
    }
    
    
}
