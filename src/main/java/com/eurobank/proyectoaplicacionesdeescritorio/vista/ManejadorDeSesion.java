package com.eurobank.proyectoaplicacionesdeescritorio.vista;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;

public class ManejadorDeSesion {

    private static Empleado empleadoActual;
    private static Sucursal sucursalActual;

    private ManejadorDeSesion(){
        throw new IllegalAccessError("Clase de utileria..");
    }
    public static void iniciarSesion(Empleado empleado) {
        empleadoActual = empleado;
    }

    public static Empleado obtenerEmpleado() {
        return empleadoActual;
    }

    public static void cerrarSesion() {
        empleadoActual = null;
        sucursalActual = null;
    }

    public static void setSucursalActual(Sucursal sucursal) {
        sucursalActual = sucursal;
    }

    public static Sucursal getSucursalActual() {
        return sucursalActual;
    }

    public static boolean haySesionActiva() {
        return empleadoActual != null;
    }
}
