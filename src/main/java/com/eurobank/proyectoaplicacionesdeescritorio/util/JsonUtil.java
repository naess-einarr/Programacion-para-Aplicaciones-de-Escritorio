package com.eurobank.proyectoaplicacionesdeescritorio.util;

import com.eurobank.proyectoaplicacionesdeescritorio.dao.adaptadores.CuentaTypeAdapter;
import com.eurobank.proyectoaplicacionesdeescritorio.dao.adaptadores.EmpleadoTypeAdapter;
import com.eurobank.proyectoaplicacionesdeescritorio.dao.adaptadores.SucursalTypeAdapter;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cuenta;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Sucursal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
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
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, type, context)
                        -> LocalDate.parse(json.getAsString()))
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, type, context) -> {
                            if (json.isJsonObject()) {
                                JsonObject dateObj = json.getAsJsonObject();
                                return LocalDate.of(
                                        dateObj.get("year").getAsInt(),
                                        dateObj.get("month").getAsInt(),
                                        dateObj.get("day").getAsInt()
                                );
                            } else {
                                return LocalDate.parse(json.getAsString());
                            }
                        })
                .registerTypeAdapter(Empleado.class, new EmpleadoTypeAdapter())
                .registerTypeAdapter(Sucursal.class, new SucursalTypeAdapter())
                .registerTypeAdapter(Cuenta.class, new CuentaTypeAdapter())
                .setPrettyPrinting()
                .create();
    }
    
    public void guardar(T objeto, String rutaArchivo) throws Exception {
        if (objeto == null) {
            throw new IllegalArgumentException("El objeto no puede ser nulo");
        }
        
        crearDirectorioSiNoExiste(rutaArchivo);
        
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(rutaArchivo), StandardCharsets.UTF_8)) {
            gson.toJson(objeto, writer);
        }
    }
    
    public void guardarLista(List<T> lista, String rutaArchivo) throws Exception {
        if (lista == null) {
            throw new IllegalArgumentException("La lista no puede ser nula");
        }
        
        crearDirectorioSiNoExiste(rutaArchivo);
        
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(rutaArchivo), StandardCharsets.UTF_8)) {
            gson.toJson(lista, writer);
        }
    }
    
    public T cargar(String rutaArchivo, Class<T> claseObjeto) throws Exception {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            throw new FileNotFoundException("El archivo no existe: " + rutaArchivo);
        }
        
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(rutaArchivo), StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, claseObjeto);
        }
    }
    
    public List<T> cargarLista(String rutaArchivo, Class<T> claseObjeto) throws Exception {
        File archivo = new File(rutaArchivo);
        archivo.getAbsoluteFile();
        if (!archivo.exists()) {
            throw new FileNotFoundException("El archivo no existe: " + rutaArchivo);
        }
        
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(rutaArchivo), StandardCharsets.UTF_8)) {
            Type tipoLista = TypeToken.getParameterized(List.class, claseObjeto).getType();
            return gson.fromJson(reader, tipoLista);
        }
    }
    
    private void crearDirectorioSiNoExiste(String rutaArchivo) throws Exception {
        Path path = Paths.get(rutaArchivo);
        Path directorio = path.getParent();
        
        if (directorio != null && !Files.exists(directorio)) {
            Files.createDirectories(directorio);
        }
    }
    
    public boolean archivoExiste(String rutaArchivo) {
        return new File(rutaArchivo).exists();
    }
}