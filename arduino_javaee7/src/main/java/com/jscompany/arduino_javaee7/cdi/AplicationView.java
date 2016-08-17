/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_javaee7.cdi;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
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
@Named
@ApplicationScoped
public class AplicationView implements Serializable {

    public static final Long serialVersionUID = 1L;
    
    private Session session;
    
    @PostConstruct
    public void init(){
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
}
