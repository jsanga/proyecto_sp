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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joao Sanga
 */
@ManagedBean
@ViewScoped
public class DashboardView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @ManagedProperty(value= "#{userSession}")
    private UserSession uSession;
    
    @ManagedProperty(value= "#{aplicationView}")
    private AplicationView appView;
    
    private Boolean estadoConexion;
    private Boolean estadoAlarma;
    private Boolean alarmaActivada;
    private String mensajeAlarma;
        
    private List<Artefacto> artefactoList;
    private List<Artefacto> artefactoList2;
    private List<Artefacto> artefactoList3;
    private List<Artefacto> artefactoList4;
    private int porcentajeLuz;
    private Double porcentajeArduino;
    
    @PostConstruct
    public void init(){
        if(uSession.getEstaLogueado()){
            estadoConexion = true;
            estadoAlarma = false;
            alarmaActivada = false;      
            this.llenarEstaciones();
            if(uSession.getCont() == 0){
                JsfUti.messageInfo(null, "Info", "Bienvenido");
            }
            JsfUti.update("frmMain");
            uSession.setCont(uSession.getCont() + 1);
        }else{
            JsfUti.redirectFaces("");
        }
    }
    
    public void enviarPorcentaje(){
        porcentajeArduino = (Double.parseDouble(""+porcentajeLuz) / 100) * 5;
        System.out.println(porcentajeArduino);
    }
        
    public void cambiarEstadoBoton(Artefacto art){
        art.setEstadoBoton(!art.getEstadoBoton());
    }
    
    public void estadoEncendido(){
        try{
            if(appView.getArduino1() != null){
                System.out.println("Estado Encendido");
                System.out.println(appView.getArduino1().getPortsAvailable());
                System.out.println(appView.getArduino1().receiveData());
            }else{
                JsfUti.messageInfo(null, "Info", "El arduino no está conectado");
            }
            JsfUti.update("frmMain");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void sendMessage(){
        
        try{
            appView.getArduino1().sendData("1 3");
            /*appView.getArduino1().sendByte(3);
            appView.getArduino1().sendByte(0);
            appView.getArduino1().sendByte(0);
            appView.getArduino1().sendByte(0);
            appView.getArduino1().sendByte(4);
            appView.getArduino1().sendByte(13);
            appView.getArduino1().sendByte(9);
            try {
                //RECIBE CONSTANTEMENTE DE ARDUINO
                //System.out.println(appView.getArduino1().receiveData());
            } catch (Exception ex) {
                Logger.getLogger(AplicationView.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void estadoApagado(){
        try{
            appView.getArduino1().sendData("0");
            JsfUti.update("frmMain");
            
        }catch(Exception e){
            e.printStackTrace();
        }
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
        
        artefactoList2.add(new Artefacto("Ventana", false));
        artefactoList2.add(new Artefacto("Puerta de garaje", false));
        artefactoList2.add(new Artefacto("Puerta", true));
        artefactoList2.add(new Artefacto("Luces", true));
        
        // COCINA
        
        artefactoList3.add(new Artefacto("Alarma", false));
        artefactoList3.add(new Artefacto("Puerta", false));
        
        // HABITACION
        
        artefactoList4.add(new Artefacto("Foco", false));
        artefactoList4.add(new Artefacto("Ventanas", false));
        artefactoList4.add(new Artefacto("Luces", true));
        
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

    public AplicationView getAppView() {
        return appView;
    }

    public void setAppView(AplicationView appView) {
        this.appView = appView;
    }

    public int getPorcentajeLuz() {
        return porcentajeLuz;
    }

    public void setPorcentajeLuz(int porcentajeLuz) {
        this.porcentajeLuz = porcentajeLuz;
    }

}
