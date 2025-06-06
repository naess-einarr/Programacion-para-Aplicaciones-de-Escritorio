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
    private String nombreGerente;
    private String personaContacto;
    private List<String> listaCuentasAsociadas;
    private List<String> listaEmpleadosAsociados;
    
    public Sucursal() {
    }
    
    public Sucursal(String idSucursal, String nombreSucursal, String direccionSucursal,
                    String telefonoSucursal, String correoSucursal, String nombreGerente,
                    String personaContacto) {
        
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.direccionSucursal = direccionSucursal;
        this.telefonoSucursal = telefonoSucursal;
        this.correoSucursal = correoSucursal;
        this.nombreGerente = nombreGerente;
        this.personaContacto = personaContacto;
    }
    
    // Getters y Setters

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

    public String getNombreGerente() {
        return nombreGerente;
    }

    public void setNombreGerente(String nombreGerente) {
        this.nombreGerente = nombreGerente;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public List<String> getListaCuentasAsociadas() {
        return listaCuentasAsociadas;
    }

    public void setListaCuentasAsociadas(List<String> listaCuentasAsociadas) {
        this.listaCuentasAsociadas = listaCuentasAsociadas;
    }

    public List<String> getListaEmpleadosAsociados() {
        return listaEmpleadosAsociados;
    }

    public void setListaEmpleadosAsociados(List<String> listaEmpleadosAsociados) {
        this.listaEmpleadosAsociados = listaEmpleadosAsociados;
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