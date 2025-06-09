package com.eurobank.proyectoaplicacionesdeescritorio.util;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileOutputStream;
import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase utilitaria genérica para exportar listas de objetos a diferentes formatos
 * @author Guillermo
 */

public class ExportadorGenerico {

    private static final Logger LOG = LogManager.getLogger(ExportadorGenerico.class);

    public enum TipoExportacion {
        CSV("Archivo CSV", "*.csv"),
        EXCEL_XLSX("Archivo Excel (XLSX)", "*.xlsx"),
        EXCEL_XLS("Archivo Excel (XLS)", "*.xls");

        private final String descripcion;
        private final String extension;

        TipoExportacion(String descripcion, String extension) {
            this.descripcion = descripcion;
            this.extension = extension;
        }

        public String getDescripcion() { return descripcion; }
        public String getExtension() { return extension; }
    }

    /**
     * Exporta una lista de objetos al formato especificado
     * @param <T> Tipo de objeto a exportar
     * @param datos Lista de objetos a exportar
     * @param tipoExportacion Tipo de archivo a generar
     * @param stage Ventana padre para el FileChooser
     * @param nombreArchivo Nombre sugerido para el archivo
     * @return true si la exportación fue exitosa, false en caso contrario
     */
    public static <T> boolean exportar(List<T> datos, TipoExportacion tipoExportacion,
                                       Stage stage, String nombreArchivo) {
        if (datos == null || datos.isEmpty()) {
            AlertaUtil.mostrarAlerta("Advertencia", "No hay datos para exportar",
                    Alert.AlertType.WARNING);
            return false;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo");
        fileChooser.setInitialFileName(nombreArchivo);

        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter(
                tipoExportacion.getDescripcion(), tipoExportacion.getExtension());
        fileChooser.getExtensionFilters().add(filtro);

        File archivo = fileChooser.showSaveDialog(stage);
        if (archivo != null) {
            try {
                switch (tipoExportacion) {
                    case CSV:
                        return exportarCSV(datos, archivo);
                    case EXCEL_XLSX:
                        return exportarExcel(datos, archivo, true);
                    case EXCEL_XLS:
                        return exportarExcel(datos, archivo, false);
                    default:
                        return false;
                }
            } catch (Exception e) {
                LOG.error("Error al exportar archivo", e);
                AlertaUtil.mostrarAlerta("Error", "Error al exportar el archivo: " + e.getMessage(),
                        Alert.AlertType.ERROR);
                return false;
            }
        }
        return false;
    }

    /**
     * Exporta los datos a formato CSV
     */
    private static <T> boolean exportarCSV(List<T> datos, File archivo) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo))) {
            T primerObjeto = datos.get(0);
            String[] headers = obtenerNombresColumnas(primerObjeto);
            writer.writeNext(headers);

            for (T objeto : datos) {
                String[] fila = obtenerValoresFila(objeto);
                writer.writeNext(fila);
            }

            AlertaUtil.mostrarAlerta("Éxito", "Archivo CSV exportado correctamente",
                    Alert.AlertType.INFORMATION);
            return true;
        }
    }

    /**
     * Exporta los datos a formato Excel
     */
    private static <T> boolean exportarExcel(List<T> datos, File archivo, boolean esXlsx) throws IOException {
        Workbook workbook = esXlsx ? new XSSFWorkbook() : new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Datos");

        // Crear estilo para el header
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        HSSFColor.HSSFColorPredefined IndexedColors = null;
        headerStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        T primerObjeto = datos.get(0);
        String[] headers = obtenerNombresColumnas(primerObjeto);

        // Crear fila de headers
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Llenar datos
        int rowNum = 1;
        for (T objeto : datos) {
            Row row = sheet.createRow(rowNum++);
            String[] valores = obtenerValoresFila(objeto);
            for (int i = 0; i < valores.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(valores[i]);
            }
        }

        // Ajustar ancho de columnas
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(archivo)) {
            workbook.write(fileOut);
        }

        workbook.close();

        AlertaUtil.mostrarAlerta("Éxito", "Archivo Excel exportado correctamente",
                Alert.AlertType.INFORMATION);
        return true;
    }

    /**
     * Obtiene los nombres de las columnas usando reflexión
     */
    private static <T> String[] obtenerNombresColumnas(T objeto) {
        Class<?> clase = objeto.getClass();
        Field[] campos = clase.getDeclaredFields();

        List<String> nombres = new ArrayList<>();
        for (Field campo : campos) {
            if (!campo.isSynthetic() && !Modifier.isStatic(campo.getModifiers())) {
                String nombre = campo.getName();
                // Convertir camelCase a formato más legible
                nombre = nombre.replaceAll("([a-z])([A-Z])", "$1 $2");
                nombre = Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1);
                nombres.add(nombre);
            }
        }

        return nombres.toArray(new String[0]);
    }

    /**
     * Obtiene los valores de una fila usando reflexión
     */
    private static <T> String[] obtenerValoresFila(T objeto) {
        Class<?> clase = objeto.getClass();
        Field[] campos = clase.getDeclaredFields();

        List<String> valores = new ArrayList<>();
        for (Field campo : campos) {
            if (!campo.isSynthetic() && !Modifier.isStatic(campo.getModifiers())) {
                try {
                    campo.setAccessible(true);
                    Object valor = campo.get(objeto);
                    valores.add(valor != null ? valor.toString() : "");
                } catch (IllegalAccessException e) {
                    // Intentar con getter
                    try {
                        String nombreGetter = "get" + Character.toUpperCase(campo.getName().charAt(0)) +
                                campo.getName().substring(1);
                        Method getter = clase.getMethod(nombreGetter);
                        Object valor = getter.invoke(objeto);
                        valores.add(valor != null ? valor.toString() : "");
                    } catch (Exception ex) {
                        valores.add("");
                    }
                }
            }
        }

        return valores.toArray(new String[0]);
    }
}
