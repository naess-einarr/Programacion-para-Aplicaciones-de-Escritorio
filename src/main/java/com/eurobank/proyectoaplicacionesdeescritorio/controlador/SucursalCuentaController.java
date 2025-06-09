package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.CuentaDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.dao.SucursalDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cliente;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cuenta;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SucursalCuentaController implements Initializable {
    private static final Logger LOG = LogManager.getLogger(SucursalCuentaController.class);

    @FXML
    private TableColumn<Cuenta, String> columnaCuenta;

    @FXML
    private TableColumn<Cuenta, String> columnaCuentaAsociada;

    @FXML
    private TableColumn<Cuenta, String> columnaNombre;

    @FXML
    private TableColumn<Cuenta, String> columnaNombreAsociado;
    
    @FXML
    private TableColumn<Cuenta, String> columnaApellidos;

    @FXML
    private TableColumn<Cuenta, String> columnaApellidosAsociado;
    
    @FXML
    private TableView<Cuenta> tablaCuentasAsociadas;

    @FXML
    private TableView<Cuenta> tablaCuentasDisponibles;
    
    @FXML
    private Label textSucursal;
    
    private CuentaDAO cuentaDAO;
    private SucursalDAO sucursalDAO;
    private Sucursal sucursalSeleccionada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cuentaDAO = new CuentaDAO();
        sucursalDAO = new SucursalDAO();
        
        configurarTabla();
    }    
    
    @FXML
    void accionAsociarTodos(ActionEvent event) {
        tablaCuentasAsociadas.getItems().addAll(tablaCuentasDisponibles.getItems());
        tablaCuentasDisponibles.getItems().clear();
    }

    @FXML
    void accionAsociarUno(ActionEvent event) {
        Cuenta cuentaSeleccionada = tablaCuentasDisponibles.getSelectionModel().getSelectedItem();
        if(Objects.isNull(cuentaSeleccionada)){
            AlertaUtil.mostrarAlerta(AlertaUtil.ADVERTENCIA, "Seleccione un empleado disponible", Alert.AlertType.WARNING);
            return;
        }
        tablaCuentasDisponibles.getItems().remove(cuentaSeleccionada);
        tablaCuentasAsociadas.getItems().add(cuentaSeleccionada);
    }

    @FXML
    void accionCancelar(ActionEvent event) {
        if(AlertaUtil.mostrarAlertaCancelarGuardado()){
            ManejadorDeVistas.getInstancia().limpiarCache();
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.SUCURSAL);
        }
    }

    @FXML
    void accionGuardar(ActionEvent event) {
        try {
            List<Cuenta> listaActualizada = new ArrayList<>(tablaCuentasAsociadas.getItems());
            sucursalSeleccionada.setCuentasAsociadas(listaActualizada);
            sucursalDAO.actualizar(sucursalSeleccionada);
        } catch (Exception ex) {
            LOG.error(ex);
        }
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.SUCURSAL);
    }

    @FXML
    void accionRegresarTodos(ActionEvent event) {
        tablaCuentasDisponibles.getItems().addAll(tablaCuentasAsociadas.getItems());
        tablaCuentasAsociadas.getItems().clear();
    }

    @FXML
    void accionRegresarUno(ActionEvent event) {
        Cuenta cuentaSeleccionada = tablaCuentasAsociadas.getSelectionModel().getSelectedItem();
        if(Objects.isNull(cuentaSeleccionada)){
            AlertaUtil.mostrarAlerta(AlertaUtil.ADVERTENCIA, "Seleccione un empleado asociado", Alert.AlertType.WARNING);
            return;
        }
        tablaCuentasAsociadas.getItems().remove(cuentaSeleccionada);
        tablaCuentasDisponibles.getItems().add(cuentaSeleccionada);
    }
    public void administrarSucursal(Sucursal sucursalSeleccionada){
        this.sucursalSeleccionada = sucursalSeleccionada;
        textSucursal.setText(sucursalSeleccionada.toString());
        cargarListaEmpleadosAsociados();
        cargarListaEmpleadosDisponibles();
    }
    
        private void cargarListaEmpleadosAsociados(){
        ObservableList<Cuenta> cuentasAsociadas = FXCollections.observableArrayList(sucursalSeleccionada.getCuentasAsociadas());
        tablaCuentasAsociadas.setItems(cuentasAsociadas);
    }
    
    private void cargarListaEmpleadosDisponibles(){
        try {
            ObservableList<Cuenta> cuentasDisponibles = FXCollections.observableArrayList(cuentaDAO.obtenerTodos());
            tablaCuentasDisponibles.setItems(cuentasDisponibles);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }
    private void configurarTabla(){
        columnaCuenta.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        columnaCuentaAsociada.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        columnaNombre.setCellValueFactory(cellData -> {
            Cliente cliente = cellData.getValue().getCliente();
            if (cliente != null) {
                return new SimpleStringProperty(cliente.getNombreCompleto());
            }
            return new SimpleStringProperty("");
        });
        columnaNombreAsociado.setCellValueFactory(cellData -> {
            Cliente cliente = cellData.getValue().getCliente();
            if (cliente != null) {
                return new SimpleStringProperty(cliente.getNombreCompleto());
            }
            return new SimpleStringProperty("");
        });
        columnaApellidos.setCellValueFactory(cellData -> {
            Cliente cliente = cellData.getValue().getCliente();
            if (cliente != null) {
                return new SimpleStringProperty(cliente.getApellidosCompletos());
            }
            return new SimpleStringProperty("");
        });
        columnaApellidosAsociado.setCellValueFactory(cellData -> {
            Cliente cliente = cellData.getValue().getCliente();
            if (cliente != null) {
                return new SimpleStringProperty(cliente.getApellidosCompletos());
            }
            return new SimpleStringProperty("");
        });
    }
}
