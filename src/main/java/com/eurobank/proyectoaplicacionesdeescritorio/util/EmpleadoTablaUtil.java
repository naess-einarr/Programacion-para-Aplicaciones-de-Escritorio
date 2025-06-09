package com.eurobank.proyectoaplicacionesdeescritorio.util;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cajero;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Ejecutivo;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import java.time.LocalDate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class EmpleadoTablaUtil {
    
    private EmpleadoTablaUtil(){
        throw new IllegalAccessError("Clase utileria");
    }
    
    public static void configurarColumnasBasicas(TableColumn<Empleado, String> columnTipoEmpleado,
                                               TableColumn<Empleado, String> columnaID,
                                               TableColumn<Empleado, String> columnaNombre,
                                               TableColumn<Empleado, String> columnaDireccion,
                                               TableColumn<Empleado, LocalDate> columnaFechaNacimiento,
                                               TableColumn<Empleado, String> columnaGenero,
                                               TableColumn<Empleado, Double> columnaSalario) {
        
        columnTipoEmpleado.setCellValueFactory(new PropertyValueFactory<>("tipoEmpleado"));
        columnaID.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionCompleta"));
        columnaFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnaGenero.setCellValueFactory(new PropertyValueFactory<>("generoEmpleado"));
        columnaSalario.setCellValueFactory(new PropertyValueFactory<>("salarioMensual"));
    }
    
    public static void configurarColumnasDinamicas(TableColumn<Empleado, String> columnaUno,
                                                 TableColumn<Empleado, Integer> columnaDos) {
        
        columnaUno.setCellValueFactory(cellData -> {
            Empleado empleado = cellData.getValue();
            if (empleado instanceof Gerente) {
                return new SimpleStringProperty(((Gerente) empleado).getNivelAcceso());
            } else if (empleado instanceof Ejecutivo) {
                return new SimpleStringProperty(((Ejecutivo) empleado).getEspecializacionEjecutivo());
            } else if (empleado instanceof Cajero) {
                return new SimpleStringProperty(((Cajero) empleado).getHorarioTrabajo());
            }
            return new SimpleStringProperty("");
        });
        
        columnaDos.setCellValueFactory(cellData -> {
            Empleado empleado = cellData.getValue();
            Integer valor = 0;
            if (empleado instanceof Gerente) {
                valor = ((Gerente) empleado).getAniosExperiencia();
            } else if (empleado instanceof Ejecutivo) {
                valor = ((Ejecutivo) empleado).getNumeroClientesAsignados();
            } else if (empleado instanceof Cajero) {
                valor = ((Cajero) empleado).getNumeroVentanilla();
            }
            return new SimpleObjectProperty<>(valor);
        });
        columnaUno.setVisible(Boolean.FALSE);
        columnaDos.setVisible(Boolean.FALSE);
    }
    
    public static void configurarLabelsSegunTipo(String tipoEmpleado, TableColumn columnaUno, TableColumn columnaDos) {
        if (EmpleadoDatosUtil.TIPO_GERENTE.equals(tipoEmpleado)) {
            columnaUno.setText("Nivel de acceso");
            columnaDos.setText("Años de experiencia");
        } else if (EmpleadoDatosUtil.TIPO_EJECUTIVO.equals(tipoEmpleado)) {
            columnaUno.setText("Especialización");
            columnaDos.setText("Clientes asignados");
        } else if (EmpleadoDatosUtil.TIPO_CAJERO.equals(tipoEmpleado)) {
            columnaUno.setText("Horario de trabajo");
            columnaDos.setText("Numero de ventanilla");
        }
    }

    public static void restablecerTabla(TableView<?> table) {
        Callback<TableView.ResizeFeatures, Boolean> oldPolicy = table.getColumnResizePolicy();

        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        for (TableColumn<?, ?> column : table.getColumns()) {
            column.setVisible(false);
            column.setVisible(true);
        }

        table.setColumnResizePolicy(oldPolicy);

        table.refresh();
    }
}