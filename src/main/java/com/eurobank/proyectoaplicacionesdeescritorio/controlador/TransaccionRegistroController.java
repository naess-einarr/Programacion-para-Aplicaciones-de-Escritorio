package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.CuentaDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.dao.TransaccionDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cuenta;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.ConstantesUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.TransaccionDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeSesion;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransaccionRegistroController implements Initializable {

    private static final Logger LOG = LogManager.getLogger(TransaccionRegistroController.class);
    
    @FXML
    ComboBox comboTipoTransaccion;
    
    @FXML
    ComboBox<Cuenta>comboCuentaOrigen;
    
    @FXML
    ComboBox<Cuenta> comboCuentaDestino;
    
    @FXML
    TextField textSucursal;
    
    @FXML
    TextField textIDTransaccion;
    
    CuentaDAO cuentabancariaDAO;
    TransaccionDAO transaccionDAO;
    Sucursal sucursal;
    Empleado empleadoActual;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cuentabancariaDAO = new CuentaDAO();
        transaccionDAO = new TransaccionDAO();
        comboTipoTransaccion.setItems(TransaccionDatosUtil.listaTipoTransaccion());
        cargarComboCuentas();
        cargarComboTipoTransaccion();
        generarIDTransaccion();
        obtenerEmpleadoActual();
        cargarSucursalEmpleado();
        textIDTransaccion.setDisable(true);
        textSucursal.setDisable(true);
    }    
    
    @FXML
    public void realizarTransaccion(){
        
    }
    
    private void cargarComboTipoTransaccion(){
        comboTipoTransaccion.setItems(TransaccionDatosUtil.listaTipoTransaccion());
    }
    private void cargarComboCuentas(){
        try {
            ObservableList<Cuenta> items = FXCollections.observableArrayList(cuentabancariaDAO.obtenerTodos());
            comboCuentaOrigen.setItems(items);
            comboCuentaDestino.setItems(items);
        } catch (IOException ioe){
            LOG.error(ConstantesUtil.ERROR_CARGAR_INFORMACION, ioe);
        } catch (Exception e) {
            AlertaUtil.mostrarAlertaVentana();
        }
    }
    
    private void generarIDTransaccion(){
        String idTransaccion = UUID.randomUUID().toString().toUpperCase();
        textIDTransaccion.setText(idTransaccion);
    }
    
    @FXML
    private void cancelarTransaccion(){
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.TRANSACCION);
    }
    
    private void obtenerEmpleadoActual() {
        try {
            MenuController menuController = ManejadorDeVistas.getInstancia().obtenerControlador(ManejadorDeVistas.Vista.MENU);
        } catch (Exception ex) {
            LOG.error("Error al obtener empleado actual", ex);
        }
    }
    
        private void cargarSucursalEmpleado() {
        try {
            if (ManejadorDeSesion.haySesionActiva()) {
                Sucursal sucursalActual = ManejadorDeSesion.getSucursalActual();

                if (sucursalActual != null) {
                    textSucursal.setText(sucursalActual.toString());
                }
            }
        } catch (Exception ex) {
            LOG.error("Error al cargar sucursal del empleado", ex);
        }
    }

    
}
