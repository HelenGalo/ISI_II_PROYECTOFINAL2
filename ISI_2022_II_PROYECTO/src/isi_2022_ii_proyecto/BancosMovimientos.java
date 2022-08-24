/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package isi_2022_ii_proyecto;

import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.ColorFondo;
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
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class BancosMovimientos extends javax.swing.JFrame {

    /**
     * Creates new form AgregarCliente
     */
    public void setCodigob(String codigob) {
        this.codigob = codigob;
        inicio();
    }

    String codigob;
    String tipoc;

    public void setTipoc(String tipoc) {
        this.tipoc = tipoc;
    }
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();
    
    
  public void inicio(){
        RSUtilities.setFullScreenJFrame(this);
        listarentradas();
        listaresalidas();
        totalentradas();
        totalsalidas();
        totalc();
  }
  
  public void conectar(){
        conexion.setBm(this);
        con = conexion.conexion();
    }
   public void validarconexion(){

        if(con==null){
            conectar();
            
            
        }
        
    }
    
    public BancosMovimientos() {
        RSUtilities.setFullScreenJFrame(this);
        initComponents();
        rSDateChooser2.setVisible(false);
        rSDateChooser1.setVisible(false);
        rSComboBox2.setVisible(false);
        rSComboBox1.setVisible(false);
        
      setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());

     
    
        
    }

    
    public void totalc(){
        String t="";
        String SQL = "SELECT cb.TotalenCuenta FROM CuentasBancarias cb WHERE cb.IdCuenta="+codigob;
               
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                 DecimalFormat formato = new DecimalFormat("##,###.00");
                t = String.valueOf(rs.getString("TotalenCuenta"));
            
            }
            
           if(tipoc.equals("LEMPIRAS")){
               jLabel20.setText("L."+t);
           }else{
                if(tipoc.equals("DOLARES")){
               jLabel20.setText("$."+t);
           }
           }

          
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR FELLE SO" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        con=null;
        validarconexion();
        }
    }
    
    public void listarentradas(){
        String[] registros = new String[4];
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancosEntradas.getModel();

        String SQL = "SELECT db.Descripcion,db.Valor,db.Hora,db.Fecha FROM DetallesBancarios db\n" +
                     "WHERE db.IdTipoTransaccion=1 AND db.IdCuenta="+codigob;
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                 DecimalFormat formato = new DecimalFormat("##,###.00");
                registros[0] = rs.getString("Descripcion");
                registros[1] = rs.getString("Valor");
                registros[2] = rs.getString("Hora");
                registros[3] = rs.getString("Fecha");
                modelo.addRow(registros);
            }

            JTableBancosEntradas.setModel(modelo);
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR HELLO" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        
           con=null;
        validarconexion();
        }
    }
      
    public void listaresalidas(){
        String[] registros = new String[4];
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancosSalidas.getModel();

        String SQL = "SELECT db.Descripcion,db.Valor,db.Hora,db.Fecha FROM DetallesBancarios db\n" +
                     "WHERE db.IdTipoTransaccion=2 AND db.IdCuenta="+codigob;
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                DecimalFormat formato = new DecimalFormat("##,###.00");
                registros[0] = rs.getString("Descripcion");
                registros[1] = rs.getString("Valor");
                registros[2] = rs.getString("Hora");
                registros[3] = rs.getString("Fecha");
                modelo.addRow(registros);
            }

            JTableBancosSalidas.setModel(modelo);
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR ARI " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
       
           con=null;
        validarconexion();
        
        }
    }
    
    public void totalentradas(){
        String tentradas="0.00";
        String SQL = "SELECT sum(db.Valor) FROM DetallesBancarios db\n" +
                     "WHERE db.IdTipoTransaccion=1 AND db.IdCuenta="+codigob;
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
 DecimalFormat formato = new DecimalFormat("##,###.00");
            while (rs.next()) {
                tentradas=String.valueOf(rs.getFloat("sum(db.Valor)"));
              
            }
            if(tipoc.equals("LEMPIRAS")){
               jLabel16.setText("L."+tentradas.toString());
            }else{
                if(tipoc.equals("DOLARES")){
               jLabel16.setText("$."+tentradas.toString());
            }
            }
            

            
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR NPES" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
         con=null;
        validarconexion();
        
        }
    }
    
    public void totalsalidas(){
        String tsalidas="0.00";
        String SQL = "SELECT sum(db.Valor) FROM DetallesBancarios db\n" +
                     "WHERE db.IdTipoTransaccion=2 AND db.IdCuenta="+codigob;
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
 DecimalFormat formato = new DecimalFormat("##,###.00");
            while (rs.next()) {
                tsalidas=String.valueOf(rs.getFloat("sum(db.Valor)"));
              
            }
            if(tipoc.equals("LEMPIRAS")){
               jLabel17.setText("L."+tsalidas.toString());
            }else{
                if(tipoc.equals("DOLARES")){
               jLabel17.setText("$."+tsalidas.toString());
            }
            }
            

            
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR PUEDE SER" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
         con=null;
        validarconexion();
        
        }
    }
     
     private void limpiartabla(){
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancosEntradas.getModel();
        while (modelo.getRowCount() > 0)
        {
        modelo.removeRow(0);
        }
        
        
        
       
        
     
    }
     
    private void limpiartabla2(){
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancosSalidas.getModel();
        while (modelo.getRowCount() > 0)
        {
        modelo.removeRow(0);
        }
        
        
        
       
        
     
    }
    
    public void listarentradasf(String s){
        
        String[] registros = new String[4];
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancosEntradas.getModel();

        String SQL = s;
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                DecimalFormat formato = new DecimalFormat("##,###.00");
                registros[0] = rs.getString("Descripcion");
                registros[1] = rs.getString("Valor");
                registros[2] = rs.getString("Hora");
                registros[3] = rs.getString("Fecha");
                modelo.addRow(registros);
            }

            JTableBancosEntradas.setModel(modelo);
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR AQUI" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
       
            con=null;
        validarconexion();
        }
    }
    
    public void listaresalidasf(String s){
        String[] registros = new String[4];
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancosSalidas.getModel();

        String SQL = s;
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                DecimalFormat formato = new DecimalFormat("##,###.00");
                registros[0] = rs.getString("Descripcion");
                registros[1] = rs.getString("Valor");
                registros[2] = rs.getString("Hora");
                registros[3] = rs.getString("Fecha");
                modelo.addRow(registros);
            }

            JTableBancosSalidas.setModel(modelo);
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR OTRO" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
            con=null;
        validarconexion();
        
        }
    }
     
    
    public void totalentradasf(String s){
        String tentradas="0.00";
        String SQL = s;
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
DecimalFormat formato = new DecimalFormat("##,###.00");
            while (rs.next()) {
                tentradas=String.valueOf(rs.getFloat("sum(db.Valor)"));
              
            }
            if(tipoc.equals("LEMPIRAS")){
               jLabel16.setText("L."+tentradas.toString());
            }else{
                if(tipoc.equals("DOLARES")){
               jLabel16.setText("$."+tentradas.toString());
            }
            }
            

            
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "EL QUE NO" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           con=null;
        validarconexion();
        
        }
    }
    
    public void totalsalidasf(String s){
        String tsalidas="0.00";
        String SQL = s;
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
DecimalFormat formato = new DecimalFormat("##,###.00");
            while (rs.next()) {
                tsalidas=String.valueOf(rs.getFloat("sum(db.Valor)"));
              
            }
            if(tipoc.equals("LEMPIRAS")){
               jLabel17.setText("L."+tsalidas.toString());
            }else{
                if(tipoc.equals("DOLARES")){
               jLabel17.setText("$."+tsalidas.toString());
            }
            }
            

            
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "EL QUE BUSCO" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           con=null;
        validarconexion();
        
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
        jLabel13 = new javax.swing.JLabel();
        rSLabelIcon13 = new rojerusan.RSLabelIcon();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableBancosSalidas = new rojerusan.RSTableMetro1();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTableBancosEntradas = new rojerusan.RSTableMetro1();
        rSButtonIcon_new11 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new14 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new15 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new16 = new newscomponents.RSButtonIcon_new();
        rSPanelOpacity2 = new rojerusan.RSPanelOpacity();
        jLabel17 = new javax.swing.JLabel();
        rSPanelOpacity3 = new rojerusan.RSPanelOpacity();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        jLabel6 = new javax.swing.JLabel();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        rSButtonIcon_new12 = new newscomponents.RSButtonIcon_new();
        rSPanelBorder1 = new RSMaterialComponent.RSPanelBorder();
        jLabel20 = new javax.swing.JLabel();
        rSDateChooser1 = new rojeru_san.componentes.RSDateChooser();
        rSDateChooser2 = new rojeru_san.componentes.RSDateChooser();
        rSCheckBox1 = new rojerusan.RSCheckBox();
        rSCheckBox2 = new rojerusan.RSCheckBox();
        jLabel19 = new javax.swing.JLabel();
        rSComboBox1 = new RSMaterialComponent.RSComboBox();
        rSComboBox2 = new RSMaterialComponent.RSComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
            .addGap(0, 602, Short.MAX_VALUE)
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
                .addComponent(linesetting5, javax.swing.GroupLayout.DEFAULT_SIZE, 3, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linesetting4, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
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
            .addGroup(HeaderLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconminmaxclose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        jPanel1.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1369, -1));

        MenuIcon.setBackground(new java.awt.Color(0, 55, 133));
        MenuIcon.setPreferredSize(new java.awt.Dimension(50, 450));

        linesetting.setBackground(new java.awt.Color(5, 10, 46));
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

        linesetting1.setBackground(new java.awt.Color(5, 10, 46));
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

        linesetting2.setBackground(new java.awt.Color(5, 10, 46));
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

        linesetting10.setBackground(new java.awt.Color(5, 10, 46));
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

        menuhide.setBackground(new java.awt.Color(33, 150, 243));

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
                .addComponent(rSLabelIcon5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
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

        rSButtonIcon_new5.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new5.setText("Buscar Cuenta B.");
        rSButtonIcon_new5.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        rSButtonIcon_new5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new5ActionPerformed(evt);
            }
        });

        rSButtonIcon_new6.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new6.setText("Deshabilitar Cuenta B");
        rSButtonIcon_new6.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new6.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE_SWEEP);
        rSButtonIcon_new6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new6ActionPerformed(evt);
            }
        });

        rSButtonIcon_new7.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new7.setText("Agregar Cuenta Bancaria");
        rSButtonIcon_new7.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new7.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        rSButtonIcon_new7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new7ActionPerformed(evt);
            }
        });

        rSButtonIcon_new9.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new9.setText("Modificar Cuenta B.");
        rSButtonIcon_new9.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.RECENT_ACTORS);
        rSButtonIcon_new9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new9ActionPerformed(evt);
            }
        });

        rSButtonIcon_new10.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new10.setText("Imprimir Cuenta");
        rSButtonIcon_new10.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new10.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PRINT);
        rSButtonIcon_new10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuhideLayout = new javax.swing.GroupLayout(menuhide);
        menuhide.setLayout(menuhideLayout);
        menuhideLayout.setHorizontalGroup(
            menuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linesetting12, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSButtonIcon_new5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSButtonIcon_new6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSButtonIcon_new7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSButtonIcon_new9, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSButtonIcon_new10, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSButtonIcon_new3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting6, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        menuhideLayout.setVerticalGroup(
            menuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuhideLayout.createSequentialGroup()
                .addComponent(linesetting12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(310, 310, 310)
                .addComponent(linesetting6, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addComponent(MenuIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuhide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
            .addComponent(menuhide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 46, 260, 690));

        dashboardview.setBackground(new java.awt.Color(232, 245, 255));
        dashboardview.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        rSLabelIcon12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ANNOUNCEMENT);
        rSLabelIcon12.setName(""); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Detalles Bancarios");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("/");

        rSLabelIcon13.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon13.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
        rSLabelIcon13.setName(""); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Listado de Cuentas Bancarias");

        rSPanelOpacity1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(rSLabelIcon8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(rSLabelIcon17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(rSLabelIcon12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(rSLabelIcon13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout rSPanelOpacity1Layout = new javax.swing.GroupLayout(rSPanelOpacity1);
        rSPanelOpacity1.setLayout(rSPanelOpacity1Layout);
        rSPanelOpacity1Layout.setHorizontalGroup(
            rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(rSLabelIcon8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(rSLabelIcon17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(rSLabelIcon12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(151, 151, 151))
        );
        rSPanelOpacity1Layout.setVerticalGroup(
            rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSLabelIcon17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel3.add(rSPanelOpacity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 50));

        JTableBancosSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCION", "VALOR", "HORA", "FECHA"
            }
        ));
        JTableBancosSalidas.setBackgoundHead(new java.awt.Color(60, 76, 143));
        JTableBancosSalidas.setBackgoundHover(new java.awt.Color(60, 76, 143));
        JTableBancosSalidas.setColorSecondary(new java.awt.Color(255, 255, 255));
        JTableBancosSalidas.setEffectHover(true);
        JTableBancosSalidas.setHighHead(50);
        JTableBancosSalidas.setRowHeight(50);
        JTableBancosSalidas.setShowHorizontalLines(true);
        JTableBancosSalidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableBancosSalidasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTableBancosSalidas);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 430, 340));

        JTableBancosEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCION", "VALOR", "HORA", "FECHA"
            }
        ));
        JTableBancosEntradas.setBackgoundHead(new java.awt.Color(60, 76, 143));
        JTableBancosEntradas.setBackgoundHover(new java.awt.Color(60, 76, 143));
        JTableBancosEntradas.setColorSecondary(new java.awt.Color(255, 255, 255));
        JTableBancosEntradas.setEffectHover(true);
        JTableBancosEntradas.setHighHead(50);
        JTableBancosEntradas.setRowHeight(50);
        JTableBancosEntradas.setShowHorizontalLines(true);
        JTableBancosEntradas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableBancosEntradasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(JTableBancosEntradas);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 430, 340));

        rSButtonIcon_new11.setBackground(new java.awt.Color(255, 51, 102));
        rSButtonIcon_new11.setText("TOTAL SALIDAS:");
        rSButtonIcon_new11.setBackgroundHover(new java.awt.Color(255, 51, 102));
        rSButtonIcon_new11.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONEY_OFF);
        rSButtonIcon_new11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new11ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonIcon_new11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 440, 170, -1));

        rSButtonIcon_new14.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new14.setText("TOTAL ENTRADAS:");
        rSButtonIcon_new14.setBackgroundHover(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new14.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        rSButtonIcon_new14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new14ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonIcon_new14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 180, -1));

        rSButtonIcon_new15.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new15.setText("ENTRADAS DE EFECTIVO");
        rSButtonIcon_new15.setBackgroundHover(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new15.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        rSButtonIcon_new15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new15ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonIcon_new15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 430, -1));

        rSButtonIcon_new16.setBackground(new java.awt.Color(255, 51, 102));
        rSButtonIcon_new16.setText("SALIDAS DE EFECTIVO");
        rSButtonIcon_new16.setBackgroundHover(new java.awt.Color(255, 51, 102));
        rSButtonIcon_new16.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONEY_OFF);
        rSButtonIcon_new16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new16ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonIcon_new16, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 430, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Menu Principal");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });

        javax.swing.GroupLayout rSPanelOpacity2Layout = new javax.swing.GroupLayout(rSPanelOpacity2);
        rSPanelOpacity2.setLayout(rSPanelOpacity2Layout);
        rSPanelOpacity2Layout.setHorizontalGroup(
            rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity2Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 80, Short.MAX_VALUE))
        );
        rSPanelOpacity2Layout.setVerticalGroup(
            rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel3.add(rSPanelOpacity2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 440, 260, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Menu Principal");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });

        javax.swing.GroupLayout rSPanelOpacity3Layout = new javax.swing.GroupLayout(rSPanelOpacity3);
        rSPanelOpacity3.setLayout(rSPanelOpacity3Layout);
        rSPanelOpacity3Layout.setHorizontalGroup(
            rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity3Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 44, Short.MAX_VALUE))
        );
        rSPanelOpacity3Layout.setVerticalGroup(
            rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel3.add(rSPanelOpacity3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 250, 40));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Modulo Bancos");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        dashboardview.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 165, 1033, 490));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("MODÚLO BANCOS");

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE_OUTLINE);

        rSLabelHora1.setForeground(new java.awt.Color(20, 101, 187));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(rSLabelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(rSLabelIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        );

        dashboardview.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1112, -1));

        rSButtonIcon_new12.setBackground(new java.awt.Color(255, 153, 51));
        rSButtonIcon_new12.setText("Filtrar Movimientos ");
        rSButtonIcon_new12.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FILTER_2);
        rSButtonIcon_new12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new12ActionPerformed(evt);
            }
        });
        dashboardview.add(rSButtonIcon_new12, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 270, 41));

        rSPanelBorder1.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelBorder1.setBgBorder(new java.awt.Color(102, 102, 255));
        rSPanelBorder1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 0, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("L.0.00");

        javax.swing.GroupLayout rSPanelBorder1Layout = new javax.swing.GroupLayout(rSPanelBorder1);
        rSPanelBorder1.setLayout(rSPanelBorder1Layout);
        rSPanelBorder1Layout.setHorizontalGroup(
            rSPanelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelBorder1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        rSPanelBorder1Layout.setVerticalGroup(
            rSPanelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        dashboardview.add(rSPanelBorder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 140, 40));

        rSDateChooser1.setPlaceholder("Fecha Inicial");
        dashboardview.add(rSDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 257, 40));

        rSDateChooser2.setColorBackground(new java.awt.Color(102, 102, 255));
        rSDateChooser2.setPlaceholder("Fecha Final");
        dashboardview.add(rSDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 257, 40));

        rSCheckBox1.setText("Por Hora");
        rSCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSCheckBox1MouseClicked(evt);
            }
        });
        dashboardview.add(rSCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 130, -1));

        rSCheckBox2.setText("Por Fecha");
        rSCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSCheckBox2MouseClicked(evt);
            }
        });
        dashboardview.add(rSCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 140, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 0, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("TOTAL CUENTA:");
        dashboardview.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 140, 40));

        rSComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Hora Final", "00:00:00", "01:00:00", "02:00:00", "03:00:00", "04:00:00", "05:00:00", "06:00:00", "07:00:00", "08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00", "24:00:00" }));
        dashboardview.add(rSComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 120, 290, -1));

        rSComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Hora Inicial", "00:00:00", "01:00:00", "02:00:00", "03:00:00", "04:00:00", "05:00:00", "06:00:00", "07:00:00", "08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00", "24:00:00" }));
        rSComboBox2.setColorArrow(new java.awt.Color(102, 102, 255));
        rSComboBox2.setColorFondo(new java.awt.Color(102, 102, 255));
        dashboardview.add(rSComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, 290, -1));

        jPanel1.add(dashboardview, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 1109, 690));

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
                .addGap(2147322392, 2147322392, 2147322392))
        );

        setSize(new java.awt.Dimension(1369, 737));
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
        Banco banco = new Banco();
        banco.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new3ActionPerformed

    private void rSButtonIcon_new5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new5ActionPerformed
        // TODO add your handling code here:
        BuscarCuentaB bc = new BuscarCuentaB();
        bc.setVisible(true);

    }//GEN-LAST:event_rSButtonIcon_new5ActionPerformed

    private void rSButtonIcon_new6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new6ActionPerformed
        // TODO add your handling code here:
        Proveedores proveedores = new Proveedores();
        proveedores.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new6ActionPerformed

    private void rSButtonIcon_new7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new7ActionPerformed
        // TODO add your handling code here:
        AgregarClientes ag = new AgregarClientes();
        ag.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new7ActionPerformed

    private void rSButtonIcon_new9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new9ActionPerformed
        // TODO add your handling code here:
   
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
            System.out.println("Error: "+ ex.getMessage());
        }
        System.exit(0);
    }//GEN-LAST:event_rSButtonIconOne7ActionPerformed

    private void rSButtonIconOne8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne8ActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_rSButtonIconOne8ActionPerformed

    private void rSButtonIcon_new11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new11ActionPerformed

    private void rSButtonIcon_new12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new12ActionPerformed
        // TODO add your handling code here:
        limpiartabla();
        limpiartabla2();
        String formato="yyyy/MM/dd";
        String Fi="";
        String Fn="";
        String hi="";
        String hf="";
        Date FechaI;
        Date FechaF;
        
        
        if(rSCheckBox2.isSelected()==true && rSCheckBox1.isSelected()==true){
            FechaI=rSDateChooser1.getDatoFecha();
            FechaF=rSDateChooser2.getDatoFecha();
            SimpleDateFormat formateador = new SimpleDateFormat(formato);
            Fi = formateador.format(FechaI);
            Fn =formateador.format(FechaF);
            hi = rSComboBox2.getSelectedItem().toString();
            hf = rSComboBox1.getSelectedItem().toString();
        }
        
        
        if(rSCheckBox2.isSelected()==false && rSCheckBox1.isSelected()==true){
             hi = rSComboBox2.getSelectedItem().toString();
             hf = rSComboBox1.getSelectedItem().toString();
        }
        
         if(rSCheckBox2.isSelected()==true && rSCheckBox1.isSelected()==false){
            FechaI=rSDateChooser1.getDatoFecha();
            FechaF=rSDateChooser2.getDatoFecha();
            SimpleDateFormat formateador = new SimpleDateFormat(formato);
            Fi = formateador.format(FechaI);
            Fn =formateador.format(FechaF);
        }
        
        
        
        
        String sql1="SELECT db.Descripcion,db.Valor,db.Hora,db.Fecha FROM DetallesBancarios db \n" +
                "WHERE db.IdTipoTransaccion=1 AND db.IdCuenta="+codigob+" AND db.Hora between '"+hi+"' and '"+hf+"'"+" AND db.Fecha between '"+Fi+"' and '"+Fn+"';";
        
        String sql2="SELECT db.Descripcion,db.Valor,db.Hora,db.Fecha FROM DetallesBancarios db \n" +
                "WHERE db.IdTipoTransaccion=1 AND db.IdCuenta="+codigob+" AND db.Hora between '"+hi+"' and '"+hf+"';";
        
        String sql3="SELECT db.Descripcion,db.Valor,db.Hora,db.Fecha FROM DetallesBancarios db \n" +
                "WHERE db.IdTipoTransaccion=1 AND db.IdCuenta="+codigob+" AND db.Fecha between '"+Fi+"' and '"+Fn+"';";
        
        String sqlvs1="SELECT db.Descripcion,db.Valor,db.Hora,db.Fecha FROM DetallesBancarios db \n" +
                "WHERE db.IdTipoTransaccion=2 AND db.IdCuenta="+codigob+" AND db.Hora between '"+hi+"' and '"+hf+"'"+" AND db.Fecha between '"+Fi+"' and '"+Fn+"';";
        
        String sqlvs2="SELECT db.Descripcion,db.Valor,db.Hora,db.Fecha FROM DetallesBancarios db \n" +
                "WHERE db.IdTipoTransaccion=2 AND db.IdCuenta="+codigob+" AND db.Hora between '"+hi+"' and '"+hf+"';";
        
        String sqlvs3="SELECT db.Descripcion,db.Valor,db.Hora,db.Fecha FROM DetallesBancarios db \n" +
                "WHERE db.IdTipoTransaccion=2 AND db.IdCuenta="+codigob+" AND db.Fecha between '"+Fi+"' and '"+Fn+"';";
        
        String sqlt1="SELECT sum(db.Valor) FROM DetallesBancarios db\n" +
                "WHERE db.IdTipoTransaccion=1 AND db.IdCuenta="+codigob+" AND db.Hora between '"+hi+"' and '"+hf+"'"+" AND db.Fecha between '"+Fi+"' and '"+Fn+"';";
        
        String sqlt2="SELECT sum(db.Valor) FROM DetallesBancarios db\n" +
                "WHERE db.IdTipoTransaccion=1 AND db.IdCuenta="+codigob+" AND db.Hora between '"+hi+"' and '"+hf+"';";
        
        String sqlt3="SELECT sum(db.Valor) FROM DetallesBancarios db \n" +
                "WHERE db.IdTipoTransaccion=1 AND db.IdCuenta="+codigob+" AND db.Fecha between '"+Fi+"' and '"+Fn+"';";
        
        String sqlts1="SELECT sum(db.Valor) FROM DetallesBancarios db\n" +
                "WHERE db.IdTipoTransaccion=2 AND db.IdCuenta="+codigob+" AND db.Hora between '"+hi+"' and '"+hf+"'"+" AND db.Fecha between '"+Fi+"' and '"+Fn+"';";
        
        String sqlts2="SELECT sum(db.Valor) FROM DetallesBancarios db\n" +
                "WHERE db.IdTipoTransaccion=2 AND db.IdCuenta="+codigob+" AND db.Hora between '"+hi+"' and '"+hf+"';";
        
        String sqlts3="SELECT sum(db.Valor) FROM DetallesBancarios db\n" +
                "WHERE db.IdTipoTransaccion=2 AND db.IdCuenta="+codigob+" AND db.Fecha between '"+Fi+"' and '"+Fn+"';";
        
        if(rSCheckBox2.isSelected()==true && rSCheckBox1.isSelected()==true){
            listarentradasf(sql1);
            listaresalidasf(sqlvs1);
            totalentradasf(sqlt1);
            totalsalidasf(sqlts1);
        }else{
            if(rSCheckBox2.isSelected()==false && rSCheckBox1.isSelected()==true){
            listarentradasf(sql2);
            listaresalidasf(sqlvs2);
            totalentradasf(sqlt2);
            totalsalidasf(sqlts2);
        }else{
            if(rSCheckBox2.isSelected()==true && rSCheckBox1.isSelected()==false){
            listarentradasf(sql3);
            listaresalidasf(sqlvs3);
            totalentradasf(sqlt3);
            totalsalidasf(sqlts3);
        }
            }
            
        }
       
       
       
        
        
    }//GEN-LAST:event_rSButtonIcon_new12ActionPerformed

    private void JTableBancosSalidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableBancosSalidasMouseClicked
        // TODO add your handling code here:
        int seleccion = JTableBancosSalidas.rowAtPoint(evt.getPoint());
        codigob =   String.valueOf(JTableBancosSalidas.getValueAt(seleccion, 0));
    }//GEN-LAST:event_JTableBancosSalidasMouseClicked

    private void JTableBancosEntradasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableBancosEntradasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JTableBancosEntradasMouseClicked

    private void rSButtonIcon_new14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new14ActionPerformed

    private void rSButtonIcon_new15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new15ActionPerformed

    private void rSButtonIcon_new16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new16ActionPerformed

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        // TODO add your handling code here:
        rSPanelOpacity3.setBackground(new Color(0,55,133));
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        // TODO add your handling code here:
         rSPanelOpacity2.setBackground(new Color(0,55,133));
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        // TODO add your handling code here:
         rSPanelOpacity3.setBackground(new Color(0,112,192));
    }//GEN-LAST:event_jLabel16MouseExited

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        // TODO add your handling code here:
        rSPanelOpacity2.setBackground(new Color(0,112,192));
    }//GEN-LAST:event_jLabel17MouseExited

    private void rSCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSCheckBox1MouseClicked
        // TODO add your handling code here:
        if(rSCheckBox1.isSelected()){
            rSComboBox2.setVisible(true);
            rSComboBox1.setVisible(true);
        }else{
            rSComboBox2.setVisible(false);
            rSComboBox1.setVisible(false);
        }
    }//GEN-LAST:event_rSCheckBox1MouseClicked

    private void rSCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSCheckBox2MouseClicked
        // TODO add your handling code here:
         if(rSCheckBox2.isSelected()){
            rSDateChooser2.setVisible(true);
            rSDateChooser1.setVisible(true);
        }else{
            rSDateChooser2.setVisible(false);
            rSDateChooser1.setVisible(false);
         }
    }//GEN-LAST:event_rSCheckBox2MouseClicked
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
            java.util.logging.Logger.getLogger(BancosMovimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BancosMovimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BancosMovimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BancosMovimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new BancosMovimientos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private rojerusan.RSTableMetro1 JTableBancosEntradas;
    private rojerusan.RSTableMetro1 JTableBancosSalidas;
    private javax.swing.JPanel MenuIcon;
    private javax.swing.JPanel dashboardview;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JScrollPane jScrollPane3;
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
    private newscomponents.RSButtonIcon_new rSButtonIcon_new14;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new15;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new16;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new3;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new6;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new7;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new9;
    private rojerusan.RSCheckBox rSCheckBox1;
    private rojerusan.RSCheckBox rSCheckBox2;
    private RSMaterialComponent.RSComboBox rSComboBox1;
    private RSMaterialComponent.RSComboBox rSComboBox2;
    private rojeru_san.componentes.RSDateChooser rSDateChooser1;
    private rojeru_san.componentes.RSDateChooser rSDateChooser2;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rojerusan.RSLabelIcon rSLabelIcon1;
    private rojerusan.RSLabelIcon rSLabelIcon10;
    private rojerusan.RSLabelIcon rSLabelIcon11;
    private rojerusan.RSLabelIcon rSLabelIcon12;
    private rojerusan.RSLabelIcon rSLabelIcon13;
    private rojerusan.RSLabelIcon rSLabelIcon17;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private rojerusan.RSLabelIcon rSLabelIcon3;
    private rojerusan.RSLabelIcon rSLabelIcon4;
    private rojerusan.RSLabelIcon rSLabelIcon5;
    private rojerusan.RSLabelIcon rSLabelIcon7;
    private rojerusan.RSLabelIcon rSLabelIcon8;
    private rojerusan.RSLabelIcon rSLabelIcon9;
    private RSMaterialComponent.RSPanelBorder rSPanelBorder1;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity1;
    private rojerusan.RSPanelOpacity rSPanelOpacity2;
    private rojerusan.RSPanelOpacity rSPanelOpacity3;
    // End of variables declaration//GEN-END:variables
}
