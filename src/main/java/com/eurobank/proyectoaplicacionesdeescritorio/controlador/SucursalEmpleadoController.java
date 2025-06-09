package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SucursalEmpleadoController implements Initializable {

    @FXML
    private TableColumn<?, ?> columnaID;

    @FXML
    private TableColumn<?, ?> columnaIDAsociado;

    @FXML
    private TableColumn<?, ?> columnaNombre;

    @FXML
    private TableColumn<?, ?> columnaNombreAsociado;
    
    @FXML
    private TableView<Empleado> tablaEmpleadosAsociados;

    @FXML
    private TableView<Empleado> tablaEmpleadosDisponibles;
    
    private EmpleadoDAO empleadoDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleadoDAO = new EmpleadoDAO();
    }    
    
    @FXML
    void accionAsociarTodos(ActionEvent event) {

    }

    @FXML
    void accionAsociarUno(ActionEvent event) {

    }

    @FXML
    void accionCancelar(ActionEvent event) {

    }

    @FXML
    void accionGuardar(ActionEvent event) {

    }

    @FXML
    void accionRegresarTodos(ActionEvent event) {

    }

    @FXML
    void accionRegresarUno(ActionEvent event) {

    }
   
}
