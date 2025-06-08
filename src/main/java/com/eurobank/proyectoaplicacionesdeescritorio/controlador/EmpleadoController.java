package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cajero;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Ejecutivo;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.ComboDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmpleadoController implements Initializable {
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
        try {
            if(Objects.isNull(tablaEmpleados.getSelectionModel().getSelectedItem())){
                AlertaUtil.mostrarAlerta("INFORACION", "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
                return;
            }
            
            ManejadorDeVistas.getInstancia().limpiarCacheVista(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);

            EmpleadoRegistroController empleadoRegistroController = ManejadorDeVistas.getInstancia().obtenerControlador(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);
            Empleado empleado = tablaEmpleados.getSelectionModel().getSelectedItem();
            empleadoRegistroController.setEmpleadoEditar(empleado);
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);

        } catch (IOException ex) {

        }
    }

    @FXML
    void accionEliminar(ActionEvent event) {
        if (Objects.isNull(tablaEmpleados.getSelectionModel().getSelectedItem())) {
            AlertaUtil.mostrarAlerta("INFORMACION", "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
            return;
        }
        Empleado empleado = tablaEmpleados.getSelectionModel().getSelectedItem();
        try {
            if (AlertaUtil.mostrarAlertaEliminar()) {
                empleadoDAO.eliminar(empleado.getIdEmpleado());
            }
        } catch (Exception ex) {

        }
    }

    @FXML
    void accionRegistrar(ActionEvent event) {
        if(Objects.isNull(comboTipoEmpleado.getSelectionModel().getSelectedItem())){
            AlertaUtil.mostrarAlerta("INFORMACION", "Debe seleccionar un tipo de empleado.", Alert.AlertType.INFORMATION);
            return;
        }
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);
    }

    @FXML
    void cargarListaEmpleadosPorTipo(ActionEvent event) {
        try {
            if(ComboDatosUtil.TIPO_GERENTE.equals(comboTipoEmpleado.getSelectionModel().getSelectedItem())){
                tablaEmpleados.setItems(FXCollections.observableArrayList(empleadoDAO.obtenerGerentes()));
            }else if(ComboDatosUtil.TIPO_EJECUTIVO.equals(comboTipoEmpleado.getSelectionModel().getSelectedItem())){
                tablaEmpleados.setItems(FXCollections.observableArrayList(empleadoDAO.obtenerEjecutivos()));
            }else if(ComboDatosUtil.TIPO_CAJERO.equals(comboTipoEmpleado.getSelectionModel().getSelectedItem())){
                tablaEmpleados.setItems(FXCollections.observableArrayList(empleadoDAO.obtenerCajeros()));
            }
        } catch (Exception ex) {

        }
    }
    private void cargarComboTipoEmpleado(){
        comboTipoEmpleado.setItems(FXCollections.observableArrayList(ComboDatosUtil.listaTipoEmpleado()));
    }

    private void configurarTablaEmpleados() {
        columnTipoEmpleado.setCellValueFactory(new PropertyValueFactory<>("tipoEmpleado"));
        columnaID.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionCompleta"));
        columnaFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnaGenero.setCellValueFactory(new PropertyValueFactory<>("generoEmpleado"));
        columnaSalario.setCellValueFactory(new PropertyValueFactory<>("salarioMensual"));

        // Columnas específicas usando Callback
        columnaUno.setCellValueFactory(cellData -> {
            Empleado empleado = cellData.getValue();
            if (empleado instanceof Gerente) {
                return new SimpleStringProperty(((Gerente) empleado).getNivelAcceso());
            } else if (empleado instanceof Ejecutivo) {
                return new SimpleStringProperty(((Ejecutivo) empleado).getEspecializacionEjecutivo());
            } else if (empleado instanceof Cajero) {
                return new SimpleStringProperty(((Cajero) empleado).getHorarioTrabajo());
            }
            return new SimpleStringProperty(""); // Valor por defecto
        });

        columnaDos.setCellValueFactory(cellData -> {
            Empleado empleado = cellData.getValue();
            Integer valor = 0; // valor por defecto

            if (empleado instanceof Gerente) {
                valor = ((Gerente) empleado).getAniosExperiencia();
            } else if (empleado instanceof Ejecutivo) {
                valor = ((Ejecutivo) empleado).getNumeroClientesAsignados();
            } else if (empleado instanceof Cajero) {
                valor = ((Cajero) empleado).getNumeroVentanilla();
            }
            return new SimpleObjectProperty<>(valor);
        });
    }
}
