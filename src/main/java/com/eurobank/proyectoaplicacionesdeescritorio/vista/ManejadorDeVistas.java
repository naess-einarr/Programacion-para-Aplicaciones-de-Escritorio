package com.eurobank.proyectoaplicacionesdeescritorio.vista;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ManejadorDeVistas {
    
    // Enum para definir las vistas disponibles
    public enum Vista {
        LOGIN("/vistas/login.fxml"),
        MENU("/vistas/menu.fxml"),
        CLIENTE("/vistas/cliente.fxml"),
        CUENTA("/vistas/cuenta.fxml"),
        EMPLEADO("/vistas/empleado.fxml"),
        SUCURSAL("/vistas/sucursal.fxml"),
        TRANSACCION("/vistas/transaccion.fxml");
        
        private final String rutaFXML;
        
        Vista(String rutaFXML) {
            this.rutaFXML = rutaFXML;
        }
        
        public String getRutaFXML() {
            return rutaFXML;
        }
    }
    
    private static ManejadorDeVistas instancia;
    private Stage escenarioPrincipal;
    private Map<Vista, Parent> vistasCache;
    
    // Constructor privado para patrón Singleton
    private ManejadorDeVistas() {
        this.vistasCache = new HashMap<>();
    }
    
    // Método para obtener la instancia única
    public static ManejadorDeVistas obtenerInstancia() {
        if (instancia == null) {
            instancia = new ManejadorDeVistas();
        }
        return instancia;
    }
    
    // Establecer el escenario principal
    public void establecerEscenarioPrincipal(Stage escenario) {
        this.escenarioPrincipal = escenario;
    }
    
    // Cargar una vista FXML
    public Parent cargarVista(Vista vista) throws IOException {
        // Verificar si la vista ya está en caché
        if (vistasCache.containsKey(vista)) {
            return vistasCache.get(vista);
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista.getRutaFXML()));
            Parent root = loader.load();
            
            // Guardar en caché para futuras cargas
            vistasCache.put(vista, root);
            
            return root;
        } catch (IOException e) {
            System.err.println("Error al cargar la vista: " + vista.name());
            throw e;
        }
    }
    
    // Cambiar a una vista específica
    public void cambiarVista(Vista vista) {
        cambiarVista(vista, "EuroBank - " + vista.name());
    }
    
    // Cambiar a una vista específica con título personalizado
    public void cambiarVista(Vista vista, String titulo) {
        try {
            Parent root = cargarVista(vista);
            Scene escena = new Scene(root);
            
            if (escenarioPrincipal != null) {
                escenarioPrincipal.setScene(escena);
                escenarioPrincipal.setTitle(titulo);
                escenarioPrincipal.show();
            }
        } catch (IOException e) {
            System.err.println("Error al cambiar a la vista: " + vista.name());
            e.printStackTrace();
        }
    }
    
    // Abrir una vista en una nueva ventana
    public Stage abrirVistaEnNuevaVentana(Vista vista, String titulo) {
        try {
            Parent root = cargarVista(vista);
            Scene escena = new Scene(root);
            
            Stage nuevaVentana = new Stage();
            nuevaVentana.setScene(escena);
            nuevaVentana.setTitle(titulo);
            nuevaVentana.show();
            
            return nuevaVentana;
        } catch (IOException e) {
            System.err.println("Error al abrir nueva ventana para la vista: " + vista.name());
            e.printStackTrace();
            return null;
        }
    }
    
    // Obtener el controlador de una vista específica
    public <T> T obtenerControlador(Vista vista) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(vista.getRutaFXML()));
        loader.load();
        return loader.getController();
    }
    
    // Limpiar caché de vistas
    public void limpiarCache() {
        vistasCache.clear();
    }
    
    // Limpiar caché de una vista específica
    public void limpiarCacheVista(Vista vista) {
        vistasCache.remove(vista);
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
}