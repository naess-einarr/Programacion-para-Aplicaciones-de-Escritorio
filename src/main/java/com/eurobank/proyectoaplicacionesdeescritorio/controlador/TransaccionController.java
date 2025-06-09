package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.TransaccionDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Transaccion;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TransaccionController implements Initializable {
    
    @FXML
    private TableColumn<Transaccion, String> columnaID;

    @FXML
    private TableColumn<Transaccion, Double> columnaMonto;

    @FXML
    private TableColumn<Transaccion, LocalDate> columnaFecha;

    @FXML
    private TableColumn<Transaccion, String> cuentaOrigen;

    @FXML
    private TableColumn<Transaccion, String> cuentaDestino;

    @FXML
    private TableColumn<Transaccion, String> columnaSucursal;

    @FXML
    private TableView<Transaccion> tablaTransacciones;

    private TransaccionDAO transaccionDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transaccionDAO = new TransaccionDAO();
        configurarTablaTransacciones();
    }    
    
    private void accionRegistrar(){
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.TRANSACCION);
    }
    private void accionCancelar(){
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.MENU);
    }
    private void configurarTablaTransacciones(){
        columnaID.setCellValueFactory(new PropertyValueFactory<>("idTransaccion"));
        columnaMonto.setCellValueFactory(new PropertyValueFactory<>("montoTransaccion"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaHoraTransaccion"));
        cuentaOrigen.setCellValueFactory(new PropertyValueFactory<>("cuentaOrigen"));
        cuentaDestino.setCellValueFactory(new PropertyValueFactory<>("cuentaDestino"));
        columnaSucursal.setCellValueFactory(new PropertyValueFactory<>("sucursal"));
        
    }
    
}
