package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDate;

public class Empleado {
    
    private String idEmpleado;
    private String nombreCompleto;
    private String direccionCompleta;
    private LocalDate fechaNacimiento;
    private String generoEmpleado;
    private Double salarioMensual;
    private String nombreUsuario;
    private String contrasenaAcceso;
    protected String tipoEmpleado;
    
    protected Empleado() {

    }

    public Empleado(String idEmpleado, String nombreCompleto, String direccionCompleta, LocalDate fechaNacimiento, String generoEmpleado, Double salarioMensual, String nombreUsuario, String contrasenaAcceso, String tipoEmpleado) {
        this.idEmpleado = idEmpleado;
        this.nombreCompleto = nombreCompleto;
        this.direccionCompleta = direccionCompleta;
        this.fechaNacimiento = fechaNacimiento;
        this.generoEmpleado = generoEmpleado;
        this.salarioMensual = salarioMensual;
        this.nombreUsuario = nombreUsuario;
        this.contrasenaAcceso = contrasenaAcceso;
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGeneroEmpleado() {
        return generoEmpleado;
    }

    public void setGeneroEmpleado(String generoEmpleado) {
        this.generoEmpleado = generoEmpleado;
    }

    public Double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(Double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenaAcceso() {
        return contrasenaAcceso;
    }

    public void setContrasenaAcceso(String contrasenaAcceso) {
        this.contrasenaAcceso = contrasenaAcceso;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString() {
        return idEmpleado + " - " + nombreCompleto;
    }
    
    
 }