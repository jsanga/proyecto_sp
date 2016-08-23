/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_java.managedbeans;

import com.jscompany.arduino_java.ejb.interfaces.ConexionCacheLocal;
import com.panamahitek.PanamaHitek_Arduino;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.Serializable;
import static java.lang.Thread.MIN_PRIORITY;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Joao Sanga
 */
@ManagedBean
@ApplicationScoped
public class AplicationView implements Serializable {

    public static final Long serialVersionUID = 1L;
    
    @EJB
    private ConexionCacheLocal conexion;
    
    private Session session;
    
    private PanamaHitek_Arduino arduino1;
    private PanamaHitek_Arduino arduino2;
    private PanamaHitek_Arduino arduino3;
    private PanamaHitek_Arduino arduino4;
    private SerialPortEventListener listener;
    
    private Thread h1;
    private Thread h2;
    private Thread h3;
    private Thread h4;
    
    @PostConstruct
    public void init(){
        arduino1 = new PanamaHitek_Arduino();
        arduino2 = new PanamaHitek_Arduino();
        arduino3 = new PanamaHitek_Arduino();
        arduino4 = new PanamaHitek_Arduino();
        listener = new SerialPortEventListener() {

            @Override
            public void serialEvent(SerialPortEvent spe) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
                
        try{
            arduino1.arduinoRXTX("COM4", 9600, listener);
            //arduino2.arduinoRXTX("COM2", 9600, listener);
            //arduino3.arduinoRXTX("COM3", 9600, listener);
            //arduino4.arduinoRXTX("COM1", 9600, listener);
            
            h1 = new Thread(new SerialReader1(arduino1));
            h2 = new Thread(new SerialReader2(arduino2));
            h3 = new Thread(new SerialReader3(arduino3));
            h4 = new Thread(new SerialReader4(arduino4));
            
            h1.start();
            h2.start();
            h3.start();
            h4.start();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //
        
        final String username = "testjsanga@gmail.com";
        final String password = "testjsanga123";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password);
          }
        });
    }
    
    public Boolean sendMessage(){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("testjsanga@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("intermif_21@hotmail.com"));
            message.setSubject("ALARMA ENCENDIDA");
            message.setText("Se ha activado la alarma. Revise el estado del sistema domótico."
                + "\n\nHora de la activación: "+new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy. 'A las' hh:mm:ss.", new Locale("es", "Ec")).format(new Date()));

            Transport.send(message);

            return true;

        } catch (Exception e) {            
            e.printStackTrace();
            return false;            
        }       
    }
    
    // Hilo de lectura 1
    private static class SerialReader1 implements Runnable {    
        
        private PanamaHitek_Arduino arduino;
        
        private List<String> lectura;
        
        public SerialReader1 (PanamaHitek_Arduino arduino){
            this.arduino = arduino;
        }
        
        public void run (){
            byte[] buffer = new byte[1024];
            int len = -1;

            try{
                while(true){            
                    try {
                        while(true){
                            
                            System.out.println(this.arduino.receiveData());
                            //System.out.println("Hello from a thread! - cont1 ");
                            sleep(MIN_PRIORITY);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SerialReader1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                /*while ( ( len = this.in.read(buffer)) > -1 )
                {
                    System.out.print(new String(buffer,0,len));
                }*/
            }catch ( Exception e ){
                e.printStackTrace();
            }            
        }
    }
    // Hilo de lectura 2
    private static class SerialReader2 implements Runnable {
        
        private PanamaHitek_Arduino arduino;
        
        private List<String> lectura;
        
        public SerialReader2 (PanamaHitek_Arduino arduino){
            this.arduino = arduino;
        }
        
        public void run (){
            
            try{
                while(true){            
                    try {
                        //if()
                        while(true){
                            //System.out.println("Hello from a thread! - cont2 ");
                            sleep(MIN_PRIORITY);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SerialReader2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                /*while ( ( len = this.in.read(buffer)) > -1 )
                {
                    System.out.print(new String(buffer,0,len));
                }*/
            }catch ( Exception e ){
                e.printStackTrace();
            }            
        }
    }
    // Hilo de lectura 3
    private static class SerialReader3 implements Runnable{
        
        private PanamaHitek_Arduino arduino;
        
        private List<String> lectura;
        
        public SerialReader3 (PanamaHitek_Arduino arduino){
            this.arduino = arduino;
        }
        
        public void run (){
            
            try{
                while(true){            
                    try {
                        while(true){
                            //System.out.println("Hello from a thread! - cont3 ");
                            sleep(MIN_PRIORITY);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SerialReader3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }catch ( Exception e ){
                e.printStackTrace();
            }            
        }
    }
    // Hilo de lectura 4
    private static class SerialReader4 implements Runnable{
        
        private PanamaHitek_Arduino arduino;
        
        private List<String> lectura;
        
        public SerialReader4 (PanamaHitek_Arduino arduino){
            this.arduino = arduino;
        }
        
        public void run (){            
            try{                
                while(true){
            
                    try {
                        while(true){
                            //System.out.println("Hello from a thread! - cont4 ");
                            sleep(MIN_PRIORITY);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SerialReader4.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }catch ( Exception e ){
                e.printStackTrace();
            }            
        }
    }

    public PanamaHitek_Arduino getArduino1() {
        return arduino1;
    }

    public void setArduino1(PanamaHitek_Arduino arduino1) {
        this.arduino1 = arduino1;
    }

    public PanamaHitek_Arduino getArduino2() {
        return arduino2;
    }

    public void setArduino2(PanamaHitek_Arduino arduino2) {
        this.arduino2 = arduino2;
    }

    public PanamaHitek_Arduino getArduino3() {
        return arduino3;
    }

    public void setArduino3(PanamaHitek_Arduino arduino3) {
        this.arduino3 = arduino3;
    }

    public PanamaHitek_Arduino getArduino4() {
        return arduino4;
    }

    public void setArduino4(PanamaHitek_Arduino arduino4) {
        this.arduino4 = arduino4;
    }

    public SerialPortEventListener getListener() {
        return listener;
    }

    public void setListener(SerialPortEventListener listener) {
        this.listener = listener;
    }
    
}
