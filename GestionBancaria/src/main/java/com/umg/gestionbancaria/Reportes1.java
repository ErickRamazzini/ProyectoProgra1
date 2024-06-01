/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.gestionbancaria;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author erick.ramazzini
 */
public class Reportes1 {
    String cuentaArchivo = System.getProperty("user.dir") + "/registro/Cuenta.txt";
    
     public void generarReporteExcel() {
         
        // Se declara la variable en donde se almacenarán los datos obtenidos
        List<Apertura1> lista = new ArrayList<>();

        // Obtener los datos
        lista = obtenerListaCuentas();

        // Establecer el nombre del reporte
        String nombreReporte = System.getProperty("user.dir") + "/reporte.xlsx";

        // Crear un nuevo libro de trabajo
        Workbook workbook = new XSSFWorkbook();

        // Crear una hoja
        Sheet hoja = workbook.createSheet("Reporte");

        // Establecer los títulos del encabezado
        String[] titulos = {"Número de Cuenta", "Saldo Actual"};

        // Crear la fila del encabezado
        Row filaEncabezados = hoja.createRow(0);

        // Llenar la fila del encabezado
        for (int i = 0; i < titulos.length; i++) {
            Cell celda = filaEncabezados.createCell(i);
            celda.setCellValue(titulos[i]);
            hoja.autoSizeColumn(i);
        }

        // Llenar las filas con los datos de las cuentas
        int numFila = 1; // La primera fila es la de los encabezados
        for (Apertura1 tarea : lista) {
            Row fila = hoja.createRow(numFila++);

            fila.createCell(0).setCellValue(tarea.getNumeroCuenta());
            fila.createCell(1).setCellValue(tarea.getMontoIncial());

            // Ajustar el tamaño de las columnas
            for (int i = 0; i < titulos.length; i++) {
                hoja.autoSizeColumn(i);
            }
        }

        // Escribir el archivo en el sistema de archivos
        try (FileOutputStream fileOut = new FileOutputStream(nombreReporte)) {
            workbook.write(fileOut);
            System.out.println("Reporte generado exitosamente en: " + nombreReporte);
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }

     // creamo una lista  para  leer los archivos del documento de texto
    private List<Apertura1> obtenerListaCuentas() {
        List<Apertura1> lista = new ArrayList<>();

        // leemos el archivo de texto y  extrameomos ds datos para el reporte de excel monto y numero de cuenta
        try (BufferedReader br = new BufferedReader(new FileReader(cuentaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partesLine = linea.split(",");

                String NumeroCuenta = partesLine[0];
                double MontoIncial = Double.parseDouble(partesLine[1]);
              
                

                Apertura1 tarea = new Apertura1();
                tarea.setNumeroCuenta(NumeroCuenta);
                tarea.getMontoIncial();

                lista.add(tarea);
            }
        } catch (Exception e) {
            System.out.println("ERROR AL LEER LAS CUENTAS: " + e.getMessage());
        }

        return lista;
    }
    // este metod va servir pra identificar si existen la cuentas

    private boolean cuentaExiste(String NumeroCuenta) {
        try (BufferedReader br = new BufferedReader(new FileReader(cuentaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partesLinea = linea.split(",");
                if (partesLinea[0].equals(NumeroCuenta)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de cuentas: " + e.getMessage());
        }
        return false;
    }
}
