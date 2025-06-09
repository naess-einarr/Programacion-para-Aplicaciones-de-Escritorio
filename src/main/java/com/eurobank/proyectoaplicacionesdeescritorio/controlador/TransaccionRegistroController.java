package com.eurobank.proyectoaplicacionesdeescritorio.controlador;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.CuentaDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.dao.TransaccionDAO;
import com.eurobank.proyectoaplicacionesdeescritorio.excepciones.SaldoInsuficienteException;
import com.eurobank.proyectoaplicacionesdeescritorio.excepciones.TransaccionFallidaException;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cuenta;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Transaccion;
import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.ConstantesUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.TransaccionDatosUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeSesion;
import com.eurobank.proyectoaplicacionesdeescritorio.vista.ManejadorDeVistas;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransaccionRegistroController implements Initializable {

    private static final Logger LOG = LogManager.getLogger(TransaccionRegistroController.class);

    @FXML
    private ComboBox<Cuenta> comboCuentaDestino;

    @FXML
    private ComboBox<Cuenta> comboCuentaOrigen;

    @FXML
    private ComboBox<String> comboTipoTransaccion;

    @FXML
    private TextField textIDTransaccion;

    @FXML
    private TextField textMonto;

    @FXML
    private TextField textSucursal;

    @FXML
    private GridPane gridPane;

    CuentaDAO cuentaDAO;
    TransaccionDAO transaccionDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cuentaDAO = new CuentaDAO();
        transaccionDAO = new TransaccionDAO();
        comboTipoTransaccion.setItems(TransaccionDatosUtil.listaTipoTransaccion());
        cargarComboCuentas();
        cargarComboTipoTransaccion();
        generarIDTransaccion();
        cargarSucursalEmpleado();
    }

    @FXML
    public void realizarTransaccion() {
        Transaccion transaccion = new Transaccion();
        transaccion.setIdTransaccion(textIDTransaccion.getText().trim());

        String tipoTransaccion = comboTipoTransaccion.getSelectionModel().getSelectedItem();
        transaccion.setTipoTransaccion(tipoTransaccion);
        Sucursal sucursalActual = ManejadorDeSesion.getSucursalActual();
        transaccion.setSucursal(sucursalActual);
        Empleado empleadoActual = ManejadorDeSesion.obtenerEmpleado();
        Double montoTransaccion = Double.valueOf(textMonto.getText().trim());
        transaccion.setMontoTransaccion(montoTransaccion);
        transaccion.setCuentaOrigen(comboCuentaOrigen.getSelectionModel().getSelectedItem());
        transaccion.setCuentaDestino(comboCuentaDestino.getSelectionModel().getSelectedItem());
        transaccion.setEmpleadoResponsable(empleadoActual);
        transaccion.setFechaHoraTransaccion(LocalDateTime.now());
        try {
            if (validaTransaccion(transaccion)) {
                ejecutaTransaccion(transaccion);
                transaccionDAO.guardar(transaccion);
                ManejadorDeVistas.getInstancia().limpiarCache();
                ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.TRANSACCION);
            }
        } catch (Exception ex) {
            AlertaUtil.mostrarAlerta(AlertaUtil.ERROR, "Error en la transaccion", Alert.AlertType.ERROR);
           LOG.error(ex);
        }
        
    }
       
    private boolean validaTransaccion(Transaccion transaccion) throws Exception{
        switch (transaccion.getTipoTransaccion()) {
            case TransaccionDatosUtil.TIPO_DEPOSITO:
            return cuentaDAO.buscarPorId(transaccion.getCuentaDestino().getNumeroCuenta()) != null;
            
            case TransaccionDatosUtil.TIPO_RETIRO:
            Cuenta cuentaOrigen = cuentaDAO.buscarPorId(transaccion.getCuentaOrigen().getNumeroCuenta());
            Double resultado = cuentaOrigen.getSaldoActual() - transaccion.getMontoTransaccion();
            return validaSaldo(resultado);
            
            case TransaccionDatosUtil.TIPO_TRANSFERENCIA:
            Cuenta origen = cuentaDAO.buscarPorId(transaccion.getCuentaOrigen().getNumeroCuenta());
            Double restante = origen.getSaldoActual() - transaccion.getMontoTransaccion();
            return validaSaldo(restante) && cuentaDAO.buscarPorId(transaccion.getCuentaDestino().getNumeroCuenta()) != null;   

            default:
                throw new AssertionError();
        }
    }
    
    public boolean validaSaldo(Double saldo) throws SaldoInsuficienteException{
        if(saldo >= 0){
            return true;
        }else{
            AlertaUtil.mostrarAlerta(AlertaUtil.ADVERTENCIA, "Saldo insuficiente en cuenta origen", Alert.AlertType.WARNING);
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
    }
    
    private void ejecutaTransaccion(Transaccion transaccion) throws Exception {
        Cuenta cuentaOrigen;
        Cuenta cuentaDestino;
        Double saldoActual;
        Double montoTransaccion;
        Double saldoFinal;
        switch (transaccion.getTipoTransaccion()) {
            case TransaccionDatosUtil.TIPO_DEPOSITO:
                cuentaDestino = cuentaDAO.buscarPorId(transaccion.getCuentaDestino().getNumeroCuenta());
                saldoActual = cuentaDestino.getSaldoActual();
                montoTransaccion = transaccion.getMontoTransaccion();
                saldoFinal = saldoActual + montoTransaccion;
                cuentaDestino.setSaldoActual(saldoFinal);
                cuentaDAO.actualizar(cuentaDestino);
            break;

            case TransaccionDatosUtil.TIPO_RETIRO:
                cuentaOrigen = cuentaDAO.buscarPorId(transaccion.getCuentaOrigen().getNumeroCuenta());
                saldoFinal = cuentaOrigen.getSaldoActual() - transaccion.getMontoTransaccion();
                cuentaOrigen.setSaldoActual(saldoFinal);
                cuentaDAO.actualizar(cuentaOrigen);
            break;

            case TransaccionDatosUtil.TIPO_TRANSFERENCIA:
                cuentaOrigen = cuentaDAO.buscarPorId(transaccion.getCuentaOrigen().getNumeroCuenta());
                saldoFinal = cuentaOrigen.getSaldoActual() - transaccion.getMontoTransaccion();
                cuentaOrigen.setSaldoActual(saldoFinal);
                cuentaDAO.actualizar(cuentaOrigen);
                
                cuentaDestino = cuentaDAO.buscarPorId(transaccion.getCuentaDestino().getNumeroCuenta());
                saldoActual = cuentaDestino.getSaldoActual();
                montoTransaccion = transaccion.getMontoTransaccion();
                saldoFinal = saldoActual + montoTransaccion;
                cuentaDestino.setSaldoActual(saldoFinal);
                cuentaDAO.actualizar(cuentaDestino);

            break;
            default:
                throw new TransaccionFallidaException("Error al ejecutar transacción");
        }
    }

    @FXML
    void ajustaTipoTransaccion(ActionEvent event) {
        final String tipo = comboTipoTransaccion.getSelectionModel().getSelectedItem();
        restaurarTodasLasFilas();

        if(tipo.equals(TransaccionDatosUtil.TIPO_DEPOSITO)){
            mostrarFila(3, true);
            mostrarFila(4, false); 
            comboCuentaOrigen.getSelectionModel().clearSelection();
        }else if(tipo.equals(TransaccionDatosUtil.TIPO_RETIRO)){
            mostrarFila(3, false);
            mostrarFila(4, true);
            comboCuentaDestino.getSelectionModel().clearSelection();
        }else{
            restaurarTodasLasFilas();
        }
    }

    private void cargarComboTipoTransaccion() {
        comboTipoTransaccion.setItems(TransaccionDatosUtil.listaTipoTransaccion());
    }

    private void cargarComboCuentas() {
            List<Cuenta> cuentasDisponiblesSucursal = ManejadorDeSesion.getSucursalActual() != null ? 
                    ManejadorDeSesion.getSucursalActual().getCuentasAsociadas() :
                    new ArrayList<>();
            List<Cuenta> cuentasComplementadas = new ArrayList<>();
            for(Cuenta cuenta : cuentasDisponiblesSucursal){
                try {
                    cuentasComplementadas.add(cuentaDAO.buscarPorId(cuenta.getNumeroCuenta()));
                } catch (Exception ex) {
                    LOG.error(ex);
                }
            }
            ObservableList<Cuenta> items = FXCollections.observableArrayList(cuentasComplementadas);
            comboCuentaOrigen.setItems(items);
            comboCuentaDestino.setItems(items);
    }

    private void generarIDTransaccion() {
        String idTransaccion = UUID.randomUUID().toString().toUpperCase();
        textIDTransaccion.setText(idTransaccion);
    }

    @FXML
    private void cancelarTransaccion() {
        ManejadorDeVistas.getInstancia().limpiarCache();
        ManejadorDeVistas.getInstancia().cambiarVista(ManejadorDeVistas.Vista.TRANSACCION);
    }

    private void cargarSucursalEmpleado() {
        try {
            if (ManejadorDeSesion.haySesionActiva()) {
                Sucursal sucursalActual = ManejadorDeSesion.getSucursalActual();

                if (sucursalActual != null) {
                    textSucursal.setText(sucursalActual.toString());
                }
            }
        } catch (Exception ex) {
            LOG.error("Error al cargar sucursal del empleado", ex);
        }
    }

    private void restaurarTodasLasFilas() {
        for (Node node : gridPane.getChildren()) {
            if(node.isDisable()){
                node.setDisable(false);
                node.setManaged(true);
            }

        }
    }

    private void mostrarFila(int numeroFila, boolean mostrar) {
        for (Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            int fila = (rowIndex != null) ? rowIndex : 0;
            
            if (fila == numeroFila) {
                node.setDisable(mostrar);
                node.setManaged(mostrar);
            }
        }
    }

}
