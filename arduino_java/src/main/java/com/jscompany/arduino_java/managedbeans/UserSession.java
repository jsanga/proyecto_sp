/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_java.managedbeans;

import java.io.Serializable;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Joao Sanga
 */
@ManagedBean
@SessionScoped
public class UserSession implements Serializable {

    public static final Long serialVersionUID = 1L;
    
    private Boolean estaLogueado = false;
        
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
    
}
