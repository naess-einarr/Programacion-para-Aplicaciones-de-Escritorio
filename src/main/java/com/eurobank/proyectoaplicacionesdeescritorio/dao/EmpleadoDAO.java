package com.eurobank.proyectoaplicacionesdeescritorio.dao;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cajero;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Ejecutivo;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.eurobank.proyectoaplicacionesdeescritorio.util.JsonUtil;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EmpleadoDAO implements GenericDAO<Empleado> {

    private static final String ARCHIVO_EMPLEADOS = "data/empleados.json";
    private final JsonUtil<Empleado> jsonUtil;

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
        if (idEmpleado == null || idEmpleado.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del empleado no puede ser nulo o vacío");
        }

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

    public Empleado buscarPorUsuario(String usuario) throws Exception {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario no puede ser nulo o vacío");
        }

        List<Empleado> empleados = obtenerTodos();

        return empleados.stream()
                .filter(empleado -> empleado.getNombreUsuario().equals(usuario))
                .findFirst()
                .orElse(null);
    }

    public Empleado validarCredenciales(String usuario, String contrasena) throws Exception {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario no puede ser nulo o vacío");
        }
        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía");
        }

        Empleado empleado = buscarPorUsuario(usuario);

        if (empleado != null && empleado.getContrasenaAcceso().equals(contrasena)) {
            return empleado;
        }

        return null;
    }

    public List<Empleado> buscarPorTipo(String tipoEmpleado) throws Exception {
        if (tipoEmpleado == null || tipoEmpleado.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de empleado no puede ser nulo o vacío");
        }

        List<Empleado> todosLosEmpleados = obtenerTodos();

        return todosLosEmpleados.stream()
                .filter(empleado -> empleado.getTipoEmpleado().equals(tipoEmpleado))
                .collect(Collectors.toList());
    }

    // Métodos específicos para obtener instancias tipadas
    public List<Gerente> obtenerGerentes() throws Exception {
        return obtenerTodos().stream()
                .filter(empleado -> empleado instanceof Gerente)
                .map(empleado -> (Gerente) empleado)
                .collect(Collectors.toList());
    }

    public List<Cajero> obtenerCajeros() throws Exception {
        return obtenerTodos().stream()
                .filter(empleado -> empleado instanceof Cajero)
                .map(empleado -> (Cajero) empleado)
                .collect(Collectors.toList());
    }

    public List<Ejecutivo> obtenerEjecutivos() throws Exception {
        return obtenerTodos().stream()
                .filter(empleado -> empleado instanceof Ejecutivo)
                .map(empleado -> (Ejecutivo) empleado)
                .collect(Collectors.toList());
    }

    public int obtenerSiguienteId() throws Exception {
        List<Empleado> empleados = obtenerTodos();

        if (empleados.isEmpty()) {
            return 1;
        }

        int maxNumero = empleados.stream()
                .mapToInt(empleado -> {
                    String id = empleado.getIdEmpleado();
                    if (id != null && id.startsWith("EMP")) {
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
