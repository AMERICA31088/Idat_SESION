/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idat.app;

import com.idat.entity.Persona;
import com.idat.service.ServicioPersona;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "WSPersona")
public class WSPersona {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "ayuda")
    public String hello(@WebParam(name = "name") String txt) {
        return "Autor " + txt + " !"; // Cambié 'nombre' a 'txt'
    }
    
    @WebMethod(operationName = "listadoPersona") 
    public List<Persona> listaPersona() { // Cambié 'Lista< Persona>' a 'List<Persona>' y 'listaérsona' a 'listaPersona'
    
        System.out.println("............");
        ServicioPersona servicio = new ServicioPersona();
        Persona p = new Persona();
        p.setCodigo(0);
        p.setNombre("");
        p.setApellido("");
        
        List<Persona> lista = servicio.operacionesLectura("ConsultarTodo", p ); 
        return lista;
    }
}

