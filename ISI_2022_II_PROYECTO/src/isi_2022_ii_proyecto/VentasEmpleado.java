/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto;

import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.ConfirmacionGuardar;
import isi_2022_ii_proyecto.Recursos.VentanaEmergente1;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rojeru_san.complementos.RSUtilities;
import rojerusan.RSEffectFade;

/**
 *
 * @author Edwin Rafael
 */
public class VentasEmpleado extends javax.swing.JFrame {
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();

      String prod;
    
  String usuario;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
        jLabel15.setText("Usuario en sesion: "+usuario);
    }
      

   
    /**
     * Creates new form CalendarForm
     */
    public VentasEmpleado() {
        RSUtilities.setFullScreenJFrame(this);
        initComponents();
      setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
        RSUtilities.setOpaqueWindow(this, false);
        RSUtilities.setOpacityComponent(this.jPanel1, 150);
       
        
    }
    
    
    public void conectar(){
        conexion.setVentaempleado(this);
        con = conexion.conexion();
    }
        public void validarconexion(){

        if(con==null){
            conectar();
            
            
        }
        
    }
    
    public void conectarerror(){
        conexion.setVentaempleado(this);
        conexion.setPass(false);
        con = conexion.conexion();
    }
    
       public void conectarinicio(){
        conexion.setVentaempleado(this);
        con = conexion.conexion();
    }
       
        public void conectarsinerror(){
        conexion.setPass(true);
        conexion.setVentaempleado(this);
        con = conexion.conexion();
    }
     
     
     public void limpiartabla(){
        DefaultTableModel modelo =  (DefaultTableModel) JTableVentasE.getModel();
        while (modelo.getRowCount() > 0)
        {
        modelo.removeRow(0);
        }
        
        
        
       
        
     
    }

    
   private void mostrar(){
         String nombre="";
         String Total ="";
            String[] registros = new String[5];
        DefaultTableModel modelo =  (DefaultTableModel) JTableVentasE.getModel();

     String SQL = "SELECT v.IdOrden,v.FechaVenta,v.HoraVenta,c.Nombres,c.Apellidos,e.PrimerNombre, e.PrimerApellido,e.SegundoApellido,tp.Descripcion From Empleados e\n" +
                    "INNER JOIN Ventas v ON v.IdEmpleado = e.IdEmpleado\n" +
                    "INNER JOIN Clientes c ON c.IdCliente = v.IdCliente\n" +
                     "INNER JOIN TipoPago tp ON tp.IdTipoPago = v.IdTipoPago WHERE e.IdEmpleado LIKE '"+JTextbuscar.getText()+"%'";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                registros[0] = rs.getString("v.IdOrden");
                registros[1] = rs.getString("v.FechaVenta");
                registros[2] = rs.getString("v.HoraVenta");
                registros[3] = rs.getString("c.Nombres")+" "+rs.getString("c.Apellidos");
                registros[4] = rs.getString("tp.Descripcion");
                modelo.addRow(registros);
                nombre= rs.getString("e.PrimerNombre")+" "+rs.getString("e.PrimerApellido");
             
            }
            nombreP.setText(nombre);
       

            JTableVentasE.setModel(modelo);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
            con=null;
            validarconexion();
        }
        
    } 
   private void buscarNombre(){
         String nombre="";
         String Total ="";
          //  String[] registros = new String[5];
        DefaultTableModel modelo =  (DefaultTableModel) JTableVentasE.getModel();

     String SQL = "SELECT v.IdOrden,v.FechaVenta,v.HoraVenta,c.Nombres,c.Apellidos,e.PrimerNombre, e.PrimerApellido,e.SegundoApellido,tp.Descripcion, COUNT(v.IdOrden) as Total From Empleados e\n" +
                    "INNER JOIN Ventas v ON v.IdEmpleado = e.IdEmpleado\n" +
                    "INNER JOIN Clientes c ON c.IdCliente = v.IdCliente\n" +
                     "INNER JOIN TipoPago tp ON tp.IdTipoPago = v.IdTipoPago WHERE e.IdEmpleado LIKE '"+JTextbuscar.getText()+"%'";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
              
                nombre= rs.getString("e.PrimerNombre")+" "+rs.getString("e.PrimerApellido");
                Total=rs.getString("Total");
            }
            nombreP.setText(nombre);
            TV.setText(Total);

            JTableVentasE.setModel(modelo);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
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
        rSPanelOpacity1 = new RSMaterialComponent.RSPanelOpacity();
        jPanel4 = new javax.swing.JPanel();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        jLabel6 = new javax.swing.JLabel();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        linesetting4 = new javax.swing.JPanel();
        linesetting5 = new javax.swing.JPanel();
        rSButtonIconOne4 = new RSMaterialComponent.RSButtonIconOne();
        jPanel3 = new javax.swing.JPanel();
        rSPanelOpacity3 = new RSMaterialComponent.RSPanelOpacity();
        jLabel7 = new javax.swing.JLabel();
        rSLabelIcon8 = new rojerusan.RSLabelIcon();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rSLabelIcon17 = new rojerusan.RSLabelIcon();
        jLabel13 = new javax.swing.JLabel();
        rSLabelIcon12 = new rojerusan.RSLabelIcon();
        jLabel14 = new javax.swing.JLabel();
        rSLabelIcon6 = new rojerusan.RSLabelIcon();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableVentasE = new rojerusan.RSTableMetro1();
        JTextbuscar = new RSMaterialComponent.RSTextFieldIconUno();
        TV = new javax.swing.JLabel();
        nombreP1 = new javax.swing.JLabel();
        nombreP = new javax.swing.JLabel();
        TV1 = new javax.swing.JLabel();
        rSButtonIcon_new3 = new newscomponents.RSButtonIcon_new();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        rSPanelOpacity1.setBackground(new java.awt.Color(232, 245, 255));
        rSPanelOpacity1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);
        jPanel4.add(rSLabelIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("MODÚLO VENTAS");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 220, 40));

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE_OUTLINE);
        jPanel4.add(rSLabelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 60, 40));

        rSLabelHora1.setForeground(new java.awt.Color(20, 101, 187));
        jPanel4.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 108, -1));

        jLabel15.setBackground(new java.awt.Color(102, 51, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 0, 255));
        jLabel15.setText("Usuario en sesion: ");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));

        rSPanelOpacity1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 870, 50));

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
            .addGap(0, 40, Short.MAX_VALUE)
        );
        linesetting4Layout.setVerticalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        linesetting5.setBackground(new java.awt.Color(20, 101, 187));
        linesetting5.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonIconOne4.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIconOne4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        rSButtonIconOne4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne4ActionPerformed(evt);
            }
        });

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
                .addGap(242, 242, 242)
                .addComponent(linesetting5, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(linesetting4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(310, 310, 310)
                .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(linesetting4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(linesetting5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        rSPanelOpacity1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        rSPanelOpacity3.setBackground(new java.awt.Color(60, 76, 143));

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
        jLabel11.setText("Modúlo Ventas");

        rSLabelIcon17.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon17.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LOCAL_MALL);
        rSLabelIcon17.setInheritsPopupMenu(true);
        rSLabelIcon17.setName(""); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("/");

        rSLabelIcon12.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
        rSLabelIcon12.setName(""); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Listado de Ventas");

        rSLabelIcon6.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon6.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        rSLabelIcon6.setName(""); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ventas por Empleado");

        rSPanelOpacity3.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(rSLabelIcon8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(rSLabelIcon17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(rSLabelIcon12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(rSLabelIcon6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout rSPanelOpacity3Layout = new javax.swing.GroupLayout(rSPanelOpacity3);
        rSPanelOpacity3.setLayout(rSPanelOpacity3Layout);
        rSPanelOpacity3Layout.setHorizontalGroup(
            rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity3Layout.createSequentialGroup()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSLabelIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel8)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        rSPanelOpacity3Layout.setVerticalGroup(
            rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSLabelIcon17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTableVentasE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Orden", "FechaVenta", "HoraVenta", "Cliente", "Tipo de Pago"
            }
        ));
        JTableVentasE.setToolTipText("");
        JTableVentasE.setBackgoundHead(new java.awt.Color(60, 76, 143));
        JTableVentasE.setBackgoundHover(new java.awt.Color(60, 76, 143));
        JTableVentasE.setColorSecondary(new java.awt.Color(255, 255, 255));
        JTableVentasE.setEffectHover(true);
        JTableVentasE.setHighHead(50);
        JTableVentasE.setRowHeight(50);
        JTableVentasE.setShowHorizontalLines(true);
        JTableVentasE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableVentasEMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTableVentasE);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelOpacity3, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(rSPanelOpacity3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );

        rSPanelOpacity1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 830, 350));

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
        rSPanelOpacity1.add(JTextbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 160, 30));

        TV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TV.setForeground(new java.awt.Color(153, 0, 255));
        TV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TV.setText("Total");
        rSPanelOpacity1.add(TV, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 80, 30));

        nombreP1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nombreP1.setForeground(new java.awt.Color(153, 0, 255));
        nombreP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreP1.setText("Empleado:");
        rSPanelOpacity1.add(nombreP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 110, 30));

        nombreP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nombreP.setForeground(new java.awt.Color(153, 0, 255));
        nombreP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreP.setText("Empleado");
        rSPanelOpacity1.add(nombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 210, 30));

        TV1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TV1.setForeground(new java.awt.Color(153, 0, 255));
        TV1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TV1.setText("Total Ventas");
        rSPanelOpacity1.add(TV1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 110, 30));

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
        rSPanelOpacity1.add(rSButtonIcon_new3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 110, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSPanelOpacity1, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSPanelOpacity1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTableVentasEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableVentasEMouseClicked

        int seleccion = JTableVentasE.rowAtPoint(evt.getPoint());
        prod =   String.valueOf(JTableVentasE.getValueAt(seleccion, 0));
        System.out.println("THIs" +prod);
    }//GEN-LAST:event_JTableVentasEMouseClicked

    private void JTextbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextbuscarActionPerformed
        limpiartabla();
        buscarNombre();
            mostrar();
    }//GEN-LAST:event_JTextbuscarActionPerformed

    private void JTextbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextbuscarKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            limpiartabla();
            buscarNombre();
            mostrar();
        }
    }//GEN-LAST:event_JTextbuscarKeyReleased

    private void rSButtonIconOne4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne4ActionPerformed
  try {
            // TODO add your handling code here:
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIconOne4ActionPerformed

    private void rSButtonIcon_new3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new3ActionPerformed
        Ventas v= new Ventas();
        v.setUsuario(usuario);
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new3ActionPerformed

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
            java.util.logging.Logger.getLogger(VentasEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentasEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentasEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentasEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentasEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSTableMetro1 JTableVentasE;
    private RSMaterialComponent.RSTextFieldIconUno JTextbuscar;
    private javax.swing.JLabel TV;
    private javax.swing.JLabel TV1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel linesetting4;
    private javax.swing.JPanel linesetting5;
    private javax.swing.JLabel nombreP;
    private javax.swing.JLabel nombreP1;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new3;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rojerusan.RSLabelIcon rSLabelIcon1;
    private rojerusan.RSLabelIcon rSLabelIcon12;
    private rojerusan.RSLabelIcon rSLabelIcon17;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private rojerusan.RSLabelIcon rSLabelIcon6;
    private rojerusan.RSLabelIcon rSLabelIcon8;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity1;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity3;
    // End of variables declaration//GEN-END:variables
}
