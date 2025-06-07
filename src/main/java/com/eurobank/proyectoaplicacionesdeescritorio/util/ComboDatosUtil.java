package com.eurobank.proyectoaplicacionesdeescritorio.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComboDatosUtil {

    private ComboDatosUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static final ObservableList<String> listaTipoEmpleado() {
        return FXCollections.observableArrayList(
                "GERENTE",
                "EJECUTIVO",
                "CAJERO"
        );
    }

    public static final ObservableList<String> listaGenero() {
        return FXCollections.observableArrayList(
                "MASCULINO",
                "FEMENINO"
        );
    }

    public static final ObservableList<String> listaNivelAcceso() {
        return FXCollections.observableArrayList(
                "SUCURSAL",
                "REGIONAL",
                "NACIONAL"
        );
    }
}
