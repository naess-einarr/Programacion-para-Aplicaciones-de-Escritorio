import java.time.LocalDate;

/**
 * Clase que representa un ejecutivo de cuenta del banco.
 * Hereda de Empleado y añade atributos específicos del rol de ejecutivo.
 */
public class Ejecutivo extends Empleado {
    
    private int numeroClientesAsignados;
    private String especializacionEjecutivo;
    
    public Ejecutivo() {
        super();
    }
    
    public Ejecutivo(String idEmpleado, String nombreCompleto, String direccionCompleta,
                     LocalDate fechaNacimiento, String generoEmpleado, double salarioMensual,
                     String nombreUsuario, String contraseñaAcceso, int numeroClientesAsignados,
                     String especializacionEjecutivo) {
        
        super(idEmpleado, nombreCompleto, direccionCompleta, fechaNacimiento,
              generoEmpleado, salarioMensual, nombreUsuario, contraseñaAcceso, "EJECUTIVO");
        
        this.numeroClientesAsignados = numeroClientesAsignados;
        this.especializacionEjecutivo = especializacionEjecutivo;
    }
    
    public int obtenerNumeroClientesAsignados() {
        return numeroClientesAsignados;
    }
    
    public void establecerNumeroClientesAsignados(int numeroClientesAsignados) {
        this.numeroClientesAsignados = numeroClientesAsignados;
    }
    
    public String obtenerEspecializacionEjecutivo() {
        return especializacionEjecutivo;
    }
    
    public void establecerEspecializacionEjecutivo(String especializacionEjecutivo) {
        this.especializacionEjecutivo = especializacionEjecutivo;
    }
}