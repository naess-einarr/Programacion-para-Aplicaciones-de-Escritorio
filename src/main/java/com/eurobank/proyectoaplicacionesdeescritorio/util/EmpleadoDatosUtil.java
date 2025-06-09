package com.eurobank.proyectoaplicacionesdeescritorio.util;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmpleadoDatosUtil {

    public static final String TIPO_GERENTE = "GERENTE";
    public static final String TIPO_EJECUTIVO = "EJECUTIVO";
    public static final String TIPO_CAJERO = "CAJERO";

    public static final String GENERO_MASCULINO = "MASCULINO";
    public static final String GENERO_FEMENINO = "FEMENINO";

    public static final String NIVEL_SUCURSAL = "SUCURSAL";
    public static final String NIVEL_REGIONAL = "REGIONAL";
    public static final String NIVEL_NACIONAL = "NACIONAL";
    
    public static final String ESPECIALIZACION_PYMES = "PYMES";
    public static final String ESPECIALIZACION_CORPORATIVO = "CORPORATIVO";
    
    public static final String HORARIO_MATUTINO = "MATUTINO 8:00 - 14:00";
    public static final String HORARIO_VESPERTINO = "VESPERTINO 14:00 - 20:00"; 
    public static final String HORARIO_COMPLETO = "COMPLETO  8:00 - 17:00"; 
    public static final String HORARIO_SABADOS = "SABADOS 9:00 - 13:00"; 
    
    public static final Integer VENTANILLA_1 = 1;
    public static final Integer VENTANILLA_2 = 2;
    public static final Integer VENTANILLA_3 = 3;
    public static final Integer VENTANILLA_4 = 4;
    public static final Integer VENTANILLA_5 = 5;


    private EmpleadoDatosUtil() {
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
    
        public static final ObservableList<String> listaEspecializacion() {
        return FXCollections.observableArrayList(
                ESPECIALIZACION_PYMES,
                ESPECIALIZACION_CORPORATIVO
        );
    }
    
    public static final ObservableList<String> listaHorarioDeTrabajo() {
        return FXCollections.observableArrayList(
                HORARIO_MATUTINO,
                HORARIO_VESPERTINO,
                HORARIO_COMPLETO,
                HORARIO_SABADOS
        );
    }
    
    public static final ObservableList<Integer> listaVentanilla() {
        return FXCollections.observableArrayList(
                VENTANILLA_1,
                VENTANILLA_2,
                VENTANILLA_3,
                VENTANILLA_4,
                VENTANILLA_5
        );
    }

    public static final String generaContrasenaGenerica(String nombre, LocalDate fechaNacimiento) {
        String primerosTresCaracteres = nombre.length() >= 3
                ? nombre.substring(0, 3).toUpperCase()
                : nombre.toUpperCase();

        String dia = String.format("%02d", fechaNacimiento.getDayOfMonth());
        String mes = String.format("%02d", fechaNacimiento.getMonthValue());
        String anio = String.format("%02d", fechaNacimiento.getYear() % 100);

        return primerosTresCaracteres + dia + mes + anio;
    }
}
