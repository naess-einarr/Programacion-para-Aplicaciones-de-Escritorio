package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cajero;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Ejecutivo;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.eurobank.proyectoaplicacionesdeescritorio.util.ComboDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EmpleadoRegistroController implements Initializable{
    
    private static final String PREFIJO_EMPLEADO = "EMP";
    
    @FXML
    private TextField textID;
    
    @FXML
    private Label labelColumnaDos;

    @FXML
    private Label labelColumnaUno;
    
    @FXML
    private TextField textColumnaDos;

    @FXML
    private TextField textColumnaUno;
    
    @FXML
    private ComboBox<String> comboGenero;

    @FXML
    private ComboBox<String> comboTipoEmpleado;

    @FXML
    private DatePicker dateFechaNacimiento;

    @FXML
    private TextArea textDireccion;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textSalario;
    
    private EmpleadoDAO empleadoDAO;
    
    private Empleado empleadoEditar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleadoDAO = new EmpleadoDAO();
        comboGenero.setItems(ComboDatosUtil.listaGenero());
        comboTipoEmpleado.setItems(ComboDatosUtil.listaTipoEmpleado());
    }
    
    @FXML
    void cancelarRegistro(ActionEvent event) {
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO);
    }
    
    @FXML
    void cargarSucursal(MouseEvent event) {
            ManejadorDeVistas.getInstancia().abrirVistaEnNuevaVentana(ManejadorDeVistas.Vista.SUCURSAL_SELECCION);
    }

    @FXML
    void guardarRegistro(ActionEvent event) {
        
        try {
            
            Cajero empleado = new Cajero();
            String nuevoId = String.valueOf(empleadoDAO.obtenerSiguienteId());
            empleado.setIdEmpleado(PREFIJO_EMPLEADO.concat(nuevoId));
            empleado.setNombreCompleto(textNombre.getText().trim());
            empleado.setDireccionCompleta(textDireccion.getText().trim());
            empleado.setFechaNacimiento(dateFechaNacimiento.getValue());
            empleado.setGeneroEmpleado(comboGenero.getValue());
            empleado.setSalarioMensual(Double.parseDouble(textSalario.getText().trim()));
            empleado.setTipoEmpleado(comboTipoEmpleado.getValue());
            empleado.setHorarioTrabajo("horas");
            empleado.setNumeroVentanilla(2);
            empleado.setIdSucursal("suc01");
            
            empleado.setNombreUsuario("empleadon");
            empleado.setContrasenaAcceso("empleadon");
            
                if(Objects.nonNull(empleadoEditar)){
                    empleadoDAO.actualizar(empleado);
                }else{
                    empleadoDAO.guardar(empleado);
                }
            
            ManejadorDeVistas.getInstancia().limpiarCache();
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO);
        } catch (Exception ex) {

        }
    }

    public void setEmpleadoEditar(Empleado empleadoEditar) {
        this.textID.setText(empleadoEditar.getIdEmpleado());
        this.textNombre.setText(empleadoEditar.getNombreCompleto());
        this.textDireccion.setText(empleadoEditar.getDireccionCompleta());
        this.dateFechaNacimiento.setValue(empleadoEditar.getFechaNacimiento());
        this.comboGenero.getSelectionModel().select(empleadoEditar.getGeneroEmpleado());
        this.textSalario.setText(Double.toString(empleadoEditar.getSalarioMensual()));
        this.comboTipoEmpleado.getSelectionModel().select(empleadoEditar.getTipoEmpleado());
        
        String columnaUno = null;
        String columnaDos = null;
        String labelUno=  null;
        String labelDos = null;
        
        if(empleadoEditar instanceof Cajero){
            columnaUno = ((Cajero) empleadoEditar).getHorarioTrabajo();
            columnaDos = ((Cajero) empleadoEditar).getNumeroVentanilla().toString();
            labelUno = "Horario de trabajo";
            labelDos = "Numéro de ventanilla";
        }else if(empleadoEditar instanceof Ejecutivo){
            columnaUno = ((Ejecutivo) empleadoEditar).getEspecializacionEjecutivo();
            columnaDos = ((Ejecutivo) empleadoEditar).getNumeroClientesAsignados().toString();
            labelUno = "Especialización";
            labelDos = "Clientes asignados";
        }else if(empleadoEditar instanceof Gerente){
            columnaUno = ((Gerente) empleadoEditar).getNivelAcceso();
            columnaDos = ((Gerente) empleadoEditar).getAniosExperiencia().toString();
            labelUno = "Nivel de acceso";
            labelDos = "Años de experiencia";
        }
        this.textColumnaUno.setText(columnaUno);
        this.textColumnaDos.setText(columnaDos);
        this.labelColumnaUno.setText(labelUno);
        this.labelColumnaDos.setText(labelDos);
    }
    
        
}
