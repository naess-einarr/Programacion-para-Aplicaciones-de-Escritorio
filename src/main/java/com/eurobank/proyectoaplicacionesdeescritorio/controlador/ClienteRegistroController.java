package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.ClienteDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cliente;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.ConstantesUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.Validador;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class ClienteRegistroController implements Initializable {
    
    private static final Logger LOG = LogManager.getLogger(ClienteRegistroController.class);
    
    @FXML
    private TextField textIdCliente;
    
    @FXML
    private TextField textNombreCliente;
    
    @FXML
    private TextField textApellidoCliente;
    
    @FXML
    private TextField textNacionalidadCliente;
    
    @FXML
    private DatePicker dateFechaNacCliente;
    
    @FXML
    private TextField textRfcCliente;
    
    @FXML
    private TextField textCurpCliente;
    
    @FXML
    private TextArea textDireccionCliente;
    
    @FXML
    private TextField textTelefonoCliente;
    
    @FXML
    private TextField textCorreoCliente;
    
    @FXML
    private Button botonRegistrar;
    
    @FXML
    private Button botonCancelar;
    
    private ClienteDAO clienteDAO;
    private Cliente clienteEdicion;
    private boolean modoEdicion;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clienteDAO = new ClienteDAO();
        modoEdicion = false;
        generarIdUnico();
    }
    
    private void generarIdUnico() {
        if (!modoEdicion) {
            String idUnico = "CLI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            textIdCliente.setText(idUnico);
        }
    }
    
    @FXML
    public void accionRegistrar() {
        try {
            Cliente cliente = crearClienteDesdeFormulario();
            Validador.validarCliente(cliente);
            
            if (modoEdicion) {
                clienteDAO.actualizar(cliente);
                AlertaUtil.mostrarAlertaRegistroExitoso();
            } else {
                clienteDAO.guardar(cliente);
                AlertaUtil.mostrarAlertaRegistroExitoso();
            }
            
            ManejadorDeVistas.getInstancia().limpiarCache();
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CLIENTE);
            
        } catch (IllegalArgumentException e) {
            LOG.warn(ConstantesUtil.ALERTA_DATOS_INVALIDOS, e);
            AlertaUtil.mostrarAlerta(AlertaUtil.ADVERTENCIA, e.getMessage(), Alert.AlertType.WARNING);
        } catch (Exception e) {
            LOG.error("Error al procesar cliente: ", e);
            AlertaUtil.mostrarAlerta(AlertaUtil.ERROR, e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    public void accionCancelar() {
        if (AlertaUtil.mostrarAlertaCancelarGuardado()) {
            ManejadorDeVistas.getInstancia().limpiarCache();
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CLIENTE);
        }
    }
    
    private Cliente crearClienteDesdeFormulario() {
        Cliente cliente = new Cliente();
        
        cliente.setIdCliente(textIdCliente.getText().trim());
        cliente.setNombreCompleto(textNombreCliente.getText().trim());
        cliente.setApellidosCompletos(textApellidoCliente.getText().trim());
        cliente.setNacionalidadCliente(textNacionalidadCliente.getText().trim());
        cliente.setFechaNacimiento(dateFechaNacCliente.getValue());
        cliente.setRfcCliente(textRfcCliente.getText().trim().toUpperCase());
        cliente.setCurpCliente(textCurpCliente.getText().trim().toUpperCase());
        cliente.setDireccionCompleta(textDireccionCliente.getText().trim());
        cliente.setTelefonoContacto(textTelefonoCliente.getText().trim());
        cliente.setCorreoElectronico(textCorreoCliente.getText().trim().toLowerCase());
        
        return cliente;
    }
    
    public void configurarModoEdicion(Cliente clienteEditar) {
        this.modoEdicion = true;
        this.clienteEdicion = clienteEditar;
        llenarCamposEdicion(clienteEditar);
        
        botonRegistrar.setText("Actualizar");
        
    }
    
    private void llenarCamposEdicion(Cliente clienteEditar) {
        textIdCliente.setText(clienteEditar.getIdCliente());
        textNombreCliente.setText(clienteEditar.getNombreCompleto());
        textApellidoCliente.setText(clienteEditar.getApellidosCompletos());
        textNacionalidadCliente.setText(clienteEditar.getNacionalidadCliente());
        dateFechaNacCliente.setValue(clienteEditar.getFechaNacimiento());
        textRfcCliente.setText(clienteEditar.getRfcCliente());
        textCurpCliente.setText(clienteEditar.getCurpCliente());
        textDireccionCliente.setText(clienteEditar.getDireccionCompleta());
        textTelefonoCliente.setText(clienteEditar.getTelefonoContacto());
        textCorreoCliente.setText(clienteEditar.getCorreoElectronico());
    }
    
}