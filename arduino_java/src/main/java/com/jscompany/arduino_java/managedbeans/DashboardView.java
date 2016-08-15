/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_java.managedbeans;

import com.jscompany.arduino_java.entidades.Artefacto;
import com.jscompany.arduino_java.util.JsfUti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Joao Sanga
 */
@Named
@ApplicationScoped
public class DashboardView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    Logger logger = Logger.getLogger(getClass().getSimpleName());
    
    @Inject
    private UserSession uSession;
    
    @Inject
    private AplicationView appView;
    
    private Boolean estadoConexion;
    private Boolean estadoAlarma;
    private Boolean alarmaActivada;
    private String mensajeAlarma;
    
    private List<Artefacto> artefactoList;
    private List<Artefacto> artefactoList2;
    private List<Artefacto> artefactoList3;
    private List<Artefacto> artefactoList4;
    
    
    @PostConstruct
    public void init(){
        if(uSession.getEstaLogueado()){
            estadoConexion = true;
            estadoAlarma = false;
            alarmaActivada = false;
            
            artefactoList = new ArrayList<Artefacto>();
            artefactoList2 = new ArrayList<Artefacto>();
            artefactoList3 = new ArrayList<Artefacto>();
            artefactoList4 = new ArrayList<Artefacto>();
            this.llenarEstaciones();
        }else{
            JsfUti.redirectFaces("");
        }
    }
    
    public void llenarEstaciones(){
        artefactoList.add(new Artefacto());
    }
    
    public void enviarMsj(){
        appView.sendMessage();
    }
        
    public void cambiarEstado(){
        estadoAlarma = !estadoAlarma;
    }
    
    public void detenerAlarma(){
        alarmaActivada = false;
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public Boolean getEstadoConexion() {
        return estadoConexion;
    }

    public void setEstadoConexion(Boolean estadoConexion) {
        this.estadoConexion = estadoConexion;
    }

    public Boolean getEstadoAlarma() {
        return estadoAlarma;
    }

    public void setEstadoAlarma(Boolean estadoAlarma) {
        this.estadoAlarma = estadoAlarma;
    }

    public Boolean getAlarmaActivada() {
        return alarmaActivada;
    }

    public void setAlarmaActivada(Boolean alarmaActivada) {
        this.alarmaActivada = alarmaActivada;
    }

    public AplicationView getAppView() {
        return appView;
    }

    public void setAppView(AplicationView appView) {
        this.appView = appView;
    }

    public String getMensajeAlarma() {
        return mensajeAlarma;
    }

    public void setMensajeAlarma(String mensajeAlarma) {
        this.mensajeAlarma = mensajeAlarma;
    }

    
    
}
