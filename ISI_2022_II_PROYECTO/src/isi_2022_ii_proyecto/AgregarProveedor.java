/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package isi_2022_ii_proyecto;

import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.ColorFondo;
import isi_2022_ii_proyecto.Recursos.ConfirmacionGuardar;
import isi_2022_ii_proyecto.Recursos.VentanaEmergente1;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AgregarProveedor extends javax.swing.JFrame {

    /**
     * Creates new form AgregarProveedor
     */
    boolean a = true;
    String proveedor;
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();
    int id=0;
      boolean estadoagregar=false;
      
      
  String usuario;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
        jLabel15.setText("Usuario en sesion: "+usuario);
    }

    public void setEstadoagregar(boolean estadoagregar) {
        this.estadoagregar = estadoagregar;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    public AgregarProveedor() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
           listarEstado();
        buscardatos();
        aviso.setVisible(false);
         aviso2.setVisible(false);
         aviso3.setVisible(false);
         setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
    }
    public void conectar(){
        conexion.setAgprovee(this);
        con = conexion.conexion();
    }
    public void validarconexion(){

        if(con==null){
            conectar();
            
            
        }
        
    }
    public void conectarerror(){
        conexion.setAgprovee(this);
        conexion.setPass(false);
        con = conexion.conexion();
    }
    
       public void conectarinicio(){
        conexion.setAgprovee(this);
        con = conexion.conexion();
    }
       
        public void conectarsinerror(){
        conexion.setPass(true);
        conexion.setAgprovee(this);
        con = conexion.conexion();
    }
    
     public void validarConfirmacion(){
        if(estadoagregar=true){
            insertarP();
        }
    }
  
       
        public void listarEstado(){
        JEstado.addItem("Seleccionar Estado");
        JEstado.setSelectedIndex(0);
        String descripcion="";
         
        String SQL = "SELECT b.Descripcion From EstadosUsuario b";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                descripcion =rs.getString("b.Descripcion");
    
                JEstado.addItem(descripcion);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        con=null;
        validarconexion();
        
        }
    }    
            
       
            
    
            
           

       
    
    
    
    public void buscardatos(){
          String SQL = "SELECT * FROM Proveedores WHERE IdProveedor=(SELECT max(IdProveedor) FROM Proveedores)";
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                id = rs.getInt("IdProveedor");
               
                
            }

            
            System.out.println("SIN SUMAR"+String.valueOf(id));
            id = id +1;
            System.out.println(String.valueOf(id));
            JCodigoDisponible.setText(String.valueOf(id));
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
           con=null;
        validarconexion();
        }
    }
    
    public int Obtenerestado(){
          String SQL = "SELECT * FROM EstadosUsuario g Where g.Descripcion="+"'"+JEstado.getSelectedItem().toString()+"'";
          int idg=0;
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idg = rs.getInt("IdEstado");
               
                
            }

            
            
 
            
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        
           con=null;
        validarconexion();
        }
        return idg;
        
    }
    public static final int UNIQUE_CONSTRAINT_VIOLATED = 1062;
    public void insertarP(){
        String NombreE="";
        String direccion="";
        String telefono="";
        String correo="";
        int estadop=Obtenerestado();
        
        
        NombreE = NombreE1.getText();
        direccion = DireccionE.getText();
        telefono=Tel.getText();
        correo=CorreoP.getText();
     
  
       
                
       String SQL = "INSERT INTO Proveedores (IdProveedor,NombreEmpresa,DireccionEmpresa,Telefono,CorreoElectronico,Estado) VALUES"
                + "(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setInt(1, id);
            preparedStmt.setString (2, NombreE);
            preparedStmt.setString   (3, direccion);
            preparedStmt.setString(4, telefono);
            preparedStmt.setString(5, correo);
            preparedStmt.setInt(6, estadop);
            preparedStmt.execute();
            
             VentanaEmergente1 ve = new VentanaEmergente1();
             ve.setVisible(true);

        }catch (SQLException  e) {
                String msj = "ERROR";
                if (UNIQUE_CONSTRAINT_VIOLATED == e.getErrorCode ()) {
                  JOptionPane.showMessageDialog(null,"EL REGISTRO EXISTE EN LA BASE DE DATOS");
               
                }else{
                    JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE); 
                }
           con=null;
         validarconexion();
        }
        
        
        
       
    }
    
    public Boolean validar(){
        boolean a=true;
      if(NombreE1.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre valido");
          a= false;
      }
       if(validarNombre(NombreE1.getText())==false){
          a=false;
      }
      
       if(DireccionE.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese una direccion valida");
          a= false;
      }
       
       
        if(Tel.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un telefono valido");
          a= false;
      }
        if (validarT(Tel.getText())==false){
           a=false;
       }
        
       if(CorreoP.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un correo valido");
          a= false;
      }
      /* if (validarC(CorreoP.getText())==false){
           a=false;
       }
       if (validarcorreo(CorreoP.getText())==false){
           a=false;
       }*/
       
       
       if(JEstado.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor seleccione un estado valido");
          a= false;
       }
       
       return a;
       
      
    }
     public  boolean validarNombre(String Nombre){
    boolean check=false;
    
    /*Verificamos que no sea null*/ 
    if(Nombre != null){
        /* 1ª Condición: que la letra inicial sea mayúscula*/
        //boolean isFirstUpper=Character.isUpperCase(Nombre.charAt(0));

        /* 2ª Condición: que el tamaño sea >= 3 y <= 15*/
        int stringSize=Nombre.length();
        boolean isValidSize=(stringSize >= 3 && stringSize <= 25);

        /* 3ª Condición: que contenga al menos un espacio*/
        boolean isSpaced=Nombre.contains(" ");

        /* Verificamos que las tres condiciones son verdaderas*/
        check=  (isValidSize &&  isSpaced) ;
    }
    /*Devolvemos el estado de la validación*/
    return check;
    
        
    }
    public boolean validarC(String correo){
            
        Pattern patron = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})+(\\.[A-Za-z]{2,3})$");
        Matcher comparar = patron.matcher(correo);
        return comparar.find();
     
    }
    
    public boolean validarcorreo(String correo){
            
          Pattern patron = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" 
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})$");
        Matcher comparar = patron.matcher(correo);
        return comparar.find();
     
    }

      public boolean verificar(){
        if (validarcorreo(CorreoP.getText())==false && validarC(CorreoP.getText())==false){
            System.out.println("FALLO");
             return false;
        }else{
            return true;
         }
     }
      
     public boolean validarT(String cel){
         Pattern patron = Pattern
                .compile("^[389]?[0-9]{4}?[0-9]{4}$");        
        Matcher comparar = patron.matcher(cel);
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
        jLabel18 = new javax.swing.JLabel();
        CorreoP = new rojeru_san.RSMTextFull();
        rSPanelCircle1 = new rojeru_san.rspanel.RSPanelCircle();
        JCodigoDisponible = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        JEstado = new rojerusan.RSComboMetro();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        NombreE1 = new rojeru_san.RSMTextFull();
        DireccionE = new rojeru_san.RSMTextFull();
        Tel = new rojeru_san.RSMTextFull();
        aviso2 = new javax.swing.JLabel();
        aviso = new javax.swing.JLabel();
        aviso3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        jLabel6 = new javax.swing.JLabel();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jLabel15 = new javax.swing.JLabel();
        guardar = new newscomponents.RSButtonIcon_new();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1416, -1));

        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        menu.add(MenuIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 668));

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
        jLabel3.setText("Proveedor");
        linesetting12.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 210, 40));

        jLabel12.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Agregar");
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
                .addComponent(linesetting6, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
        );

        menu.add(menuhide, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 52, -1, 660));

        dashboardview.setBackground(new java.awt.Color(232, 245, 255));
        dashboardview.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jLabel8.setText("Agregar Proveedor");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Menú Principal");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("/");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Modúlo Proveedores");

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
        jLabel14.setText("Listado de Proveedores");
        jLabel14.setToolTipText("");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(9, 9, 9)
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
                    .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSLabelIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel3.add(rSPanelOpacity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 50));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 0, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("IdProveedor");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 190, 40));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 0, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Nombre del Empresa:");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 190, 30));

        CorreoP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CorreoP.setPlaceholder("Ingresar Correo Electronico");
        CorreoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorreoPActionPerformed(evt);
            }
        });
        CorreoP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CorreoPKeyReleased(evt);
            }
        });
        jPanel3.add(CorreoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 390, -1));

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

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 0, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Teléfono");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 180, 40));

        JEstado.setBorder(null);
        JEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JEstadoActionPerformed(evt);
            }
        });
        jPanel3.add(JEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, 260, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 0, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Estado");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 180, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 0, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Direccion de Empresa");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 180, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(153, 0, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Correo Electronico:");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 180, 40));

        NombreE1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NombreE1.setPlaceholder("Ingresa nombre Empresas..");
        NombreE1.setSoloLetras(true);
        NombreE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreE1ActionPerformed(evt);
            }
        });
        NombreE1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NombreE1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombreE1KeyTyped(evt);
            }
        });
        jPanel3.add(NombreE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 380, -1));

        DireccionE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DireccionE.setPlaceholder("Ingresa Informacion Empresas..");
        DireccionE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DireccionEActionPerformed(evt);
            }
        });
        DireccionE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DireccionEKeyTyped(evt);
            }
        });
        jPanel3.add(DireccionE, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 380, -1));

        Tel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tel.setPlaceholder("Ingresa el numero Telefono");
        Tel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelActionPerformed(evt);
            }
        });
        Tel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TelKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TelKeyTyped(evt);
            }
        });
        jPanel3.add(Tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 390, -1));

        aviso2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        aviso2.setForeground(new java.awt.Color(255, 0, 0));
        aviso2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aviso2.setText("*Teléfono invalído*");
        jPanel3.add(aviso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 320, 180, 30));

        aviso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        aviso.setForeground(new java.awt.Color(255, 0, 0));
        aviso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aviso.setText("*Correo invalido*");
        jPanel3.add(aviso, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 390, 170, 25));

        aviso3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        aviso3.setForeground(new java.awt.Color(255, 0, 0));
        aviso3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aviso3.setText("*Nombre Incorrecto*");
        jPanel3.add(aviso3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 180, -1));

        dashboardview.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 118, 1030, 520));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);
        jPanel4.add(rSLabelIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("MODÚLO PROVEEDORES");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 350, 40));

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE_OUTLINE);
        jPanel4.add(rSLabelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 60, 50));

        rSLabelHora1.setForeground(new java.awt.Color(20, 101, 187));
        jPanel4.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 10, 108, -1));

        jLabel15.setBackground(new java.awt.Color(102, 51, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 0, 255));
        jLabel15.setText("Usuario en sesion: ");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, -1));

        dashboardview.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1156, -1));

        guardar.setBackground(new java.awt.Color(33, 150, 243));
        guardar.setText("Guardar Proveedor");
        guardar.setBackgroundHover(new java.awt.Color(0, 55, 133));
        guardar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.GRID_ON);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        dashboardview.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 190, 40));

        jPanel1.add(dashboardview, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, 662));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1373, 712));
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
       Proveedores proveedor = new  Proveedores ();
       proveedor.setUsuario(usuario);
        proveedor.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new3ActionPerformed

    private void linesetting12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_linesetting12MouseClicked

    private void JEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JEstadoActionPerformed

    private void CorreoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorreoPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CorreoPActionPerformed

    private void NombreE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreE1ActionPerformed

    private void DireccionEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DireccionEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DireccionEActionPerformed

    private void TelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelActionPerformed

    private void TelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TelKeyReleased
     if (validarT(Tel.getText())){
              aviso2.setVisible(false);
             //  JOptionPane.showMessageDialog(this, "El correo ingresado es valido");  
        }
          else{
                    aviso2.setVisible(true);
              //JOptionPane.showMessageDialog(this, "El correo ingresado no es valido"); 
          }
    }//GEN-LAST:event_TelKeyReleased

    private void CorreoPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CorreoPKeyReleased
    if (validarC(CorreoP.getText())||validarcorreo(CorreoP.getText()) ){
              aviso.setVisible(false);
             //  JOptionPane.showMessageDialog(this, "El correo ingresado es valido");  
        }
          else{
                    aviso.setVisible(true);
              //JOptionPane.showMessageDialog(this, "El correo ingresado no es valido"); 
          }
    }//GEN-LAST:event_CorreoPKeyReleased

    private void TelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TelKeyTyped
         if (Tel.getText().trim().length() == 8) {
        evt.consume();
        
         }
    }//GEN-LAST:event_TelKeyTyped

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
          if(validar()==true){
           if(verificar()==true){
            ConfirmacionGuardar p = new  ConfirmacionGuardar();
            p.setGprov(this);
            p.setTipo("GProveedor");
            p.setVisible(true);

        }else{
              
            JOptionPane.showMessageDialog(this, "POR FAVOR VERIFIQUE LA INFORMACIÓN");
        }
        } 
    }//GEN-LAST:event_guardarActionPerformed

    private void NombreE1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreE1KeyReleased
if (validarNombre(NombreE1.getText())){
            aviso3.setVisible(false);

        }
        else{
            aviso3.setVisible(true);

        }      
      
    }//GEN-LAST:event_NombreE1KeyReleased

    private void NombreE1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreE1KeyTyped
          char caracter=evt.getKeyChar();
          if(Character.isLowerCase(caracter)){
              
            evt.setKeyChar(Character.toUpperCase(caracter));
      }
          if (NombreE1.getText().trim().length() == 25) {
        evt.consume();
       }
    }//GEN-LAST:event_NombreE1KeyTyped

    private void DireccionEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DireccionEKeyTyped
           char caracter=evt.getKeyChar();
          if(Character.isLowerCase(caracter)){
              
            evt.setKeyChar(Character.toUpperCase(caracter));
      }
          if (NombreE1.getText().trim().length() == 25) {
        evt.consume();
       }
    }//GEN-LAST:event_DireccionEKeyTyped
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
            java.util.logging.Logger.getLogger(AgregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarProveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSMTextFull CorreoP;
    private rojeru_san.RSMTextFull DireccionE;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel JCodigoDisponible;
    private rojerusan.RSComboMetro JEstado;
    private javax.swing.JPanel MenuIcon;
    private rojeru_san.RSMTextFull NombreE1;
    private rojeru_san.RSMTextFull Tel;
    private javax.swing.JLabel aviso;
    private javax.swing.JLabel aviso2;
    private javax.swing.JLabel aviso3;
    private javax.swing.JPanel dashboardview;
    private newscomponents.RSButtonIcon_new guardar;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne3;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new3;
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
    // End of variables declaration//GEN-END:variables
}
