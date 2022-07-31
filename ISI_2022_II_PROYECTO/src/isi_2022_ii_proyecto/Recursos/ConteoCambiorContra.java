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
public class ConteoCambiorContra implements Runnable {
    
    RSProgressCircleAnimated barra;
    LoginVentana lv;

    public void setInicio(LoginVentana lv) {
        this.lv = lv;
    }

    public RSProgressCircleAnimated getBarra() {
        return barra;
    }

    public void setBarra(RSProgressCircleAnimated barra) {
        this.barra = barra;
    }

    @Override
    public void run() {
           try {
             
            barra.setVisible(true);

            for (int i = 0; i <= 100; i++) {
                Thread.sleep(40);
                if (i == 100) {
                    lv.mensajevalidado();
                    
        
                }
            }
        } catch (Exception e) {
        }
    }
    
}
