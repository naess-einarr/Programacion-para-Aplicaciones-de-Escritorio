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

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString() {
        return getIdEmpleado() +" - "+getNombreCompleto();
    }
    
    
    
}