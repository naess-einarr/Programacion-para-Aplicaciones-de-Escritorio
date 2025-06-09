package com.eurobank.proyectoaplicacionesdeescritorio.dao.adaptadores;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.*;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransaccionTypeAdapter implements JsonSerializer<Transaccion>, JsonDeserializer<Transaccion> {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    @Override
    public JsonElement serialize(Transaccion transaccion, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        
        // Campos básicos de Transaccion
        jsonObject.addProperty("idTransaccion", transaccion.getIdTransaccion());
        jsonObject.addProperty("montoTransaccion", transaccion.getMontoTransaccion());
        jsonObject.addProperty("tipoTransaccion", transaccion.getTipoTransaccion());
        
        // Fecha y hora
        if (transaccion.getFechaHoraTransaccion() != null) {
            jsonObject.addProperty("fechaHoraTransaccion", transaccion.getFechaHoraTransaccion().format(FORMATTER));
        }
        
        // Cuenta Origen - solo numeroCuenta y cliente con idCliente, nombreCompleto y apellidosCompletos
        if (transaccion.getCuentaOrigen() != null) {
            JsonObject cuentaOrigenObj = new JsonObject();
            cuentaOrigenObj.addProperty("numeroCuenta", transaccion.getCuentaOrigen().getNumeroCuenta());
            
            if (transaccion.getCuentaOrigen().getCliente() != null) {
                JsonObject clienteOrigenObj = new JsonObject();
                clienteOrigenObj.addProperty("idCliente", transaccion.getCuentaOrigen().getCliente().getIdCliente());
                clienteOrigenObj.addProperty("nombreCompleto", transaccion.getCuentaOrigen().getCliente().getNombreCompleto());
                clienteOrigenObj.addProperty("apellidosCompletos", transaccion.getCuentaOrigen().getCliente().getApellidosCompletos());
                cuentaOrigenObj.add("cliente", clienteOrigenObj);
            }
            jsonObject.add("cuentaOrigen", cuentaOrigenObj);
        }
        
        // Cuenta Destino - solo numeroCuenta y cliente con idCliente, nombreCompleto y apellidosCompletos
        if (transaccion.getCuentaDestino() != null) {
            JsonObject cuentaDestinoObj = new JsonObject();
            cuentaDestinoObj.addProperty("numeroCuenta", transaccion.getCuentaDestino().getNumeroCuenta());
            
            if (transaccion.getCuentaDestino().getCliente() != null) {
                JsonObject clienteDestinoObj = new JsonObject();
                clienteDestinoObj.addProperty("idCliente", transaccion.getCuentaDestino().getCliente().getIdCliente());
                clienteDestinoObj.addProperty("nombreCompleto", transaccion.getCuentaDestino().getCliente().getNombreCompleto());
                clienteDestinoObj.addProperty("apellidosCompletos", transaccion.getCuentaDestino().getCliente().getApellidosCompletos());
                cuentaDestinoObj.add("cliente", clienteDestinoObj);
            }
            jsonObject.add("cuentaDestino", cuentaDestinoObj);
        }
        
        // Sucursal - solo idSucursal y nombreSucursal
        if (transaccion.getSucursal() != null) {
            JsonObject sucursalObj = new JsonObject();
            sucursalObj.addProperty("idSucursal", transaccion.getSucursal().getIdSucursal());
            sucursalObj.addProperty("nombreSucursal", transaccion.getSucursal().getNombreSucursal());
            jsonObject.add("sucursal", sucursalObj);
        }
        
        // Empleado - solo idEmpleado y nombreCompleto
        if (transaccion.getEmpleadoResponsable() != null) {
            JsonObject empleadoObj = new JsonObject();
            empleadoObj.addProperty("idEmpleado", transaccion.getEmpleadoResponsable().getIdEmpleado());
            empleadoObj.addProperty("nombreCompleto", transaccion.getEmpleadoResponsable().getNombreCompleto());
            jsonObject.add("empleadoResponsable", empleadoObj);
        }
        
        return jsonObject;
    }

    @Override
    public Transaccion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
            throws JsonParseException {
        
        JsonObject jsonObject = json.getAsJsonObject();
        Transaccion transaccion = new Transaccion();
        
        try {
            // Campos básicos - con validaciones null
            if (jsonObject.has("idTransaccion") && !jsonObject.get("idTransaccion").isJsonNull()) {
                transaccion.setIdTransaccion(jsonObject.get("idTransaccion").getAsString());
            }
            
            if (jsonObject.has("montoTransaccion") && !jsonObject.get("montoTransaccion").isJsonNull()) {
                transaccion.setMontoTransaccion(jsonObject.get("montoTransaccion").getAsDouble());
            }
            
            if (jsonObject.has("tipoTransaccion") && !jsonObject.get("tipoTransaccion").isJsonNull()) {
                transaccion.setTipoTransaccion(jsonObject.get("tipoTransaccion").getAsString());
            }
            
            // Fecha y hora
            if (jsonObject.has("fechaHoraTransaccion") && !jsonObject.get("fechaHoraTransaccion").isJsonNull()) {
                String fechaStr = jsonObject.get("fechaHoraTransaccion").getAsString();
                transaccion.setFechaHoraTransaccion(LocalDateTime.parse(fechaStr, FORMATTER));
            }
            
            // Cuenta Origen
            if (jsonObject.has("cuentaOrigen") && !jsonObject.get("cuentaOrigen").isJsonNull()) {
                JsonObject cuentaOrigenObj = jsonObject.getAsJsonObject("cuentaOrigen");
                Cuenta cuentaOrigen = new Cuenta();
                
                if (cuentaOrigenObj.has("numeroCuenta") && !cuentaOrigenObj.get("numeroCuenta").isJsonNull()) {
                    cuentaOrigen.setNumeroCuenta(cuentaOrigenObj.get("numeroCuenta").getAsString());
                }
                
                if (cuentaOrigenObj.has("cliente") && !cuentaOrigenObj.get("cliente").isJsonNull()) {
                    JsonObject clienteOrigenObj = cuentaOrigenObj.getAsJsonObject("cliente");
                    Cliente clienteOrigen = new Cliente();
                    
                    if (clienteOrigenObj.has("idCliente") && !clienteOrigenObj.get("idCliente").isJsonNull()) {
                        clienteOrigen.setIdCliente(clienteOrigenObj.get("idCliente").getAsString());
                    }
                    if (clienteOrigenObj.has("nombreCompleto") && !clienteOrigenObj.get("nombreCompleto").isJsonNull()) {
                        clienteOrigen.setNombreCompleto(clienteOrigenObj.get("nombreCompleto").getAsString());
                    }
                    if (clienteOrigenObj.has("apellidosCompletos") && !clienteOrigenObj.get("apellidosCompletos").isJsonNull()) {
                        clienteOrigen.setApellidosCompletos(clienteOrigenObj.get("apellidosCompletos").getAsString());
                    }
                    cuentaOrigen.setCliente(clienteOrigen);
                }
                transaccion.setCuentaOrigen(cuentaOrigen);
            }
            
            // Cuenta Destino
            if (jsonObject.has("cuentaDestino") && !jsonObject.get("cuentaDestino").isJsonNull()) {
                JsonObject cuentaDestinoObj = jsonObject.getAsJsonObject("cuentaDestino");
                Cuenta cuentaDestino = new Cuenta();
                
                if (cuentaDestinoObj.has("numeroCuenta") && !cuentaDestinoObj.get("numeroCuenta").isJsonNull()) {
                    cuentaDestino.setNumeroCuenta(cuentaDestinoObj.get("numeroCuenta").getAsString());
                }
                
                if (cuentaDestinoObj.has("cliente") && !cuentaDestinoObj.get("cliente").isJsonNull()) {
                    JsonObject clienteDestinoObj = cuentaDestinoObj.getAsJsonObject("cliente");
                    Cliente clienteDestino = new Cliente();
                    
                    if (clienteDestinoObj.has("idCliente") && !clienteDestinoObj.get("idCliente").isJsonNull()) {
                        clienteDestino.setIdCliente(clienteDestinoObj.get("idCliente").getAsString());
                    }
                    if (clienteDestinoObj.has("nombreCompleto") && !clienteDestinoObj.get("nombreCompleto").isJsonNull()) {
                        clienteDestino.setNombreCompleto(clienteDestinoObj.get("nombreCompleto").getAsString());
                    }
                    if (clienteDestinoObj.has("apellidosCompletos") && !clienteDestinoObj.get("apellidosCompletos").isJsonNull()) {
                        clienteDestino.setApellidosCompletos(clienteDestinoObj.get("apellidosCompletos").getAsString());
                    }
                    cuentaDestino.setCliente(clienteDestino);
                }
                transaccion.setCuentaDestino(cuentaDestino);
            }
            
            // Sucursal
            if (jsonObject.has("sucursal") && !jsonObject.get("sucursal").isJsonNull()) {
                JsonObject sucursalObj = jsonObject.getAsJsonObject("sucursal");
                Sucursal sucursal = new Sucursal();
                
                if (sucursalObj.has("idSucursal") && !sucursalObj.get("idSucursal").isJsonNull()) {
                    sucursal.setIdSucursal(sucursalObj.get("idSucursal").getAsString());
                }
                if (sucursalObj.has("nombreSucursal") && !sucursalObj.get("nombreSucursal").isJsonNull()) {
                    sucursal.setNombreSucursal(sucursalObj.get("nombreSucursal").getAsString());
                }
                transaccion.setSucursal(sucursal);
            }
            
            // Empleado Responsable
            if (jsonObject.has("empleadoResponsable") && !jsonObject.get("empleadoResponsable").isJsonNull()) {
                JsonObject empleadoObj = jsonObject.getAsJsonObject("empleadoResponsable");
                Empleado empleado = new Empleado() {}; // Instancia anónima si Empleado es abstracto
                
                if (empleadoObj.has("idEmpleado") && !empleadoObj.get("idEmpleado").isJsonNull()) {
                    empleado.setIdEmpleado(empleadoObj.get("idEmpleado").getAsString());
                }
                if (empleadoObj.has("nombreCompleto") && !empleadoObj.get("nombreCompleto").isJsonNull()) {
                    empleado.setNombreCompleto(empleadoObj.get("nombreCompleto").getAsString());
                }
                transaccion.setEmpleadoResponsable(empleado);
            }
            
        } catch (Exception e) {
            throw new JsonParseException("Error al deserializar Transaccion: " + e.getMessage(), e);
        }
        
        return transaccion;
    }
}