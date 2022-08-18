 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto;

import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.ConfirmacionGuardar;
import isi_2022_ii_proyecto.Recursos.VentanaEmergente1;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rojeru_san.complementos.RSUtilities;
import rojerusan.RSEffectFade;

/**
 *
 * @author Edwin Rafael
 */
public class AgregarBanco extends javax.swing.JFrame {
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();

    int id;
      String banco;
     public void setId(int id) {
        this.id = id;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
    
    
    boolean estadoagregar=false;

    public void setEstadoagregar(boolean estadoagregar) {
        this.estadoagregar = estadoagregar;
    }

   
    /**
     * Creates new form CalendarForm
     */
    public AgregarBanco() {
        RSUtilities.setFullScreenJFrame(this);
        initComponents();
       inicializar();
        buscardatos();
        listarEstado();
        RSUtilities.setOpaqueWindow(this, false);
        RSUtilities.setOpacityComponent(this.jPanel1, 150);
       
        avisoT1.setVisible(false);
         setIconImage(new ImageIcon(getClass().getResource("/isi_2022_ii_proyecto/Imagenes/LOGOFACTURAS.png")).getImage());    
    }
      public void validarConfirmacion(){
        if(estadoagregar=true){
            insertar();
        }
    }
        public void inicializar(){
       JCodigoDisponible.setText(banco);
    }
      public void buscardatos(){
          String SQL = "SELECT * FROM Bancos WHERE IdBanco=(SELECT max(IdBanco) FROM Bancos)";
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                id = rs.getInt("IdBanco");
               
                
            }

            
            System.out.println("SIN SUMAR"+String.valueOf(id));
            id = id +1;
            System.out.println(String.valueOf(id));
            JCodigoDisponible.setText(String.valueOf(id));
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }  public void listarEstado(){
        JEstados.addItem("Seleccionar Estado");
        JEstados.setSelectedIndex(0);
        String descripcion="";
         
        String SQL = "SELECT b.Descripcion From EstadosUsuario b";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                descripcion =rs.getString("b.Descripcion");
    
                JEstados.addItem(descripcion);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
      public int Obtenerestado(){
          String SQL = "SELECT * FROM EstadosUsuario g Where g.Descripcion="+"'"+JEstados.getSelectedItem().toString()+"'";
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
    public static final int UNIQUE_CONSTRAINT_VIOLATED = 1062;
      public boolean insertar(){
        String nombre=""; 
 
        int estado = 0;
     
   
       nombre= Nombre.getText();
       estado = Obtenerestado();
        
 
   
        
        String SQL = "INSERT INTO Bancos(IdBanco,Nombre,IdEstado) VALUES"
                + "(?, ?, ?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setInt(1, id);
            preparedStmt.setString(2, nombre);
            preparedStmt.setInt(3, estado);  
            preparedStmt.execute();
            
           VentanaEmergente1 ve = new VentanaEmergente1();
             ve.setVisible(true);
             
      } catch (SQLException  e) {
                String msj = "ERROR";
                if (UNIQUE_CONSTRAINT_VIOLATED == e.getErrorCode ()) {
                  
                    msj = "EL REGISTRO EXISTE EN LA BASE DE DATOS";
                }
                JOptionPane.showMessageDialog(null, e, msj, JOptionPane.ERROR_MESSAGE);
                return false;
        
        }catch (Exception e) {
               JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
       public Boolean validar(){
        boolean a=true;
   
      
      
        if(Nombre.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre");
          a= false;
      }
          if(validarNombre(Nombre.getText())==false){
          a=false;
      }
    
       
          if(JEstados.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor seleccione un estado");
          a= false;
      
    }

       
       
      
       return a;
       
      
    }
    
     public  boolean validarNombre(String Nombre){
    boolean check=false;
    
    /*Verificamos que no sea null*/ 
    if(Nombre != null){
        /* 1ª Condición: que la letra inicial sea mayúscula*/
        boolean isFirstUpper=Character.isUpperCase(Nombre.charAt(0));

        /* 2ª Condición: que el tamaño sea >= 3 y <= 15*/
        int stringSize=Nombre.length();
        boolean isValidSize=(stringSize >= 3 && stringSize <= 25);

        /* 3ª Condición: que contenga al menos un espacio*/
        boolean isSpaced=Nombre.contains(" ");

        /* Verificamos que las tres condiciones son verdaderas*/
        check= ( (isFirstUpper==true)  && (isFirstUpper && isValidSize &&  isSpaced) );
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
        rSPanelOpacity1 = new RSMaterialComponent.RSPanelOpacity();
        jPanel4 = new javax.swing.JPanel();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        jLabel6 = new javax.swing.JLabel();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        rSButtonIconOne4 = new RSMaterialComponent.RSButtonIconOne();
        jLabel5 = new javax.swing.JLabel();
        linesetting4 = new javax.swing.JPanel();
        linesetting5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rSPanelOpacity2 = new RSMaterialComponent.RSPanelOpacity();
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
        jLabel29 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        producto = new javax.swing.JLabel();
        Nombre = new rojeru_san.RSMTextFull();
        guardar = new newscomponents.RSButtonIcon_new();
        rSButtonIcon_new3 = new newscomponents.RSButtonIcon_new();
        rSPanelCircle1 = new rojeru_san.rspanel.RSPanelCircle();
        JCodigoDisponible = new javax.swing.JLabel();
        avisoT1 = new javax.swing.JLabel();
        JEstados = new rojerusan.RSComboMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        rSPanelOpacity1.setBackground(new java.awt.Color(232, 245, 255));
        rSPanelOpacity1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);
        jPanel4.add(rSLabelIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("MODÚLO BANCOS");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 490, 60));

        rSLabelHora1.setForeground(new java.awt.Color(20, 101, 187));
        jPanel4.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 108, -1));

        rSPanelOpacity1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 870, 50));

        jPanel2.setBackground(new java.awt.Color(20, 101, 187));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("SYSTEM");

        rSButtonIconOne4.setBackground(new java.awt.Color(20, 101, 187));
        rSButtonIconOne4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        rSButtonIconOne4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne4ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("GEVEC");

        linesetting4.setBackground(new java.awt.Color(20, 101, 187));
        linesetting4.setPreferredSize(new java.awt.Dimension(50, 10));

        javax.swing.GroupLayout linesetting4Layout = new javax.swing.GroupLayout(linesetting4);
        linesetting4.setLayout(linesetting4Layout);
        linesetting4Layout.setHorizontalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        linesetting4Layout.setVerticalGroup(
            linesetting4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        linesetting5.setBackground(new java.awt.Color(20, 101, 187));
        linesetting5.setPreferredSize(new java.awt.Dimension(50, 10));
        linesetting5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addComponent(linesetting5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(linesetting4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(286, 286, 286)
                .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linesetting5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linesetting4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconOne4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        rSPanelOpacity1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelOpacity2.setBackground(new java.awt.Color(60, 76, 143));

        rSLabelIcon6.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon6.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_TO_PHOTOS);
        rSLabelIcon6.setName(""); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("/");

        rSLabelIcon8.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon8.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Agregar Bancos");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Menú Principal");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("/");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Modúlo Bancos");

        rSLabelIcon17.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon17.setFocusCycleRoot(true);
        rSLabelIcon17.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        rSLabelIcon17.setName(""); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("/");

        rSLabelIcon12.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
        rSLabelIcon12.setName(""); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Listado de Bancos");

        rSPanelOpacity2.setLayer(rSLabelIcon6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(rSLabelIcon8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(rSLabelIcon17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(rSLabelIcon12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout rSPanelOpacity2Layout = new javax.swing.GroupLayout(rSPanelOpacity2);
        rSPanelOpacity2.setLayout(rSPanelOpacity2Layout);
        rSPanelOpacity2Layout.setHorizontalGroup(
            rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity2Layout.createSequentialGroup()
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
                .addContainerGap(96, Short.MAX_VALUE))
        );
        rSPanelOpacity2Layout.setVerticalGroup(
            rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSLabelIcon17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(rSPanelOpacity2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 50));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(153, 0, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Codígo de Banco:");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 80, 150, 40));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(153, 0, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Estado de Banco");
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 160, 40));

        producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        producto.setForeground(new java.awt.Color(153, 0, 255));
        producto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producto.setText("Nombre de Banco");
        jPanel3.add(producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 140, 50));

        Nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Nombre.setPlaceholder("                               ");
        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });
        Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombreKeyTyped(evt);
            }
        });
        jPanel3.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 370, 40));

        guardar.setBackground(new java.awt.Color(0, 55, 133));
        guardar.setText("Guardar Banco");
        guardar.setBackgroundHover(new java.awt.Color(0, 55, 133));
        guardar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.GRID_ON);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel3.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 170, 50));

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
        jPanel3.add(rSButtonIcon_new3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, 160, 50));

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
                .addComponent(JCodigoDisponible, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );
        rSPanelCircle1Layout.setVerticalGroup(
            rSPanelCircle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JCodigoDisponible, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jPanel3.add(rSPanelCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 70, 70));

        avisoT1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        avisoT1.setForeground(new java.awt.Color(255, 0, 0));
        avisoT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        avisoT1.setText("*Formato invalído*");
        jPanel3.add(avisoT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 180, -1));

        JEstados.setBorder(null);
        JEstados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JEstadosActionPerformed(evt);
            }
        });
        jPanel3.add(JEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 340, -1));

        rSPanelOpacity1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 810, 370));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSPanelOpacity1, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSPanelOpacity1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void NombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreKeyReleased
         if (validarNombre(Nombre.getText())){
             avisoT1.setVisible(false);
             
        }
          else{
                    avisoT1.setVisible(true);
              
          }
      

    }//GEN-LAST:event_NombreKeyReleased

    private void NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreKeyTyped

         if (Nombre.getText().trim().length() == 30) {
            evt.consume();

        }
    }//GEN-LAST:event_NombreKeyTyped

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        if(validar()==true){
           ConfirmacionGuardar ban= new  ConfirmacionGuardar();
           ban.setAbanco(this);
            ban.setTipo("GBanco");
            ban.setVisible(true);
         
        }
          
        else{

            JOptionPane.showMessageDialog(this, "POR FAVOR VERIFIQUE LA INFORMACIÓN");
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void rSButtonIcon_new3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new3ActionPerformed
        Banco c = new Banco();
        c.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_rSButtonIcon_new3ActionPerformed

    private void rSButtonIconOne4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne4ActionPerformed
        try {
            // TODO add your handling code here:
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_rSButtonIconOne4ActionPerformed

    private void JEstadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JEstadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JEstadosActionPerformed

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
            java.util.logging.Logger.getLogger(AgregarBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarBanco().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JCodigoDisponible;
    private rojerusan.RSComboMetro JEstados;
    private rojeru_san.RSMTextFull Nombre;
    private javax.swing.JLabel avisoT1;
    private newscomponents.RSButtonIcon_new guardar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel34;
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
    private javax.swing.JPanel linesetting4;
    private javax.swing.JPanel linesetting5;
    private javax.swing.JLabel producto;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne4;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new3;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rojerusan.RSLabelIcon rSLabelIcon1;
    private rojerusan.RSLabelIcon rSLabelIcon12;
    private rojerusan.RSLabelIcon rSLabelIcon17;
    private rojerusan.RSLabelIcon rSLabelIcon6;
    private rojerusan.RSLabelIcon rSLabelIcon8;
    private rojeru_san.rspanel.RSPanelCircle rSPanelCircle1;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity1;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity2;
    // End of variables declaration//GEN-END:variables
}
