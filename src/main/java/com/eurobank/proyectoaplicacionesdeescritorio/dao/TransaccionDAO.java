package com.eurobank.proyectoaplicacionesdeescritorio.dao;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Transaccion;
import com.eurobank.proyectoaplicacionesdeescritorio.util.JsonUtil;
import java.util.List;
import java.util.ArrayList;

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
    
    public List<Transaccion> obtenerTransferencias() throws Exception {
        List<Transaccion> todasLasTransacciones = obtenerTodos();
        List<Transaccion> transferencias = new ArrayList<>();
        
        for (Transaccion transaccion : todasLasTransacciones) {
            if (transaccion.esTransferencia() || transaccion.getCuentaDestino() != null) {
                transferencias.add(transaccion);
            }
        }
        
        return transferencias;
    }
}