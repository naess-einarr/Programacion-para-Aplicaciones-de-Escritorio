package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void abrirVentanaAdministrarClientes(ActionEvent event) {

    }

    @FXML
    void abrirVentanaAdministrarCuentasBancarias(ActionEvent event) {

    }

    @FXML
    void abrirVentanaAdministrarEmpleados(ActionEvent event) {
        ManejadorDeVistas.obtenerInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO);
    }

    @FXML
    void abrirVentanaAdministrarSucursales(ActionEvent event) {

    }

    @FXML
    void abrirVentanaAdministrarTransacciones(ActionEvent event) {

    }
    
    @FXML
    void cerrarSesion(MouseEvent event) {
        ManejadorDeVistas.obtenerInstancia().limpiarCache();
        ManejadorDeVistas.obtenerInstancia().cambiarVista(ManejadorDeVistas.Vista.LOGIN);
    }
    
}
