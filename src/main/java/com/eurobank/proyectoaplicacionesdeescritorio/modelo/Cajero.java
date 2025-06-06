package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDate;

/**
 * Clase que representa un cajero del banco.
 * Hereda de Empleado y añade atributos específicos del rol de cajero.
 */
public class Cajero extends Empleado {
    
    private String horarioTrabajo;
    private int numeroVentanilla;
    
    public Cajero() {
        super();
    }
    
    public Cajero(String idEmpleado, String nombreCompleto, String direccionCompleta,
                  LocalDate fechaNacimiento, String generoEmpleado, double salarioMensual,
                  String nombreUsuario, String contraseñaAcceso, String horarioTrabajo, 
                  int numeroVentanilla) {
        
        super(idEmpleado, nombreCompleto, direccionCompleta, fechaNacimiento, 
              generoEmpleado, salarioMensual, nombreUsuario, contraseñaAcceso, "CAJERO");
        
        this.horarioTrabajo = horarioTrabajo;
        this.numeroVentanilla = numeroVentanilla;
    }
    
    public String getHorarioTrabajo() {
        return horarioTrabajo;
    }
    
    public void setHorarioTrabajo(String horarioTrabajo) {
        this.horarioTrabajo = horarioTrabajo;
    }
    
    public int getNumeroVentanilla() {
        return numeroVentanilla;
    }
    
    public void setNumeroVentanilla(int numeroVentanilla) {
        this.numeroVentanilla = numeroVentanilla;
    }
}