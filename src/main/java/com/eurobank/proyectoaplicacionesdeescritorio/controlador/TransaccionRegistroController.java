package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.CuentaDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.dao.TransaccionDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cuenta;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.TransaccionDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
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
    ComboBox comboCuentaOrigen;
    
    @FXML
    ComboBox comboCuentaDestino;
    
    @FXML
    TextField textSucursal;
    
    @FXML
    TextField textIDTransaccion;
    
    CuentaDAO cuentabancariaDAO;
    TransaccionDAO transaccionDAO;
    Sucursal sucursal;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transaccionDAO = new TransaccionDAO();
        comboTipoTransaccion.setItems(TransaccionDatosUtil.listaTipoTransaccion());
        cargarComboCuentas();
        cargarComboTipoTransaccion();
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
        } catch (Exception ex) {
            AlertaUtil.mostrarAlertaVentana();
        }
    }
    
    private void cargarSucursal(){
        textSucursal.setText("");
    }
    
    
    private void generarIDTransaccion(){
        String idTransaccion = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        textIDTransaccion.setText(idTransaccion);
    }
    
    @FXML
    private void cancelarTransaccion(){
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.TRANSACCION);
    }
    
}
