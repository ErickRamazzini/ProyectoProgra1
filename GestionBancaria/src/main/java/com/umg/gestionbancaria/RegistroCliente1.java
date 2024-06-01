/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.gestionbancaria;
// de claramo las importaciones para el amnejo de archivos de texto, listas, array
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class RegistroCliente1 {
  
    //Declaramos la ruta dela archivo que vamos a utilizar que vamos  a utilizar
    String rutaArchivo = System.getProperty("user.dir") + "/registro/registro.txt";
    
  //  Variables que vamos a utilizar
   private String DPI="";
    private String Nombre="";
    private String Apellido="";
    private String Direccion="";
    private String Telefono="";
    private String Correo="";
    private String Ocupacion="";
    private String Ingreso="";

    // creamos un metodo para acceder des la interfaz
    public void setguardar (String DPI, String Nombre,String Apellido, String Direccion, String Telefono, String Correo, String Ocupacion, String Ingreso ){
    
    this.DPI = DPI;
    this.Nombre = Nombre;
    this.Apellido = Apellido;
    this.Direccion = Direccion;
    this.Telefono = Telefono;
    this.Correo = Correo;
    this.Ocupacion = Ocupacion;
    this.Ingreso = Ingreso;
    
    }

    public String getDPI() {
        return this.DPI;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public String getApellido() {
        return this.Apellido;
    }

    public String getDireccion() {
        return this.Direccion;
    }

    public String getTelefono() {
        return this.Telefono;
    }

    public String getCorreo() {
        return this.Correo;
    }

    public String getOcupacion() {
        return this.Ocupacion;
    }

    public String getIngreso() {
        return this.Ingreso;
    }
    
    // creamos un metodo para hacer el proceso de guardar el registro
    public void registrar(){
    
    FileWriter escritor = null;
        BufferedWriter bw = null;
        
        try {
            //
            escritor = new FileWriter(rutaArchivo, true);
            bw = new BufferedWriter(escritor);
            // variable que se vaalmacenar en el archivo de texto
            String tarea = "DPI: "+this.DPI+ ", NOMBRE: " + this.Nombre + ", APELLIDO: " + this.Apellido + ", DIRECCION: " + this.Direccion +
            ", TELEFONO: "+this.Telefono + ", OCUPACIÓN: "+ this.Ocupacion +", INGRESOS MENSUALES: "+this.Ingreso;
            
            bw.write(tarea);
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Error al guardar la tarea: " + e.getMessage());
        } finally {
            // CERRAR EL ARCHIVO
            try {
                bw.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el archivo. " + e.getMessage());
            }
        }
        
    }
    
    // declarmo en metodo para que se pueda accder a la clase des la interfaz
    
    public void setActualizar (String txtDPI, String txtDireccion, String txtTelefono, String txtCorreo, String txtOcupacion, String txtIngreso ){
    // variables  autilizar en este proceso
    this.DPI = txtDPI;
    this.Direccion = txtDireccion;
    this.Telefono = txtTelefono;
    this.Correo = txtCorreo;
    this.Ocupacion = txtOcupacion;
    this.Ingreso = txtIngreso;
    
    }
    
    // creamos un metodo actualizar para registrar los datos que quiere actualizar el cliente
    public void actualizar() {
        List<String> registros = new ArrayList<>();
        BufferedReader br = null;
        BufferedWriter bw = null;

        // escribimos los datsoen en el nuevo archivo de texto
        try {
            br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;

            // creamo un ciclo bucle para comparar linea por  que concida con el dpi y actulizar dicha liena con el codigo
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(", ");
                if (datos[0].equals("DPI: " + this.DPI)) {
                    String registroActualizado = "DPI: " + this.DPI + ", NOMBRE: " + datos[1].split(": ")[1] + ", APELLIDO: " + datos[2].split(": ")[1] +  
                            ", DIRECCION: " + this.Direccion + ", TELEFONO: " + this.Telefono + ", CORREO: " + this.Correo + 
                            ", OCUPACION: " +this.Ocupacion + ", INGRESOS MENSUALES: " + this.Ingreso;
                    registros.add(registroActualizado);
                } else {
                    registros.add(linea);
                }
            }
            br.close();

            bw = new BufferedWriter(new FileWriter(rutaArchivo));
            for (String registro : registros) {
                bw.write(registro);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
                if (bw != null) bw.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }
    }
    

    
     
    
        
    

