package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.ClienteDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.dao.CuentaDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cliente;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cuenta;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.CuentaDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CuentaRegistroController implements Initializable {
    private static final Logger LOG = LogManager.getLogger(CuentaRegistroController.class);
    
    @FXML
    private ComboBox<Cliente> comboCliente;
    
    @FXML
    private ComboBox<String> comboTipo;
    
    @FXML
    private TextField textCuenta;
    
    @FXML
    private TextField textLimiteDeCredito;
    
    @FXML
    private TextField textSaldoActual;
    
    private Cuenta cuentaEdicion;
    private boolean modoEdicion;
    private CuentaDAO cuentaDAO;
    private ClienteDAO clienteDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cuentaDAO = new CuentaDAO();
        clienteDAO = new ClienteDAO();
        cargarComboCliente();
        cargarComboTipo();
        llenarCampoIdCuenta();
    }    
    
    @FXML
    void accionCancelar(ActionEvent event) {
        if(AlertaUtil.mostrarAlertaCancelarGuardado()){
            ManejadorDeVistas.getInstancia().limpiarCache();
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CUENTA);
        }
    }
    
    @FXML
    void accionGuardar(ActionEvent event) {
        try {
            Cuenta cuenta = llenarObjetoCuenta();

            if (modoEdicion) {
                cuentaDAO.actualizar(cuenta);
            } else {
                cuentaDAO.guardar(cuenta);
            }

            ManejadorDeVistas.getInstancia().limpiarCache();
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CUENTA);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }
    
    public void llenarCamposEdicion(Cuenta cuenta) {
        this.modoEdicion = Boolean.TRUE;
        this.cuentaEdicion = cuenta;
        textCuenta.setText(cuenta.getNumeroCuenta());
        textSaldoActual.setText(String.valueOf(cuenta.getSaldoActual()));
        textLimiteDeCredito.setText(String.valueOf(cuenta.getLimiteCredito()));
        comboTipo.getSelectionModel().select(cuenta.getTipo());
        comboCliente.getSelectionModel().select(cuenta.getCliente());
    }
    
    private void cargarComboCliente(){
        try {
            ObservableList<Cliente> items = FXCollections.observableArrayList(clienteDAO.obtenerTodos());
            comboCliente.setItems(items);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }
    
    private void cargarComboTipo(){
        comboTipo.setItems(FXCollections.observableArrayList(CuentaDatosUtil.listaTipo()));
    }
    
    private Cuenta llenarObjetoCuenta(){
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(textCuenta.getText().trim());
        cuenta.setTipo(comboTipo.getSelectionModel().getSelectedItem());
        cuenta.setSaldoActual(Double.parseDouble(textSaldoActual.getText().trim()));
        cuenta.setLimiteCredito(Double.parseDouble(textLimiteDeCredito.getText().trim()));
        cuenta.setCliente(obtenerDatosCliente(comboCliente.getSelectionModel().getSelectedItem()));
        
        return cuenta;
    }
    
    private void llenarCampoIdCuenta(){
        if(!modoEdicion){
            String nuevoId;
            try {
                nuevoId = CuentaDatosUtil.PREFIJO_CUENTA.concat(String.valueOf(cuentaDAO.obtenerSiguienteId()));
                textCuenta.setText(nuevoId);
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }
    }
    
    private Cliente obtenerDatosCliente(Cliente cliente){
        Cliente clienteDatos = new Cliente();
        clienteDatos.setIdCliente(cliente.getIdCliente());
        clienteDatos.setNombreCompleto(cliente.getNombreCompleto());
        clienteDatos.setApellidosCompletos(cliente.getApellidosCompletos());
        return clienteDatos;
    }
}