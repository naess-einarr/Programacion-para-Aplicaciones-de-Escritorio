package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.ClienteDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cliente;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClienteController implements Initializable {
    
    @FXML
    private TableColumn<Cliente, String> columnaID;
    
    @FXML
    private TableColumn<Cliente, LocalDate> columnaFechaNacimiento;

    @FXML
    private TableColumn<Cliente, String> columnaRFC;
    
    @FXML
    private TableColumn<Cliente, String> columnaNombre;
    
    @FXML
    private TableColumn<Cliente, String> columnaApellido;
    
    @FXML
    private TableColumn<Cliente, String> columnaNacionalidad;
    
    @FXML
    private TableColumn<Cliente, String> columnaTelefono;
    
    @FXML
    private TableColumn<Cliente, String> columnaCorreo;
    
    @FXML
    private TableView<Cliente> tablaClientes;

    private ClienteDAO clienteDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO = new ClienteDAO();
        configurarTablaClientes();
        cargarListaClientes();
    }
    
    @FXML
    public void accionRegistrar(){
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CLIENTE_REGISTRO);
    }
    
    @FXML
    public void accionCancelar(){
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.MENU);
    }
    
    @FXML
    public void accionEliminar(){
        
        if (Objects.isNull(tablaClientes.getSelectionModel().getSelectedItem())) {
            
            AlertaUtil.mostrarAlerta(AlertaUtil.INFORMACION, "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
            return;
        }
        Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        try{
            if (AlertaUtil.mostrarAlertaEliminar()){
                clienteDAO.eliminar(cliente.getIdCliente());
            }
        } catch (IOException ioe){
            AlertaUtil.mostrarAlertaEliminacionFallida();
        } catch (Exception e){
            AlertaUtil.mostrarAlertaEliminacionFallida();
        }
        
        cargarListaClientes();
        
        
    }
    
    @FXML
    public void accionEditar(){
        try {
            if(Objects.isNull(tablaClientes.getSelectionModel().getSelectedItem())){
                AlertaUtil.mostrarAlerta(AlertaUtil.INFORMACION, "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
                return;
            }
            ManejadorDeVistas.getInstancia().limpiarCacheVista(ManejadorDeVistas.Vista.CLIENTE_REGISTRO);

            ClienteRegistroController clienteRegistroController = ManejadorDeVistas.getInstancia().obtenerControlador(ManejadorDeVistas.Vista.CLIENTE_REGISTRO);
            Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
            clienteRegistroController.setEditarCliente(cliente);
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.CLIENTE_REGISTRO);
        }catch (IOException ioe){
            AlertaUtil.mostrarAlertaVentana();
        }
    }
    
    @FXML
    private void cargarListaClientes(){
        try{
            tablaClientes.setItems(FXCollections.observableList(clienteDAO.obtenerTodos()));
        } catch (Exception e){
            AlertaUtil.mostrarAlertaVentana();
        }
    }
    
    @FXML
    private void configurarTablaClientes() {
        columnaID.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        columnaRFC.setCellValueFactory(new PropertyValueFactory<>("rfcCliente"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellidosCompletos"));
        columnaNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidadCliente"));
        columnaFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoContacto"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));

    }
    
    
}
