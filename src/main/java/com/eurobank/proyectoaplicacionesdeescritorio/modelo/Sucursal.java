package com.eurobank.model;

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
    public String obtenerIdSucursal() {
        return idSucursal;
    }
    
    public void establecerIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    public String obtenerNombreSucursal() {
        return nombreSucursal;
    }
    
    public void establecerNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }
    
    public String obtenerDireccionSucursal() {
        return direccionSucursal;
    }
    
    public void establecerDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }
    
    public String obtenerTelefonoSucursal() {
        return telefonoSucursal;
    }
    
    public void establecerTelefonoSucursal(String telefonoSucursal) {
        this.telefonoSucursal = telefonoSucursal;
    }
    
    public String obtenerCorreoSucursal() {
        return correoSucursal;
    }
    
    public void establecerCorreoSucursal(String correoSucursal) {
        this.correoSucursal = correoSucursal;
    }
    
    public String obtenerNombreGerente() {
        return nombreGerente;
    }
    
    public void establecerNombreGerente(String nombreGerente) {
        this.nombreGerente = nombreGerente;
    }
    
    public String obtenerPersonaContacto() {
        return personaContacto;
    }
    
    public void establecerPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }
    
    public List<String> obtenerListaCuentasAsociadas() {
        return listaCuentasAsociadas;
    }
    
    public void establecerListaCuentasAsociadas(List<String> listaCuentasAsociadas) {
        this.listaCuentasAsociadas = listaCuentasAsociadas;
    }
    
    public List<String> obtenerListaEmpleadosAsociados() {
        return listaEmpleadosAsociados;
    }
    
    public void establecerListaEmpleadosAsociados(List<String> listaEmpleadosAsociados) {
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