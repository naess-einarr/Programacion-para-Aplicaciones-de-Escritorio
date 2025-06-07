package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmpleadoController implements Initializable {
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
    private TableView<Empleado> tablaEmpleados;

    private EmpleadoDAO empleadoDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleadoDAO = new EmpleadoDAO();
        configurarTablaEmpleados();
        cargarListaEmpleados();
    }

    @FXML
    void accionCancelar(ActionEvent event) {
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.MENU);
    }

    @FXML
    void accionEditar(ActionEvent event) {

    }

    @FXML
    void accionEliminar(ActionEvent event) {

    }

    @FXML
    void accionRegistrar(ActionEvent event) {
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.EMPLEADO_REGISTRO);
    }

    private void cargarListaEmpleados() {
        try {
            tablaEmpleados.setItems(FXCollections.observableArrayList(empleadoDAO.obtenerTodos()));
        } catch (Exception ex) {

        }
    }

    private void configurarTablaEmpleados() {
        columnaID.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionCompleta"));
        columnaFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnaGenero.setCellValueFactory(new PropertyValueFactory<>("generoEmpleado"));
        columnaSalario.setCellValueFactory(new PropertyValueFactory<>("salarioMensual"));
    }

}
