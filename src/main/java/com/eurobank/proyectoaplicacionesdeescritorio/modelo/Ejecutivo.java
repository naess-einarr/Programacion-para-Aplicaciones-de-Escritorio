package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

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
    
    @Override
    public String toString() {
        return getIdEmpleado() +" - "+getNombreCompleto();
    }
}