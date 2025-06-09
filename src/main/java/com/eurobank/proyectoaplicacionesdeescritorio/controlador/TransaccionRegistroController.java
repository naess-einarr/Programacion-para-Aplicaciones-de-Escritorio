package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.SucursalDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.dao.TransaccionDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.CuentaBancaria;
import com.eurobank.proyectoaplicacionesdeescritorio.util.TransaccionDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransaccionRegistroController implements Initializable {

    private static final Logger LOG = LogManager.getLogger(TransaccionRegistroController.class);
    
    @FXML
    ComboBox comboTipoTransaccion;
    
    TransaccionDAO transaccionDAO;
    SucursalDAO sucursalDAO;
    CuentaBancaria cuentaBancariaDAO;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transaccionDAO = new TransaccionDAO();
        comboTipoTransaccion.setItems(TransaccionDatosUtil.listaTipoTransaccion());
        cargarComboCuentas();
    }    
    
    @FXML
    public void realizarTransaccion(){
        
    }
    
    @FXML
    public void cancelarTransaccion(){
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.TRANSACCION);
    }
    
}
