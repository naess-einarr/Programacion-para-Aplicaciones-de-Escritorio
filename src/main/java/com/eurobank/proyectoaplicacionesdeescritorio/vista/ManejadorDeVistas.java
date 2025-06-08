package com.eurobank.proyectoaplicacionesdeescritorio.vista;

import com.eurobank.proyectoaplicacionesdeescritorio.util.AlertaUtil;
import com.eurobank.proyectoaplicacionesdeescritorio.util.ConstantesUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Alert;

public class ManejadorDeVistas {

    public enum Vista {
        LOGIN("/vistas/login.fxml", false),
        MENU("/vistas/menu.fxml", false),
        CLIENTE("/vistas/cliente.fxml", true),
        CUENTA("/vistas/cuenta.fxml", true),
        EMPLEADO("/vistas/empleado.fxml", true),
        SUCURSAL("/vistas/sucursal.fxml", true),
        TRANSACCION("/vistas/transaccion.fxml", true),
        CLIENTE_REGISTRO("/vistas/clienteregistro.fxml", false),
        EMPLEADO_REGISTRO("/vistas/empleadoregistro.fxml", false),
        SUCURSAL_SELECCION("/vistas/sucursalseleccion.fxml", false);

        private final String rutaFXML;
        private final boolean resizable;

        Vista(String rutaFXML, boolean resizable) {
            this.rutaFXML = rutaFXML;
            this.resizable = resizable;
        }

        public String getRutaFXML() {
            return rutaFXML;
        }

        public boolean isResizable() {
            return resizable;
        }
    }

    private static ManejadorDeVistas instancia;
    private Stage escenarioPrincipal;
    private Map<Vista, Parent> vistasCache;
    private Map<Vista, Object> controladoresCache;

    // Constructor privado para patrón Singleton
    private ManejadorDeVistas() {
        this.vistasCache = new HashMap<>();
        this.controladoresCache = new HashMap<>();
    }

    // Método para obtener la instancia única
    public static ManejadorDeVistas getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorDeVistas();
        }
        return instancia;
    }

    // Establecer el escenario principal
    public void setEscenarioPrincipal(Stage escenario) {
        this.escenarioPrincipal = escenario;
    }

    // Cargar una vista FXML
    public Parent cargarVista(Vista vista) throws IOException {
        if (vistasCache.containsKey(vista)) {
            return vistasCache.get(vista);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista.getRutaFXML()));
            Parent root = loader.load();

            // Guardar tanto la vista como el controlador
            vistasCache.put(vista, root);
            controladoresCache.put(vista, loader.getController());

            return root;
        } catch (IOException e) {
            System.err.println("Error al cargar la vista: " + vista.name());
            throw e;
        }
    }

    public void cambiarVista(Vista vista) {
        cambiarVista(vista, "EuroBank - " + vista.name());
    }

    public void cambiarVista(Vista vista, String titulo) {
        try {
            Parent root = cargarVista(vista);
            Scene escena = new Scene(root);
            
            if (escenarioPrincipal != null) {
                
                // Restaurar ventana a su estado original antes del cambio
                // Ocultar ventana temporalmente
                escenarioPrincipal.hide();
                if (escenarioPrincipal.isMaximized()) {
                    escenarioPrincipal.setMaximized(false);
                }
                escenarioPrincipal.sizeToScene();
                // Se asignan valores para la siguiente ventana
                escenarioPrincipal.setScene(escena);
                escenarioPrincipal.setTitle(titulo);
                escenarioPrincipal.setResizable(vista.isResizable());
                
                // Centrar la ventana en la pantalla
                escenarioPrincipal.centerOnScreen();
                
                escenarioPrincipal.show();
            }
        } catch (IOException e) {
            System.err.println("Error al cambiar a la vista: " + vista.name());
            e.printStackTrace();
        }
    }

    // Obtener el controlador de una vista específica
    public <T> T obtenerControlador(Vista vista) throws IOException {
        // Si ya está en caché, devolver el controlador cacheado
        if (controladoresCache.containsKey(vista)) {
            return (T) controladoresCache.get(vista);
        }

        // Si no está en caché, cargar la vista (esto también guardará el controlador)
        cargarVista(vista);
        return (T) controladoresCache.get(vista);
    }

    // Limpiar caché de vistas
    public void limpiarCache() {
        vistasCache.clear();
        controladoresCache.clear();
    }

    // Limpiar caché de una vista específica
    public void limpiarCacheVista(Vista vista) {
        vistasCache.remove(vista);
        controladoresCache.remove(vista);
    }

    // Verificar si una vista está en caché
    public boolean estaEnCache(Vista vista) {
        return vistasCache.containsKey(vista);
    }

    // Cerrar la aplicación
    public void cerrarAplicacion() {
        if (escenarioPrincipal != null) {
            escenarioPrincipal.close();
        }
    }

    // Obtener el escenario principal
    public Stage obtenerEscenarioPrincipal() {
        return escenarioPrincipal;
    }
    
        // Abrir una vista en una nueva ventana
    public Stage abrirVistaEnNuevaVentana(Vista vista) {
        try {
            Parent root = cargarVista(vista);
            Scene escena = new Scene(root);

            Stage nuevaVentana = new Stage();
            nuevaVentana.setScene(escena);
            nuevaVentana.setTitle("EuroBank - " + vista.name());
            nuevaVentana.setResizable(vista.isResizable());
            nuevaVentana.showAndWait();

            return nuevaVentana;
        } catch (IOException ioe) {
            AlertaUtil.mostrarAlerta(ConstantesUtil.ERROR, "No se pudo abrir la ventana", Alert.AlertType.ERROR);

            return null;
        }
    }

}