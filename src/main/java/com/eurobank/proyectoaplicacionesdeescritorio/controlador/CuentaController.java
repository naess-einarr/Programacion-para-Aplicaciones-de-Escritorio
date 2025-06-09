package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.CuentaDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cliente;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cuenta;
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

public class CuentaController implements Initializable {
    private static final Logger LOG = LogManager.getLogger(CuentaController.class);

    @FXML
    private TableColumn<Cuenta, Cliente> columnaClienteAsociado;
    @FXML
    private TableColumn<Cuenta, String> columnaID;
    @FXML
    private TableColumn<Cuenta, Double> columnaLimiteCredito;
    @FXML
    private TableColumn<Cuenta, Double> columnaSaldoActual;
    @FXML
    private TableColumn<Cuenta, String> columnaTipo;
    @FXML
    private TableView<Cuenta> tablaCuentas;
    
    private CuentaDAO cuentaDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cuentaDAO = new CuentaDAO();
        configurarTabla();
        cargarDatosTabla();
    }    

    @FXML
    void accionRegistrar(ActionEvent event) {
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CUENTA_REGISTRO);
    }

    @FXML
    void accionEditar(ActionEvent event) {
        Cuenta cuentaBancariaSeleccionada = tablaCuentas.getSelectionModel().getSelectedItem();
        try {
            if (Objects.isNull(cuentaBancariaSeleccionada)) {
                AlertaUtil.mostrarAlerta("INFORACION", "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
                return;
            }

            ManejadorDeVistas.getInstancia().limpiarCacheVista(ManejadorDeVistas.Vista.CUENTA_REGISTRO);

            CuentaRegistroController cuentaRegistroController = ManejadorDeVistas.getInstancia().obtenerControlador(ManejadorDeVistas.Vista.CUENTA_REGISTRO);
            cuentaRegistroController.llenarCamposEdicion(cuentaBancariaSeleccionada);
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CUENTA_REGISTRO);

        } catch (IOException ex) {
            LOG.error(ex);
        }
    }

    @FXML
    void accionEliminar(ActionEvent event) {
        if (Objects.isNull(tablaCuentas.getSelectionModel().getSelectedItem())) {
            
            AlertaUtil.mostrarAlerta(AlertaUtil.INFORMACION, "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
            return;
        }
        Cuenta cuentaBancaria = tablaCuentas.getSelectionModel().getSelectedItem();
        try {
            if (AlertaUtil.mostrarAlertaEliminar()) {
                cuentaDAO.eliminar(cuentaBancaria.getNumeroCuenta());
                tablaCuentas.getItems().remove(cuentaBancaria);
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    @FXML
    void accionCancelar(ActionEvent event) {
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.MENU);
    }
    
    private void cargarDatosTabla() {
        try {
            tablaCuentas.setItems(FXCollections.observableArrayList(cuentaDAO.obtenerTodos()));
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }
    
    private void configurarTabla() {
        columnaClienteAsociado.setCellValueFactory(cellData -> {
            Cliente cliente = cellData.getValue().getCliente();
            if (cliente != null) {
                return new SimpleObjectProperty(cliente);
            }
            return new SimpleObjectProperty(null);
        });
        columnaID.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        columnaLimiteCredito.setCellValueFactory(new PropertyValueFactory<>("limiteCredito"));
        columnaSaldoActual.setCellValueFactory(new PropertyValueFactory<>("saldoActual"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    }
}