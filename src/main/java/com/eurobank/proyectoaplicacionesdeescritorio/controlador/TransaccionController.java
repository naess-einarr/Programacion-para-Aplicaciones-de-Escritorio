package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.TransaccionDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Transaccion;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.ConstantesUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransaccionController implements Initializable {
    
    private static final Logger LOG = LogManager.getLogger(TransaccionController.class);
    
    @FXML
    private TableColumn<Transaccion, String> columnaID;

    @FXML
    private TableColumn<Transaccion, Double> columnaMonto;

    @FXML
    private TableColumn<Transaccion, LocalDate> columnaFecha;
    
    @FXML
    private TableColumn <Transaccion, String> columnaTipo;

    @FXML
    private TableColumn<Transaccion, String> columnaOrigen;

    @FXML
    private TableColumn<Transaccion, String> columnaDestino;

    @FXML
    private TableColumn<Transaccion, String> columnaSucursal;

    @FXML
    private TableView<Transaccion> tablaTransacciones;

    private TransaccionDAO transaccionDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transaccionDAO = new TransaccionDAO();
        configurarTablaTransacciones();
        cargarListaTransacciones();
    }    
    
    @FXML
    private void accionRegistrar(){
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.TRANSACCION_REGISTRO);
    }
    
    @FXML
    private void accionCancelar(){
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.MENU);
    }
    
    private void cargarListaTransacciones(){
        try {
            tablaTransacciones.setItems(FXCollections.observableList(transaccionDAO.obtenerTodos()));
            
        } catch (Exception e){
            LOG.error(ConstantesUtil.ERROR_CARGAR_INFORMACION, e);
            AlertaUtil.mostrarAlerta(AlertaUtil.ERROR, ConstantesUtil.ERROR_CARGAR_INFORMACION, Alert.AlertType.ERROR);
        }
    }
    
    private void configurarTablaTransacciones(){
        
        columnaID.setCellValueFactory(new PropertyValueFactory<>("idTransaccion"));
        columnaMonto.setCellValueFactory(new PropertyValueFactory<>("montoTransaccion"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaHoraTransaccion"));
        columnaTipo.setCellValueFactory (new PropertyValueFactory<>("tipoTransaccion"));
        columnaOrigen.setCellValueFactory(new PropertyValueFactory<>("cuentaOrigen"));
        columnaDestino.setCellValueFactory(new PropertyValueFactory<>("cuentaDestino"));
        columnaSucursal.setCellValueFactory(new PropertyValueFactory<>("sucursal"));
        
    }
    
}
