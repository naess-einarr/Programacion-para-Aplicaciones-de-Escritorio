package com.eurobank.proyectoaplicacionesdeescritorio.util;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cajero;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cliente;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Ejecutivo;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import java.time.LocalDate;
import java.time.Period;

public class Validador {

    private Validador() {
        throw new UnsupportedOperationException("Clase de utilería");
    }

    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_NOMBRE_OBLIGATORIO);
        }

        if (nombre.length() < ConstantesUtil.LONGITUD_NOMBRE_MIN || nombre.length() > ConstantesUtil.LONGITUD_NOMBRE_MAX) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_NOMBRE_LONGITUD);
        }

        if (!nombre.matches(ConstantesUtil.REGEX_SOLO_LETRAS)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_NOMBRE_FORMATO);
        }
    }

    public static void validarApellido(String apellido) {
        if (apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_APELLIDO_OBLIGATORIO);
        }

        if (apellido.length() < ConstantesUtil.LONGITUD_NOMBRE_MIN || apellido.length() > ConstantesUtil.LONGITUD_NOMBRE_MAX) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_APELLIDO_LONGITUD);
        }

        if (!apellido.matches(ConstantesUtil.REGEX_SOLO_LETRAS)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_APELLIDO_FORMATO);
        }
    }

    public static void validarNacionalidad(String nacionalidad) {
        if (nacionalidad == null || nacionalidad.isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_NACIONALIDAD_OBLIGATORIA);
        }

        if (!nacionalidad.trim().matches(ConstantesUtil.REGEX_SOLO_LETRAS)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_NACIONALIDAD_FORMATO);
        }
    }

    public static void validarFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_FECHA_OBLIGATORIA);
        }

        if (Period.between(fechaNacimiento, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_MENOR_DE_EDAD);
        }

        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_FECHA_FUTURA);
        }

        if (fechaNacimiento.isBefore(LocalDate.of(1900, 1, 1))) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_FECHA_MUY_ANTIGUA);
        }
    }

    public static void validarRfc(String rfc) {
        if (rfc == null || rfc.isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_RFC_OBLIGATORIO);
        }

        if (rfc.length() != ConstantesUtil.LONGITUD_RFC) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_RFC_LONGITUD);
        }

        if (!rfc.matches(ConstantesUtil.REGEX_RFC)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_RFC_FORMATO);
        }
    }

    public static void validarCurp(String curp) {
        if (curp == null || curp.isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_CURP_OBLIGATORIO);
        }

        if (curp.length() != ConstantesUtil.LONGITUD_CURP) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_CURP_LONGITUD);
        }

        if (!curp.matches(ConstantesUtil.REGEX_CURP)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_CURP_FORMATO);
        }
    }

    private static void validarDireccion(String direccion) {
        if (direccion == null || direccion.isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_DIRECCION_OBLIGATORIA);
        }

        if (direccion.length() < ConstantesUtil.LONGITUD_DIRECCION_MIN || direccion.length() > ConstantesUtil.LONGITUD_DIRECCION_MAX) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_DIRECCION_LONGITUD);
        }

        if (!direccion.matches(ConstantesUtil.REGEX_LETRAS_Y_NUMEROS)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_DIRECCION_FORMATO);
        }
    }

    private static void validarTelefono(String telefono) {
        if (telefono == null || telefono.isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_TELEFONO_OBLIGATORIO);
        }

        if (telefono.length() < ConstantesUtil.LONGITUD_TELEFONO_MIN) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_TELEFONO_LONGITUD);
        }

        if (!telefono.matches(ConstantesUtil.REGEX_TELEFONO)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_TELEFONO_FORMATO);
        }
    }

    private static void validarCorreo(String correo) {
        if (correo == null || correo.isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_CORREO_OBLIGATORIO);
        }

        if (!correo.matches(ConstantesUtil.REGEX_EMAIL)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_CORREO_FORMATO);
        }
    }

    public static void validarIdEmpleado(String idEmpleado) {
        if (idEmpleado == null || idEmpleado.isEmpty()) {
            throw new IllegalArgumentException("El ID del empleado es obligatorio");
        }
    }

    public static void validarGeneroEmpleado(String genero) {
        if (genero == null || genero.isEmpty()) {
            throw new IllegalArgumentException("El género es obligatorio");
        }
    }

    public static void validarSalarioMensual(Double salario) {

        if (salario == null) {
            throw new IllegalArgumentException("El salario mensual es obligatorio");
        }

        if (salario <= 8300) {
            throw new IllegalArgumentException("El salario debe ser el minimo al menos, no seas explotador");
        }

    }

    public static void validarTipoEmpleado(String tipoEmpleado) {
        if (tipoEmpleado == null || tipoEmpleado.isEmpty()) {
            throw new IllegalArgumentException("El tipo de empleado es obligatorio");
        }
    }

    private static void validarEspecializacion(String especializacion) {
        if (especializacion == null || especializacion.isEmpty()){
            throw new IllegalArgumentException ("La especializacion es obligatoria");
        }
        
        
    }
    
    private static void validarAniosExperiencia(Integer anios){
        
        if (anios == null){
            throw new IllegalArgumentException("Los años de experiencia son obligatorios");
        }
        
        if (anios < 0){
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos");
        }
    }
    
    private static void validarNivelAcceso(String nivelAcceso){
        if (nivelAcceso == null|| nivelAcceso.isEmpty() ){
            throw new IllegalArgumentException("El nivel de Acceso es obligatorio");
        }
        
    }
    
    private static void validarHorario(String horario){
        if (horario == null || horario.isEmpty()){
            throw new IllegalArgumentException("El horario es obligatorio");
        }
    }
    
    private static void validarVentanilla (int ventanilla){
        if (ventanilla < 1){
            throw new IllegalArgumentException("No puede haber ventanillas negativas");
        }
        
    }
    

    public static void validarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        validarIdEmpleado(empleado.getIdEmpleado());
        validarNombre(empleado.getNombreCompleto());
        validarDireccion(empleado.getDireccionCompleta());
        validarFechaNacimiento(empleado.getFechaNacimiento());
        validarGeneroEmpleado(empleado.getGeneroEmpleado());
        validarSalarioMensual(empleado.getSalarioMensual());
        validarTipoEmpleado(empleado.getTipoEmpleado());

        if (empleado instanceof Cajero) {
            validarHorario(((Cajero) empleado).getHorarioTrabajo());
            validarVentanilla(((Cajero) empleado).getNumeroVentanilla());
        } else if (empleado instanceof Ejecutivo) {
            validarEspecializacion(((Ejecutivo) empleado).getEspecializacionEjecutivo());
        } else if (empleado instanceof Gerente) {
            validarNivelAcceso(((Gerente) empleado).getNivelAcceso());
            validarAniosExperiencia(((Gerente) empleado).getAniosExperiencia()); 
        }

    }

    public static void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        validarNombre(cliente.getNombreCompleto());
        validarApellido(cliente.getApellidosCompletos());
        validarNacionalidad(cliente.getNacionalidadCliente());
        validarFechaNacimiento(cliente.getFechaNacimiento());
        validarRfc(cliente.getRfcCliente());
        validarCurp(cliente.getCurpCliente());
        validarDireccion(cliente.getDireccionCompleta());
        validarTelefono(cliente.getTelefonoContacto());
        validarCorreo(cliente.getCorreoElectronico());
    }

}
