package com.eurobank.proyectoaplicacionesdeescritorio.dao;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cliente;
import com.eurobank.proyectoaplicacionesdeescritorio.util.JsonUtil;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase DAO para gestionar la persistencia de los clientes.
 * Implementa operaciones CRUD usando archivos JSON como almacenamiento.
 */
public class ClienteDAO implements GenericDAO<Cliente> {
    
    private static final String ARCHIVO_CLIENTES = "data/clientes.json";
    private JsonUtil<Cliente> jsonUtil;
    
    public ClienteDAO() {
        this.jsonUtil = new JsonUtil<>();
    }
    
    @Override
    public void guardar(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        
        List<Cliente> clientes = obtenerTodos();
        
        if (buscarPorRfc(cliente.getRfcCliente()) != null) {
            throw new Exception("Ya existe un cliente con el RFC: " + cliente.getRfcCliente());
        }
        
        clientes.add(cliente);
        jsonUtil.guardarLista(clientes, ARCHIVO_CLIENTES);
    }
    
    @Override
    public Cliente buscarPorId(String idCliente) throws Exception {
        List<Cliente> clientes = obtenerTodos();
        
        return clientes.stream()
                .filter(cliente -> cliente.getIdCliente().equals(idCliente))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Cliente> obtenerTodos() throws Exception {
        try {
            return jsonUtil.cargarLista(ARCHIVO_CLIENTES, Cliente.class);
        } catch (Exception e) {
            // Si el archivo no existe, retornar lista vacía
            return new ArrayList<>();
        }
    }
    
    @Override
    public void actualizar(Cliente clienteActualizado) throws Exception {
        if (clienteActualizado == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        
        List<Cliente> clientes = obtenerTodos();
        boolean encontrado = false;
        
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente().equals(clienteActualizado.getIdCliente())) {
                clientes.set(i, clienteActualizado);
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            throw new Exception("No se encontró el cliente con ID: " + clienteActualizado.getIdCliente());
        }
        
        jsonUtil.guardarLista(clientes, ARCHIVO_CLIENTES);
    }
    
    @Override
    public void eliminar(String clienteID) throws Exception {
        
        List<Cliente> clientes = obtenerTodos();
        boolean eliminado = clientes.removeIf(cliente -> cliente.getIdCliente().equals(clienteID));
        
        if (!eliminado) {
            throw new Exception("No se encontró el cliente el ID:" + clienteID);
        }
        
        jsonUtil.guardarLista(clientes, ARCHIVO_CLIENTES);
    }
    
    @Override
    public boolean existe(String idCliente) throws Exception {
        return buscarPorId(idCliente) != null;
    }
    
    /**
     * Busca un cliente por su RFC.
     * @param rfc RFC del cliente
     * @return El cliente encontrado o null si no existe
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public Cliente buscarPorRfc(String rfc) throws Exception {
        List<Cliente> clientes = obtenerTodos();
        
        return clientes.stream()
                .filter(cliente -> cliente.getRfcCliente().equals(rfc))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Busca clientes por nombre (búsqueda parcial).
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de clientes que coinciden con el criterio
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<Cliente> buscarPorNombre(String nombre) throws Exception {
        List<Cliente> todosLosClientes = obtenerTodos();
        List<Cliente> resultados = new ArrayList<>();
        
        for (Cliente cliente : todosLosClientes) {
            String nombreCompleto = cliente.getNombreCompleto() + " " + cliente.getApellidosCompletos();
            if (nombreCompleto.toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(cliente);
            }
        }
        
        return resultados;
    }
    
    /**
     * Busca clientes por nacionalidad.
     * @param nacionalidad Nacionalidad a buscar
     * @return Lista de clientes de la nacionalidad especificada
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public List<Cliente> buscarPorNacionalidad(String nacionalidad) throws Exception {
        List<Cliente> todosLosClientes = obtenerTodos();
        List<Cliente> clientesPorNacionalidad = new ArrayList<>();
        
        for (Cliente cliente : todosLosClientes) {
            if (cliente.getNacionalidadCliente().equalsIgnoreCase(nacionalidad)) {
                clientesPorNacionalidad.add(cliente);
            }
        }
        
        return clientesPorNacionalidad;
    }
    
    /**
     * Busca un cliente por su correo electrónico.
     * @param correo Correo electrónico del cliente
     * @return El cliente encontrado o null si no existe
     * @throws Exception si ocurre un error durante la búsqueda
     */
    public Cliente buscarPorCorreo(String correo) throws Exception {
        List<Cliente> clientes = obtenerTodos();
        
        return clientes.stream()
                .filter(cliente -> cliente.getCorreoElectronico().equals(correo))
                .findFirst()
                .orElse(null);
    }
}
