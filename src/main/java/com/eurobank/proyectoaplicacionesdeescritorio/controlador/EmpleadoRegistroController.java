package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cajero;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmpleadoRegistroController implements Initializable{

    @FXML
    private ComboBox<?> comboGenero;

    @FXML
    private ComboBox<?> comboTipoEmpleado;

    @FXML
    private DatePicker dateFechaNacimiento;

    @FXML
    private TextArea textDireccion;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textSalario;
    
    private EmpleadoDAO empleadoDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleadoDAO = new EmpleadoDAO();
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
        try {
            empleadoDAO.guardar(empleado);
        } catch (Exception ex) {

        }
        
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO);
    }

}
