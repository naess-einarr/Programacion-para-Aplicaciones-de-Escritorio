package com.eurobank.proyectoaplicacionesdeescritorio.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComboDatosUtil {

    public static final String TIPO_GERENTE = "GERENTE";
    public static final String TIPO_EJECUTIVO = "EJECUTIVO";
    public static final String TIPO_CAJERO = "CAJERO";

    public static final String GENERO_MASCULINO = "MASCULINO";
    public static final String GENERO_FEMENINO = "FEMENINO";

    public static final String NIVEL_SUCURSAL = "SUCURSAL";
    public static final String NIVEL_REGIONAL = "REGIONAL";
    public static final String NIVEL_NACIONAL = "NACIONAL";

    private ComboDatosUtil() {
        throw new IllegalStateException("Clase de utileria.");
    }

// Métodos usando las constantes
    public static final ObservableList<String> listaTipoEmpleado() {
        return FXCollections.observableArrayList(
                TIPO_GERENTE,
                TIPO_EJECUTIVO,
                TIPO_CAJERO
        );
    }

    public static final ObservableList<String> listaGenero() {
        return FXCollections.observableArrayList(
                GENERO_MASCULINO,
                GENERO_FEMENINO
        );
    }

    public static final ObservableList<String> listaNivelAcceso() {
        return FXCollections.observableArrayList(
                NIVEL_SUCURSAL,
                NIVEL_REGIONAL,
                NIVEL_NACIONAL
        );
    }

}
