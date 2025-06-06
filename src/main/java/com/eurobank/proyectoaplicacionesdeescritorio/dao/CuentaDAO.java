package com.eurobank.proyectoaplicacionesdeescritorio.dao;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.CuentaBancaria;
import com.eurobank.proyectoaplicacionesdeescritorio.util.JsonUtil;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase DAO para gestionar la persistencia de las cuentas bancarias.
 * Implementa operaciones CRUD usando archivos JSON como almacenamiento.
 */
public class CuentaDAO implements GenericDAO<CuentaBancaria> {
    
    private static final String ARCHIVO_CUENTAS = "data/cuentas.json";
    private JsonUtil<CuentaBancaria> jsonUtil;
    
    public CuentaDAO() {
        this.jsonUtil = new JsonUtil<>();
    }
    
    @Override
    public void guardar(CuentaBancaria cuenta) throws Exception {
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula");
        }
        
        List<CuentaBancaria> cuentas = obtenerTodos();
        
        // Verificar si ya existe una cuenta con el mismo número
        if (buscarPorId(cuenta.getNumeroCuenta()) != null) {
            throw new Exception("Ya existe una cuenta con el número: " + cuenta.getNumeroCuenta());
        }
        
        cuentas.add(cuenta);
        jsonUtil.guardarLista(cuentas, ARCHIVO_CUENTAS);
    }
    
    @Override
    public CuentaBancaria buscarPorId(String numeroCuenta) throws Exception {
        List<CuentaBancaria> cuentas = obtenerTodos();
        
        return cuentas.stream()
                .filter(cuenta -> cuenta.getNumeroCuenta().equals(numeroCuenta))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<CuentaBancaria> obtenerTodos() throws Exception {
        try {
            return jsonUtil.cargarLista(ARCHIVO_CUENTAS, CuentaBancaria.class);
        } catch (Exception e) {
            // Si el archivo no existe, retornar lista vacía
            return new ArrayList<>();
        }
    }
    
    @Override
    public void actualizar(CuentaBancaria cuentaActualizada) throws Exception {
        if (cuentaActualizada == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula");
        }
        
        List<CuentaBancaria> cuentas = obtenerTodos();
        boolean encontrada = false;
        
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getNumeroCuenta().equals(cuentaActualizada.getNumeroCuenta())) {
                cuentas.set(i, cuentaActualizada);
                encontrada = true;
                break;
            }
        }
        
        if (!encontrada) {
            throw new Exception("No se encontró la cuenta con número: " + cuentaActualizada.getNumeroCuenta());
        }
        
        jsonUtil.guardarLista(cuentas, ARCHIVO_CUENTAS);
    }
    
    @Override
    public void eliminar(String numeroCuenta) throws Exception {
        List<CuentaBancaria> cuentas = obtenerTodos();
        boolean eliminada = cuentas.removeIf(cuenta -> cuenta.getNumeroCuenta().equals(numeroCuenta));
        
        if (!eliminada) {
            throw new Exception("No se encontró la cuenta con número: " + numeroCuenta);
        }
        
        jsonUtil.guardarLista(cuentas, ARCHIVO_CUENTAS);
    }
    
    @Override
    public boolean existe(String numeroCuenta) throws Exception {
        return buscarPorId(numeroCuenta) != null;
    }
    
    /**
     * Busca todas las cuentas asociadas a un cliente específico.
     * @param idCliente ID del cliente
     * @return Lista de cuentas del cliente
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<CuentaBancaria> buscarPorCliente(String idCliente) throws Exception {
        List<CuentaBancaria> todasLasCuentas = obtenerTodos();
        List<CuentaBancaria> cuentasDelCliente = new ArrayList<>();
        
        for (CuentaBancaria cuenta : todasLasCuentas) {
            if (cuenta.getIdClienteAsociado().equals(idCliente)) {
                cuentasDelCliente.add(cuenta);
            }
        }
        
        return cuentasDelCliente;
    }
    
    /**
     * Busca todas las cuentas de una sucursal específica.
     * @param idSucursal ID de la sucursal
     * @return Lista de cuentas de la sucursal
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<CuentaBancaria> buscarPorSucursal(String idSucursal) throws Exception {
        List<CuentaBancaria> todasLasCuentas = obtenerTodos();
        List<CuentaBancaria> cuentasDeLaSucursal = new ArrayList<>();
        
        for (CuentaBancaria cuenta : todasLasCuentas) {
            if (cuenta.getIdSucursalAsociada().equals(idSucursal)) {
                cuentasDeLaSucursal.add(cuenta);
            }
        }
        
        return cuentasDeLaSucursal;
    }
    
    /**
     * Busca cuentas por tipo (CORRIENTE, AHORROS, EMPRESARIAL).
     * @param tipoCuenta Tipo de cuenta a buscar
     * @return Lista de cuentas del tipo especificado
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<CuentaBancaria> buscarPorTipo(String tipoCuenta) throws Exception {
        List<CuentaBancaria> todasLasCuentas = obtenerTodos();
        List<CuentaBancaria> cuentasDelTipo = new ArrayList<>();
        
        for (CuentaBancaria cuenta : todasLasCuentas) {
            if (cuenta.getTipoCuenta().equals(tipoCuenta)) {
                cuentasDelTipo.add(cuenta);
            }
        }
        
        return cuentasDelTipo;
    }
}
