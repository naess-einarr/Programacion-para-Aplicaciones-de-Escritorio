package com.eurobank.proyectoaplicacionesdeescritorio.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

/**
 * Utilidad para manejo de archivos JSON.
 * Permite guardar y cargar objetos y listas usando la librería Gson.
 * @param <T> Tipo de objeto que maneja la utilidad
 */
public class JsonUtil<T> {
    
    private final Gson gson;
    
    public JsonUtil() {
        this.gson = new GsonBuilder()
                // Adaptador para LocalDate (formato yyyy-MM-dd)
                .registerTypeAdapter(LocalDate.class,
                    (JsonDeserializer<LocalDate>) (json, type, context) ->
                        LocalDate.parse(json.getAsString()))
                
                // Formato para java.util.Date (usa horas, minutos, etc.)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")

                .setPrettyPrinting()
                .create();
    }
    
    /**
     * Guarda un objeto en un archivo JSON.
     * @param objeto Objeto a guardar
     * @param rutaArchivo Ruta del archivo donde guardar
     * @throws Exception si ocurre un error durante el guardado
     */
    public void guardar(T objeto, String rutaArchivo) throws Exception {
        if (objeto == null) {
            throw new IllegalArgumentException("El objeto no puede ser nulo");
        }
        
        crearDirectorioSiNoExiste(rutaArchivo);
        
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(objeto, writer);
        }
    }
    
    /**
     * Guarda una lista de objetos en un archivo JSON.
     * @param lista Lista de objetos a guardar
     * @param rutaArchivo Ruta del archivo donde guardar
     * @throws Exception si ocurre un error durante el guardado
     */
    public void guardarLista(List<T> lista, String rutaArchivo) throws Exception {
        if (lista == null) {
            throw new IllegalArgumentException("La lista no puede ser nula");
        }
        
        crearDirectorioSiNoExiste(rutaArchivo);
        
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(lista, writer);
        }
    }
    
    /**
     * Carga un objeto desde un archivo JSON.
     * @param rutaArchivo Ruta del archivo a cargar
     * @param claseObjeto Clase del objeto a cargar
     * @return Objeto cargado del archivo
     * @throws Exception si ocurre un error durante la carga
     */
    public T cargar(String rutaArchivo, Class<T> claseObjeto) throws Exception {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            throw new FileNotFoundException("El archivo no existe: " + rutaArchivo);
        }
        
        try (FileReader reader = new FileReader(rutaArchivo)) {
            return gson.fromJson(reader, claseObjeto);
        }
    }
    
    /**
     * Carga una lista de objetos desde un archivo JSON.
     * @param rutaArchivo Ruta del archivo a cargar
     * @param claseObjeto Clase de los objetos de la lista
     * @return Lista de objetos cargados del archivo
     * @throws Exception si ocurre un error durante la carga
     */
    public List<T> cargarLista(String rutaArchivo, Class<T> claseObjeto) throws Exception {
        File archivo = new File(rutaArchivo);
        archivo.getAbsoluteFile();
        if (!archivo.exists()) {
            throw new FileNotFoundException("El archivo no existe: " + rutaArchivo);
        }
        
        try (FileReader reader = new FileReader(rutaArchivo)) {
            Type tipoLista = TypeToken.getParameterized(List.class, claseObjeto).getType();
            return gson.fromJson(reader, tipoLista);
        }
    }
    
    /**
     * Crea el directorio si no existe.
     * @param rutaArchivo Ruta del archivo
     * @throws Exception si ocurre un error al crear el directorio
     */
    private void crearDirectorioSiNoExiste(String rutaArchivo) throws Exception {
        Path path = Paths.get(rutaArchivo);
        Path directorio = path.getParent();
        
        if (directorio != null && !Files.exists(directorio)) {
            Files.createDirectories(directorio);
        }
    }
    
    /**
     * Verifica si un archivo existe.
     * @param rutaArchivo Ruta del archivo a verificar
     * @return true si el archivo existe, false en caso contrario
     */
    public boolean archivoExiste(String rutaArchivo) {
        return new File(rutaArchivo).exists();
    }
}