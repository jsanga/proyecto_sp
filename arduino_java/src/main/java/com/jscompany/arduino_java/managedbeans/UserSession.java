/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_java.managedbeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Joao Sanga
 */
@ManagedBean
@SessionScoped
public class UserSession implements Serializable {

    public static final Long serialVersionUID = 1L;
    
    private Boolean estaLogueado = false;
    private Integer cont;
        
    public void loguearUsuario(){
        estaLogueado = true;
    }
    
    public void cerrarSesion(){
        estaLogueado = false;
    }

    public Boolean getEstaLogueado() {
        return estaLogueado;
    }

    public void setEstaLogueado(Boolean estaLogueado) {
        this.estaLogueado = estaLogueado;
    }

    public Integer getCont() {
        return cont;
    }

    public void setCont(Integer cont) {
        this.cont = cont;
    }
    
}
