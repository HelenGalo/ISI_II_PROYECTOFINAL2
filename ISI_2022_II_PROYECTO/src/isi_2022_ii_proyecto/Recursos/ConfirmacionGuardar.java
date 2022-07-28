/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto.Recursos;

import isi_2022_ii_proyecto.AgregarCaja;
import isi_2022_ii_proyecto.AgregarClientes;
import isi_2022_ii_proyecto.AgregarCuentaBancaria;
import isi_2022_ii_proyecto.AgregarEnvio;
import isi_2022_ii_proyecto.AgregarProveedor;
import isi_2022_ii_proyecto.AperturarCajas;
import isi_2022_ii_proyecto.Banco;
import rojeru_san.complementos.RSUtilities;

/**
 *
 * @author Edwin Rafael
 */
public class ConfirmacionGuardar extends javax.swing.JFrame {
    AgregarCuentaBancaria gcuenta;
    AgregarEnvio genvio;
    AgregarCaja gcaja;
    AgregarProveedor gprov;
    AgregarClientes gcliente;

    public void setGcliente(AgregarClientes gcliente) {
        this.gcliente = gcliente;
    }

    
    
    
    public void setGprov(AgregarProveedor gprov) {
        this.gprov = gprov;
    }
    
    

    public void setGcaja(AgregarCaja gcaja) {
        this.gcaja = gcaja;
    }
    

    public void setGenvio(AgregarEnvio genvio) {
        this.genvio = genvio;
    }

    public void setGcuenta(AgregarCuentaBancaria gcuenta) {
        this.gcuenta = gcuenta;
    }
    String tipo="";

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    

    /**
     * Creates new form Confirmacion
     */
    public ConfirmacionGuardar() {
         RSUtilities.setFullScreenJFrame(this);
        initComponents();
        RSUtilities.setOpaqueWindow(this, false);
        RSUtilities.setOpacityComponent(this.jPanel1, 150);
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
        rSLabelIcon1 = new RSMaterialComponent.RSLabelIcon();
        rSButtonIcon_new1 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new2 = new newscomponents.RSButtonIcon_new();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        rSPanelOpacity1.setBackground(new java.awt.Color(0, 55, 133));
        rSPanelOpacity1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon1.setToolTipText("");
        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HELP);
        rSPanelOpacity1.add(rSLabelIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 150, 140));

        rSButtonIcon_new1.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIcon_new1.setText("Cancelar");
        rSButtonIcon_new1.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rSButtonIcon_new1.setFocusable(false);
        rSButtonIcon_new1.setForegroundIcon(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new1.setForegroundText(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        rSButtonIcon_new1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new1ActionPerformed(evt);
            }
        });
        rSPanelOpacity1.add(rSButtonIcon_new1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 130, 60));

        rSButtonIcon_new2.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIcon_new2.setForeground(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new2.setText("Aceptar");
        rSButtonIcon_new2.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rSButtonIcon_new2.setFocusable(false);
        rSButtonIcon_new2.setForegroundIcon(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new2.setForegroundText(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHECK_BOX);
        rSButtonIcon_new2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new2ActionPerformed(evt);
            }
        });
        rSPanelOpacity1.add(rSButtonIcon_new2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 130, 60));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("ATENCIÓN ");
        rSPanelOpacity1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("¿ESTÁ SEGURO DE REALIZAR LOS CAMBIOS?");
        rSPanelOpacity1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

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
                .addComponent(rSPanelOpacity1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonIcon_new1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new1ActionPerformed

    private void rSButtonIcon_new2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new2ActionPerformed
        // TODO add your handling code here:
        if(tipo=="GBancos"){
            gcuenta.setEstadoagregar(true);
            gcuenta.validarConfirmacion();
            this.dispose();
        }
        if(tipo=="GEnvios"){
            genvio.setEstadoagregar(true);
            genvio.validarConfirmacion();
            this.dispose();
        }
        if(tipo=="GCaja"){
            gcaja.setEstadoagregar(true);
            gcaja.validarConfirmacion();
            this.dispose();
        }
        if(tipo=="GProveedor"){
            gprov.setEstadoagregar(true);
            gprov.validarConfirmacion();
            this.dispose();
        }
         
        
          if(tipo=="GCliente"){
            gcliente.setEstadoagregar(true);
            gcliente.validarConfirmacion();
            this.dispose();
        }
        
    }//GEN-LAST:event_rSButtonIcon_new2ActionPerformed

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
            java.util.logging.Logger.getLogger(ConfirmacionGuardar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfirmacionGuardar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfirmacionGuardar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfirmacionGuardar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfirmacionGuardar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new1;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new2;
    private RSMaterialComponent.RSLabelIcon rSLabelIcon1;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity1;
    // End of variables declaration//GEN-END:variables

  
}
