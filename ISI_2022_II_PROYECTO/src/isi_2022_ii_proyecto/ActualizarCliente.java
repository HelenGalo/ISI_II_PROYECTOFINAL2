/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package isi_2022_ii_proyecto;

import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Recursos.ColorFondo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ActualizarCliente extends javax.swing.JFrame {

    /**
     * Creates new form AgregarCliente
     */
    boolean a = true;
    String codigoe="";
    String rol="";
    ConexionBD conexion = new ConexionBD();
    Connection con = conexion.conexion();
    int id=0;
    HashMap<String, Integer> empleados = new HashMap<String, Integer>();

  
    
    public ActualizarCliente() {
        initComponents();
        buscardatos();
        listarEmpleados();
        
    }
    
    
    
    public void insertar(){
        
        int idUsuario=0;
        int IdEmpleado=0;
        String Contra="";
        int Intentos=0;
        int IdRol=0;
        String Usuario="";
        
        idUsuario = Integer.parseInt(JCodigoDisponible.getText());
        IdEmpleado = empleados.get(JComboEmpleados.getSelectedItem().toString());
        Usuario=Juser2.getText();
        System.out.println(idUsuario);
          System.out.println(IdEmpleado);
        char [] arrayC=rSMPassView1.getPassword();
        Contra= new String(arrayC);
        
        Intentos = Integer.parseInt(String.valueOf(JIntentos.getSelectedItem()));
        try {
                String sql = "SELECT r.IdRol FROM Roles r Where r.Nombre="+"'"+rol+"'";
                Statement st = (Statement) con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    IdRol=rs.getInt("IdRol");
                }
        } catch (Exception e) {
               JOptionPane.showMessageDialog(this, e.getMessage());
                
        }
            
        System.out.println(String.valueOf(idUsuario)+" "+String.valueOf(IdEmpleado)+" "+Contra+String.valueOf(Intentos)+" "+ String.valueOf(IdRol)+" "+Usuario);
        
    
        
        
        String SQL = "INSERT INTO Usuarios (IdUsuario,IdEmpleado,Contrase,Intentos,IdRol,Usuario) VALUES"
                + "(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(SQL);
            preparedStmt.setInt(1, idUsuario);
            preparedStmt.setInt (2, IdEmpleado);
            preparedStmt.setString(3, Contra);
            preparedStmt.setInt(4, Intentos);
            preparedStmt.setInt(5, IdRol);
            preparedStmt.setString(6, Usuario);
            preparedStmt.execute();
            
            JOptionPane.showMessageDialog(this, "Usuario Guardado");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        
        
       
    }
        
    public Boolean validar(){
        char [] arrayC=rSMPassView1.getPassword();
        String acceso= new String(arrayC);
        if(JCodigoDisponible.getText().isEmpty() || JComboEmpleados.getSelectedItem().toString().isEmpty() || Juser2.getText().isEmpty() 
                || JIntentos.getSelectedItem().toString().isEmpty() || acceso.isEmpty() || rol.isEmpty() ){
            
            return false;
        }else{
            return true;
        }
    }
    
  
      public void listarEmpleados(){
          String nombres="";
          String apellidos="";
          int idEmpleado=0;
          
         
         
          
          
          
 
       
       

        String SQL = "SELECT e.IdEmpleado,e.PrimerNombre,e.SegundoNombre, e.PrimerApellido,e.SegundoApellido FROM Empleados e\n" +
                    "LEFT JOIN Usuarios u ON e.IdEmpleado = u.IdEmpleado\n" +
                    "WHERE u.IdEmpleado is null;";
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                idEmpleado =rs.getInt("IdEmpleado");
                nombres =rs.getString("PrimerNombre")+" "+rs.getString("SegundoNombre");
                apellidos = rs.getString("PrimerApellido")+" "+rs.getString("SegundoApellido");
                JComboEmpleados.addItem(nombres+" "+apellidos);
                empleados.put(nombres+" "+apellidos,idEmpleado);
            }

            System.out.println(empleados);
            for(int i=1; i<=3;i++){
            JIntentos.addItem(i);
            };
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void buscardatos(){
          String SQL = "SELECT * FROM Usuarios u WHERE u.IdUsuario=(SELECT max(IdUsuario) FROM Usuarios)";
          
          
        try {
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                id = rs.getInt("IdUsuario");
               
                
            }

            
            System.out.println("SIN SUMAR"+String.valueOf(id));
            id = id +1;
            System.out.println(String.valueOf(id));
            JCodigoDisponible.setText(String.valueOf(id));
            
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
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
        rSPanelCircle1 = new rojeru_san.rspanel.RSPanelCircle();
        JCodigoDisponible = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        rSButtonIcon_new8 = new newscomponents.RSButtonIcon_new();
        rSPanelRound1 = new rojeru_san.rspanel.RSPanelRound();
        rSPanelRound2 = new rojeru_san.rspanel.RSPanelRound();
        rSRadioButtonMaterial1 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial2 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial3 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial5 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial6 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial7 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial8 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial9 = new RSMaterialComponent.RSRadioButtonMaterial();
        NombreC = new rojeru_san.RSMTextFull();
        jPanel5 = new javax.swing.JPanel();
        rSPanelOpacity2 = new RSMaterialComponent.RSPanelOpacity();
        rSLabelIcon13 = new rojerusan.RSLabelIcon();
        jLabel15 = new javax.swing.JLabel();
        rSLabelIcon14 = new rojerusan.RSLabelIcon();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        rSLabelIcon18 = new rojerusan.RSLabelIcon();
        jLabel26 = new javax.swing.JLabel();
        rSLabelIcon15 = new rojerusan.RSLabelIcon();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        CorreoC = new rojeru_san.RSMTextFull();
        rSPanelCircle2 = new rojeru_san.rspanel.RSPanelCircle();
        JCodigoDisponible1 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        rSButtonIcon_new9 = new newscomponents.RSButtonIcon_new();
        rSPanelRound3 = new rojeru_san.rspanel.RSPanelRound();
        rSPanelRound4 = new rojeru_san.rspanel.RSPanelRound();
        rSRadioButtonMaterial4 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial10 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial11 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial12 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial13 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial14 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial15 = new RSMaterialComponent.RSRadioButtonMaterial();
        rSRadioButtonMaterial16 = new RSMaterialComponent.RSRadioButtonMaterial();
        jLabel32 = new javax.swing.JLabel();
        NombreE2 = new rojeru_san.RSMTextFull();
        ApellidoC = new rojeru_san.RSMTextFull();
        TelC = new rojeru_san.RSMTextFull();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        jLabel6 = new javax.swing.JLabel();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        rSLabelHora1 = new rojeru_san.RSLabelHora();

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
        jLabel3.setText("Clientes");
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
                .addComponent(linesetting6, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addComponent(MenuIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuhide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
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
        jLabel8.setText("Agregar Clientes");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Menu Principal");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("/");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Modulo Clientes");

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
        jLabel14.setText("Listado de Clientes");

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

        jPanel3.add(rSPanelOpacity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 50));

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

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 0, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("ASIGNACION DE ROLES");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 260, 30));

        rSButtonIcon_new8.setBackground(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new8.setText("Guardar");
        rSButtonIcon_new8.setBackgroundHover(new java.awt.Color(153, 0, 255));
        rSButtonIcon_new8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rSButtonIcon_new8.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        rSButtonIcon_new8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new8ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonIcon_new8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 110, 40));

        rSPanelRound1.setColorBackground(new java.awt.Color(60, 76, 143));
        rSPanelRound1.setColorBorde(new java.awt.Color(60, 76, 143));

        rSPanelRound2.setColorBackground(new java.awt.Color(255, 255, 255));
        rSPanelRound2.setColorBorde(new java.awt.Color(60, 76, 143));

        rSRadioButtonMaterial1.setText("Gerente Comercial");
        rSRadioButtonMaterial1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial1ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial2.setText("Superusuario");
        rSRadioButtonMaterial2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial2ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial3.setText("Jefe Almacen");
        rSRadioButtonMaterial3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial3ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial5.setText("Gerente General");
        rSRadioButtonMaterial5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial5ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial6.setText("Vendedor");
        rSRadioButtonMaterial6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial6ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial7.setText("Cajero");
        rSRadioButtonMaterial7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial7ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial8.setText("Supervisor");
        rSRadioButtonMaterial8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial8ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial9.setText("Jefe Logistica");
        rSRadioButtonMaterial9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rSPanelRound2Layout = new javax.swing.GroupLayout(rSPanelRound2);
        rSPanelRound2.setLayout(rSPanelRound2Layout);
        rSPanelRound2Layout.setHorizontalGroup(
            rSPanelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelRound2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(rSPanelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rSRadioButtonMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        rSPanelRound2Layout.setVerticalGroup(
            rSPanelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelRound2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(rSRadioButtonMaterial2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSRadioButtonMaterial8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout rSPanelRound1Layout = new javax.swing.GroupLayout(rSPanelRound1);
        rSPanelRound1.setLayout(rSPanelRound1Layout);
        rSPanelRound1Layout.setHorizontalGroup(
            rSPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelRound1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(rSPanelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        rSPanelRound1Layout.setVerticalGroup(
            rSPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSPanelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(rSPanelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 100, 250, 370));

        NombreC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NombreC.setPlaceholder("Ingresa nombre");
        NombreC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreCActionPerformed(evt);
            }
        });
        jPanel3.add(NombreC, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelOpacity2.setBackground(new java.awt.Color(60, 76, 143));

        rSLabelIcon13.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon13.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        rSLabelIcon13.setName(""); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("/");

        rSLabelIcon14.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon14.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Agregar Proveedor");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Menu Principal");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("/");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Modulo Proveedores");

        rSLabelIcon18.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon18.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WIFI_TETHERING);
        rSLabelIcon18.setName(""); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("/");

        rSLabelIcon15.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon15.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
        rSLabelIcon15.setName(""); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Listado de Proveedores");
        jLabel27.setToolTipText("");

        rSPanelOpacity2.setLayer(rSLabelIcon13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(rSLabelIcon14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(rSLabelIcon18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel26, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(rSLabelIcon15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rSPanelOpacity2.setLayer(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout rSPanelOpacity2Layout = new javax.swing.GroupLayout(rSPanelOpacity2);
        rSPanelOpacity2.setLayout(rSPanelOpacity2Layout);
        rSPanelOpacity2Layout.setHorizontalGroup(
            rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(rSLabelIcon14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addGap(9, 9, 9)
                .addComponent(jLabel15)
                .addGap(12, 12, 12)
                .addComponent(rSLabelIcon18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addGap(13, 13, 13)
                .addComponent(rSLabelIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addContainerGap())
        );
        rSPanelOpacity2Layout.setVerticalGroup(
            rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelOpacity2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSLabelIcon18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelIcon14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSLabelIcon13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rSPanelOpacity2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel5.add(rSPanelOpacity2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 50));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(153, 0, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Nombre Cliente");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 190, 30));

        CorreoC.setPlaceholder("Ingresar Correo Electronico");
        CorreoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorreoCActionPerformed(evt);
            }
        });
        jPanel5.add(CorreoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, -1, -1));

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

        jPanel5.add(rSPanelCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 70, 70));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(153, 0, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("ASIGNACION DE ROLES");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 260, 30));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(153, 0, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Telefono");
        jPanel5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 180, 20));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 0, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Apellido Cliente");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 180, 30));

        rSButtonIcon_new9.setBackground(new java.awt.Color(0, 55, 133));
        rSButtonIcon_new9.setText("Guardar");
        rSButtonIcon_new9.setBackgroundHover(new java.awt.Color(153, 0, 255));
        rSButtonIcon_new9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rSButtonIcon_new9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        rSButtonIcon_new9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIcon_new9ActionPerformed(evt);
            }
        });
        jPanel5.add(rSButtonIcon_new9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 110, 40));

        rSPanelRound3.setColorBackground(new java.awt.Color(60, 76, 143));
        rSPanelRound3.setColorBorde(new java.awt.Color(60, 76, 143));

        rSPanelRound4.setColorBackground(new java.awt.Color(255, 255, 255));
        rSPanelRound4.setColorBorde(new java.awt.Color(60, 76, 143));

        rSRadioButtonMaterial4.setText("Gerente Comercial");
        rSRadioButtonMaterial4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial4ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial10.setText("Superusuario");
        rSRadioButtonMaterial10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial10ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial11.setText("Jefe Almacen");
        rSRadioButtonMaterial11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial11ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial12.setText("Gerente General");
        rSRadioButtonMaterial12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial12ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial13.setText("Vendedor");
        rSRadioButtonMaterial13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial13ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial14.setText("Cajero");
        rSRadioButtonMaterial14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial14ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial15.setText("Supervisor");
        rSRadioButtonMaterial15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial15ActionPerformed(evt);
            }
        });

        rSRadioButtonMaterial16.setText("Jefe Logistica");
        rSRadioButtonMaterial16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSRadioButtonMaterial16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rSPanelRound4Layout = new javax.swing.GroupLayout(rSPanelRound4);
        rSPanelRound4.setLayout(rSPanelRound4Layout);
        rSPanelRound4Layout.setHorizontalGroup(
            rSPanelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelRound4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(rSPanelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rSRadioButtonMaterial4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rSRadioButtonMaterial16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        rSPanelRound4Layout.setVerticalGroup(
            rSPanelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelRound4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(rSRadioButtonMaterial10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSRadioButtonMaterial16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSRadioButtonMaterial15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout rSPanelRound3Layout = new javax.swing.GroupLayout(rSPanelRound3);
        rSPanelRound3.setLayout(rSPanelRound3Layout);
        rSPanelRound3Layout.setHorizontalGroup(
            rSPanelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelRound3Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(rSPanelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        rSPanelRound3Layout.setVerticalGroup(
            rSPanelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSPanelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(rSPanelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 100, 250, 370));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(153, 0, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Correo Electronico:");
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 180, 40));

        NombreE2.setPlaceholder("Ingresa nombre Empresas..");
        NombreE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreE2ActionPerformed(evt);
            }
        });
        jPanel5.add(NombreE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, -1));

        ApellidoC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ApellidoC.setPlaceholder("Apellido");
        ApellidoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidoCActionPerformed(evt);
            }
        });
        jPanel5.add(ApellidoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, -1, -1));

        TelC.setPlaceholder("Ingresa el numero Telefono");
        TelC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelCActionPerformed(evt);
            }
        });
        jPanel5.add(TelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 0, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("CodClientes");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 190, 40));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);
        jPanel4.add(rSLabelIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("MODULO CLIENTES");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 823, 40));

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE_OUTLINE);
        jPanel4.add(rSLabelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 60, 50));

        rSLabelHora1.setForeground(new java.awt.Color(20, 101, 187));
        jPanel4.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 10, 108, -1));

        javax.swing.GroupLayout dashboardviewLayout = new javax.swing.GroupLayout(dashboardview);
        dashboardview.setLayout(dashboardviewLayout);
        dashboardviewLayout.setHorizontalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(89, 89, 89))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dashboardviewLayout.setVerticalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 1416, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dashboardview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1362, 712));
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

    private void rSRadioButtonMaterial1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial1ActionPerformed
        // TODO add your handling code here:
          rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial1.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial1ActionPerformed

    private void rSRadioButtonMaterial2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial2ActionPerformed
        // TODO add your handling code here:
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial2.getText();
      
    }//GEN-LAST:event_rSRadioButtonMaterial2ActionPerformed

    private void rSRadioButtonMaterial3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial3ActionPerformed
        // TODO add your handling code here:
         rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial3.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial3ActionPerformed

    private void rSRadioButtonMaterial5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial5ActionPerformed
        // TODO add your handling code here:
           rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial5.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial5ActionPerformed

    private void rSRadioButtonMaterial6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial6ActionPerformed
        // TODO add your handling code here:
         rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial6.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial6ActionPerformed

    private void rSRadioButtonMaterial7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial7ActionPerformed
        // TODO add your handling code here:
        rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial7.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial7ActionPerformed

    private void rSRadioButtonMaterial8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial8ActionPerformed
        // TODO add your handling code here:
        rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial8.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial8ActionPerformed

    private void rSRadioButtonMaterial9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial9ActionPerformed
        // TODO add your handling code here:
         rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rol=rSRadioButtonMaterial9.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial9ActionPerformed

    private void rSButtonIcon_new8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new8ActionPerformed
        // TODO add your handling code here:
        if(validar()==true){
            
                insertar();
           
             
        }else{
            JOptionPane.showMessageDialog(this, "POR FAVOR LLENE O SELECCIONE LOS CAMPOS FALTANTES");
        }
       
    }//GEN-LAST:event_rSButtonIcon_new8ActionPerformed

    private void NombreCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreCActionPerformed

    private void CorreoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorreoCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CorreoCActionPerformed

    private void rSButtonIcon_new9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new9ActionPerformed
        // TODO add your handling code here:
        if(validar()==true){

            insertar();

        }else{
            JOptionPane.showMessageDialog(this, "POR FAVOR LLENE O SELECCIONE LOS CAMPOS FALTANTES");
        }
    }//GEN-LAST:event_rSButtonIcon_new9ActionPerformed

    private void rSRadioButtonMaterial4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial4ActionPerformed
        // TODO add your handling code here:
        rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial1.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial4ActionPerformed

    private void rSRadioButtonMaterial10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial10ActionPerformed
        // TODO add your handling code here:
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial2.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial10ActionPerformed

    private void rSRadioButtonMaterial11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial11ActionPerformed
        // TODO add your handling code here:
        rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial3.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial11ActionPerformed

    private void rSRadioButtonMaterial12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial12ActionPerformed
        // TODO add your handling code here:
        rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial5.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial12ActionPerformed

    private void rSRadioButtonMaterial13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial13ActionPerformed
        // TODO add your handling code here:
        rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial6.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial13ActionPerformed

    private void rSRadioButtonMaterial14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial14ActionPerformed
        // TODO add your handling code here:
        rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial7.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial14ActionPerformed

    private void rSRadioButtonMaterial15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial15ActionPerformed
        // TODO add your handling code here:
        rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rSRadioButtonMaterial9.setSelected(false);
        rol=rSRadioButtonMaterial8.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial15ActionPerformed

    private void rSRadioButtonMaterial16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSRadioButtonMaterial16ActionPerformed
        // TODO add your handling code here:
        rSRadioButtonMaterial2.setSelected(false);
        rSRadioButtonMaterial3.setSelected(false);
        rSRadioButtonMaterial1.setSelected(false);
        rSRadioButtonMaterial5.setSelected(false);
        rSRadioButtonMaterial7.setSelected(false);
        rSRadioButtonMaterial8.setSelected(false);
        rSRadioButtonMaterial6.setSelected(false);
        rol=rSRadioButtonMaterial9.getText();
    }//GEN-LAST:event_rSRadioButtonMaterial16ActionPerformed

    private void NombreE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreE2ActionPerformed

    private void ApellidoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidoCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidoCActionPerformed

    private void TelCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelCActionPerformed

    private void linesetting12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_linesetting12MouseClicked

    private void rSButtonIcon_new3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIcon_new3ActionPerformed
        // TODO add your handling code here:
        Usuario usuario = new Usuario();
        usuario.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_rSButtonIcon_new3ActionPerformed

    private void linesetting11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting11MouseExited
        // TODO add your handling code here:
        linesetting1.setBackground(new Color(0,55,133));
    }//GEN-LAST:event_linesetting11MouseExited

    private void linesetting11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting11MouseEntered
        // TODO add your handling code here:
        linesetting1.setBackground(Color.red);
    }//GEN-LAST:event_linesetting11MouseEntered

    private void linesetting9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting9MouseExited
        // TODO add your handling code here:
        linesetting.setBackground(new Color(0,55,133));
    }//GEN-LAST:event_linesetting9MouseExited

    private void linesetting9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting9MouseEntered
        // TODO add your handling code here:
        linesetting.setBackground(Color.red);
    }//GEN-LAST:event_linesetting9MouseEntered

    private void linesetting8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting8MouseExited
        // TODO add your handling code here:
        linesetting2.setBackground(new Color(0,55,133));
    }//GEN-LAST:event_linesetting8MouseExited

    private void linesetting8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting8MouseEntered
        // TODO add your handling code here:
        linesetting2.setBackground(Color.red);
    }//GEN-LAST:event_linesetting8MouseEntered

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

    private void linesetting7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting7MouseExited
        // TODO add your handling code here:
        linesetting10.setBackground(new Color(0,55,133));
    }//GEN-LAST:event_linesetting7MouseExited

    private void linesetting7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linesetting7MouseEntered
        // TODO add your handling code here:
        linesetting10.setBackground(Color.red);
    }//GEN-LAST:event_linesetting7MouseEntered
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
            java.util.logging.Logger.getLogger(ActualizarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ActualizarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSMTextFull ApellidoC;
    private rojeru_san.RSMTextFull CorreoC;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel JCodigoDisponible;
    private javax.swing.JLabel JCodigoDisponible1;
    private javax.swing.JPanel MenuIcon;
    private rojeru_san.RSMTextFull NombreC;
    private rojeru_san.RSMTextFull NombreE2;
    private rojeru_san.RSMTextFull TelC;
    private javax.swing.JPanel dashboardview;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
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
    private javax.swing.JPanel jPanel5;
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
    private newscomponents.RSButtonIcon_new rSButtonIcon_new8;
    private newscomponents.RSButtonIcon_new rSButtonIcon_new9;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rojerusan.RSLabelIcon rSLabelIcon1;
    private rojerusan.RSLabelIcon rSLabelIcon10;
    private rojerusan.RSLabelIcon rSLabelIcon11;
    private rojerusan.RSLabelIcon rSLabelIcon12;
    private rojerusan.RSLabelIcon rSLabelIcon13;
    private rojerusan.RSLabelIcon rSLabelIcon14;
    private rojerusan.RSLabelIcon rSLabelIcon15;
    private rojerusan.RSLabelIcon rSLabelIcon17;
    private rojerusan.RSLabelIcon rSLabelIcon18;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private rojerusan.RSLabelIcon rSLabelIcon3;
    private rojerusan.RSLabelIcon rSLabelIcon4;
    private rojerusan.RSLabelIcon rSLabelIcon5;
    private rojerusan.RSLabelIcon rSLabelIcon6;
    private rojerusan.RSLabelIcon rSLabelIcon7;
    private rojerusan.RSLabelIcon rSLabelIcon8;
    private rojerusan.RSLabelIcon rSLabelIcon9;
    private rojeru_san.rspanel.RSPanelCircle rSPanelCircle1;
    private rojeru_san.rspanel.RSPanelCircle rSPanelCircle2;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity1;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity2;
    private rojeru_san.rspanel.RSPanelRound rSPanelRound1;
    private rojeru_san.rspanel.RSPanelRound rSPanelRound2;
    private rojeru_san.rspanel.RSPanelRound rSPanelRound3;
    private rojeru_san.rspanel.RSPanelRound rSPanelRound4;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial1;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial10;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial11;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial12;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial13;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial14;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial15;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial16;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial2;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial3;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial4;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial5;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial6;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial7;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial8;
    private RSMaterialComponent.RSRadioButtonMaterial rSRadioButtonMaterial9;
    // End of variables declaration//GEN-END:variables
}
