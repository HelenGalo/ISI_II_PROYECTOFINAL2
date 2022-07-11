/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi_2022_ii_proyecto.Recursos;

/**
 *
 * @author orell
 */
public class Item {
    
    private int id;
    private  String descripcion;
    
    
    public Item(int id,String descripcion){
        this.id=id;
        this.descripcion=descripcion;
        
                
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
    @Override
    public String toString(){
    return this.getDescripcion();
    }
}
