/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package isi_2022_ii_proyecto;

import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.ColorFondo;
import isi_2022_ii_proyecto.Recursos.ConfirmacionModificar;
import isi_2022_ii_proyecto.Recursos.VentanaEmergente1;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 *
 * @author orell
 */
public class ActualizarEmpleado extends javax.swing.JFrame {

    /**
     * Creates new form AgregarCliente
     */
    boolean a = true;
    String empleado;
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();
    int id=0;
    private String rol;
   
    String codigoe="";

   
    HashMap<String, Integer> puestos = new HashMap<String, Integer>();
    HashMap<String, Integer> documentos = new HashMap<String, Integer>();
  boolean estadosModificar=false;

    public void setEstadosModificar(boolean estadosModificar) {
        this.estadosModificar = estadosModificar;
    }
    public void setCempleado(String cempleado) {
        this.codigoe= cempleado;
    }
      public void validarConfirmacion(){
        if(estadosModificar=true){
            actualizar();
        }
     }
    
       
    public ActualizarEmpleado() {
        initComponents();
         inicializar();
        buscardatos();
        aviso.setVisible(false);
        setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
    }
    
    public void Mostrar(){
       String primernombre="";
       String segundonombre="";
       String Papellido="";
       String Sapellido="";
       String correo="";
       String genero="";
       String puesto="";
       String numeroCta="";
       String estado="";
       String Tipodocumento="";
       SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
       Date Fn = null;
       Date Fc = null;
       String fechan="";
       String fechac="";
       String direccion="";
       
       String SQL = "Select e.PrimerNombre,e.SegundoNombre, e.PrimerApellido,e.SegundoApellido, e.CorreoElectronico,e.DireccionEmpleado, e.NumeroCuenta, e.FechaNacimiento,e.FechaContratacion, p.Nombre,t.Descripcion, eu.Descripcion,g.Nombre from Empleados e \n"
               + "INNER JOIN EstadosUsuario eu ON eu.IdEstado = e.IdEstado\n" +
               "INNER JOIN Generos g ON g.IdGenero = e.IdGenero\n" +
               "INNER JOIN Puestos p ON p.IdPuesto = e.IdPuesto\n" +
               "INNER JOIN TipoDocumento t ON t.IdTipoDocumento = e.IdTipoDocumento\n" +
               "Where IdEmpleado="+Integer.parseInt(codigoe);
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
              primernombre=rs.getString("e.PrimerNombre");
              segundonombre =rs.getString("e.SegundoNombre");
              Papellido =rs.getString("e.PrimerApellido");
              Sapellido =rs.getString("e.SegundoApellido");
              correo = rs.getString("e.CorreoElectronico");
              direccion = rs.getString("e.DireccionEmpleado");
              numeroCta = String.valueOf(rs.getInt("e.NumeroCuenta"));
              fechan =rs.getString("e.FechaNacimiento");
              fechac=rs.getString("e.FechaContratacion");
              estado=rs.getString("eu.Descripcion");
              genero=rs.getString("g.Nombre");
              puesto=rs.getString("p.Nombre");
              Tipodocumento=rs.getString("t.Descripcion");
                      
              
              
            }
            
            try {
                    Fn= formateador.parse(fechan);
                    FN.setDatoFecha(Fn);
                } catch (ParseException ex) {
                    Logger.getLogger(ActualizarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Fc = formateador.parse(fechac);
                    FC.setDatoFecha(Fc);
                } catch (ParseException ex) {
                    Logger.getLogger(ActualizarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            pnomb.setText(primernombre);
            segundonom.setText(segundonombre);
            ape1.setText(Papellido);
            ape2.setText(Sapellido);
            correo1.setText(correo);
            cuenta.setText(String.valueOf(numeroCta));
            JCodigoDisponible.setText(codigoe);
            ActualT.setText(Tipodocumento);
            actualg.setText(genero);
            actualp.setText(puesto);
            actual.setText(estado);
           
       
            

           
       
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     public void actualizar(){
        
       String primernombre="";
       String segundonombre="";
       String Papellido="";
       String Sapellido="";
       String formato="yyyy/MM/dd";
       String correo="";
       int genero=1;
       int puesto=1;
       int numeroCta=1;
       int estado=0;
       int IdTipodocumento=1;
       
       Date FechaN=FN.getDatoFecha();
       Date FechaC=FC.getDatoFecha();
       SimpleDateFormat formateador = new SimpleDateFormat(formato);
       String Fn = formateador.format(FechaN);
       String Fc =formateador.format(FechaC);
        
       
        primernombre=pnomb.getText();
        segundonombre=segundonom.getText();
        Papellido = ape1.getText();
        Sapellido= ape2.getText();
        genero=Obtenergenero();
        correo = correo1.getText();
        puesto=Obtenerpuesto();
        
        numeroCta=Integer.parseInt(cuenta.getText());
        
        estado=Obtenerestado();
        
        IdTipodocumento=ObtenerTipoD();
        String direccion="";

        
 
        String SQL = "UPDATE Empleados SET PrimerNombre=?, SegundoNombre=?, FechaNacimiento=?, IdTipoDocumento=?, CorreoElectronico=?, IdGenero=?, DireccionEmpleado=?, IdPuesto=?, FechaContratacion=?, NumeroCuenta=?, IdEstado=?, PrimerApellido=?,\n"
                + "SegundoApellido=? WHERE IdEmpleado="+JCodigoDisponible.getText()+";";
        try {
           PreparedStatement preparedStmt = con.prepareStatement(SQL);;
         
            preparedStmt.setString (1, primernombre);
            preparedStmt.setString   (2, segundonombre);
            preparedStmt.setString(3, Fn);
            preparedStmt.setInt(4, IdTipodocumento);
            preparedStmt.setString(5, correo);
            preparedStmt.setInt(6, genero);
            preparedStmt.setString(7, direccion);
            preparedStmt.setInt(8, puesto);
            preparedStmt.setString(9, Fc);
            preparedStmt.setInt(10, numeroCta);
            preparedStmt.setInt(11, estado);
            preparedStmt.setString(12, Papellido);
            preparedStmt.setString(13, Sapellido);
            preparedStmt.execute();
               VentanaEmergente1 ve = new VentanaEmergente1();
             ve.setVisible(true);

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR: " + e.getMessage());
        }
        
        
        
       
    }
    public void inicializar(){
         String SQL = "SELECT * FROM TipoDocumento";
         JComboTipo2.addItem("Seleccionar Documento");
         JComboTipo2.setSelectedIndex(0);
         String des="";
         
         String SQL1 = "SELECT * FROM Puestos";
             JCombopuesto.addItem("Seleccionar Genero");
           JCombopuesto.setSelectedIndex(0);
         String puesto="";
         
         String SQL2 = "SELECT * FROM EstadosUsuario";
        JComboEstado.addItem("Seleccionar Estado");
        JComboEstado.setSelectedIndex(0);
         String estadou="";
         
         String SQL3 = "SELECT * FROM Generos";
          JComboGen1.addItem("Seleccionar Genero");
        JComboGen1.setSelectedIndex(0);
         String genero="";
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
               des = rs.getString("Descripcion");
               JComboTipo2.addItem(des);
               
                
            }
            
            
            Statement st1 = (Statement) con.createStatement();
            ResultSet rs1 = st1.executeQuery(SQL1);

            while (rs1.next()) {
               des = rs1.getString("Nombre");
               JComboGen1.addItem(des);
               
                
            }
            
            Statement st2 = (Statement) con.createStatement();
            ResultSet rs2 = st2.executeQuery(SQL2);

            while (rs2.next()) {
               des = rs2.getString("Descripcion");
               JComboEstado.addItem(des);
               
                
            }
            
            
            Statement st3 = (Statement) con.createStatement();
            ResultSet rs3 = st3.executeQuery(SQL3);

            while (rs3.next()) {
               des = rs3.getString("Nombre");
               JCombopuesto.addItem(des);
               
                
            }

            
    
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
       
    
    }
    
    public void buscardatos(){
          String SQL = "SELECT * FROM Empleados WHERE IdEmpleado=(SELECT max(IdEmpleado) FROM Empleados)";
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                id = rs.getInt("IdEmpleado");
               
                
            }

            
            System.out.println("SIN SUMAR"+String.valueOf(id));
            id = id +1;
 
            JCodigoDisponible.setText(String.valueOf(id));
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public int ObtenerTipoD(){
          String SQL = "SELECT t.IdTipoDocumento FROM TipoDocumento t Where t.Descripcion="+"'"+JComboTipo2.getSelectedItem().toString()+"'";
          int idp=0;
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idp = rs.getInt("IdTipoDocumento");
               
                
            }

            
            
 
            
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return idp;
        
    }
    
    
    
     public int Obtenerpuesto(){
          String SQL = "SELECT p.IdPuesto FROM Puestos p Where p.Nombre="+"'"+JComboGen1.getSelectedItem().toString()+"'";
          int idp=0;
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idp = rs.getInt("IdPuesto");
               
                
            }

            
            
 
            
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return idp;
        
    }
    
    
    public int Obtenergenero(){
          String SQL = "SELECT g.IdGenero FROM Generos g Where g.Nombre="+"'"+JCombopuesto.getSelectedItem().toString()+"'";
          int idg=0;
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idg = rs.getInt("IdGenero");
               
                
            }

            
            
 
            
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return idg;
        
    }
    
     public int Obtenerestado(){
          String SQL = "SELECT g.IdEstado FROM EstadosUsuario g Where g.Descripcion="+"'"+JComboEstado.getSelectedItem().toString()+"'";
          int idg=0;
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idg = rs.getInt("IdEstado");
               
                
            }

            
            
 
            
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return idg;
        
    }
    
     
       public Boolean validar(){
        boolean a=true;
      if(pnomb.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre valido");
          a= false;
      }
      
       if(segundonom.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre valido");
          a= false;
      }
        if(ape1.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un apellido valido");
          a= false;
      }
         
       
        if(ape2.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un apellido valido");
          a= false;
      }
        if(cuenta.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un correo valido");
          a= false;
      }
        
       if(correo1.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un correo valido");
          a= false;
      }
       if (validarC(correo1.getText())==false){
           a=false;
       }
            
     
       
       
      
       return a;
       
      
    }
        
    
     public boolean validarC(String correo){
            
        Pattern patron = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})+(\\.[A-Za-z]{2,3})$");
        Matcher comparar = patron.matcher(correo);
        return comparar.find();
     
    }
   
    
  
      
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSYearDateBeanInfo1 = new rojerusan.RSYearDateBeanInfo();
        jPanel1 = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        iconminmaxclose = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        linesetting4 = new javax.swing.JPanel();
        linesetting5 = new javax.swing.JPanel();
        linesetting3 = new javax.swing.JPanel();
        rSButtonIconOne3 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne4 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne5 = new RSMaterialComponent.RSButtonIconOne();
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
        dashboardview = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rSPanelOpacity1 = new RSMaterialComponent.RSPanelOpacity();
        rSLabelIcon6 = new rojerusan.RSLabelIcon();
        jLabel7 = new javax.swing.JLabel();
        rSLabelIcon8 = new rojerusan.RSLabelIcon();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rSLabelIcon17 = new rojerusan.RSLabelIcon();
        jLabel13 = new javax.swing.JLabel();
        rSLabelIcon12 = new rojerusan.RSLabelIcon();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Genero = new javax.swing.JLabel();
        rSPanelCircle1 = new rojeru_san.rspanel.RSPanelCircle();
        JCodigoDisponible = new javax.swing.JLabel();
        actualp = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        ape2 = new rojeru_san.RSMTextFull();
        cuenta = new rojeru_san.RSMTextFull();
        segundonom = new rojeru_san.RSMTextFull();
        ident1 = new rojeru_san.RSMTextFull();
        JComboEstado = new rojerusan.RSComboMetro();
        jLabel23 = new javax.swing.JLabel();
        CorreoAE = new javax.swing.JLabel();
        JCombopuesto = new rojerusan.RSComboMetro();
        pnomb = new rojeru_san.RSMTextFull();
        CorreoAE1 = new javax.swing.JLabel();
        correo1 = new rojeru_san.RSMTextFull();
        estado = new javax.swing.JLabel();
        JComboGen1 = new rojerusan.RSComboMetro();
        jLabel24 = new javax.swing.JLabel();
        JComboTipo2 = new rojerusan.RSComboMetro();
        ActualT = new javax.swing.JLabel();
        ape1 = new rojeru_san.RSMTextFull();
        FechaContracion1 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        FN = new rojeru_san.componentes.RSDateChooser();
        FC = new rojeru_san.componentes.RSDateChooser();
        aviso = new javax.swing.JLabel();
        actual = new javax.swing.JLabel();
        estado2 = new javax.swing.JLabel();
        actualg = new javax.swing.JLabel();
        Genero1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        jLabel6 = new javax.swing.JLabel();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        rSButtonIcon_new9 = new newscomponents.RSButtonIcon_new();

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

        linesetting4.setBackground(new java.awt.Color(20, 101, 187));
        linesetting4.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesetting4Layout = new javax.swing.GroupLayout(linesetting4);
        linesetting4.setLayout(linesetting4Layout);
        linesetting4Layout.setHorizontalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 838, Short.MAX_VALUE)
        );
        linesetting4Layout.setVerticalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        linesetting5.setBackground(new java.awt.Color(20, 101, 187));
        linesetting5.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        linesetting3.setBackground(new java.awt.Color(20, 101, 187));
        linesetting3.setPreferredSize(new java.awt.Dimension(50, 10));

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

        rSButtonIconOne5.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIconOne5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REMOVE);
        rSButtonIconOne5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout linesetting3Layout = new javax.swing.GroupLayout(linesetting3);
        linesetting3.setLayout(linesetting3Layout);
        linesetting3Layout.setHorizontalGroup(
            linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting3Layout.createSequentialGroup()
                .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIconOne3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        linesetting3Layout.setVerticalGroup(
            linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jLabel4))
                    .addComponent(jLabel5))
                .addGap(13, 13, 13)
                .addComponent(linesetting5, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linesetting4, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(linesetting3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
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

        linehidemenu.setBackground(new java.awt.Color(5, 10, 46));
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
                .addComponent(rSLabelIcon5, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
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

        linesetting12.setBackground(new java.awt.Color(0, 55, 133));
        linesetting12.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linesetting12MouseClicked(evt);
            }
        });
        linesetting12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Empleados");
        linesetting12.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 210, 40));

        jLabel12.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Modificar");
        linesetting12.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 40));

        javax.swing.GroupLayout menuhideLayout = new javax.swing.GroupLayout(menuhide);
        menuhide.setLayout(menuhideLayout);
        menuhideLayout.setHorizontalGroup(
            menuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linesetting12, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSButtonIcon_new3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(linesetting6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        menuhideLayout.setVerticalGroup(
            menuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuhideLayout.createSequentialGroup()
                .addComponent(linesetting12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(500, 500, 500)
                .addComponent(linesetting6, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addComponent(MenuIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuhide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
            .addComponent(menuhide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dashboardview.setBackground(new java.awt.Color(232, 245, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelOpacity1.setBackground(new java.awt.Color(60, 76, 143));

        rSLabelIcon6.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon6.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        rSLabelIcon6.setName(""); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("/");

        rSLabelIcon8.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon8.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Modificar Empleado");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Menu Principal");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("/");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Modulo Empleado");

        rSLabelIcon17.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon17.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WIFI_TETHERING);
        rSLabelIcon17.setName(""); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("/");

        rSLabelIcon12.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
        rSLabelIcon12.setName(""); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Listado de Empleados");

        rSPanelOpacity1.setLayer(rSLabelIcon6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(rSLabelIcon8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(rSLabelIcon17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSLabelIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addContainerGap())
        );
        rSPanelOpacity1Layout.setVerticalGroup(
            rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSLabelIcon17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel3.add(rSPanelOpacity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 50));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 0, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Codigo Empleado Disponible:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 220, 50));

        Genero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Genero.setForeground(new java.awt.Color(153, 0, 255));
        Genero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Genero.setText("Genero:");
        jPanel3.add(Genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 390, 150, 30));

        rSPanelCircle1.setBackground(new java.awt.Color(60, 76, 143));

        JCodigoDisponible.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JCodigoDisponible.setForeground(new java.awt.Color(255, 255, 255));
        JCodigoDisponible.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JCodigoDisponible.setText("1");

        javax.swing.GroupLayout rSPanelCircle1Layout = new javax.swing.GroupLayout(rSPanelCircle1);
        rSPanelCircle1.setLayout(rSPanelCircle1Layout);
        rSPanelCircle1Layout.setHorizontalGroup(
            rSPanelCircle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelCircle1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JCodigoDisponible, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        rSPanelCircle1Layout.setVerticalGroup(
            rSPanelCircle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelCircle1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JCodigoDisponible, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(rSPanelCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 70, 70));

        actualp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        actualp.setForeground(new java.awt.Color(153, 0, 255));
        actualp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actualp.setText("Actual Puesto");
        jPanel3.add(actualp, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 430, 140, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 0, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Apellidos del Empleado:");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 190, 30));

        ape2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ape2.setPlaceholder("Ingresa nombre");
        ape2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ape2ActionPerformed(evt);
            }
        });
        jPanel3.add(ape2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 130, -1));

        cuenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cuenta.setPlaceholder("Ingrese Cuenta");
        cuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuentaActionPerformed(evt);
            }
        });
        jPanel3.add(cuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 220, 220, 40));

        segundonom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        segundonom.setPlaceholder("Ingresa nombre");
        segundonom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundonomActionPerformed(evt);
            }
        });
        jPanel3.add(segundonom, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 130, -1));

        ident1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ident1.setPlaceholder("Ingresa nombre");
        ident1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ident1ActionPerformed(evt);
            }
        });
        jPanel3.add(ident1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, 280, -1));

        JComboEstado.setColorArrow(new java.awt.Color(102, 0, 255));
        JComboEstado.setColorFondo(new java.awt.Color(60, 76, 143));
        JComboEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JComboEstadoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JComboEstadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JComboEstadoMouseExited(evt);
            }
        });
        JComboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboEstadoActionPerformed(evt);
            }
        });
        jPanel3.add(JComboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 310, 220, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(153, 0, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Nombre del Empleado:");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 190, 30));

        CorreoAE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CorreoAE.setForeground(new java.awt.Color(153, 0, 255));
        CorreoAE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CorreoAE.setText("Numero cuenta:");
        jPanel3.add(CorreoAE, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 150, 30));

        JCombopuesto.setColorArrow(new java.awt.Color(102, 0, 255));
        JCombopuesto.setColorFondo(new java.awt.Color(60, 76, 143));
        JCombopuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JCombopuestoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JCombopuestoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JCombopuestoMouseExited(evt);
            }
        });
        JCombopuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCombopuestoActionPerformed(evt);
            }
        });
        jPanel3.add(JCombopuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 390, 220, 30));

        pnomb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnomb.setPlaceholder("Ingresa nombre");
        pnomb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnombActionPerformed(evt);
            }
        });
        jPanel3.add(pnomb, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 140, -1));

        CorreoAE1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CorreoAE1.setForeground(new java.awt.Color(153, 0, 255));
        CorreoAE1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CorreoAE1.setText("Correo Electronico:");
        jPanel3.add(CorreoAE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 150, 30));

        correo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        correo1.setPlaceholder("Ingrese Correo");
        correo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correo1ActionPerformed(evt);
            }
        });
        correo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                correo1KeyReleased(evt);
            }
        });
        jPanel3.add(correo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 220, 40));

        estado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estado.setForeground(new java.awt.Color(153, 0, 255));
        estado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estado.setText("Estado:");
        jPanel3.add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, 160, 30));

        JComboGen1.setColorArrow(new java.awt.Color(102, 0, 255));
        JComboGen1.setColorFondo(new java.awt.Color(60, 76, 143));
        JComboGen1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JComboGen1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JComboGen1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JComboGen1MouseExited(evt);
            }
        });
        JComboGen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboGen1ActionPerformed(evt);
            }
        });
        jPanel3.add(JComboGen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 470, 220, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(153, 0, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("No. Identificacion:");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 170, 30));

        JComboTipo2.setColorArrow(new java.awt.Color(102, 0, 255));
        JComboTipo2.setColorFondo(new java.awt.Color(60, 76, 143));
        JComboTipo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JComboTipo2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JComboTipo2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JComboTipo2MouseExited(evt);
            }
        });
        JComboTipo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboTipo2ActionPerformed(evt);
            }
        });
        jPanel3.add(JComboTipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 280, 30));

        ActualT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ActualT.setForeground(new java.awt.Color(153, 0, 255));
        ActualT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ActualT.setText("Tipo documento");
        jPanel3.add(ActualT, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 170, 30));

        ape1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ape1.setPlaceholder("Ingresa nombre");
        ape1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ape1ActionPerformed(evt);
            }
        });
        jPanel3.add(ape1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, 140, -1));

        FechaContracion1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        FechaContracion1.setForeground(new java.awt.Color(153, 0, 255));
        FechaContracion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FechaContracion1.setText("Fecha Contratacion:");
        jPanel3.add(FechaContracion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 150, 40));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(153, 0, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Fecha de nacimiento");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 180, 30));
        jPanel3.add(FN, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));
        jPanel3.add(FC, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, -1, -1));

        aviso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        aviso.setForeground(new java.awt.Color(255, 0, 0));
        aviso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aviso.setText("*Correo invalido*");
        jPanel3.add(aviso, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 140, 170, 25));

        actual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        actual.setForeground(new java.awt.Color(153, 0, 255));
        actual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actual.setText("Actual Estado:");
        jPanel3.add(actual, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 270, 160, 30));

        estado2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estado2.setForeground(new java.awt.Color(153, 0, 255));
        estado2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estado2.setText("Actual Estado:");
        jPanel3.add(estado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 160, 30));

        actualg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        actualg.setForeground(new java.awt.Color(153, 0, 255));
        actualg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actualg.setText("Actual Genero:");
        jPanel3.add(actualg, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 150, 30));

        Genero1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Genero1.setForeground(new java.awt.Color(153, 0, 255));
        Genero1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Genero1.setText("Genero:");
        jPanel3.add(Genero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, 150, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 0, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Puesto:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 470, 140, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 0, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Puesto:");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 140, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 0, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Tipo documento");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 170, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(153, 0, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Tipo documento");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 170, 30));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);
        jPanel4.add(rSLabelIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("MODULO EMPLEADO");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 823, 40));

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE_OUTLINE);
        jPanel4.add(rSLabelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 60, 50));

        rSLabelHora1.setForeground(new java.awt.Color(20, 101, 187));
        jPanel4.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 10, 108, -1));

        rSButtonIcon_new9.setBackground(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new9.setText("Modificar Cambios");
        rSButtonIcon_new9.setBackgroundHover(new java.awt.Color(153, 0, 255));
        rSButtonIcon_new9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rSButtonIcon_new9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.UPDATE);
        rSButtonIcon_new9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dashboardviewLayout = new javax.swing.GroupLayout(dashboardview);
        dashboardview.setLayout(dashboardviewLayout);
        dashboardviewLayout.setHorizontalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonIcon_new9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dashboardviewLayout.setVerticalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonIcon_new9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 1416, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dashboardview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dashboardview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1362, 716));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonIconOne3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne3ActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }//GEN-LAST:event_rSButtonIconOne3ActionPerformed

    private void rSButtonIconOne4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne4ActionPerformed
        try {
            // TODO add your handling code here:
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_rSButtonIconOne4ActionPerformed

    private void rSButtonIconOne5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne5ActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_rSButtonIconOne5ActionPerformed

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

    private void rSButtonIcon_new3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new3ActionPerformed
        // TODO add your handling code here:
        Empleados em= new Empleados();
        em.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_rSButtonIcon_new3ActionPerformed

    private void linesetting12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_linesetting12MouseClicked

    private void ape2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ape2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ape2ActionPerformed

    private void cuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuentaActionPerformed

    private void segundonomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundonomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundonomActionPerformed

    private void ident1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ident1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ident1ActionPerformed

    private void JComboEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboEstadoMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_JComboEstadoMouseClicked

    private void JComboEstadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboEstadoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboEstadoMouseEntered

    private void JComboEstadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboEstadoMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JComboEstadoMouseExited

    private void JComboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboEstadoActionPerformed

    private void JCombopuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCombopuestoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JCombopuestoMouseClicked

    private void JCombopuestoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCombopuestoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JCombopuestoMouseEntered

    private void JCombopuestoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCombopuestoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JCombopuestoMouseExited

    private void JCombopuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCombopuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCombopuestoActionPerformed

    private void pnombActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnombActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnombActionPerformed

    private void correo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correo1ActionPerformed

    private void JComboTipo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboTipo2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboTipo2MouseClicked

    private void JComboTipo2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboTipo2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboTipo2MouseEntered

    private void JComboTipo2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboTipo2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboTipo2MouseExited

    private void JComboTipo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboTipo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboTipo2ActionPerformed

    private void JComboGen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboGen1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboGen1ActionPerformed

    private void JComboGen1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboGen1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboGen1MouseExited

    private void JComboGen1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboGen1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboGen1MouseEntered

    private void JComboGen1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JComboGen1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboGen1MouseClicked

    private void ape1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ape1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ape1ActionPerformed

    private void rSButtonIcon_new9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new9ActionPerformed
        if(validar()==true){
            ConfirmacionModificar me = new ConfirmacionModificar();
            me.setAemp(this);
            me.setTipo("Prov");
            me.setVisible(true);
        }else{

            JOptionPane.showMessageDialog(this, "POR FAVOR VERIFIQUE LA INFORMACION");
        }
    }//GEN-LAST:event_rSButtonIcon_new9ActionPerformed

    private void correo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_correo1KeyReleased
         if (validarC(correo1.getText())){
              aviso.setVisible(false);
             //  JOptionPane.showMessageDialog(this, "El correo ingresado es valido");  
        }
          else{
                    aviso.setVisible(true);
              //JOptionPane.showMessageDialog(this, "El correo ingresado no es valido"); 
          }
    }//GEN-LAST:event_correo1KeyReleased
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
            java.util.logging.Logger.getLogger(ActualizarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActualizarEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActualT;
    private javax.swing.JLabel CorreoAE;
    private javax.swing.JLabel CorreoAE1;
    private rojeru_san.componentes.RSDateChooser FC;
    private rojeru_san.componentes.RSDateChooser FN;
    private javax.swing.JLabel FechaContracion1;
    private javax.swing.JLabel Genero;
    private javax.swing.JLabel Genero1;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel JCodigoDisponible;
    private rojerusan.RSComboMetro JComboEstado;
    private rojerusan.RSComboMetro JComboGen1;
    private rojerusan.RSComboMetro JComboTipo2;
    private rojerusan.RSComboMetro JCombopuesto;
    private javax.swing.JPanel MenuIcon;
    private javax.swing.JLabel actual;
    private javax.swing.JLabel actualg;
    private javax.swing.JLabel actualp;
    private rojeru_san.RSMTextFull ape1;
    private rojeru_san.RSMTextFull ape2;
    private javax.swing.JLabel aviso;
    private rojeru_san.RSMTextFull correo1;
    private rojeru_san.RSMTextFull cuenta;
    private javax.swing.JPanel dashboardview;
    private javax.swing.JLabel estado;
    private javax.swing.JLabel estado2;
    private javax.swing.JPanel iconminmaxclose;
    private rojeru_san.RSMTextFull ident1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JPanel linehidemenu;
    private javax.swing.JPanel linesetting;
    private javax.swing.JPanel linesetting1;
    private javax.swing.JPanel linesetting10;
    private javax.swing.JPanel linesetting11;
    private javax.swing.JPanel linesetting12;
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
    private rojeru_san.RSMTextFull pnomb;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne3;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new3;
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
    private rojerusan.RSLabelIcon rSLabelIcon6;
    private rojerusan.RSLabelIcon rSLabelIcon7;
    private rojerusan.RSLabelIcon rSLabelIcon8;
    private rojerusan.RSLabelIcon rSLabelIcon9;
    private rojeru_san.rspanel.RSPanelCircle rSPanelCircle1;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity1;
    private rojerusan.RSYearDateBeanInfo rSYearDateBeanInfo1;
    private rojeru_san.RSMTextFull segundonom;
    // End of variables declaration//GEN-END:variables

    void setId(String code) {
       
    }


    
}
