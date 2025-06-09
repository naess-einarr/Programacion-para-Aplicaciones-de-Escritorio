package com.eurobank.proyectoaplicacionesdeescritorio.dao.adaptadores;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Empleado;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Cajero;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Ejecutivo;
import com.eurobank.proyectoaplicacionesdeescritorio.modelo.Gerente;
import com.google.gson.*;
import java.lang.reflect.Type;

public class EmpleadoTypeAdapter implements JsonDeserializer<Empleado>, JsonSerializer<Empleado> {

    @Override
    public Empleado deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
            throws JsonParseException {
        
        JsonObject jsonObject = json.getAsJsonObject();
        String tipoEmpleado = jsonObject.get("tipoEmpleado").getAsString();
        
        switch (tipoEmpleado) {
            case "CAJERO":
                return context.deserialize(json, Cajero.class);
            case "EJECUTIVO":
                return context.deserialize(json, Ejecutivo.class);
            case "GERENTE":
                return context.deserialize(json, Gerente.class);
            default:
                return context.deserialize(json, Empleado.class);
        }
    }

    @Override
    public JsonElement serialize(Empleado src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src, src.getClass());
    }
}