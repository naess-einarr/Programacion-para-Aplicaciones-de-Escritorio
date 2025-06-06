
package com.eurobank.proyectoaplicacionesdeescritorio.dao;

import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD (Create, Read, Update, Delete).
 * Define los métodos básicos que deben implementar todos los DAOs del sistema.
 * @param <T> Tipo de entidad que maneja el DAO
 */
public interface GenericDAO<T> {
    
    /**
     * Guarda una nueva entidad en el almacenamiento.
     * @param entidad Objeto a guardar
     * @throws Exception si ocurre un error durante el guardado
     */
    void guardar(T entidad) throws Exception;
    
    /**
     * Busca una entidad por su identificador único.
     * @param identificador ID de la entidad a buscar
     * @return La entidad encontrada o null si no existe
     * @throws Exception si ocurre un error durante la búsqueda
     */
    T buscarPorId(String identificador) throws Exception;
    
    /**
     * Obtiene todas las entidades almacenadas.
     * @return Lista con todas las entidades
     * @throws Exception si ocurre un error durante la consulta
     */
    List<T> obtenerTodos() throws Exception;
    
    /**
     * Actualiza una entidad existente.
     * @param entidad Objeto con los datos actualizados
     * @throws Exception si ocurre un error durante la actualización
     */
    void actualizar(T entidad) throws Exception;
    
    /**
     * Elimina una entidad por su identificador.
     * @param identificador ID de la entidad a eliminar
     * @throws Exception si ocurre un error durante la eliminación
     */
    void eliminar(String identificador) throws Exception;
    
    /**
     * Verifica si existe una entidad con el identificador dado.
     * @param identificador ID a verificar
     * @return true si existe, false en caso contrario
     * @throws Exception si ocurre un error durante la verificación
     */
    boolean existe(String identificador) throws Exception;
}