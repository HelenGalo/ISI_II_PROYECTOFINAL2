/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto;

import isi_2022_ii_proyecto.Conexion.ConexionBD;
import java.awt.JobAttributes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rojerusan.RSTableMetro1;

/**
 *
 * @author Edwin Rafael
 */
public class TipoVenta extends javax.swing.JFrame {
ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();
    RSTableMetro1 tablaorden;
    DefaultTableModel modelo;
    String usuario;
    int codigcliente; 
    int codigvendedor;
    int idorden=0;
    int codigocaja=0;
    String nombrecliente;
    String vendedor;
    String cajero;
    String subtotal;
    String total;
    String descuento;
    String isv;
    String totalp;
    int seleccion;
    String codigodireccion;
    String tipodepagoenvio;
    int tipov;
    
     public void setTablaorden(RSTableMetro1 tablaorden) {
        this.tablaorden = tablaorden;
    }
    
    
    /**
     * Creates new form TipoVenta
     */
    public TipoVenta() {
        initComponents();
        modelo = (DefaultTableModel) JTableBancos.getModel();
        rSPanel1.setVisible(false);
        rSPanel4.setVisible(false);
        rSPanelForma3.setVisible(false);
        rSPanel2.setSize(780,210);
        rSPanel2.setLocation(540,220);
        rSButtonIcon_new19.setVisible(false);
        iniciarEmpresasEnvio();
        
      
    }
    
    
    public void iniciarEmpresasEnvio(){
        rSComboBox1.addItem("Seleccionar Empresa");
        String nombre="";
         String SQL = "select ev.NombreEmpresa from EmpresasEnvio ev WHERE ev.Estado=1;";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
           
                nombre = rs.getString("NombreEmpresa");
                rSComboBox1.addItem(nombre);
              
            }

         
            
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    public void seteardatosorden(int idorden,String usuario,int codigcliente, int codigvendedor, int codigocaja, String ncliente, String nvendedor, String ncajero, String subt, String tot, String cantp, String des, String isv){
        this.usuario=usuario;
        this.codigcliente=codigcliente;
        this.codigvendedor = codigvendedor;
        this.codigocaja = codigocaja;
        this.nombrecliente = ncliente;
        this.vendedor= nvendedor;
        this.cajero=ncajero;
        this.subtotal=subt;
        this.total=tot;
        this.descuento=des;
        this.isv=isv;
        this.totalp=cantp;
        this.idorden=idorden;
        
        cargardatos();
        cargardirecciones();
        
        
    }
    
    public int ObtenerIdEmpresaEnvio(){
        int idempresaenvio=0;
        String SQL = "SELECT ev.IdEmpresaEnvio FROM EmpresasEnvio ev WHERE ev.NombreEmpresa='"+rSComboBox1.getSelectedItem().toString()+"';";
        
                try {
                        Statement st = (Statement) con.createStatement();
                        ResultSet rs = st.executeQuery(SQL);

                        while (rs.next()) {
                           idempresaenvio=rs.getInt("ev.IdEmpresaEnvio");
                           
                        }

                        
                    }catch(SQLException e){
                        System.out.println("Error al obtener el id de la direcciones"+e.getMessage());

                    }
                return idempresaenvio;
    }
    
    
    public void cargardirecciones(){
        String [] registros = new String[2];
        
        String SQL = "SELECT IdDireccionCliente,DireccionEnvioCompleta FROM DireccionesCliente WHERE IdCliente="+codigcliente;
        
                try {
                        Statement st = (Statement) con.createStatement();
                        ResultSet rs = st.executeQuery(SQL);

                        while (rs.next()) {
                            registros[0] = rs.getString("IdDireccionCliente");
                            registros[1] = rs.getString("DireccionEnvioCompleta");
                            modelo.addRow(registros);
                        }

                        
                    }catch(SQLException e){
                        System.out.println("Error al obtener las direcciones"+e.getMessage());

                    }
                JTableBancos.setModel(modelo);
    }
        
        
    public void cargardatos(){
        jLabel35.setText(vendedor);
        jLabel36.setText(String.valueOf(codigvendedor));
        jLabel38.setText(nombrecliente);
        jLabel39.setText(String.valueOf(codigcliente));
        jLabel42.setText(String.valueOf(codigocaja));
        jLabel44.setText(cajero);
        jLabel45.setText(usuario);
     
    }
    
    public void buscartarifa(){
        String empresa = "";
        empresa=rSComboBox1.getSelectedItem().toString();
        String tarifa="";
        String SQL = "select ev.Tarifa from EmpresasEnvio ev WHERE ev.Estado=1 AND ev.NombreEmpresa='"+empresa+"';";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
           
                tarifa = rs.getString("ev.Tarifa"); 
              
            }

         
            
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        jLabel61.setText(tarifa);
        
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
        linesetting3 = new javax.swing.JPanel();
        rSButtonIconOne5 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne3 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne4 = new RSMaterialComponent.RSButtonIconOne();
        linesetting4 = new javax.swing.JPanel();
        linesetting5 = new javax.swing.JPanel();
        dashboardview = new javax.swing.JPanel();
        rSPanelGradiente3 = new rspanelgradiente.RSPanelGradiente();
        jLabel34 = new javax.swing.JLabel();
        rSLabelIcon13 = new rojerusan.RSLabelIcon();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        rSLabelIcon14 = new rojerusan.RSLabelIcon();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        rSLabelIcon15 = new rojerusan.RSLabelIcon();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        rSLabelIcon16 = new rojerusan.RSLabelIcon();
        jLabel43 = new javax.swing.JLabel();
        rSLabelIcon17 = new rojerusan.RSLabelIcon();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        rSPanel2 = new necesario.RSPanel();
        rSPanel3 = new necesario.RSPanel();
        jLabel56 = new javax.swing.JLabel();
        rSPanel4 = new necesario.RSPanel();
        jLabel55 = new javax.swing.JLabel();
        rSPanelForma3 = new rojeru_san.rspanel.RSPanelForma();
        jLabel51 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        rSDateChooser2 = new rojeru_san.componentes.RSDateChooser();
        rSComboBox2 = new RSMaterialComponent.RSComboBox();
        jLabel50 = new javax.swing.JLabel();
        rSRadioButton2 = new rojerusan.RSRadioButton();
        rSLabelIcon18 = new rojerusan.RSLabelIcon();
        rSRadioButton3 = new rojerusan.RSRadioButton();
        rSButtonIcon_new19 = new newscomponents.RSButtonIcon_new();
        jLabel52 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        rSComboBox1 = new rojerusan.RSComboMetro();
        rSPanel1 = new necesario.RSPanel();
        rSPanel5 = new necesario.RSPanel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableBancos = new rojerusan.RSTableMetro1();
        rSButtonIcon_new18 = new newscomponents.RSButtonIcon_new();
        jLabel57 = new javax.swing.JLabel();
        rSButtonIcon_new20 = new newscomponents.RSButtonIcon_new();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(20, 101, 187));
        jPanel1.setForeground(new java.awt.Color(20, 101, 187));

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

        linesetting3.setBackground(new java.awt.Color(20, 101, 187));
        linesetting3.setPreferredSize(new java.awt.Dimension(50, 10));

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

        javax.swing.GroupLayout linesetting3Layout = new javax.swing.GroupLayout(linesetting3);
        linesetting3.setLayout(linesetting3Layout);
        linesetting3Layout.setHorizontalGroup(
            linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonIconOne3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        linesetting3Layout.setVerticalGroup(
            linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        linesetting4.setBackground(new java.awt.Color(20, 101, 187));
        linesetting4.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesetting4Layout = new javax.swing.GroupLayout(linesetting4);
        linesetting4.setLayout(linesetting4Layout);
        linesetting4Layout.setHorizontalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        linesetting4Layout.setVerticalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        linesetting5.setBackground(new java.awt.Color(20, 101, 187));
        linesetting5.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesetting5Layout = new javax.swing.GroupLayout(linesetting5);
        linesetting5.setLayout(linesetting5Layout);
        linesetting5Layout.setHorizontalGroup(
            linesetting5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );
        linesetting5Layout.setVerticalGroup(
            linesetting5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
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
                .addGap(13, 13, 13)
                .addComponent(linesetting5, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linesetting4, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linesetting3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        dashboardview.setBackground(new java.awt.Color(255, 255, 255));
        dashboardview.setLayout(null);

        rSPanelGradiente3.setColorPrimario(new java.awt.Color(51, 153, 255));
        rSPanelGradiente3.setColorSecundario(new java.awt.Color(20, 101, 187));
        rSPanelGradiente3.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.VERTICAL);

        jLabel34.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Vendedor");

        rSLabelIcon13.setBackground(new java.awt.Color(255, 255, 255));
        rSLabelIcon13.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon13.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        rSLabelIcon13.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.VIBRATION);

        jLabel35.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Vendedor");

        jLabel36.setFont(new java.awt.Font("Franklin Gothic Book", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Vendedor");

        jLabel17.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Verificar");

        jLabel37.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Cliente");

        rSLabelIcon14.setBackground(new java.awt.Color(255, 255, 255));
        rSLabelIcon14.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon14.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        rSLabelIcon14.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_CIRCLE);

        jLabel38.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Cliente");

        jLabel39.setFont(new java.awt.Font("Franklin Gothic Book", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Cliente");

        jLabel40.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Caja");

        rSLabelIcon15.setBackground(new java.awt.Color(255, 255, 255));
        rSLabelIcon15.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon15.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        rSLabelIcon15.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.AIRPLAY);

        jLabel41.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("CodigoCaja");

        jLabel42.setFont(new java.awt.Font("Franklin Gothic Book", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Caja");

        rSLabelIcon16.setBackground(new java.awt.Color(255, 255, 255));
        rSLabelIcon16.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon16.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        rSLabelIcon16.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_CIRCLE);

        jLabel43.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Usuario");

        rSLabelIcon17.setBackground(new java.awt.Color(255, 255, 255));
        rSLabelIcon17.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon17.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        rSLabelIcon17.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CONTACTS);

        jLabel44.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Usuario");

        jLabel45.setFont(new java.awt.Font("Franklin Gothic Book", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Usuario");

        jLabel31.setFont(new java.awt.Font("Franklin Gothic Book", 0, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Orden");

        javax.swing.GroupLayout rSPanelGradiente3Layout = new javax.swing.GroupLayout(rSPanelGradiente3);
        rSPanelGradiente3.setLayout(rSPanelGradiente3Layout);
        rSPanelGradiente3Layout.setHorizontalGroup(
            rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel31))
                .addGap(34, 34, 34)
                .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(rSLabelIcon16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(rSLabelIcon14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(rSLabelIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(rSLabelIcon17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        rSPanelGradiente3Layout.setVerticalGroup(
            rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSLabelIcon14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSLabelIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSLabelIcon17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(rSLabelIcon16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dashboardview.add(rSPanelGradiente3);
        rSPanelGradiente3.setBounds(0, 0, 1360, 108);

        rSPanel2.setBackground(new java.awt.Color(255, 255, 255));
        rSPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 0, 255), new java.awt.Color(153, 0, 255)));
        rSPanel2.setColorBackground(new java.awt.Color(255, 255, 255));
        rSPanel2.setLayout(null);

        rSPanel3.setBackground(new java.awt.Color(153, 153, 255));
        rSPanel3.setColorBackground(new java.awt.Color(0, 55, 133));

        jLabel56.setFont(new java.awt.Font("Franklin Gothic Book", 3, 24)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("TIPO DE VENTA");

        javax.swing.GroupLayout rSPanel3Layout = new javax.swing.GroupLayout(rSPanel3);
        rSPanel3.setLayout(rSPanel3Layout);
        rSPanel3Layout.setHorizontalGroup(
            rSPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        rSPanel3Layout.setVerticalGroup(
            rSPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        rSPanel2.add(rSPanel3);
        rSPanel3.setBounds(0, 0, 780, 50);

        rSPanel4.setBackground(new java.awt.Color(153, 153, 255));
        rSPanel4.setColorBackground(new java.awt.Color(0, 55, 133));
        rSPanel4.setLayout(null);

        jLabel55.setFont(new java.awt.Font("Franklin Gothic Book", 3, 24)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("DIRECCIÓN DE ENVIÓ");
        rSPanel4.add(jLabel55);
        jLabel55.setBounds(30, 8, 730, 40);

        rSPanel2.add(rSPanel4);
        rSPanel4.setBounds(0, 180, 790, 60);

        rSPanelForma3.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelForma3.setLayout(null);

        jLabel51.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(102, 0, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Hora de Entrega:");
        rSPanelForma3.add(jLabel51);
        jLabel51.setBounds(280, 130, 210, 20);

        jLabel58.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(102, 0, 255));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("direccion");
        rSPanelForma3.add(jLabel58);
        jLabel58.setBounds(30, 30, 420, 40);

        jLabel53.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(102, 0, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Dirección:");
        rSPanelForma3.add(jLabel53);
        jLabel53.setBounds(30, 0, 420, 20);

        jLabel62.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(102, 0, 255));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("Programar Envio");
        rSPanelForma3.add(jLabel62);
        jLabel62.setBounds(30, 80, 420, 20);

        jLabel63.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(102, 0, 255));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("Fecha de Entrega:");
        rSPanelForma3.add(jLabel63);
        jLabel63.setBounds(20, 130, 230, 20);

        rSDateChooser2.setColorBackground(new java.awt.Color(102, 102, 255));
        rSDateChooser2.setPlaceholder("Fecha de Entrega");
        rSPanelForma3.add(rSDateChooser2);
        rSDateChooser2.setBounds(10, 170, 230, 40);

        rSComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Hora de Entrega", "00:00:00", "01:00:00", "02:00:00", "03:00:00", "04:00:00", "05:00:00", "06:00:00", "07:00:00", "08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00", "24:00:00" }));
        rSPanelForma3.add(rSComboBox2);
        rSComboBox2.setBounds(270, 170, 230, 40);

        rSPanel2.add(rSPanelForma3);
        rSPanelForma3.setBounds(10, 250, 510, 220);

        jLabel50.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(102, 0, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Seleccione el tipo de la venta:");
        rSPanel2.add(jLabel50);
        jLabel50.setBounds(210, 90, 340, 20);

        rSRadioButton2.setText("A DOMICILIO");
        rSRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rSRadioButton2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButton2ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSRadioButton2);
        rSRadioButton2.setBounds(200, 120, 180, 40);

        rSLabelIcon18.setBackground(new java.awt.Color(255, 255, 255));
        rSLabelIcon18.setForeground(new java.awt.Color(102, 0, 255));
        rSLabelIcon18.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        rSLabelIcon18.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SHOPPING_CART);
        rSLabelIcon18.setInheritsPopupMenu(true);
        rSPanel2.add(rSLabelIcon18);
        rSLabelIcon18.setBounds(30, 80, 100, 90);

        rSRadioButton3.setText("PRESENCIAL");
        rSRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rSRadioButton3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButton3ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSRadioButton3);
        rSRadioButton3.setBounds(380, 120, 180, 40);

        rSButtonIcon_new19.setBackground(new java.awt.Color(0, 102, 204));
        rSButtonIcon_new19.setText("Aceptar");
        rSButtonIcon_new19.setAlignmentX(0.5F);
        rSButtonIcon_new19.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new19.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEND);
        rSButtonIcon_new19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new19ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonIcon_new19);
        rSButtonIcon_new19.setBounds(580, 100, 130, 40);

        jLabel52.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(102, 0, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Empresa de envio:");
        rSPanel2.add(jLabel52);
        jLabel52.setBounds(550, 250, 170, 21);

        jLabel59.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(102, 0, 255));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("L.");
        rSPanel2.add(jLabel59);
        jLabel59.setBounds(540, 360, 20, 21);

        jLabel60.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(102, 0, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Pago de Tarifa:");
        rSPanel2.add(jLabel60);
        jLabel60.setBounds(540, 330, 200, 21);

        jLabel61.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(102, 0, 255));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("0.00");
        rSPanel2.add(jLabel61);
        jLabel61.setBounds(560, 360, 180, 21);

        rSComboBox1.setColorArrow(new java.awt.Color(102, 0, 255));
        rSComboBox1.setColorFondo(new java.awt.Color(60, 76, 143));
        rSComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rSComboBox1ItemStateChanged(evt);
            }
        });
        rSComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSComboBox1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rSComboBox1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rSComboBox1MouseExited(evt);
            }
        });
        rSPanel2.add(rSComboBox1);
        rSComboBox1.setBounds(540, 280, 190, 30);

        dashboardview.add(rSPanel2);
        rSPanel2.setBounds(540, 130, 780, 480);

        rSPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.blue, java.awt.Color.blue));
        rSPanel1.setColorBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout rSPanel5Layout = new javax.swing.GroupLayout(rSPanel5);
        rSPanel5.setLayout(rSPanel5Layout);
        rSPanel5Layout.setHorizontalGroup(
            rSPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        rSPanel5Layout.setVerticalGroup(
            rSPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        jLabel32.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 51, 204));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("DIRECCIONES GUARDADAS.");

        JTableBancos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero de Dirección", "Dirección"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTableBancos.setBackgoundHead(new java.awt.Color(255, 255, 255));
        JTableBancos.setBackgoundHover(new java.awt.Color(60, 76, 143));
        JTableBancos.setBorderHead(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 102, 255)));
        JTableBancos.setColorBorderHead(new java.awt.Color(0, 51, 255));
        JTableBancos.setColorBorderRows(new java.awt.Color(255, 255, 255));
        JTableBancos.setColorSecondary(new java.awt.Color(255, 255, 255));
        JTableBancos.setEffectHover(true);
        JTableBancos.setForegroundHead(new java.awt.Color(51, 51, 255));
        JTableBancos.setHighHead(50);
        JTableBancos.setRowHeight(50);
        JTableBancos.setShowHorizontalLines(true);
        JTableBancos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableBancosMouseClicked(evt);
            }
        });
        JTableBancos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTableBancosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(JTableBancos);
        if (JTableBancos.getColumnModel().getColumnCount() > 0) {
            JTableBancos.getColumnModel().getColumn(0).setResizable(false);
        }

        rSButtonIcon_new18.setBackground(new java.awt.Color(0, 102, 204));
        rSButtonIcon_new18.setText("Seleccionar");
        rSButtonIcon_new18.setAlignmentX(0.5F);
        rSButtonIcon_new18.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new18.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEND);
        rSButtonIcon_new18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new18ActionPerformed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(102, 0, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Seleccione una dirección:");

        rSButtonIcon_new20.setBackground(new java.awt.Color(0, 102, 204));
        rSButtonIcon_new20.setText("Agregar");
        rSButtonIcon_new20.setAlignmentX(0.5F);
        rSButtonIcon_new20.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new20.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        rSButtonIcon_new20.setName(""); // NOI18N
        rSButtonIcon_new20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rSPanel1Layout = new javax.swing.GroupLayout(rSPanel1);
        rSPanel1.setLayout(rSPanel1Layout);
        rSPanel1Layout.setHorizontalGroup(
            rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(rSButtonIcon_new18, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(rSButtonIcon_new20, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rSPanel1Layout.setVerticalGroup(
            rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addComponent(rSPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonIcon_new18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIcon_new20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        dashboardview.add(rSPanel1);
        rSPanel1.setBounds(70, 130, 360, 470);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 1348, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(dashboardview, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1360, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardview, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void rSRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButton2ActionPerformed
        // TODO add your handling code here:
        if(rSRadioButton2.isSelected()==false){
            
            rSPanel1.setVisible(false);
            rSPanel4.setVisible(false);
            rSPanelForma3.setVisible(false);
            rSPanel2.setSize(780,210);
            rSPanel2.setLocation(540,220);
        }else{
            tipov =2;
            rSButtonIcon_new19.setVisible(false);
            rSRadioButton3.setSelected(false);
            rSPanel1.setVisible(true);
            rSButtonIcon_new19.setLocation(580,410);
        }
       
     
    }//GEN-LAST:event_rSRadioButton2ActionPerformed

    private void rSButtonIcon_new18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new18ActionPerformed
        // TODO add your handling code here:
        if(codigodireccion!=null){
            jLabel58.setText(JTableBancos.getValueAt(seleccion, 1).toString());
            rSPanel2.setSize(780,480);
            rSPanel2.setLocation(540,130);
            rSPanel4.setVisible(true);
            rSPanelForma3.setVisible(true);
            rSButtonIcon_new19.setLocation(590, 420);
            rSButtonIcon_new19.setVisible(true);
            
        }else{
            JOptionPane.showMessageDialog(this, "ATENCION", "Seleccione un valor", JOptionPane.INFORMATION_MESSAGE);
        }
        
        

    }//GEN-LAST:event_rSButtonIcon_new18ActionPerformed

    private void JTableBancosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableBancosMouseClicked
        // TODO add your handling code here:
        seleccion = JTableBancos.rowAtPoint(evt.getPoint());
        codigodireccion =  JTableBancos.getValueAt(seleccion,0 ).toString();

    }//GEN-LAST:event_JTableBancosMouseClicked

    private void JTableBancosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTableBancosKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_JTableBancosKeyReleased

    private void rSRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButton3ActionPerformed
        // TODO add your handling code here
        if(rSRadioButton3.isSelected()==false){
            
            rSButtonIcon_new19.setVisible(false);
            rSPanel2.setSize(780,210);
            rSPanel2.setLocation(540,220);
            rSPanel1.setVisible(false);
            rSPanel4.setVisible(false);
            rSPanelForma3.setVisible(false);
            
            
        }else{
           tipov =1;
           rSButtonIcon_new19.setLocation(590,100);
           rSButtonIcon_new19.setVisible(true);
           rSRadioButton2.setSelected(false);
           rSPanel1.setVisible(false);
           rSPanel4.setVisible(false);
           rSPanelForma3.setVisible(false);
           rSPanel2.setSize(780,210);
           rSPanel2.setLocation(540,220);
        
        }
        
        
    
    }//GEN-LAST:event_rSRadioButton3ActionPerformed

    private void rSButtonIcon_new19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new19ActionPerformed
        // TODO add your handling code here:
        if(rSRadioButton3.isSelected()){
            VerificarOrden vo = new VerificarOrden();
            vo.setTablaorden(tablaorden);
            vo.seteardatosorden(idorden,usuario, codigcliente, codigvendedor, Integer.valueOf(jLabel42.getText()), jLabel38.getText(), jLabel35.getText(), jLabel44.getText(), subtotal,  total,  totalp,  descuento,  isv, tipov);
            vo.cargartabla();
            vo.setVisible(true);
        }else{
            if(rSRadioButton2.isSelected()){
                if(rSDateChooser2.getDatoFecha().toString().length()>0 && rSComboBox2.getSelectedItem().toString()!="Seleccionar Hora de Entrega"){
                    float ntotal = Float.parseFloat(total)+Float.parseFloat(jLabel61.getText());
                    VerificarOrdenADomicilio vo = new VerificarOrdenADomicilio();
                    vo.setTablaorden(tablaorden);
                    vo.seteardatosorden(idorden,usuario, codigcliente, codigvendedor, Integer.valueOf(jLabel42.getText()), jLabel38.getText(), jLabel35.getText(), jLabel44.getText(), subtotal,  String.valueOf(ntotal),  totalp,  descuento,  isv, jLabel61.getText(),2);
                    String formato="yyyy/MM/dd";
                    String Fe="";
                    Date FechaE;
                    FechaE=rSDateChooser2.getDatoFecha();
                    SimpleDateFormat formateador = new SimpleDateFormat(formato);
                    Fe = formateador.format(FechaE);
                    vo.setdatosEnvio(Integer.parseInt(codigodireccion), Fe, rSComboBox2.getSelectedItem().toString(), ObtenerIdEmpresaEnvio());
                    vo.cargartabla();
                    vo.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Por favor Programe el pedido");
                }
            
            }
        }
         
    try {
        con.close();
    } catch (SQLException ex) {
        Logger.getLogger(TipoVenta.class.getName()).log(Level.SEVERE, null, ex);
    }
         this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new19ActionPerformed

    private void rSButtonIcon_new20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new20ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_rSButtonIcon_new20ActionPerformed

    private void rSComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSComboBox1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_rSComboBox1MouseClicked

    private void rSComboBox1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSComboBox1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_rSComboBox1MouseEntered

    private void rSComboBox1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSComboBox1MouseExited
        // TODO add your handling code here:
      
    }//GEN-LAST:event_rSComboBox1MouseExited

    private void rSComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rSComboBox1ItemStateChanged
        // TODO add your handling code here:
        buscartarifa();
    }//GEN-LAST:event_rSComboBox1ItemStateChanged

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
            java.util.logging.Logger.getLogger(TipoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TipoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TipoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TipoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TipoVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private rojerusan.RSTableMetro1 JTableBancos;
    private javax.swing.JPanel dashboardview;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel linesetting3;
    private javax.swing.JPanel linesetting4;
    private javax.swing.JPanel linesetting5;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne3;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new18;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new19;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new20;
    private rojerusan.RSComboMetro rSComboBox1;
    private RSMaterialComponent.RSComboBox rSComboBox2;
    private rojeru_san.componentes.RSDateChooser rSDateChooser2;
    private rojerusan.RSLabelIcon rSLabelIcon13;
    private rojerusan.RSLabelIcon rSLabelIcon14;
    private rojerusan.RSLabelIcon rSLabelIcon15;
    private rojerusan.RSLabelIcon rSLabelIcon16;
    private rojerusan.RSLabelIcon rSLabelIcon17;
    private rojerusan.RSLabelIcon rSLabelIcon18;
    private necesario.RSPanel rSPanel1;
    private necesario.RSPanel rSPanel2;
    private necesario.RSPanel rSPanel3;
    private necesario.RSPanel rSPanel4;
    private necesario.RSPanel rSPanel5;
    private rojeru_san.rspanel.RSPanelForma rSPanelForma3;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente3;
    private rojerusan.RSRadioButton rSRadioButton2;
    private rojerusan.RSRadioButton rSRadioButton3;
    // End of variables declaration//GEN-END:variables
}
