package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cajero;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Ejecutivo;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.ConstantesUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.EmpleadoDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.EmpleadoTablaUtil;

import com.eurobank.proyectoaplicacionesdeescritorio.util.ExportadorGenerico;

import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeSesion;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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
                AlertaUtil.mostrarAlerta(AlertaUtil.ADVERTENCIA, "Debe seleccionar un registro", Alert.AlertType.INFORMATION);
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
            LOG.error(ConstantesUtil.LOG_ERROR_ARCHIVO);
            AlertaUtil.mostrarAlertaRegistroFallido();
        }

    }

    @FXML
    void cargarListaEmpleadosPorTipo(ActionEvent event) {
        
        try {
            String tipoSeleccionado = comboTipoEmpleado.getSelectionModel().getSelectedItem();

            EmpleadoTablaUtil.configurarLabelsSegunTipo(tipoSeleccionado, columnaUno, columnaDos);
            EmpleadoTablaUtil.restablecerTabla(tablaEmpleados);
            
            Empleado empleado = ManejadorDeSesion.obtenerEmpleado();
            if(empleado instanceof Gerente){
                if(EmpleadoDatosUtil.NIVEL_NACIONAL.equals(((Gerente)empleado).getNivelAcceso())){
                    tablaGerenteNacional(tipoSeleccionado);
                }else{
                    tablaEmpleadosGeneral(tipoSeleccionado);
                }
            }else{
                tablaEmpleadosGeneral(tipoSeleccionado);
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }
    
    private void tablaEmpleadosGeneral(String tipoSeleccionado) throws Exception {
        if (EmpleadoDatosUtil.TIPO_GERENTE.equals(tipoSeleccionado)) {
            tablaEmpleados.setItems(FXCollections.observableArrayList(cargarGerentes()));
        } else if (EmpleadoDatosUtil.TIPO_EJECUTIVO.equals(tipoSeleccionado)) {
            tablaEmpleados.setItems(FXCollections.observableArrayList(cargarEjecutivos()));
        } else if (EmpleadoDatosUtil.TIPO_CAJERO.equals(tipoSeleccionado)) {
            tablaEmpleados.setItems(FXCollections.observableArrayList(cargarCajeros()));
        }        
    }
    
    private void tablaGerenteNacional(String tipoSeleccionado) throws Exception {
        if (EmpleadoDatosUtil.TIPO_GERENTE.equals(tipoSeleccionado)) {
            tablaEmpleados.setItems(FXCollections.observableArrayList(empleadoDAO.obtenerGerentes()));
        } else if (EmpleadoDatosUtil.TIPO_EJECUTIVO.equals(tipoSeleccionado)) {
            tablaEmpleados.setItems(FXCollections.observableArrayList(empleadoDAO.obtenerEjecutivos()));
        } else if (EmpleadoDatosUtil.TIPO_CAJERO.equals(tipoSeleccionado)) {
            tablaEmpleados.setItems(FXCollections.observableArrayList(empleadoDAO.obtenerCajeros()));
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
    
    private List<Empleado> cargarListaEmpleadosPorSesion(String tipo) throws Exception {
        List<Empleado> items;
        Empleado empleado = ManejadorDeSesion.obtenerEmpleado();
        Sucursal sucursal = ManejadorDeSesion.getSucursalActual();

        if (empleado instanceof Gerente) {
            if (EmpleadoDatosUtil.NIVEL_NACIONAL.equals(((Gerente) empleado).getNivelAcceso())) {
                items = empleadoDAO.obtenerTodos();
            } else {
                items = complementarEmpleado(sucursal.getEmpleadosAsociados());
            }
        } else {
                items = complementarEmpleado(sucursal.getEmpleadosAsociados());
        }

        if (tipo != null && !tipo.trim().isEmpty()) {
            items = items.stream()
                    .filter(emp -> tipo.equalsIgnoreCase(emp.getTipoEmpleado()))
                    .collect(Collectors.toList());
        }

        return items;
    }
    
    private List<Empleado> complementarEmpleado(List<Empleado> lista) throws Exception{
        List<Empleado> complementado = new ArrayList<>();
        for(Empleado empleado : lista){
            complementado.add(empleadoDAO.buscarPorId(empleado.getIdEmpleado()));
        }
        return complementado;
    }

    private List<Gerente> cargarGerentes() throws Exception {
        List<Empleado> empleados = cargarListaEmpleadosPorSesion("GERENTE");
        return empleados.stream()
                .filter(emp -> emp instanceof Gerente)
                .map(emp -> (Gerente) emp)
                .collect(Collectors.toList());
    }

    private List<Ejecutivo> cargarEjecutivos() throws Exception {
        List<Empleado> empleados = cargarListaEmpleadosPorSesion("EJECUTIVO");
        return empleados.stream()
                .filter(emp -> emp instanceof Ejecutivo)
                .map(emp -> (Ejecutivo) emp)
                .collect(Collectors.toList());
    }

    private List<Cajero> cargarCajeros() throws Exception {
        List<Empleado> empleados = cargarListaEmpleadosPorSesion("CAJERO");
        return empleados.stream()
                .filter(emp -> emp instanceof Cajero)
                .map(emp -> (Cajero) emp)
                .collect(Collectors.toList());
    }
    
    public void accionExportar() {
        String tipoEmpleado = ManejadorDeSesion.obtenerEmpleado().getTipoEmpleado();

        configurarTablaEmpleados();
        tablaEmpleados.refresh();

        ExportadorGenerico.exportar(tablaEmpleados.getItems(),
                ExportadorGenerico.TipoExportacion.EXCEL_XLS,
                ManejadorDeVistas.getInstancia().obtenerEscenarioPrincipal(),
                "empleados_" + tipoEmpleado + LocalDate.now() + ".xls");
    }

}
