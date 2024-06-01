/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.gestionbancaria;

/**
 *
 * @author erick.ramazzini
 */
// Importaciones para el manejo de archivos de texto y listas
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RealizarDeposito1 {
    
    // definimos las rutas de los archivos de texto que vamos a usar
    String rutaArchivo = System.getProperty("user.dir") + "/registro/Cuenta.txt";
    String arDeposito = System.getProperty("user.dir") + "/registro/Deposito.txt";
    
    // declaramos las variables que vamos utilizar
    private String Cuenta ;
    private double Deposito ;

   
// metodo para porder llamarlo desde la interfaz
    public void setRealizarDeposito1(String Cuenta, double Deposito) {
        this.Cuenta = Cuenta;
        this.Deposito = Deposito;
    }

    public String getCuenta() {
        return Cuenta;
    }

    

    public double getDeposito() {
        return Deposito;
    }

    

    
    public void Deposito(){
     
   
        try {
            

            // Leer todas las cuentas y saldos en el archivo de texto llamado cuenta.txt
            BufferedReader leer = new BufferedReader(new FileReader(rutaArchivo));
            List<String> cuentas = new ArrayList<>();
            String linea;
            boolean cuentaEncontrada = false;
            double nuevoSaldo = 0.0;

            // creamos un bucle para leer  linea por linea
            while ((linea = leer.readLine()) != null) {
                String[] partes = linea.split(",");
                String cuenta = partes[0];
                double saldo = Double.parseDouble(partes[1]);

                if (cuenta.equals(Cuenta)) {
                    cuentaEncontrada = true;
                    saldo += Deposito;
                    nuevoSaldo = saldo;
                }
                
                cuentas.add(cuenta + "," + saldo);
            }
            leer.close();
//  para madar un mensaje emergente
            if (!cuentaEncontrada) {
                JOptionPane.showMessageDialog(null, "Número de cuenta incorrecto");
                return;
            }

            // Escribir las cuentas actualizadas en el archivo
            BufferedWriter escribirCuenta = new BufferedWriter(new FileWriter(rutaArchivo));
            for (String cuenta : cuentas) {
                escribirCuenta.write(cuenta);
                escribirCuenta.newLine();
            }
            escribirCuenta.close();

            // Registrar el depósito en el archivo de depósitos
            BufferedWriter escribirDeposito = new BufferedWriter(new FileWriter(arDeposito, true));
            escribirDeposito.write("Cuenta: " + this.Cuenta + ", Monto: " + this.Deposito);
            escribirDeposito.newLine();
            escribirDeposito.close();

            // Notificar al usuario
            JOptionPane.showMessageDialog(null, "Depósito realizado con éxito. Nuevo saldo: " + nuevoSaldo);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al realizar el depósito");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Monto inválido");
        }
    }

   
    
    
    
    
    }
    
