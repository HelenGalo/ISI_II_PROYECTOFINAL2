/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto.Recursos;

import isi_2022_ii_proyecto.Inicio;
import javax.swing.JOptionPane;
import rojerusan.RSProgressCircleAnimated;

/**
 *
 * @author Edwin Rafael
 */
public class Conteos implements Runnable {
    
  
    LoginVentana lv;

    public void setInicio(LoginVentana lv) {
        this.lv = lv;
    }

   

    @Override
    public void run() {
           try {
             

            for (int i = 0; i <= 100; i++) {
                Thread.sleep(38);
                if (i == 100) {
                    lv.GoCambiarC();
                    
        
                }
            }
        } catch (Exception e) {
        }
    }
    
}
