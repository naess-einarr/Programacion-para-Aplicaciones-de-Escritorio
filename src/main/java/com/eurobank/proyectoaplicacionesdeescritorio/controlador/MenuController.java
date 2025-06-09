package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MenuController implements Initializable {
    
    @FXML
    private Label textNombre;

    @FXML
    private Label textPuesto;

    
    private Empleado empleado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    void abrirVentanaAdministrarClientes(ActionEvent event) {
         ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CLIENTE);
    }

    @FXML
    void abrirVentanaAdministrarCuentasBancarias(ActionEvent event) {

    }

    @FXML
    void abrirVentanaAdministrarEmpleados(ActionEvent event) {
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO);
    }

    @FXML
    void abrirVentanaAdministrarSucursales(ActionEvent event) {

    }

    @FXML
    void abrirVentanaAdministrarTransacciones(ActionEvent event) {
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.TRANSACCION);
    }
    
    @FXML
    void cerrarSesion(MouseEvent event) {
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.LOGIN);
    }
    
    public void cargarDatosMenuEmpleado(){
        textNombre.setText(empleado.getNombreCompleto());
        textPuesto.setText(empleado.getTipoEmpleado());
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
}
