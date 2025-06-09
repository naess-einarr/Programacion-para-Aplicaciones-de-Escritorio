package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.EmpleadoDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.dao.SucursalDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeSesion;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginController implements Initializable {
        private static final Logger LOG = LogManager.getLogger(LoginController.class);
        
        @FXML
        private PasswordField textContrasena;

        @FXML
        private TextField textUsuario;
        
        @FXML
        private Button botonIniciarSesion;
        
        private EmpleadoDAO empleadoDAO;
        private SucursalDAO sucursalDAO;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            empleadoDAO = new EmpleadoDAO();
            sucursalDAO = new SucursalDAO();
            botonIniciarSesion.setDefaultButton(true);
        }

        @FXML
        void cancelarInicioSesion(ActionEvent event) {
            ManejadorDeSesion.cerrarSesion();
            Stage escenarioPrincipal = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ManejadorDeVistas.getInstancia().setEscenarioPrincipal(escenarioPrincipal);
            ManejadorDeVistas.getInstancia().cerrarAplicacion();

        }

        @FXML
        void inicioSesion(ActionEvent event) {
            String usuario = textUsuario.getText().trim();
            String contrasena = textContrasena.getText().trim();
            Empleado empleado;
            try {
                empleado = empleadoDAO.validarCredenciales(usuario, contrasena);
                if(Objects.nonNull(empleado)){
                    ManejadorDeSesion.iniciarSesion(empleado);
                    ManejadorDeSesion.setSucursalActual(sucursalDAO.buscarSucursalPorIdEmpleado(empleado.getIdEmpleado()));
                    MenuController menuController = ManejadorDeVistas.getInstancia().obtenerControlador(ManejadorDeVistas.Vista.MENU);
                    menuController.cargarDatosMenuEmpleado();
                    ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.MENU);

                }else{
                    AlertaUtil.mostrarAlerta("Error", "Datos inválidos", Alert.AlertType.ERROR);
                }
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }

  }
