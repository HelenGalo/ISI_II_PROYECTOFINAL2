 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package isi_2022_ii_proyecto;

import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.ColorFondo;
import isi_2022_ii_proyecto.Recursos.ConfirmacionModificarProductos;
import isi_2022_ii_proyecto.Recursos.VentanaEmergente1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author orell
 */
public class ActualizarProductos extends javax.swing.JFrame {

    /**
     * Creates new form AgregarCliente
     */
     boolean a = true;
    String prod;
   
   
    int proveedor=1;
    int Categoria=1;
    int estado=1;
    int id=0;  
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();
      String usuario;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
        jLabel15.setText("Usuario en sesion: "+usuario);
    }
      
 boolean estadosModificar=false;

    public void setEstadosModificar(boolean estadosModificar) {
        this.estadosModificar = estadosModificar;
    }
  
       public void setId(int id) {
        this.id = id;
    }
  public void setProducto(String producto) {
        this.prod = producto;
    }
  public void conectar(){
        conexion.setAp(this);
        con = conexion.conexion();
    }
    
  public void validarconexion(){

        if(con==null){
            conectar();
            
            
        }
        
    }
  public void conectarerror(){
        conexion.setAp(this);
        conexion.setPass(false);
        con = conexion.conexion();
    }
    
       public void conectarinicio(){
        conexion.setAp(this);
        con = conexion.conexion();
    }
       
        public void conectarsinerror(){
        conexion.setPass(true);
        conexion.setAp(this);
        con = conexion.conexion();
    }
    public ActualizarProductos() {
        initComponents();
         this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        inicializar();
        listarEstado();
       ListarProveedor();
       listarCategoria();
       avisoT.setVisible(false);
        setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
    }
        public void validarConfirmacion(){
        if(estadosModificar=true){
            ActualizarP();
        }
    }
    public void inicializar(){
         JCodigoDisponible1.setText(prod);
    }
    
    
    public void mostrarP(){
           String nombre="";
           String descrip="";
           String proveedor="";
           String Categoria="";
           Float precio=0.00f;
           String estado="";
       
        String SQL = "SELECT  p.IdProducto, p.Nombre, p.Descripcion, p.Precio, c.NombreCategoria,pr.NombreEmpresa, eu.Descripcion  FROM Productos p\n" +
                      "INNER JOIN Proveedores pr ON pr.IdProveedor = p.IdProveedor\n" +
                      "INNER JOIN Categorias c ON c.IdCategoria = p.IdCategoria\n" +
                     "INNER JOIN EstadosUsuario eu ON eu.IdEstado = p.IdEstado\n" +
                      "WHERE p.IdProducto="+id;
         try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            DecimalFormat formato = new DecimalFormat("##,###.00");
            while (rs.next()) {
          
            nombre = rs.getString("p.Nombre");
            descrip = rs.getString("p.Descripcion");
            precio = rs.getFloat("p.Precio");
            proveedor= rs.getString("pr.NombreEmpresa");
            Categoria =rs.getString("c.NombreCategoria");
            estado=rs.getString("eu.Descripcion");
            }
            
           NombreP.setText(nombre);
           Descrip1.setText(descrip);
           pre.setText(formato.format(precio));
           actualCat.setText(Categoria);
           actualp.setText(proveedor);
           actuale.setText(estado);
           
           
          JCodigoDisponible1.setText(String.valueOf(id));
        
    
          } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
         con=null;
        validarconexion();
          
          }
    }
        
    
    
    public void listarCategoria(){
        JCategoria.addItem("Seleccionar Categoria");
        JCategoria.setSelectedIndex(0);
        String cat = "";
        
      String SQL = "SELECT C.NombreCategoria FROM Categorias C";
        
         try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
              cat = rs.getString("C.NombreCategoria");
               JCategoria.addItem(cat);
               
              
                   
               } 
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        con=null;
        validarconexion();
            
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
      
      
     public void ListarProveedor(){
         JProveedores.addItem("Selecionar Proveedor");
         JProveedores.setSelectedIndex(0);
         String pro ="";
          String SQL = "SELECT p.NombreEmpresa FROM Proveedores p WHERE IdProveedor=1";
             
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
               pro = rs.getString("p.NombreEmpresa");
               JProveedores.addItem(pro);
               
                
            }
         



        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
          con=null;
        validarconexion();
        }
       
    
    }
    

    
        public int ObtenerCategoria(){
          int categoria=0;
          String SQL = "SELECT * FROM Categorias c Where c.NombreCategoria="+"'"+JCategoria.getSelectedItem().toString()+"'";
 
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                categoria = rs.getInt("IdCategoria");
               
                
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
          con=null;
        validarconexion();
        }
        return categoria;
        
    }
          public int Obtenerestado(){
              int estado =0;
          String SQL = "SELECT * FROM EstadosUsuario g Where g.Descripcion="+"'"+JEstado.getSelectedItem().toString()+"'";
   
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                estado = rs.getInt("IdEstado");
               
                
            }

            
            
 
            
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
          con=null;
        validarconexion();
        
        }
        return estado;
        
    }
            public int ObtenerProveedor(){
          String SQL = "SELECT * FROM Proveedores p Where p.NombreEmpresa="+"'"+JProveedores.getSelectedItem().toString()+"'";
          int idg=0;
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idg = rs.getInt("IdProveedor");
               
                
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        con=null;
        validarconexion();
        }
        return idg;
        
    }
      public static final int UNIQUE_CONSTRAINT_VIOLATED = 1062; 
      public boolean ActualizarP(){
             String nombre="";
           String descrip="";
         
          
         nombre = NombreP.getText();
        descrip = Descrip1.getText();
        Float precio = Float.valueOf(pre.getText());
        proveedor=ObtenerProveedor();
        Categoria=ObtenerCategoria();
        estado=Obtenerestado();
        
        String SQL = "UPDATE  Productos SET Nombre=?,IdCategoria=?,IdProveedor=?,Descripcion=?,Precio=?,IdEstado=? WHERE IdProducto="+"'"+id+"'";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setString (1, nombre);
            preparedStmt.setInt(2, Categoria);
            preparedStmt.setInt(3, proveedor);
            preparedStmt.setString (4,descrip);
            preparedStmt.setFloat(5, precio);
            preparedStmt.setInt(6, estado);
          
            preparedStmt.execute();
            
          VentanaEmergente1 ve = new VentanaEmergente1();
             ve.setVisible(true);

         } catch (SQLException  e) {
                String msj = "ERROR";
                if (UNIQUE_CONSTRAINT_VIOLATED == e.getErrorCode ()) {
                  JOptionPane.showMessageDialog(null,"EL REGISTRO EXISTE EN LA BASE DE DATOS");
                  
                }else{
                     JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                    
                con=null;
              validarconexion();
               
        }
        return true;
         }

      
       
    
        
      public Boolean validar(){
        boolean a=true;
      if(NombreP.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre valido");
          a= false;
      }
      
         if(validarNombre(NombreP.getText())==false)  {
                   a= false;     
                }
 
     
        if(pre.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese numeros unicamente");
          a= false;
      }
        
         if(Validartarifa(pre.getText())==false)  {
                   a= false;     
                }
     
       if(Descrip1.getText().isEmpty()){
       
          JOptionPane.showMessageDialog(this, "Por favor ingrese una descripcion");
          a= false;
      }
       
       if(JCategoria.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor seleccione una categoria valido");
          a= false;
      
    }
       
        if(JProveedores.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor seleccione una categoria valido");
          a= false;
      
 
       
      
    }
             return a;
      }
  
public boolean Validartarifa(String tarifa){
        Pattern patron = Pattern
                .compile("^[0-9]*(\\.[0-9]{2})$");        
        Matcher comparar = patron.matcher(tarifa);
        return comparar.find();
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
        jPanel4 = new javax.swing.JPanel();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        jLabel6 = new javax.swing.JLabel();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jLabel15 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        rSPanelOpacity3 = new RSMaterialComponent.RSPanelOpacity();
        rSLabelIcon16 = new rojerusan.RSLabelIcon();
        jLabel24 = new javax.swing.JLabel();
        rSLabelIcon19 = new rojerusan.RSLabelIcon();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        rSLabelIcon20 = new rojerusan.RSLabelIcon();
        jLabel30 = new javax.swing.JLabel();
        rSLabelIcon21 = new rojerusan.RSLabelIcon();
        jLabel34 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        pre = new rojeru_san.RSMTextFull();
        rSPanelCircle2 = new rojeru_san.rspanel.RSPanelCircle();
        JCodigoDisponible1 = new javax.swing.JLabel();
        precio = new javax.swing.JLabel();
        NombreP = new rojeru_san.RSMTextFull();
        jLabel17 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        JCategoria = new rojerusan.RSComboMetro();
        Estado = new javax.swing.JLabel();
        JProveedores = new rojerusan.RSComboMetro();
        jLabel35 = new javax.swing.JLabel();
        Descrip1 = new rojeru_san.RSMTextFull();
        actualp = new javax.swing.JLabel();
        Estado1 = new javax.swing.JLabel();
        categoria = new javax.swing.JLabel();
        actualCat = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        JEstado = new rojerusan.RSComboMetro();
        Estado2 = new javax.swing.JLabel();
        actuale = new javax.swing.JLabel();
        avisoT = new javax.swing.JLabel();
        avisoT1 = new javax.swing.JLabel();
        rSButtonIcon_new9 = new newscomponents.RSButtonIcon_new();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setMinimumSize(new java.awt.Dimension(150, 50));
        Header.setPreferredSize(new java.awt.Dimension(800, 50));

        jPanel2.setBackground(new java.awt.Color(20, 101, 187));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("SYSTEM");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("GEVEC");

        linesetting4.setBackground(new java.awt.Color(20, 101, 187));
        linesetting4.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addContainerGap(141, Short.MAX_VALUE)
                .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSButtonIconOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );
        linesetting3Layout.setVerticalGroup(
            linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonIconOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(linesetting5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linesetting4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(83, 83, 83)
                .addComponent(linesetting3, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1362, -1));

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
                .addComponent(rSLabelIcon5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jLabel3.setText("Productos");
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
            .addComponent(linesetting6, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        menuhideLayout.setVerticalGroup(
            menuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuhideLayout.createSequentialGroup()
                .addComponent(linesetting12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButtonIcon_new3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(linesetting6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addComponent(MenuIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(menuhide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
            .addComponent(menuhide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 46, -1, -1));

        dashboardview.setBackground(new java.awt.Color(232, 245, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);
        jPanel4.add(rSLabelIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("MODÚLO PRODUCTOS");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 0, 280, 53));

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE_OUTLINE);
        jPanel4.add(rSLabelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1012, 0, 60, 50));

        rSLabelHora1.setForeground(new java.awt.Color(20, 101, 187));
        jPanel4.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(875, 10, 108, -1));

        jLabel15.setBackground(new java.awt.Color(102, 51, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 0, 255));
        jLabel15.setText("Usuario en sesion: ");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        rSPanelOpacity3.setBackground(new java.awt.Color(60, 76, 143));

        rSLabelIcon16.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon16.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.RESTORE);
        rSLabelIcon16.setName(""); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("/");

        rSLabelIcon19.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon19.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Modificar Productos");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Menú Principal");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("/");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Modúlo Productos");

        rSLabelIcon20.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon20.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SHOPPING_CART);
        rSLabelIcon20.setName(""); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("/");

        rSLabelIcon21.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon21.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
        rSLabelIcon21.setName(""); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Listado de Productos");

        rSPanelOpacity3.setLayer(rSLabelIcon16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(rSLabelIcon19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel26, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel28, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(rSLabelIcon20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel30, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(rSLabelIcon21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity3.setLayer(jLabel34, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout rSPanelOpacity3Layout = new javax.swing.GroupLayout(rSPanelOpacity3);
        rSPanelOpacity3.setLayout(rSPanelOpacity3Layout);
        rSPanelOpacity3Layout.setHorizontalGroup(
            rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(rSLabelIcon19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel26)
                .addGap(11, 11, 11)
                .addComponent(jLabel24)
                .addGap(12, 12, 12)
                .addComponent(rSLabelIcon20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addGap(25, 25, 25)
                .addComponent(jLabel27)
                .addGap(13, 13, 13)
                .addComponent(rSLabelIcon21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSLabelIcon16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rSPanelOpacity3Layout.setVerticalGroup(
            rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSLabelIcon20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(153, 0, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Nombre del Producto:");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 133, 180, 40));

        pre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pre.setPlaceholder("Ingresar Precio del producto");
        pre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preActionPerformed(evt);
            }
        });
        pre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                preKeyReleased(evt);
            }
        });
        jPanel5.add(pre, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 286, 380, 40));

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
                .addComponent(JCodigoDisponible1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );
        rSPanelCircle2Layout.setVerticalGroup(
            rSPanelCircle2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelCircle2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JCodigoDisponible1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.add(rSPanelCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 70, 70));

        precio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        precio.setForeground(new java.awt.Color(153, 0, 255));
        precio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio.setText("Precio");
        jPanel5.add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 295, 159, 40));

        NombreP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NombreP.setPlaceholder("Ingrese los nombres del producto");
        NombreP.setSoloLetras(true);
        NombreP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombrePActionPerformed(evt);
            }
        });
        NombreP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NombrePKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombrePKeyTyped(evt);
            }
        });
        jPanel5.add(NombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 135, 359, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 0, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("CodProducto:");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 170, 70));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(153, 0, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Categoría:");
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 180, 40));

        JCategoria.setColorArrow(new java.awt.Color(102, 0, 255));
        JCategoria.setColorFondo(new java.awt.Color(60, 76, 143));
        JCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JCategoriaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JCategoriaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JCategoriaMouseExited(evt);
            }
        });
        JCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCategoriaActionPerformed(evt);
            }
        });
        jPanel5.add(JCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, 190, 30));

        Estado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Estado.setForeground(new java.awt.Color(153, 0, 255));
        Estado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Estado.setText("Nuevo Proveedor");
        jPanel5.add(Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, 130, 40));

        JProveedores.setColorArrow(new java.awt.Color(102, 0, 255));
        JProveedores.setColorFondo(new java.awt.Color(60, 76, 143));
        JProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JProveedoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JProveedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JProveedoresMouseExited(evt);
            }
        });
        JProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JProveedoresActionPerformed(evt);
            }
        });
        jPanel5.add(JProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, 190, 30));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 0, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Descripción:");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 215, 159, 40));

        Descrip1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Descrip1.setPlaceholder("Ingresar descripcion del Producto");
        Descrip1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Descrip1ActionPerformed(evt);
            }
        });
        Descrip1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Descrip1KeyTyped(evt);
            }
        });
        jPanel5.add(Descrip1, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 218, 380, 40));

        actualp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        actualp.setForeground(new java.awt.Color(153, 0, 255));
        actualp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actualp.setText("Proveedores");
        jPanel5.add(actualp, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 220, 120, 40));

        Estado1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Estado1.setForeground(new java.awt.Color(153, 0, 255));
        Estado1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Estado1.setText("Actual Proveedor:");
        jPanel5.add(Estado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, 130, 40));

        categoria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        categoria.setForeground(new java.awt.Color(153, 0, 255));
        categoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        categoria.setText("Actual Categoría:");
        jPanel5.add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 130, 40));

        actualCat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        actualCat.setForeground(new java.awt.Color(153, 0, 255));
        actualCat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actualCat.setText("Categoría");
        jPanel5.add(actualCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 120, 40));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 0, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Estado");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, 60, 30));

        JEstado.setColorArrow(new java.awt.Color(102, 0, 255));
        JEstado.setColorFondo(new java.awt.Color(60, 76, 143));
        JEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JEstadoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JEstadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JEstadoMouseExited(evt);
            }
        });
        JEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JEstadoActionPerformed(evt);
            }
        });
        jPanel5.add(JEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, 180, 30));

        Estado2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Estado2.setForeground(new java.awt.Color(153, 0, 255));
        Estado2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Estado2.setText("Actual Estado:");
        jPanel5.add(Estado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 130, 40));

        actuale.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        actuale.setForeground(new java.awt.Color(153, 0, 255));
        actuale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actuale.setText("estado");
        jPanel5.add(actuale, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 120, 40));

        avisoT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        avisoT.setForeground(new java.awt.Color(255, 0, 0));
        avisoT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        avisoT.setText("*Formato invalído*");
        jPanel5.add(avisoT, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 180, -1));

        avisoT1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        avisoT1.setForeground(new java.awt.Color(255, 0, 0));
        avisoT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        avisoT1.setText("*Formato invalído*");
        jPanel5.add(avisoT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 180, -1));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelOpacity3, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(rSPanelOpacity3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(82, 82, 82))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(rSButtonIcon_new9, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dashboardviewLayout.setVerticalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(rSButtonIcon_new9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );

        jPanel1.add(dashboardview, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 47, -1, 665));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1362, 712));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonIconOne3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne3ActionPerformed

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

    private void linesetting12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_linesetting12MouseClicked

    private void rSButtonIcon_new3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new3ActionPerformed
        // TODO add your handling code here:
      Productos p= new Productos();
        p.setUsuario(usuario);
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new3ActionPerformed

    private void rSButtonIcon_new9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new9ActionPerformed
     if(validar()==true){
      ConfirmacionModificarProductos cmp = new ConfirmacionModificarProductos();
      cmp.setmp(this);
      cmp.setTipo("MPRODUCTO");
      cmp.setVisible(true);
         }else{

            JOptionPane.showMessageDialog(this, "POR FAVOR VALIDE LA INFORMACION");
        }
    }//GEN-LAST:event_rSButtonIcon_new9ActionPerformed

    private void JCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCategoriaActionPerformed

    private void JCategoriaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCategoriaMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JCategoriaMouseExited

    private void JCategoriaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCategoriaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JCategoriaMouseEntered

    private void JCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCategoriaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JCategoriaMouseClicked

    private void NombrePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombrePActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombrePActionPerformed

    private void preActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_preActionPerformed

    private void JProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JProveedoresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JProveedoresMouseClicked

    private void JProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JProveedoresMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JProveedoresMouseEntered

    private void JProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JProveedoresMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JProveedoresMouseExited

    private void JProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JProveedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JProveedoresActionPerformed

    private void Descrip1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Descrip1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Descrip1ActionPerformed

    private void JEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JEstadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JEstadoMouseClicked

    private void JEstadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JEstadoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JEstadoMouseEntered

    private void JEstadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JEstadoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JEstadoMouseExited

    private void JEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JEstadoActionPerformed

    private void preKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_preKeyReleased
         if (Validartarifa(pre.getText())){
              avisoT.setVisible(false);
             //  JOptionPane.showMessageDialog(this, "El correo ingresado es valido");  
        }
          else{
                    avisoT.setVisible(true);
              //JOptionPane.showMessageDialog(this, "El correo ingresado no es valido"); 
          }
    }//GEN-LAST:event_preKeyReleased

    private void NombrePKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombrePKeyTyped

        char caracter=evt.getKeyChar();
          if(Character.isLowerCase(caracter)){
              
            evt.setKeyChar(Character.toUpperCase(caracter));
      }
        
        if (NombreP.getText().trim().length() == 30) {
        evt.consume();
       }
    }//GEN-LAST:event_NombrePKeyTyped

    private void Descrip1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Descrip1KeyTyped

 if (Descrip1.getText().trim().length() == 30) {
        evt.consume();
       }        // TODO add your handling code here:
    }//GEN-LAST:event_Descrip1KeyTyped

    private void NombrePKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombrePKeyReleased
         if (validarNombre(NombreP.getText())){
            avisoT1.setVisible(false);

        }
        else{
            avisoT1.setVisible(true);

        }
    }//GEN-LAST:event_NombrePKeyReleased
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
            java.util.logging.Logger.getLogger(ActualizarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ActualizarProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSMTextFull Descrip1;
    private javax.swing.JLabel Estado;
    private javax.swing.JLabel Estado1;
    private javax.swing.JLabel Estado2;
    private javax.swing.JPanel Header;
    private rojerusan.RSComboMetro JCategoria;
    private javax.swing.JLabel JCodigoDisponible1;
    private rojerusan.RSComboMetro JEstado;
    private rojerusan.RSComboMetro JProveedores;
    private javax.swing.JPanel MenuIcon;
    private rojeru_san.RSMTextFull NombreP;
    private javax.swing.JLabel actualCat;
    private javax.swing.JLabel actuale;
    private javax.swing.JLabel actualp;
    private javax.swing.JLabel avisoT;
    private javax.swing.JLabel avisoT1;
    private javax.swing.JLabel categoria;
    private javax.swing.JPanel dashboardview;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
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
    private rojeru_san.RSMTextFull pre;
    private javax.swing.JLabel precio;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne3;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new3;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new9;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rojerusan.RSLabelIcon rSLabelIcon1;
    private rojerusan.RSLabelIcon rSLabelIcon10;
    private rojerusan.RSLabelIcon rSLabelIcon11;
    private rojerusan.RSLabelIcon rSLabelIcon16;
    private rojerusan.RSLabelIcon rSLabelIcon19;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private rojerusan.RSLabelIcon rSLabelIcon20;
    private rojerusan.RSLabelIcon rSLabelIcon21;
    private rojerusan.RSLabelIcon rSLabelIcon3;
    private rojerusan.RSLabelIcon rSLabelIcon4;
    private rojerusan.RSLabelIcon rSLabelIcon5;
    private rojerusan.RSLabelIcon rSLabelIcon7;
    private rojerusan.RSLabelIcon rSLabelIcon9;
    private rojeru_san.rspanel.RSPanelCircle rSPanelCircle2;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity3;
    // End of variables declaration//GEN-END:variables

   
}
