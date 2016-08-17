/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_javaee7.cdi;

import com.jscompany.arduino_javaee7.entidades.Artefacto;
import com.jscompany.arduino_javaee7.util.JsfUti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Joao Sanga
 */
@Named
@ViewScoped
public class DashboardView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
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
            
            this.llenarEstaciones();
            JsfUti.update("frmMain");
        }else{
            JsfUti.redirectFaces("");
        }
    }
    
    public void cambiarEstadoBoton(Artefacto art){
        art.setEstadoBoton(!art.getEstadoBoton());
    }
    
    public void llenarEstaciones(){
        
        artefactoList = new ArrayList<Artefacto>();
        artefactoList2 = new ArrayList<Artefacto>();
        artefactoList3 = new ArrayList<Artefacto>();
        artefactoList4 = new ArrayList<Artefacto>();
        
        // COMEDOR
        
        artefactoList.add(new Artefacto("Focos", true));
        artefactoList.add(new Artefacto("Ventana", false));
        artefactoList.add(new Artefacto("Luces", true));
        
        // SALA
        
        artefactoList2.add(new Artefacto("Ventana", true));
        artefactoList2.add(new Artefacto("Puerta de garaje", false));
        artefactoList2.add(new Artefacto("Puerta", false));
        artefactoList2.add(new Artefacto("Luces", true));
        
        // COCINA
        
        artefactoList3.add(new Artefacto("Alarma", false));
        artefactoList3.add(new Artefacto("Puerta", false));
        
        // HABITACION
        
        artefactoList4.add(new Artefacto("Foco", false));
        artefactoList4.add(new Artefacto("Ventanas", false));
        artefactoList4.add(new Artefacto("Luces", true));
        
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

    public List<Artefacto> getArtefactoList() {
        return artefactoList;
    }

    public void setArtefactoList(List<Artefacto> artefactoList) {
        this.artefactoList = artefactoList;
    }

    public List<Artefacto> getArtefactoList2() {
        return artefactoList2;
    }

    public void setArtefactoList2(List<Artefacto> artefactoList2) {
        this.artefactoList2 = artefactoList2;
    }

    public List<Artefacto> getArtefactoList3() {
        return artefactoList3;
    }

    public void setArtefactoList3(List<Artefacto> artefactoList3) {
        this.artefactoList3 = artefactoList3;
    }

    public List<Artefacto> getArtefactoList4() {
        return artefactoList4;
    }

    public void setArtefactoList4(List<Artefacto> artefactoList4) {
        this.artefactoList4 = artefactoList4;
    }
    
}
