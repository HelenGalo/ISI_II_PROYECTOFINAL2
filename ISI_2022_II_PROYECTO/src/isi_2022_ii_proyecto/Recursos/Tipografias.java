/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto.Recursos;

import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author Edwin Rafael
 */
public class Tipografias {
    
   private Font font = null;
   public String Montserath = "Montserrat-SemiBold.ttf";
   
   
   public Font fuente(String fontName, int estilo, float tama){
       
       try {
           InputStream is = getClass().getResourceAsStream(fontName);
           font =Font.createFont(Font.TRUETYPE_FONT, is);
       } catch (Exception e) {
           System.err.println(fontName + "No se cargo la fuente");
           font = new Font("Arial", Font.PLAIN, 14);
       }
       Font tfont = font.deriveFont(estilo, tama);
       return tfont;
   }
   
    
    
    
    
}
