/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_java.ejb.implementation;

import com.jscompany.arduino_java.ejb.interfaces.ConexionCacheLocal;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

/**
 *
 * @author Joao Sanga
 */
@Singleton(name = "conexionCache")
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ConexionCache implements ConexionCacheLocal {
    
    //@Resource
    //private ManagedThreadFactory threadFactory;
    
    public void clearCache(){
        
    }
    
    /*
    protected ConcurrentMap<String, SerialPort> conexionMap;
    protected ConcurrentMap<String, String> lockerMap = new ConcurrentHashMap();
    
    @Override
    public void clearCache(){
        conexionMap.remove("conexion");
    }
    
    @Override
    public SerialPort getConexion(String portName){
        SerialPort comPort = conexionMap.get(portName);
        if(comPort == null){
            try {
                buildConexion(portName);
            } catch (Exception ex) {
                Logger.getLogger(ConexionCache.class.getName()).log(Level.SEVERE, null, ex);
            }
            return conexionMap.get(portName);
        }
        
        return comPort;
    }
    
    private void loadConexion(String portName) throws Exception{
        try{
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            if ( portIdentifier.isCurrentlyOwned() )
            {
                System.out.println("Error: Port is currently in use");
            }
            else
            {
                CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);

                if ( commPort instanceof SerialPort )
                {
                    SerialPort serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(57600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

                    InputStream in = serialPort.getInputStream();
                    OutputStream out = serialPort.getOutputStream();

                    (new Thread(new SerialReader(in))).start();
                    (new Thread(new SerialWriter(out))).start();
                    conexionMap.putIfAbsent(portName, serialPort);
                }else{
                    System.out.println("Error: Only serial ports are handled by this example.");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void buildConexion(String portName) throws Exception{
        synchronized (getLockerObject(portName)) {
            CommPort comPort = conexionMap.get("portName");
            if (comPort == null) {
                this.loadConexion(portName);
            }
        }
            
    }
    
    protected String getLockerObject(String portName) {
        lockerMap.putIfAbsent(portName, portName);

        return lockerMap.get(portName);
    }*/
    
    /** 
    private static class SerialReader implements Runnable 
    {
        InputStream in;
        
        public SerialReader ( InputStream in )
        {
            this.in = in;
        }
        
        public void run ()
        {
            byte[] buffer = new byte[1024];
            int len = -1;
            try
            {
                while ( ( len = this.in.read(buffer)) > -1 )
                {
                    System.out.print(new String(buffer,0,len));
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }*/

    /** 
    private static class SerialWriter implements Runnable 
    {
        OutputStream out;
        
        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }
        
        public void run ()
        {
            try
            {                
                int c = 0;
                while ( ( c = System.in.read()) > -1 )
                {
                    this.out.write(c);
                }                
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }*/
    
    /**
     * Inicializa el map de menubars en vacio
     
    protected void initMenubarsMap() {
        this.conexionMap = new ConcurrentHashMap();
    }*/

    /**
     * Inicializa el singleton ejb
     
    @PostConstruct
    protected void init() {
        this.initMenubarsMap();
    }*/
}
