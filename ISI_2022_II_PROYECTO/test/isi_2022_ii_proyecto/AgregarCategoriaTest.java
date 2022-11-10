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
public class AgregarCategoriaTest {
    
    public AgregarCategoriaTest() {
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
     * Test of setUsuario method, of class AgregarCategoria.
     */
    @Test
    public void testSetUsuario() {
        System.out.println("setUsuario");
        String usuario = "";
        AgregarCategoria instance = new AgregarCategoria();
        instance.setUsuario(usuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class AgregarCategoria.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        AgregarCategoria instance = new AgregarCategoria();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategoria method, of class AgregarCategoria.
     */
    @Test
    public void testSetCategoria() {
        System.out.println("setCategoria");
        String categoria = "";
        AgregarCategoria instance = new AgregarCategoria();
        instance.setCategoria(categoria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEstadoagregar method, of class AgregarCategoria.
     */
    @Test
    public void testSetEstadoagregar() {
        System.out.println("setEstadoagregar");
        boolean estadoagregar = false;
        AgregarCategoria instance = new AgregarCategoria();
        instance.setEstadoagregar(estadoagregar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conectar method, of class AgregarCategoria.
     */
    @Test
    public void testConectar() {
        System.out.println("conectar");
        AgregarCategoria instance = new AgregarCategoria();
        instance.conectar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarconexion method, of class AgregarCategoria.
     */
    @Test
    public void testValidarconexion() {
        System.out.println("validarconexion");
        AgregarCategoria instance = new AgregarCategoria();
        instance.validarconexion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conectarerror method, of class AgregarCategoria.
     */
    @Test
    public void testConectarerror() {
        System.out.println("conectarerror");
        AgregarCategoria instance = new AgregarCategoria();
        instance.conectarerror();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conectarinicio method, of class AgregarCategoria.
     */
    @Test
    public void testConectarinicio() {
        System.out.println("conectarinicio");
        AgregarCategoria instance = new AgregarCategoria();
        instance.conectarinicio();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conectarsinerror method, of class AgregarCategoria.
     */
    @Test
    public void testConectarsinerror() {
        System.out.println("conectarsinerror");
        AgregarCategoria instance = new AgregarCategoria();
        instance.conectarsinerror();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarConfirmacion method, of class AgregarCategoria.
     */
    @Test
    public void testValidarConfirmacion() {
        System.out.println("validarConfirmacion");
        AgregarCategoria instance = new AgregarCategoria();
        instance.validarConfirmacion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inicializar method, of class AgregarCategoria.
     */
    @Test
    public void testInicializar() {
        System.out.println("inicializar");
        AgregarCategoria instance = new AgregarCategoria();
        instance.inicializar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscardatos method, of class AgregarCategoria.
     */
    @Test
    public void testBuscardatos() {
        System.out.println("buscardatos");
        AgregarCategoria instance = new AgregarCategoria();
        try{
              instance.buscardatos(); 
        }catch(Exception e){
              // TODO review the generated test code and remove the default call to fail.
        fail("No se encontro el dato.");
        }
     
      
    }

    /**
     * Test of insertar method, of class AgregarCategoria.
     */
    @Test
    public void testInsertar() {
        System.out.println("insertar");
        AgregarCategoria instance = new AgregarCategoria();
        boolean expResult = true;
        boolean result = instance.insertar();
        if (expResult!=result){
              // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        }
       // assertEquals(expResult, result);
      
    }

    /**
     * Test of validar method, of class AgregarCategoria.
     */
    @Test
    public void testValidar() {
        System.out.println("validar");
        AgregarCategoria instance = new AgregarCategoria();
        Boolean expResult = null;
        Boolean result = instance.validar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarNombre method, of class AgregarCategoria.
     */
    @Test
    public void testValidarNombre() {
        System.out.println("validarNombre");
        String Nombre = "Adidas ";//valor correcto
        AgregarCategoria instance = new AgregarCategoria();
        boolean expResult = true;
        boolean result = instance.validarNombre(Nombre);
       // assertEquals(expResult, result);
       if (expResult!=result){
              // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        }
    }

    /**
     * Test of main method, of class AgregarCategoria.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AgregarCategoria.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
