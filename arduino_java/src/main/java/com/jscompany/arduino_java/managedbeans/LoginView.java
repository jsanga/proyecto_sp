/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_java.managedbeans;

import com.jscompany.arduino_java.util.JsfUti;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Joao Sanga
 */
@ManagedBean
@ViewScoped
public class LoginView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @ManagedProperty(value= "#{userSession}")
    private UserSession uSession;
    
    private String username, pass;
    private Boolean recordarDatos;
    
    @PostConstruct
    public void init(){
        uSession.cerrarSesion();
    }
    
    public void login(){
        if(username.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")){
            uSession.loguearUsuario();
            uSession.setCont(0);
            JsfUti.redirectFaces("/dashboard.xhtml");            
        }else{
            JsfUti.messageInfo(null, "Info", "Usuario no registrado");
            JsfUti.update("frmMain");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public Boolean getRecordarDatos() {
        return recordarDatos;
    }

    public void setRecordarDatos(Boolean recordarDatos) {
        this.recordarDatos = recordarDatos;
    }
    
}
