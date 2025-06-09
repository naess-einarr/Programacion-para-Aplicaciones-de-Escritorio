package com.eurobank.proyectoaplicacionesdeescritorio.dao.adaptadores;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.*;
import com.google.gson.*;
import java.lang.reflect.Type;

public class CuentaTypeAdapter implements JsonSerializer<Cuenta>, JsonDeserializer<Cuenta> {
    
    @Override
    public JsonElement serialize(Cuenta cuenta, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        
        // Campos simples de la cuenta
        jsonObject.addProperty("numeroCuenta", cuenta.getNumeroCuenta());
        jsonObject.addProperty("tipo", cuenta.getTipo());
        jsonObject.addProperty("saldoActual", cuenta.getSaldoActual());
        jsonObject.addProperty("limiteCredito", cuenta.getLimiteCredito());
        
        // Cliente - solo idCliente, nombreCompleto y apellidosCompletos
        if (cuenta.getCliente() != null) {
            JsonObject clienteObj = new JsonObject();
            clienteObj.addProperty("idCliente", cuenta.getCliente().getIdCliente());
            clienteObj.addProperty("nombreCompleto", cuenta.getCliente().getNombreCompleto());
            clienteObj.addProperty("apellidosCompletos", cuenta.getCliente().getApellidosCompletos());
            jsonObject.add("cliente", clienteObj);
        }
        
        return jsonObject;
    }

    @Override
    public Cuenta deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
            throws JsonParseException {
        
        JsonObject jsonObject = json.getAsJsonObject();
        Cuenta cuenta = new Cuenta();
        
        // Campos simples de la cuenta
        cuenta.setNumeroCuenta(jsonObject.get("numeroCuenta").getAsString());
        cuenta.setTipo(jsonObject.get("tipo").getAsString());
        cuenta.setSaldoActual(jsonObject.get("saldoActual").getAsDouble());
        cuenta.setLimiteCredito(jsonObject.get("limiteCredito").getAsDouble());
        
        // Cliente - solo idCliente, nombreCompleto y apellidosCompletos
        if (jsonObject.has("cliente") && !jsonObject.get("cliente").isJsonNull()) {
            JsonObject clienteObj = jsonObject.getAsJsonObject("cliente");
            Cliente cliente = new Cliente();
            cliente.setIdCliente(clienteObj.get("idCliente").getAsString());
            cliente.setNombreCompleto(clienteObj.get("nombreCompleto").getAsString());
            cliente.setApellidosCompletos(clienteObj.get("apellidosCompletos").getAsString());
            cuenta.setCliente(cliente);
        }
        
        return cuenta;
    }
}