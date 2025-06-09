package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDate;

public class Cajero extends Empleado {
    
    private String horarioTrabajo;
    private Integer numeroVentanilla;
    
    public Cajero() {
        super();
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

    @Override
    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    @Override
    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    
}