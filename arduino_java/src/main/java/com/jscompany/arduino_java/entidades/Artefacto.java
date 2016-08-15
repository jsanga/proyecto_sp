/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_java.entidades;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author Joao Sanga
 */
public class Artefacto implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    private String id;
    
    private String descripcion;
    
    private Boolean boton;
    
    public Artefacto(){
    
    }
    
    public Artefacto(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getBoton() {
        return boton;
    }

    public void setBoton(Boolean boton) {
        this.boton = boton;
    }
    
}
