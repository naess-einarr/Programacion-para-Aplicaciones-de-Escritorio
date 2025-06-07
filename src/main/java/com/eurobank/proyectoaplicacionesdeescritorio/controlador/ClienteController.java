package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ClienteController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void accionRegistrar(){
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CLIENTE_REGISTRO);
    }
    
    @FXML
    public void accionCancelar(){
        
    }
    
    @FXML
    public void accionEliminar(){
        
    }
    
    @FXML
    public void accionEditar(){
        
    }
    
    
}
