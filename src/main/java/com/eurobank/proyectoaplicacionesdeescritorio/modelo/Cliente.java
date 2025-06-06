
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
    public String obtenerIdCliente() {
        return idCliente;
    }
    
    public void establecerIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    
    public String obtenerNombreCompleto() {
        return nombreCompleto;
    }
    
    public void establecerNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public String obtenerApellidosCompletos() {
        return apellidosCompletos;
    }
    
    public void establecerApellidosCompletos(String apellidosCompletos) {
        this.apellidosCompletos = apellidosCompletos;
    }
    
    public String obtenerNacionalidadCliente() {
        return nacionalidadCliente;
    }
    
    public void establecerNacionalidadCliente(String nacionalidadCliente) {
        this.nacionalidadCliente = nacionalidadCliente;
    }
    
    public LocalDate obtenerFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void establecerFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String obtenerRfcCliente() {
        return rfcCliente;
    }
    
    public void establecerRfcCliente(String rfcCliente) {
        this.rfcCliente = rfcCliente;
    }
    
    public String obtenerCurpCliente() {
        return curpCliente;
    }
    
    public void establecerCurpCliente(String curpCliente) {
        this.curpCliente = curpCliente;
    }
    
    public String obtenerDireccionCompleta() {
        return direccionCompleta;
    }
    
    public void establecerDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }
    
    public String obtenerTelefonoContacto() {
        return telefonoContacto;
    }
    
    public void establecerTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
    
    public String obtenerCorreoElectronico() {
        return correoElectronico;
    }
    
    public void establecerCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    public List<String> obtenerListaCuentasAsociadas() {
        return listaCuentasAsociadas;
    }
    
    public void establecerListaCuentasAsociadas(List<String> listaCuentasAsociadas) {
        this.listaCuentasAsociadas = listaCuentasAsociadas;
    }
    
    public List<String> obtenerListaTarjetasAsociadas() {
        return listaTarjetasAsociadas;
    }
    
    public void establecerListaTarjetasAsociadas(List<String> listaTarjetasAsociadas) {
        this.listaTarjetasAsociadas = listaTarjetasAsociadas;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
               "idCliente='" + idCliente + '\'' +
               ", nombreCompleto='" + nombreCompleto + '\'' +
               ", apellidosCompletos='" + apellidosCompletos + '\'' +
               '}';
    }
}