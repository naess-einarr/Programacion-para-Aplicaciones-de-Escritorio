package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cajero;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Ejecutivo;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.EmpleadoDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmpleadoRegistroController implements Initializable{
    private static final Logger LOG = LogManager.getLogger(EmpleadoRegistroController.class);

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
    private ComboBox<String> comboColumnaUno;
    @FXML
    private ComboBox<String> comboGenero;
    @FXML
    private TextField textTipoDeEmpleado;
    @FXML
    private DatePicker dateFechaNacimiento;
    @FXML
    private TextArea textDireccion;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textSalario;
    @FXML
    private ComboBox<Integer> comboColumnaDos;
    
    private EmpleadoDAO empleadoDAO;
    private boolean modoEdicion;
    private String tipoEmpleado;
    private Empleado empleadoEditar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleadoDAO = new EmpleadoDAO();
        cargarComboGenero();
        llenarCampoIdEmpleado();
    }
    
    @FXML
    void cancelarRegistro(ActionEvent event) {
        if(AlertaUtil.mostrarAlertaCancelarGuardado()){
           ManejadorDeVistas.getInstancia().limpiarCache();
           ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO);
        }
    }

    @FXML
    void guardarRegistro(ActionEvent event) {
        try {
                Empleado empleado = llenarObjetoEmpleado();

                if(modoEdicion){
                    empleadoDAO.actualizar(empleado);
                }else{
                    empleadoDAO.guardar(empleado);
                }
            
            ManejadorDeVistas.getInstancia().limpiarCache();
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO);
        } catch (Exception ex) {
                LOG.error(ex);
        }
    }
    
    private void cargarComboGenero(){
        comboGenero.setItems(EmpleadoDatosUtil.listaGenero());
    }

    public void cargarObjetosPersonalizados() {
        switch (tipoEmpleado) {
            case EmpleadoDatosUtil.TIPO_GERENTE:
                comboColumnaUno.setItems(EmpleadoDatosUtil.listaNivelAcceso());
                comboColumnaDos.setVisible(Boolean.FALSE);
                break;
            case EmpleadoDatosUtil.TIPO_EJECUTIVO:
                comboColumnaUno.setItems(EmpleadoDatosUtil.listaEspecializacion());
                comboColumnaDos.setVisible(Boolean.FALSE);
                if(!modoEdicion){
                    labelColumnaDos.setVisible(Boolean.FALSE);
                    textColumnaDos.setVisible(Boolean.FALSE);
                }
                break;
            case EmpleadoDatosUtil.TIPO_CAJERO:
                comboColumnaUno.setItems(EmpleadoDatosUtil.listaHorarioDeTrabajo());
                comboColumnaDos.setItems(EmpleadoDatosUtil.listaVentanilla());
                textColumnaDos.setVisible(Boolean.FALSE);
                break;
            default:
                break;
        }
    }
    
    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }  
    
    private Empleado llenarObjetoEmpleado() throws Exception{
        Empleado empleado = new Empleado() {};

        switch (tipoEmpleado) {
            case EmpleadoDatosUtil.TIPO_GERENTE:
                empleado = new Gerente();
                break;
            case EmpleadoDatosUtil.TIPO_EJECUTIVO:
                empleado = new Ejecutivo();
                break;
            case EmpleadoDatosUtil.TIPO_CAJERO:
                empleado = new Cajero();
                break;
            default:
                break;
        }

        empleado.setIdEmpleado(textID.getText().trim());
        empleado.setNombreCompleto(textNombre.getText().trim());
        empleado.setDireccionCompleta(textDireccion.getText().trim());
        empleado.setFechaNacimiento(dateFechaNacimiento.getValue());
        empleado.setGeneroEmpleado(comboGenero.getValue());
        empleado.setSalarioMensual(Double.parseDouble(textSalario.getText().trim()));
        empleado.setTipoEmpleado(textTipoDeEmpleado.getText());
        empleado.setNombreUsuario(textID.getText().trim());

        if (!modoEdicion) {
            String contrasenaGenerica = EmpleadoDatosUtil.generaContrasenaGenerica(empleado.getNombreCompleto(), empleado.getFechaNacimiento());
            empleado.setContrasenaAcceso(contrasenaGenerica);
        } else {
            empleado.setContrasenaAcceso(empleadoEditar.getContrasenaAcceso());
        }

        if (empleado instanceof Cajero) {
            ((Cajero) empleado).setHorarioTrabajo(comboColumnaUno.getSelectionModel().getSelectedItem());
            ((Cajero) empleado).setNumeroVentanilla(comboColumnaDos.getSelectionModel().getSelectedItem());
        }else if (!modoEdicion && empleado instanceof Ejecutivo) {
            ((Ejecutivo) empleado).setEspecializacionEjecutivo(comboColumnaUno.getSelectionModel().getSelectedItem());
            ((Ejecutivo) empleado).setNumeroClientesAsignados(NumberUtils.INTEGER_ZERO);
        }else if (modoEdicion && empleado instanceof Ejecutivo) {
            ((Ejecutivo) empleado).setEspecializacionEjecutivo(comboColumnaUno.getSelectionModel().getSelectedItem());
            ((Ejecutivo) empleado).setNumeroClientesAsignados(((Ejecutivo)empleadoEditar).getNumeroClientesAsignados());
        } else if (empleado instanceof Gerente) {
            ((Gerente) empleado).setNivelAcceso(comboColumnaUno.getSelectionModel().getSelectedItem());
            ((Gerente) empleado).setAniosExperiencia(Integer.valueOf(textColumnaDos.getText().trim()));
        }
        return empleado;
    }

    public void llenarCamposEdicion(Empleado empleadoEditar) {
        modoEdicion = Boolean.TRUE;
        this.empleadoEditar = empleadoEditar;
        this.textID.setText(empleadoEditar.getIdEmpleado());
        this.textNombre.setText(empleadoEditar.getNombreCompleto());
        this.textDireccion.setText(empleadoEditar.getDireccionCompleta());
        this.dateFechaNacimiento.setValue(empleadoEditar.getFechaNacimiento());
        this.comboGenero.getSelectionModel().select(empleadoEditar.getGeneroEmpleado());
        this.textSalario.setText(Double.toString(empleadoEditar.getSalarioMensual()));
        this.textTipoDeEmpleado.setText(empleadoEditar.getTipoEmpleado());
        
        String columnaUno = null;
        String columnaDos = null;
        
        if(empleadoEditar instanceof Cajero){
            columnaUno = ((Cajero) empleadoEditar).getHorarioTrabajo();
            comboColumnaDos.getSelectionModel().select(((Cajero) empleadoEditar).getNumeroVentanilla());
        }else if(empleadoEditar instanceof Ejecutivo){
            columnaUno = ((Ejecutivo) empleadoEditar).getEspecializacionEjecutivo();
            columnaDos = ((Ejecutivo) empleadoEditar).getNumeroClientesAsignados().toString();
        }else if(empleadoEditar instanceof Gerente){
            columnaUno = ((Gerente) empleadoEditar).getNivelAcceso();
            columnaDos = ((Gerente) empleadoEditar).getAniosExperiencia().toString();
        }
        this.comboColumnaUno.getSelectionModel().select(columnaUno);
        this.textColumnaDos.setText(columnaDos);

    }
    
    public void configurarLabelDinamicos() {
        String labelUno = "";
        String labelDos = "";
        switch (tipoEmpleado) {
            case EmpleadoDatosUtil.TIPO_GERENTE:
                labelUno = "Nivel de acceso";
                labelDos = "Años de experiencia";
                break;
            case EmpleadoDatosUtil.TIPO_EJECUTIVO:
                labelUno = "Especialización";
                labelDos = "Clientes asignados";
                break;
            case EmpleadoDatosUtil.TIPO_CAJERO:
                labelUno = "Horario de trabajo";
                labelDos = "Numéro de ventanilla";
                break;
            default:
                break;
        }
        this.labelColumnaUno.setText(labelUno);
        comboColumnaUno.setPromptText("Seleccione "+labelUno.toLowerCase());
        this.labelColumnaDos.setText(labelDos);
        textTipoDeEmpleado.setText(tipoEmpleado);
    }
    
    private void llenarCampoIdEmpleado(){
        if(!modoEdicion){
            String nuevoId;
            try {
                nuevoId = PREFIJO_EMPLEADO.concat(String.valueOf(empleadoDAO.obtenerSiguienteId()));
                textID.setText(nuevoId);
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }
    }
        
}