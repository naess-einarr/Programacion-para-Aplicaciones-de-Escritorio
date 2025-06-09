package com.eurobank.proyectoaplicacionesdeescritorio.dao;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cuenta;
import com.eurobank.proyectoaplicacionesdeescritorio.util.JsonUtil;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase DAO para gestionar la persistencia de las cuentas bancarias.
 * Implementa operaciones CRUD usando archivos JSON como almacenamiento.
 */
public class CuentaDAO implements GenericDAO<Cuenta> {
    
    private static final String ARCHIVO_CUENTAS = "data/cuentas.json";
    private JsonUtil<Cuenta> jsonUtil;
    
    public CuentaDAO() {
        this.jsonUtil = new JsonUtil<>();
    }
    
    @Override
    public void guardar(Cuenta cuenta) throws Exception {
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula");
        }
        
        List<Cuenta> cuentas = obtenerTodos();
        
        // Verificar si ya existe una cuenta con el mismo número
        if (buscarPorId(cuenta.getNumeroCuenta()) != null) {
            throw new Exception("Ya existe una cuenta con el número: " + cuenta.getNumeroCuenta());
        }
        
        cuentas.add(cuenta);
        jsonUtil.guardarLista(cuentas, ARCHIVO_CUENTAS);
    }
    
    @Override
    public Cuenta buscarPorId(String numeroCuenta) throws Exception {
        List<Cuenta> cuentas = obtenerTodos();
        
        return cuentas.stream()
                .filter(cuenta -> cuenta.getNumeroCuenta().equals(numeroCuenta))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Cuenta> obtenerTodos() throws Exception {
        try {
            return jsonUtil.cargarLista(ARCHIVO_CUENTAS, Cuenta.class);
        } catch (Exception e) {
            // Si el archivo no existe, retornar lista vacía
            return new ArrayList<>();
        }
    }
    
    @Override
    public void actualizar(Cuenta cuentaActualizada) throws Exception {
        if (cuentaActualizada == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula");
        }
        
        List<Cuenta> cuentas = obtenerTodos();
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
        List<Cuenta> cuentas = obtenerTodos();
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
    
    public List<Cuenta> buscarPorCliente(String idCliente) throws Exception {
        List<Cuenta> todasLasCuentas = obtenerTodos();
        List<Cuenta> cuentasDelCliente = new ArrayList<>();
        
        for (Cuenta cuenta : todasLasCuentas) {
            if (cuenta.getCliente().getIdCliente().equals(idCliente)) {
                cuentasDelCliente.add(cuenta);
            }
        }
        
        return cuentasDelCliente;
    }
    
    public List<Cuenta> buscarPorTipo(String tipo) throws Exception {
        List<Cuenta> todasLasCuentas = obtenerTodos();
        List<Cuenta> cuentasDelTipo = new ArrayList<>();
        
        for (Cuenta cuenta : todasLasCuentas) {
            if (cuenta.getTipo().equals(tipo)) {
                cuentasDelTipo.add(cuenta);
            }
        }
        
        return cuentasDelTipo;
    }
    public int obtenerSiguienteId() throws Exception {
        List<Cuenta> cuentas = obtenerTodos();

        if (cuentas.isEmpty()) {
            return 1;
        }

        int maxNumero = cuentas.stream()
                .mapToInt(cuenta -> {
                    String id = cuenta.getNumeroCuenta();
                    if (id != null && id.startsWith("CTA")) {
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
}
