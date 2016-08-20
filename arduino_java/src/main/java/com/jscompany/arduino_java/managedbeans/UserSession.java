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
    
    private PanamaHitek_Arduino arduino;

        
    public void loguearUsuario(){
        estaLogueado = true;
        arduino = new PanamaHitek_Arduino();
    }
    
    public Boolean setData(SerialPortEventListener listener){
        try{
            arduino.arduinoRXTX("COM3", 9600, listener);
            
            /*if(puertoSerial == null){
                return false;
            }else{
                ino = new PanamaHitek_Arduino();
                return true;
            }*/
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Boolean getConexionStatus(){
        System.out.println(arduino.printMessage());
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

    public PanamaHitek_Arduino getArduino() {
        return arduino;
    }

    public void setArduino(PanamaHitek_Arduino arduino) {
        this.arduino = arduino;
    }
    
}
