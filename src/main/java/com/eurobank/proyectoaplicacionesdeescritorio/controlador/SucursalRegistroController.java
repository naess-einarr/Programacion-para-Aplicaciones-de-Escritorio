
package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.dao.SucursalDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cuenta;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Ejecutivo;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.SucursalDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.ArrayList;
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

public class SucursalRegistroController implements Initializable {
    private static final Logger LOG = LogManager.getLogger(SucursalRegistroController.class);
   
    @FXML
    private ComboBox<Ejecutivo> comboContacto;

    @FXML
    private TextField textCorreo;

    @FXML
    private TextField textDireccion;

    @FXML
    private ComboBox<Gerente> comboGerente;

    @FXML
    private TextField textID;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textTelefono;
    
    private SucursalDAO sucursalDAO;
    private EmpleadoDAO empleadoDAO;
    private Sucursal sucursalEdicion;
    private boolean modoEdicion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sucursalDAO = new SucursalDAO();
        empleadoDAO = new EmpleadoDAO();
        cargarComboGerente();
        cargarComboEjecutivo();
        llenarCampoIdSucursal();
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
            Sucursal sucursal = llenarObjetoSucursal();

            if (modoEdicion) {
                sucursalDAO.actualizar(sucursal);
            } else {
                sucursalDAO.guardar(sucursal);
            }

            ManejadorDeVistas.getInstancia().limpiarCache();
            ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.SUCURSAL);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    public void llenarCamposEdicion(Sucursal sucursal) {
        this.modoEdicion = Boolean.TRUE;
        this.sucursalEdicion = sucursal;
        textID.setText(sucursal.getIdSucursal());
        textNombre.setText(sucursal.getNombreSucursal());
        textDireccion.setText(sucursal.getDireccionSucursal());
        textTelefono.setText(sucursal.getTelefonoSucursal());
        textCorreo.setText(sucursal.getCorreoSucursal());
        comboGerente.getSelectionModel().select(sucursal.getGerente());
        comboContacto.getSelectionModel().select(sucursal.getContacto());
    }
    
    private void cargarComboGerente(){
        try {
            ObservableList<Gerente> items = FXCollections.observableArrayList(empleadoDAO.obtenerGerentes());
            comboGerente.setItems(items);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }
    
    private void cargarComboEjecutivo(){
        try {
            ObservableList<Ejecutivo> items = FXCollections.observableArrayList(empleadoDAO.obtenerEjecutivos());
            comboContacto.setItems(items);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }
    
    private Sucursal llenarObjetoSucursal(){
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(textID.getText().trim());
        sucursal.setNombreSucursal(textNombre.getText().trim());
        sucursal.setDireccionSucursal(textDireccion.getText().trim());
        sucursal.setTelefonoSucursal(textTelefono.getText().trim());
        sucursal.setCorreoSucursal(textCorreo.getText().trim());
        sucursal.setGerente(obtenerDatosGerente(comboGerente.getSelectionModel().getSelectedItem()));
        sucursal.setContacto(obtenerDatosEjecutivo(comboContacto.getSelectionModel().getSelectedItem()));
        if(modoEdicion){
            sucursal.setEmpleadosAsociados(sucursalEdicion.getEmpleadosAsociados());
            sucursal.setCuentasAsociadas(sucursalEdicion.getCuentasAsociadas());
        }else{
            sucursal.setEmpleadosAsociados(new ArrayList<Empleado>());
            sucursal.setCuentasAsociadas(new ArrayList<Cuenta>());
        }
        return sucursal;
    }
    
    private void llenarCampoIdSucursal(){
        if(!modoEdicion){
            String nuevoId;
            try {
                nuevoId = SucursalDatosUtil.PREFIJO_SUCURSAL.concat(String.valueOf(sucursalDAO.obtenerSiguienteId()));
                textID.setText(nuevoId);
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }
    }
    
    private Gerente obtenerDatosGerente(Gerente gerente){
        Gerente gerenteDatos = new Gerente();
        gerenteDatos.setIdEmpleado(gerente.getIdEmpleado());
        gerenteDatos.setNombreCompleto(gerente.getNombreCompleto());
        return gerenteDatos;
    }
    
    private Ejecutivo obtenerDatosEjecutivo(Ejecutivo ejecutivo){
        Ejecutivo ejecutivoDatos = new Ejecutivo();
        ejecutivoDatos.setIdEmpleado(ejecutivo.getIdEmpleado());
        ejecutivoDatos.setNombreCompleto(ejecutivo.getNombreCompleto());
        return ejecutivoDatos;
    }
        
}
