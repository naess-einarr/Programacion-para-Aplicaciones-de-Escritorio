
package com.eurobank.proyectoaplicacionesdeescritorio.dao;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import com.eurobank.proyectoaplicacionesdeescritorio.util.JsonUtil;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase DAO para gestionar la persistencia de las sucursales.
 * Implementa operaciones CRUD usando archivos JSON como almacenamiento.
 */
public class SucursalDAO implements GenericDAO<Sucursal> {
    
    private static final String ARCHIVO_SUCURSALES = "data/sucursales.json";
    private JsonUtil<Sucursal> jsonUtil;
    
    public SucursalDAO() {
        this.jsonUtil = new JsonUtil<>();
    }
    
    @Override
    public void guardar(Sucursal sucursal) throws Exception {
        if (sucursal == null) {
            throw new IllegalArgumentException("La sucursal no puede ser nula");
        }
        
        List<Sucursal> sucursales = obtenerTodos();
        
        // Verificar si ya existe una sucursal con el mismo ID
        if (buscarPorId(sucursal.getIdSucursal()) != null) {
            throw new Exception("Ya existe una sucursal con el ID: " + sucursal.getIdSucursal());
        }
        
        sucursales.add(sucursal);
        jsonUtil.guardarLista(sucursales, ARCHIVO_SUCURSALES);
    }
    
    @Override
    public Sucursal buscarPorId(String idSucursal) throws Exception {
        List<Sucursal> sucursales = obtenerTodos();
        
        return sucursales.stream()
                .filter(sucursal -> sucursal.getIdSucursal().equals(idSucursal))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Sucursal> obtenerTodos() throws Exception {
        try {
            return jsonUtil.cargarLista(ARCHIVO_SUCURSALES, Sucursal.class);
        } catch (Exception e) {
            // Si el archivo no existe, retornar lista vacía
            return new ArrayList<>();
        }
    }
    
    @Override
    public void actualizar(Sucursal sucursalActualizada) throws Exception {
        if (sucursalActualizada == null) {
            throw new IllegalArgumentException("La sucursal no puede ser nula");
        }
        
        List<Sucursal> sucursales = obtenerTodos();
        boolean encontrada = false;
        
        for (int i = 0; i < sucursales.size(); i++) {
            if (sucursales.get(i).getIdSucursal().equals(sucursalActualizada.getIdSucursal())) {
                sucursales.set(i, sucursalActualizada);
                encontrada = true;
                break;
            }
        }
        
        if (!encontrada) {
            throw new Exception("No se encontró la sucursal con ID: " + sucursalActualizada.getIdSucursal());
        }
        
        jsonUtil.guardarLista(sucursales, ARCHIVO_SUCURSALES);
    }
    
    @Override
    public void eliminar(String idSucursal) throws Exception {
        List<Sucursal> sucursales = obtenerTodos();
        boolean eliminada = sucursales.removeIf(sucursal -> sucursal.getIdSucursal().equals(idSucursal));
        
        if (!eliminada) {
            throw new Exception("No se encontró la sucursal con ID: " + idSucursal);
        }
        
        jsonUtil.guardarLista(sucursales, ARCHIVO_SUCURSALES);
    }
    
    @Override
    public boolean existe(String idSucursal) throws Exception {
        return buscarPorId(idSucursal) != null;
    }
    
    /**
     * Busca sucursales por nombre (búsqueda parcial).
     * @param nombreSucursal Nombre o parte del nombre a buscar
     * @return Lista de sucursales que coinciden con el criterio
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<Sucursal> buscarPorNombre(String nombreSucursal) throws Exception {
        List<Sucursal> todasLasSucursales = obtenerTodos();
        List<Sucursal> resultados = new ArrayList<>();
        
        for (Sucursal sucursal : todasLasSucursales) {
            if (sucursal.getNombreSucursal().toLowerCase().contains(nombreSucursal.toLowerCase())) {
                resultados.add(sucursal);
            }
        }
        
        return resultados;
    }
    
    public int obtenerSiguienteId() throws Exception {
        List<Sucursal> sucursales = obtenerTodos();

        if (sucursales.isEmpty()) {
            return 1;
        }

        int maxNumero = sucursales.stream()
                .mapToInt(sucursal -> {
                    String id = sucursal.getIdSucursal();
                    if (id != null && id.startsWith("SUC")) {
                        try {
                            return Integer.parseInt(id.substring(3));
                        } catch (NumberFormatException e) {
                            return 0;
                        }
                    }
                    return 0;
                })
                .max()
                .orElse(0);

        return maxNumero + 1;
    }

    public Sucursal buscarSucursalPorIdEmpleado(String idEmpleado) throws Exception {
    List<Sucursal> todasLasSucursales = obtenerTodos(); // tu método actual que trae todas las sucursales

    for (Sucursal sucursal : todasLasSucursales) {
        // Comparar con el gerente
        if (sucursal.getGerente() != null && 
            idEmpleado.equalsIgnoreCase(sucursal.getGerente().getIdEmpleado())) {
            return sucursal;
        }

        // Comparar con el contacto
        if (sucursal.getContacto() != null && 
            idEmpleado.equalsIgnoreCase(sucursal.getContacto().getIdEmpleado())) {
            return sucursal;
        }

        // Buscar en empleados asociados
        if (sucursal.getEmpleadosAsociados() != null) {
            boolean estaAsociado = sucursal.getEmpleadosAsociados().stream()
                .anyMatch(e -> idEmpleado.equalsIgnoreCase(e.getIdEmpleado()));

            if (estaAsociado) {
                return sucursal;
            }
        }
    }

    return null; 
}

}
