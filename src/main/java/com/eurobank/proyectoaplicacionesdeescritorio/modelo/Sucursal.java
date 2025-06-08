package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.util.List;

/**
 * Clase que representa una sucursal bancaria.
 * Contiene información de identificación y contacto de la sucursal.
 */
public class Sucursal {
    
    private String idSucursal;
    private String nombreSucursal;
    private String direccionSucursal;
    private String telefonoSucursal;
    private String correoSucursal;
    private Gerente gerente;
    private String contacto;
    private List<String> cuentasAsociadas;
    private List<String> empleadosAsociados;
    
    public Sucursal() {
    }
    
    public Sucursal(String idSucursal, String nombreSucursal, String direccionSucursal,
                    String telefonoSucursal, String correoSucursal, Gerente gerente,
                    String personaContacto) {
        
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.direccionSucursal = direccionSucursal;
        this.telefonoSucursal = telefonoSucursal;
        this.correoSucursal = correoSucursal;
        this.gerente = gerente;
        this.contacto = personaContacto;
    }

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

    public void setGerente(Gerente idGerente) {
        this.gerente = idGerente;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public List<String> getListaCuentasAsociadas() {
        return cuentasAsociadas;
    }

    public void setListaCuentasAsociadas(List<String> listaCuentasAsociadas) {
        this.cuentasAsociadas = listaCuentasAsociadas;
    }

    public List<String> getListaEmpleadosAsociados() {
        return empleadosAsociados;
    }

    public void setListaEmpleadosAsociados(List<String> listaEmpleadosAsociados) {
        this.empleadosAsociados = listaEmpleadosAsociados;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
               "idSucursal='" + idSucursal + '\'' +
               ", nombreSucursal='" + nombreSucursal + '\'' +
               ", direccionSucursal='" + direccionSucursal + '\'' +
               '}';
    }
}