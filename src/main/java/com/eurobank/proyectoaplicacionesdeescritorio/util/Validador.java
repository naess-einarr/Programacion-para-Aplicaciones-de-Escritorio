package com.eurobank.proyectoaplicacionesdeescritorio.util;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cliente;
import java.time.LocalDate;

/**
 * Clase utilitaria para validar datos de diferentes entidades del sistema.
 * Contiene métodos estáticos para validación de campos y objetos completos.
 */
public class Validador {

    private Validador() {
        throw new UnsupportedOperationException("Clase de utilería");
    }

    /**
     * Valida el nombre de una persona.
     * @param nombre Nombre a validar
     * @throws IllegalArgumentException si el nombre no es válido
     */
    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_NOMBRE_OBLIGATORIO);
        }

        String nombreLimpio = nombre.trim();

        if (nombreLimpio.length() < ConstantesUtil.LONGITUD_NOMBRE_MIN || nombreLimpio.length() > ConstantesUtil.LONGITUD_NOMBRE_MAX) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_NOMBRE_LONGITUD);
        }

        if (!nombreLimpio.matches(ConstantesUtil.REGEX_SOLO_LETRAS)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_NOMBRE_FORMATO);
        }
    }

    /**
     * Valida el apellido de una persona.
     * @param apellido Apellido a validar
     * @throws IllegalArgumentException si el apellido no es válido
     */
    public static void validarApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_APELLIDO_OBLIGATORIO);
        }

        String apellidoLimpio = apellido.trim();

        if (apellidoLimpio.length() < ConstantesUtil.LONGITUD_NOMBRE_MIN || apellidoLimpio.length() > ConstantesUtil.LONGITUD_NOMBRE_MAX) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_APELLIDO_LONGITUD);
        }

        if (!apellidoLimpio.matches(ConstantesUtil.REGEX_SOLO_LETRAS)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_APELLIDO_FORMATO);
        }
    }

    /**
     * Valida la nacionalidad de una persona.
     * @param nacionalidad Nacionalidad a validar
     * @throws IllegalArgumentException si la nacionalidad no es válida
     */
    public static void validarNacionalidad(String nacionalidad) {
        if (nacionalidad == null || nacionalidad.trim().isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_NACIONALIDAD_OBLIGATORIA);
        }

        if (!nacionalidad.trim().matches(ConstantesUtil.REGEX_SOLO_LETRAS)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_NACIONALIDAD_FORMATO);
        }
    }

    /**
     * Valida la fecha de nacimiento.
     * @param fechaNacimiento Fecha a validar
     * @throws IllegalArgumentException si la fecha no es válida
     */
    public static void validarFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_FECHA_OBLIGATORIA);
        }

        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_FECHA_FUTURA);
        }

        if (fechaNacimiento.isBefore(LocalDate.of(1900, 1, 1))) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_FECHA_MUY_ANTIGUA);
        }
    }

    /**
     * Valida el RFC de una persona física.
     * @param rfc RFC a validar
     * @throws IllegalArgumentException si el RFC no es válido
     */
    public static void validarRfc(String rfc) {
        if (rfc == null || rfc.trim().isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_RFC_OBLIGATORIO);
        }

        String rfcLimpio = rfc.trim().toUpperCase();

        if (rfcLimpio.length() < ConstantesUtil.LONGITUD_RFC_MIN || rfcLimpio.length() > ConstantesUtil.LONGITUD_RFC_MAX) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_RFC_LONGITUD);
        }

        // Validación básica de formato para persona física (4 letras + 6 números + 3 caracteres)
        if (rfcLimpio.length() == 13 && !rfcLimpio.matches(ConstantesUtil.REGEX_RFC)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_RFC_FORMATO);
        }
    }

    /**
     * Valida el CURP.
     * @param curp CURP a validar
     * @throws IllegalArgumentException si el CURP no es válido
     */
    public static void validarCurp(String curp) {
        if (curp == null || curp.trim().isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_CURP_OBLIGATORIO);
        }

        String curpLimpio = curp.trim().toUpperCase();

        if (curpLimpio.length() != ConstantesUtil.LONGITUD_CURP) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_CURP_LONGITUD);
        }

        if (!curpLimpio.matches(ConstantesUtil.REGEX_CURP)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_CURP_FORMATO);
        }
    }

    /**
     * Valida la dirección.
     * @param direccion Dirección a validar
     * @throws IllegalArgumentException si la dirección no es válida
     */
    public static void validarDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_DIRECCION_OBLIGATORIA);
        }

        String direccionLimpia = direccion.trim();

        if (direccionLimpia.length() < ConstantesUtil.LONGITUD_DIRECCION_MIN || direccionLimpia.length() > ConstantesUtil.LONGITUD_DIRECCION_MAX) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_DIRECCION_LONGITUD);
        }

        if (!direccionLimpia.matches(ConstantesUtil.REGEX_LETRAS_Y_NUMEROS)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_DIRECCION_FORMATO);
        }
    }

    /**
     * Valida el número de teléfono.
     * @param telefono Teléfono a validar
     * @throws IllegalArgumentException si el teléfono no es válido
     */
    public static void validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_TELEFONO_OBLIGATORIO);
        }

        String telefonoLimpio = telefono.trim();

        // Contar solo los dígitos para validar longitud mínima
        long cantidadDigitos = telefonoLimpio.chars()
                .filter(Character::isDigit)
                .count();

        if (cantidadDigitos < ConstantesUtil.LONGITUD_TELEFONO_MIN) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_TELEFONO_LONGITUD);
        }

        if (!telefonoLimpio.matches(ConstantesUtil.REGEX_TELEFONO)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_TELEFONO_FORMATO);
        }
    }

    public static void validarCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_CORREO_OBLIGATORIO);
        }

        if (!correo.trim().matches(ConstantesUtil.REGEX_EMAIL)) {
            throw new IllegalArgumentException(ConstantesUtil.ERROR_CORREO_FORMATO);
        }
    }

    /**
     * Valida todos los campos de un cliente.
     * @param cliente Cliente a validar
     * @throws IllegalArgumentException si algún campo no es válido
     */
    public static void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
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
