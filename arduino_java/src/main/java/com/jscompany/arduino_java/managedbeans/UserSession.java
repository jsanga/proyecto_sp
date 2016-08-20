/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_java.managedbeans;

import com.jscompany.arduino_java.ejb.interfaces.ConexionCacheLocal;
import com.panamahitek.PanamaHitek_Arduino;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Joao Sanga
 */
@ManagedBean
@SessionScoped
public class UserSession implements Serializable {

    public static final Long serialVersionUID = 1L;
    
    @EJB
    private ConexionCacheLocal conexion;
    
    private Boolean estaLogueado = false;
    private Integer cont;
    private SerialPort puertoSerial;
    
    PanamaHitek_Arduino ino;

        
    public void loguearUsuario(){
        estaLogueado = true;
        puertoSerial = conexion.getConexion("COM3");
    }
    
    public Boolean getConexionStatus(SerialPortEventListener listener){
        try{
            if(puertoSerial == null){
                return false;
            }else{
                ino = new PanamaHitek_Arduino();
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
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

    public SerialPort getPuertoSerial() {
        return puertoSerial;
    }

    public void setPuertoSerial(SerialPort puertoSerial) {
        this.puertoSerial = puertoSerial;
    }
    
}
