/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package isi_2022_ii_proyecto;

import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.ColorFondo;
import isi_2022_ii_proyecto.Recursos.ConfirmacionDeshabilitarCuenta;
import isi_2022_ii_proyecto.Recursos.TotalBancos;
import isi_2022_ii_proyecto.Recursos.VentanaEmergente1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import rojeru_san.complementos.RSUtilities;
/**
 *
 * @author orell
 */
public class Banco extends javax.swing.JFrame {

    /**
     * Creates new form AgregarCliente
     */
    String codigob;
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();
    boolean estadoC = false;
    
  
     String usuario;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
        jLabel15.setText("Usuario en sesion: "+usuario);
    }
    
    public Banco() {
        RSUtilities.setFullScreenJFrame(this);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
        listar();
      

     
    
        
    }
    
    
    public void conectar(){
        conexion.setBanco(this);
        con = conexion.conexion();
    }
     public void validarconexion(){

        if(con==null){
            conectar();
            
            
        }
        
    }
    
    public void deshabilitar(){
        String SQL = "UPDATE  CuentasBancarias SET IdEstado=? Where IdCuenta="+codigob;
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
          
            preparedStmt.setInt (1, 0);
            preparedStmt.execute();
            
            VentanaEmergente1 ve = new VentanaEmergente1();
            ve.setVisible(true);

        } catch (Exception e) {
            con=null;
        validarconexion();
            
            System.out.println("ERROR" + e.getMessage());   
        }
    }

      public void listar(){
          DecimalFormat formato = new DecimalFormat("##,###.00");
        String[] registros = new String[5];
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancos.getModel();

        String SQL = "SELECT cb.IdCuenta, b.Nombre, tc.Descripcion, tm.Moneda, cb.TotalenCuenta FROM CuentasBancarias cb\n" +
                    "INNER JOIN Bancos b ON b.IdBanco = cb.IdBanco\n" +
                    "INNER JOIN TipoCuenta tc ON tc.IdTipoCuenta = cb.IdTipoCuenta\n"+
                    "INNER JOIN TipoMoneda tm ON tm.IdTipoMoneda = cb.IdTipoMoneda WHERE cb.IdEstado=1";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                 
                registros[0] = rs.getString("IdCuenta");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Descripcion");
                registros[3] = rs.getString("Moneda");
                registros[4] = formato.format(rs.getDouble("TotalenCuenta"));
                modelo.addRow(registros);
            }

            JTableBancos.setModel(modelo);
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        
           con=null;
        validarconexion();
        }
    }
     
      public void listarHabilitados(){
          DecimalFormat formato = new DecimalFormat("##,###.00");
        String[] registros = new String[5];
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancos.getModel();

        String SQL = "SELECT cb.IdCuenta, b.Nombre, tc.Descripcion, tm.Moneda, cb.TotalenCuenta FROM CuentasBancarias cb\n" +
                    "INNER JOIN Bancos b ON b.IdBanco = cb.IdBanco\n" +
                    "INNER JOIN TipoCuenta tc ON tc.IdTipoCuenta = cb.IdTipoCuenta\n"+
                "INNER JOIN TipoMoneda tm ON tm.IdTipoMoneda = cb.IdTipoMoneda WHERE cb.IdEstado=1";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                registros[0] = rs.getString("IdCuenta");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Descripcion");
                registros[3] = rs.getString("Moneda");
                registros[4] = formato.format(rs.getDouble("TotalenCuenta"));
                modelo.addRow(registros);
            }

            JTableBancos.setModel(modelo);
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
           con=null;
        validarconexion();
        }
    }
      public void listarDeshabilitados(){
          DecimalFormat formato = new DecimalFormat("##,###.00");
        String[] registros = new String[5];
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancos.getModel();

        String SQL = "SELECT cb.IdCuenta, b.Nombre, tc.Descripcion, tm.Moneda, cb.TotalenCuenta FROM CuentasBancarias cb\n" +
                    "INNER JOIN Bancos b ON b.IdBanco = cb.IdBanco\n" +
                    "INNER JOIN TipoCuenta tc ON tc.IdTipoCuenta = cb.IdTipoCuenta\n"+
                "INNER JOIN TipoMoneda tm ON tm.IdTipoMoneda = cb.IdTipoMoneda WHERE cb.IdEstado=0";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                registros[0] = rs.getString("IdCuenta");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Descripcion");
                registros[3] = rs.getString("Moneda");
                registros[4] = formato.format(rs.getDouble("TotalenCuenta"));
                modelo.addRow(registros);
            }

            JTableBancos.setModel(modelo);
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
       
           con=null;
        validarconexion();
        }
    }
     
     public void limpiartabla(){
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancos.getModel();
        while (modelo.getRowCount() > 0)
        {
        modelo.removeRow(0);
        }
        
        
        
       
        
     
    }
    
    
    
    private void buscarNombre(){
        String[] registros = new String[5];
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancos.getModel();

        String SQL = "SELECT cb.IdCuenta, b.Nombre, tc.Descripcion, tm.Moneda, cb.TotalenCuenta FROM CuentasBancarias cb\n" +
                    "INNER JOIN Bancos b ON b.IdBanco = cb.IdBanco\n" +
                    "INNER JOIN TipoCuenta tc ON tc.IdTipoCuenta = cb.IdTipoCuenta\n"+
                "INNER JOIN TipoMoneda tm ON tm.IdTipoMoneda = cb.IdTipoMoneda WHERE b.Nombre LIKE '"+JTextbuscar.getText()+"%';";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                registros[0] = rs.getString("IdCuenta");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Descripcion");
                registros[3] = rs.getString("Moneda");
                registros[4] = rs.getString("TotalenCuenta");
                modelo.addRow(registros);
            }

            JTableBancos.setModel(modelo);
            

        } catch (SQLException e) {
            con=null;
        validarconexion();
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
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
        Header = new javax.swing.JPanel();
        iconminmaxclose = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        linesetting4 = new javax.swing.JPanel();
        linesetting5 = new javax.swing.JPanel();
        linesetting13 = new javax.swing.JPanel();
        rSButtonIconOne6 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne7 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne8 = new RSMaterialComponent.RSButtonIconOne();
        menu = new javax.swing.JPanel();
        MenuIcon = new javax.swing.JPanel();
        linesetting = new javax.swing.JPanel();
        linehidemenu = new javax.swing.JPanel();
        linesetting1 = new javax.swing.JPanel();
        linesetting2 = new javax.swing.JPanel();
        linesetting7 = new javax.swing.JPanel();
        rSLabelIcon7 = new rojerusan.RSLabelIcon();
        linesetting8 = new javax.swing.JPanel();
        rSLabelIcon9 = new rojerusan.RSLabelIcon();
        linesetting9 = new javax.swing.JPanel();
        rSLabelIcon10 = new rojerusan.RSLabelIcon();
        linesetting10 = new javax.swing.JPanel();
        linesetting11 = new javax.swing.JPanel();
        rSLabelIcon11 = new rojerusan.RSLabelIcon();
        menuhide = new javax.swing.JPanel();
        rSButtonIcon_new3 = new newscomponents.RSButtonIcon_new();
        linesetting6 = new javax.swing.JPanel();
        rSLabelIcon3 = new rojerusan.RSLabelIcon();
        rSLabelIcon4 = new rojerusan.RSLabelIcon();
        rSLabelIcon5 = new rojerusan.RSLabelIcon();
        linesetting12 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rSButtonIcon_new5 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new6 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new7 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new9 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new10 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new13 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new14 = new newscomponents.RSButtonIcon_new();
        dashboardview = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rSPanelOpacity1 = new RSMaterialComponent.RSPanelOpacity();
        jLabel7 = new javax.swing.JLabel();
        rSLabelIcon8 = new rojerusan.RSLabelIcon();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rSLabelIcon17 = new rojerusan.RSLabelIcon();
        rSLabelIcon12 = new rojerusan.RSLabelIcon();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableBancos = new rojerusan.RSTableMetro1();
        jPanel4 = new javax.swing.JPanel();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        jLabel6 = new javax.swing.JLabel();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jLabel15 = new javax.swing.JLabel();
        JTextbuscar = new RSMaterialComponent.RSTextFieldIconUno();
        rSButtonIcon_new8 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new11 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new12 = new newscomponents.RSButtonIcon_new();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setMinimumSize(new java.awt.Dimension(150, 50));
        Header.setPreferredSize(new java.awt.Dimension(800, 50));

        iconminmaxclose.setBackground(new java.awt.Color(5, 10, 46));

        javax.swing.GroupLayout iconminmaxcloseLayout = new javax.swing.GroupLayout(iconminmaxclose);
        iconminmaxclose.setLayout(iconminmaxcloseLayout);
        iconminmaxcloseLayout.setHorizontalGroup(
            iconminmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        iconminmaxcloseLayout.setVerticalGroup(
            iconminmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(20, 101, 187));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("SYSTEM");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("GEVEC");

        linesetting4.setBackground(new java.awt.Color(20, 101, 187));
        linesetting4.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesetting4Layout = new javax.swing.GroupLayout(linesetting4);
        linesetting4.setLayout(linesetting4Layout);
        linesetting4Layout.setHorizontalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        linesetting4Layout.setVerticalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        linesetting5.setBackground(new java.awt.Color(20, 101, 187));
        linesetting5.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        linesetting13.setBackground(new java.awt.Color(20, 101, 187));
        linesetting13.setPreferredSize(new java.awt.Dimension(50, 10));

        rSButtonIconOne6.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIconOne6.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FULLSCREEN);
        rSButtonIconOne6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne6ActionPerformed(evt);
            }
        });

        rSButtonIconOne7.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIconOne7.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        rSButtonIconOne7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne7ActionPerformed(evt);
            }
        });

        rSButtonIconOne8.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIconOne8.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REMOVE);
        rSButtonIconOne8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout linesetting13Layout = new javax.swing.GroupLayout(linesetting13);
        linesetting13.setLayout(linesetting13Layout);
        linesetting13Layout.setHorizontalGroup(
            linesetting13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting13Layout.createSequentialGroup()
                .addComponent(rSButtonIconOne8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIconOne6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIconOne7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        linesetting13Layout.setVerticalGroup(
            linesetting13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(linesetting13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonIconOne8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4))
                    .addComponent(jLabel5))
                .addGap(253, 253, 253)
                .addComponent(linesetting5, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linesetting4, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addGap(278, 278, 278)
                .addComponent(linesetting13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(iconminmaxclose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(iconminmaxclose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MenuIcon.setBackground(new java.awt.Color(0, 55, 133));
        MenuIcon.setPreferredSize(new java.awt.Dimension(50, 450));

        linesetting.setBackground(new java.awt.Color(0, 55, 133));
        linesetting.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesettingLayout = new javax.swing.GroupLayout(linesetting);
        linesetting.setLayout(linesettingLayout);
        linesettingLayout.setHorizontalGroup(
            linesettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        linesettingLayout.setVerticalGroup(
            linesettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        linehidemenu.setBackground(new java.awt.Color(0, 55, 133));
        linehidemenu.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linehidemenuLayout = new javax.swing.GroupLayout(linehidemenu);
        linehidemenu.setLayout(linehidemenuLayout);
        linehidemenuLayout.setHorizontalGroup(
            linehidemenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        linehidemenuLayout.setVerticalGroup(
            linehidemenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        linesetting1.setBackground(new java.awt.Color(0, 55, 133));
        linesetting1.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesetting1Layout = new javax.swing.GroupLayout(linesetting1);
        linesetting1.setLayout(linesetting1Layout);
        linesetting1Layout.setHorizontalGroup(
            linesetting1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        linesetting1Layout.setVerticalGroup(
            linesetting1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        linesetting2.setBackground(new java.awt.Color(0, 55, 133));
        linesetting2.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesetting2Layout = new javax.swing.GroupLayout(linesetting2);
        linesetting2.setLayout(linesetting2Layout);
        linesetting2Layout.setHorizontalGroup(
            linesetting2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        linesetting2Layout.setVerticalGroup(
            linesetting2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        linesetting7.setBackground(new java.awt.Color(0, 55, 133));
        linesetting7.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                linesetting7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                linesetting7MouseExited(evt);
            }
        });
        linesetting7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon7.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon7.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.INFO);
        linesetting7.add(rSLabelIcon7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        linesetting8.setBackground(new java.awt.Color(0, 55, 133));
        linesetting8.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linesetting8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                linesetting8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                linesetting8MouseExited(evt);
            }
        });
        linesetting8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon9.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ENHANCED_ENCRYPTION);
        linesetting8.add(rSLabelIcon9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        linesetting9.setBackground(new java.awt.Color(0, 55, 133));
        linesetting9.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                linesetting9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                linesetting9MouseExited(evt);
            }
        });
        linesetting9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon10.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon10.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.VIEW_LIST);
        rSLabelIcon10.setInheritsPopupMenu(true);
        linesetting9.add(rSLabelIcon10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        linesetting10.setBackground(new java.awt.Color(0, 55, 133));
        linesetting10.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesetting10Layout = new javax.swing.GroupLayout(linesetting10);
        linesetting10.setLayout(linesetting10Layout);
        linesetting10Layout.setHorizontalGroup(
            linesetting10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        linesetting10Layout.setVerticalGroup(
            linesetting10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        linesetting11.setBackground(new java.awt.Color(0, 55, 133));
        linesetting11.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                linesetting11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                linesetting11MouseExited(evt);
            }
        });
        linesetting11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon11.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon11.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SETTINGS);
        linesetting11.add(rSLabelIcon11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        javax.swing.GroupLayout MenuIconLayout = new javax.swing.GroupLayout(MenuIcon);
        MenuIcon.setLayout(MenuIconLayout);
        MenuIconLayout.setHorizontalGroup(
            MenuIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linehidemenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        MenuIconLayout.setVerticalGroup(
            MenuIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuIconLayout.createSequentialGroup()
                .addComponent(linehidemenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(linesetting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(linesetting9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(linesetting1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(linesetting11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(linesetting10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(linesetting7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(linesetting2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(linesetting8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menu.add(MenuIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 672));

        menuhide.setBackground(new java.awt.Color(33, 150, 243));
        menuhide.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonIcon_new3.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new3.setText("Regresar");
        rSButtonIcon_new3.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rSButtonIcon_new3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REPEAT);
        rSButtonIcon_new3.setInheritsPopupMenu(true);
        rSButtonIcon_new3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new3ActionPerformed(evt);
            }
        });
        menuhide.add(rSButtonIcon_new3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 381, 210, -1));

        linesetting6.setBackground(new java.awt.Color(20, 101, 187));
        linesetting6.setPreferredSize(new java.awt.Dimension(50, 10));

        rSLabelIcon3.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PALETTE);

        rSLabelIcon4.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LOCAL_PRINTSHOP);

        rSLabelIcon5.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SCHEDULE);

        javax.swing.GroupLayout linesetting6Layout = new javax.swing.GroupLayout(linesetting6);
        linesetting6.setLayout(linesetting6Layout);
        linesetting6Layout.setHorizontalGroup(
            linesetting6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSLabelIcon5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(rSLabelIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        linesetting6Layout.setVerticalGroup(
            linesetting6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(linesetting6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuhide.add(linesetting6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 210, 52));

        linesetting12.setBackground(new java.awt.Color(0, 55, 133));
        linesetting12.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linesetting12MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Banco");

        jLabel12.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Gestiones");

        javax.swing.GroupLayout linesetting12Layout = new javax.swing.GroupLayout(linesetting12);
        linesetting12.setLayout(linesetting12Layout);
        linesetting12Layout.setHorizontalGroup(
            linesetting12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        linesetting12Layout.setVerticalGroup(
            linesetting12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(linesetting12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuhide.add(linesetting12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 70));

        rSButtonIcon_new5.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new5.setText("Buscar Cuenta B.");
        rSButtonIcon_new5.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        rSButtonIcon_new5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new5ActionPerformed(evt);
            }
        });
        menuhide.add(rSButtonIcon_new5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        rSButtonIcon_new6.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new6.setText("Deshabilitar Cuenta B");
        rSButtonIcon_new6.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new6.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE_SWEEP);
        rSButtonIcon_new6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new6ActionPerformed(evt);
            }
        });
        menuhide.add(rSButtonIcon_new6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 210, -1));

        rSButtonIcon_new7.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new7.setText("Agregar Cuenta Bancaria");
        rSButtonIcon_new7.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new7.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        rSButtonIcon_new7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new7ActionPerformed(evt);
            }
        });
        menuhide.add(rSButtonIcon_new7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, -1));

        rSButtonIcon_new9.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new9.setText("Modificar Cuenta B.");
        rSButtonIcon_new9.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.RECENT_ACTORS);
        rSButtonIcon_new9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new9ActionPerformed(evt);
            }
        });
        menuhide.add(rSButtonIcon_new9, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 190, -1, -1));

        rSButtonIcon_new10.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new10.setText("Imprimir Cuenta");
        rSButtonIcon_new10.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new10.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PRINT);
        rSButtonIcon_new10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new10ActionPerformed(evt);
            }
        });
        menuhide.add(rSButtonIcon_new10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 328, 210, -1));

        rSButtonIcon_new13.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new13.setText("Modificar Bancos");
        rSButtonIcon_new13.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new13.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REFRESH);
        rSButtonIcon_new13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new13ActionPerformed(evt);
            }
        });
        menuhide.add(rSButtonIcon_new13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 210, -1));

        rSButtonIcon_new14.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new14.setText("Agregar Banco");
        rSButtonIcon_new14.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new14.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE_OUTLINE);
        rSButtonIcon_new14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new14ActionPerformed(evt);
            }
        });
        menuhide.add(rSButtonIcon_new14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 228, 210, -1));

        menu.add(menuhide, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 210, -1));

        dashboardview.setBackground(new java.awt.Color(232, 245, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        rSPanelOpacity1.setBackground(new java.awt.Color(60, 76, 143));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("/");

        rSLabelIcon8.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon8.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Menú Principal");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("/");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Modúlo Bancos");

        rSLabelIcon17.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon17.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WIFI_TETHERING);
        rSLabelIcon17.setName(""); // NOI18N

        rSLabelIcon12.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
        rSLabelIcon12.setName(""); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Listado de Cuentas Bancarias");

        rSPanelOpacity1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(rSLabelIcon8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(rSLabelIcon17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(rSLabelIcon12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout rSPanelOpacity1Layout = new javax.swing.GroupLayout(rSPanelOpacity1);
        rSPanelOpacity1.setLayout(rSPanelOpacity1Layout);
        rSPanelOpacity1Layout.setHorizontalGroup(
            rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(rSLabelIcon8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(rSLabelIcon17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(13, 13, 13)
                .addComponent(rSLabelIcon12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(489, 489, 489))
        );
        rSPanelOpacity1Layout.setVerticalGroup(
            rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelIcon12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        JTableBancos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número de Cuenta", "Institucion Bancaria", "Tipo de Cuenta", "Tipo de Moneda", "Total en Cuenta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTableBancos.setBackgoundHead(new java.awt.Color(60, 76, 143));
        JTableBancos.setBackgoundHover(new java.awt.Color(60, 76, 143));
        JTableBancos.setColorSecondary(new java.awt.Color(255, 255, 255));
        JTableBancos.setEffectHover(true);
        JTableBancos.setHighHead(50);
        JTableBancos.setRowHeight(50);
        JTableBancos.setShowHorizontalLines(true);
        JTableBancos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableBancosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTableBancos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelOpacity1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane2)
                .addGap(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(rSPanelOpacity1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("MODÚLO BANCOS");

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE_OUTLINE);

        rSLabelHora1.setForeground(new java.awt.Color(20, 101, 187));

        jLabel15.setBackground(new java.awt.Color(102, 51, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 0, 255));
        jLabel15.setText("Usuario en sesion: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(rSLabelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(491, 491, 491)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(rSLabelIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(rSLabelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(rSLabelIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        JTextbuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        JTextbuscar.setPlaceholder("Busqueda rapida");
        JTextbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextbuscarActionPerformed(evt);
            }
        });
        JTextbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTextbuscarKeyReleased(evt);
            }
        });

        rSButtonIcon_new8.setBackground(new java.awt.Color(0, 204, 51));
        rSButtonIcon_new8.setText("Total en Bancos");
        rSButtonIcon_new8.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new8.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FUNCTIONS);
        rSButtonIcon_new8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new8ActionPerformed(evt);
            }
        });

        rSButtonIcon_new11.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new11.setText("Cuentas Habilitadas");
        rSButtonIcon_new11.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new11.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.GRID_ON);
        rSButtonIcon_new11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new11ActionPerformed(evt);
            }
        });

        rSButtonIcon_new12.setBackground(new java.awt.Color(255, 153, 51));
        rSButtonIcon_new12.setText("Cuentas Deshabilitadas");
        rSButtonIcon_new12.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.GRID_OFF);
        rSButtonIcon_new12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dashboardviewLayout = new javax.swing.GroupLayout(dashboardview);
        dashboardview.setLayout(dashboardviewLayout);
        dashboardviewLayout.setHorizontalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dashboardviewLayout.createSequentialGroup()
                        .addComponent(JTextbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(rSButtonIcon_new11, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(rSButtonIcon_new12, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(rSButtonIcon_new8, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        dashboardviewLayout.setVerticalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTextbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIcon_new11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIcon_new12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIcon_new8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, 1362, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(dashboardview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(dashboardview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(60, 60, 60))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        setSize(new java.awt.Dimension(1362, 711));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void linesetting7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting7MouseEntered
        // TODO add your handling code here:
        linesetting10.setBackground(Color.red);
    }//GEN-LAST:event_linesetting7MouseEntered

    private void linesetting7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting7MouseExited
        // TODO add your handling code here:
        linesetting10.setBackground(new Color(0,55,133));
    }//GEN-LAST:event_linesetting7MouseExited

    private void linesetting8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting8MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
    }//GEN-LAST:event_linesetting8MouseClicked

    private void linesetting8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting8MouseEntered
        // TODO add your handling code here:
        linesetting2.setBackground(Color.red);
    }//GEN-LAST:event_linesetting8MouseEntered

    private void linesetting8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting8MouseExited
        // TODO add your handling code here:
        linesetting2.setBackground(new Color(0,55,133));
    }//GEN-LAST:event_linesetting8MouseExited

    private void linesetting9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting9MouseEntered
        // TODO add your handling code here:
        linesetting.setBackground(Color.red);
    }//GEN-LAST:event_linesetting9MouseEntered

    private void linesetting9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting9MouseExited
        // TODO add your handling code here:
        linesetting.setBackground(new Color(0,55,133));

    }//GEN-LAST:event_linesetting9MouseExited

    private void linesetting11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting11MouseEntered
        // TODO add your handling code here:
        linesetting1.setBackground(Color.red);

    }//GEN-LAST:event_linesetting11MouseEntered

    private void linesetting11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting11MouseExited
        // TODO add your handling code here:
        linesetting1.setBackground(new Color(0,55,133));
    }//GEN-LAST:event_linesetting11MouseExited

    private void linesetting12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_linesetting12MouseClicked

    private void rSButtonIcon_new3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new3ActionPerformed
        // TODO add your handling code here:
        Menu menu = new Menu();
        menu.setVisible(true);
        menu.setUsuario(usuario);
        this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new3ActionPerformed

    private void rSButtonIcon_new5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new5ActionPerformed
        // TODO add your handling code here:
        BuscarCuentaB bc = new BuscarCuentaB();
        bc.setBanco(this);
        bc.setUsuario(usuario);
        bc.setVisible(true);
        

    }//GEN-LAST:event_rSButtonIcon_new5ActionPerformed

    private void rSButtonIcon_new6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new6ActionPerformed
        // TODO add your handling code here:
        
       if(codigob!=null){
          
           ConfirmacionDeshabilitarCuenta c = new ConfirmacionDeshabilitarCuenta();
           c.setBafectado(this);
           c.setTipo("DBanco");
           c.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione un registro en la tabla");
        }
       
       
    }//GEN-LAST:event_rSButtonIcon_new6ActionPerformed

    private void rSButtonIcon_new7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new7ActionPerformed
        // TODO add your handling code here:
        AgregarCuentaBancaria ac = new AgregarCuentaBancaria();
        ac.setVisible(true);
        ac.setUsuario(usuario);
        this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new7ActionPerformed

    private void rSButtonIcon_new9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new9ActionPerformed
        // TODO add your handling code here:
         if(codigob!=null){
          
           ModificarCuentaB mc = new ModificarCuentaB();
           mc.setCodigob(codigob);
           mc.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione un registro en la tabla");
        }
       
   
    }//GEN-LAST:event_rSButtonIcon_new9ActionPerformed

    private void rSButtonIcon_new10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new10ActionPerformed

    private void rSButtonIconOne6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne6ActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }//GEN-LAST:event_rSButtonIconOne6ActionPerformed

    private void rSButtonIconOne7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne7ActionPerformed
        try {
            // TODO add your handling code here:
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: de exe"+ ex.getMessage());
        }
        System.exit(0);
    }//GEN-LAST:event_rSButtonIconOne7ActionPerformed

    private void rSButtonIconOne8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne8ActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_rSButtonIconOne8ActionPerformed

    private void JTextbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextbuscarActionPerformed

    }//GEN-LAST:event_JTextbuscarActionPerformed

    private void JTextbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextbuscarKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            limpiartabla();
            buscarNombre();

        }

    }//GEN-LAST:event_JTextbuscarKeyReleased

    private void rSButtonIcon_new8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new8ActionPerformed
        // TODO add your handling code here:
        TotalBancos tb = new TotalBancos();
        tb.setVisible(true);
    }//GEN-LAST:event_rSButtonIcon_new8ActionPerformed

    private void rSButtonIcon_new11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new11ActionPerformed
        // TODO add your handling code here:
        rSButtonIcon_new6.setEnabled(true);
        limpiartabla();
        listarHabilitados();
    }//GEN-LAST:event_rSButtonIcon_new11ActionPerformed

    private void rSButtonIcon_new12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new12ActionPerformed
        // TODO add your handling code here:
        rSButtonIcon_new6.setEnabled(false);
        limpiartabla();
        listarDeshabilitados();
        
    }//GEN-LAST:event_rSButtonIcon_new12ActionPerformed

    private void JTableBancosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableBancosMouseClicked
        // TODO add your handling code here:
        int seleccion = JTableBancos.rowAtPoint(evt.getPoint());
        codigob =   String.valueOf(JTableBancos.getValueAt(seleccion, 0));
    }//GEN-LAST:event_JTableBancosMouseClicked

    private void rSButtonIcon_new13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new13ActionPerformed
        ActualizarBanco acb = new ActualizarBanco();
          acb.setVisible(true);
        acb.setUsuario(usuario);
        this.dispose();
        
    }//GEN-LAST:event_rSButtonIcon_new13ActionPerformed

    private void rSButtonIcon_new14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new14ActionPerformed
        AgregarBanco ab = new AgregarBanco();
        ab.setVisible(true);
        ab.setUsuario(usuario);
        this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new14ActionPerformed
public void Clickmenu(JPanel h1, JPanel h2, int numberbool){
        if(numberbool == 1){
            h1.setBackground(new Color(25,29,74));
            h2.setBackground(new Color(5,10,46));
        }
        else{
             h1.setBackground(new Color(5,10,46));
            h2.setBackground(new Color(25,29,74));
        }
    }
    public void changeimage(JLabel button, String resourcheimg){
        ImageIcon aimg = new ImageIcon(getClass().getResource(resourcheimg));
        button.setIcon(aimg);
        
    }
    
    public void hideshow(JPanel menushowhide, boolean dashboard , JLabel button){
        if(dashboard == true){
            menushowhide.setPreferredSize(new Dimension(50,menushowhide.getHeight()));
           // changeimage(button, "/image/menu_32px.png");
        }
        else{
             menushowhide.setPreferredSize(new Dimension(270,menushowhide.getHeight()));
              //changeimage(button, "/image/back_32px.png");
        }
    } 
       public void  changecolor(JPanel hover, Color rand){
     hover.setBackground(rand);
 }
    
  
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
            java.util.logging.Logger.getLogger(Banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Banco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new Banco().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private rojerusan.RSTableMetro1 JTableBancos;
    private RSMaterialComponent.RSTextFieldIconUno JTextbuscar;
    private javax.swing.JPanel MenuIcon;
    private javax.swing.JPanel dashboardview;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel linehidemenu;
    private javax.swing.JPanel linesetting;
    private javax.swing.JPanel linesetting1;
    private javax.swing.JPanel linesetting10;
    private javax.swing.JPanel linesetting11;
    private javax.swing.JPanel linesetting12;
    private javax.swing.JPanel linesetting13;
    private javax.swing.JPanel linesetting2;
    private javax.swing.JPanel linesetting4;
    private javax.swing.JPanel linesetting5;
    private javax.swing.JPanel linesetting6;
    private javax.swing.JPanel linesetting7;
    private javax.swing.JPanel linesetting8;
    private javax.swing.JPanel linesetting9;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel menuhide;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne6;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne7;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne8;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new10;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new11;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new12;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new13;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new14;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new3;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new6;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new7;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new8;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new9;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rojerusan.RSLabelIcon rSLabelIcon1;
    private rojerusan.RSLabelIcon rSLabelIcon10;
    private rojerusan.RSLabelIcon rSLabelIcon11;
    private rojerusan.RSLabelIcon rSLabelIcon12;
    private rojerusan.RSLabelIcon rSLabelIcon17;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private rojerusan.RSLabelIcon rSLabelIcon3;
    private rojerusan.RSLabelIcon rSLabelIcon4;
    private rojerusan.RSLabelIcon rSLabelIcon5;
    private rojerusan.RSLabelIcon rSLabelIcon7;
    private rojerusan.RSLabelIcon rSLabelIcon8;
    private rojerusan.RSLabelIcon rSLabelIcon9;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity1;
    // End of variables declaration//GEN-END:variables
}
