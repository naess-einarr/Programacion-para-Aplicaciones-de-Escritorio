package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.TransaccionDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.util.ComboDatosTransaccion;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TransaccionRegistroController implements Initializable {

    @FXML
    ComboBox comboTipoTransaccion;
    
    TransaccionDAO transaccionDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transaccionDAO = new TransaccionDAO();
        comboTipoTransaccion.setItems(ComboDatosTransaccion.listaTipoTransaccion());
        
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
