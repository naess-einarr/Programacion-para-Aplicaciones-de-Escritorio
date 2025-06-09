package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

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