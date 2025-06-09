package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.SucursalDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Ejecutivo;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SucursalController implements Initializable {
    private static final Logger LOG = LogManager.getLogger(SucursalController.class);


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
    void accionRegistrar(ActionEvent event) {
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.SUCURSAL_REGISTRO);
    }
    
    @FXML
    void accionEditar(ActionEvent event) {
        Sucursal sucursalSeleccionada = tablaSucursales.getSelectionModel().getSelectedItem();
        try {
            if (Objects.isNull(sucursalSeleccionada)) {
                AlertaUtil.mostrarAlerta("INFORACION", "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
                return;
            }

            ManejadorDeVistas.getInstancia().limpiarCacheVista(ManejadorDeVistas.Vista.SUCURSAL_REGISTRO);

            SucursalRegistroController sucursalRegistroController = ManejadorDeVistas.getInstancia().obtenerControlador(ManejadorDeVistas.Vista.SUCURSAL_REGISTRO);
            sucursalRegistroController.llenarCamposEdicion(sucursalSeleccionada);
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.SUCURSAL_REGISTRO);

        } catch (IOException ex) {
            LOG.error(ex);
        }
    }

    @FXML
    void accionEliminar(ActionEvent event) {
        if (Objects.isNull(tablaSucursales.getSelectionModel().getSelectedItem())) {
            
            AlertaUtil.mostrarAlerta(AlertaUtil.INFORMACION, "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
            return;
        }
        Sucursal sucursal = tablaSucursales.getSelectionModel().getSelectedItem();
        try {
            if (AlertaUtil.mostrarAlertaEliminar()) {
                sucursalDAO.eliminar(sucursal.getIdSucursal());
                tablaSucursales.getItems().remove(sucursal);
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }
   
    @FXML
    void accionCuentas(ActionEvent event) {
        Sucursal sucursalSeleccionada = tablaSucursales.getSelectionModel().getSelectedItem();
        if (Objects.isNull(sucursalSeleccionada)) {
            AlertaUtil.mostrarAlerta("INFORACION", "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
            return;
        }
        try {
            SucursalCuentaController sucursalCuentaController = ManejadorDeVistas.getInstancia().obtenerControlador(ManejadorDeVistas.Vista.SUCURSAL_CUENTAS);
            sucursalCuentaController.administrarSucursal(sucursalSeleccionada);
        } catch (IOException ex) {
            LOG.error(ex);
        }

        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.SUCURSAL_CUENTAS);
    }
    
    @FXML
    void accionEmpleados(ActionEvent event) {
        Sucursal sucursalSeleccionada = tablaSucursales.getSelectionModel().getSelectedItem();
        if (Objects.isNull(sucursalSeleccionada)) {
            AlertaUtil.mostrarAlerta("INFORACION", "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
            return;
        }
        try {
            SucursalEmpleadoController sucursalEmpleadoController = ManejadorDeVistas.getInstancia().obtenerControlador(ManejadorDeVistas.Vista.SUCURSAL_EMPLEADOS);
            sucursalEmpleadoController.administrarSucursal(sucursalSeleccionada);
        } catch (IOException ex) {
            LOG.error(ex);
        }
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.SUCURSAL_EMPLEADOS);
    }
    
    @FXML
    void accionCancelar(ActionEvent event) {
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.MENU);
    }
    
    private void cargarDatosTabla() {
        try {
            tablaSucursales.setItems(FXCollections.observableArrayList(sucursalDAO.obtenerTodos()));
        } catch (Exception ex) {
            LOG.error(ex);
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
