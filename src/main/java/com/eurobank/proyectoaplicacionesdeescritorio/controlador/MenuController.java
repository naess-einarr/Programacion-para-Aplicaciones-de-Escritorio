package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import com.eurobank.proyectoaplicacionesdeescritorio.util.EmpleadoDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeSesion;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MenuController implements Initializable {
    @FXML
    private Button botonAdministrarClientes;

    @FXML
    private Button botonAdministrarCuentasBancarias;

    @FXML
    private Button botonAdministrarEmpleados;

    @FXML
    private Button botonAdministrarSucursales;

    @FXML
    private Button botonAdministrarTransacciones;

    @FXML
    private Label textNombre;
    @FXML
    private Label textPuesto;
    @FXML
    private Label textSucursal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(ManejadorDeSesion.haySesionActiva()){
            cargarDatosMenuEmpleado();
            ajustarMenuPerfil();
        }
    }
    
    @FXML
    void abrirVentanaAdministrarClientes(ActionEvent event) {
         ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CLIENTE);
    }

    @FXML
    void abrirVentanaAdministrarCuentasBancarias(ActionEvent event) {
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CUENTA);
    }

    @FXML
    void abrirVentanaAdministrarEmpleados(ActionEvent event) {
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO);
    }

    @FXML
    void abrirVentanaAdministrarSucursales(ActionEvent event) {
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.SUCURSAL);
    }

    @FXML
    void abrirVentanaAdministrarTransacciones(ActionEvent event) {
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.TRANSACCION);
        
    }
    
    @FXML
    void cerrarSesion(MouseEvent event) {
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.LOGIN);
    }
    
    public void cargarDatosMenuEmpleado(){
        Empleado empleado = ManejadorDeSesion.obtenerEmpleado();
        Sucursal sucursal = ManejadorDeSesion.getSucursalActual();
        textNombre.setText(empleado.toString());
        textPuesto.setText(empleado.getTipoEmpleado());
        String sucursalActual= sucursal != null ? sucursal.toString() : "Sin sucursal asignada";
        textSucursal.setText(sucursalActual);
    }
    
private void ajustarMenuPerfil(){
    Empleado empleado = ManejadorDeSesion.obtenerEmpleado();
    String tipoEmpleado = empleado.getTipoEmpleado();
    
    if(EmpleadoDatosUtil.TIPO_GERENTE.equals(tipoEmpleado)){
        if(empleado instanceof Gerente){
           botonAdministrarSucursales.setDisable(!EmpleadoDatosUtil.NIVEL_NACIONAL.equals(((Gerente) empleado).getNivelAcceso())); // Gerente puede administrar sucursales
        }
    } else if(EmpleadoDatosUtil.TIPO_EJECUTIVO.equals(tipoEmpleado)){
        botonAdministrarTransacciones.setDisable(true);
        botonAdministrarSucursales.setDisable(true);
        botonAdministrarEmpleados.setDisable(true);
    } else if(EmpleadoDatosUtil.TIPO_CAJERO.equals(tipoEmpleado)){
        botonAdministrarClientes.setDisable(true);
        botonAdministrarCuentasBancarias.setDisable(true);
        botonAdministrarSucursales.setDisable(true);
        botonAdministrarEmpleados.setDisable(true);
        botonAdministrarTransacciones.setDisable(ManejadorDeSesion.getSucursalActual()==null);
    }
}
}
