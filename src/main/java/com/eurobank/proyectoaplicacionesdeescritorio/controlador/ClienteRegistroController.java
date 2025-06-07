package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.ClienteDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cliente;
import com.eurobank.proyectoaplicacionesdeescritorio.util.Validador;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Controlador para el formulario de registro de clientes.
 */
public class ClienteRegistroController implements Initializable {
    
    private static final Logger LOG = LogManager.getLogger(ClienteRegistroController.class);
    
    // Campos del formulario vinculados al FXML
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
        LOG.info("Iniciando proceso de registro de cliente");
        
        try {
            Cliente nuevoCliente = crearClienteDesdeFormulario();
            
            Validador.validarCliente(nuevoCliente);
            
            clienteDAO.guardar(nuevoCliente);
            
            mostrarMensaje("Éxito", "Cliente registrado correctamente", Alert.AlertType.INFORMATION);
            
            LOG.info("Cliente registrado exitosamente con ID: {}", nuevoCliente.getIdCliente());
            
            limpiarFormulario();
            
        } catch (IllegalArgumentException e) {
            LOG.warn("Error de validación: {}", e.getMessage());
            mostrarMensaje("Error de Validación", e.getMessage(), Alert.AlertType.WARNING);
        } catch (Exception e) {
            LOG.error("Error al registrar cliente: {}", e.getMessage(), e);
            mostrarMensaje("Error", "Error al registrar el cliente: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    public void accionCancelar() {
        LOG.info("Cancelando registro de cliente");
        
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Está seguro que desea cancelar?");
        confirmacion.setContentText("Se perderán todos los datos ingresados.");
        
        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            cerrarVentana();
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
        
        LOG.debug("Formulario limpiado y listo para nuevo registro");
    }
    
    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    private void cerrarVentana() {
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();
        LOG.info("Ventana de registro cerrada");
    }
}