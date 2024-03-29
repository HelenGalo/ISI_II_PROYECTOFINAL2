/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto;
import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.ColorFondo;
import isi_2022_ii_proyecto.Recursos.VentanaInformativaPOSMENU;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Edwin Rafael
 */
public class Menu extends javax.swing.JFrame {
    boolean a = true;
    String usuario;
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();

    public void setUsuario(String usuario) {
        this.usuario = usuario;
        jLabel10.setText("Usuario en sesion: "+usuario);
    }
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
        
    }
    
     public void conectar(){
        conexion.setMenu(this);
        con = conexion.conexion();
    }
    
    
 
    
    
    public void refrescarInt(){
       if(validarconexion()==true){
           String SQL1 = "UPDATE Usuarios u SET u.Intentos=? WHERE u.Usuario="+"'"+usuario+"'";
  
        try {
            int intentosAct=3 ;
            PreparedStatement preparedStmt = con.prepareStatement(SQL1);
            preparedStmt.setInt (1, intentosAct);
            preparedStmt.execute();
            
             //JOptionPane.showMessageDialog(null, "Intentos Refrescados");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
       }else{
           conectar();
           
       }
        
        
       
    }
    
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
    
    
    
     public boolean validarRolPOS(){
        boolean estado=false;

             
            int idrol=0;
         String SQL = "SELECT u.IdRol FROM Usuarios u WHERE u.Usuario='"+usuario+"';";
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idrol = rs.getInt("u.IdRol");
               
                
            }
        }catch(SQLException e){
              JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        
        if(idrol==2 || idrol==1){
            estado = true;
        }
    
       
        
        
   
        
        return estado;
        
        
    }
     
     public boolean validarRoles(String m){
        boolean estado=false;
     
         int idrol=0;
         String SQL = "SELECT u.IdRol FROM Usuarios u WHERE u.Usuario='"+usuario+"';";
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idrol = rs.getInt("u.IdRol");
               
                
            }
        }catch(SQLException e){
              JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        
        switch(m){
            case "Productos":
                if(idrol==1 || idrol==5){
                    estado = true;
                }
                break;
            case "Empleados":
                if(idrol==1){
                    estado = true;
                }
                break;
            case "Clientes":
                if(idrol==1 || idrol==2 ){
                    estado = true;
                }
                break;
            case "Usuarios":
                if(idrol==1){
                    estado = true;
                }
                break;
            case "Proveedores":
                if(idrol==1 || idrol==5){
                    estado = true;
                }
                break;
            case "Bancos":
                if(idrol==1 || idrol==4){
                    estado = true;
                }
                break;
            case "Logistica":
                if(idrol==1 || idrol==6){
                    estado = true;
                }
                break;
            case "Ventas":
                if(idrol==1 || idrol==7){
                    estado = true;
                }
                break;
            case "POS":
                if(idrol==1 || idrol==2){
                    estado = true;
                }
                break;
            case "Compras":
                if(idrol==1 || idrol==6){
                    estado = true;
                }
                break;
            case "Envios":
                if(idrol==1){
                    estado = true;
                }
                break;
            case "Almacenes":
                if(idrol==1 || idrol==5){
                    estado = true;
                }
                break;
            case "Caja":
                if(idrol==1 || idrol==7){
                    estado = true;
                }
                break;
            case "Facturas":
                if(idrol==1 || idrol==7){
                    estado = true;  
                }
                break;
        }
        
        
    
       
       
        
        
   
        
        return estado;
        
        
    }
     
     
   
    
 
    
    public boolean validarcaja(){
        boolean estado=false;
     
          int idestadocaja=0;
         String SQL = "Select c.IdEstadoCaja from Caja c\n" +
                    "INNER JOIN Usuarios u ON u.IdUsuario = c.IdUsuario\n" +
                    "WHERE u.Usuario ='"+usuario+"' and c.IdEstadoCaja=2;";
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idestadocaja = rs.getInt("c.IdEstadoCaja");
               
                
            }
        }catch(SQLException e){
              JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        if(idestadocaja==2 || idestadocaja==1){
            estado = true;
        } 
     
        
       
        
        
        
        return estado;
        
        
    }
    
    public boolean validarconexion(){
        boolean a = false;
        if(con==null){
            conectar();
            
            
        }else{
            a = true;
        }
        return a;
    }
    
    
    
     public int obtenerlimitefactura(){
    String SQL1 = "Select fc.RangoFinal from FormatoFacturaCabecera fc Where fc.IdEstado=1;";
     String nfactura=""; 
      char vi;
      char vf;
      String contadori="";
      String contadorf="";
        
         try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL1);

            while (rs.next()) {
                nfactura =rs.getString("fc.RangoFinal");
             
            }
        
     
            }catch(SQLException e){
                 System.out.println("Error "+e.getMessage());
        
            }
         
         
         for(int i=0; i<11;i++){
                vi= nfactura.charAt(i);
                contadori = contadori+String.valueOf(vi);
         }
         
         for(int i=11; i<19;i++){
                vf= nfactura.charAt(i);
                contadorf = contadorf+String.valueOf(vf);
         }
         
         int nvalorfactura=0;
         nvalorfactura = Integer.parseInt(contadorf);
         return nvalorfactura;
         
}
    
    public String obtenerRangoInicial(){
    String SQL1 = "Select fc.RangoInicial from FormatoFacturaCabecera fc Where fc.IdEstado=1;";
     String nfactura=""; 
      char vi;
      char vf;
      String contadori="";
      String contadorf="";
        
         try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL1);

            while (rs.next()) {
                nfactura =rs.getString("fc.RangoInicial");
             
            }
        
     
            }catch(SQLException e){
                 System.out.println("Error "+e.getMessage());
        
            }
         
       
         
         
         return nfactura;
         
}
    
    
    public boolean validarfactura(){
          boolean a= false;
        String nfactura="";
        String contadori="";
        String contadorf="";
        String nfacturanueva="";
        char vi;
        char vf;
        String SQL1 = "Select f.IdFactura From Factura f\n" +
                     "WHERE f.IdFactura = (SELECT MAX(f1.IdFactura) FROM Factura f1);";
        
        
         try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL1);

            while (rs.next()) {
                nfactura =rs.getString("f.IdFactura");
             
            }
        
     
            }catch(SQLException e){
                 System.out.println("Error "+e.getMessage());
        
            }
         
         boolean paseinicio=false;
         if(nfactura==null){
              nfactura = obtenerRangoInicial();
              paseinicio = true;
         }else{
             if(nfactura.equals("")){
                 nfactura = obtenerRangoInicial();
                 paseinicio = true;
             }
         }
         
         for(int i=0; i<11;i++){
                vi= nfactura.charAt(i);
                contadori = contadori+String.valueOf(vi);
         }
         
         for(int i=11; i<19;i++){
                vf= nfactura.charAt(i);
                contadorf = contadorf+String.valueOf(vf);
         }
         
         int nvalorfactura=0;
         if(paseinicio==false){
             nvalorfactura = Integer.parseInt(contadorf)+1;
         }
         
     

       
         
         if(nvalorfactura<=obtenerlimitefactura()){
             a=true;
         }
         
         return a;
        
        
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
        menu = new javax.swing.JPanel();
        MenuIcon = new javax.swing.JPanel();
        linesetting = new javax.swing.JPanel();
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
        jLabel3 = new javax.swing.JLabel();
        rSButtonIcon_new1 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new2 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new3 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new4 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new5 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new6 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new7 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new9 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new10 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new11 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new12 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new13 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new14 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new15 = new newscomponents.RSButtonIcon_new();
        linesetting6 = new javax.swing.JPanel();
        rSLabelIcon3 = new rojerusan.RSLabelIcon();
        rSLabelIcon4 = new rojerusan.RSLabelIcon();
        rSLabelIcon5 = new rojerusan.RSLabelIcon();
        dashboardview = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rSPanelOpacity1 = new RSMaterialComponent.RSPanelOpacity();
        rSLabelIcon6 = new rojerusan.RSLabelIcon();
        jLabel7 = new javax.swing.JLabel();
        rSLabelIcon8 = new rojerusan.RSLabelIcon();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        BOTONCLIENTE = new rojeru_san.rspanel.RSPanelRound();
        rSLabelIcon12 = new rojerusan.RSLabelIcon();
        F = new rojeru_san.rslabel.RSLabelLineWrap();
        BOTONEMPLEADO = new rojeru_san.rspanel.RSPanelRound();
        rSLabelIcon13 = new rojerusan.RSLabelIcon();
        F1 = new rojeru_san.rslabel.RSLabelLineWrap();
        BOTONPROVEEDORES = new rojeru_san.rspanel.RSPanelRound();
        rSLabelIcon15 = new rojerusan.RSLabelIcon();
        F3 = new rojeru_san.rslabel.RSLabelLineWrap();
        BOTONUSUARIO = new rojeru_san.rspanel.RSPanelRound();
        rSLabelIcon14 = new rojerusan.RSLabelIcon();
        F2 = new rojeru_san.rslabel.RSLabelLineWrap();
        BOTONBANCO = new rojeru_san.rspanel.RSPanelRound();
        rSLabelIcon16 = new rojerusan.RSLabelIcon();
        F4 = new rojeru_san.rslabel.RSLabelLineWrap();
        rSLabelAnimated1 = new rojeru_san.rslabel.RSLabelAnimated();
        BOTONENVIO = new rojeru_san.rspanel.RSPanelRound();
        rSLabelIcon17 = new rojerusan.RSLabelIcon();
        F5 = new rojeru_san.rslabel.RSLabelLineWrap();
        BOTONPRO = new rojeru_san.rspanel.RSPanelRound();
        rSLabelIcon18 = new rojerusan.RSLabelIcon();
        F6 = new rojeru_san.rslabel.RSLabelLineWrap();
        BOTONCAJA = new rojeru_san.rspanel.RSPanelRound();
        rSLabelIcon19 = new rojerusan.RSLabelIcon();
        F7 = new rojeru_san.rslabel.RSLabelLineWrap();
        BOTONV = new rojeru_san.rspanel.RSPanelRound();
        rSLabelIcon20 = new rojerusan.RSLabelIcon();
        F8 = new rojeru_san.rslabel.RSLabelLineWrap();
        BOTONAL = new rojeru_san.rspanel.RSPanelRound();
        rSLabelIcon21 = new rojerusan.RSLabelIcon();
        F9 = new rojeru_san.rslabel.RSLabelLineWrap();
        jPanel4 = new javax.swing.JPanel();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        jLabel6 = new javax.swing.JLabel();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setMinimumSize(new java.awt.Dimension(150, 50));
        Header.setPreferredSize(new java.awt.Dimension(800, 50));
        Header.setLayout(new java.awt.BorderLayout());

        iconminmaxclose.setBackground(new java.awt.Color(5, 10, 46));

        javax.swing.GroupLayout iconminmaxcloseLayout = new javax.swing.GroupLayout(iconminmaxclose);
        iconminmaxclose.setLayout(iconminmaxcloseLayout);
        iconminmaxcloseLayout.setHorizontalGroup(
            iconminmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        iconminmaxcloseLayout.setVerticalGroup(
            iconminmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Header.add(iconminmaxclose, java.awt.BorderLayout.LINE_END);

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
            .addGap(0, 739, Short.MAX_VALUE)
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
            .addGap(0, 335, Short.MAX_VALUE)
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

        Header.add(jPanel2, java.awt.BorderLayout.CENTER);

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
        });
        linesetting8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon9.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ENHANCED_ENCRYPTION);
        linesetting8.add(rSLabelIcon9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        linesetting9.setBackground(new java.awt.Color(0, 55, 133));
        linesetting9.setPreferredSize(new java.awt.Dimension(50, 10));
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
        linesetting11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon11.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon11.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SETTINGS);
        linesetting11.add(rSLabelIcon11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        javax.swing.GroupLayout MenuIconLayout = new javax.swing.GroupLayout(MenuIcon);
        MenuIcon.setLayout(MenuIconLayout);
        MenuIconLayout.setHorizontalGroup(
            MenuIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGap(50, 50, 50)
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

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CLIENTES");

        rSButtonIcon_new1.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new1.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SELECT_ALL);
        rSButtonIcon_new1.setLabel("Productos");
        rSButtonIcon_new1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new1ActionPerformed(evt);
            }
        });

        rSButtonIcon_new2.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new2.setText("Almacenes");
        rSButtonIcon_new2.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.STORE);
        rSButtonIcon_new2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new2ActionPerformed(evt);
            }
        });

        rSButtonIcon_new3.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new3.setText("Empleados");
        rSButtonIcon_new3.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rSButtonIcon_new3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WC);
        rSButtonIcon_new3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new3ActionPerformed(evt);
            }
        });

        rSButtonIcon_new4.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new4.setText("Clientes");
        rSButtonIcon_new4.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_BOX);
        rSButtonIcon_new4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new4ActionPerformed(evt);
            }
        });

        rSButtonIcon_new5.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new5.setText("Usuarios");
        rSButtonIcon_new5.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WIFI_TETHERING);
        rSButtonIcon_new5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new5ActionPerformed(evt);
            }
        });

        rSButtonIcon_new6.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new6.setText("Proveedores");
        rSButtonIcon_new6.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new6.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BUSINESS);
        rSButtonIcon_new6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new6ActionPerformed(evt);
            }
        });

        rSButtonIcon_new7.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new7.setText("Caja");
        rSButtonIcon_new7.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new7.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        rSButtonIcon_new7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new7ActionPerformed(evt);
            }
        });

        rSButtonIcon_new9.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new9.setText("Facturas");
        rSButtonIcon_new9.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.RECEIPT);
        rSButtonIcon_new9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new9ActionPerformed(evt);
            }
        });

        rSButtonIcon_new10.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new10.setText("Bancos");
        rSButtonIcon_new10.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new10.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_BALANCE);
        rSButtonIcon_new10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new10ActionPerformed(evt);
            }
        });

        rSButtonIcon_new11.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new11.setText("Logistica");
        rSButtonIcon_new11.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new11.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WIDGETS);
        rSButtonIcon_new11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new11ActionPerformed(evt);
            }
        });

        rSButtonIcon_new12.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new12.setText("Ventas");
        rSButtonIcon_new12.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.TRENDING_UP);
        rSButtonIcon_new12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new12ActionPerformed(evt);
            }
        });

        rSButtonIcon_new13.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new13.setText("POS");
        rSButtonIcon_new13.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new13.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.VIBRATION);
        rSButtonIcon_new13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new13ActionPerformed(evt);
            }
        });

        rSButtonIcon_new14.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new14.setText("Compras");
        rSButtonIcon_new14.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new14.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WEB_ASSET);
        rSButtonIcon_new14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new14ActionPerformed(evt);
            }
        });

        rSButtonIcon_new15.setBackground(new java.awt.Color(33, 150, 243));
        rSButtonIcon_new15.setText("Envios");
        rSButtonIcon_new15.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new15.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHECK_BOX);
        rSButtonIcon_new15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new15ActionPerformed(evt);
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
                .addComponent(rSLabelIcon5, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(rSLabelIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        linesetting6Layout.setVerticalGroup(
            linesetting6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(linesetting6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout menuhideLayout = new javax.swing.GroupLayout(menuhide);
        menuhide.setLayout(menuhideLayout);
        menuhideLayout.setHorizontalGroup(
            menuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuhideLayout.createSequentialGroup()
                .addGroup(menuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuhideLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSButtonIcon_new1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIcon_new3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIcon_new4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIcon_new5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIcon_new6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIcon_new7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linesetting6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuhideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rSButtonIcon_new9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonIcon_new10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonIcon_new11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonIcon_new12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonIcon_new13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonIcon_new14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSButtonIcon_new15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIcon_new2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        menuhideLayout.setVerticalGroup(
            menuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuhideLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel3)
                .addGap(11, 11, 11)
                .addComponent(rSButtonIcon_new1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonIcon_new9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonIcon_new15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButtonIcon_new2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(linesetting6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addComponent(MenuIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuhide, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addComponent(menuhide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dashboardview.setBackground(new java.awt.Color(232, 245, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelOpacity1.setBackground(new java.awt.Color(60, 76, 143));

        rSLabelIcon6.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon6.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.RATE_REVIEW);
        rSLabelIcon6.setName(""); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("/");

        rSLabelIcon8.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon8.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Panel de Acceso Rápido");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Menú Principal");

        rSPanelOpacity1.setLayer(rSLabelIcon6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(rSLabelIcon8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout rSPanelOpacity1Layout = new javax.swing.GroupLayout(rSPanelOpacity1);
        rSPanelOpacity1.setLayout(rSPanelOpacity1Layout);
        rSPanelOpacity1Layout.setHorizontalGroup(
            rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(rSLabelIcon8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(rSLabelIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addContainerGap(640, Short.MAX_VALUE))
        );
        rSPanelOpacity1Layout.setVerticalGroup(
            rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelIcon8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(2, Short.MAX_VALUE))
        );

        jPanel3.add(rSPanelOpacity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1032, 42));

        BOTONCLIENTE.setBackground(new java.awt.Color(0, 55, 133));
        BOTONCLIENTE.setColorBackground(new java.awt.Color(0, 55, 133));
        BOTONCLIENTE.setColorBorde(new java.awt.Color(255, 255, 255));
        BOTONCLIENTE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BOTONCLIENTEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BOTONCLIENTEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BOTONCLIENTEMouseExited(evt);
            }
        });

        rSLabelIcon12.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_BOX);

        F.setColorForeground(new java.awt.Color(255, 255, 255));
        F.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F.setInheritsPopupMenu(true);
        F.setName("GBGHY"); // NOI18N
        F.setText("Clientes");

        javax.swing.GroupLayout BOTONCLIENTELayout = new javax.swing.GroupLayout(BOTONCLIENTE);
        BOTONCLIENTE.setLayout(BOTONCLIENTELayout);
        BOTONCLIENTELayout.setHorizontalGroup(
            BOTONCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONCLIENTELayout.createSequentialGroup()
                .addGroup(BOTONCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BOTONCLIENTELayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(rSLabelIcon12, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BOTONCLIENTELayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        BOTONCLIENTELayout.setVerticalGroup(
            BOTONCLIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONCLIENTELayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(rSLabelIcon12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel3.add(BOTONCLIENTE, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 151, -1, -1));

        BOTONEMPLEADO.setBackground(new java.awt.Color(0, 55, 133));
        BOTONEMPLEADO.setColorBackground(new java.awt.Color(0, 55, 133));
        BOTONEMPLEADO.setColorBorde(new java.awt.Color(255, 255, 255));
        BOTONEMPLEADO.setPreferredSize(new java.awt.Dimension(170, 152));
        BOTONEMPLEADO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BOTONEMPLEADOMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BOTONEMPLEADOMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BOTONEMPLEADOMouseExited(evt);
            }
        });

        rSLabelIcon13.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon13.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WC);

        F1.setColorForeground(new java.awt.Color(255, 255, 255));
        F1.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F1.setInheritsPopupMenu(true);
        F1.setName("GBGHY"); // NOI18N
        F1.setText("Empleados");

        javax.swing.GroupLayout BOTONEMPLEADOLayout = new javax.swing.GroupLayout(BOTONEMPLEADO);
        BOTONEMPLEADO.setLayout(BOTONEMPLEADOLayout);
        BOTONEMPLEADOLayout.setHorizontalGroup(
            BOTONEMPLEADOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONEMPLEADOLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(rSLabelIcon13, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(BOTONEMPLEADOLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        BOTONEMPLEADOLayout.setVerticalGroup(
            BOTONEMPLEADOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONEMPLEADOLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel3.add(BOTONEMPLEADO, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 151, 173, -1));

        BOTONPROVEEDORES.setBackground(new java.awt.Color(0, 55, 133));
        BOTONPROVEEDORES.setColorBackground(new java.awt.Color(0, 55, 133));
        BOTONPROVEEDORES.setColorBorde(new java.awt.Color(255, 255, 255));
        BOTONPROVEEDORES.setPreferredSize(new java.awt.Dimension(170, 152));
        BOTONPROVEEDORES.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BOTONPROVEEDORESMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BOTONPROVEEDORESMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BOTONPROVEEDORESMouseExited(evt);
            }
        });

        rSLabelIcon15.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon15.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BUSINESS);

        F3.setColorForeground(new java.awt.Color(255, 255, 255));
        F3.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F3.setInheritsPopupMenu(true);
        F3.setName("GBGHY"); // NOI18N
        F3.setText("Proveedores");

        javax.swing.GroupLayout BOTONPROVEEDORESLayout = new javax.swing.GroupLayout(BOTONPROVEEDORES);
        BOTONPROVEEDORES.setLayout(BOTONPROVEEDORESLayout);
        BOTONPROVEEDORESLayout.setHorizontalGroup(
            BOTONPROVEEDORESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BOTONPROVEEDORESLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(BOTONPROVEEDORESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BOTONPROVEEDORESLayout.createSequentialGroup()
                        .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGap(18, 18, 18))
        );
        BOTONPROVEEDORESLayout.setVerticalGroup(
            BOTONPROVEEDORESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONPROVEEDORESLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(rSLabelIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel3.add(BOTONPROVEEDORES, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 151, 171, -1));

        BOTONUSUARIO.setBackground(new java.awt.Color(0, 55, 133));
        BOTONUSUARIO.setColorBackground(new java.awt.Color(0, 55, 133));
        BOTONUSUARIO.setColorBorde(new java.awt.Color(255, 255, 255));
        BOTONUSUARIO.setPreferredSize(new java.awt.Dimension(170, 152));
        BOTONUSUARIO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BOTONUSUARIOMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BOTONUSUARIOMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BOTONUSUARIOMouseExited(evt);
            }
        });

        rSLabelIcon14.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon14.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WIFI_TETHERING);

        F2.setColorForeground(new java.awt.Color(255, 255, 255));
        F2.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F2.setInheritsPopupMenu(true);
        F2.setName("GBGHY"); // NOI18N
        F2.setText("Usuarios");

        javax.swing.GroupLayout BOTONUSUARIOLayout = new javax.swing.GroupLayout(BOTONUSUARIO);
        BOTONUSUARIO.setLayout(BOTONUSUARIOLayout);
        BOTONUSUARIOLayout.setHorizontalGroup(
            BOTONUSUARIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONUSUARIOLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(BOTONUSUARIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BOTONUSUARIOLayout.createSequentialGroup()
                        .addComponent(rSLabelIcon14, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BOTONUSUARIOLayout.createSequentialGroup()
                        .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );
        BOTONUSUARIOLayout.setVerticalGroup(
            BOTONUSUARIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONUSUARIOLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(rSLabelIcon14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel3.add(BOTONUSUARIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 151, 171, -1));

        BOTONBANCO.setBackground(new java.awt.Color(0, 55, 133));
        BOTONBANCO.setColorBackground(new java.awt.Color(0, 55, 133));
        BOTONBANCO.setColorBorde(new java.awt.Color(255, 255, 255));
        BOTONBANCO.setPreferredSize(new java.awt.Dimension(170, 152));
        BOTONBANCO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BOTONBANCOMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BOTONBANCOMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BOTONBANCOMouseExited(evt);
            }
        });

        rSLabelIcon16.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon16.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_BALANCE);

        F4.setColorForeground(new java.awt.Color(255, 255, 255));
        F4.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F4.setInheritsPopupMenu(true);
        F4.setName("GBGHY"); // NOI18N
        F4.setText("Bancos");

        javax.swing.GroupLayout BOTONBANCOLayout = new javax.swing.GroupLayout(BOTONBANCO);
        BOTONBANCO.setLayout(BOTONBANCOLayout);
        BOTONBANCOLayout.setHorizontalGroup(
            BOTONBANCOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONBANCOLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(rSLabelIcon16, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BOTONBANCOLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        BOTONBANCOLayout.setVerticalGroup(
            BOTONBANCOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONBANCOLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(rSLabelIcon16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel3.add(BOTONBANCO, new org.netbeans.lib.awtextra.AbsoluteConstraints(789, 151, 172, -1));

        rSLabelAnimated1.setText("INFOTECNOLOGIA - GEVEC SYSTEM");
        jPanel3.add(rSLabelAnimated1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 536, 252, 40));

        BOTONENVIO.setBackground(new java.awt.Color(0, 55, 133));
        BOTONENVIO.setColorBackground(new java.awt.Color(0, 55, 133));
        BOTONENVIO.setColorBorde(new java.awt.Color(255, 255, 255));
        BOTONENVIO.setPreferredSize(new java.awt.Dimension(170, 152));
        BOTONENVIO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BOTONENVIOMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BOTONENVIOMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BOTONENVIOMouseExited(evt);
            }
        });

        rSLabelIcon17.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon17.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHECK_CIRCLE);

        F5.setColorForeground(new java.awt.Color(255, 255, 255));
        F5.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F5.setInheritsPopupMenu(true);
        F5.setName("GBGHY"); // NOI18N
        F5.setText("Envios");

        javax.swing.GroupLayout BOTONENVIOLayout = new javax.swing.GroupLayout(BOTONENVIO);
        BOTONENVIO.setLayout(BOTONENVIOLayout);
        BOTONENVIOLayout.setHorizontalGroup(
            BOTONENVIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONENVIOLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(rSLabelIcon17, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BOTONENVIOLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        BOTONENVIOLayout.setVerticalGroup(
            BOTONENVIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONENVIOLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(rSLabelIcon17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel3.add(BOTONENVIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 320, 172, -1));

        BOTONPRO.setBackground(new java.awt.Color(0, 55, 133));
        BOTONPRO.setColorBackground(new java.awt.Color(0, 55, 133));
        BOTONPRO.setColorBorde(new java.awt.Color(255, 255, 255));
        BOTONPRO.setPreferredSize(new java.awt.Dimension(170, 152));
        BOTONPRO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BOTONPROMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BOTONPROMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BOTONPROMouseExited(evt);
            }
        });

        rSLabelIcon18.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon18.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LOCAL_MALL);

        F6.setColorForeground(new java.awt.Color(255, 255, 255));
        F6.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F6.setInheritsPopupMenu(true);
        F6.setName("GBGHY"); // NOI18N
        F6.setText("Productos");

        javax.swing.GroupLayout BOTONPROLayout = new javax.swing.GroupLayout(BOTONPRO);
        BOTONPRO.setLayout(BOTONPROLayout);
        BOTONPROLayout.setHorizontalGroup(
            BOTONPROLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONPROLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(rSLabelIcon18, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BOTONPROLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        BOTONPROLayout.setVerticalGroup(
            BOTONPROLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONPROLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(rSLabelIcon18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel3.add(BOTONPRO, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 172, -1));

        BOTONCAJA.setBackground(new java.awt.Color(0, 55, 133));
        BOTONCAJA.setColorBackground(new java.awt.Color(0, 55, 133));
        BOTONCAJA.setColorBorde(new java.awt.Color(255, 255, 255));
        BOTONCAJA.setPreferredSize(new java.awt.Dimension(170, 152));
        BOTONCAJA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BOTONCAJAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BOTONCAJAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BOTONCAJAMouseExited(evt);
            }
        });

        rSLabelIcon19.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon19.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);

        F7.setColorForeground(new java.awt.Color(255, 255, 255));
        F7.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F7.setInheritsPopupMenu(true);
        F7.setName("GBGHY"); // NOI18N
        F7.setText("Caja");

        javax.swing.GroupLayout BOTONCAJALayout = new javax.swing.GroupLayout(BOTONCAJA);
        BOTONCAJA.setLayout(BOTONCAJALayout);
        BOTONCAJALayout.setHorizontalGroup(
            BOTONCAJALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONCAJALayout.createSequentialGroup()
                .addGroup(BOTONCAJALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BOTONCAJALayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(rSLabelIcon19, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BOTONCAJALayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(F7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        BOTONCAJALayout.setVerticalGroup(
            BOTONCAJALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONCAJALayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(rSLabelIcon19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel3.add(BOTONCAJA, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 172, -1));

        BOTONV.setBackground(new java.awt.Color(0, 55, 133));
        BOTONV.setColorBackground(new java.awt.Color(0, 55, 133));
        BOTONV.setColorBorde(new java.awt.Color(255, 255, 255));
        BOTONV.setPreferredSize(new java.awt.Dimension(170, 152));
        BOTONV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BOTONVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BOTONVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BOTONVMouseExited(evt);
            }
        });

        rSLabelIcon20.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon20.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LOCAL_OFFER);

        F8.setColorForeground(new java.awt.Color(255, 255, 255));
        F8.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F8.setInheritsPopupMenu(true);
        F8.setName("GBGHY"); // NOI18N
        F8.setText("Ventas");

        javax.swing.GroupLayout BOTONVLayout = new javax.swing.GroupLayout(BOTONV);
        BOTONV.setLayout(BOTONVLayout);
        BOTONVLayout.setHorizontalGroup(
            BOTONVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONVLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(rSLabelIcon20, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BOTONVLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        BOTONVLayout.setVerticalGroup(
            BOTONVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONVLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(rSLabelIcon20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel3.add(BOTONV, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, 172, -1));

        BOTONAL.setBackground(new java.awt.Color(0, 55, 133));
        BOTONAL.setColorBackground(new java.awt.Color(0, 55, 133));
        BOTONAL.setColorBorde(new java.awt.Color(255, 255, 255));
        BOTONAL.setPreferredSize(new java.awt.Dimension(170, 152));
        BOTONAL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BOTONALMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BOTONALMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BOTONALMouseExited(evt);
            }
        });

        rSLabelIcon21.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon21.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.STORE_MALL_DIRECTORY);
        rSLabelIcon21.setInheritsPopupMenu(true);

        F9.setColorForeground(new java.awt.Color(255, 255, 255));
        F9.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F9.setInheritsPopupMenu(true);
        F9.setName("GBGHY"); // NOI18N
        F9.setText("Almacén");

        javax.swing.GroupLayout BOTONALLayout = new javax.swing.GroupLayout(BOTONAL);
        BOTONAL.setLayout(BOTONALLayout);
        BOTONALLayout.setHorizontalGroup(
            BOTONALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONALLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(rSLabelIcon21, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BOTONALLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        BOTONALLayout.setVerticalGroup(
            BOTONALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BOTONALLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(rSLabelIcon21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel3.add(BOTONAL, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, 172, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("MENÚ PRINCIPAL");

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE_OUTLINE);

        rSLabelHora1.setForeground(new java.awt.Color(20, 101, 187));

        jLabel10.setBackground(new java.awt.Color(102, 51, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 0, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(rSLabelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSLabelIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(rSLabelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSLabelIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dashboardviewLayout = new javax.swing.GroupLayout(dashboardview);
        dashboardview.setLayout(dashboardviewLayout);
        dashboardviewLayout.setHorizontalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );
        dashboardviewLayout.setVerticalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(dashboardview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(dashboardview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void rSButtonIconOne3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne3ActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }//GEN-LAST:event_rSButtonIconOne3ActionPerformed

    private void rSButtonIconOne4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_rSButtonIconOne4ActionPerformed

    private void rSButtonIconOne5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne5ActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_rSButtonIconOne5ActionPerformed

    private void rSButtonIcon_new1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new1ActionPerformed
        if(validarRoles(rSButtonIcon_new1.getText())){
            Productos p = new Productos();
            p.setUsuario(usuario);
            p.setVisible(true);
            this.dispose(); 
        }else{
            VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
            vi.setVisible(true);
            
        }
       
    }//GEN-LAST:event_rSButtonIcon_new1ActionPerformed

    private void rSButtonIcon_new3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new3ActionPerformed
        // TODO add your handling code here:
        if(validarRoles(rSButtonIcon_new3.getText())){
        Empleados empleados = new Empleados();
        empleados.setUsuario(usuario);
        empleados.setVisible(true);
        this.dispose();
        }else{
            VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
            vi.setVisible(true); 
        }
    }//GEN-LAST:event_rSButtonIcon_new3ActionPerformed

    private void rSButtonIcon_new4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new4ActionPerformed
        // TODO add your handling code here:
         if(validarRoles(rSButtonIcon_new4.getText())){
        Cliente clientes = new Cliente();
        clientes.setUsuario(usuario);
        clientes.setVisible(true);
        this.dispose();
         }else{
            VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
            vi.setVisible(true); 
         }
    }//GEN-LAST:event_rSButtonIcon_new4ActionPerformed

    private void rSButtonIcon_new5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new5ActionPerformed
        // TODO add your handling code here:
         if(validarRoles(rSButtonIcon_new5.getText())){
        Usuario usuarios = new Usuario();
        usuarios.setUsuario(usuario);
        usuarios.setVisible(true);
        this.dispose();
         }else{
            VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
            vi.setVisible(true);  
         }
    }//GEN-LAST:event_rSButtonIcon_new5ActionPerformed

    private void rSButtonIcon_new6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new6ActionPerformed
        // TODO add your handling code here:
        if(validarRoles(rSButtonIcon_new6.getText())){
        Proveedores proveedores = new Proveedores();
        proveedores.setUsuario(usuario);
        proveedores.setVisible(true);
        this.dispose();
        }else{
             VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
            vi.setVisible(true);  
        }
        
    }//GEN-LAST:event_rSButtonIcon_new6ActionPerformed

    private void linesetting8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting8MouseClicked
        // TODO add your handling code here:
        
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_linesetting8MouseClicked

    private void BOTONCLIENTEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONCLIENTEMouseClicked
        // TODO add your handling code here:
        if(validarRoles(F.getText())){
        Cliente c = new Cliente();
        c.setUsuario(usuario);
        c.setVisible(true);
        this.dispose();
        }else{
                 VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
                 vi.setVisible(true);  
                }
    }//GEN-LAST:event_BOTONCLIENTEMouseClicked

    private void BOTONEMPLEADOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONEMPLEADOMouseClicked
        // TODO add your handling code here:
        if(validarRoles(F1.getText())){
         Empleados empleados = new Empleados();
         empleados.setUsuario(usuario);
         empleados.setVisible(true);
        this.setVisible(false);}
        else{
                 VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
                 vi.setVisible(true);  
                }
        
    }//GEN-LAST:event_BOTONEMPLEADOMouseClicked

    private void BOTONUSUARIOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONUSUARIOMouseClicked
        // TODO add your handling code here:
        if(validarRoles(F2.getText())){
        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setVisible(true);
        this.dispose();
        }else{
            VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
            vi.setVisible(true);
        }
    }//GEN-LAST:event_BOTONUSUARIOMouseClicked

    private void BOTONPROVEEDORESMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONPROVEEDORESMouseClicked
        // TODO add your handling code here:
        if(validarRoles(F3.getText())){
        
        Proveedores p = new Proveedores();
        p.setUsuario(usuario);
        p.setVisible(true);
        this.dispose();
        }else{
            VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
            vi.setVisible(true);
        }
    }//GEN-LAST:event_BOTONPROVEEDORESMouseClicked

    private void BOTONCLIENTEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONCLIENTEMouseEntered
        // TODO add your handling code here:
     
    }//GEN-LAST:event_BOTONCLIENTEMouseEntered

    private void BOTONCLIENTEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONCLIENTEMouseExited
        // TODO add your handling code here:
      
        
    }//GEN-LAST:event_BOTONCLIENTEMouseExited

    private void BOTONEMPLEADOMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONEMPLEADOMouseEntered
        // TODO add your handling code here:
      
    }//GEN-LAST:event_BOTONEMPLEADOMouseEntered

    private void BOTONEMPLEADOMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONEMPLEADOMouseExited
        // TODO add your handling code here:
      
    }//GEN-LAST:event_BOTONEMPLEADOMouseExited

    private void BOTONUSUARIOMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONUSUARIOMouseEntered
        // TODO add your handling code here:
      
    }//GEN-LAST:event_BOTONUSUARIOMouseEntered

    private void BOTONUSUARIOMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONUSUARIOMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_BOTONUSUARIOMouseExited

    private void BOTONPROVEEDORESMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONPROVEEDORESMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_BOTONPROVEEDORESMouseEntered

    private void BOTONPROVEEDORESMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONPROVEEDORESMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_BOTONPROVEEDORESMouseExited

    private void BOTONBANCOMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONBANCOMouseEntered
        // TODO add your handling code here:
      
    }//GEN-LAST:event_BOTONBANCOMouseEntered

    private void BOTONBANCOMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONBANCOMouseExited
        // TODO add your handling code here:
    
    }//GEN-LAST:event_BOTONBANCOMouseExited

    private void rSButtonIcon_new7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new7ActionPerformed
        // TODO add your handling code here:
        if(validarRoles(rSButtonIcon_new7.getText())==true){
            Caja caja = new Caja();
            caja.setUsuario(usuario);
            caja.setVisible(true);
            this.dispose();
        }else{
            VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
            vi.setEstado("N");
            vi.setVisible(true);
            
        }
        
    }//GEN-LAST:event_rSButtonIcon_new7ActionPerformed

    private void BOTONENVIOMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONENVIOMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BOTONENVIOMouseEntered

    private void BOTONENVIOMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONENVIOMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BOTONENVIOMouseExited

    private void BOTONENVIOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONENVIOMouseClicked
        if(validarRoles(F5.getText())){
        Envio e = new Envio();
            e.setUsuario(usuario);
             e.setVisible(true);
             this.dispose();
        }else{
            VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
            vi.setVisible(true);
        }
    }//GEN-LAST:event_BOTONENVIOMouseClicked

    private void rSButtonIcon_new15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new15ActionPerformed
             if(validarRoles(rSButtonIcon_new15.getText())){
            Envio e = new Envio();
            e.setUsuario(usuario);
            e.setVisible(true);
            this.dispose();
             }else{
                VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
                vi.setVisible(true);
             }
    }//GEN-LAST:event_rSButtonIcon_new15ActionPerformed

    private void BOTONBANCOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONBANCOMouseClicked
        // TODO add your handling code here:
        if(validarRoles(F4.getText())){
        Banco b = new Banco();
        b.setVisible(a);
        this.dispose();
        }else{
             VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
             vi.setVisible(true);
        }
    }//GEN-LAST:event_BOTONBANCOMouseClicked

    private void rSButtonIcon_new10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new10ActionPerformed
        // TODO add your handling code here:
         if(validarRoles(rSButtonIcon_new10.getText())){
        Banco b = new Banco();
        b.setVisible(a);
        b.setUsuario(usuario);
        this.dispose();}
         else{
               VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
             vi.setVisible(true); 
         }
    }//GEN-LAST:event_rSButtonIcon_new10ActionPerformed

    private void BOTONPROMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONPROMouseClicked
            if(validarRoles(F6.getText())){
        Productos p = new Productos();
       p.setUsuario(usuario);
        p.setVisible(true);
        this.dispose();}
        else{
                VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
                vi.setVisible(true); 
                }
    }//GEN-LAST:event_BOTONPROMouseClicked

    private void BOTONPROMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONPROMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BOTONPROMouseEntered

    private void BOTONPROMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONPROMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BOTONPROMouseExited

    private void BOTONCAJAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONCAJAMouseClicked
        if(validarRoles(F7.getText())){
            Caja p = new Caja();
            p.setUsuario(usuario);
            p.setVisible(true);
            this.dispose();
        }else{
            VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
            vi.setEstado("N");
            vi.setVisible(true);
        }
        
    }//GEN-LAST:event_BOTONCAJAMouseClicked

    private void BOTONCAJAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONCAJAMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BOTONCAJAMouseEntered

    private void BOTONCAJAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONCAJAMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BOTONCAJAMouseExited

    private void rSButtonIcon_new13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new13ActionPerformed
        // TODO add your handling code here:
        
   
            if(validarRolPOS()==true){
            if(validarcaja()==true){
                if(validarfactura()==true){
                    GenerarVenta gv = new GenerarVenta();
                    gv.setUsuario(usuario);
                    gv.setMenu(this);
                    gv.setVisible(true);
                }else{
                    VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
                    vi.setEstado("FC");
                    vi.setVisible(true);
                }
                
            }else{
                VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
                vi.setEstado("CC");
                vi.setVisible(true);
            }
            
            
        }else{
            VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
            vi.setEstado("N");
            vi.setVisible(true);
        }
            
       
        
       
        
    }//GEN-LAST:event_rSButtonIcon_new13ActionPerformed

    private void BOTONVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONVMouseClicked
        if(validarRoles(F8.getText())==true){
        Ventas v = new Ventas();
        v.setVisible(true);
        v.setUsuario(usuario);
        this.dispose();
        }else{
                VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
                vi.setVisible(true); 
                }
    }//GEN-LAST:event_BOTONVMouseClicked

    private void BOTONVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONVMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BOTONVMouseEntered

    private void BOTONVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONVMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BOTONVMouseExited

    private void BOTONALMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONALMouseClicked
        if(validarRoles(F9.getText())==true){
        Almacen al = new Almacen();
        al.setUsuario(usuario);
        al.setVisible(true);
        this.dispose();}else{
              VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
              vi.setVisible(true); 
        }
    }//GEN-LAST:event_BOTONALMouseClicked

    private void BOTONALMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONALMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BOTONALMouseEntered

    private void BOTONALMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOTONALMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BOTONALMouseExited

    private void rSButtonIcon_new12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new12ActionPerformed
        if(validarRoles(rSButtonIcon_new12.getText())==true){
        Ventas v = new Ventas();
        v.setUsuario(usuario);
        v.setVisible(true);
        this.dispose();}
        else{
             VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
              vi.setVisible(true); 
        }
    }//GEN-LAST:event_rSButtonIcon_new12ActionPerformed

    private void rSButtonIcon_new2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new2ActionPerformed
        if(validarRoles(rSButtonIcon_new2.getText())==true){
        Almacen al = new Almacen();
        al.setUsuario(usuario);
        al.setVisible(true);
        this.dispose();}else{
             VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
             vi.setVisible(true); 
        }
    }//GEN-LAST:event_rSButtonIcon_new2ActionPerformed

    private void rSButtonIcon_new11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new11ActionPerformed
       if(validarRoles(rSButtonIcon_new11.getText())==true){
       Logistica lg = new Logistica();
       lg.setUsuario(usuario);
       lg.setVisible(true);
       this.dispose();}else{
           VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
           vi.setVisible(true);
       }
    }//GEN-LAST:event_rSButtonIcon_new11ActionPerformed

    private void rSButtonIcon_new9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new9ActionPerformed
        // TODO add your handling code here:
        if(validarRoles(rSButtonIcon_new9.getText())==true){
        Facturas f = new Facturas();
        f.setUsuario(usuario);
        f.setVisible(true);
        this.dispose();
        }else{
           VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
           vi.setVisible(true);
        }
    }//GEN-LAST:event_rSButtonIcon_new9ActionPerformed

    private void rSButtonIcon_new14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new14ActionPerformed
     //  if(validarconexion()==true){
             if(validarRoles(rSButtonIcon_new14.getText())==true){
                    GenerarCompra gc= new GenerarCompra();
                    gc.setUsuario(usuario);
                    gc.setMenu(this);
                    gc.setVisible(true);
                }else{
                   VentanaInformativaPOSMENU vi = new VentanaInformativaPOSMENU();
                   vi.setVisible(true);
               }
        
        
       
        

        
    }//GEN-LAST:event_rSButtonIcon_new14ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.rspanel.RSPanelRound BOTONAL;
    private rojeru_san.rspanel.RSPanelRound BOTONBANCO;
    private rojeru_san.rspanel.RSPanelRound BOTONCAJA;
    private rojeru_san.rspanel.RSPanelRound BOTONCLIENTE;
    private rojeru_san.rspanel.RSPanelRound BOTONEMPLEADO;
    private rojeru_san.rspanel.RSPanelRound BOTONENVIO;
    private rojeru_san.rspanel.RSPanelRound BOTONPRO;
    private rojeru_san.rspanel.RSPanelRound BOTONPROVEEDORES;
    private rojeru_san.rspanel.RSPanelRound BOTONUSUARIO;
    private rojeru_san.rspanel.RSPanelRound BOTONV;
    private rojeru_san.rslabel.RSLabelLineWrap F;
    private rojeru_san.rslabel.RSLabelLineWrap F1;
    private rojeru_san.rslabel.RSLabelLineWrap F2;
    private rojeru_san.rslabel.RSLabelLineWrap F3;
    private rojeru_san.rslabel.RSLabelLineWrap F4;
    private rojeru_san.rslabel.RSLabelLineWrap F5;
    private rojeru_san.rslabel.RSLabelLineWrap F6;
    private rojeru_san.rslabel.RSLabelLineWrap F7;
    private rojeru_san.rslabel.RSLabelLineWrap F8;
    private rojeru_san.rslabel.RSLabelLineWrap F9;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel MenuIcon;
    private javax.swing.JPanel dashboardview;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel linesetting;
    private javax.swing.JPanel linesetting1;
    private javax.swing.JPanel linesetting10;
    private javax.swing.JPanel linesetting11;
    private javax.swing.JPanel linesetting2;
    private javax.swing.JPanel linesetting3;
    private javax.swing.JPanel linesetting4;
    private javax.swing.JPanel linesetting5;
    private javax.swing.JPanel linesetting6;
    private javax.swing.JPanel linesetting7;
    private javax.swing.JPanel linesetting8;
    private javax.swing.JPanel linesetting9;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel menuhide;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne3;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new1;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new10;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new11;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new12;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new13;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new14;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new15;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new2;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new3;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new4;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new6;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new7;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new9;
    private rojeru_san.rslabel.RSLabelAnimated rSLabelAnimated1;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rojerusan.RSLabelIcon rSLabelIcon1;
    private rojerusan.RSLabelIcon rSLabelIcon10;
    private rojerusan.RSLabelIcon rSLabelIcon11;
    private rojerusan.RSLabelIcon rSLabelIcon12;
    private rojerusan.RSLabelIcon rSLabelIcon13;
    private rojerusan.RSLabelIcon rSLabelIcon14;
    private rojerusan.RSLabelIcon rSLabelIcon15;
    private rojerusan.RSLabelIcon rSLabelIcon16;
    private rojerusan.RSLabelIcon rSLabelIcon17;
    private rojerusan.RSLabelIcon rSLabelIcon18;
    private rojerusan.RSLabelIcon rSLabelIcon19;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private rojerusan.RSLabelIcon rSLabelIcon20;
    private rojerusan.RSLabelIcon rSLabelIcon21;
    private rojerusan.RSLabelIcon rSLabelIcon3;
    private rojerusan.RSLabelIcon rSLabelIcon4;
    private rojerusan.RSLabelIcon rSLabelIcon5;
    private rojerusan.RSLabelIcon rSLabelIcon6;
    private rojerusan.RSLabelIcon rSLabelIcon7;
    private rojerusan.RSLabelIcon rSLabelIcon8;
    private rojerusan.RSLabelIcon rSLabelIcon9;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity1;
    // End of variables declaration//GEN-END:variables
}
