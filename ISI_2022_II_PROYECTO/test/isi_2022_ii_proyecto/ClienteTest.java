/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package isi_2022_ii_proyecto;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class ClienteTest {
    
    public ClienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setUsuario method, of class Cliente.
     */
    @Test
    public void testSetUsuario() {
        System.out.println("setUsuario");
        String usuario = null;
        Cliente instance = new Cliente();
        try{
           instance.setUsuario(usuario); 
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("Usuario incorrecto."); 
        }
    }

    /**
     * Test of conectar method, of class Cliente.
     */
    @Test
    public void testConectar() {
        System.out.println("conectar");
        Cliente instance = new Cliente();
        
        try{
             instance.conectar();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of validarconexion method, of class Cliente.
     */
    @Test
    public void testValidarconexion() {
        System.out.println("validarconexion");
        Cliente instance = new Cliente();
      
         try{
          instance.validarconexion();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of conectarerror method, of class Cliente.
     */
    @Test
    public void testConectarerror() {
        System.out.println("conectarerror");
        Cliente instance = new Cliente();
         try{
               instance.conectarerror();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of conectarinicio method, of class Cliente.
     */
    @Test
    public void testConectarinicio() {
        System.out.println("conectarinicio");
        Cliente instance = new Cliente();
        try{
             instance.conectarinicio(); 
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of conectarsinerror method, of class Cliente.
     */
    @Test
    public void testConectarsinerror() {
        System.out.println("conectarsinerror");
        Cliente instance = new Cliente();
        try{
           instance.conectarsinerror();  
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
       
        
    }

    /**
     * Test of deshabilitar method, of class Cliente.
     */
    @Test
    public void testDeshabilitar() {
        System.out.println("deshabilitar");
        Cliente instance = new Cliente();
        instance.deshabilitar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changecolor method, of class Cliente.
     */
    @Test
    public void testChangecolor() {
        System.out.println("changecolor");
        JPanel hover = null;
        Color rand = null;
        Cliente instance = new Cliente();
        instance.changecolor(hover, rand);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listar method, of class Cliente.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        Cliente instance = new Cliente();
         try{
        instance.listar();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of listarh method, of class Cliente.
     */
    @Test
    public void testListarh() {
        System.out.println("listarh");
        Cliente instance = new Cliente();
           try{
           instance.listarh();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of listardh method, of class Cliente.
     */
    @Test
    public void testListardh() {
        System.out.println("listardh");
        Cliente instance = new Cliente();
           try{
        instance.listardh();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of limpiartabla method, of class Cliente.
     */
    @Test
    public void testLimpiartabla() {
        System.out.println("limpiartabla");
        Cliente instance = new Cliente();
       
            try{
       instance.limpiartabla();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of Clickmenu method, of class Cliente.
     */
    @Test
    public void testClickmenu() {
        System.out.println("Clickmenu");
        JPanel h1 = null;
        JPanel h2 = null;
        int numberbool = 0;
        Cliente instance = new Cliente();
        try{
          instance.Clickmenu(h1, h2, numberbool);
        }catch (Exception e){
            // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
            
        }
      
        
    }

    /**
     * Test of changeimage method, of class Cliente.
     */
    @Test
    public void testChangeimage() {
        System.out.println("changeimage");
        JLabel button = null;
        String resourcheimg = "";
        Cliente instance = new Cliente();
       
       try{
          instance.changeimage(button, resourcheimg);
        }catch (Exception e){
            // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
            
        }
      
    }

    /**
     * Test of hideshow method, of class Cliente.
     */
    @Test
    public void testHideshow() {
        System.out.println("hideshow");
        JPanel menushowhide = null;
        boolean dashboard = false;
        JLabel button = null;
        Cliente instance = new Cliente();
      
         try{
         instance.hideshow(menushowhide, dashboard, button);
        }catch (Exception e){
            // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
            
        }
    }

    /**
     * Test of main method, of class Cliente.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Cliente.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
