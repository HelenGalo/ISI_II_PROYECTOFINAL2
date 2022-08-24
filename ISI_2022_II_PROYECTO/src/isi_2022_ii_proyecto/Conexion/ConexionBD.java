/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi_2022_ii_proyecto.Conexion;

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
import isi_2022_ii_proyecto.AgregarInventario;
import isi_2022_ii_proyecto.AgregarLogistica;
import isi_2022_ii_proyecto.AgregarProducto;
import isi_2022_ii_proyecto.AgregarProveedor;
import isi_2022_ii_proyecto.AgregarUsuario;
import isi_2022_ii_proyecto.AgregarBanco;
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
import isi_2022_ii_proyecto.POSCLOUD;
import isi_2022_ii_proyecto.POS1;
import isi_2022_ii_proyecto.PrecioHistorico;
import isi_2022_ii_proyecto.Productos;
import isi_2022_ii_proyecto.Proveedores;
import isi_2022_ii_proyecto.RecuperarContraseña;
import isi_2022_ii_proyecto.Recursos.VentanaErrorDeconexion;
import isi_2022_ii_proyecto.Recursos.VentanaErrorDeconexion3;
import isi_2022_ii_proyecto.TablaProductos;
import isi_2022_ii_proyecto.TablaProductosCompras;
import isi_2022_ii_proyecto.TipoVenta;
import isi_2022_ii_proyecto.Usuario;
import isi_2022_ii_proyecto.Ventas;
import isi_2022_ii_proyecto.VentasEmpleado;
import isi_2022_ii_proyecto.VerificarOrden;
import isi_2022_ii_proyecto.VerificarOrdenADomicilio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Edwin Rafael
 */
public class ConexionBD {
    Inicio inicio=null;
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

    public void setConectar(Connection conectar) {
        this.conectar = conectar;
    }
    ActualizarAlmacen actualizaralmacen=null;
    ActualizarBanco ab = null;
    ActualizarCaja ac = null;
    ActualizarCategoria aca = null;
    ActualizarCliente acl = null;
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
    POS1 pos1=null;
    TablaProductosCompras tbc = null;
    
    boolean pass = true;

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public void setPos1(POS1 pos1) {
        this.pos1 = pos1;
    }

    public void setTbc(TablaProductosCompras tbc) {
        this.tbc = tbc;
    }
    
    
    
    
    

    public void setInicio(Inicio inicio) {
        this.inicio = inicio;
    }
    
    
     Connection conectar = null;
     
     
     

    public Connection conexion() {
        
        
        
        if(pass==true){
             try {
            String url = "jdbc:mysql://162.241.62.192:3306/fhopenet_GEVEC?useSSL=false&allowPublicKeyRetrieval=true";
            String usuario = "fhopenet_gestionador";
            String password = "#+6ePODf*=,}";
            
            conectar = DriverManager.getConnection(url,usuario,password);
                    
            
                    
        }catch(SQLException e){
            if(e.getErrorCode()==0){
                if(inicio!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setInicio(inicio);
                   v.setVisible(true);
                }else{
                    if(actualizaralmacen!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setActualizaralmacen(actualizaralmacen);
                   v.setVisible(true);
                    }else{
                        if(ab!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setAb(ab);
                   v.setVisible(true);
                    }else{
                        if(ac!=null){
                            VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                            v.setAc(ac);
                            v.setVisible(true);
                    }else{
                        if(aca!=null){
                            VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                            v.setAca(aca);
                            v.setVisible(true);
                    }else{
                        if(acl!=null){
                            VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                            v.setAcl(acl);
                            v.setVisible(true);
                    }else{
                        if(ae!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setAe(ae);
                   v.setVisible(true);
                    }else{
                        if(aenv!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAenv(aenv);
                        v.setVisible(true);
                    }else{
                        if(ain!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAin(ain);
                        v.setVisible(true);
                    }else{
                        if(al!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAl(al);
                        v.setVisible(true);
                    }else{
                        if(anc!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAnc(anc);
                        v.setVisible(true);
                    }else{
                        if(ap!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAp(ap);
                        v.setVisible(true);
                    }else{
                        if(apro!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setApro(apro);
                        v.setVisible(true);
                    }else{
                        if(au!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAu(au);
                        v.setVisible(true);
                    }else{
                        if(aal!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAal(aal);
                        v.setVisible(true);
                    }else{
                        if(acaja!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAcaja(acaja);
                        v.setVisible(true);
                    }else{
                        if(acat!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAcat(acat);
                        v.setVisible(true);
                    }else{
                        if(agcliente!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAgcliente(agcliente);
                        v.setVisible(true);
                    }else{
                        if(acban!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAcban(acban);
                        v.setVisible(true);
                    }else{
                        if(aem!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAem(aem);
                        v.setVisible(true);
                    }else{
                        if(aenvio!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAenvio(aenvio);
                        v.setVisible(true);
                    }else{
                        if(ainven!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAinven(ainven);
                        v.setVisible(true);
                    }else{
                        if(aglogi!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAglogi(aglogi);
                        v.setVisible(true);
                    }else{
                        if(agprod!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAgprod(agprod);
                        v.setVisible(true);
                    }else{
                        if(agprovee!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAgprovee(agprovee);
                        v.setVisible(true);
                    }else{
                        if(almacen  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAlmacen(almacen);
                        v.setVisible(true);
                    }else{
                        if(apertcajas !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setApertcajas(apertcajas);
                        v.setVisible(true);
                    }else{
                        if(banco !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBanco(banco);
                        v.setVisible(true);
                    }else{
                        if(bm !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBm(bm);
                        v.setVisible(true);
                    }else{
                        if(bc !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBc(bc);
                        v.setVisible(true);
                    }else{
                        if(bcb !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBcb(bcb);
                        v.setVisible(true);
                    }else{
                        if(bproducto !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBproducto(bproducto);
                        v.setVisible(true);
                    }else{
                        if(caja!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCaja(caja);
                        v.setVisible(true);
                    }else{
                        if(cambiarcontra !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCambiarcontra(cambiarcontra);
                        v.setVisible(true);
                    }else{
                        if(cate  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCate(cate);
                        v.setVisible(true);
                    }else{
                        if(cliente  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCliente(cliente);
                        v.setVisible(true);
                    }else{
                        if(compras  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCompras(compras);
                        v.setVisible(true);
                    }else{
                        if(das  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setDas(das);
                        v.setVisible(true);
                    }else{
                        if(empleado   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setEmpleado(empleado);
                        v.setVisible(true);
                    }else{
                        if(envio   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setEnvio(envio);
                        v.setVisible(true);
                    }else{
                        if(facturas   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setFacturas(facturas);
                        v.setVisible(true);
                    }else{
                        if(gnerarve   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setGnerarve(gnerarve);
                        v.setVisible(true);
                    }else{
                        if(inventario   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setInventario(inventario);
                        v.setVisible(true);
                    }else{
                        if(logistica   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setLogistica(logistica);
                        v.setVisible(true);
                    }else{
                        if(menu   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setMenu(menu);
                        v.setVisible(true);
                    }else{
                        if(mcuentb   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setMcuentb(mcuentb);
                        v.setVisible(true);
                    }else{
                        if(pos   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setPos(pos);
                        v.setVisible(true);
                    }else{
                        if(precioh    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setPrecioh(precioh);
                        v.setVisible(true);
                    }else{
                        if(productos    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setProductos(productos);
                        v.setVisible(true);
                    }else{
                        if(proveedores    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setProveedores(proveedores);
                        v.setVisible(true);
                    }else{
                        if(rcontrase    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setRcontrase(rcontrase);
                        v.setVisible(true);
                    }else{
                        if(tproductos    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setTproductos(tproductos);
                        v.setVisible(true);
                    }else{
                        if(tventa    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setTventa(tventa);
                        v.setVisible(true);
                    }else{
                        if(usuario!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setUsuario(usuario);
                        v.setVisible(true);
                    }else{
                        if(ventas!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVentas(ventas);
                        v.setVisible(true);
                    }else{
                        if(ventaempleado!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVentaempleado(ventaempleado);
                        v.setVisible(true);
                    }else{
                        if(vorden !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVorden(vorden);
                        v.setVisible(true);
                    }else{
                        if(vodomicilio  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVodomicilio(vodomicilio);
                        v.setVisible(true);
                        }else{
                          if(pos1  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setPos1(pos1);
                        v.setVisible(true);  
                        }else{
                             if(tbc!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setTbc(tbc);
                        v.setVisible(true);  
                        }
                          } 
                          }            
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                }else{
                if(inicio!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setInicio(inicio);
                   v.setVisible(true);
                }else{
                    if(actualizaralmacen!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setActualizaralmacen(actualizaralmacen);
                   v.setVisible(true);
                    }else{
                        if(ab!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setAb(ab);
                   v.setVisible(true);
                    }else{
                        if(ac!=null){
                            VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                            v.setAc(ac);
                            v.setVisible(true);
                    }else{
                        if(aca!=null){
                            VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                            v.setAca(aca);
                            v.setVisible(true);
                    }else{
                        if(acl!=null){
                            VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                            v.setAcl(acl);
                            v.setVisible(true);
                    }else{
                        if(ae!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setAe(ae);
                   v.setVisible(true);
                    }else{
                        if(aenv!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAenv(aenv);
                        v.setVisible(true);
                    }else{
                        if(ain!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAin(ain);
                        v.setVisible(true);
                    }else{
                        if(al!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAl(al);
                        v.setVisible(true);
                    }else{
                        if(anc!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAnc(anc);
                        v.setVisible(true);
                    }else{
                        if(ap!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAp(ap);
                        v.setVisible(true);
                    }else{
                        if(apro!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setApro(apro);
                        v.setVisible(true);
                    }else{
                        if(au!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAu(au);
                        v.setVisible(true);
                    }else{
                        if(aal!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAal(aal);
                        v.setVisible(true);
                    }else{
                        if(acaja!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAcaja(acaja);
                        v.setVisible(true);
                    }else{
                        if(acat!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAcat(acat);
                        v.setVisible(true);
                    }else{
                        if(agcliente!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAgcliente(agcliente);
                        v.setVisible(true);
                    }else{
                        if(acban!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAcban(acban);
                        v.setVisible(true);
                    }else{
                        if(aem!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAem(aem);
                        v.setVisible(true);
                    }else{
                        if(aenvio!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAenvio(aenvio);
                        v.setVisible(true);
                    }else{
                        if(ainven!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAinven(ainven);
                        v.setVisible(true);
                    }else{
                        if(aglogi!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAglogi(aglogi);
                        v.setVisible(true);
                    }else{
                        if(agprod!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAgprod(agprod);
                        v.setVisible(true);
                    }else{
                        if(agprovee!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAgprovee(agprovee);
                        v.setVisible(true);
                    }else{
                        if(almacen  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAlmacen(almacen);
                        v.setVisible(true);
                    }else{
                        if(apertcajas !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setApertcajas(apertcajas);
                        v.setVisible(true);
                    }else{
                        if(banco !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBanco(banco);
                        v.setVisible(true);
                    }else{
                        if(bm !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBm(bm);
                        v.setVisible(true);
                    }else{
                        if(bc !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBc(bc);
                        v.setVisible(true);
                    }else{
                        if(bcb !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBcb(bcb);
                        v.setVisible(true);
                    }else{
                        if(bproducto !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBproducto(bproducto);
                        v.setVisible(true);
                    }else{
                        if(caja!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCaja(caja);
                        v.setVisible(true);
                    }else{
                        if(cambiarcontra !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCambiarcontra(cambiarcontra);
                        v.setVisible(true);
                    }else{
                        if(cate  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCate(cate);
                        v.setVisible(true);
                    }else{
                        if(cliente  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCliente(cliente);
                        v.setVisible(true);
                    }else{
                        if(compras  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCompras(compras);
                        v.setVisible(true);
                    }else{
                        if(das  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setDas(das);
                        v.setVisible(true);
                    }else{
                        if(empleado   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setEmpleado(empleado);
                        v.setVisible(true);
                    }else{
                        if(envio   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setEnvio(envio);
                        v.setVisible(true);
                    }else{
                        if(facturas   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setFacturas(facturas);
                        v.setVisible(true);
                    }else{
                        if(gnerarve   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setGnerarve(gnerarve);
                        v.setVisible(true);
                    }else{
                        if(inventario   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setInventario(inventario);
                        v.setVisible(true);
                    }else{
                        if(logistica   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setLogistica(logistica);
                        v.setVisible(true);
                    }else{
                        if(menu   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setMenu(menu);
                        v.setVisible(true);
                    }else{
                        if(mcuentb   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setMcuentb(mcuentb);
                        v.setVisible(true);
                    }else{
                        if(pos   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setPos(pos);
                        v.setVisible(true);
                    }else{
                        if(precioh    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setPrecioh(precioh);
                        v.setVisible(true);
                    }else{
                        if(productos    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setProductos(productos);
                        v.setVisible(true);
                    }else{
                        if(proveedores    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setProveedores(proveedores);
                        v.setVisible(true);
                    }else{
                        if(rcontrase    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setRcontrase(rcontrase);
                        v.setVisible(true);
                    }else{
                        if(tproductos    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setTproductos(tproductos);
                        v.setVisible(true);
                    }else{
                        if(tventa    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setTventa(tventa);
                        v.setVisible(true);
                    }else{
                        if(usuario!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setUsuario(usuario);
                        v.setVisible(true);
                    }else{
                        if(ventas!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVentas(ventas);
                        v.setVisible(true);
                    }else{
                        if(ventaempleado!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVentaempleado(ventaempleado);
                        v.setVisible(true);
                    }else{
                        if(vorden !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVorden(vorden);
                        v.setVisible(true);
                    }else{
                        if(vodomicilio  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVodomicilio(vodomicilio);
                        v.setVisible(true);
                        }else{
                          if(pos1  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setPos1(pos1);
                        v.setVisible(true);  
                        }else{
                             if(tbc!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setTbc(tbc);
                        v.setVisible(true);  
                        }
                          } 
                          }            
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                
            }
        
      
            
        }
        }else{
            
                activarpasse();
        }
    
       
        return conectar;
        
        
    }
    
    
    public void errores(){
    
                if(inicio!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setInicio(inicio);
                   v.setVisible(true);
                }else{
                    if(actualizaralmacen!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setActualizaralmacen(actualizaralmacen);
                   v.setVisible(true);
                    }else{
                        if(ab!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setAb(ab);
                   v.setVisible(true);
                    }else{
                        if(ac!=null){
                            VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                            v.setAc(ac);
                            v.setVisible(true);
                    }else{
                        if(aca!=null){
                            VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                            v.setAca(aca);
                            v.setVisible(true);
                    }else{
                        if(acl!=null){
                            VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                            v.setAcl(acl);
                            v.setVisible(true);
                    }else{
                        if(ae!=null){
                   VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                   v.setAe(ae);
                   v.setVisible(true);
                    }else{
                        if(aenv!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAenv(aenv);
                        v.setVisible(true);
                    }else{
                        if(ain!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAin(ain);
                        v.setVisible(true);
                    }else{
                        if(al!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAl(al);
                        v.setVisible(true);
                    }else{
                        if(anc!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAnc(anc);
                        v.setVisible(true);
                    }else{
                        if(ap!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAp(ap);
                        v.setVisible(true);
                    }else{
                        if(apro!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setApro(apro);
                        v.setVisible(true);
                    }else{
                        if(au!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAu(au);
                        v.setVisible(true);
                    }else{
                        if(aal!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAal(aal);
                        v.setVisible(true);
                    }else{
                        if(acaja!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAcaja(acaja);
                        v.setVisible(true);
                    }else{
                        if(acat!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAcat(acat);
                        v.setVisible(true);
                    }else{
                        if(agcliente!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAgcliente(agcliente);
                        v.setVisible(true);
                    }else{
                        if(acban!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAcban(acban);
                        v.setVisible(true);
                    }else{
                        if(aem!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAem(aem);
                        v.setVisible(true);
                    }else{
                        if(aenvio!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAenvio(aenvio);
                        v.setVisible(true);
                    }else{
                        if(ainven!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAinven(ainven);
                        v.setVisible(true);
                    }else{
                        if(aglogi!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAglogi(aglogi);
                        v.setVisible(true);
                    }else{
                        if(agprod!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAgprod(agprod);
                        v.setVisible(true);
                    }else{
                        if(agprovee!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAgprovee(agprovee);
                        v.setVisible(true);
                    }else{
                        if(almacen  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setAlmacen(almacen);
                        v.setVisible(true);
                    }else{
                        if(apertcajas !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setApertcajas(apertcajas);
                        v.setVisible(true);
                    }else{
                        if(banco !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBanco(banco);
                        v.setVisible(true);
                    }else{
                        if(bm !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBm(bm);
                        v.setVisible(true);
                    }else{
                        if(bc !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBc(bc);
                        v.setVisible(true);
                    }else{
                        if(bcb !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBcb(bcb);
                        v.setVisible(true);
                    }else{
                        if(bproducto !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setBproducto(bproducto);
                        v.setVisible(true);
                    }else{
                        if(caja!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCaja(caja);
                        v.setVisible(true);
                    }else{
                        if(cambiarcontra !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCambiarcontra(cambiarcontra);
                        v.setVisible(true);
                    }else{
                        if(cate  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCate(cate);
                        v.setVisible(true);
                    }else{
                        if(cliente  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCliente(cliente);
                        v.setVisible(true);
                    }else{
                        if(compras  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setCompras(compras);
                        v.setVisible(true);
                    }else{
                        if(das  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setDas(das);
                        v.setVisible(true);
                    }else{
                        if(empleado   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setEmpleado(empleado);
                        v.setVisible(true);
                    }else{
                        if(envio   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setEnvio(envio);
                        v.setVisible(true);
                    }else{
                        if(facturas   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setFacturas(facturas);
                        v.setVisible(true);
                    }else{
                        if(gnerarve   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setGnerarve(gnerarve);
                        v.setVisible(true);
                    }else{
                        if(inventario   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setInventario(inventario);
                        v.setVisible(true);
                    }else{
                        if(logistica   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setLogistica(logistica);
                        v.setVisible(true);
                    }else{
                        if(menu   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setMenu(menu);
                        v.setVisible(true);
                    }else{
                        if(mcuentb   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setMcuentb(mcuentb);
                        v.setVisible(true);
                    }else{
                        if(pos   !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setPos(pos);
                        v.setVisible(true);
                    }else{
                        if(precioh    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setPrecioh(precioh);
                        v.setVisible(true);
                    }else{
                        if(productos    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setProductos(productos);
                        v.setVisible(true);
                    }else{
                        if(proveedores    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setProveedores(proveedores);
                        v.setVisible(true);
                    }else{
                        if(rcontrase    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setRcontrase(rcontrase);
                        v.setVisible(true);
                    }else{
                        if(tproductos    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setTproductos(tproductos);
                        v.setVisible(true);
                    }else{
                        if(tventa    !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setTventa(tventa);
                        v.setVisible(true);
                    }else{
                        if(usuario!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setUsuario(usuario);
                        v.setVisible(true);
                    }else{
                        if(ventas!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVentas(ventas);
                        v.setVisible(true);
                    }else{
                        if(ventaempleado!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVentaempleado(ventaempleado);
                        v.setVisible(true);
                    }else{
                        if(vorden !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVorden(vorden);
                        v.setVisible(true);
                    }else{
                        if(vodomicilio  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setVodomicilio(vodomicilio);
                        v.setVisible(true);
                        }else{
                          if(pos1  !=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setPos1(pos1);
                        v.setVisible(true);  
                        }else{
                             if(tbc!=null){
                        VentanaErrorDeconexion v = new VentanaErrorDeconexion();
                        v.setTbc(tbc);
                        v.setVisible(true);  
                        }
                          } 
                          }            
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                        }
                }
    
    
    public void activarpasse(){
        
        VentanaErrorDeconexion3 f = new VentanaErrorDeconexion3();
        f.setB(this);
        f.ejecutar();
  
        
        
    }
    
    
}

