/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package isi_2022_ii_proyecto;

import isi_2022_ii_proyecto.Conexion.ConexionBD;
import java.sql.Connection;

/**
 *
 * @author Edwin Rafael
 */
public class ISI_2022_II_PROYECTO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         ConexionBD conexion = new ConexionBD();
         Connection con = conexion.conexion();
    }
    
}
