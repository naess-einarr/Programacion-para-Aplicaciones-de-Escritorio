package com.eurobank.proyectoaplicacionesdeescritorio.util;


public class ConstantesUtil {

    
    private ConstantesUtil(){
        throw new UnsupportedOperationException();
    }
    
    public static final int LIMITE_MAXIMO_CREDITO = 50000;
    public static final double SALDO_MINIMO_CUENTA = 0.0;
    public static final int LONGITUD_MINIMA_CONTRASENA = 6;
    public static final int LONGITUD_RFC_MIN = 10;
    public static final int LONGITUD_RFC_MAX = 13;
    public static final int LONGITUD_CURP = 18;
    public static final int LONGITUD_NOMBRE_MIN = 2;
    public static final int LONGITUD_NOMBRE_MAX = 50;
    public static final int LONGITUD_TELEFONO_MIN = 10;
    public static final int LONGITUD_DIRECCION_MIN = 10;
    public static final int LONGITUD_DIRECCION_MAX = 200;

    
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String REGEX_TELEFONO = "^[0-9]{10}$";
    public static final String REGEX_NUMERO_CUENTA = "^ACC[0-9]{6}$";
    public static final String REGEX_RFC = "^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$";
    public static final String REGEX_CURP = "^[A-Z]{4}\\d{6}[A-Z]{6}\\d{2}$$";
    public static final String REGEX_SOLO_LETRAS = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
    public static final String REGEX_LETRAS_Y_NUMEROS = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s.,#-]+$";
    
    public static final String ALERTA_CLASE_UTILERIA = "Clase de utilería...";
    public static final String ALERTA_SALDO_INSUFICIENTE = "El saldo de la cuenta es insuficiente para realizar esta operación";
    public static final String ALERTA_CLIENTE_NO_ENCONTRADO = "No se encontró el cliente especificado en el sistema";
    public static final String ALERTA_TRANSACCION_FALLIDA = "La transacción no pudo completarse correctamente";
    public static final String ALERTA_CREDENCIALES_INVALIDAS = "Usuario o contraseña incorrectos";
    public static final String ALERTA_CAMPOS_VACIOS = "Todos los campos son obligatorios";
    public static final String ALERTA_ACTUALIZACION_FALLIDA = "No se pudo actualizar el registro";
    public static final String ALERTA_ACTUALIZACION_EXITOSA = "El registro se actualizó exitosamente";
    public static final String ALERTA_DATOS_INVALIDOS = "Se ingresaron datos invalidos";

    public static final String LOG_INICIO_SESION = "Usuario ha iniciado sesión: ";
    public static final String LOG_TRANSACCION_EXITOSA = "Transacción completada exitosamente: ";
    public static final String LOG_ERROR_ARCHIVO = "Error al acceder al archivo: ";
    public static final String LOG_CLIENTE_REGISTRADO = "Nuevo cliente registrado";
    public static final String LOG_ACTUALIZACION_FALLIDA = "Falló la actualizacion de un registro";
    
    public static final String ERROR_NOMBRE_OBLIGATORIO = "El nombre es obligatorio";
    public static final String ERROR_NOMBRE_FORMATO = "El nombre solo puede contener letras y espacios";
    public static final String ERROR_NOMBRE_LONGITUD = "El nombre debe tener entre 2 y 50 caracteres";
    public static final String ERROR_APELLIDO_OBLIGATORIO = "El apellido es obligatorio";
    public static final String ERROR_APELLIDO_FORMATO = "El apellido solo puede contener letras y espacios";
    public static final String ERROR_APELLIDO_LONGITUD = "El apellido debe tener entre 2 y 50 caracteres";
    public static final String ERROR_NACIONALIDAD_OBLIGATORIA = "La nacionalidad es obligatoria";
    public static final String ERROR_NACIONALIDAD_FORMATO = "La nacionalidad solo puede contener letras";
    public static final String ERROR_FECHA_OBLIGATORIA = "La fecha de nacimiento es obligatoria";
    public static final String ERROR_FECHA_FUTURA = "La fecha de nacimiento no puede ser futura";
    public static final String ERROR_FECHA_MUY_ANTIGUA = "La fecha de nacimiento no puede ser anterior a 1900";
    public static final String ERROR_RFC_OBLIGATORIO = "El RFC es obligatorio";
    public static final String ERROR_RFC_LONGITUD = "El RFC debe tener entre 10 y 13 caracteres";
    public static final String ERROR_RFC_FORMATO = "El RFC no tiene un formato válido";
    public static final String ERROR_CURP_OBLIGATORIO = "El CURP es obligatorio";
    public static final String ERROR_CURP_LONGITUD = "El CURP debe tener exactamente 18 caracteres";
    public static final String ERROR_CURP_FORMATO = "El CURP no tiene un formato válido";
    public static final String ERROR_DIRECCION_OBLIGATORIA = "La dirección es obligatoria";
    public static final String ERROR_DIRECCION_LONGITUD = "La dirección debe tener entre 10 y 200 caracteres";
    public static final String ERROR_DIRECCION_FORMATO = "La dirección contiene caracteres no válidos";
    public static final String ERROR_TELEFONO_OBLIGATORIO = "El teléfono es obligatorio";
    public static final String ERROR_TELEFONO_LONGITUD = "El teléfono debe tener al menos 10 dígitos";
    public static final String ERROR_TELEFONO_FORMATO = "El teléfono solo puede contener números, espacios, guiones, paréntesis y el símbolo +";
    public static final String ERROR_CORREO_OBLIGATORIO = "El correo electrónico es obligatorio";
    public static final String ERROR_CORREO_FORMATO = "El formato del correo electrónico no es válido";
    public static final String ERROR_CARGAR_INFORMACION = "Error al cargar la informacion";

}