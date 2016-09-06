/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_java.managedbeans;

import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.PanamaHitek_multiMessage;
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
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    
    private Session session;
    
    private PanamaHitek_Arduino arduino1;
    private SerialPortEventListener listener;
    private PanamaHitek_multiMessage mensaje;
    private Boolean stateConexion;
    private Thread estadoConexion;

    private Integer cont = 0;
    
    @PostConstruct // Función que inicializa los esclavos y los almacena en hilos separados para su posterior consulta.
    public void init(){ 
        arduino1 = new PanamaHitek_Arduino();
        mensaje = new PanamaHitek_multiMessage(3, arduino1);
        
        listener = new SerialPortEventListener() {
            @Override
            public void serialEvent(SerialPortEvent spe) {
                //RECIBE CONSTANTEMENTE DE ARDUINO
                if(arduino1.isMessageAvailable())
                    System.out.println(arduino1.printMessage());
                
            }
        };        
        try {
            arduino1.arduinoRXTX ("COM1", 9600, listener);
        } catch (Exception ex) {
            Logger.getLogger(AplicationView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //estadoConexion = new Thread(new EstadoArduino(arduino1, listener, cont));
        //estadoConexion.start();
        
        // Inicializa también las variables necesarias para ejecutar el sistema de notificaciones al usuario a través de su e-mail.
        
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
    
    public Boolean sendMessage(){ // Método que envía un correo al usuario en caso de que la alarma se active
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
    
    public Boolean sendMessageDesconectado(){ // Método que envía un correo al usuario en caso de que la alarma se active
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("testjsanga@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("intermif_21@hotmail.com"));
            message.setSubject("SISTEMA DESCONECTADO");
            message.setText("Se ha desconectado el sistema domótico."
                + "\n\nHora de la desconexión: "+new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy. 'A las' hh:mm:ss.", new Locale("es", "Ec")).format(new Date()));

            Transport.send(message);

            return true;

        } catch (Exception e) {            
            e.printStackTrace();
            return false;            
        }       
    }
    
    // Hilo de lectura del estado de conexion del Arduino
    private static class EstadoArduino implements Runnable {    
        
        private PanamaHitek_Arduino arduino;
        private SerialPortEventListener listener;
        private Integer cont;
        
        @ManagedProperty(value= "#{aplicationView}")
        private AplicationView appView;
        
        public EstadoArduino (PanamaHitek_Arduino arduino, SerialPortEventListener listener, Integer cont){
            this.arduino = arduino;
            this.listener = listener;
            this.cont = cont;
        }
        
        public void run (){

            try{
                while(true){            
                    try {
                        while(true){                            
                            try {
                                
                                if(arduino == null){
                                    arduino = new PanamaHitek_Arduino();
                                //if(arduino.getSerialPorts() == null || arduino.getSerialPorts().isEmpty()){
                                //    arduino = new PanamaHitek_Arduino();
                                    cont = cont + 1;
                                    arduino.arduinoRXTX ("COM4", 9600, listener);                                     
                                    
                                }else{
                                    if(cont > 1)
                                        appView.sendMessageDesconectado();
                                    if(arduino.getSerialPorts() == null || arduino.getSerialPorts().isEmpty())
                                        arduino = null;
                                    cont = 0;
                                }
                                
                            } catch (Exception ex) {
                                Logger.getLogger(AplicationView.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            sleep(2000);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SerialReader1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }catch ( Exception e ){
                e.printStackTrace();
            }            
        }

        public AplicationView getAppView() {
            return appView;
        }

        public void setAppView(AplicationView appView) {
            this.appView = appView;
        }
        
    }
    
    // Hilo de lectura 1
    private static class SerialReader1 implements Runnable {    
        
        private PanamaHitek_multiMessage mensaje;
        
        private List<String> lectura;
        
        public SerialReader1 (PanamaHitek_multiMessage mensaje){
            this.mensaje = mensaje;
        }
        
        public void run (){
            byte[] buffer = new byte[1024];
            int len = -1;

            try{
                while(true){            
                    try {
                        while(true){                            
                            System.out.println(this.mensaje.getMessage(1));
                            
                            sleep(MIN_PRIORITY);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SerialReader1.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public SerialPortEventListener getListener() {
        return listener;
    }

    public void setListener(SerialPortEventListener listener) {
        this.listener = listener;
    }

    public Thread getEstadoConexion() {
        return estadoConexion;
    }

    public void setEstadoConexion(Thread estadoConexion) {
        this.estadoConexion = estadoConexion;
    }
    
}
