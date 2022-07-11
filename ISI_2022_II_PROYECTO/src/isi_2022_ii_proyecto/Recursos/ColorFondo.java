package isi_2022_ii_proyecto.Recursos;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Somos programadores
 */


public class ColorFondo extends JPanel {


   
    protected void paintComponent(Graphics g) {

        // this.setBackground(color1);
        Graphics2D G2D = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();
        
         Color color1 = new Color(0x1363DF);
         Color color2 = new Color(0xAA888);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);

        G2D.setPaint(gp);
        G2D.fillRect(0, 0, w, h);

    }


}
