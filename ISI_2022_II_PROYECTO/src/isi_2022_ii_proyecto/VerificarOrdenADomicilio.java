/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto;






import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.AvisoFacturaConRTN;
import isi_2022_ii_proyecto.Recursos.VentanaEmergente1;

import java.awt.Font;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import rojerusan.RSTableMetro1;

/**
 *
 * @author Edwin Rafael
 */
public class VerificarOrdenADomicilio extends javax.swing.JFrame {
    
    
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
    String isv18;
    String totalp;
    String envio;
    String RTNC="";

    public void setRTNC(String RTNC) {
        this.RTNC = RTNC;
    }
    /*VARIABLES PARA TABLA DE ENVIOS*/
    int tipodeVenta;
    int idDireccionCliente;
    String fechaentrega;
    String horaentrega;
    int idEmpresaEnvio;
    int idTipoPagoEnvio;


    boolean estadoorden;
    boolean estadodetalleorden;
    boolean estadototalcaja=false;


    
    
    

    public void setTablaorden(RSTableMetro1 tablaorden) {
        this.tablaorden = tablaorden;
    }
    
    
    
    

    /**
     * Creates new form VerificarOrden
     */
    public VerificarOrdenADomicilio() {
        initComponents();
        rSPanel2.setSize(780, 210);
        rSPanel4.setVisible(false);
        rSPanelForma3.setVisible(false);
        rSPanelForma6.setVisible(false);
        rSPanel4.setVisible(false);
        estadodetalleorden=false;
        estadodetalleorden=false;
        setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());
        
    }
    
    
    
    public void cargartabla(){
       modelo=(DefaultTableModel) tablaorden.getModel();
       JTableBancos.setModel(modelo);
    }
    
    public int obteneridusuario(){
        int idusuario=0;
        String SQL = "SELECT u.IdUsuario FROM Usuarios u WHERE u.Usuario='"+usuario+"';";
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idusuario = rs.getInt("u.IdUsuario");
               
                
            }
        }catch(SQLException e){
              JOptionPane.showMessageDialog(this, e.getMessage());
        } 
        
        return idusuario;
        
    }
    
    
    public int obtenertipopago(){
        int tipopago=0;
        if(rSRadioButton2.isSelected()){
            tipopago= 1;
        }else{
            if(rSRadioButton1.isSelected()){
                tipopago= 3;
            }else{
              if(rSRadioButton3.isSelected()){
                tipopago= 4;
            }  
            }
        }
        return tipopago;
    }
    
    public float obtenertotalcajaA(){
        float totalcajaa=0.00f;
        String SQL = "Select c.TotalCaja from Caja c Where c.IdUsuario="+obteneridusuario();
         try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                totalcajaa = Float.valueOf(rs.getString("c.TotalCaja"));
             
            }
        
     
            }catch(SQLException e){
                 System.out.println("Error "+e.getMessage());
        
            }
         return totalcajaa;
    }
    
    
    public void actualizartotalcaja(){
        float totalcajaa=0.00f;
        totalcajaa=obtenertotalcajaA();
        String SQL = "UPDATE Caja SET TotalCaja=? WHERE IdUsuario="+obteneridusuario();
        float totalcajan=Float.valueOf(jLabel22.getText())+totalcajaa;
        
  
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setFloat(1, Float.valueOf(totalcajan));
            preparedStmt.execute();
            estadototalcaja=true;
          

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public float obtenerMontoOperacion(){
        
        String SQL = "SELECT h.MontoOperacion FROM HistoriaCajas h Where h.IdCaja="+codigocaja+" AND h.IdEstadoHistoria="+1;
        float montooperacion=0.00f;
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
           
                montooperacion =Float.valueOf(rs.getString("h.MontoOperacion"));

            }
        
     
            }catch(SQLException e){
                 System.out.println("Error "+e.getMessage());
        
            }
        
        return montooperacion;
        
    }
    
    
    public void actualizarHistoriaCaja(){
        float montooperaciona=0.00f;
        montooperaciona=obtenerMontoOperacion();
        String SQL = "UPDATE HistoriaCajas SET MontoOperacion=? WHERE IdCaja="+codigocaja+" AND IdEstadoHistoria="+1;
        float montooperacionn=Float.valueOf(jLabel22.getText())+montooperaciona;
        
  
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setFloat(1, Float.valueOf(montooperacionn));
            preparedStmt.execute();
            
    

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    
    public void insertarOrden(){
        int idorden = Integer.valueOf(jLabel46.getText());
        String fechaventa=rSLabelFecha1.getFecha();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaapertura = dtf.format(LocalDateTime.now());
        int idcliente = this.codigcliente;
        int idempleado = this.codigvendedor;
        int idusuario=obteneridusuario();
        int idcaja = this.codigocaja;
        int idtipopago=obtenertipopago();

      
     
         
       String SQL = "INSERT INTO Ventas (IdOrden, FechaVenta, HoraVenta, IdCliente, IdEmpleado, IdUsuario, IdCaja, IdTipoPago) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setInt(1, idorden);
            preparedStmt.setString(2, fechaventa);
            preparedStmt.setString(3, horaapertura);
            preparedStmt.setInt(4, idcliente);
            preparedStmt.setInt(5, idempleado);
            preparedStmt.setInt(6, idusuario);
            preparedStmt.setInt(7, idcaja);
            preparedStmt.setInt(8, idtipopago);
            preparedStmt.execute();
            estadoorden = true;
            
     
           
       

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        
        
       
    }
    
    
    public void insertarDetallesOrden(int idorden, int idproducto, int cantidad, float descuento, float subtotal){
               String SQL = "INSERT INTO DetalledeVenta (IdOrden, IdProducto, Cantidad, Descuento, Subtotal) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setInt(1, idorden);
            preparedStmt.setInt(2, idproducto);
            preparedStmt.setInt(3, cantidad);
            preparedStmt.setFloat(4, descuento);
            preparedStmt.setFloat(5, subtotal);
            preparedStmt.execute();
            
            estadodetalleorden=true;
          
           
       

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void enviarDetallesOrden(){
        int idproducto=0;
        int cantidad=0;
        float descuento =0.00f;
        float subtotal = 0.00f;
        
        for(int i=0; i<JTableBancos.getRowCount();i++){
             idproducto=Integer.valueOf(modelo.getValueAt(i, 0).toString());
             cantidad=Integer.valueOf(modelo.getValueAt(i, 3).toString());
             descuento=Float.valueOf(modelo.getValueAt(i, 4).toString());
             subtotal=Float.valueOf(modelo.getValueAt(i, 5).toString());
             insertarDetallesOrden(idorden,idproducto,cantidad,descuento,subtotal);
            }
        
 
        
        
    }
    
    
    public void cargardatos(){
        jLabel35.setText(vendedor);
        jLabel36.setText(String.valueOf(codigvendedor));
        jLabel38.setText(nombrecliente);
        jLabel39.setText(String.valueOf(codigcliente));
        jLabel42.setText(String.valueOf(codigocaja));
        jLabel44.setText(cajero);
        jLabel45.setText(usuario);
        jLabel23.setText(subtotal);
        jLabel28.setText(descuento);
        jLabel18.setText(isv);
        jLabel8.setText(totalp);
        jLabel22.setText(total);
        jLabel46.setText(String.valueOf(idorden));
        jLabel21.setText(envio);
    }
    
    public void seteardatosorden(int idorden,String usuario,int codigcliente, int codigvendedor, int codigocaja, String ncliente, String nvendedor, String ncajero, String subt, String tot, String cantp, String des, String isv, String envio, int tipov){
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
        this.envio=envio;
        this.tipodeVenta=tipov;
        
        cargardatos();
        
        
    }
    
    public void setdatosEnvio(int idDireccionCliente, String fechaentrega, String horaEntrega, int idEmpresaEnvio){
        this.idDireccionCliente=idDireccionCliente;
        this.fechaentrega=fechaentrega;
        this.horaentrega=horaEntrega;
        this.idEmpresaEnvio=idEmpresaEnvio;
        
    }
    
    
    public void mostrarelementos(){
       rSPanel2.setSize(780, 460);
       rSPanel4.setVisible(true);
       rSPanelForma3.setVisible(true);
       jLabel51.setVisible(false);
       JTextbuscar.setVisible(false);
       rSButtonIcon_new18.setVisible(false);
      
       
       
    }
    
    public void calcularcambio(){
        float cambio=0.00f;
        cambio=Float.valueOf(JTextbuscar.getText())-Float.valueOf(jLabel22.getText());
        jLabel53.setText(String.valueOf(cambio));
    }
    
    
     public void enviarActualizacionExistencia(){
        int idproducto=0;
        int cantidad=0;
       
        
        for(int i=0; i<JTableBancos.getRowCount();i++){
             idproducto=Integer.valueOf(modelo.getValueAt(i, 0).toString());
             cantidad=Integer.valueOf(modelo.getValueAt(i, 3).toString());
             actualizarinventario(idproducto, cantidad);
            }
        
 
        
        
    }
    
     public int existenciaactual(int idproducto){
        int existenciactual=0;
        String SQL1 = "Select ap.ExistenciaActual from AlmacenProducto ap\n" +
                    "Where ap.IdProducto="+idproducto+" AND ap.IdAlmacen=(Select s.IdAlmacen From Sucursales s\n" +
                    "INNER JOIN Empleados e ON e.IdSucursal = s.IdSucursal\n" +
                    "INNER JOIN Usuarios u ON u.IdEmpleado = e.IdEmpleado\n" +
                    "WHERE u.Usuario='"+usuario+"');";
        
        
         try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL1);

            while (rs.next()) {
                existenciactual =rs.getInt("ap.ExistenciaActual");
             
            }
        
     
            }catch(SQLException e){
                 System.out.println("Error "+e.getMessage());
        
            }
         return existenciactual;
     }
     
     
    public void actualizarinventario(int idproducto, int cantidad){
        int existenciaactual=0;
        existenciaactual = existenciaactual(idproducto);
        
        int nuevoinventario=existenciaactual-cantidad;
     
        
        String SQL = "UPDATE AlmacenProducto ap SET ap.ExistenciaActual=?\n" +
                    "WHERE IdAlmacen=(Select s.IdAlmacen From Sucursales s\n" +
                    "INNER JOIN Empleados e ON e.IdSucursal = s.IdSucursal\n" +
                    "INNER JOIN Usuarios u ON u.IdEmpleado = e.IdEmpleado\n" +
                    "WHERE u.Usuario=+'"+usuario+"' AND ap.IdProducto="+idproducto+");";
        
        
      
        
        
  
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setInt(1, nuevoinventario);
            preparedStmt.execute();
            
           

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void insertarEnvio(){
        String SQL = "INSERT INTO Envios (IdOrden, IdDireccionCliente, IdEstadoVenta, FechaEntrega, HoraEntrega, IdEmpresaEnvio, IdTipoPago) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setInt(1, idorden);
            preparedStmt.setInt(2, idDireccionCliente);
            preparedStmt.setInt(3, 1);
            preparedStmt.setString(4, fechaentrega);
            preparedStmt.setString(5, horaentrega);
            preparedStmt.setInt(6, idEmpresaEnvio);
            preparedStmt.setInt(7, idTipoPagoEnvio);
            preparedStmt.execute();
      
     
           
       

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }
    
    
    public void insertarTotalOrden(){
        String SQL = "INSERT INTO EntradasCaja (IdOrden, Total) VALUES(?, ?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setInt(1, idorden);
            preparedStmt.setFloat(2, Float.valueOf(jLabel22.getText()));
            preparedStmt.execute();
      
     
           
       

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void insertarTotalEnvio(){
        String SQL = "INSERT INTO DetalleEnvio (IdOrden, Total) VALUES(?, ?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setInt(1, idorden);
            preparedStmt.setFloat(2, Float.valueOf(jLabel21.getText()));
            preparedStmt.execute();
      
     
           
       

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void Factura(){
        String SQL = "INSERT INTO Factura (IdFactura, IdOrden, SubTotal, ISV15, ISV18, Total, NumeroFormato, FechaEmision, HoraEmision, TotalP, FRTN) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setString(1, ObtenerNumeroFactura());
            preparedStmt.setInt(2, idorden);
            preparedStmt.setFloat(3, Float.valueOf(subtotal));
            preparedStmt.setFloat(4, Float.valueOf(isv));
            preparedStmt.setFloat(5, Float.valueOf(isv18));
            preparedStmt.setFloat(6, Float.valueOf(total));
            preparedStmt.setInt(7, ObtenerFormatoFactura());
            preparedStmt.setString(8, ObtenerFechaEmision());
            preparedStmt.setString(9, ObtenerHoraEmision());
            preparedStmt.setInt(10, Integer.parseInt(totalp));
            preparedStmt.setString(11, RTNC);
            preparedStmt.execute();
      
     
           
       

        } catch (SQLException e) {
            System.err.println("Error al guardar la factura "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error"+e.getMessage());
        }
        
        
    }
    
    
    public String ObtenerNumerodeFacturaXOrden(){
        String numerodefactura = "";
        String SQL1 = "Select f.IdFactura from Factura f\n" +
                      "Where f.IdOrden="+idorden;
        
        
         try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL1);

            while (rs.next()) {
                numerodefactura =rs.getString("f.IdFactura");
             
            }
        
     
            }catch(SQLException e){
                 System.out.println("Error al obtener el numero de factura"+e.getMessage());
        
            }
         return numerodefactura;
         
    }
    
    public void ImprimirFactura(){
        try {
            JasperReport reporte = null;
            String path1 = "src\\Reportes\\FacturaD.jasper";
            Map parametro = new HashMap();
            parametro.put("Nfactura",ObtenerNumerodeFacturaXOrden());
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path1);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
         } catch (JRException jex) {
             System.err.println("Error al imprimir factura "+ jex.getMessage());
            JOptionPane.showMessageDialog(null,"JasperException"+jex.getMessage());
        } catch (Exception ex) {
             System.err.println("Error al imprimir factura "+ ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getStackTrace());
        }
    }
    
     public int ObtenerFormatoFactura(){
        int numeroformato=0;
        String SQL1 = "Select fc.NumeroFormato From FormatoFacturaCabecera fc\n" +
                      "Where fc.IdEstado=1;";
        
        
         try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL1);

            while (rs.next()) {
                numeroformato =rs.getInt("fc.NumeroFormato");
             
            }
        
     
            }catch(SQLException e){
                 System.out.println("Error al obtener el numero de formato"+e.getMessage());
        
            }
         
         return numeroformato;
    }
    
    public String ObtenerFechaEmision(){
        String fechae="";
          String SQL1 = "Select v.FechaVenta From Ventas v\n" +
                      "Where v.IdOrden="+idorden;
        
        
         try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL1);

            while (rs.next()) {
                fechae =rs.getString("v.FechaVenta");
             
            }
        
     
            }catch(SQLException e){
                 System.out.println("Error "+e.getMessage());
        
            }
         return fechae;
    }
    
    public String ObtenerHoraEmision(){
        String Horae="";
          String SQL1 = "Select v.HoraVenta From Ventas v\n" +
                      "Where v.IdOrden="+idorden;
        
        
         try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL1);

            while (rs.next()) {
                Horae =rs.getString("v.HoraVenta");
             
            }
        
     
            }catch(SQLException e){
                 System.out.println("Error "+e.getMessage());
        
            }
         return Horae;
    }
    
     public String ObtenerNumeroFactura(){
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
         
         
         for(int i=0; i<11;i++){
                vi= nfactura.charAt(i);
                contadori = contadori+String.valueOf(vi);
         }
         
         for(int i=11; i<19;i++){
                vf= nfactura.charAt(i);
                contadorf = contadorf+String.valueOf(vf);
         }
         
         int nvalorfactura=0;
         nvalorfactura = Integer.parseInt(contadorf) + 1;
         String formatted = String.format("%0" + 8 + "d",nvalorfactura );
     

       
         nfacturanueva = contadori + formatted;
         
         return nfacturanueva;
        
        
    }
     
     
     public void pagar(){
         if(rSRadioButton2.isSelected()){
           if(JTextbuscar.getText().length()>0){
                   insertarOrden();
                   insertarEnvio();
                   insertarTotalEnvio();
                   insertarTotalOrden();
                   enviarDetallesOrden();
                    if(estadodetalleorden==true && estadoorden==true){

                        enviarActualizacionExistencia();
                        actualizartotalcaja();
                        if(estadototalcaja==true){
                           actualizarHistoriaCaja();
                           Factura();
                           VentanaEmergente1 ve = new VentanaEmergente1();
                           ve.setVisible(true);
                        }

                    calcularcambio();
                    rSPanelForma3.setVisible(false);
                    rSPanelForma6.setVisible(true);
                    }
                   
               
            
           
            
            }else{
                JOptionPane.showMessageDialog(rootPane, "Ingrese un valor de efectivo");
            } 
        }else{
                if(rSRadioButton1.isSelected()){
                    insertarOrden();
                    insertarEnvio();
                    insertarTotalEnvio();
                    insertarTotalOrden();
                    enviarDetallesOrden();
                    if(estadodetalleorden==true && estadoorden==true){
                        enviarActualizacionExistencia();
                        actualizartotalcaja();
                        if(estadototalcaja==true){
                           actualizarHistoriaCaja();
                           Factura();
                           VentanaEmergente1 ve = new VentanaEmergente1();
                           ve.setVisible(true);
                        }

                    calcularcambio();
                    rSPanelForma3.setVisible(false);
                    rSPanelForma6.setVisible(true);
                    }   
                }else{
                        if(rSRadioButton3.isSelected()){
                              insertarOrden();
                              insertarEnvio();
                              insertarTotalEnvio();
                              insertarTotalOrden();
                              enviarDetallesOrden();
                              if(estadodetalleorden==true && estadoorden==true){
                                enviarActualizacionExistencia();
                                actualizartotalcaja();
                                if(estadototalcaja==true){
                                   actualizarHistoriaCaja();
                                   Factura();
                                   VentanaEmergente1 ve = new VentanaEmergente1();
                                   ve.setVisible(true);
                                }
                                calcularcambio();
                                rSPanelForma3.setVisible(false);
                                rSPanelForma6.setVisible(true);
                               }   
                        }
                }
                   
                   
              
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
        rSPanelShadow2 = new RSMaterialComponent.RSPanelShadow();
        rSPanelForma1 = new rojeru_san.rspanel.RSPanelForma();
        rSPanelForma4 = new rojeru_san.rspanel.RSPanelForma();
        rSPanelForma5 = new rojeru_san.rspanel.RSPanelForma();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        jLabel47 = new javax.swing.JLabel();
        rSLabelFecha1 = new rojeru_san.RSLabelFecha();
        jLabel48 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rSLabelIcon18 = new rojerusan.RSLabelIcon();
        rSButtonIcon_new17 = new newscomponents.RSButtonIcon_new();
        jLabel10 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        rSPanelFunky1 = new rojeru_san.rspanel.RSPanelFunky();
        jLabel46 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        rSPanelForma2 = new rojeru_san.rspanel.RSPanelForma();
        rSPanel2 = new necesario.RSPanel();
        rSPanel3 = new necesario.RSPanel();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableBancos = new rojerusan.RSTableMetro1();
        rSPanel4 = new necesario.RSPanel();
        jLabel55 = new javax.swing.JLabel();
        rSPanelForma3 = new rojeru_san.rspanel.RSPanelForma();
        jLabel50 = new javax.swing.JLabel();
        rSRadioButton1 = new rojerusan.RSRadioButton();
        rSRadioButton2 = new rojerusan.RSRadioButton();
        jLabel51 = new javax.swing.JLabel();
        JTextbuscar = new RSMaterialComponent.RSTextFieldIconUno();
        rSButtonIcon_new18 = new newscomponents.RSButtonIcon_new();
        rSRadioButton3 = new rojerusan.RSRadioButton();
        rSPanelForma6 = new rojeru_san.rspanel.RSPanelForma();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        rSButtonIcon_new19 = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new16 = new newscomponents.RSButtonIcon_new();

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

        rSPanelShadow2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        rSPanelForma1.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelForma1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelForma4.setBackground(new java.awt.Color(255, 255, 255));

        rSPanelForma5.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelForma5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 153, 255), new java.awt.Color(51, 153, 255)));
        rSPanelForma5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        rSPanelForma5.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 90, 40));

        jLabel47.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 0, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Fecha de Emisión:");
        rSPanelForma5.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 130, 20));

        rSLabelFecha1.setFormato("yyyy/MM/dd");
        rSPanelForma5.add(rSLabelFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 80, 20));

        jLabel48.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 0, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("Hora:");
        rSPanelForma5.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 40, 20));

        jLabel26.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 153));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Subtotal:");
        rSPanelForma5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 100, -1));

        jLabel24.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 0, 153));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("L.");
        rSPanelForma5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 20, 40));

        jLabel23.setFont(new java.awt.Font("Franklin Gothic Book", 1, 17)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 153));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("0.00");
        rSPanelForma5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 260, 40));

        jLabel19.setFont(new java.awt.Font("Franklin Gothic Book", 1, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 153));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Descuento:");
        rSPanelForma5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 100, 40));

        jLabel27.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 153));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("L.");
        rSPanelForma5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 20, 40));

        jLabel28.setFont(new java.awt.Font("Franklin Gothic Book", 1, 17)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 153));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("0.00");
        rSPanelForma5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 260, 40));

        jLabel9.setFont(new java.awt.Font("Franklin Gothic Book", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 153));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Envio:");
        rSPanelForma5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 100, 40));

        jLabel18.setFont(new java.awt.Font("Franklin Gothic Book", 1, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 153));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("0.00");
        rSPanelForma5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 250, 40));

        jLabel32.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 51, 204));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("RESUMEN GENERAL DE LA ORDEN");
        rSPanelForma5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 410, 30));

        jLabel21.setBackground(new java.awt.Color(102, 0, 102));
        jLabel21.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 0, 153));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("0.00");
        rSPanelForma5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 230, 40));

        jLabel20.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 0, 153));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Total:");
        rSPanelForma5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 70, 40));

        jLabel25.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 0, 153));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("L.");
        rSPanelForma5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, 40));

        jLabel22.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 0, 153));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("0.00");
        rSPanelForma5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 250, 40));

        jLabel30.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 0, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Total Productos:");
        rSPanelForma5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 120, 20));

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("0");
        rSPanelForma5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 40, 20));

        rSLabelIcon18.setBackground(new java.awt.Color(255, 255, 255));
        rSLabelIcon18.setForeground(new java.awt.Color(0, 51, 255));
        rSLabelIcon18.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        rSLabelIcon18.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LOCAL_MALL);
        rSPanelForma5.add(rSLabelIcon18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 110, 80));

        rSButtonIcon_new17.setBackground(new java.awt.Color(0, 153, 102));
        rSButtonIcon_new17.setText("Confimar Orden");
        rSButtonIcon_new17.setAlignmentX(0.5F);
        rSButtonIcon_new17.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new17.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEND);
        rSButtonIcon_new17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new17ActionPerformed(evt);
            }
        });
        rSPanelForma5.add(rSButtonIcon_new17, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 170, 40));

        jLabel10.setFont(new java.awt.Font("Franklin Gothic Book", 1, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("ISV:");
        rSPanelForma5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 100, 40));

        jLabel29.setBackground(new java.awt.Color(102, 0, 102));
        jLabel29.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 0, 153));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("L.");
        rSPanelForma5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 20, 40));

        jLabel33.setBackground(new java.awt.Color(102, 0, 102));
        jLabel33.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 0, 153));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("L.");
        rSPanelForma5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 20, 40));

        javax.swing.GroupLayout rSPanelForma4Layout = new javax.swing.GroupLayout(rSPanelForma4);
        rSPanelForma4.setLayout(rSPanelForma4Layout);
        rSPanelForma4Layout.setHorizontalGroup(
            rSPanelForma4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelForma4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(rSPanelForma5, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        rSPanelForma4Layout.setVerticalGroup(
            rSPanelForma4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelForma5, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );

        rSPanelForma1.add(rSPanelForma4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 490, 380));

        rSPanelFunky1.setBackground(new java.awt.Color(20, 101, 187));

        jLabel46.setFont(new java.awt.Font("Franklin Gothic Demi", 3, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("0");

        jLabel49.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("ORDEN N.");

        rSPanelForma2.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelForma2.setForma(rojeru_san.rspanel.RSPanelForma.FORMA.ROUND);

        javax.swing.GroupLayout rSPanelForma2Layout = new javax.swing.GroupLayout(rSPanelForma2);
        rSPanelForma2.setLayout(rSPanelForma2Layout);
        rSPanelForma2Layout.setHorizontalGroup(
            rSPanelForma2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        rSPanelForma2Layout.setVerticalGroup(
            rSPanelForma2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rSPanelFunky1Layout = new javax.swing.GroupLayout(rSPanelFunky1);
        rSPanelFunky1.setLayout(rSPanelFunky1Layout);
        rSPanelFunky1Layout.setHorizontalGroup(
            rSPanelFunky1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelFunky1Layout.createSequentialGroup()
                .addContainerGap(161, Short.MAX_VALUE)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(rSPanelFunky1Layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(rSPanelForma2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rSPanelFunky1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rSPanelFunky1Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jLabel49)
                    .addContainerGap(298, Short.MAX_VALUE)))
        );
        rSPanelFunky1Layout.setVerticalGroup(
            rSPanelFunky1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelFunky1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSPanelForma2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
            .addGroup(rSPanelFunky1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rSPanelFunky1Layout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(172, Short.MAX_VALUE)))
        );

        rSPanelForma1.add(rSPanelFunky1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, 260));

        rSPanelShadow2.add(rSPanelForma1, java.awt.BorderLayout.CENTER);

        dashboardview.add(rSPanelShadow2);
        rSPanelShadow2.setBounds(26, 119, 500, 510);

        rSPanel2.setBackground(new java.awt.Color(255, 255, 255));
        rSPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        rSPanel2.setColorBackground(new java.awt.Color(255, 255, 255));
        rSPanel2.setLayout(null);

        rSPanel3.setBackground(new java.awt.Color(153, 153, 255));
        rSPanel3.setColorBackground(new java.awt.Color(0, 55, 133));

        jLabel56.setFont(new java.awt.Font("Franklin Gothic Book", 3, 24)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("DETALLE DE LA ORDEN");

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

        JTableBancos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodigoProducto", "Nombre", "Precio", "Cantidad", "Descuento", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
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

        rSPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(0, 50, 780, 160);

        rSPanel4.setBackground(new java.awt.Color(153, 153, 255));
        rSPanel4.setColorBackground(new java.awt.Color(0, 55, 133));
        rSPanel4.setLayout(null);

        jLabel55.setFont(new java.awt.Font("Franklin Gothic Book", 3, 24)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("PANEL DE PAGO");
        rSPanel4.add(jLabel55);
        jLabel55.setBounds(30, 8, 730, 40);

        rSPanel2.add(rSPanel4);
        rSPanel4.setBounds(0, 210, 790, 60);

        rSPanelForma3.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelForma3.setLayout(null);

        jLabel50.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(102, 0, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Tipo de Pago:");
        rSPanelForma3.add(jLabel50);
        jLabel50.setBounds(135, 11, 130, 20);

        rSRadioButton1.setText("BOTON");
        rSRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rSRadioButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButton1ActionPerformed(evt);
            }
        });
        rSPanelForma3.add(rSRadioButton1);
        rSRadioButton1.setBounds(140, 40, 110, 40);

        rSRadioButton2.setText("EFECTIVO");
        rSRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rSRadioButton2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButton2ActionPerformed(evt);
            }
        });
        rSPanelForma3.add(rSRadioButton2);
        rSRadioButton2.setBounds(10, 38, 110, 40);

        jLabel51.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(102, 0, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Ingrese la cantidad de efectivo recibido:");
        rSPanelForma3.add(jLabel51);
        jLabel51.setBounds(20, 90, 370, 20);

        JTextbuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTextbuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        JTextbuscar.setPlaceholder("Efectivo recibido");
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
        rSPanelForma3.add(JTextbuscar);
        JTextbuscar.setBounds(40, 130, 188, 42);

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
        rSPanelForma3.add(rSButtonIcon_new18);
        rSButtonIcon_new18.setBounds(250, 130, 108, 40);

        rSRadioButton3.setText("TRANSFERENCIA");
        rSRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        rSRadioButton3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButton3ActionPerformed(evt);
            }
        });
        rSPanelForma3.add(rSRadioButton3);
        rSRadioButton3.setBounds(260, 40, 140, 40);

        rSPanel2.add(rSPanelForma3);
        rSPanelForma3.setBounds(0, 270, 400, 200);

        rSPanelForma6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel52.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(102, 0, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Su cambio es:");

        jLabel53.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(102, 0, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("0.00");

        jLabel54.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(102, 0, 255));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("L.");

        rSButtonIcon_new19.setBackground(new java.awt.Color(0, 153, 102));
        rSButtonIcon_new19.setText("Generar Factura");
        rSButtonIcon_new19.setAlignmentX(0.5F);
        rSButtonIcon_new19.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new19.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DONE);
        rSButtonIcon_new19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new19ActionPerformed(evt);
            }
        });

        rSButtonIcon_new16.setBackground(new java.awt.Color(102, 102, 255));
        rSButtonIcon_new16.setText("Menu Principal");
        rSButtonIcon_new16.setAlignmentX(0.5F);
        rSButtonIcon_new16.setBackgroundHover(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSButtonIcon_new16.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DONE_ALL);
        rSButtonIcon_new16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rSPanelForma6Layout = new javax.swing.GroupLayout(rSPanelForma6);
        rSPanelForma6.setLayout(rSPanelForma6Layout);
        rSPanelForma6Layout.setHorizontalGroup(
            rSPanelForma6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelForma6Layout.createSequentialGroup()
                .addGroup(rSPanelForma6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelForma6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(rSPanelForma6Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 87, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelForma6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(rSPanelForma6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rSButtonIcon_new16, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIcon_new19, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );
        rSPanelForma6Layout.setVerticalGroup(
            rSPanelForma6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelForma6Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(rSPanelForma6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(rSButtonIcon_new19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonIcon_new16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        rSPanel2.add(rSPanelForma6);
        rSPanelForma6.setBounds(400, 270, 380, 200);

        dashboardview.add(rSPanel2);
        rSPanel2.setBounds(544, 119, 780, 470);

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
            Logger.getLogger(VerificarOrdenADomicilio.class.getName()).log(Level.SEVERE, null, ex);
        }
     

    }//GEN-LAST:event_rSButtonIconOne4ActionPerformed

    private void JTableBancosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableBancosMouseClicked
        // TODO add your handling code here:
  
    }//GEN-LAST:event_JTableBancosMouseClicked

    private void JTableBancosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTableBancosKeyReleased
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_JTableBancosKeyReleased

    private void rSRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButton2ActionPerformed
        // TODO add your handling code here:
        idTipoPagoEnvio=1;
        rSRadioButton1.setSelected(false);
        rSRadioButton3.setSelected(false);
        jLabel51.setVisible(true);
        JTextbuscar.setVisible(true);
        rSButtonIcon_new18.setLocation(250,130);
        rSButtonIcon_new18.setVisible(true);
    }//GEN-LAST:event_rSRadioButton2ActionPerformed

    private void JTextbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextbuscarActionPerformed

    }//GEN-LAST:event_JTextbuscarActionPerformed

    private void JTextbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextbuscarKeyReleased
        // TODO add your handling code here:
  
    }//GEN-LAST:event_JTextbuscarKeyReleased

    private void rSButtonIcon_new16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new16ActionPerformed
        // TODO add your handling code here:
        Menu menu = new Menu();
        menu.setUsuario(usuario);
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonIcon_new16ActionPerformed

    private void rSButtonIcon_new17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new17ActionPerformed
        // TODO add your handling code here:
        mostrarelementos();
       
    }//GEN-LAST:event_rSButtonIcon_new17ActionPerformed

    private void rSButtonIcon_new18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new18ActionPerformed
        // TODO add your handling code here:
        
        
        AvisoFacturaConRTN avfr = new AvisoFacturaConRTN();
        avfr.setVod(this);
        avfr.setVisible(true);
        
    }//GEN-LAST:event_rSButtonIcon_new18ActionPerformed

    private void rSRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButton1ActionPerformed
        // TODO add your handling code here:
        idTipoPagoEnvio=4;
        rSRadioButton2.setSelected(false);
        rSRadioButton3.setSelected(false);
        jLabel51.setVisible(false);
        JTextbuscar.setVisible(false);
        rSButtonIcon_new18.setLocation(149,111);
        rSButtonIcon_new18.setVisible(true);
        
        
    }//GEN-LAST:event_rSRadioButton1ActionPerformed

    private void rSButtonIcon_new19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new19ActionPerformed
        // TODO add your handling code here:
        ImprimirFactura();
    }//GEN-LAST:event_rSButtonIcon_new19ActionPerformed

    private void rSRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButton3ActionPerformed
        // TODO add your handling code here:
        idTipoPagoEnvio=3;
        rSRadioButton1.setSelected(false);
        rSRadioButton2.setSelected(false);
        jLabel51.setVisible(false);
        JTextbuscar.setVisible(false);
        rSButtonIcon_new18.setLocation(149,111);
        rSButtonIcon_new18.setVisible(true);
    }//GEN-LAST:event_rSRadioButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(VerificarOrdenADomicilio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerificarOrdenADomicilio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerificarOrdenADomicilio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerificarOrdenADomicilio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerificarOrdenADomicilio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private rojerusan.RSTableMetro1 JTableBancos;
    private RSMaterialComponent.RSTextFieldIconUno JTextbuscar;
    private javax.swing.JPanel dashboardview;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel linesetting3;
    private javax.swing.JPanel linesetting4;
    private javax.swing.JPanel linesetting5;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne3;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne5;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new16;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new17;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new18;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new19;
    private rojeru_san.RSLabelFecha rSLabelFecha1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private rojerusan.RSLabelIcon rSLabelIcon13;
    private rojerusan.RSLabelIcon rSLabelIcon14;
    private rojerusan.RSLabelIcon rSLabelIcon15;
    private rojerusan.RSLabelIcon rSLabelIcon16;
    private rojerusan.RSLabelIcon rSLabelIcon17;
    private rojerusan.RSLabelIcon rSLabelIcon18;
    private necesario.RSPanel rSPanel2;
    private necesario.RSPanel rSPanel3;
    private necesario.RSPanel rSPanel4;
    private rojeru_san.rspanel.RSPanelForma rSPanelForma1;
    private rojeru_san.rspanel.RSPanelForma rSPanelForma2;
    private rojeru_san.rspanel.RSPanelForma rSPanelForma3;
    private rojeru_san.rspanel.RSPanelForma rSPanelForma4;
    private rojeru_san.rspanel.RSPanelForma rSPanelForma5;
    private rojeru_san.rspanel.RSPanelForma rSPanelForma6;
    private rojeru_san.rspanel.RSPanelFunky rSPanelFunky1;
    private rspanelgradiente.RSPanelGradiente rSPanelGradiente3;
    private RSMaterialComponent.RSPanelShadow rSPanelShadow2;
    private rojerusan.RSRadioButton rSRadioButton1;
    private rojerusan.RSRadioButton rSRadioButton2;
    private rojerusan.RSRadioButton rSRadioButton3;
    // End of variables declaration//GEN-END:variables
}
