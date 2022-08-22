/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package isi_2022_ii_proyecto;


import Atxy2k.CustomTextField.RestrictedTextField;
import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.Conteo;
import isi_2022_ii_proyecto.Recursos.ErrorToken;
import isi_2022_ii_proyecto.Recursos.LoginVentana;
import isi_2022_ii_proyecto.Recursos.MInformacionCorreo;
import java.awt.Color;
import java.awt.Point;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import rojeru_san.complementos.RSUtilities;

/**
 *
 * @author Edwin Rafael
 */
public class CambiarContrase extends javax.swing.JFrame {
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();
    String usuario;
    String acceso;
    String vacceso="";
    Hashtable<String, String> cifrador = new Hashtable<String, String>();
    String contradC="";
    String corredor1="";
    String des = "";
    String codigoacceso="";
    String codigoplano="";
    String correo="";
    
    String tipoerror="";
    
    
    boolean a=false;
    boolean b=false;

    
 

    /**
     * Creates new form Inicio
     */
   
    public CambiarContrase() {
        initComponents(); 
        this.setLocationRelativeTo(null);
        RSUtilities.setCenterWindow(this);
        inicializarValoresC();
        ocultarelementos();
        RestrictedTextField r = new RestrictedTextField(rSTextFullRound2);
        RestrictedTextField r1 = new RestrictedTextField(rSTextFullRound3);
        RestrictedTextField r2 = new RestrictedTextField(rSTextFullRound4);
        RestrictedTextField r3 = new RestrictedTextField(rSTextFullRound5);
        RestrictedTextField r4 = new RestrictedTextField(rSTextFullRound6);
        RestrictedTextField r5 = new RestrictedTextField(rSTextFullRound7);
        
        
        r.setLimit(1);
        r1.setLimit(1);
        r2.setLimit(1);
        r3.setLimit(1);
        r4.setLimit(1);
        r5.setLimit(1);
        
        setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
   
        
        
        
        
    }
    
    public void conectar(){
        conexion.setCambiarcontra(this);
        con = conexion.conexion();
    }
    
    public void cerrarc(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarNuevaContrase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void actualizarcontra(){
       LoginVentana lv = new LoginVentana();
       lv.setCc(this);
       lv.setIdusuario(obtenerID());
       lv.setVisible(true);
       lv.iniciarconteo();
       cerrarc();
    }
    
    
      
    public void buscar(Hashtable ri){
        
        Enumeration llaves = ri.keys();
         while (llaves.hasMoreElements()) {
                Object key = llaves.nextElement();
                Object elemnt = cifrador.get(key);
                if(cifrador.get(key).equals(corredor1)){
                    contradC = contradC + key.toString();
                  
                
                }

            }
    }
    
    private String desencriptar(String C){
        contradC="";
        String contraC = C;
        char puntero;
        
        
        int cont=0;
        int cont1=0;
       
        for (int i=0; i<6; i++){
            corredor1="";
            for( int j=cont1; j<6+cont; j++){
                puntero = contraC.charAt(j);
                corredor1 = corredor1 + String.valueOf(puntero);
               
          
            }
       
            
            buscar(cifrador);
            cont = cont + 6;
            cont1=cont1+6;
            
                
            }
 
         return contradC;
            
           
      
        }
    
    
 
    
    public void errortoken(){
        ErrorToken et = new ErrorToken();
        et.setTipo(tipoerror);
        et.setVisible(true);
    }
    
    
    
    public int obtenerID(){
        
        int idEmpleado=0;
        String SQL = "SELECT u.IdEmpleado FROM Usuarios u\n" +
        "INNER JOIN Empleados e ON e.IdEmpleado = u.IdEmpleado WHERE e.CorreoElectronico='"+rSTextFullRound1.getText()+"';";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idEmpleado =rs.getInt("u.IdEmpleado");
              
            }
    }catch(Exception e){
        
    }
        System.out.println("el id es: "+idEmpleado);
        System.out.println("el sql es: "+SQL);
        return idEmpleado;
    }
    
    public void validartoken(){
        String tokenbd="";
        String token="";
        token=rSTextFullRound2.getText()+rSTextFullRound3.getText()+rSTextFullRound4.getText()+rSTextFullRound5.getText()+rSTextFullRound6.getText()+rSTextFullRound7.getText();
        String SQL = "Select ut.idUsuarioTokens, ut.Codigo from UsuarioTokens ut\n" +
        "Where ut.idUsuarioTokens=(Select max(idUsuarioTokens) from UsuarioTokens Where IdUsuario=(Select u.IdUsuario from Usuarios u \n" +
        "INNER JOIN Empleados em ON em.IdEmpleado = u.IdEmpleado WHERE em.CorreoElectronico='"+rSTextFullRound1.getText()+"'));";

         
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                tokenbd =rs.getString("ut.Codigo");
              
            }
        }catch(Exception e ){
            System.out.println("ERROR"+ e.getMessage());
        
        }
        int tokeningresado= Integer.valueOf(token);
        System.out.println("TOKEN R "+tokenbd);
        System.out.println("TOKEN ING "+token);
        
        if(Integer.valueOf(desencriptar(tokenbd))==tokeningresado){
            actualizarcontra();
        }else{
          tipoerror="ET";
          errortoken();
        }
        
    }
    
    
    public void ocultarelementos(){
        rSButtonRound1.setVisible(false);
        rSButtonRound1.setLocation(220, 270);
        panelRound1.setSize(678, 355);
        panelRound1.setLocation(130, 150);
        jLabel7.setVisible(false);
        rSTextFullRound2.setVisible(false);
        rSTextFullRound3.setVisible(false);
        rSTextFullRound4.setVisible(false);
        rSTextFullRound5.setVisible(false);
        rSTextFullRound6.setVisible(false);
        rSTextFullRound7.setVisible(false);
        
    }
    
    
    public void mostrarelementos(){
        rSTextFullRound1.setEditable(false);
        rSButtonRound1.setLocation(220, 400);
        rSButtonRound2.setVisible(false);
        rSButtonRound1.setVisible(true);
        panelRound1.setSize(678, 460);
        panelRound1.setLocation(130, 90);
        jLabel7.setVisible(true);
        rSTextFullRound2.setVisible(true);
        rSTextFullRound3.setVisible(true);
        rSTextFullRound4.setVisible(true);
        rSTextFullRound5.setVisible(true);
        rSTextFullRound6.setVisible(true);
        rSTextFullRound7.setVisible(true);
    }
   
    
    public void MostrarMensaje(){
        MInformacionCorreo mic = new MInformacionCorreo();
        mic.setVisible(true);
    }
    
    
    public boolean ExistenciaCorreo(){
        String correo="";
        String SQL = "SELECT e.CorreoElectronico FROM Empleados e\n" +
                    "Where e.CorreoElectronico='"+rSTextFullRound1.getText()+"';";
           
         
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                correo =rs.getString("e.CorreoElectronico");
              
            }
        }catch(Exception e ){
            System.out.println("ERROR"+ e.getMessage());
        
        }
        
        if(correo.isEmpty()){
            
            return false;
           
            
        }else{
            return true;
        }
        
        
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
    
   
    
   
    
    
    

    
    
    
    
    public boolean validacionesTexto(){
        
        if(rSTextFullRound1.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un correo valido");
          return false;
        }else{
            return true;
        }
        
        
         
    }
    
    
    public boolean verificar(){
        if (validarCDominio(rSTextFullRound1.getText())==false && validarCVariosD(rSTextFullRound1.getText())==false){
            System.out.println("FALLO");
             return false;
        }else{
            return true;
         }
       
    }
    
    public boolean validarCVariosD(String correo){
            
        Pattern patron = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})+(\\.[A-Za-z]{2,3})$");
        Matcher comparar = patron.matcher(correo);
        return comparar.find();
     
    }
    
    public boolean validarCDominio(String correo){
            
        Pattern patron = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})$");
        Matcher comparar = patron.matcher(correo);
        return comparar.find();
     
    }
    
      public void regresar(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(RecuperarContraseña.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Inicio i = new Inicio();
        i.setVisible(true);
        this.dispose();
    }
      
      
    public void mostrarcodigos(){
        if(validacionesTexto()==true){
           if(verificar()==true){
               if(ExistenciaCorreo()==true){
                    mostrarelementos();
               }else{
                   tipoerror="CI";
                   errortoken();
                   
               }
           }
           
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
        panelRound1 = new isi_2022_ii_proyecto.Recursos.PanelRound();
        label1 = new javax.swing.JLabel();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        rSLabelFecha2 = new rojeru_san.RSLabelFecha();
        F = new rojeru_san.rslabel.RSLabelLineWrap();
        fullmax3 = new javax.swing.JLabel();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        rSLabelIcon3 = new rojerusan.RSLabelIcon();
        rSTextFullRound2 = new rojeru_san.rsfield.RSTextFullRound();
        rSTextFullRound3 = new rojeru_san.rsfield.RSTextFullRound();
        rSTextFullRound4 = new rojeru_san.rsfield.RSTextFullRound();
        rSTextFullRound5 = new rojeru_san.rsfield.RSTextFullRound();
        rSTextFullRound6 = new rojeru_san.rsfield.RSTextFullRound();
        rSTextFullRound7 = new rojeru_san.rsfield.RSTextFullRound();
        rSButtonRound2 = new rojerusan.RSButtonRound();
        jLabel7 = new javax.swing.JLabel();
        rSTextFullRound1 = new rojeru_san.rsfield.RSTextFullRound();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        jLabel9 = new javax.swing.JLabel();
        rSButtonRound1 = new rojerusan.RSButtonRound();
        F1 = new rojeru_san.rslabel.RSLabelLineWrap();
        Header = new javax.swing.JPanel();
        iconminmaxclose = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        linesetting4 = new javax.swing.JPanel();
        rSButtonIconOne5 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne4 = new RSMaterialComponent.RSButtonIconOne();
        linesetting5 = new javax.swing.JPanel();
        linesetting3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(252, 235, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 55, 133));
        jPanel1.setPreferredSize(new java.awt.Dimension(1444, 834));
        jPanel1.setLayout(null);

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(110);
        panelRound1.setRoundBottomRight(110);
        panelRound1.setRoundTopLeft(110);
        panelRound1.setRoundTopRight(110);
        panelRound1.setLayout(null);

        label1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(19, 99, 223));
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("Correo Invalido");
        panelRound1.add(label1);
        label1.setBounds(520, 220, 100, 20);
        panelRound1.add(rSLabelHora1);
        rSLabelHora1.setBounds(60, 20, 110, 30);
        panelRound1.add(rSLabelFecha2);
        rSLabelFecha2.setBounds(540, 30, 110, 20);

        F.setColorForeground(new java.awt.Color(19, 99, 223));
        F.setFocusable(false);
        F.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F.setInheritsPopupMenu(true);
        F.setName("GBGHY"); // NOI18N
        F.setText("T O K E N  D E  A C C E S O");
        panelRound1.add(F);
        F.setBounds(230, 120, 240, 20);

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
        panelRound1.add(fullmax3);
        fullmax3.setBounds(180, 30, 0, 0);

        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.POLYMER);
        rSLabelIcon1.setInheritsPopupMenu(true);
        panelRound1.add(rSLabelIcon1);
        rSLabelIcon1.setBounds(170, 50, 350, 70);

        rSLabelIcon3.setForeground(new java.awt.Color(19, 99, 223));
        rSLabelIcon3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.KEYBOARD_ARROW_LEFT);
        rSLabelIcon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSLabelIcon3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rSLabelIcon3MouseEntered(evt);
            }
        });
        panelRound1.add(rSLabelIcon3);
        rSLabelIcon3.setBounds(30, 20, 30, 30);

        rSTextFullRound2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rSTextFullRound2.setPlaceholder("");
        rSTextFullRound2.setSoloNumeros(true);
        rSTextFullRound2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSTextFullRound2ActionPerformed(evt);
            }
        });
        panelRound1.add(rSTextFullRound2);
        rSTextFullRound2.setBounds(120, 330, 50, 42);

        rSTextFullRound3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rSTextFullRound3.setPlaceholder("");
        rSTextFullRound3.setSoloNumeros(true);
        panelRound1.add(rSTextFullRound3);
        rSTextFullRound3.setBounds(200, 330, 50, 42);

        rSTextFullRound4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rSTextFullRound4.setPlaceholder("");
        rSTextFullRound4.setSoloNumeros(true);
        panelRound1.add(rSTextFullRound4);
        rSTextFullRound4.setBounds(280, 330, 50, 42);

        rSTextFullRound5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rSTextFullRound5.setPlaceholder("");
        rSTextFullRound5.setSoloNumeros(true);
        panelRound1.add(rSTextFullRound5);
        rSTextFullRound5.setBounds(360, 330, 50, 42);

        rSTextFullRound6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rSTextFullRound6.setPlaceholder("");
        rSTextFullRound6.setSoloNumeros(true);
        panelRound1.add(rSTextFullRound6);
        rSTextFullRound6.setBounds(440, 330, 50, 42);

        rSTextFullRound7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rSTextFullRound7.setPlaceholder("");
        rSTextFullRound7.setSoloNumeros(true);
        panelRound1.add(rSTextFullRound7);
        rSTextFullRound7.setBounds(520, 330, 50, 42);

        rSButtonRound2.setBackground(new java.awt.Color(19, 99, 223));
        rSButtonRound2.setText("Aceptar");
        rSButtonRound2.setColorHover(new java.awt.Color(255, 255, 255));
        rSButtonRound2.setColorTextHover(new java.awt.Color(19, 99, 223));
        rSButtonRound2.setFocusable(false);
        rSButtonRound2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rSButtonRound2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rSButtonRound2MouseEntered(evt);
            }
        });
        rSButtonRound2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRound2ActionPerformed(evt);
            }
        });
        panelRound1.add(rSButtonRound2);
        rSButtonRound2.setBounds(220, 270, 260, 40);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(19, 99, 223));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Ingrese el código que hemos enviado a su correo registrado");
        jLabel7.setFocusable(false);
        panelRound1.add(jLabel7);
        jLabel7.setBounds(60, 270, 580, 30);

        rSTextFullRound1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rSTextFullRound1.setPlaceholder("Escribe tu correo...");
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
        panelRound1.add(rSTextFullRound1);
        rSTextFullRound1.setBounds(170, 210, 340, 40);

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ASSIGNMENT_IND);
        panelRound1.add(rSLabelIcon2);
        rSLabelIcon2.setBounds(110, 210, 40, 40);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(19, 99, 223));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ingrese el correo en el que solicito el código de acceso, y presione enter.");
        panelRound1.add(jLabel9);
        jLabel9.setBounds(60, 160, 560, 30);

        rSButtonRound1.setBackground(new java.awt.Color(19, 99, 223));
        rSButtonRound1.setText("Cambiar Contraseña");
        rSButtonRound1.setColorHover(new java.awt.Color(255, 255, 255));
        rSButtonRound1.setColorTextHover(new java.awt.Color(19, 99, 223));
        rSButtonRound1.setFocusable(false);
        rSButtonRound1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
        panelRound1.add(rSButtonRound1);
        rSButtonRound1.setBounds(220, 400, 260, 40);

        jPanel1.add(panelRound1);
        panelRound1.setBounds(130, 90, 678, 460);

        F1.setBackground(new java.awt.Color(255, 255, 255));
        F1.setColorForeground(new java.awt.Color(255, 255, 255));
        F1.setFuente(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        F1.setInheritsPopupMenu(true);
        F1.setName("GBGHY"); // NOI18N
        F1.setText("FAITH HOPE COMPANY © 2022");
        jPanel1.add(F1);
        F1.setBounds(332, 576, 281, 29);

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

        javax.swing.GroupLayout linesetting4Layout = new javax.swing.GroupLayout(linesetting4);
        linesetting4.setLayout(linesetting4Layout);
        linesetting4Layout.setHorizontalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linesetting4Layout.createSequentialGroup()
                .addContainerGap(667, Short.MAX_VALUE)
                .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        linesetting4Layout.setVerticalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linesetting4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

        javax.swing.GroupLayout linesetting3Layout = new javax.swing.GroupLayout(linesetting3);
        linesetting3.setLayout(linesetting3Layout);
        linesetting3Layout.setHorizontalGroup(
            linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
        linesetting3Layout.setVerticalGroup(
            linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        jPanel1.add(Header);
        Header.setBounds(0, 0, 927, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonIconOne4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne4ActionPerformed
        try {
            // TODO add your handling code here:
            con.close();
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(CambiarContrase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_rSButtonIconOne4ActionPerformed

    private void rSButtonIconOne5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIconOne5ActionPerformed

    private void rSButtonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRound1ActionPerformed
        // TODO add your handling code here:
        
      validartoken();
      
      
    }//GEN-LAST:event_rSButtonRound1ActionPerformed

    private void rSButtonRound1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonRound1MouseEntered
        // TODO add your handling code here:
    
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

    private void rSLabelIcon3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSLabelIcon3MouseClicked
        // TODO add your handling code here:
        cerrarc();
        regresar();
    }//GEN-LAST:event_rSLabelIcon3MouseClicked

    private void rSTextFullRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSTextFullRound1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSTextFullRound1ActionPerformed

    private void rSTextFullRound1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rSTextFullRound1KeyReleased
        // TODO add your handling code here:

        if (validarCDominio(rSTextFullRound1.getText()) || validarCVariosD(rSTextFullRound1.getText())){
            //aviso es un label a la ´par del tetxfield
            label1.setVisible(false);

        }
        else{
            label1.setVisible(true);

        }
    }//GEN-LAST:event_rSTextFullRound1KeyReleased

    private void rSButtonRound2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonRound2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonRound2MouseEntered

    private void rSButtonRound2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRound2ActionPerformed
        // TODO add your handling code here:
        mostrarcodigos();
    }//GEN-LAST:event_rSButtonRound2ActionPerformed

    private void rSTextFullRound2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSTextFullRound2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSTextFullRound2ActionPerformed

    private void rSLabelIcon3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSLabelIcon3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_rSLabelIcon3MouseEntered

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
            java.util.logging.Logger.getLogger(CambiarContrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CambiarContrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CambiarContrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CambiarContrase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new CambiarContrase().setVisible(true);
               
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.rslabel.RSLabelLineWrap F;
    private rojeru_san.rslabel.RSLabelLineWrap F1;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel fullmax3;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label1;
    private javax.swing.JPanel linesetting3;
    private javax.swing.JPanel linesetting4;
    private javax.swing.JPanel linesetting5;
    private isi_2022_ii_proyecto.Recursos.PanelRound panelRound1;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne5;
    private rojerusan.RSButtonRound rSButtonRound1;
    private rojerusan.RSButtonRound rSButtonRound2;
    private rojeru_san.RSLabelFecha rSLabelFecha2;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rojerusan.RSLabelIcon rSLabelIcon1;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private rojerusan.RSLabelIcon rSLabelIcon3;
    private rojeru_san.rsfield.RSTextFullRound rSTextFullRound1;
    private rojeru_san.rsfield.RSTextFullRound rSTextFullRound2;
    private rojeru_san.rsfield.RSTextFullRound rSTextFullRound3;
    private rojeru_san.rsfield.RSTextFullRound rSTextFullRound4;
    private rojeru_san.rsfield.RSTextFullRound rSTextFullRound5;
    private rojeru_san.rsfield.RSTextFullRound rSTextFullRound6;
    private rojeru_san.rsfield.RSTextFullRound rSTextFullRound7;
    // End of variables declaration//GEN-END:variables
}

