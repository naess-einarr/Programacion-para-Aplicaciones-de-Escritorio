package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDate;

/**
 * Clase que representa un gerente del banco.
 * Hereda de Empleado y anade atributos específicos del rol de gerente.
 */
public class Gerente extends Empleado {
    
    private String nivelAcceso;
    private Integer aniosExperiencia;
    
    public Gerente() {
        super();
        this.tipoEmpleado = "GERENTE";
    }
    
    public Gerente(String idEmpleado, String nombreCompleto, String direccionCompleta,
                   LocalDate fechaNacimiento, String generoEmpleado, double salarioMensual,
                   String nombreUsuario, String contrasenaAcceso, String nivelAcceso,
                   Integer aniosExperiencia) {
        
        super(idEmpleado, nombreCompleto, direccionCompleta, fechaNacimiento,
              generoEmpleado, salarioMensual, nombreUsuario, contrasenaAcceso, "GERENTE");
        
        this.nivelAcceso = nivelAcceso;
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public Integer getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(Integer aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    @Override
    public String toString() {
        return getNombreUsuario();
    }
    
    
    
}