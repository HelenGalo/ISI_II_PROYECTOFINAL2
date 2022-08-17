/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto;
import Atxy2k.CustomTextField.RestrictedTextField;
import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.ColorFondo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.chrono.IsoEra;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Edwin Rafael
 */
public class Compras extends javax.swing.JFrame {
    boolean a = true;
    
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();
    DefaultTableModel modelo1;  
    DefaultTableModel modelo;
    String usuario;
    int codigcliente; 
    int codigvendedor;
    int idorden=0;
    String codigop;
    String codigop1;
    RestrictedTextField r;
    String valorsiete="";
    int seleccion1;
     int seleccion2;



    public void setCodigvendedor(int codigvendedor) {
        this.codigvendedor = codigvendedor;
    }

    public void setCodigoCliente(int codigcliente) {
        this.codigcliente = codigcliente;
    }
    
    
    
    

    public void setUsuario(String usuario) {
        this.usuario = usuario;
     
    }
    /**
     * Creates new form Menu
     */
    public Compras() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        buscarUltimoId();
        modelo1=(DefaultTableModel) JTableBancos.getModel();
        modelo=(DefaultTableModel) JTableBancos1.getModel();
        setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
        
    }
    
    
    
    public void buscarUltimoId(){
          String SQL = "SELECT v.IdOrden FROM Ventas v WHERE v.IdOrden=(SELECT max(IdOrden) FROM Ventas)";
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idorden = rs.getInt("v.IdOrden");
               
                
            }

            
 
            idorden = idorden +1;
    
            jLabel33.setText(String.valueOf(idorden));
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    public int obteneridalmacendesucursal(){
        int idalmacen=0;
        String sql = "Select s.IdAlmacen From Sucursales s\n" +
                    "INNER JOIN Empleados e ON e.IdSucursal = s.IdSucursal\n" +
                    "INNER JOIN Usuarios u ON u.IdEmpleado = e.IdEmpleado\n" +
                    "WHERE u.Usuario='"+jLabel45.getText()+"';";
        
        
           try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                idalmacen = rs.getInt("s.IdAlmacen");
               
                
            }
           }catch(SQLException e){
                 System.out.println("Error "+e.getMessage());
        
            }
           
           return idalmacen;
            
    }
    
   
    
      public void buscarProductoPorId(int idproducto){
        String[] registros = new String[4];
          System.out.println("ID EN ALMACEN "+obteneridalmacendesucursal());
          
          String SQL = "SELECT p.Nombre, p.Precio, ifnull(ExistenciaActual,0) as 'Ex' FROM Productos p\n"
                  +"INNER JOIN AlmacenProducto ap ON ap.IdProducto = p.IdProducto\n"
                  +"WHERE p.IdProducto="+idproducto+" AND ap.IdAlmacen="+obteneridalmacendesucursal();
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                DecimalFormat formato = new DecimalFormat("##,###.00");
                registros[0] = String.valueOf(idproducto);
                registros[1] = rs.getString("p.Nombre");
                registros[2] = formato.format(rs.getFloat("p.Precio"));
                registros[3] = rs.getString("Ex");
                modelo.addRow(registros);
            }

            JTableBancos1.setModel(modelo);
 
         
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
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
    
    
    public String obtenerdescuento(int idproducto){
         String descuento="0.00";
         String SQL = "SELECT ifnull(d.Descuento,0.00) as 'Des' FROM DetalleDescuentoProducto d Where d.IdProducto="+idproducto;
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                  descuento= rs.getString("Des");
            }
        }catch(SQLException e){
                 System.out.println("Error "+e.getMessage());
        
            }
        
        return descuento;
        
    }
    
    
    public void agregarproductosorden(int idproducto){
        int validarexistenciaorden=0;
        boolean estadoproducto=false;
        int posicion=0;
        int nuevacantidad=0;
        float nuevosubtotal=0.00f;
        float nuevodescuento=0.00f;
        
        if(modelo1.getRowCount()==0){
            String[] registros = new String[6];
            String SQL = "SELECT p.Nombre, p.Precio FROM Productos p\n"
                  +"LEFT JOIN AlmacenProducto ap ON ap.IdProducto = p.IdProducto\n"
                  +"WHERE p.IdProducto="+idproducto+" AND ap.IdAlmacen="+obteneridalmacendesucursal();;
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                registros[0] = String.valueOf(idproducto);
                registros[1] = rs.getString("p.Nombre");
                registros[2] = rs.getString("p.Precio");
                registros[3] = "1";
                registros[4] = obtenerdescuento(idproducto);
                registros[5] = String.valueOf((Float.valueOf(registros[2])*Float.valueOf(registros[3]))-Float.valueOf(registros[4]));
                modelo1.addRow(registros);
            }
        
     
            }catch(SQLException e){
                 System.out.println("Error "+e.getMessage());
        
            }
            
        
        }else{
           for(int i=0; i<modelo1.getRowCount();i++){
               validarexistenciaorden=Integer.valueOf(modelo1.getValueAt(i, 0).toString());
               int valortabla1=Integer.valueOf(modelo.getValueAt(0, 0).toString());
           if(validarexistenciaorden==valortabla1){
               
               estadoproducto=true;
               posicion=i;
            }
            }
           
            if(estadoproducto==true){
            nuevacantidad=Integer.valueOf(modelo1.getValueAt(posicion, 3).toString())+1;
            
            modelo1.setValueAt(nuevacantidad, posicion, 3);
            nuevodescuento=(Float.valueOf(modelo1.getValueAt(posicion, 3).toString())*Float.valueOf(obtenerdescuento(idproducto)));
            modelo1.setValueAt(nuevodescuento, posicion, 4);
            nuevosubtotal=(Float.valueOf(modelo1.getValueAt(posicion, 3).toString())*Float.valueOf(modelo1.getValueAt(posicion, 2).toString()))-Float.valueOf(modelo1.getValueAt(posicion, 4).toString());
            modelo1.setValueAt(nuevosubtotal, posicion, 5);
            }else{
               String[] registros = new String[6];
               String SQL = "SELECT p.Nombre, p.Precio   FROM Productos p\n"
                  +"LEFT JOIN AlmacenProducto ap ON ap.IdProducto = p.IdProducto\n"
                  +"WHERE p.IdProducto="+idproducto+" AND ap.IdAlmacen="+obteneridalmacendesucursal();
          
          
                try {
                        Statement st = (Statement) con.createStatement();
                        ResultSet rs = st.executeQuery(SQL);

                        while (rs.next()) {
                            DecimalFormat formato = new DecimalFormat("##,###.00");
                            registros[0] = String.valueOf(idproducto);
                            registros[1] = rs.getString("p.Nombre");
                            registros[2] = formato.format(rs.getFloat("p.Precio"));
                            registros[3] = "1";
                            registros[4] = "0.00";
                            registros[5] = String.valueOf((Float.valueOf(registros[2])*Float.valueOf(registros[3]))-Float.valueOf(registros[4]));
                            modelo1.addRow(registros);
                        }

                        
                    }catch(SQLException e){
                        System.out.println("Error "+e.getMessage());

                    }
                
            }
           
        }
       
        
        
        
        
        
       
    }
    
    
    public void total(){
        if(modelo1.getRowCount()>0){
            float total=0.00f;
            total = Float.valueOf(jLabel18.getText()) + Float.valueOf(jLabel23.getText())-Float.valueOf(jLabel28.getText());
            jLabel22.setText(String.valueOf(total));
        }else{
            jLabel22.setText("0.00");
        }
       
                
    }
    
    
    
    public void actualizarEnter(){
     
        float nuevosubtotal=0.00f;
        float nuevodescuento=0.00f;
        
        nuevodescuento=Float.valueOf(modelo1.getValueAt(seleccion1, 3).toString())*Float.valueOf(String.valueOf(obtenerdescuento(Integer.valueOf(codigop1))));
        modelo1.setValueAt(nuevodescuento, seleccion1, 4);
        nuevosubtotal=(Float.valueOf(modelo1.getValueAt(seleccion1, 3).toString())*Float.valueOf(modelo1.getValueAt(seleccion1, 2).toString()))-Float.valueOf(modelo1.getValueAt(seleccion1, 4).toString());
        modelo1.setValueAt(nuevosubtotal, seleccion1, 5);
        
        
    
    }
    
    
    public void sumarsubtotal(){
        if(modelo1.getRowCount()>0){
        float sumador=0.00f;
        float subtotal=0.00f;
        for(int i=0;i<modelo1.getRowCount();i++){
            sumador=sumador+(Float.valueOf(modelo1.getValueAt(i, 2).toString())*Float.valueOf(modelo1.getValueAt(i, 3).toString()));
        }
        
        subtotal= (float) (sumador-(sumador*0.15));
        
        jLabel23.setText(String.valueOf(subtotal));
        }else{
            jLabel23.setText("");
        }
        
    }
    
    public void regresar(){
        Menu menu = new Menu();
        menu.setUsuario(usuario);
        menu.setVisible(true);
        this.dispose();
    }
    
    
    public void sumarisv(){
        float sumador=0.00f;
        float isv=0.00f;
        for(int i=0;i<modelo1.getRowCount();i++){
            sumador=sumador+(Float.valueOf(modelo1.getValueAt(i, 2).toString())*Float.valueOf(modelo1.getValueAt(i, 3).toString()));
        }
        
        isv= (float) (sumador*0.15);
        
        jLabel18.setText(String.valueOf(isv));
    }
    
    
    public void sumardescuento(){
        float sumador=0.00f;
        float descuento=0.00f;
        for(int i=0;i<modelo1.getRowCount();i++){
            sumador=sumador+Float.valueOf(modelo1.getValueAt(i, 4).toString());
        }
        
        descuento= sumador;
        
        jLabel28.setText(String.valueOf(descuento));
    }
    
    
    
    public void sumarcantidadproductos(){
        int sumador=0;
        for(int i=0;i<modelo1.getRowCount();i++){
            sumador=sumador+Integer.valueOf(modelo1.getValueAt(i, 3).toString());
        }
        jLabel8.setText(String.valueOf(sumador));
    }
    
    
     public void restarcantidadproductos(int codigproducto){
        int corredor=0;
        int posicion=0;
        int nuevacantidad=0;
        float nuevosubtotal=0.00f;
        float nuevodescuento=0.00f;
        for(int i=0; i<modelo1.getRowCount();i++){
               corredor=Integer.valueOf(modelo1.getValueAt(i, 0).toString());
           if(codigproducto==corredor){
               posicion=i;
            }
            }
          
            nuevacantidad=Integer.valueOf(modelo1.getValueAt(posicion, 3).toString())-1;
            
            modelo1.setValueAt(nuevacantidad, posicion, 3);
            nuevodescuento=(Float.valueOf(modelo1.getValueAt(posicion, 3).toString())*Float.valueOf(obtenerdescuento(codigproducto)));
            modelo1.setValueAt(nuevodescuento, posicion, 4);
            nuevosubtotal=(Float.valueOf(modelo1.getValueAt(posicion, 3).toString())*Float.valueOf(modelo1.getValueAt(posicion, 2).toString()))-Float.valueOf(modelo1.getValueAt(posicion, 4).toString());
            modelo1.setValueAt(nuevosubtotal, posicion, 5);
          
     }
        
           
           
    
    
    public void QuitarproductoId(int codigproducto){
        int corredor=0;
        int posicion=0;
        for(int i=0; i<modelo1.getRowCount();i++){
               corredor=Integer.valueOf(modelo1.getValueAt(i, 0).toString());
           if(codigproducto==corredor){
               posicion=i;
            }
            }
        modelo1.removeRow(posicion);
     
    }
    
    
    public void limpiartabla(){
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancos1.getModel();
        while (modelo.getRowCount() > 0)
        {
        modelo.removeRow(0);
        }
     
    }
    
    public void limpiartabla2(){
        DefaultTableModel modelo =  (DefaultTableModel) JTableBancos.getModel();
        while (modelo.getRowCount() > 0)
        {
        modelo.removeRow(0);
        }
        jLabel23.setText("0.00");
        jLabel18.setText("0.00");
        jLabel28.setText("0.00");
        jLabel22.setText("0.00");
        jLabel8.setText("0");
     
    }
    
    public void iniciarvendedor(){
        String nombrevendedor="";
        String SQL = "SELECT e.PrimerNombre, e.PrimerApellido FROM Empleados e Where e.IdEmpleado="+codigvendedor;
        
        
 
        try {
          Statement  st = (Statement) con.createStatement();
          ResultSet rs = st.executeQuery(SQL);
        
        
         while (rs.next()) {
           
                nombrevendedor = rs.getString("e.PrimerNombre")+" "+rs.getString("e.PrimerApellido");
            }
        } catch (SQLException ex) {
            System.out.println("Error "+ ex.getMessage());
        }
        
         
        jLabel35.setText(nombrevendedor);
        jLabel36.setText(String.valueOf(codigvendedor));
         
    }
    
     public void iniciarcajero(){
         String nombrecajero="";
        String SQL3 = "SELECT e.PrimerNombre, e.PrimerApellido FROM Empleados e\n"
                   +"INNER JOIN Usuarios u ON u.IdEmpleado = e.IdEmpleado\n"+
                 "Where u.Usuario='"+usuario+"';";
        
        
        try {
           
            Statement st3 = (Statement) con.createStatement();
            ResultSet rs3 = st3.executeQuery(SQL3);
 
            
            while (rs3.next()) {
           
                nombrecajero = rs3.getString("e.PrimerNombre")+" "+rs3.getString("e.PrimerApellido");;
            }
        
        }catch(SQLException e){
            System.out.println("Error "+ e.getMessage());
        }
        
        
        
        jLabel44.setText(nombrecajero);
        jLabel45.setText(usuario);
         
    }
    
    
    
    
    
     public void iniciarcliente(){
        String nombrecliente="";
        String SQL1 = "SELECT c.Nombres, c.Apellidos FROM Clientes c Where c.IdCliente="+codigcliente;
        
        
     
        try {
           Statement st1 = (Statement) con.createStatement();
           ResultSet rs1 = st1.executeQuery(SQL1);
        
         while (rs1.next()) {
           
                nombrecliente = rs1.getString("c.Nombres")+" "+rs1.getString("c.Apellidos");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error "+ ex.getMessage());
        }
        
        jLabel38.setText(nombrecliente);
        jLabel39.setText(String.valueOf(codigcliente));
         
    }
     
     
    
     public void iniciarcaja(){
        int codigocaja=0;
              
        String SQL2 = "SELECT c.IdCaja FROM Caja c\n"
                 +"INNER JOIN Usuarios u ON u.IdUsuario = c.IdUsuario\n"+
                 "Where u.Usuario='"+usuario+"';";
        
        
     
        try {
             
            Statement st2 = (Statement) con.createStatement();
            ResultSet rs2 = st2.executeQuery(SQL2);;
        
            while (rs2.next()) {
           
                codigocaja = rs2.getInt("c.IdCaja");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error "+ ex.getMessage());
        }
          jLabel42.setText(String.valueOf(codigocaja));
    }
    
    
    public void iniciardatos(){
        
        
       iniciarcajero();
       iniciarcaja();
       iniciarcliente();
       iniciarvendedor();
       
        
       
        
       
        
        
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanelVector1 = new rojeru_san.rspanel.RSPanelVector();
        jPanel1 = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        iconminmaxclose = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        linesetting3 = new javax.swing.JPanel();
        rSButtonIconOne3 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne4 = new RSMaterialComponent.RSButtonIconOne();
        linesetting4 = new javax.swing.JPanel();
        rSButtonIconOne5 = new RSMaterialComponent.RSButtonIconOne();
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
        rSPanelShadow1 = new necesario.RSPanelShadow();
        rSPanelsSlider2 = new rojerusan.RSPanelsSlider();
        rSPanelsSlider3 = new rojerusan.RSPanelsSlider();
        rSPanel1 = new necesario.RSPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableBancos = new rojerusan.RSTableMetro1();
        jLabel8 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        rSPanelGradiente1 = new rspanelgradiente.RSPanelGradiente();
        jLabel9 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        jLabel30 = new javax.swing.JLabel();
        rSPanelShadow2 = new RSMaterialComponent.RSPanelShadow();
        rSPanel2 = new necesario.RSPanel();
        JTextbuscar = new RSMaterialComponent.RSTextFieldIconUno();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTableBancos1 = new rojerusan.RSTableMetro1();
        rSButtonHover1 = new rojeru_san.complementos.RSButtonHover();
        rSButtonIcon_new12 = new newscomponents.RSButtonIcon_new();
        rSButtonHover4 = new rojeru_san.complementos.RSButtonHover();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        rSPanelForma3 = new rojeru_san.rspanel.RSPanelForma();
        jLabel50 = new javax.swing.JLabel();
        rSRadioButton1 = new rojerusan.RSRadioButton();
        rSRadioButton2 = new rojerusan.RSRadioButton();
        jLabel51 = new javax.swing.JLabel();
        JTextbuscar1 = new RSMaterialComponent.RSTextFieldIconUno();
        rSButtonIcon_new18 = new newscomponents.RSButtonIcon_new();
        rSRadioButton3 = new rojerusan.RSRadioButton();
        rSLabelFecha1 = new rojeru_san.RSLabelFecha();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        JEstado = new rojerusan.RSComboMetro();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel31 = new javax.swing.JLabel();

        javax.swing.GroupLayout rSPanelVector1Layout = new javax.swing.GroupLayout(rSPanelVector1);
        rSPanelVector1.setLayout(rSPanelVector1Layout);
        rSPanelVector1Layout.setHorizontalGroup(
            rSPanelVector1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        rSPanelVector1Layout.setVerticalGroup(
            rSPanelVector1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
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
            .addGap(0, 50, Short.MAX_VALUE)
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
                .addComponent(rSButtonIconOne3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        linesetting3Layout.setVerticalGroup(
            linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(linesetting3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        linesetting4.setBackground(new java.awt.Color(20, 101, 187));
        linesetting4.setPreferredSize(new java.awt.Dimension(50, 10));

        rSButtonIconOne5.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIconOne5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REMOVE);
        rSButtonIconOne5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout linesetting4Layout = new javax.swing.GroupLayout(linesetting4);
        linesetting4.setLayout(linesetting4Layout);
        linesetting4Layout.setHorizontalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linesetting4Layout.createSequentialGroup()
                .addContainerGap(680, Short.MAX_VALUE)
                .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        linesetting4Layout.setVerticalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linesetting4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSButtonIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        linesetting5.setBackground(new java.awt.Color(20, 101, 187));
        linesetting5.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesetting5Layout = new javax.swing.GroupLayout(linesetting5);
        linesetting5.setLayout(linesetting5Layout);
        linesetting5Layout.setHorizontalGroup(
            linesetting5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
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
                .addComponent(linesetting5, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linesetting4, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
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
        dashboardview.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelGradiente3.setColorPrimario(new java.awt.Color(51, 153, 255));
        rSPanelGradiente3.setColorSecundario(new java.awt.Color(20, 101, 187));
        rSPanelGradiente3.setGradiente(rspanelgradiente.RSPanelGradiente.Gradiente.VERTICAL);

        jLabel34.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Gerente Comercial");

        rSLabelIcon13.setBackground(new java.awt.Color(255, 255, 255));
        rSLabelIcon13.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon13.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        rSLabelIcon13.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);

        jLabel35.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Vendedor");

        jLabel36.setFont(new java.awt.Font("Franklin Gothic Book", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel17.setFont(new java.awt.Font("Franklin Gothic Book", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Compras");

        jLabel37.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Proveedor");

        rSLabelIcon14.setBackground(new java.awt.Color(255, 255, 255));
        rSLabelIcon14.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon14.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        rSLabelIcon14.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCOUNT_CIRCLE);

        jLabel38.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Proveedor");

        jLabel39.setFont(new java.awt.Font("Franklin Gothic Book", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Provvedor");

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

        javax.swing.GroupLayout rSPanelGradiente3Layout = new javax.swing.GroupLayout(rSPanelGradiente3);
        rSPanelGradiente3.setLayout(rSPanelGradiente3Layout);
        rSPanelGradiente3Layout.setHorizontalGroup(
            rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(rSLabelIcon16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLabel34)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rSPanelGradiente3Layout.setVerticalGroup(
            rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSLabelIcon16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dashboardview.add(rSPanelGradiente3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 108));

        rSPanelShadow1.setBackground(new java.awt.Color(242, 244, 242));
        rSPanelShadow1.add(rSPanelsSlider2, java.awt.BorderLayout.LINE_START);

        rSPanelsSlider3.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelsSlider3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        rSPanelShadow1.add(rSPanelsSlider3, java.awt.BorderLayout.CENTER);

        rSPanel1.setBackground(new java.awt.Color(242, 244, 242));
        rSPanel1.setColorBackground(new java.awt.Color(242, 244, 242));

        JTableBancos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CódigoProducto", "Nombre", "Precio", "Cantidad", "Proveedor", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("0");

        jLabel32.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 0, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("N. COMPRA:");

        jLabel33.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 0, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("0");

        rSPanelGradiente1.setColorPrimario(new java.awt.Color(51, 153, 255));
        rSPanelGradiente1.setColorSecundario(new java.awt.Color(20, 101, 187));

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("ISV:");

        jLabel21.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("L.");

        jLabel28.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("0.00");

        jLabel27.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("L.");

        jLabel24.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("L.");

        jLabel23.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("0.00");

        jLabel19.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Descuento:");

        jLabel18.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("0.00");

        jLabel26.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Subtotal:");

        jLabel20.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Total:");

        jLabel25.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("L.");

        jLabel13.setFont(new java.awt.Font("Franklin Gothic Book", 2, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Productos con ISV incluido");

        jLabel22.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("0.00");

        javax.swing.GroupLayout rSPanelGradiente1Layout = new javax.swing.GroupLayout(rSPanelGradiente1);
        rSPanelGradiente1.setLayout(rSPanelGradiente1Layout);
        rSPanelGradiente1Layout.setHorizontalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13))
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                .addGap(291, 291, 291)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                .addGap(281, 281, 281)
                                .addComponent(jLabel25))
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                .addGap(301, 301, 301)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        rSPanelGradiente1Layout.setVerticalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jLabel29.setFont(new java.awt.Font("Franklin Gothic Book", 3, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 0, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("DETALLE DE COMPRA");

        jLabel30.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 0, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Total Productos:");

        javax.swing.GroupLayout rSPanel1Layout = new javax.swing.GroupLayout(rSPanel1);
        rSPanel1.setLayout(rSPanel1Layout);
        rSPanel1Layout.setHorizontalGroup(
            rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanel1Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanel1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanel1Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(rSPanelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rSPanel1Layout.setVerticalGroup(
            rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSPanelGradiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        rSPanelShadow1.add(rSPanel1, java.awt.BorderLayout.PAGE_START);

        dashboardview.add(rSPanelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 120, 640, -1));

        rSPanel2.setBackground(new java.awt.Color(242, 244, 242));
        rSPanel2.setForeground(new java.awt.Color(0, 102, 102));
        rSPanel2.setColorBackground(new java.awt.Color(242, 244, 242));
        rSPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTextbuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTextbuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        JTextbuscar.setPlaceholder("Buscar por codigo");
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
        rSPanel2.add(JTextbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 190, -1));

        jLabel12.setFont(new java.awt.Font("Franklin Gothic Book", 2, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 0, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Productos");
        rSPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 110, 40));

        JTableBancos1.setForeground(new java.awt.Color(204, 204, 204));
        JTableBancos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CódigoProducto", "Nombre", "Precio", "Inventario D."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTableBancos1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        JTableBancos1.setAutoscrolls(false);
        JTableBancos1.setBackgoundHead(new java.awt.Color(255, 255, 255));
        JTableBancos1.setBackgoundHover(new java.awt.Color(60, 76, 143));
        JTableBancos1.setBorderHead(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 102, 255)));
        JTableBancos1.setColorBorderHead(new java.awt.Color(204, 204, 204));
        JTableBancos1.setColorBorderRows(new java.awt.Color(153, 153, 153));
        JTableBancos1.setColorSecondary(new java.awt.Color(255, 255, 255));
        JTableBancos1.setEffectHover(true);
        JTableBancos1.setFocusable(false);
        JTableBancos1.setForegroundHead(new java.awt.Color(0, 51, 255));
        JTableBancos1.setGridColor(new java.awt.Color(153, 153, 153));
        JTableBancos1.setHighHead(50);
        JTableBancos1.setInheritsPopupMenu(true);
        JTableBancos1.setOpaque(false);
        JTableBancos1.setRowHeight(50);
        JTableBancos1.setShowHorizontalLines(true);
        JTableBancos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableBancos1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(JTableBancos1);

        rSPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 540, 110));

        rSButtonHover1.setBackground(new java.awt.Color(255, 0, 102));
        rSButtonHover1.setText("-");
        rSButtonHover1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonHover1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 50, -1));

        rSButtonIcon_new12.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIcon_new12.setText("Ver Todos");
        rSButtonIcon_new12.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.GRID_ON);
        rSButtonIcon_new12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new12ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonIcon_new12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 150, -1));

        rSButtonHover4.setText("+");
        rSButtonHover4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rSButtonHover4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover4ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonHover4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 50, -1));

        jLabel14.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 0, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Observaciones");
        rSPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 190, 30));

        jLabel15.setFont(new java.awt.Font("Franklin Gothic Book", 2, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 0, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Acciones");
        rSPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 110, 40));

        rSPanelForma3.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelForma3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel50.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(102, 0, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Forma de Pago");
        rSPanelForma3.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 11, 130, 20));

        rSRadioButton1.setText("TARJETA");
        rSRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rSRadioButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButton1ActionPerformed(evt);
            }
        });
        rSPanelForma3.add(rSRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 110, -1));

        rSRadioButton2.setText("EFECTIVO");
        rSRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rSRadioButton2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButton2ActionPerformed(evt);
            }
        });
        rSPanelForma3.add(rSRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 38, 110, -1));

        jLabel51.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(102, 0, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Ingrese la cantidad de efectivo recibido:");
        rSPanelForma3.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 370, 20));

        JTextbuscar1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTextbuscar1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        JTextbuscar1.setPlaceholder("Efectivo recibido");
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
        rSPanelForma3.add(JTextbuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 188, -1));

        rSButtonIcon_new18.setBackground(new java.awt.Color(0, 102, 204));
        rSButtonIcon_new18.setText("Pagar");
        rSButtonIcon_new18.setAlignmentX(0.5F);
        rSButtonIcon_new18.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new18.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEND);
        rSButtonIcon_new18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new18ActionPerformed(evt);
            }
        });
        rSPanelForma3.add(rSButtonIcon_new18, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 108, -1));

        rSRadioButton3.setText("MIXTO");
        rSRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rSRadioButton3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButton3ActionPerformed(evt);
            }
        });
        rSPanelForma3.add(rSRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 100, -1));

        rSPanel2.add(rSPanelForma3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 350, 200));

        rSLabelFecha1.setFormato("yyyy/MM/dd");
        rSPanel2.add(rSLabelFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 80, 20));
        rSPanel2.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 90, 40));

        jLabel47.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 0, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Fecha de Compra:");
        rSPanel2.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, 20));

        jLabel48.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 0, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("Hora:");
        rSPanel2.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 40, 20));

        jLabel16.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 0, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Buscador");
        rSPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 100, 40));

        JEstado.setBorder(null);
        JEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JEstadoActionPerformed(evt);
            }
        });
        rSPanel2.add(JEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 180, 30));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        rSPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 290, 60));

        jLabel31.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 0, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Forma de Envio");
        rSPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 120, 30));

        rSPanelShadow2.add(rSPanel2, java.awt.BorderLayout.CENTER);

        dashboardview.add(rSPanelShadow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 110, 730, 493));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 1336, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(dashboardview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(dashboardview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonIconOne3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne3ActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }//GEN-LAST:event_rSButtonIconOne3ActionPerformed

    private void rSButtonIconOne4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne4ActionPerformed
        try {
            // TODO add your handling code here:
            con.close();
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_rSButtonIconOne4ActionPerformed

    private void rSButtonIconOne5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne5ActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_rSButtonIconOne5ActionPerformed

    private void rSButtonHover4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover4ActionPerformed
        // TODO add your handling code here:
       
        if(codigop!=null){
            if(Integer.valueOf(JTableBancos1.getValueAt(seleccion2,3).toString())>0){
                if(JTableBancos.getRowCount()==0){
                    agregarproductosorden(Integer.valueOf(codigop));
                    sumarcantidadproductos();
                    sumarsubtotal();
                    sumardescuento();
                    sumarisv();
                    total();
                
                    
                }else{
                    if(Integer.valueOf(JTableBancos.getValueAt(seleccion1,3).toString())<Integer.valueOf(JTableBancos1.getValueAt(seleccion2,3).toString())){
                    agregarproductosorden(Integer.valueOf(codigop));
                    sumarcantidadproductos();
                    sumarsubtotal();
                    sumardescuento();
                    sumarisv();
                    total();
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Maxima cantidad de productos disponibles");
                    }
                }
                
                
               
            }else{
                JOptionPane.showMessageDialog(rootPane, "No hay productos en existencia para el producto seleccionado");
            }
            
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione el producto en la tabla");
        }
        
    }//GEN-LAST:event_rSButtonHover4ActionPerformed

    private void rSButtonIcon_new12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIcon_new12ActionPerformed

    private void JTableBancos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableBancos1MouseClicked
        // TODO add your handling code here:
        seleccion2 = JTableBancos1.rowAtPoint(evt.getPoint());
        codigop =  JTableBancos1.getValueAt(seleccion2,0 ).toString();
 
    }//GEN-LAST:event_JTableBancos1MouseClicked

    private void JTableBancosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableBancosMouseClicked
        // TODO add your handling code here:
         seleccion1 = JTableBancos.rowAtPoint(evt.getPoint());
       codigop1 =   String.valueOf(JTableBancos.getValueAt(seleccion1, 0));
    }//GEN-LAST:event_JTableBancosMouseClicked

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        // TODO add your handling code here:
        if(codigop1!=null){
            if(Integer.valueOf(modelo1.getValueAt(seleccion1, 3).toString())>0){
                restarcantidadproductos(Integer.valueOf(codigop1));
                sumarcantidadproductos();
                sumarsubtotal();
                sumardescuento();
                sumarisv();
                total();
            }
          
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione el producto en la tabla");
        }
    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void JTableBancosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTableBancosKeyReleased
        // TODO add your handling code here:
        if(codigop1!=null){
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
            if(Integer.valueOf(JTableBancos.getValueAt(seleccion1,3).toString())>0){
              
               
                if(Integer.valueOf(JTableBancos.getValueAt(seleccion1,3).toString())<=Integer.valueOf(JTableBancos1.getValueAt(seleccion2,3).toString())){
                actualizarEnter();
                sumarcantidadproductos();
                sumarsubtotal();
                sumardescuento();
                sumarisv();
                total();
                codigop=null;
                
                }else{
                   JOptionPane.showMessageDialog(rootPane, "Ha ingresado una cantidad que sobrepasa la cantidad disponbible, el maximo de productos es de: "+Integer.valueOf(JTableBancos1.getValueAt(seleccion2,3).toString()));
                   JTableBancos.setValueAt(Integer.valueOf(JTableBancos1.getValueAt(seleccion2,3).toString()), seleccion1, 3);
 
                }
                
                
            }else{
                JOptionPane.showMessageDialog(rootPane, "Debe agregar el minimo de producto");
                JTableBancos.setValueAt(1, seleccion1, 3);

            }
            
            }
                
                
               
        
            
    }else{
                JOptionPane.showMessageDialog(rootPane, "No hay productos en existencia para el producto seleccionado");

            }
        
    }//GEN-LAST:event_JTableBancosKeyReleased

    private void JTextbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextbuscarKeyReleased
        // TODO add your handling code here:
        if(JTextbuscar.getText().length()>0){
            JTextbuscar.setPlaceholder("");
        }else{
            JTextbuscar.setPlaceholder("Buscar por codigo");
        }
        
     
        if(evt.getKeyCode()==KeyEvent.VK_ENTER && JTextbuscar.getText().isEmpty()==false){
            limpiartabla();
            buscarProductoPorId(Integer.valueOf(JTextbuscar.getText()));
            codigop=null;
        }else{
            if(evt.getKeyCode()==KeyEvent.VK_ENTER && JTextbuscar.getText().isEmpty()==true){
                limpiartabla();
                codigop=null;
            }
        }
    }//GEN-LAST:event_JTextbuscarKeyReleased

    private void JTextbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextbuscarActionPerformed

    }//GEN-LAST:event_JTextbuscarActionPerformed

    private void rSRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButton1ActionPerformed
        // TODO add your handling code here:
        rSRadioButton2.setSelected(false);
        rSRadioButton3.setSelected(false);
        jLabel51.setVisible(false);
        JTextbuscar.setVisible(false);
        rSButtonIcon_new18.setLocation(149,111);
        rSButtonIcon_new18.setVisible(true);

    }//GEN-LAST:event_rSRadioButton1ActionPerformed

    private void rSRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButton2ActionPerformed
        // TODO add your handling code here:
        rSRadioButton1.setSelected(false);
        rSRadioButton3.setSelected(false);
        jLabel51.setVisible(true);
        JTextbuscar.setVisible(true);
        rSButtonIcon_new18.setLocation(250,130);
        rSButtonIcon_new18.setVisible(true);
    }//GEN-LAST:event_rSRadioButton2ActionPerformed

    private void JTextbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextbuscar1ActionPerformed

    }//GEN-LAST:event_JTextbuscar1ActionPerformed

    private void JTextbuscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextbuscar1KeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_JTextbuscar1KeyReleased

    private void rSButtonIcon_new18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new18ActionPerformed
        // TODO add your handling code here:
       /* if(rSRadioButton2.isSelected()){
            if(JTextbuscar.getText().length()>0){
                if(tipodeVenta==1){
                    insertarOrden();
                    enviarDetallesOrden();
                    if(estadodetalleorden==true && estadoorden==true){

                        enviarActualizacionExistencia();
                        actualizartotalcaja();
                        if(estadototalcaja==true){
                            actualizarHistoriaCaja();
                            VentanaEmergente1 ve = new VentanaEmergente1();
                            ve.setVisible(true);
                        }

                        calcularcambio();
                        rSPanelForma3.setVisible(false);
                        rSPanelForma6.setVisible(true);
                    }

                }else{
                    if(tipodeVenta==2){
                        insertarOrden();
                        enviarDetallesOrden();
                        insertarEnvio();
                        if(estadodetalleorden==true && estadoorden==true){
                            enviarActualizacionExistencia();
                            actualizartotalcaja();
                            if(estadototalcaja==true){
                                actualizarHistoriaCaja();
                                VentanaEmergente1 ve = new VentanaEmergente1();
                                ve.setVisible(true);
                            }

                            calcularcambio();
                            rSPanelForma3.setVisible(false);
                            rSPanelForma6.setVisible(true);
                        }
                    }
                }

            }else{
                JOptionPane.showMessageDialog(rootPane, "Ingrese un valor de efectivo");
            }
        }else{
            if(tipodeVenta==1){
                insertarOrden();
                enviarDetallesOrden();
                if(estadodetalleorden==true && estadoorden==true){

                    enviarActualizacionExistencia();
                    actualizartotalcaja();
                    if(estadototalcaja==true){
                        actualizarHistoriaCaja();
                        VentanaEmergente1 ve = new VentanaEmergente1();
                        ve.setVisible(true);
                    }

                    calcularcambio();
                    rSPanelForma3.setVisible(false);
                    rSPanelForma6.setVisible(true);
                }

            }else{
                if(tipodeVenta==2){
                    insertarOrden();
                    enviarDetallesOrden();
                    insertarEnvio();
                    if(estadodetalleorden==true && estadoorden==true){
                        enviarActualizacionExistencia();
                        actualizartotalcaja();
                        if(estadototalcaja==true){
                            actualizarHistoriaCaja();
                            VentanaEmergente1 ve = new VentanaEmergente1();
                            ve.setVisible(true);
                        }

                        calcularcambio();
                        rSPanelForma3.setVisible(false);
                        rSPanelForma6.setVisible(true);
                    }
                }
            }
        }*/

    }//GEN-LAST:event_rSButtonIcon_new18ActionPerformed

    private void rSRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButton3ActionPerformed
        // TODO add your handling code here:
        rSRadioButton1.setSelected(false);
        rSRadioButton2.setSelected(false);
        jLabel51.setVisible(false);
        JTextbuscar.setVisible(false);
        rSButtonIcon_new18.setLocation(149,111);
        rSButtonIcon_new18.setVisible(true);
    }//GEN-LAST:event_rSRadioButton3ActionPerformed

    private void JEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JEstadoActionPerformed

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
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private rojerusan.RSComboMetro JEstado;
    private rojerusan.RSTableMetro1 JTableBancos;
    private rojerusan.RSTableMetro1 JTableBancos1;
    private RSMaterialComponent.RSTextFieldIconUno JTextbuscar;
    private RSMaterialComponent.RSTextFieldIconUno JTextbuscar1;
    private javax.swing.JPanel dashboardview;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
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
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel linesetting3;
    private javax.swing.JPanel linesetting4;
    private javax.swing.JPanel linesetting5;
    private rojeru_san.complementos.RSButtonHover rSButtonHover1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne3;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new12;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new18;
    private rojeru_san.RSLabelFecha rSLabelFecha1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private rojerusan.RSLabelIcon rSLabelIcon13;
    private rojerusan.RSLabelIcon rSLabelIcon14;
    private rojerusan.RSLabelIcon rSLabelIcon15;
    private rojerusan.RSLabelIcon rSLabelIcon16;
    private rojerusan.RSLabelIcon rSLabelIcon17;
    private necesario.RSPanel rSPanel1;
    private necesario.RSPanel rSPanel2;
    private rojeru_san.rspanel.RSPanelForma rSPanelForma3;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente1;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente3;
    private necesario.RSPanelShadow rSPanelShadow1;
    private RSMaterialComponent.RSPanelShadow rSPanelShadow2;
    private rojeru_san.rspanel.RSPanelVector rSPanelVector1;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private rojerusan.RSPanelsSlider rSPanelsSlider2;
    private rojerusan.RSPanelsSlider rSPanelsSlider3;
    private rojerusan.RSRadioButton rSRadioButton1;
    private rojerusan.RSRadioButton rSRadioButton2;
    private rojerusan.RSRadioButton rSRadioButton3;
    // End of variables declaration//GEN-END:variables
}
