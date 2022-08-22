/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi_2022_ii_proyecto.Recursos;

import RSMaterialComponent.RSLabelIcon;
import RSMaterialComponent.RSPanelOpacity;
import isi_2022_ii_proyecto.ActualizarAlmacen;
import isi_2022_ii_proyecto.ActualizarBanco;
import isi_2022_ii_proyecto.ActualizarCaja;
import isi_2022_ii_proyecto.ActualizarCategoria;
import isi_2022_ii_proyecto.ActualizarCliente;
import isi_2022_ii_proyecto.ActualizarEmpleado;
import isi_2022_ii_proyecto.ActualizarEnvio;
import isi_2022_ii_proyecto.ActualizarInventario;
import isi_2022_ii_proyecto.ActualizarLogistica;
import isi_2022_ii_proyecto.ActualizarNuevaContrase;
import isi_2022_ii_proyecto.ActualizarProductos;
import isi_2022_ii_proyecto.ActualizarProveedor;
import isi_2022_ii_proyecto.ActualizarUsuarios;
import isi_2022_ii_proyecto.AgregarAlmacen;
import isi_2022_ii_proyecto.AgregarCaja;
import isi_2022_ii_proyecto.AgregarCategoria;
import isi_2022_ii_proyecto.AgregarClientes;
import isi_2022_ii_proyecto.AgregarCuentaBancaria;
import isi_2022_ii_proyecto.AgregarEmpleado;
import isi_2022_ii_proyecto.AgregarEnvio;
import isi_2022_ii_proyecto.AgregarBanco;
import isi_2022_ii_proyecto.AgregarInventario;
import isi_2022_ii_proyecto.AgregarLogistica;
import isi_2022_ii_proyecto.AgregarProducto;
import isi_2022_ii_proyecto.AgregarProveedor;
import isi_2022_ii_proyecto.AgregarUsuario;
import isi_2022_ii_proyecto.Almacen;
import isi_2022_ii_proyecto.AperturarCajas;
import isi_2022_ii_proyecto.Banco;
import isi_2022_ii_proyecto.BancosMovimientos;
import isi_2022_ii_proyecto.BuscarCaja;
import isi_2022_ii_proyecto.BuscarCuentaB;
import isi_2022_ii_proyecto.BuscarProducto;
import isi_2022_ii_proyecto.Caja;
import isi_2022_ii_proyecto.CambiarContrase;
import isi_2022_ii_proyecto.Categoria;
import isi_2022_ii_proyecto.Cliente;
import isi_2022_ii_proyecto.Compras;
import isi_2022_ii_proyecto.Conexion.ConexionBD;
import isi_2022_ii_proyecto.Dashboard;
import isi_2022_ii_proyecto.Empleados;
import isi_2022_ii_proyecto.Envio;
import isi_2022_ii_proyecto.Facturas;
import isi_2022_ii_proyecto.GenerarVenta;
import isi_2022_ii_proyecto.Inicio;
import isi_2022_ii_proyecto.Inventario;
import isi_2022_ii_proyecto.Logistica;
import isi_2022_ii_proyecto.Menu;
import isi_2022_ii_proyecto.ModificarCuentaB;
import isi_2022_ii_proyecto.POS;
import isi_2022_ii_proyecto.PrecioHistorico;
import isi_2022_ii_proyecto.Productos;
import isi_2022_ii_proyecto.Proveedores;
import isi_2022_ii_proyecto.RecuperarContraseña;
import isi_2022_ii_proyecto.TablaProductos;
import isi_2022_ii_proyecto.TipoVenta;
import isi_2022_ii_proyecto.Usuario;
import isi_2022_ii_proyecto.Ventas;
import isi_2022_ii_proyecto.VentasEmpleado;
import isi_2022_ii_proyecto.VerificarOrden;
import isi_2022_ii_proyecto.VerificarOrdenADomicilio;
import java.sql.Connection;
import javax.swing.JLabel;
import javax.swing.JPanel;
import rojeru_san.complementos.RSUtilities;
import rojerusan.RSButtonRound;

/**
 *
 * @author Edwin Rafael
 */
public class VentanaErrorDeconexion extends javax.swing.JFrame {
 
    Inicio inicio=null;
    ActualizarAlmacen actualizaralmacen=null;
    ActualizarBanco ab = null;
    ActualizarCaja ac = null;
    ActualizarCategoria aca = null;
    ActualizarCliente acl = null;
    AgregarBanco agregarbanco=null;

    public void setAgregarbanco(AgregarBanco agregarbanco) {
        this.agregarbanco = agregarbanco;
    }

    public void setActualizaralmacen(ActualizarAlmacen actualizaralmacen) {
        this.actualizaralmacen = actualizaralmacen;
    }

    public void setAb(ActualizarBanco ab) {
        this.ab = ab;
    }

    public void setAc(ActualizarCaja ac) {
        this.ac = ac;
    }

    public void setAca(ActualizarCategoria aca) {
        this.aca = aca;
    }

    public void setAcl(ActualizarCliente acl) {
        this.acl = acl;
    }

    public void setAe(ActualizarEmpleado ae) {
        this.ae = ae;
    }

    public void setAenv(ActualizarEnvio aenv) {
        this.aenv = aenv;
    }

    public void setAin(ActualizarInventario ain) {
        this.ain = ain;
    }

    public void setAl(ActualizarLogistica al) {
        this.al = al;
    }

    public void setAnc(ActualizarNuevaContrase anc) {
        this.anc = anc;
    }

    public void setAp(ActualizarProductos ap) {
        this.ap = ap;
    }

    public void setApro(ActualizarProveedor apro) {
        this.apro = apro;
    }

    public void setAu(ActualizarUsuarios au) {
        this.au = au;
    }

    public void setAal(AgregarAlmacen aal) {
        this.aal = aal;
    }

    public void setAcaja(AgregarCaja acaja) {
        this.acaja = acaja;
    }

    public void setAcat(AgregarCategoria acat) {
        this.acat = acat;
    }

    public void setAgcliente(AgregarClientes agcliente) {
        this.agcliente = agcliente;
    }

    public void setAcban(AgregarCuentaBancaria acban) {
        this.acban = acban;
    }

    public void setAem(AgregarEmpleado aem) {
        this.aem = aem;
    }

    public void setAenvio(AgregarEnvio aenvio) {
        this.aenvio = aenvio;
    }

    public void setAinven(AgregarInventario ainven) {
        this.ainven = ainven;
    }

    public void setAglogi(AgregarLogistica aglogi) {
        this.aglogi = aglogi;
    }

    public void setAgprod(AgregarProducto agprod) {
        this.agprod = agprod;
    }

    public void setAgprovee(AgregarProveedor agprovee) {
        this.agprovee = agprovee;
    }

    public void setAgusuario(AgregarUsuario agusuario) {
        this.agusuario = agusuario;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public void setApertcajas(AperturarCajas apertcajas) {
        this.apertcajas = apertcajas;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void setBm(BancosMovimientos bm) {
        this.bm = bm;
    }

    public void setBc(BuscarCaja bc) {
        this.bc = bc;
    }

    public void setBcb(BuscarCuentaB bcb) {
        this.bcb = bcb;
    }

    public void setBproducto(BuscarProducto bproducto) {
        this.bproducto = bproducto;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public void setCambiarcontra(CambiarContrase cambiarcontra) {
        this.cambiarcontra = cambiarcontra;
    }

    public void setCate(Categoria cate) {
        this.cate = cate;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCompras(Compras compras) {
        this.compras = compras;
    }

    public void setDas(Dashboard das) {
        this.das = das;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public void setFacturas(Facturas facturas) {
        this.facturas = facturas;
    }

    public void setGnerarve(GenerarVenta gnerarve) {
        this.gnerarve = gnerarve;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setLogistica(Logistica logistica) {
        this.logistica = logistica;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setMcuentb(ModificarCuentaB mcuentb) {
        this.mcuentb = mcuentb;
    }

    public void setPos(POS pos) {
        this.pos = pos;
    }

    public void setPrecioh(PrecioHistorico precioh) {
        this.precioh = precioh;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public void setRcontrase(RecuperarContraseña rcontrase) {
        this.rcontrase = rcontrase;
    }

    public void setTproductos(TablaProductos tproductos) {
        this.tproductos = tproductos;
    }

    public void setTventa(TipoVenta tventa) {
        this.tventa = tventa;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    public void setVentaempleado(VentasEmpleado ventaempleado) {
        this.ventaempleado = ventaempleado;
    }

    public void setVorden(VerificarOrden vorden) {
        this.vorden = vorden;
    }

    public void setVodomicilio(VerificarOrdenADomicilio vodomicilio) {
        this.vodomicilio = vodomicilio;
    }

    public void setjLabel6(JLabel jLabel6) {
        this.jLabel6 = jLabel6;
    }

    public void setjLabel7(JLabel jLabel7) {
        this.jLabel7 = jLabel7;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public void setrSButtonRound1(RSButtonRound rSButtonRound1) {
        this.rSButtonRound1 = rSButtonRound1;
    }

    public void setrSLabelIcon1(RSLabelIcon rSLabelIcon1) {
        this.rSLabelIcon1 = rSLabelIcon1;
    }

    public void setrSPanelOpacity1(RSPanelOpacity rSPanelOpacity1) {
        this.rSPanelOpacity1 = rSPanelOpacity1;
    }
    ActualizarEmpleado ae = null;
    ActualizarEnvio aenv = null;
    ActualizarInventario ain =null;
    ActualizarLogistica al = null;
    ActualizarNuevaContrase anc = null;
    ActualizarProductos ap = null;
    ActualizarProveedor apro = null;
    ActualizarUsuarios au = null;
    AgregarAlmacen aal = null;
    AgregarCaja acaja = null;
    AgregarCategoria acat = null;
    AgregarClientes agcliente = null;
    AgregarCuentaBancaria acban = null;
    AgregarEmpleado aem = null;
    AgregarEnvio aenvio = null;
    AgregarInventario ainven = null;
    AgregarLogistica aglogi = null;
    AgregarProducto agprod = null;
    AgregarProveedor agprovee = null;
    AgregarUsuario agusuario = null;
    Almacen almacen = null;
    AperturarCajas  apertcajas=null;
    Banco banco = null;
    BancosMovimientos bm = null;
    BuscarCaja bc = null;
    BuscarCuentaB bcb = null;
    BuscarProducto bproducto =null;
    Caja caja = null;
    CambiarContrase cambiarcontra=null;
    Categoria cate = null;
    Cliente cliente = null;
    Compras compras = null;
    Dashboard das = null;
    Empleados empleado = null;
    Envio envio = null;
    Facturas facturas = null;
    GenerarVenta gnerarve = null;
    Inventario inventario =null;
    Logistica logistica = null;
    Menu menu = null;
    ModificarCuentaB mcuentb= null;
    POS pos = null;
    PrecioHistorico precioh = null;
    Productos productos = null;
    Proveedores proveedores = null;
    RecuperarContraseña rcontrase = null;
    TablaProductos tproductos = null;
    TipoVenta tventa = null;
    Usuario usuario = null;
    Ventas ventas = null;
    VentasEmpleado ventaempleado = null;
    VerificarOrden vorden = null;
    VerificarOrdenADomicilio vodomicilio = null;
 
  

    public void setInicio(Inicio inicio) {
        this.inicio = inicio;
    }

   

    /**
     * Creates new form VentanaEmergente1
     */
    public VentanaErrorDeconexion() {
        RSUtilities.setFullScreenJFrame(this);
        initComponents();
        RSUtilities.setOpaqueWindow(this, false);
        RSUtilities.setOpacityComponent(this.jPanel1, 150);
    }
    
    

    
    
    public void reconectarpantalla(){
        if(inicio!=null){
                   inicio.conectar();
                   this.dispose();
                }else{
                    if(actualizaralmacen!=null){
                        actualizaralmacen.conectar();
                        this.dispose();
                   
                    }else{
                        if(ab!=null){
                            ab.conectar();
                            this.dispose();
                   
                    }else{
                        if(ac!=null){
                            ac.conectar();
                            this.dispose();
                    }else{
                        if(aca!=null){
                            aca.conectar();
                            this.dispose();
                    }else{
                        if(acl!=null){
                            acl.conectar();
                            this.dispose();
                    }else{
                        if(ae!=null){
                            ae.conectar();
                            this.dispose();
                    }else{
                        if(aenv!=null){
                            aenv.conectar();
                            this.dispose();
                    }else{
                        if(ain!=null){
                            ain.conectar();
                            this.dispose();
                    }else{
                        if(al!=null){
                            al.conectar();
                            this.dispose();
                    }else{
                        if(anc!=null){
                            anc.conectar();
                            this.dispose();
                    }else{
                        if(ap!=null){
                            ap.conectar();
                            this.dispose();
                    }else{
                        if(apro!=null){
                            apro.conectar()
                            this.dispose();
                    }else{
                        if(au!=null){
                            au.conectar();
                            this.dispose();
                    }else{
                        if(aal!=null){
                            aal.conectar();
                            this.dispose();
                    }else{
                        if(acaja!=null){
                            acaja.conectar();
                            this.dispose();
                    }else{
                        if(acat!=null){
                            acat.conectar();
                            this.dispose();
                    }else{
                        if(agcliente!=null){
                            agcliente.conectar();
                            this.dispose();
                    }else{
                        if(acban!=null){
                            acban.conectar();
                            this.dispose();
                    }else{
                        if(aem!=null){
                            aem.conectar();
                            this.dispose();
                    }else{
                        if(aenvio!=null){
                            aenvio.conectar();
                            this.dispose();
                    }else{
                        if(ainven!=null){
                            ainven.conectar();
                            this.dispose();
                    }else{
                        if(aglogi!=null){
                            aglogi.conectar();
                            this.dispose();
                    }else{
                        if(agprod!=null){
                            agprod.conectar();
                            this.dispose();
                    }else{
                        if(agprovee!=null){
                            agprovee.conectar();
                            this.dispose();
                    }else{
                        if(almacen  !=null){
                            almacen.conectar();
                            this.dispose();
                    }else{
                        if(apertcajas !=null){
                            apertcajas.conectar();
                            this.dispose();
                    }else{
                        if(banco !=null){
                            banco.conectar();
                            this.dispose();
                    }else{
                        if(bm !=null){
                            bm.conectar();
                            this.dispose();
                    }else{
                        if(bc !=null){
                            bc.conectar();
                            this.dispose();
                    }else{
                        if(bcb !=null){
                            bcb.conectar();
                            this.dispose();
                    }else{
                        if(bproducto !=null){
                            bproducto.conectar();
                            this.dispose();
                    }else{
                        if(caja  !=null){
                            caja.conectar()
                            this.dispose();
                    }else{
                        if(cambiarcontra !=null){
                            cambiarcontra.conectar();
                            this.dispose();
                    }else{
                        if(cate  !=null){
                            cate.conectar();
                            this.dispose();
                    }else{
                        if(cliente  !=null){
                            cliente.conectar();
                            this.dispose();
                    }else{
                        if(compras  !=null){
                            compras.conectar();
                            this.dispose();
                    }else{
                        if(das  !=null){
                            das.conectar();
                            this.dispose();
                    }else{
                        if(empleado   !=null){
                            empleado.conectar();
                            this.dispose();
                    }else{
                        if(envio   !=null){
                            envio.conectar();
                            this.dispose();
                    }else{
                        if(facturas   !=null){
                            facturas.conectar();
                            this.dispose();
                    }else{
                        if(gnerarve   !=null){
                            gnerarve.conectar();
                            this.dispose();
                    }else{
                        if(inventario   !=null){
                            inventario.conectar()
                            this.dispose();
                    }else{
                        if(logistica   !=null){
                            logistica.conectar();
                        this.dispose();
                    }else{
                        if(menu   !=null){
                            menu.conectar();
                        this.dispose();
                    }else{
                        if(mcuentb   !=null){
                            mcuentb.conectar()
                        this.dispose();
                    }else{
                        if(pos   !=null){
                            pos.conectar();
                        this.dispose();
                    }else{
                        if(precioh    !=null){
                            precioh.conectar();
                        this.dispose();
                    }else{
                        if(productos    !=null){
                            productos.conectar();
                        this.dispose();
                    }else{
                        if(proveedores    !=null){
                            proveedores.conectar();
                        this.dispose();
                    }else{
                        if(rcontrase    !=null){
                            rcontrase.conectar();
                        this.dispose();
                    }else{
                        if(tproductos    !=null){
                            tproductos.conectar();
                        this.dispose();
                    }else{
                        if(tventa    !=null){
                            tventa.conectar();
                        this.dispose();
                    }else{
                        if(usuario!=null){
                            usuario.conectar();
                        this.dispose();
                    }else{
                        if(ventas!=null){
                            ventas.conectar();
                        this.dispose();
                    }else{
                        if(ventaempleado!=null){
                            ventaempleado.conectar();
                        this.dispose();
                    }else{
                        if(vorden !=null){
                            vorden.conectar();
                        this.dispose();
                    }else{
                        if(vodomicilio  !=null){
                            vodomicilio.conectar();
                        this.dispose();
                        }else{
                            if(agregarbanco  !=null){
                            agregarbanco.conectar();
                            this.dispose();
                            }
                        }
                        
                    }
                }
                
            }
            
        }}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}
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
        rSLabelIcon1 = new RSMaterialComponent.RSLabelIcon();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rSButtonRound1 = new rojerusan.RSButtonRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        rSPanelOpacity1.setBackground(new java.awt.Color(0, 55, 133));
        rSPanelOpacity1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon1.setToolTipText("");
        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ERROR);
        rSPanelOpacity1.add(rSLabelIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 150, 140));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ERROR");
        rSPanelOpacity1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 550, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("ERROR DE CONEXION");
        rSPanelOpacity1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        rSButtonRound1.setBackground(new java.awt.Color(0, 153, 102));
        rSButtonRound1.setText("Reconectar");
        rSButtonRound1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rSButtonRound1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRound1ActionPerformed(evt);
            }
        });
        rSPanelOpacity1.add(rSButtonRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSPanelOpacity1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSPanelOpacity1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRound1ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_rSButtonRound1ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaErrorDeconexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaErrorDeconexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaErrorDeconexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaErrorDeconexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaErrorDeconexion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private rojerusan.RSButtonRound rSButtonRound1;
    private RSMaterialComponent.RSLabelIcon rSLabelIcon1;
    private RSMaterialComponent.RSPanelOpacity rSPanelOpacity1;
    // End of variables declaration//GEN-END:variables
}
