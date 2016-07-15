/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.implementacion;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.HibernateEjbInterceptor;
import com.jscompany.ebsystem.ejb.interfaces.UsuariosServices;
import com.jscompany.ebsystem.entidades.usuarios.Loguin;
import com.jscompany.ebsystem.services.Servicios;
import com.jscompany.ebsystem.util.JsfUti;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author JoaoIsrael
 */
@Stateless(name = "usuariosServices")
@Interceptors(value = {HibernateEjbInterceptor.class})
public class UsuariosEjb implements UsuariosServices{
    
    @EJB(beanName = "aclService")
    private Servicios services;
    
    @Override
    public Boolean validarUsuario(String username, String pass){
        Boolean b;
        
        try{
            Loguin l = (Loguin)services.getEntityByParameters(Querys.getUsuariobyUserPass, new String[]{"user", "pass"}, new Object[]{username, pass});
            
            if(l!=null){
                JsfUti.redirectFaces("/faces/admin/principal.xhtml");
                /*
                rol = persona.getRol();
                
                switch(rol.getRolName()){
                    case "admin":
                        JsfUti.redirectFaces("/faces/admin/perfil.xhtml");
                        break;
                    case "profesor":
                        JsfUti.redirectFaces("/faces/profesor/perfil.xhtml");
                        break;
                    case "estudiante":
                        JsfUti.redirectFaces("/faces/estudiante/perfil.xhtml");
                        break;
                    case "invitado":
                        JsfUti.redirectFaces("/faces/general/perfil.xhtml");
                        break;
                    default:
                        System.out.println("Error!");
                        break;
                }
                */
                b = true;
            }else
                b=false;
        }catch(Exception e){
            b = false;
            e.printStackTrace();
        }
        return b;
    }
    
    @Override
    public Loguin guardarLoguin(Loguin l){
        return null;
    }
    
}
