package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDate;

/**
 * Clase que representa un ejecutivo de cuenta del banco.
 * Hereda de Empleado y anade atributos específicos del rol de ejecutivo.
 */
public class Ejecutivo extends Empleado {
    
    private String especializacionEjecutivo;
    private Integer numeroClientesAsignados;
    
    public Ejecutivo() {
        super();
    }

    public String getEspecializacionEjecutivo() {
        return especializacionEjecutivo;
    }

    public void setEspecializacionEjecutivo(String especializacionEjecutivo) {
        this.especializacionEjecutivo = especializacionEjecutivo;
    }

    public Integer getNumeroClientesAsignados() {
        return numeroClientesAsignados;
    }

    public void setNumeroClientesAsignados(Integer numeroClientesAsignados) {
        this.numeroClientesAsignados = numeroClientesAsignados;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}