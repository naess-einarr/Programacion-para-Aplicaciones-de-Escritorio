import java.time.LocalDate;

/**
 * Clase que representa un gerente del banco.
 * Hereda de Empleado y añade atributos específicos del rol de gerente.
 */
public class Gerente extends Empleado {
    
    private String nivelAcceso;
    private int añosExperiencia;
    
    public Gerente() {
        super();
    }
    
    public Gerente(String idEmpleado, String nombreCompleto, String direccionCompleta,
                   LocalDate fechaNacimiento, String generoEmpleado, double salarioMensual,
                   String nombreUsuario, String contraseñaAcceso, String nivelAcceso,
                   int añosExperiencia) {
        
        super(idEmpleado, nombreCompleto, direccionCompleta, fechaNacimiento,
              generoEmpleado, salarioMensual, nombreUsuario, contraseñaAcceso, "GERENTE");
        
        this.nivelAcceso = nivelAcceso;
        this.añosExperiencia = añosExperiencia;
    }
    
    public String obtenerNivelAcceso() {
        return nivelAcceso;
    }
    
    public void establecerNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
    
    public int obtenerAñosExperiencia() {
        return añosExperiencia;
    }
    
    public void establecerAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }
}