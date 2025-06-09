
package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.SucursalDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SucursalSeleccionController implements Initializable {

    private SucursalDAO sucursalDAO;

    @FXML
    private TableColumn<Sucursal, String> contacto;

    @FXML
    private TableColumn<Sucursal, String> correoSucursal;

    @FXML
    private TableColumn<Sucursal, String> direccionSucursal;

    @FXML
    private TableColumn<Sucursal, String> gerente;

    @FXML
    private TableColumn<Sucursal, String> idSucursal;

    @FXML
    private TableColumn<Sucursal, String> telefonoSucursal;

    @FXML
    private TableColumn<Sucursal, String> nombreSucursal;
    
    @FXML
    private TableView<Sucursal> tablaSucursales;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sucursalDAO = new SucursalDAO();
        configurarTabla();
        cargarDatosTabla();
        
    }    

    @FXML
    void accionCancelar(ActionEvent event) {
        
    }

    @FXML
    void accionSeleccionar(ActionEvent event) {
    
    }
    
    private void cargarDatosTabla(){
        try {
            tablaSucursales.setItems(FXCollections.observableArrayList(sucursalDAO.obtenerTodos()));
        } catch (Exception ex) {

        }
    }
    
    private void configurarTabla(){
        contacto.setCellValueFactory(new PropertyValueFactory<>("contacto"));
        correoSucursal.setCellValueFactory(new PropertyValueFactory<>("correoSucursal"));
        direccionSucursal.setCellValueFactory(new PropertyValueFactory<>("direccionSucursal"));
        gerente.setCellValueFactory(cellData -> {
            Gerente gerenteObj = cellData.getValue().getGerente();
            if (gerenteObj != null) {
                return new SimpleStringProperty(gerenteObj.getNombreCompleto());
            }
            return new SimpleStringProperty("");
        });
        idSucursal.setCellValueFactory(new PropertyValueFactory<>("idSucursal"));
        telefonoSucursal.setCellValueFactory(new PropertyValueFactory<>("telefonoSucursal"));
        nombreSucursal.setCellValueFactory(new PropertyValueFactory<>("nombreSucursal"));
    }
    
}
