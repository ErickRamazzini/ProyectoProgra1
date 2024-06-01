
package com.umg.gestionbancaria;

/**
 *
 * @author erick.ramazzini
 */
// importaciones para el manejo de archivo y listas
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

// nombre de la clase
public class RetirarDinero1 {
     // definimos las rutas de los archivos de texto a utilizar
    String rutaArchivo = System.getProperty("user.dir") + "/registro/Cuenta.txt";
    String retiro = System.getProperty("user.dir") + "/registro/Retirar.txt";
    // declaramos las variables a utilizar
    private String cuenta;
    private double Retirar;
    
    public void setRetirarDinero1(String cuenta, double Retirar){
    
        this.cuenta = cuenta;
        this.Retirar=Retirar;
    }

    public String getCuenta() {
        return cuenta;
    }

    public double getRetirar() {
        return Retirar;
    }
    
    // metodo para leer el arcchivo de texto y aguardar los datos en un lsta
    public void retirar(){
   List<String> lineasCuentas = new ArrayList<>();
        boolean cuentaEncontrada = false;
        boolean saldoSuficiente = false;

        // Leer el archivo de cuentas y procesar cada línea
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String numeroCuenta = partes[0].trim();
                double saldo = Double.parseDouble(partes[1].trim());

                if (numeroCuenta.equals(cuenta)) {
                    cuentaEncontrada = true;
                    if (saldo >= this.Retirar) {
                        saldo -= this.Retirar;
                        saldoSuficiente = true;
                    }
                }
                lineasCuentas.add(numeroCuenta + "," + saldo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de cuentas: " + e.getMessage());
            return;
        }

        // Verificar si la cuenta fue encontrada y si tenía suficiente saldo
        if (!cuentaEncontrada) {
            JOptionPane.showMessageDialog(null, "Número de cuenta no encontrado.");
            return;
        }
        if (!saldoSuficiente) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
            return;
        }

        // Escribir el nuevo saldo en el archivo de cuentas
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String lineaCuenta : lineasCuentas) {
                bw.write(lineaCuenta);
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el archivo de cuentas: " + e.getMessage());
            return;
        }

        // Registrar la operación de retiro
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(retiro, true))) {
            bw.write("Cuenta: " + cuenta + ", Retiro: " + this.Retirar);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el registro de retiro: " + e.getMessage());
            return;
        }

        JOptionPane.showMessageDialog(null, "Retiro realizado con éxito.");
    }
}
    
//////////////////////////////////

