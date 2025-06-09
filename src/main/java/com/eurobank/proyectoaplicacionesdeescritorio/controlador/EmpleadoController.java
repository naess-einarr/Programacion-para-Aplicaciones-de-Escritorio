package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.EmpleadoDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.EmpleadoTablaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmpleadoController implements Initializable {
    
    private static final Logger LOG = LogManager.getLogger(EmpleadoController.class);

    @FXML
    private TableColumn<Empleado, String> columnTipoEmpleado;
    @FXML
    private TableColumn<Empleado, LocalDate> columnaFechaNacimiento;
    @FXML
    private TableColumn<Empleado, String> columnaDireccion;
    @FXML
    private TableColumn<Empleado, String> columnaGenero;
    @FXML
    private TableColumn<Empleado, String> columnaID;
    @FXML
    private TableColumn<Empleado, String> columnaNombre;
    @FXML
    private TableColumn<Empleado, Double> columnaSalario;
    @FXML
    private TableColumn<Empleado, String> columnaUno;
    @FXML
    private TableColumn<Empleado, Integer> columnaDos;
    @FXML
    private ComboBox<String> comboTipoEmpleado;
    @FXML
    private TableView<Empleado> tablaEmpleados;

    private EmpleadoDAO empleadoDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleadoDAO = new EmpleadoDAO();
        configurarTablaEmpleados();
        cargarComboTipoEmpleado();
    }

    @FXML
    void accionCancelar(ActionEvent event) {
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.MENU);
    }

    @FXML
    void accionEditar(ActionEvent event) {
        String tipoEmpleado = comboTipoEmpleado.getSelectionModel().getSelectedItem();
        Empleado empleadoSeleccionado = tablaEmpleados.getSelectionModel().getSelectedItem();

        try {

            if (Objects.isNull(empleadoSeleccionado)) {
                AlertaUtil.mostrarAlerta("INFORACION", "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
                return;
            }

            ManejadorDeVistas.getInstancia().limpiarCacheVista(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);

            EmpleadoRegistroController empleadoRegistroController = ManejadorDeVistas.getInstancia().obtenerControlador(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);
            Empleado empleado = tablaEmpleados.getSelectionModel().getSelectedItem();
            empleadoRegistroController.setTipoEmpleado(tipoEmpleado);
            empleadoRegistroController.cargarObjetosPersonalizados();
            empleadoRegistroController.llenarCamposEdicion(empleado);
            empleadoRegistroController.configurarLabelDinamicos();
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);

        } catch (IOException ex) {
            LOG.error(ex);
        }
    }

    @FXML
    void accionEliminar(ActionEvent event) {
        
        if (Objects.isNull(tablaEmpleados.getSelectionModel().getSelectedItem())) {
            
            AlertaUtil.mostrarAlerta(AlertaUtil.INFORMACION, "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
            return;
        }
        Empleado empleado = tablaEmpleados.getSelectionModel().getSelectedItem();
        try {
            if (AlertaUtil.mostrarAlertaEliminar()) {
                empleadoDAO.eliminar(empleado.getIdEmpleado());
                tablaEmpleados.getItems().remove(empleado);
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    @FXML
    void accionRegistrar(ActionEvent event) {
        String tipoEmpleado = comboTipoEmpleado.getSelectionModel().getSelectedItem();
        if (Objects.isNull(tipoEmpleado)) {
            AlertaUtil.mostrarAlerta("INFORMACION", "Debe seleccionar un tipo de empleado.", Alert.AlertType.INFORMATION);
            return;
        }
        ManejadorDeVistas.getInstancia().limpiarCacheVista(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);

        try {
            EmpleadoRegistroController empleadoRegistroController = ManejadorDeVistas.getInstancia().obtenerControlador(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);
            empleadoRegistroController.setTipoEmpleado(tipoEmpleado);
            empleadoRegistroController.cargarObjetosPersonalizados();
            empleadoRegistroController.configurarLabelDinamicos();
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);
        } catch (IOException ex) {
            LOG.error(ex);
        }

    }

    @FXML
    void cargarListaEmpleadosPorTipo(ActionEvent event) {
        
        try {
            String tipoSeleccionado = comboTipoEmpleado.getSelectionModel().getSelectedItem();

            EmpleadoTablaUtil.configurarLabelsSegunTipo(tipoSeleccionado, columnaUno, columnaDos);
            EmpleadoTablaUtil.restablecerTabla(tablaEmpleados);

            if (EmpleadoDatosUtil.TIPO_GERENTE.equals(tipoSeleccionado)) {
                tablaEmpleados.setItems(FXCollections.observableArrayList(empleadoDAO.obtenerGerentes()));
            } else if (EmpleadoDatosUtil.TIPO_EJECUTIVO.equals(tipoSeleccionado)) {
                tablaEmpleados.setItems(FXCollections.observableArrayList(empleadoDAO.obtenerEjecutivos()));
            } else if (EmpleadoDatosUtil.TIPO_CAJERO.equals(tipoSeleccionado)) {
                tablaEmpleados.setItems(FXCollections.observableArrayList(empleadoDAO.obtenerCajeros()));
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    private void cargarComboTipoEmpleado() {
        comboTipoEmpleado.setItems(FXCollections.observableArrayList(EmpleadoDatosUtil.listaTipoEmpleado()));
    }

    private void configurarTablaEmpleados() {
        EmpleadoTablaUtil.configurarColumnasBasicas(columnTipoEmpleado, columnaID, columnaNombre,
                columnaDireccion, columnaFechaNacimiento,
                columnaGenero, columnaSalario);

        EmpleadoTablaUtil.configurarColumnasDinamicas(columnaUno, columnaDos);
    }
}
