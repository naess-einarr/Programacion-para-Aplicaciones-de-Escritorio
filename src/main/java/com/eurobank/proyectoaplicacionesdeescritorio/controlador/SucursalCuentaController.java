package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.CuentaDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cuenta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SucursalCuentaController implements Initializable {
    @FXML
    private TableColumn<?, ?> columnaCuenta;

    @FXML
    private TableColumn<?, ?> columnaCuentaAsociada;

    @FXML
    private TableColumn<?, ?> columnaNombre;

    @FXML
    private TableColumn<?, ?> columnaNombreAsociado;
    
    @FXML
    private TableView<Cuenta> tablaCuentasAsociadas;

    @FXML
    private TableView<Cuenta> tablaCuentasDisponibles;
    
    private CuentaDAO cuentaDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cuentaDAO = new CuentaDAO();
    }    
    
    @FXML
    void accionAsociarTodos(ActionEvent event) {

    }

    @FXML
    void accionAsociarUno(ActionEvent event) {

    }

    @FXML
    void accionCancelar(ActionEvent event) {

    }

    @FXML
    void accionGuardar(ActionEvent event) {

    }

    @FXML
    void accionRegresarTodos(ActionEvent event) {

    }

    @FXML
    void accionRegresarUno(ActionEvent event) {

    }
}
