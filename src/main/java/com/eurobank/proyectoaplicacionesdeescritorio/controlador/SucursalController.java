package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.SucursalDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Ejecutivo;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SucursalController implements Initializable {

    @FXML
    private TableColumn<Sucursal, Ejecutivo> columnaContacto;
    @FXML
    private TableColumn<Sucursal, String> columnaCorreo;
    @FXML
    private TableColumn<Sucursal, String> columnaDireccion;
    @FXML
    private TableColumn<Sucursal, Gerente> columnaGerente;
    @FXML
    private TableColumn<Sucursal, String> columnaID;
    @FXML
    private TableColumn<Sucursal, String> columnaNombre;
    @FXML
    private TableColumn<Sucursal, String> columnaTelefono;
    @FXML
    private TableView<Sucursal> tablaSucursales;
    private SucursalDAO sucursalDAO;
    
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
    void accionCuentas(ActionEvent event) {

    }

    @FXML
    void accionEditar(ActionEvent event) {

    }

    @FXML
    void accionEliminar(ActionEvent event) {

    }

    @FXML
    void accionEmpleados(ActionEvent event) {

    }

    @FXML
    void accionRegistrar(ActionEvent event) {

    }
    
    private void cargarDatosTabla() {
        try {
            tablaSucursales.setItems(FXCollections.observableArrayList(sucursalDAO.obtenerTodos()));
        } catch (Exception ex) {

        }
    }

    private void configurarTabla() {
        columnaContacto.setCellValueFactory(cellData -> {
            Ejecutivo ejecutivo = cellData.getValue().getContacto();
            if (ejecutivo != null) {
                return new SimpleObjectProperty(ejecutivo);
            }
            return new SimpleObjectProperty(null);
        });
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correoSucursal"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionSucursal"));
        columnaGerente.setCellValueFactory(cellData -> {
            Gerente gerente = cellData.getValue().getGerente();
            if (gerente != null) {
                return new SimpleObjectProperty(gerente);
            }
            return new SimpleObjectProperty(null);
        });
        columnaID.setCellValueFactory(new PropertyValueFactory<>("idSucursal"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoSucursal"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreSucursal"));
    }
    
}
