package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.dao.SucursalDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cuenta;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
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

public class SucursalEmpleadoController implements Initializable {
    private static final Logger LOG = LogManager.getLogger(SucursalEmpleadoController.class);

    @FXML
    private TableColumn<Empleado, String> columnaID;

    @FXML
    private TableColumn<Empleado, String> columnaIDAsociado;

    @FXML
    private TableColumn<Empleado, String> columnaNombre;

    @FXML
    private TableColumn<Empleado, String> columnaNombreAsociado;
    
    @FXML
    private TableView<Empleado> tablaEmpleadosAsociados;

    @FXML
    private TableView<Empleado> tablaEmpleadosDisponibles;
    
    @FXML
    private Label textSucursal;
    
    private EmpleadoDAO empleadoDAO;
    private SucursalDAO sucursalDAO;
    private Sucursal sucursalSeleccionada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleadoDAO = new EmpleadoDAO();
        sucursalDAO = new SucursalDAO();
        configurarTabla();
    }    
    
    @FXML
    void accionAsociarTodos(ActionEvent event) {
        tablaEmpleadosAsociados.getItems().addAll(tablaEmpleadosDisponibles.getItems());
        tablaEmpleadosDisponibles.getItems().clear();
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
            List<Empleado> listaActualizada = new ArrayList<>(tablaEmpleadosAsociados.getItems());
            sucursalSeleccionada.setEmpleadosAsociados(listaActualizada);
            sucursalDAO.actualizar(sucursalSeleccionada);
        } catch (Exception ex) {
            LOG.error(ex);
        }
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.SUCURSAL);
    }

    @FXML
    void accionRegresarTodos(ActionEvent event) {
        tablaEmpleadosDisponibles.getItems().addAll(tablaEmpleadosAsociados.getItems());
        tablaEmpleadosAsociados.getItems().clear();
    }

    @FXML
    void accionRegresarUno(ActionEvent event) {
        Empleado empleadoSeleccionado = tablaEmpleadosAsociados.getSelectionModel().getSelectedItem();
        if(Objects.isNull(empleadoSeleccionado)){
            AlertaUtil.mostrarAlerta(AlertaUtil.ADVERTENCIA, "Seleccione un empleado asociado", Alert.AlertType.WARNING);
            return;
        }
        tablaEmpleadosAsociados.getItems().remove(empleadoSeleccionado);
        tablaEmpleadosDisponibles.getItems().add(empleadoSeleccionado);
    }
    
    @FXML
    void accionAsociarUno(ActionEvent event) {
        Empleado empleadoSeleccionado = tablaEmpleadosDisponibles.getSelectionModel().getSelectedItem();
        if(Objects.isNull(empleadoSeleccionado)){
            AlertaUtil.mostrarAlerta(AlertaUtil.ADVERTENCIA, "Seleccione un empleado disponible", Alert.AlertType.WARNING);
            return;
        }
        tablaEmpleadosDisponibles.getItems().remove(empleadoSeleccionado);
        tablaEmpleadosAsociados.getItems().add(empleadoSeleccionado);
    }
    private void configurarTabla(){
        columnaID.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        columnaIDAsociado.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        columnaNombreAsociado.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
    }
    
    public void administrarSucursal(Sucursal sucursalSeleccionada){
        this.sucursalSeleccionada = sucursalSeleccionada;
        textSucursal.setText(sucursalSeleccionada.toString());
        cargarListaEmpleadosAsociados();
        cargarListaEmpleadosDisponibles();
    }
    
    private void cargarListaEmpleadosAsociados(){
        ObservableList<Empleado> empleadosAsociados = FXCollections.observableArrayList(sucursalSeleccionada.getEmpleadosAsociados());
        tablaEmpleadosAsociados.setItems(empleadosAsociados);
    }
    
    private void cargarListaEmpleadosDisponibles(){
        try {
            List<Empleado> universoTotal = empleadoDAO.obtenerTodos();
            List<Sucursal> empleadosSucursales = sucursalDAO.obtenerTodos();
            
            Set<String> empleadosYaAsociados = empleadosSucursales.stream()
                .filter(sucursal -> sucursal.getEmpleadosAsociados()!= null)
                .flatMap(sucursal -> sucursal.getEmpleadosAsociados().stream())
                .map(Empleado::getIdEmpleado)
                .collect(Collectors.toSet());

            List<Empleado> empleadosDisponibles = universoTotal.stream()
                .filter(empleado -> !empleadosYaAsociados.contains(empleado.getIdEmpleado()))
                .collect(Collectors.toList());
            
            ObservableList<Empleado> empleadosFinales = FXCollections.observableArrayList(empleadosDisponibles);
            tablaEmpleadosDisponibles.setItems(empleadosFinales);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }
   
}
