/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_javaee7.entidades;

import java.io.Serializable;

/**
 *
 * @author Joao Sanga
 */
public class Artefacto implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    private String id;
    
    private String descripcion;    
    private Boolean tieneBoton;   
    private Boolean estadoBoton;    
    private Boolean estadoIndicador;
    
    public Artefacto(){
    
    }
    
    public Artefacto(String descripcion, Boolean tieneBoton){
        this.descripcion = descripcion;
        this.tieneBoton = tieneBoton;
        if(tieneBoton)
            estadoBoton = false;
        this.estadoIndicador = true;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getTieneBoton() {
        return tieneBoton;
    }

    public void setTieneBoton(Boolean tieneBoton) {
        this.tieneBoton = tieneBoton;
    }

    public Boolean getEstadoBoton() {
        return estadoBoton;
    }

    public void setEstadoBoton(Boolean estadoBoton) {
        this.estadoBoton = estadoBoton;
    }

    public Boolean getEstadoIndicador() {
        return estadoIndicador;
    }

    public void setEstadoIndicador(Boolean estadoIndicador) {
        this.estadoIndicador = estadoIndicador;
    }
    
}
