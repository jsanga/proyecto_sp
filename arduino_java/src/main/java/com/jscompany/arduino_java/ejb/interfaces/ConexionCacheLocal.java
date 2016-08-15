/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_java.ejb.interfaces;

import gnu.io.CommPort;
import gnu.io.SerialPort;
import javax.ejb.Local;

/**
 *
 * @author Joao Sanga
 */
@Local
public interface ConexionCacheLocal {
    
    public void clearCache();
    
}
