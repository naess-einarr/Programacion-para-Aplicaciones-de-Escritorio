import java.time.LocalDate;

/**
 * Clase que representa un cajero del banco.
 * Hereda de Empleado y añade atributos específicos del rol de cajero.
 */
public class Cajero extends Empleado {
    
    private String horarioTrabajo;
    private int numeroVentanilla;
    
    public Cajero() {
        super();
    }
    
    public Cajero(String idEmpleado, String nombreCompleto, String direccionCompleta,
                  LocalDate fechaNacimiento, String generoEmpleado, double salarioMensual,
                  String nombreUsuario, String contraseñaAcceso, String horarioTrabajo, 
                  int numeroVentanilla) {
        
        super(idEmpleado, nombreCompleto, direccionCompleta, fechaNacimiento, 
              generoEmpleado, salarioMensual, nombreUsuario, contraseñaAcceso, "CAJERO");
        
        this.horarioTrabajo = horarioTrabajo;
        this.numeroVentanilla = numeroVentanilla;
    }
    
    public String obtenerHorarioTrabajo() {
        return horarioTrabajo;
    }
    
    public void establecerHorarioTrabajo(String horarioTrabajo) {
        this.horarioTrabajo = horarioTrabajo;
    }
    
    public int obtenerNumeroVentanilla() {
        return numeroVentanilla;
    }
    
    public void establecerNumeroVentanilla(int numeroVentanilla) {
        this.numeroVentanilla = numeroVentanilla;
    }
}