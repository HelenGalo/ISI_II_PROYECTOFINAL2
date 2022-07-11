/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi_2022_ii_proyecto.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Edwin Rafael
 */
public class ConexionBD {
     Connection conectar = null;

    public Connection conexion() {

        try {
            String url = "jdbc:mysql://162.241.62.192:3306/fhopenet_GEVEC";
            String usuario = "fhopenet_gestionador";
            String password = "#+6ePODf*=,}";
            
            conectar = DriverManager.getConnection(url,usuario,password);
                    
            
                    
        }catch(Exception e){  
            JOptionPane.showMessageDialog(null, "Fallo " + e.getMessage());
        }
        
      
            return conectar;
        }
}
