/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.arduino_java.util;

import java.io.Serializable;

/**
 *
 * @author Joao Sanga
 */
public class ConexionArduino implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    private static final String PORT_NAMES[] = { 
    "/dev/tty.usbmodem", // Mac OS X
    "/dev/usbdev", // Linux
    "/dev/tty", // Linux
    "/dev/serial", // Linux
    "COM3", // Windows
};
    
}
