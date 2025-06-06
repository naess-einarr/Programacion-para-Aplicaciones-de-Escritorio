package com.eurobank.proyectoaplicacionesdeescritorio.dao;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.util.JsonUtil;
import java.util.List;
import java.util.ArrayList;

public class EmpleadoDAO implements GenericDAO<Empleado> {
    
    private static final String ARCHIVO_EMPLEADOS = "data/empleados.json";
    private JsonUtil<Empleado> jsonUtil;
    
    public EmpleadoDAO() {
        this.jsonUtil = new JsonUtil<>();
    }
    
    @Override
    public void guardar(Empleado empleado) throws Exception {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo");
        }
        
        List<Empleado> empleados = obtenerTodos();
        
        // Verificar si ya existe un empleado con el mismo ID
        if (buscarPorId(empleado.getIdEmpleado()) != null) {
            throw new Exception("Ya existe un empleado con el ID: " + empleado.getIdEmpleado());
        }
        
        // Verificar si ya existe un empleado con el mismo usuario
        if (buscarPorUsuario(empleado.getNombreUsuario()) != null) {
            throw new Exception("Ya existe un empleado con el usuario: " + empleado.getNombreUsuario());
        }
        
        empleados.add(empleado);
        jsonUtil.guardarLista(empleados, ARCHIVO_EMPLEADOS);
    }
    
    @Override
    public Empleado buscarPorId(String idEmpleado) throws Exception {
        List<Empleado> empleados = obtenerTodos();
        
        return empleados.stream()
                .filter(empleado -> empleado.getIdEmpleado().equals(idEmpleado))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Empleado> obtenerTodos() throws Exception {
        try {
            return jsonUtil.cargarLista(ARCHIVO_EMPLEADOS, Empleado.class);
        } catch (Exception e) {
            // Si el archivo no existe, retornar lista vacía
            return new ArrayList<>();
        }
    }
    
    @Override
    public void actualizar(Empleado empleadoActualizado) throws Exception {
        if (empleadoActualizado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo");
        }
        
        List<Empleado> empleados = obtenerTodos();
        boolean encontrado = false;
        
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getIdEmpleado().equals(empleadoActualizado.getIdEmpleado())) {
                empleados.set(i, empleadoActualizado);
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            throw new Exception("No se encontró el empleado con ID: " + empleadoActualizado.getIdEmpleado());
        }
        
        jsonUtil.guardarLista(empleados, ARCHIVO_EMPLEADOS);
    }
    
    @Override
    public void eliminar(String idEmpleado) throws Exception {
        List<Empleado> empleados = obtenerTodos();
        boolean eliminado = empleados.removeIf(empleado -> empleado.getIdEmpleado().equals(idEmpleado));
        
        if (!eliminado) {
            throw new Exception("No se encontró el empleado con ID: " + idEmpleado);
        }
        
        jsonUtil.guardarLista(empleados, ARCHIVO_EMPLEADOS);
    }
    
    @Override
    public boolean existe(String idEmpleado) throws Exception {
        return buscarPorId(idEmpleado) != null;
    }
    
    /**
     * Busca un empleado por su nombre de usuario.
     * @param usuario Nombre de usuario del empleado
     * @return El empleado encontrado o null si no existe
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public Empleado buscarPorUsuario(String usuario) throws Exception {
        List<Empleado> empleados = obtenerTodos();
        
        return empleados.stream()
                .filter(empleado -> empleado.getNombreUsuario().equals(usuario))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Valida las credenciales de un empleado.
     * @param usuario Nombre de usuario
     * @param contrasena Contraseña
     * @return El empleado si las credenciales son correctas, null en caso contrario
     * @throws Exception si ocurre un error durante la validación
     */
    public Empleado validarCredenciales(String usuario, String contrasena) throws Exception {
        Empleado empleado = buscarPorUsuario(usuario);
        
        if (empleado != null && empleado.getContrasenaAcceso().equals(contrasena)) {
            return empleado;
        }
        
        return null;
    }
    
    /**
     * Busca empleados por tipo (CAJERO, EJECUTIVO, GERENTE).
     * @param tipoEmpleado Tipo de empleado a buscar
     * @return Lista de empleados del tipo especificado
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<Empleado> buscarPorTipo(String tipoEmpleado) throws Exception {
        List<Empleado> todosLosEmpleados = obtenerTodos();
        List<Empleado> empleadosDelTipo = new ArrayList<>();
        
        for (Empleado empleado : todosLosEmpleados) {
            if (empleado.getTipoEmpleado().equals(tipoEmpleado)) {
                empleadosDelTipo.add(empleado);
            }
        }
        
        return empleadosDelTipo;
    }
    
    /**
     * Busca empleados por sucursal.
     * @param idSucursal ID de la sucursal
     * @return Lista de empleados de la sucursal
     * @throws Exception si ocurre un error durante la búsqueda
     */
    
}
