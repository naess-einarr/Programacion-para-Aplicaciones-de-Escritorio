package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDate;

/**
 * Clase que representa un ejecutivo de cuenta del banco.
 * Hereda de Empleado y añade atributos específicos del rol de ejecutivo.
 */
public class Ejecutivo extends Empleado {
    
    private int numeroClientesAsignados;
    private String especializacionEjecutivo;
    
    public Ejecutivo() {
        super();
    }
    
    public Ejecutivo(String idEmpleado, String nombreCompleto, String direccionCompleta,
                     LocalDate fechaNacimiento, String generoEmpleado, double salarioMensual,
                     String nombreUsuario, String contrasenaAcceso, int numeroClientesAsignados,
                     String especializacionEjecutivo) {
        
        super(idEmpleado, nombreCompleto, direccionCompleta, fechaNacimiento,
              generoEmpleado, salarioMensual, nombreUsuario, contrasenaAcceso, "EJECUTIVO");
        
        this.numeroClientesAsignados = numeroClientesAsignados;
        this.especializacionEjecutivo = especializacionEjecutivo;
    }

    public int getNumeroClientesAsignados() {
        return numeroClientesAsignados;
    }

    public void setNumeroClientesAsignados(int numeroClientesAsignados) {
        this.numeroClientesAsignados = numeroClientesAsignados;
    }

    public String getEspecializacionEjecutivo() {
        return especializacionEjecutivo;
    }

    public void setEspecializacionEjecutivo(String especializacionEjecutivo) {
        this.especializacionEjecutivo = especializacionEjecutivo;
    }
    
    
}