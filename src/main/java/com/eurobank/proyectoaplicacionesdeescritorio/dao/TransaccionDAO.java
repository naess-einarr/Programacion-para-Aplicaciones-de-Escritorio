package com.eurobank.proyectoaplicacionesdeescritorio.dao;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Transaccion;
import com.eurobank.proyectoaplicacionesdeescritorio.util.JsonUtil;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Clase DAO para gestionar la persistencia de las transacciones.
 * Implementa operaciones CRUD usando archivos JSON como almacenamiento.
 */
public class TransaccionDAO implements GenericDAO<Transaccion> {
    
    private static final String ARCHIVO_TRANSACCIONES = "data/transacciones.json";
    private JsonUtil<Transaccion> jsonUtil;
    
    public TransaccionDAO() {
        this.jsonUtil = new JsonUtil<>();
    }
    
    @Override
    public void guardar(Transaccion transaccion) throws Exception {
        if (transaccion == null) {
            throw new IllegalArgumentException("La transacción no puede ser nula");
        }
        
        List<Transaccion> transacciones = obtenerTodos();
        
        // Verificar si ya existe una transacción con el mismo ID
        if (buscarPorId(transaccion.getIdTransaccion()) != null) {
            throw new Exception("Ya existe una transacción con el ID: " + transaccion.getIdTransaccion());
        }
        
        transacciones.add(transaccion);
        jsonUtil.guardarLista(transacciones, ARCHIVO_TRANSACCIONES);
    }
    
    @Override
    public Transaccion buscarPorId(String idTransaccion) throws Exception {
        List<Transaccion> transacciones = obtenerTodos();
        
        return transacciones.stream()
                .filter(transaccion -> transaccion.getIdTransaccion().equals(idTransaccion))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Transaccion> obtenerTodos() throws Exception {
        try {
            return jsonUtil.cargarLista(ARCHIVO_TRANSACCIONES, Transaccion.class);
        } catch (Exception e) {
            // Si el archivo no existe, retornar lista vacía
            return new ArrayList<>();
        }
    }
    
    @Override
    public void actualizar(Transaccion transaccionActualizada) throws Exception {
        if (transaccionActualizada == null) {
            throw new IllegalArgumentException("La transacción no puede ser nula");
        }
        
        List<Transaccion> transacciones = obtenerTodos();
        boolean encontrada = false;
        
        for (int i = 0; i < transacciones.size(); i++) {
            if (transacciones.get(i).getIdTransaccion().equals(transaccionActualizada.getIdTransaccion())) {
                transacciones.set(i, transaccionActualizada);
                encontrada = true;
                break;
            }
        }
        
        if (!encontrada) {
            throw new Exception("No se encontró la transacción con ID: " + transaccionActualizada.getIdTransaccion());
        }
        
        jsonUtil.guardarLista(transacciones, ARCHIVO_TRANSACCIONES);
    }
    
    @Override
    public void eliminar(String idTransaccion) throws Exception {
        List<Transaccion> transacciones = obtenerTodos();
        boolean eliminada = transacciones.removeIf(transaccion -> transaccion.getIdTransaccion().equals(idTransaccion));
        
        if (!eliminada) {
            throw new Exception("No se encontró la transacción con ID: " + idTransaccion);
        }
        
        jsonUtil.guardarLista(transacciones, ARCHIVO_TRANSACCIONES);
    }
    
    @Override
    public boolean existe(String idTransaccion) throws Exception {
        return buscarPorId(idTransaccion) != null;
    }
    
    /**
     * Busca todas las transacciones de una cuenta específica.
     * @param numeroCuenta Número de cuenta
     * @return Lista de transacciones de la cuenta
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<Transaccion> buscarPorCuenta(String numeroCuenta) throws Exception {
        List<Transaccion> todasLasTransacciones = obtenerTodos();
        List<Transaccion> transaccionesDeLaCuenta = new ArrayList<>();
        
        for (Transaccion transaccion : todasLasTransacciones) {
            if (transaccion.getNumeroCuentaOrigen().equals(numeroCuenta) || 
                (transaccion.getNumeroCuentaDestino() != null && 
                 transaccion.getNumeroCuentaDestino().equals(numeroCuenta))) {
                transaccionesDeLaCuenta.add(transaccion);
            }
        }
        
        return transaccionesDeLaCuenta;
    }
    
    /**
     * Busca transacciones por tipo (DEPOSITO, RETIRO, TRANSFERENCIA).
     * @param tipoTransaccion Tipo de transacción a buscar
     * @return Lista de transacciones del tipo especificado
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<Transaccion> buscarPorTipo(String tipoTransaccion) throws Exception {
        List<Transaccion> todasLasTransacciones = obtenerTodos();
        List<Transaccion> transaccionesDelTipo = new ArrayList<>();
        
        for (Transaccion transaccion : todasLasTransacciones) {
            if (transaccion.getTipoTransaccion().equals(tipoTransaccion)) {
                transaccionesDelTipo.add(transaccion);
            }
        }
        
        return transaccionesDelTipo;
    }
    
    /**
     * Busca transacciones por sucursal.
     * @param idSucursal ID de la sucursal
     * @return Lista de transacciones realizadas en la sucursal
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<Transaccion> buscarPorSucursal(String idSucursal) throws Exception {
        List<Transaccion> todasLasTransacciones = obtenerTodos();
        List<Transaccion> transaccionesDeLaSucursal = new ArrayList<>();
        
        for (Transaccion transaccion : todasLasTransacciones) {
            if (transaccion.getIdSucursalTransaccion().equals(idSucursal)) {
                transaccionesDeLaSucursal.add(transaccion);
            }
        }
        
        return transaccionesDeLaSucursal;
    }
    
    /**
     * Busca transacciones en un rango de fechas.
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de transacciones en el rango especificado
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<Transaccion> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws Exception {
        List<Transaccion> todasLasTransacciones = obtenerTodos();
        List<Transaccion> transaccionesEnRango = new ArrayList<>();
        
        for (Transaccion transaccion : todasLasTransacciones) {
            LocalDateTime fechaTransaccion = transaccion.getFechaHoraTransaccion();
            if (fechaTransaccion.isEqual(fechaInicio) || fechaTransaccion.isAfter(fechaInicio)) {
                if (fechaTransaccion.isEqual(fechaFin) || fechaTransaccion.isBefore(fechaFin)) {
                    transaccionesEnRango.add(transaccion);
                }
            }
        }
        
        return transaccionesEnRango;
    }
    
    /**
     * Busca transacciones por monto mínimo.
     * @param montoMinimo Monto mínimo de las transacciones
     * @return Lista de transacciones con monto mayor o igual al especificado
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<Transaccion> buscarPorMontoMinimo(double montoMinimo) throws Exception {
        List<Transaccion> todasLasTransacciones = obtenerTodos();
        List<Transaccion> transaccionesPorMonto = new ArrayList<>();
        
        for (Transaccion transaccion : todasLasTransacciones) {
            if (transaccion.getMontoTransaccion() >= montoMinimo) {
                transaccionesPorMonto.add(transaccion);
            }
        }
        
        return transaccionesPorMonto;
    }
}
