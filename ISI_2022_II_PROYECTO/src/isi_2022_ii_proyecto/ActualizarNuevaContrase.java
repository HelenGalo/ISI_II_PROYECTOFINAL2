/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package isi_2022_ii_proyecto;


import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.Conteo;
import isi_2022_ii_proyecto.Recursos.VentanaEmergente1;
import isi_2022_ii_proyecto.Recursos.VentanaEmergente2;
import java.awt.Color;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import rojeru_san.complementos.RSUtilities;

/**
 *
 * @author Edwin Rafael
 */
public class ActualizarNuevaContrase extends javax.swing.JFrame {
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();
    
    Hashtable<String, String> cifrador = new Hashtable<String, String>();

    
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
        buscarusuario(idusuario);
     
    }
    
    int idusuario;

    
 

    /**
     * Creates new form Inicio
     */
   
    public ActualizarNuevaContrase() {
        RSUtilities.setFullScreenJFrame(this);
        initComponents();
        RSUtilities.setOpaqueWindow(this, false);
        RSUtilities.setOpacityComponent(this.jPanel1, 150);
        inicializarValoresC();
        
        setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
        
    }
    public void conectar(){
        conexion.setAnc(this);
        con = conexion.conexion();
    }
    
    public void inicializarValoresC(){
        cifrador.put("A", "E3:$%^");
        cifrador.put("B", "Aq2=!#");
        cifrador.put("C", "Zzp1@*");
        cifrador.put("D", "lkh4$5");
        cifrador.put("E", "^7192H");
        cifrador.put("F", "%=zXQ+");
        cifrador.put("G", "00FvT-");
        cifrador.put("H", "&Cas45");
        cifrador.put("I", "P4fdW3");
        cifrador.put("J", "bgCo3l");
        cifrador.put("K", "Someb3");
        cifrador.put("L", "gDmen+");
        cifrador.put("M", "Very03");
        cifrador.put("N", "02ne#$");
        cifrador.put("Ñ", "88df4#");
        cifrador.put("O", "F#0002");
        cifrador.put("P", "R%StUP");
        cifrador.put("Q", "IDa7-*");
        cifrador.put("R", "*-*.O.");
        cifrador.put("S", "^0^663");
        cifrador.put("T", "W@a&&e");
        cifrador.put("U", "KOK#%)");
        cifrador.put("V", "(-+)*$");
        cifrador.put("W", "%hjkl1");
        cifrador.put("X", "58l(%%");
        cifrador.put("Y", "=34Sap");
        cifrador.put("Z", "SaP012");
        
        //Cifrador de minusculas
        cifrador.put("a", "dFGH46");
        cifrador.put("b", "678Mza");
        cifrador.put("c", "AS23Df");
        cifrador.put("d", "ZXSR#4");
        cifrador.put("e", "POPU27");
        cifrador.put("f", "Iik&!4");
        cifrador.put("g", "!-//+!");
        cifrador.put("h", "F%^Nñd");
        cifrador.put("i", "la34F4");
        cifrador.put("j", "WcGh34");
        cifrador.put("k", "-*=Mi");
        cifrador.put("l", "ndSt92");
        cifrador.put("m", "Kl2$!=");
        cifrador.put("n", "*1V4hl");
        cifrador.put("ñ", "Ru/e40");
        cifrador.put("o", "00Fx#2");
        cifrador.put("p", "1ce5G/");
        cifrador.put("q", "*fht@)");
        cifrador.put("r", "(45gR*");
        cifrador.put("s", "!#$Tre");
        cifrador.put("t", "^g%#^!");
        cifrador.put("u", "M4%@Xx");
        cifrador.put("v", "X-XO.0");
        cifrador.put("w", "aQjf$%");
        cifrador.put("x", "H3hl8*");
        cifrador.put("y", "_-_93r");
        cifrador.put("z", "7*7*+/");
        
        //cifrador de numeros
        cifrador.put("0", "gGL4+/");
        cifrador.put("1", "0UvUZ4");
        cifrador.put("2", "5-5fJ%");
        cifrador.put("3", "/1de89");
        cifrador.put("4", "(89)bV");
        cifrador.put("5", "LoLP40");
        cifrador.put("6", "Mkl3^5");
        cifrador.put("7", "$%$Kam");
        cifrador.put("8", "BDuDu2");
        cifrador.put("9", "K11LTL");
        
        //crifrador de simbolos
        cifrador.put("$", "Le%(tm");
        cifrador.put("#", "H0WYou");
        cifrador.put("@", "L1K3Th");
        cifrador.put("%", "aT--+^");
        cifrador.put("/", "Inf@ou");
        cifrador.put("&", "JaSJs*");
        cifrador.put("*", "$$##/*/");
        cifrador.put("-", "D&oNTA");
        cifrador.put("+", "NGE/*/");
        cifrador.put("!", "+----*");
        cifrador.put("^", "!0!$$$");
        cifrador.put("(", "BnM3%*");
        cifrador.put(")", "LQ56^s");
        cifrador.put("_", "HJond#");
        cifrador.put("=", "Za3Dpe");
        cifrador.put(".", "Ho(+)D");
    }
    
  
    
    
    
 
    
    public void buscarusuario(int id){
        String usuario="";
        String SQL = "SELECT u.Usuario FROM Usuarios u Where u.IdUsuario="+id;
           
         
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                usuario =rs.getString("u.Usuario");
              
            }
    }catch(Exception e){
        
    }
        
        JUser.setText(usuario);
    }
    
    
           
    
    
     
    
    private boolean validar(){
       
        char [] arrayC=Jpassword1.getPassword();
        String acceso= new String(arrayC);
        
        char [] arrayC1=Jpassword.getPassword();
        String acceso1= new String(arrayC1);
        
        if(acceso.isEmpty() ||acceso.isEmpty()  ){
            JOptionPane.showMessageDialog(rootPane, "Los valores estan vacios");
            return false;
        }else{
            return true;
        }
    }
    
    private String encriptar(String c){
        String contra = c;
        char puntero;
        String contraC = "";
        //Cifrador de mayusculas//
        
       
        for(int i=0;i<8;i++){
            puntero = contra.charAt(i);
            contraC = contraC + cifrador.get(String.valueOf(puntero));
            
        
            
        }
        
      return contraC;
        
    }
    
    
    private void actualizarcontrasenueva(){
        char [] arrayC=Jpassword1.getPassword();
        String acceso= new String(arrayC);
        
        char [] arrayC1=Jpassword.getPassword();
        String acceso1= new String(arrayC1);
        int intentos= 3;
        
        if(acceso.equals(acceso1)){
            String SQL1 = "UPDATE Usuarios u SET u.Intentos=?, u.Contrase=? WHERE u.Usuario="+"'"+JUser.getText()+"';";
  
            try {
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL1);
                preparedStmt.setInt (1, intentos);
                preparedStmt.setString (2, encriptar(acceso1));
                preparedStmt.execute();
                
                VentanaEmergente2 v = new VentanaEmergente2();
                v.setAnc(this);
                v.setVisible(true);


            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        
    
        
       
        
    }
    
   
    
    public void  changecolor(JPanel hover, Color rand){
     hover.setBackground(rand);
    }
    
    public void cerrarc(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarNuevaContrase.class.getName()).log(Level.SEVERE, null, ex);
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
        panelRound1 = new isi_2022_ii_proyecto.Recursos.PanelRound();
        panelRound2 = new isi_2022_ii_proyecto.Recursos.PanelRound();
        panelRound3 = new isi_2022_ii_proyecto.Recursos.PanelRound();
        panelRound4 = new isi_2022_ii_proyecto.Recursos.PanelRound();
        panelRound5 = new isi_2022_ii_proyecto.Recursos.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        F1 = new rojeru_san.rslabel.RSLabelLineWrap();
        JUser = new javax.swing.JLabel();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        Jpassword = new rojeru_san.RSMPassView();
        rSLabelFecha2 = new rojeru_san.RSLabelFecha();
        F = new rojeru_san.rslabel.RSLabelLineWrap();
        fullmax3 = new javax.swing.JLabel();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        rSLabelIcon3 = new rojerusan.RSLabelIcon();
        rSButtonRound1 = new rojerusan.RSButtonRound();
        rSLabelIcon4 = new rojerusan.RSLabelIcon();
        jLabel7 = new javax.swing.JLabel();
        Jpassword1 = new rojeru_san.RSMPassView();
        rSLabelIcon5 = new rojerusan.RSLabelIcon();
        Header = new javax.swing.JPanel();
        iconminmaxclose = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        linesetting4 = new javax.swing.JPanel();
        linesetting5 = new javax.swing.JPanel();
        linesetting3 = new javax.swing.JPanel();
        rSButtonIconOne5 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne4 = new RSMaterialComponent.RSButtonIconOne();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(252, 235, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1444, 834));

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(110);
        panelRound1.setRoundBottomRight(110);
        panelRound1.setRoundTopLeft(110);
        panelRound1.setRoundTopRight(110);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.setBackground(new java.awt.Color(19, 99, 223));
        panelRound2.setRoundBottomRight(110);
        panelRound2.setRoundTopRight(110);
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setRoundBottomLeft(100);
        panelRound3.setRoundBottomRight(100);
        panelRound3.setRoundTopLeft(100);
        panelRound3.setRoundTopRight(100);

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        panelRound2.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 20, 20));

        panelRound4.setBackground(new java.awt.Color(255, 255, 255));
        panelRound4.setRoundBottomLeft(100);
        panelRound4.setRoundBottomRight(100);
        panelRound4.setRoundTopLeft(100);
        panelRound4.setRoundTopRight(100);

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        panelRound2.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 20, 20));

        panelRound5.setBackground(new java.awt.Color(255, 255, 255));
        panelRound5.setRoundBottomLeft(100);
        panelRound5.setRoundBottomRight(100);
        panelRound5.setRoundTopLeft(100);
        panelRound5.setRoundTopRight(100);

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        panelRound2.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 20, 20));

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 60)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SYSTEM");
        panelRound2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Book", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("GEVEC");
        panelRound2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        F1.setForeground(new java.awt.Color(255, 255, 255));
        F1.setColorForeground(new java.awt.Color(255, 255, 255));
        F1.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F1.setInheritsPopupMenu(true);
        F1.setName("GBGHY"); // NOI18N
        F1.setText("FAITH HOPE COMPANY © 2022");
        panelRound2.add(F1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 281, 29));

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 410, 460));

        JUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JUser.setForeground(new java.awt.Color(19, 99, 223));
        JUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JUser.setText("Usuario");
        panelRound1.add(JUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 250, 30));
        panelRound1.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 110, -1));

        Jpassword.setPlaceholder("Confirmar Contraseña");
        panelRound1.add(Jpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, -1, -1));
        panelRound1.add(rSLabelFecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 110, 20));

        F.setColorForeground(new java.awt.Color(19, 99, 223));
        F.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F.setInheritsPopupMenu(true);
        F.setName("GBGHY"); // NOI18N
        F.setText("N U E V A  C O N T R A S E Ñ A");
        panelRound1.add(F, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 270, 20));

        fullmax3.setBackground(new java.awt.Color(5, 10, 46));
        fullmax3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fullmax3.setToolTipText("");
        fullmax3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fullmax3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fullmax3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fullmax3MouseExited(evt);
            }
        });
        panelRound1.add(fullmax3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.POLYMER);
        rSLabelIcon1.setInheritsPopupMenu(true);
        panelRound1.add(rSLabelIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 70, 70));

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ASSIGNMENT_IND);
        panelRound1.add(rSLabelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 30, 30));

        rSLabelIcon3.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEND);
        panelRound1.add(rSLabelIcon3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 30, 20));

        rSButtonRound1.setBackground(new java.awt.Color(19, 99, 223));
        rSButtonRound1.setText("Cambiar Contraseña");
        rSButtonRound1.setColorHover(new java.awt.Color(255, 255, 255));
        rSButtonRound1.setColorTextHover(new java.awt.Color(19, 99, 223));
        rSButtonRound1.setFocusable(false);
        rSButtonRound1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rSButtonRound1MouseEntered(evt);
            }
        });
        rSButtonRound1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRound1ActionPerformed(evt);
            }
        });
        panelRound1.add(rSButtonRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 250, -1));

        rSLabelIcon4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SCREEN_LOCK_PORTRAIT);
        panelRound1.add(rSLabelIcon4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 30, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(19, 99, 223));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Ingrese una nueva contraseña, de 8 caracteres en total.");
        panelRound1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 390, 30));
        panelRound1.add(Jpassword1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, -1, -1));

        rSLabelIcon5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SCREEN_LOCK_ROTATION);
        panelRound1.add(rSLabelIcon5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 30, 30));

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

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("SYSTEM");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("GEVEC");

        linesetting4.setBackground(new java.awt.Color(20, 101, 187));
        linesetting4.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesetting4Layout = new javax.swing.GroupLayout(linesetting4);
        linesetting4.setLayout(linesetting4Layout);
        linesetting4Layout.setHorizontalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 793, Short.MAX_VALUE)
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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        linesetting5Layout.setVerticalGroup(
            linesetting5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        linesetting3.setBackground(new java.awt.Color(20, 101, 187));
        linesetting3.setPreferredSize(new java.awt.Dimension(50, 10));

        rSButtonIconOne5.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIconOne5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REMOVE);
        rSButtonIconOne5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne5ActionPerformed(evt);
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
                .addGap(39, 39, 39)
                .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        linesetting3Layout.setVerticalGroup(
            linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(jLabel8))
                    .addComponent(jLabel5))
                .addGap(13, 13, 13)
                .addComponent(linesetting5, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linesetting4, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(linesetting3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonIconOne4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne4ActionPerformed
        try {
            // TODO add your handling code here:
            con.close();
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarNuevaContrase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rSButtonIconOne4ActionPerformed

    private void rSButtonIconOne5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIconOne5ActionPerformed

    private void rSButtonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRound1ActionPerformed
        // TODO add your handling code here:
        
        if(validar()==true){
            actualizarcontrasenueva();
        }else{
            
        }
      

        
        
    }//GEN-LAST:event_rSButtonRound1ActionPerformed

    private void rSButtonRound1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonRound1MouseEntered
        // TODO add your handling code here:
        rSLabelIcon3.setForeground(Color.blue);
    }//GEN-LAST:event_rSButtonRound1MouseEntered

    private void fullmax3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullmax3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_fullmax3MouseExited

    private void fullmax3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullmax3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fullmax3MouseEntered

    private void fullmax3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullmax3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fullmax3MouseClicked

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
            java.util.logging.Logger.getLogger(ActualizarNuevaContrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarNuevaContrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarNuevaContrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarNuevaContrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActualizarNuevaContrase().setVisible(true);
               
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.rslabel.RSLabelLineWrap F;
    private rojeru_san.rslabel.RSLabelLineWrap F1;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel JUser;
    private rojeru_san.RSMPassView Jpassword;
    private rojeru_san.RSMPassView Jpassword1;
    private javax.swing.JLabel fullmax3;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel linesetting3;
    private javax.swing.JPanel linesetting4;
    private javax.swing.JPanel linesetting5;
    private isi_2022_ii_proyecto.Recursos.PanelRound panelRound1;
    private isi_2022_ii_proyecto.Recursos.PanelRound panelRound2;
    private isi_2022_ii_proyecto.Recursos.PanelRound panelRound3;
    private isi_2022_ii_proyecto.Recursos.PanelRound panelRound4;
    private isi_2022_ii_proyecto.Recursos.PanelRound panelRound5;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne5;
    private rojerusan.RSButtonRound rSButtonRound1;
    private rojeru_san.RSLabelFecha rSLabelFecha2;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rojerusan.RSLabelIcon rSLabelIcon1;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private rojerusan.RSLabelIcon rSLabelIcon3;
    private rojerusan.RSLabelIcon rSLabelIcon4;
    private rojerusan.RSLabelIcon rSLabelIcon5;
    // End of variables declaration//GEN-END:variables
}

