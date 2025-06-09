package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.util.List;

public class Sucursal {
    
    private String idSucursal;
    private String nombreSucursal;
    private String direccionSucursal;
    private String telefonoSucursal;
    private String correoSucursal;
    private Gerente gerente;
    private Ejecutivo contacto;
    private List<Cuenta> cuentasAsociadas;
    private List<Empleado> empleadosAsociados;

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccionSucursal() {
        return direccionSucursal;
    }

    public void setDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }

    public String getTelefonoSucursal() {
        return telefonoSucursal;
    }

    public void setTelefonoSucursal(String telefonoSucursal) {
        this.telefonoSucursal = telefonoSucursal;
    }

    public String getCorreoSucursal() {
        return correoSucursal;
    }

    public void setCorreoSucursal(String correoSucursal) {
        this.correoSucursal = correoSucursal;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public Ejecutivo getContacto() {
        return contacto;
    }

    public void setContacto(Ejecutivo contacto) {
        this.contacto = contacto;
    }

    public List<Cuenta> getCuentasAsociadas() {
        return cuentasAsociadas;
    }

    public void setCuentasAsociadas(List<Cuenta> cuentasAsociadas) {
        this.cuentasAsociadas = cuentasAsociadas;
    }

    public List<Empleado> getEmpleadosAsociados() {
        return empleadosAsociados;
    }

    public void setEmpleadosAsociados(List<Empleado> empleadosAsociados) {
        this.empleadosAsociados = empleadosAsociados;
    }

   
    @Override
    public String toString() {
        return idSucursal + " - " + nombreSucursal;
    }
}