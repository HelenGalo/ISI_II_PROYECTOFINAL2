/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto;

import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.ConfirmacionGuardar;
import isi_2022_ii_proyecto.Recursos.ConfirmacionModificar;
import isi_2022_ii_proyecto.Recursos.VentanaEmergente1;
import isi_2022_ii_proyecto.Recursos.VentanaFondosIns;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rojeru_san.complementos.RSUtilities;
import rojerusan.RSEffectFade;

/**
 *
 * @author Edwin Rafael
 */
public class CerraCaja extends javax.swing.JFrame {
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();
    String codigocaja="";

    public void setCodigocaja(String codigocaja) {
        this.codigocaja = codigocaja;
    }
    boolean estadopostaperturar=false;
    boolean estadopostaperturarbanco=false;
             

   
    boolean estadosModificar=false;

    public void setEstadosModificar(boolean estadosModificar) {
        this.estadosModificar = estadosModificar;
    }
    public void conectar(){
       /* conexion.setApertcajas(this);
        con = conexion.conexion();*/
    }
    
    
    
    public void validarConfirmacion(){
        if(estadosModificar=true){
           
            if(estadopostaperturar==true){
                /*insertarHistoriaCaja();
                actualizarCuentaBancaria(Float.valueOf(JTextbuscar.getText()));*/
            }
            
        }
    }
      public void validarconexion(){

        if(con==null){
            conectar();
            
            
        }
        
    }
    
   
    
     
    
     
     
    
    
    
    
    
   
    

    /**
     * Creates new form CalendarForm
     */
    public CerraCaja() {
        RSUtilities.setFullScreenJFrame(this);
        initComponents();
       
        RSUtilities.setOpaqueWindow(this, false);
        listarventasefectivo();
        listarventastarjeta();
        listartotalventasefectivo();
        listartotalventastarjeta();
        listartotalencaja();
       
        setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
    }
    
    
    public void listartotalencaja(){
        String valor="0.00";
        DecimalFormat formato = new DecimalFormat("##,###.00");
        String SQL = "	Select sum(ec.Total) from Ventas v\n" +
                "INNER JOIN EntradasCaja ec on ec.IdOrden = v.IdOrden\n" +
                "WHERE v.IdCaja=2 AND v.FechaVenta=\"2022/08/22\" AND v.IdTipoVenta=1 OR v.IdTipoVenta=2\n" +
                ";";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                valor = formato.format(rs.getDouble("sum(ec.Total)"));
              
    
    
                
            }
          if(valor.equals(".00")){
               jLabel35.setText("0.00");
           }else{
              jLabel35.setText(valor); 
           }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
       
                 con=null;
                validarconexion();
        }
    }
    
    
    public void listartotalventasefectivo(){
        String valor="0.00";
        DecimalFormat formato = new DecimalFormat("##,###.00");
        String SQL = "Select sum(T1.Subtotal) from(\n" +
                    "Select v.idOrden, sum(dv.Subtotal) as 'Subtotal'from Ventas v\n" +
                    "INNER JOIN DetalledeVenta dv on dv.idOrden = v.IdOrden\n" +
                    "Where v.IdTipoVenta=1 AND v.IdCaja=2 AND v.FechaVenta=\"2022/08/24\"\n" +
                    "Group by 1) as T1;";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                valor = formato.format(rs.getDouble("sum(T1.Subtotal)"));
              
    
    
                
            }
          if(valor.equals(".00")){
               jLabel26.setText("0.00");
           }else{
              jLabel26.setText(valor); 
           }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
       
                 con=null;
                validarconexion();
        }
    }
   
    public void listartotalventastarjeta(){
        String valor="0.00";
        DecimalFormat formato = new DecimalFormat("##,###.00");
        String SQL = "Select sum(T1.Subtotal) from(\n" +
                    "Select v.idOrden, sum(dv.Subtotal) as 'Subtotal'from Ventas v\n" +
                    "INNER JOIN DetalledeVenta dv on dv.idOrden = v.IdOrden\n" +
                    "Where v.IdTipoVenta=2 AND v.IdCaja=2 AND v.FechaVenta=\"2022/08/22\"\n" +
                    "Group by 1) as T1;";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                valor = formato.format(rs.getDouble("sum(T1.Subtotal)"));
              
    
    
                
            }
           if(valor.equals(".00")){
               jLabel27.setText("0.00");
           }else{
              jLabel27.setText(valor); 
           }
           
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
       
                 con=null;
                validarconexion();
        }
    } 
    
    public void listarventasefectivo(){
        DecimalFormat formato = new DecimalFormat("##,###.00");
        String idorden="";
        String subtotal="";
        String Fecha="";
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancosSalidas.getModel();
        String[] registros = new String[2];
        String SQL = "Select v.idOrden, sum(dv.Subtotal) from Ventas v\n" +
                    "INNER JOIN DetalledeVenta dv on dv.idOrden = v.IdOrden\n" +
                    "Where v.IdTipoVenta=1 AND v.IdCaja=2 AND v.FechaVenta=\"2022/08/24\"\n" +
                    "Group by 1;";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                registros[0] = rs.getString("v.idOrden");
                registros[1] = formato.format(rs.getDouble("sum(dv.Subtotal)"));
                modelo.addRow(registros);
    
    
                
            }
            JTableBancosSalidas.setModel(modelo);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
       
                 con=null;
                validarconexion();
        }
    }
    
    public void listarventastarjeta(){
        DecimalFormat formato = new DecimalFormat("##,###.00");
        String idorden="";
        String subtotal="";
        String Fecha="";
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancosSalidas1.getModel();
        String[] registros = new String[2];
        String SQL = "Select v.idOrden, sum(dv.Subtotal) from Ventas v\n" +
                    "INNER JOIN DetalledeVenta dv on dv.idOrden = v.IdOrden\n" +
                    "Where v.IdTipoVenta=2 AND v.IdCaja=2 AND v.FechaVenta=\"2022/08/22\"\n" +
                    "Group by 1;";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                registros[0] = rs.getString("v.idOrden");
                registros[1] = formato.format(rs.getDouble("sum(dv.Subtotal)"));
                modelo.addRow(registros);
    
    
                
            }
            JTableBancosSalidas1.setModel(modelo);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
       
                 con=null;
                validarconexion();
        }
    }
    

    
    public void cerrarcaja(){
       /* float monto = 0.00f;
        monto =Float.valueOf(JTextbuscar.getText());
 
       String SQL = "UPDATE Caja SET IdEstadoCaja=?, TotalCaja=? WHERE IdCaja="+JCodigoDisponible.getText();
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setInt(1, 2);   
            preparedStmt.setFloat(2, monto);         
            preparedStmt.execute();
            estadopostaperturar=true;
            
            
           

        } catch (Exception e) {
          con=null;
validarconexion();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        */
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
        rSPanelOpacity3 = new RSMaterialComponent.RSPanelOpacity();
        jPanel5 = new javax.swing.JPanel();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        jLabel15 = new javax.swing.JLabel();
        rSLabelHora2 = new rojeru_san.RSLabelHora();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        linesetting6 = new javax.swing.JPanel();
        linesetting7 = new javax.swing.JPanel();
        rSButtonIconOne5 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne3 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne4 = new RSMaterialComponent.RSButtonIconOne();
        jPanel7 = new javax.swing.JPanel();
        rSPanelOpacity4 = new RSMaterialComponent.RSPanelOpacity();
        rSLabelIcon7 = new rojerusan.RSLabelIcon();
        jLabel18 = new javax.swing.JLabel();
        rSLabelIcon9 = new rojerusan.RSLabelIcon();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        rSLabelIcon18 = new rojerusan.RSLabelIcon();
        jLabel23 = new javax.swing.JLabel();
        rSLabelIcon13 = new rojerusan.RSLabelIcon();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        rSButtonIcon_new15 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new16 = new newscomponents.RSButtonIcon_new();
        rSPanelCircle2 = new rojeru_san.rspanel.RSPanelCircle();
        JCodigoDisponible1 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        rSButtonIcon_new17 = new newscomponents.RSButtonIcon_new();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableBancosSalidas = new rojerusan.RSTableMetro1();
        rSButtonIcon_new11 = new newscomponents.RSButtonIcon_new();
        rSPanelOpacity2 = new rojerusan.RSPanelOpacity();
        jLabel26 = new javax.swing.JLabel();
        rSButtonIcon_new18 = new newscomponents.RSButtonIcon_new();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTableBancosSalidas1 = new rojerusan.RSTableMetro1();
        rSButtonIcon_new12 = new newscomponents.RSButtonIcon_new();
        rSPanelOpacity5 = new rojerusan.RSPanelOpacity();
        jLabel27 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        JTextbuscar = new RSMaterialComponent.RSTextFieldIconUno();
        jLabel34 = new javax.swing.JLabel();
        JTextbuscar1 = new RSMaterialComponent.RSTextFieldIconUno();
        jLabel35 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        rSPanelOpacity3.setBackground(new java.awt.Color(232, 245, 255));
        rSPanelOpacity3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);
        jPanel5.add(rSLabelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 0, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("MODÚLO CAJA");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 490, 60));

        rSLabelHora2.setForeground(new java.awt.Color(20, 101, 187));
        jPanel5.add(rSLabelHora2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 108, -1));

        rSPanelOpacity3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 60));

        jPanel6.setBackground(new java.awt.Color(20, 101, 187));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("SYSTEM");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("GEVEC");

        linesetting6.setBackground(new java.awt.Color(20, 101, 187));
        linesetting6.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesetting6Layout = new javax.swing.GroupLayout(linesetting6);
        linesetting6.setLayout(linesetting6Layout);
        linesetting6Layout.setHorizontalGroup(
            linesetting6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 93, Short.MAX_VALUE)
        );
        linesetting6Layout.setVerticalGroup(
            linesetting6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        linesetting7.setBackground(new java.awt.Color(20, 101, 187));
        linesetting7.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonIconOne5.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIconOne5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REMOVE);
        rSButtonIconOne5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne5ActionPerformed(evt);
            }
        });

        rSButtonIconOne3.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIconOne3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FULLSCREEN);
        rSButtonIconOne3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne3ActionPerformed(evt);
            }
        });

        rSButtonIconOne4.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIconOne4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        rSButtonIconOne4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel16))
                    .addComponent(jLabel17))
                .addGap(253, 253, 253)
                .addComponent(linesetting7, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linesetting6, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addGap(162, 162, 162)
                .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonIconOne3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        rSPanelOpacity3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelOpacity4.setBackground(new java.awt.Color(60, 76, 143));

        rSLabelIcon7.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon7.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        rSLabelIcon7.setName(""); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("/");

        rSLabelIcon9.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Aperturar Caja");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Menú Principal");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("/");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Modúlo Bancos");

        rSLabelIcon18.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon18.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WIFI_TETHERING);
        rSLabelIcon18.setName(""); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("/");

        rSLabelIcon13.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon13.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
        rSLabelIcon13.setName(""); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Listado de Cuentas");

        rSPanelOpacity4.setLayer(rSLabelIcon7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity4.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity4.setLayer(rSLabelIcon9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity4.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity4.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity4.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity4.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity4.setLayer(rSLabelIcon18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity4.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity4.setLayer(rSLabelIcon13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity4.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout rSPanelOpacity4Layout = new javax.swing.GroupLayout(rSPanelOpacity4);
        rSPanelOpacity4.setLayout(rSPanelOpacity4Layout);
        rSPanelOpacity4Layout.setHorizontalGroup(
            rSPanelOpacity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(rSLabelIcon9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel20)
                .addGap(11, 11, 11)
                .addComponent(jLabel18)
                .addGap(12, 12, 12)
                .addComponent(rSLabelIcon18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addGap(13, 13, 13)
                .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSLabelIcon7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rSPanelOpacity4Layout.setVerticalGroup(
            rSPanelOpacity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(rSPanelOpacity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSLabelIcon18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(rSPanelOpacity4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(153, 0, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("L.");
        jPanel7.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 40, 30));

        rSButtonIcon_new15.setBackground(new java.awt.Color(102, 51, 255));
        rSButtonIcon_new15.setText("Realizar Corte");
        rSButtonIcon_new15.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new15.setFocusable(false);
        rSButtonIcon_new15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new15.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.UPDATE);
        rSButtonIcon_new15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new15ActionPerformed(evt);
            }
        });
        jPanel7.add(rSButtonIcon_new15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 230, 50));

        rSButtonIcon_new16.setBackground(new java.awt.Color(255, 102, 102));
        rSButtonIcon_new16.setText("Cancelar");
        rSButtonIcon_new16.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new16.setFocusable(false);
        rSButtonIcon_new16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new16.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        rSButtonIcon_new16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new16ActionPerformed(evt);
            }
        });
        jPanel7.add(rSButtonIcon_new16, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 230, 50));

        rSPanelCircle2.setBackground(new java.awt.Color(60, 76, 143));

        JCodigoDisponible1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JCodigoDisponible1.setForeground(new java.awt.Color(255, 255, 255));
        JCodigoDisponible1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JCodigoDisponible1.setText("1");

        javax.swing.GroupLayout rSPanelCircle2Layout = new javax.swing.GroupLayout(rSPanelCircle2);
        rSPanelCircle2.setLayout(rSPanelCircle2Layout);
        rSPanelCircle2Layout.setHorizontalGroup(
            rSPanelCircle2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelCircle2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JCodigoDisponible1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        rSPanelCircle2Layout.setVerticalGroup(
            rSPanelCircle2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelCircle2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JCodigoDisponible1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.add(rSPanelCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 70, 70));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 0, 153));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("CERRAR CAJA");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 150, 50));

        rSButtonIcon_new17.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new17.setText("VENTAS EN EFECTIVO");
        rSButtonIcon_new17.setBackgroundHover(new java.awt.Color(255, 51, 102));
        rSButtonIcon_new17.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONEY_OFF);
        rSButtonIcon_new17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new17ActionPerformed(evt);
            }
        });
        jPanel7.add(rSButtonIcon_new17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 430, -1));

        JTableBancosSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ORDEN N.", "TOTAL", "FECHA"
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

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 430, 210));

        rSButtonIcon_new11.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new11.setText("TOTAL L.");
        rSButtonIcon_new11.setBackgroundHover(new java.awt.Color(255, 51, 102));
        rSButtonIcon_new11.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        rSButtonIcon_new11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new11ActionPerformed(evt);
            }
        });
        jPanel7.add(rSButtonIcon_new11, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 170, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Menu Principal");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel26MouseExited(evt);
            }
        });

        javax.swing.GroupLayout rSPanelOpacity2Layout = new javax.swing.GroupLayout(rSPanelOpacity2);
        rSPanelOpacity2.setLayout(rSPanelOpacity2Layout);
        rSPanelOpacity2Layout.setHorizontalGroup(
            rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );
        rSPanelOpacity2Layout.setVerticalGroup(
            rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel7.add(rSPanelOpacity2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 300, 260, 40));

        rSButtonIcon_new18.setBackground(new java.awt.Color(255, 51, 102));
        rSButtonIcon_new18.setText("VENTAS CON TARJETA DE CREDITO:");
        rSButtonIcon_new18.setBackgroundHover(new java.awt.Color(255, 51, 102));
        rSButtonIcon_new18.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONEY_OFF);
        rSButtonIcon_new18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new18ActionPerformed(evt);
            }
        });
        jPanel7.add(rSButtonIcon_new18, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 430, -1));

        JTableBancosSalidas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ORDEN N.", "TOTAL", "FECHA"
            }
        ));
        JTableBancosSalidas1.setBackgoundHead(new java.awt.Color(60, 76, 143));
        JTableBancosSalidas1.setBackgoundHover(new java.awt.Color(60, 76, 143));
        JTableBancosSalidas1.setColorSecondary(new java.awt.Color(255, 255, 255));
        JTableBancosSalidas1.setEffectHover(true);
        JTableBancosSalidas1.setHighHead(50);
        JTableBancosSalidas1.setRowHeight(50);
        JTableBancosSalidas1.setShowHorizontalLines(true);
        JTableBancosSalidas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableBancosSalidas1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(JTableBancosSalidas1);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 380, 430, 210));

        rSButtonIcon_new12.setBackground(new java.awt.Color(255, 51, 102));
        rSButtonIcon_new12.setText("TOTAL L.");
        rSButtonIcon_new12.setBackgroundHover(new java.awt.Color(255, 51, 102));
        rSButtonIcon_new12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        rSButtonIcon_new12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new12ActionPerformed(evt);
            }
        });
        jPanel7.add(rSButtonIcon_new12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 590, 170, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Menu Principal");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel27MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel27MouseExited(evt);
            }
        });

        javax.swing.GroupLayout rSPanelOpacity5Layout = new javax.swing.GroupLayout(rSPanelOpacity5);
        rSPanelOpacity5.setLayout(rSPanelOpacity5Layout);
        rSPanelOpacity5Layout.setHorizontalGroup(
            rSPanelOpacity5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );
        rSPanelOpacity5Layout.setVerticalGroup(
            rSPanelOpacity5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel7.add(rSPanelOpacity5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 590, 260, 40));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(153, 0, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Código de Caja:");
        jPanel7.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 120, 30));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(153, 0, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Total en Váucher:");
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 150, 40));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(153, 0, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Total en Caja:");
        jPanel7.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 120, 30));

        JTextbuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTextbuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        JTextbuscar.setPlaceholder("Ingrese el total en Váucher");
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
        jPanel7.add(JTextbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 240, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(153, 0, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Total en efectivo:");
        jPanel7.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 150, 40));

        JTextbuscar1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTextbuscar1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        JTextbuscar1.setPlaceholder("Ingrese el total en efectivo");
        JTextbuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextbuscar1ActionPerformed(evt);
            }
        });
        JTextbuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTextbuscar1KeyReleased(evt);
            }
        });
        jPanel7.add(JTextbuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 240, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 0, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("0.00");
        jPanel7.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 220, 30));

        rSPanelOpacity3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 890, 680));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1005, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSPanelOpacity3, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSPanelOpacity3, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonIcon_new15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new15ActionPerformed

    private void rSButtonIcon_new16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new16ActionPerformed

    private void rSButtonIcon_new17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new17ActionPerformed

    private void JTableBancosSalidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableBancosSalidasMouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_JTableBancosSalidasMouseClicked

    private void rSButtonIcon_new11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new11ActionPerformed

    private void jLabel26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseEntered
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jLabel26MouseEntered

    private void jLabel26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseExited
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jLabel26MouseExited

    private void rSButtonIcon_new18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new18ActionPerformed

    private void JTableBancosSalidas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableBancosSalidas1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JTableBancosSalidas1MouseClicked

    private void rSButtonIcon_new12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new12ActionPerformed

    private void jLabel27MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel27MouseEntered

    private void jLabel27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel27MouseExited

    private void JTextbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextbuscarActionPerformed

    private void JTextbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextbuscarKeyReleased
        // TODO add your handling code here:
     
    }//GEN-LAST:event_JTextbuscarKeyReleased

    private void JTextbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextbuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextbuscar1ActionPerformed

    private void JTextbuscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextbuscar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextbuscar1KeyReleased

    private void rSButtonIconOne5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne5ActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_rSButtonIconOne5ActionPerformed

    private void rSButtonIconOne3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne3ActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }//GEN-LAST:event_rSButtonIconOne3ActionPerformed

    private void rSButtonIconOne4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne4ActionPerformed
        try {
            con.close();
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(VerificarOrden.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_rSButtonIconOne4ActionPerformed

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
            java.util.logging.Logger.getLogger(CerraCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CerraCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CerraCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CerraCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new CerraCaja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JCodigoDisponible1;
    private rojerusan.RSTableMetro1 JTableBancosSalidas;
    private rojerusan.RSTableMetro1 JTableBancosSalidas1;
    private RSMaterialComponent.RSTextFieldIconUno JTextbuscar;
    private RSMaterialComponent.RSTextFieldIconUno JTextbuscar1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel linesetting6;
    private javax.swing.JPanel linesetting7;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne3;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new11;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new12;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new15;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new16;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new17;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new18;
    private rojeru_san.RSLabelHora rSLabelHora2;
    private rojerusan.RSLabelIcon rSLabelIcon13;
    private rojerusan.RSLabelIcon rSLabelIcon18;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private rojerusan.RSLabelIcon rSLabelIcon7;
    private rojerusan.RSLabelIcon rSLabelIcon9;
    private rojeru_san.rspanel.RSPanelCircle rSPanelCircle2;
    private rojerusan.RSPanelOpacity rSPanelOpacity2;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity3;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity4;
    private rojerusan.RSPanelOpacity rSPanelOpacity5;
    // End of variables declaration//GEN-END:variables
}
