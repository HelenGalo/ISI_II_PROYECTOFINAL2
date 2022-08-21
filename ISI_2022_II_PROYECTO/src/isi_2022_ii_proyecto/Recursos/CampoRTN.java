/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto.Recursos;

import isi_2022_ii_proyecto.VerificarOrden;
import isi_2022_ii_proyecto.VerificarOrdenADomicilio;
import rojeru_san.complementos.RSUtilities;

/**
 *
 * @author Edwin Rafael
 */
public class CampoRTN extends javax.swing.JFrame {
  VerificarOrden vo=null;
  VerificarOrdenADomicilio vod=null;

    public void setVo(VerificarOrden vo) {
        this.vo = vo;
    }

    public void setVod(VerificarOrdenADomicilio vod) {
        this.vod = vod;
    }
  

   
 

    /**
     * Creates new form VentanaEmergente1
     */
    public CampoRTN() {
       
        RSUtilities.setFullScreenJFrame(this);
        initComponents();
        RSUtilities.setOpaqueWindow(this, false);
        RSUtilities.setOpacityComponent(this.jPanel1, 150);
    }
    
    public boolean validar(){
        boolean a = false;
        if(rSTextFullRound1.getText().length()==0){
            jLabel8.setText("El valor ingresado está vacío");
            jLabel8.setVisible(true);
            
        }else{
            if(rSTextFullRound1.getText().length()<14){
            jLabel8.setText("El valor ingresado está incompleto");
            jLabel8.setVisible(true);
            
        }else{
            if(rSTextFullRound1.getText().length()>14){
            jLabel8.setText("El valor ingresado sobrepasa el límite permitido");
            jLabel8.setVisible(true);
            }else{
                if(rSTextFullRound1.getText().length()==14){
            jLabel8.setText("El valor ingresado es correcto");
            jLabel8.setVisible(true);
            a=true;
            }
                  }   
        
            }
            }
        return a;
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rSPanelOpacity1 = new RSMaterialComponent.RSPanelOpacity();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rSButtonRound2 = new rojerusan.RSButtonRound();
        rSTextFullRound1 = new rojeru_san.rsfield.RSTextFullRound();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        rSPanelOpacity1.setBackground(new java.awt.Color(0, 55, 133));
        rSPanelOpacity1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("FACTURA");
        rSPanelOpacity1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 450, -1));

        jLabel8.setBackground(new java.awt.Color(255, 102, 102));
        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("VALOR INGRESADO INCORRECTO");
        rSPanelOpacity1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 470, -1));

        rSButtonRound2.setBackground(new java.awt.Color(0, 153, 102));
        rSButtonRound2.setText("Si");
        rSButtonRound2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rSButtonRound2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRound2ActionPerformed(evt);
            }
        });
        rSPanelOpacity1.add(rSButtonRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 140, -1));

        rSTextFullRound1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rSTextFullRound1.setPlaceholder("Campo de RTN");
        rSTextFullRound1.setSoloNumeros(true);
        rSTextFullRound1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSTextFullRound1ActionPerformed(evt);
            }
        });
        rSTextFullRound1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rSTextFullRound1KeyReleased(evt);
            }
        });
        rSPanelOpacity1.add(rSTextFullRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 300, -1));

        rSLabelIcon2.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CONFIRMATION_NUMBER);
        rSPanelOpacity1.add(rSLabelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 40, 40));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 2, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ingrese en número RTN");
        rSPanelOpacity1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 80, 460, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSPanelOpacity1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSPanelOpacity1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonRound2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRound2ActionPerformed
        // TODO add your handling code here:
        if(validar()==true){
            if(vo!=null){
                vo.setRTNC(rSTextFullRound1.getText());
                this.setVisible(false);
                vo.pagar();
                this.dispose();
               
            }else{
                if(vod!=null){
                    vod.setRTNC(rSTextFullRound1.getText());
                    this.setVisible(false);
                    vod.pagar();
                    this.dispose();
                }
            }
        }
        
    }//GEN-LAST:event_rSButtonRound2ActionPerformed

    private void rSTextFullRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSTextFullRound1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSTextFullRound1ActionPerformed

    private void rSTextFullRound1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rSTextFullRound1KeyReleased
        // TODO add your handling code here:

        
    }//GEN-LAST:event_rSTextFullRound1KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CampoRTN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CampoRTN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CampoRTN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CampoRTN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CampoRTN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private rojerusan.RSButtonRound rSButtonRound2;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity1;
    private rojeru_san.rsfield.RSTextFullRound rSTextFullRound1;
    // End of variables declaration//GEN-END:variables
}
