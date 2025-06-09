package com.eurobank.proyectoaplicacionesdeescritorio.dao.adaptadores;

import com.eurobank.proyectoaplicacionesdeescritorio.modelo.*;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SucursalTypeAdapter implements JsonSerializer<Sucursal>, JsonDeserializer<Sucursal> {
    
    @Override
    public JsonElement serialize(Sucursal sucursal, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        
        // Campos simples
        jsonObject.addProperty("idSucursal", sucursal.getIdSucursal());
        jsonObject.addProperty("nombreSucursal", sucursal.getNombreSucursal());
        jsonObject.addProperty("direccionSucursal", sucursal.getDireccionSucursal());
        jsonObject.addProperty("telefonoSucursal", sucursal.getTelefonoSucursal());
        jsonObject.addProperty("correoSucursal", sucursal.getCorreoSucursal());
        
        // Gerente - solo idEmpleado y nombreCompleto
        if (sucursal.getGerente() != null) {
            JsonObject gerenteObj = new JsonObject();
            gerenteObj.addProperty("idEmpleado", sucursal.getGerente().getIdEmpleado());
            gerenteObj.addProperty("nombreCompleto", sucursal.getGerente().getNombreCompleto());
            jsonObject.add("gerente", gerenteObj);
        }
        
        // Contacto - solo idEmpleado y nombreCompleto
        if (sucursal.getContacto() != null) {
            JsonObject contactoObj = new JsonObject();
            contactoObj.addProperty("idEmpleado", sucursal.getContacto().getIdEmpleado());
            contactoObj.addProperty("nombreCompleto", sucursal.getContacto().getNombreCompleto());
            jsonObject.add("contacto", contactoObj);
        }
        
        // Cuentas asociadas - solo numeroCuenta
        if (sucursal.getCuentasAsociadas() != null) {
            JsonArray cuentasArray = new JsonArray();
            for (CuentaBancaria cuenta : sucursal.getCuentasAsociadas()) {
                JsonObject cuentaObj = new JsonObject();
                cuentaObj.addProperty("numeroCuenta", cuenta.getNumeroCuenta());
                cuentasArray.add(cuentaObj);
            }
            jsonObject.add("cuentasAsociadas", cuentasArray);
        }
        
        // Empleados asociados - solo idEmpleado
        if (sucursal.getEmpleadosAsociados() != null) {
            JsonArray empleadosArray = new JsonArray();
            for (Empleado empleado : sucursal.getEmpleadosAsociados()) {
                JsonObject empleadoObj = new JsonObject();
                empleadoObj.addProperty("idEmpleado", empleado.getIdEmpleado());
                empleadosArray.add(empleadoObj);
            }
            jsonObject.add("empleadosAsociados", empleadosArray);
        }
        
        return jsonObject;
    }

    @Override
    public Sucursal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
            throws JsonParseException {
        
        JsonObject jsonObject = json.getAsJsonObject();
        Sucursal sucursal = new Sucursal();
        
        // Campos simples
        sucursal.setIdSucursal(jsonObject.get("idSucursal").getAsString());
        sucursal.setNombreSucursal(jsonObject.get("nombreSucursal").getAsString());
        sucursal.setDireccionSucursal(jsonObject.get("direccionSucursal").getAsString());
        sucursal.setTelefonoSucursal(jsonObject.get("telefonoSucursal").getAsString());
        sucursal.setCorreoSucursal(jsonObject.get("correoSucursal").getAsString());
        
        // Gerente - solo idEmpleado y nombreCompleto
        JsonObject gerenteObj = jsonObject.getAsJsonObject("gerente");
        Gerente gerente = new Gerente();
        gerente.setIdEmpleado(gerenteObj.get("idEmpleado").getAsString());
        gerente.setNombreCompleto(gerenteObj.get("nombreCompleto").getAsString());
        sucursal.setGerente(gerente);
        
        // Contacto (Ejecutivo) - solo idEmpleado y nombreCompleto
        JsonObject contactoObj = jsonObject.getAsJsonObject("contacto");
        Ejecutivo contacto = new Ejecutivo();
        contacto.setIdEmpleado(contactoObj.get("idEmpleado").getAsString());
        contacto.setNombreCompleto(contactoObj.get("nombreCompleto").getAsString());
        sucursal.setContacto(contacto);
        
        // Cuentas asociadas - solo numeroCuenta
        JsonArray cuentasArray = jsonObject.getAsJsonArray("cuentasAsociadas");
        List<CuentaBancaria> cuentas = new ArrayList<>();
        for (JsonElement cuentaElement : cuentasArray) {
            JsonObject cuentaObj = cuentaElement.getAsJsonObject();
            CuentaBancaria cuenta = new CuentaBancaria();
            cuenta.setNumeroCuenta(cuentaObj.get("numeroCuenta").getAsString());
            // Los demás campos quedan como valores por defecto o null
            cuentas.add(cuenta);
        }
        sucursal.setCuentasAsociadas(cuentas);
        
        // Empleados asociados - solo idEmpleado
        JsonArray empleadosArray = jsonObject.getAsJsonArray("empleadosAsociados");
        List<Empleado> empleados = new ArrayList<>();
        for (JsonElement empleadoElement : empleadosArray) {
            JsonObject empleadoObj = empleadoElement.getAsJsonObject();
            Empleado empleado = new Empleado(){};
            empleado.setIdEmpleado(empleadoObj.get("idEmpleado").getAsString());
            // Los demás campos quedan como valores por defecto o null
            empleados.add(empleado);
        }
        sucursal.setEmpleadosAsociados(empleados);
        
        return sucursal;
    }
}