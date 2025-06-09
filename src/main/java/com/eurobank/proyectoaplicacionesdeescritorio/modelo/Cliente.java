
package com.eurobank.proyectoaplicacionesdeescritorio.modelo;

import java.time.LocalDate;
import java.util.List;

public class Cliente {
    
    private String idCliente;
    private String nombreCompleto;
    private String apellidosCompletos;
    private String nacionalidadCliente;
    private LocalDate fechaNacimiento;
    private String rfcCliente;
    private String curpCliente;
    private String direccionCompleta;
    private String telefonoContacto;
    private String correoElectronico;
    private List<String> listaCuentasAsociadas;
    private List<String> listaTarjetasAsociadas;
    
    public Cliente() {
    }
    
    public Cliente(String idCliente, String nombreCompleto, String apellidosCompletos,
                   String nacionalidadCliente, LocalDate fechaNacimiento, String rfcCliente,
                   String curpCliente, String direccionCompleta, String telefonoContacto,
                   String correoElectronico) {
        
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.apellidosCompletos = apellidosCompletos;
        this.nacionalidadCliente = nacionalidadCliente;
        this.fechaNacimiento = fechaNacimiento;
        this.rfcCliente = rfcCliente;
        this.curpCliente = curpCliente;
        this.direccionCompleta = direccionCompleta;
        this.telefonoContacto = telefonoContacto;
        this.correoElectronico = correoElectronico;
    }
    
    // Getters y Setters

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getApellidosCompletos() {
        return apellidosCompletos;
    }

    public void setApellidosCompletos(String apellidosCompletos) {
        this.apellidosCompletos = apellidosCompletos;
    }

    public String getNacionalidadCliente() {
        return nacionalidadCliente;
    }

    public void setNacionalidadCliente(String nacionalidadCliente) {
        this.nacionalidadCliente = nacionalidadCliente;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRfcCliente() {
        return rfcCliente;
    }

    public void setRfcCliente(String rfcCliente) {
        this.rfcCliente = rfcCliente;
    }

    public String getCurpCliente() {
        return curpCliente;
    }

    public void setCurpCliente(String curpCliente) {
        this.curpCliente = curpCliente;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<String> getListaCuentasAsociadas() {
        return listaCuentasAsociadas;
    }

    public void setListaCuentasAsociadas(List<String> listaCuentasAsociadas) {
        this.listaCuentasAsociadas = listaCuentasAsociadas;
    }

    public List<String> getListaTarjetasAsociadas() {
        return listaTarjetasAsociadas;
    }

    public void setListaTarjetasAsociadas(List<String> listaTarjetasAsociadas) {
        this.listaTarjetasAsociadas = listaTarjetasAsociadas;
    }
    
    
    @Override
    public String toString() {
        return idCliente +" - "+ nombreCompleto + " " + apellidosCompletos;
    }
}