package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDate;

/**
 * Clase que representa un cajero del banco.
 * Hereda de Empleado y anade atributos específicos del rol de cajero.
 */
public class Cajero extends Empleado {
    
    private String horarioTrabajo;
    private Integer numeroVentanilla;
    
    public Cajero() {
        super();
        this.tipoEmpleado = "CAJERO";
    }
    
    public Cajero(String idEmpleado, String nombreCompleto, String direccionCompleta,
                  LocalDate fechaNacimiento, String generoEmpleado, double salarioMensual,
                  String nombreUsuario, String contrasenaAcceso, String horarioTrabajo, 
                  Integer numeroVentanilla) {
        
        super(idEmpleado, nombreCompleto, direccionCompleta, fechaNacimiento, 
              generoEmpleado, salarioMensual, nombreUsuario, contrasenaAcceso, "CAJERO");
        
        this.horarioTrabajo = horarioTrabajo;
        this.numeroVentanilla = numeroVentanilla;
    }
    
    public String getHorarioTrabajo() {
        return horarioTrabajo;
    }
    
    public void setHorarioTrabajo(String horarioTrabajo) {
        this.horarioTrabajo = horarioTrabajo;
    }
    
    public Integer getNumeroVentanilla() {
        return numeroVentanilla;
    }
    
    public void setNumeroVentanilla(Integer numeroVentanilla) {
        this.numeroVentanilla = numeroVentanilla;
    }
}