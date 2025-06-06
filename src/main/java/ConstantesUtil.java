
public class ConstantesUtil {
    
    private ConstantesUtil(){
        throw new UnsupportedOperationException();
    }
    
    public static final int LIMITE_MAXIMO_CREDITO = 50000;
    public static final double SALDO_MINIMO_CUENTA = 0.0;
    public static final int LONGITUD_MINIMA_CONTRASENA = 6;
    
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String REGEX_TELEFONO = "^[0-9]{10}$";
    public static final String REGEX_NUMERO_CUENTA = "^ACC[0-9]{6}$";
    public static final String REGEX_RFC = "^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$";
    
    public static final String ALERTA_SALDO_INSUFICIENTE = "El saldo de la cuenta es insuficiente para realizar esta operación";
    public static final String ALERTA_CLIENTE_NO_ENCONTRADO = "No se encontró el cliente especificado en el sistema";
    public static final String ALERTA_TRANSACCION_FALLIDA = "La transacción no pudo completarse correctamente";
    public static final String ALERTA_CREDENCIALES_INVALIDAS = "Usuario o contraseña incorrectos";
    public static final String ALERTA_CAMPOS_VACIOS = "Todos los campos son obligatorios";
    
    // Mensajes para logging
    public static final String LOG_INICIO_SESION = "Usuario ha iniciado sesión: ";
    public static final String LOG_TRANSACCION_EXITOSA = "Transacción completada exitosamente: ";
    public static final String LOG_ERROR_ARCHIVO = "Error al acceder al archivo: ";
    public static final String LOG_CLIENTE_REGISTRADO = "Nuevo cliente registrado: ";
    
    // Constantes genéricas
    public static final String ERROR = "Error";
    public static final String EXITO = "Éxito";
    public static final String CONFIRMACION = "Confirmación";
    public static final String INFORMACION = "Información";
    public static final String ADVERTENCIA = "Advertencia";
}