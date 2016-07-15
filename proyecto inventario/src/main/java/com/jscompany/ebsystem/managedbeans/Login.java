/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans;

import com.jscompany.ebsystem.ejb.interfaces.UsuariosServices;
import com.jscompany.ebsystem.entidades.usuarios.Loguin;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.services.Servicios;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JoaoIsrael
 */
@ManagedBean
@ViewScoped
public class Login implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    private Loguin loguin;
    
    @EJB(beanName = "aclService")
    private Servicios aclServices;
    
    @EJB(beanName = "usuariosServices")
    private UsuariosServices usuariosServices;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    @PostConstruct
    public void init(){
        try{
            loguin = new Loguin();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void validarUsuario(){
        if(loguin.getUsername()==null || loguin.getPass()==null)
            JsfUti.messageError(null, "Error", "No ha ingresado datos.");
        
        if(usuariosServices.validarUsuario(loguin.getUsername(), loguin.getPass())){
            JsfUti.messageInfo(null, "Info", "Usuario encontrado.");
            uSession.setUsername(loguin.getUsername());
            uSession.setIsLogged(true);
        }
        else
            JsfUti.messageError(null, "Error", "Usuario no encontrado.");
    }
    
    public void salir(){
        uSession.cerrarSesion();
    }
    
    public void irIndex(){
        JsfUti.redirectFaces("/");
    }
    
    public void guardarLoguin(){
        aclServices.saveEntity(loguin);
    }

    

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public Loguin getLoguin() {
        return loguin;
    }

    public void setLoguin(Loguin loguin) {
        this.loguin = loguin;
    }
    
}
