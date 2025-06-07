package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class EmpleadoController implements Initializable {
    
    private EmpleadoDAO empleadoDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleadoDAO = new EmpleadoDAO();
    }    
    
    @FXML
    void accionCancelar(ActionEvent event) {
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.MENU);
    }

    @FXML
    void accionEditar(ActionEvent event) {

    }

    @FXML
    void accionEliminar(ActionEvent event) {

    }

    @FXML
    void accionRegistrar(ActionEvent event) {
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);
    }
    
    private void cargarListaEmpleados(){
        try {
            empleadoDAO.obtenerTodos();
        } catch (Exception ex) {

        }
    }
}
