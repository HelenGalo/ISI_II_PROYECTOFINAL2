/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package isi_2022_ii_proyecto;

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
public class VentasEmpleadoTest {
    
    public VentasEmpleadoTest() {
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
     * Test of setUsuario method, of class VentasEmpleado.
     */
    @Test
    public void testSetUsuario() {
        System.out.println("setUsuario");
        String usuario = null;
        VentasEmpleado instance = new VentasEmpleado();
        try{
                instance.setUsuario(usuario);
        }catch (Exception e){
             // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        }
    
       
    }

    /**
     * Test of conectar method, of class VentasEmpleado.
     */
    @Test
    public void testConectar() {
        System.out.println("conectar");
        VentasEmpleado instance = new VentasEmpleado();
        try{
             instance.conectar();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of validarconexion method, of class VentasEmpleado.
     */
    @Test
    public void testValidarconexion() {
        System.out.println("validarconexion");
        VentasEmpleado instance = new VentasEmpleado();
       
          try{
             instance.validarconexion();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of conectarerror method, of class VentasEmpleado.
     */
    @Test
    public void testConectarerror() {
        System.out.println("conectarerror");
        VentasEmpleado instance = new VentasEmpleado();
          try{
             instance.conectarerror();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
          
              
          }

    /**
     * Test of conectarinicio method, of class VentasEmpleado.
     */
    @Test
    public void testConectarinicio() {
        System.out.println("conectarinicio");
        VentasEmpleado instance = new VentasEmpleado();
         try{
             instance.conectarinicio();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of conectarsinerror method, of class VentasEmpleado.
     */
    @Test
    public void testConectarsinerror() {
        System.out.println("conectarsinerror");
        VentasEmpleado instance = new VentasEmpleado();
           try{
             instance.conectarsinerror();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of limpiartabla method, of class VentasEmpleado.
     */
    @Test
    public void testLimpiartabla() {
        System.out.println("limpiartabla");
        VentasEmpleado instance = new VentasEmpleado();
          try{
             instance.limpiartabla();
        }catch(Exception e){
           // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype."); 
        }
    }

    /**
     * Test of main method, of class VentasEmpleado.
     */
    @Test    public void testMain() {
        System.out.println("main");
        String[] args = null;
        VentasEmpleado.main(args);
      
    }
    
}
