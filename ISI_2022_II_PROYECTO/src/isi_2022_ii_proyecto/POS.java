/*//GEN-FIRST:event_rSButtonGradiente8ActionPerformed
 * To change this license header, choose License Headers in Project Properties.//GEN-LAST:event_rSButtonGradiente8ActionPerformed
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
public class POS extends javax.swing.JFrame {
    boolean a = true;
    
    ConexionBD conexion = new ConexionBD();
  
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
    public POS() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        buscarUltimoId();
        modelo1=(DefaultTableModel) JTableBancos.getModel();
        modelo=(DefaultTableModel) JTableBancos1.getModel();
        setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
        
    }
    
    
    
    public void buscarUltimoId(){
          Connection con = conexion.conexion();
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
        
        
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("error al cerrar conexion");
        }
        
    }
    
    
    
    public int obteneridalmacendesucursal(){
          Connection con = conexion.conexion();
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
           
             try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("error al cerrar conexion");
        }
           
           return idalmacen;
            
    }
    
   
    
      public void buscarProductoPorId(int idproducto){
            Connection con = conexion.conexion();
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
        
        
          try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("error al cerrar conexion");
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
          Connection con = conexion.conexion();
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
        
          try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("error al cerrar conexion");
        }
        
        return descuento;
        
    }
    
    
    public void agregarproductosorden(int idproducto){
          Connection con = conexion.conexion();
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
        
          try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("error al cerrar conexion");
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
                            
                            registros[0] = String.valueOf(idproducto);
                            registros[1] = rs.getString("p.Nombre");
                            registros[2] = String.valueOf(rs.getFloat("p.Precio"));
                            registros[3] = "1";
                            registros[4] = "0.00";
                            registros[5] = String.valueOf((Float.valueOf(registros[2])*Float.valueOf(registros[3]))-Float.valueOf(registros[4]));
                            modelo1.addRow(registros);
                        }

                        
                    }catch(SQLException e){
                        System.out.println("Error "+e.getMessage());

                    }
                
                
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("error al cerrar conexion");
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
          Connection con = conexion.conexion();
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
        
          try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("error al cerrar conexion");
        }
        
         
        jLabel35.setText(nombrevendedor);
        jLabel36.setText(String.valueOf(codigvendedor));
         
    }
    
     public void iniciarcajero(){
           Connection con = conexion.conexion();
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
        
          try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("error al cerrar conexion");
        }
        
        
        
        jLabel44.setText(nombrecajero);
        jLabel45.setText(usuario);
         
    }
    
    
    
    
    
     public void iniciarcliente(){
           Connection con = conexion.conexion();
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
        
          try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("error al cerrar conexion");
        }
        
        jLabel38.setText(nombrecliente);
        jLabel39.setText(String.valueOf(codigcliente));
         
    }
     
     
    
     public void iniciarcaja(){
           Connection con = conexion.conexion();
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
        
          try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("error al cerrar conexion");
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        rSPanelVector1 = new rojeru_san.rspanel.RSPanelVector();
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
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        jLabel30 = new javax.swing.JLabel();
        rSPanelShadow2 = new RSMaterialComponent.RSPanelShadow();
        rSPanel2 = new necesario.RSPanel();
        rSButtonGradiente4 = new rsbuttongradiente.RSButtonGradiente();
        rSButtonGradiente5 = new rsbuttongradiente.RSButtonGradiente();
        rSButtonGradiente6 = new rsbuttongradiente.RSButtonGradiente();
        rSButtonGradiente7 = new rsbuttongradiente.RSButtonGradiente();
        rSButtonGradiente8 = new rsbuttongradiente.RSButtonGradiente();
        rSButtonGradiente9 = new rsbuttongradiente.RSButtonGradiente();
        rSButtonGradiente10 = new rsbuttongradiente.RSButtonGradiente();
        rSButtonGradiente11 = new rsbuttongradiente.RSButtonGradiente();
        rSButtonGradiente12 = new rsbuttongradiente.RSButtonGradiente();
        JTextbuscar = new RSMaterialComponent.RSTextFieldIconUno();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTableBancos1 = new rojerusan.RSTableMetro1();
        rSButtonHover1 = new rojeru_san.complementos.RSButtonHover();
        rSButtonGradiente13 = new rsbuttongradiente.RSButtonGradiente();
        rSButtonGradiente14 = new rsbuttongradiente.RSButtonGradiente();
        rSButtonGradiente15 = new rsbuttongradiente.RSButtonGradiente();
        rSButtonIcon_new12 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new13 = new newscomponents.RSButtonIcon_new();
        rSButtonHover4 = new rojeru_san.complementos.RSButtonHover();
        rSButtonIcon_new14 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new15 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new16 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new17 = new newscomponents.RSButtonIcon_new();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

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

        Header.add(jPanel2, java.awt.BorderLayout.CENTER);

        dashboardview.setBackground(new java.awt.Color(255, 255, 255));

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

        jLabel17.setFont(new java.awt.Font("Franklin Gothic Book", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("POS");

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

        javax.swing.GroupLayout rSPanelGradiente3Layout = new javax.swing.GroupLayout(rSPanelGradiente3);
        rSPanelGradiente3.setLayout(rSPanelGradiente3Layout);
        rSPanelGradiente3Layout.setHorizontalGroup(
            rSPanelGradiente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
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
                        .addGap(30, 30, 30)
                        .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelGradiente3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(rSLabelIcon16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rSPanelShadow1.setBackground(new java.awt.Color(242, 244, 242));
        rSPanelShadow1.add(rSPanelsSlider2, java.awt.BorderLayout.LINE_START);

        rSPanelsSlider3.setBackground(new java.awt.Color(255, 255, 255));

        rSPanel1.setBackground(new java.awt.Color(242, 244, 242));
        rSPanel1.setColorBackground(new java.awt.Color(242, 244, 242));
        rSPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTableBancos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CódigoProducto", "Nombre", "Precio", "Cantidad", "Descuento", "Subtotal"
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

        rSPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 790, 290));

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("0");
        rSPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 180, 20));

        jLabel32.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 0, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("N. ORDEN:");
        rSPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 120, 20));

        jLabel33.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 0, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("0");
        rSPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 180, 20));

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

        jLabel18.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("0.00");

        jLabel19.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Descuento:");

        jLabel26.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Subtotal:");

        jLabel20.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Total:");

        jLabel22.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("0.00");

        jLabel25.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("L.");

        jLabel13.setFont(new java.awt.Font("Franklin Gothic Book", 2, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Productos con ISV incluido");

        javax.swing.GroupLayout rSPanelGradiente1Layout = new javax.swing.GroupLayout(rSPanelGradiente1);
        rSPanelGradiente1.setLayout(rSPanelGradiente1Layout);
        rSPanelGradiente1Layout.setHorizontalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                        .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        rSPanelGradiente1Layout.setVerticalGroup(
            rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelGradiente1Layout.createSequentialGroup()
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelGradiente1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(rSPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        rSPanel1.add(rSPanelGradiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 830, 130));

        jLabel29.setFont(new java.awt.Font("Franklin Gothic Book", 3, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 0, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("DETALLE DE ORDEN");
        rSPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 300, 20));
        rSPanel1.add(rSPanelsSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 70, -1, -1));

        jLabel30.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 0, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Total Productos:");
        rSPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 200, 20));

        javax.swing.GroupLayout rSPanelsSlider3Layout = new javax.swing.GroupLayout(rSPanelsSlider3);
        rSPanelsSlider3.setLayout(rSPanelsSlider3Layout);
        rSPanelsSlider3Layout.setHorizontalGroup(
            rSPanelsSlider3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rSPanelsSlider3Layout.setVerticalGroup(
            rSPanelsSlider3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider3Layout.createSequentialGroup()
                .addComponent(rSPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        rSPanelShadow1.add(rSPanelsSlider3, java.awt.BorderLayout.CENTER);

        rSPanel2.setBackground(new java.awt.Color(242, 244, 242));
        rSPanel2.setForeground(new java.awt.Color(0, 102, 102));
        rSPanel2.setColorBackground(new java.awt.Color(242, 244, 242));
        rSPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonGradiente4.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente4.setText("9");
        rSButtonGradiente4.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente4.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente4.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente4.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente4.setFocusPainted(false);
        rSButtonGradiente4.setFocusable(false);
        rSButtonGradiente4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente4.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente4ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 70, 60));

        rSButtonGradiente5.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente5.setText("7");
        rSButtonGradiente5.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente5.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente5.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente5.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente5.setFocusPainted(false);
        rSButtonGradiente5.setFocusable(false);
        rSButtonGradiente5.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente5.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonGradiente5MouseClicked(evt);
            }
        });
        rSButtonGradiente5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente5ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 70, 60));

        rSButtonGradiente6.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente6.setText("8");
        rSButtonGradiente6.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente6.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente6.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente6.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente6.setFocusPainted(false);
        rSButtonGradiente6.setFocusable(false);
        rSButtonGradiente6.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente6.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente6ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 70, 60));

        rSButtonGradiente7.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente7.setText("4");
        rSButtonGradiente7.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente7.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente7.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente7.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente7.setFocusPainted(false);
        rSButtonGradiente7.setFocusable(false);
        rSButtonGradiente7.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente7.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente7ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 70, 60));

        rSButtonGradiente8.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente8.setText("5");
        rSButtonGradiente8.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente8.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente8.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente8.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente8.setFocusPainted(false);
        rSButtonGradiente8.setFocusable(false);
        rSButtonGradiente8.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente8.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente8ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 70, 60));

        rSButtonGradiente9.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente9.setText("6");
        rSButtonGradiente9.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente9.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente9.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente9.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente9.setFocusPainted(false);
        rSButtonGradiente9.setFocusable(false);
        rSButtonGradiente9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente9.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente9ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 70, 60));

        rSButtonGradiente10.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente10.setText("1");
        rSButtonGradiente10.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente10.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente10.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente10.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente10.setFocusPainted(false);
        rSButtonGradiente10.setFocusable(false);
        rSButtonGradiente10.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente10.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente10ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 70, 60));

        rSButtonGradiente11.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente11.setText("2");
        rSButtonGradiente11.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente11.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente11.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente11.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente11.setFocusPainted(false);
        rSButtonGradiente11.setFocusable(false);
        rSButtonGradiente11.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente11.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente11ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 70, 60));

        rSButtonGradiente12.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente12.setText("3");
        rSButtonGradiente12.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente12.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente12.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente12.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente12.setFocusPainted(false);
        rSButtonGradiente12.setFocusable(false);
        rSButtonGradiente12.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente12.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente12ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 70, 60));

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
        rSPanel2.add(JTextbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 190, -1));

        jLabel12.setFont(new java.awt.Font("Franklin Gothic Book", 2, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 0, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Productos");
        rSPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 110, 40));

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

        rSPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 470, 110));

        rSButtonHover1.setBackground(new java.awt.Color(255, 0, 102));
        rSButtonHover1.setText("-");
        rSButtonHover1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonHover1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 50, -1));

        rSButtonGradiente13.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente13.setText("C");
        rSButtonGradiente13.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente13.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente13.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente13.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente13.setFocusPainted(false);
        rSButtonGradiente13.setFocusable(false);
        rSButtonGradiente13.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente13.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente13ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 70, 60));

        rSButtonGradiente14.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente14.setText("0");
        rSButtonGradiente14.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente14.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente14.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente14.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente14.setFocusPainted(false);
        rSButtonGradiente14.setFocusable(false);
        rSButtonGradiente14.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente14.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente14ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 70, 60));

        rSButtonGradiente15.setForeground(new java.awt.Color(0, 102, 102));
        rSButtonGradiente15.setText(".");
        rSButtonGradiente15.setColorPrimario(new java.awt.Color(255, 255, 255));
        rSButtonGradiente15.setColorPrimarioHover(new java.awt.Color(242, 244, 242));
        rSButtonGradiente15.setColorSecundario(new java.awt.Color(204, 240, 255));
        rSButtonGradiente15.setColorSecundarioHover(new java.awt.Color(204, 204, 204));
        rSButtonGradiente15.setFocusPainted(false);
        rSButtonGradiente15.setFocusable(false);
        rSButtonGradiente15.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        rSButtonGradiente15.setGradiente(rsbuttongradiente.RSButtonGradiente.Gradiente.HORIZONTAL);
        rSButtonGradiente15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente15ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonGradiente15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 70, 60));

        rSButtonIcon_new12.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIcon_new12.setText("Ver Todos");
        rSButtonIcon_new12.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.GRID_ON);
        rSButtonIcon_new12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new12ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonIcon_new12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 150, -1));

        rSButtonIcon_new13.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIcon_new13.setText("Limpiar Orden");
        rSButtonIcon_new13.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new13.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR_ALL);
        rSButtonIcon_new13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new13ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonIcon_new13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 220, -1));

        rSButtonHover4.setText("+");
        rSButtonHover4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rSButtonHover4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover4ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonHover4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 50, -1));

        rSButtonIcon_new14.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIcon_new14.setText("Cancelar Orden");
        rSButtonIcon_new14.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new14.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSButtonIcon_new14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new14ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonIcon_new14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 220, -1));

        rSButtonIcon_new15.setBackground(new java.awt.Color(0, 204, 102));
        rSButtonIcon_new15.setText("Enviar Orden");
        rSButtonIcon_new15.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new15.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEND);
        rSButtonIcon_new15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new15ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonIcon_new15, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 220, -1));

        rSButtonIcon_new16.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIcon_new16.setText("Limpiar Buscador");
        rSButtonIcon_new16.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new16.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE_SWEEP);
        rSButtonIcon_new16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new16ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonIcon_new16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 220, -1));

        rSButtonIcon_new17.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIcon_new17.setText("Quitar producto");
        rSButtonIcon_new17.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new17.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE_FOREVER);
        rSButtonIcon_new17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new17ActionPerformed(evt);
            }
        });
        rSPanel2.add(rSButtonIcon_new17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 220, -1));

        jLabel14.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 0, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Buscador");
        rSPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 40));

        jLabel15.setFont(new java.awt.Font("Franklin Gothic Book", 2, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 0, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Acciones");
        rSPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 110, 40));

        rSPanelShadow2.add(rSPanel2, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout dashboardviewLayout = new javax.swing.GroupLayout(dashboardview);
        dashboardview.setLayout(dashboardviewLayout);
        dashboardviewLayout.setHorizontalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelGradiente3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSPanelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSPanelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        dashboardviewLayout.setVerticalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addComponent(rSPanelGradiente3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSPanelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSPanelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboardview, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1348, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    }// </editor-fold>                        

    private void rSButtonIconOne3ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }                                                

    private void rSButtonIconOne4ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
     
            System.exit(0);
      
        
    }                                                

    private void rSButtonIconOne5ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        this.setExtendedState(ICONIFIED);
    }                                                

    private void rSButtonIcon_new14ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
    
            regresar();
     
    }                                                  

    private void rSButtonHover4ActionPerformed(java.awt.event.ActionEvent evt) {                                               
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
        
    }                                              

    private void rSButtonIcon_new13ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        limpiartabla2();
    }                                                  

    private void rSButtonIcon_new12ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void rSButtonGradiente15ActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
    }                                                   

    private void rSButtonGradiente14ActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
    
        String a = JTextbuscar.getText();
        JTextbuscar.setText(a+"0");
    }                                                   

    private void rSButtonGradiente13ActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
        JTextbuscar.setText("");
    }                                                   

    private void JTableBancos1MouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        seleccion2 = JTableBancos1.rowAtPoint(evt.getPoint());
        codigop =  JTableBancos1.getValueAt(seleccion2,0 ).toString();
 
    }                                          

    private void rSButtonGradiente12ActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
          String a = JTextbuscar.getText();
        JTextbuscar.setText(a+"3");
    }                                                   

    private void rSButtonGradiente11ActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
          String a = JTextbuscar.getText();
        JTextbuscar.setText(a+"2");
    }                                                   

    private void rSButtonGradiente10ActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
          String a = JTextbuscar.getText();
        JTextbuscar.setText(a+"1");
    }                                                   

    private void rSButtonGradiente9ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
          String a = JTextbuscar.getText();
        JTextbuscar.setText(a+"6");
    }                                                  

    private void rSButtonGradiente8ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
          String a = JTextbuscar.getText();
        JTextbuscar.setText(a+"5");
    }                                                  

    private void rSButtonGradiente7ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
          String a = JTextbuscar.getText();
        JTextbuscar.setText(a+"4");
    }                                                  

    private void rSButtonGradiente6ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
          String a = JTextbuscar.getText();
        JTextbuscar.setText(a+"8");
    }                                                  

    private void rSButtonGradiente5ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
          String a = JTextbuscar.getText();
        JTextbuscar.setText(a+"7");
      
    }                                                  

    private void rSButtonGradiente4ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
          String a = JTextbuscar.getText();
        JTextbuscar.setText(a+"9");
    }                                                  

    private void JTableBancosMouseClicked(java.awt.event.MouseEvent evt) {                                          
        // TODO add your handling code here:
         seleccion1 = JTableBancos.rowAtPoint(evt.getPoint());
       codigop1 =   String.valueOf(JTableBancos.getValueAt(seleccion1, 0));
    }                                         

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {                                               
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
    }                                              

    private void rSButtonIcon_new15ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        
             
            // TODO add your handling code here:
            TipoVenta vo = new TipoVenta();
            vo.setTablaorden(JTableBancos);
            vo.seteardatosorden(idorden,usuario, codigcliente, codigvendedor, Integer.valueOf(jLabel42.getText()), jLabel38.getText(), jLabel35.getText(), jLabel44.getText(),jLabel23.getText(),jLabel22.getText(),jLabel8.getText(),jLabel28.getText(),jLabel18.getText());
            vo.setVisible(true);

            this.dispose();
       
    }                                                  

    private void rSButtonIcon_new16ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        limpiartabla();
        JTextbuscar.setText("");
        codigop=null;
    }                                                  

    private void rSButtonIcon_new17ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
        
          if(codigop1!=null){
            QuitarproductoId(Integer.valueOf(codigop1));
            sumarcantidadproductos();
            sumarsubtotal();
            sumarisv();
            total();
        }else{
            JOptionPane.showMessageDialog(rootPane, "Seleccione el producto en la tabla");
        }
    }                                                  

    private void rSButtonGradiente5MouseClicked(java.awt.event.MouseEvent evt) {                                                
        // TODO add your handling code here:
        
     
    }                                               

    private void JTableBancosKeyReleased(java.awt.event.KeyEvent evt) {                                         
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
        
    }                                        

    private void JTextbuscarKeyReleased(java.awt.event.KeyEvent evt) {                                        
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
    }                                       

    private void JTextbuscarActionPerformed(java.awt.event.ActionEvent evt) {                                            

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
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new POS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel Header;
    private rojerusan.RSTableMetro1 JTableBancos;
    private rojerusan.RSTableMetro1 JTableBancos1;
    private RSMaterialComponent.RSTextFieldIconUno JTextbuscar;
    private javax.swing.JPanel dashboardview;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel linesetting3;
    private javax.swing.JPanel linesetting4;
    private javax.swing.JPanel linesetting5;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente10;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente11;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente12;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente13;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente14;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente15;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente4;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente5;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente6;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente7;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente8;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente9;
    private rojeru_san.complementos.RSButtonHover rSButtonHover1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne3;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new12;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new13;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new14;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new15;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new16;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new17;
    private rojerusan.RSLabelIcon rSLabelIcon13;
    private rojerusan.RSLabelIcon rSLabelIcon14;
    private rojerusan.RSLabelIcon rSLabelIcon15;
    private rojerusan.RSLabelIcon rSLabelIcon16;
    private rojerusan.RSLabelIcon rSLabelIcon17;
    private necesario.RSPanel rSPanel1;
    private necesario.RSPanel rSPanel2;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente1;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente3;
    private necesario.RSPanelShadow rSPanelShadow1;
    private RSMaterialComponent.RSPanelShadow rSPanelShadow2;
    private rojeru_san.rspanel.RSPanelVector rSPanelVector1;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private rojerusan.RSPanelsSlider rSPanelsSlider2;
    private rojerusan.RSPanelsSlider rSPanelsSlider3;
    // End of variables declaration                   
}
