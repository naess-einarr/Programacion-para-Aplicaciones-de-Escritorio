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
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        clienteDAO = new ClienteDAO();
        generarIdUnico();
    }
    
    private void generarIdUnico() {
        String idUnico = "CLI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        textIdCliente.setText(idUnico);
        LOG.debug("ID único generado: {}", idUnico);
    }
    
    @FXML
    public void accionRegistrar() {
        
        try {
            
            Cliente nuevoCliente = crearClienteDesdeFormulario();
            Validador.validarCliente(nuevoCliente);
            clienteDAO.guardar(nuevoCliente);
            
            AlertaUtil.mostrarAlertaRegistroExitoso();
            limpiarFormulario();
        } catch (IllegalArgumentException e) {
            
            LOG.warn(ConstantesUtil.ALERTA_DATOS_INVALIDOS, e);
            AlertaUtil.mostrarAlerta(AlertaUtil.ADVERTENCIA, e.getMessage(), Alert.AlertType.WARNING);
        } catch (Exception e) {
            
            AlertaUtil.mostrarAlerta(AlertaUtil.ERROR, e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    public void accionCancelar() {
        
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CLIENTE);
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
    
    public void setEditarCliente(Cliente clienteEditar){
        
        this.textIdCliente.setText(clienteEditar.getIdCliente());
        this.textNombreCliente.setText(clienteEditar.getNombreCompleto());
        this.textApellidoCliente.setText(clienteEditar.getApellidosCompletos());
        this.textNacionalidadCliente.setText(clienteEditar.getNacionalidadCliente());
        this.dateFechaNacCliente.setValue(clienteEditar.getFechaNacimiento());
        this.textRfcCliente.setText(clienteEditar.getRfcCliente());
        this.textCurpCliente.setText(clienteEditar.getCurpCliente());
        this.textDireccionCliente.setText(clienteEditar.getDireccionCompleta());
        this.textTelefonoCliente.setText(clienteEditar.getTelefonoContacto());
        this.textCorreoCliente.setText(clienteEditar.getCorreoElectronico());
        
    }
    
    private void limpiarFormulario() {
        textNombreCliente.clear();
        textApellidoCliente.clear();
        textNacionalidadCliente.clear();
        dateFechaNacCliente.setValue(null);
        textRfcCliente.clear();
        textCurpCliente.clear();
        textDireccionCliente.clear();
        textTelefonoCliente.clear();
        textCorreoCliente.clear();
        
        generarIdUnico();
    }
}