package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cajero;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmpleadoRegistroController implements Initializable{

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
    void guardarRegistro(ActionEvent event) {
        
        Cajero empleado = new Cajero();
        empleado.setIdEmpleado("10");
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
        
        try {
            if(Objects.nonNull(empleadoEditar)){
                empleadoDAO.actualizar(empleado);
            }else{
                empleadoDAO.guardar(empleado);
            }
        } catch (Exception ex) {

        }
        
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO);
    }

    public void setEmpleadoEditar(Empleado empleadoEditar) {
        this.textNombre.setText(empleadoEditar.getNombreCompleto());
        this.textDireccion.setText(empleadoEditar.getDireccionCompleta());
    }
        
}
