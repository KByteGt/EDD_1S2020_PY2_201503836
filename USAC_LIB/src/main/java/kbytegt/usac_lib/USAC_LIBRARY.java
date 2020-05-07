/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import com.google.gson.Gson;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author KByteGt
 */
public class USAC_LIBRARY {
    static Security security;
    static TablaHash usuarios;
    //Constantes
    static final int REQUEST_OK = 200;
    static final int REQUEST_CREATED = 201;
    static final int REQUEST_ACEPTED = 202;
    static final int REQUEST_UPDATED = 203;
    static final int REQUEST_DELETED = 204;
    static final int REQUEST_NO_CONTENT = 204;
    static final int REQUEST_ERROR = 400;
    static final int REQUEST_UNAUTHORIZED = 401;
    static final int REQUEST_NOT_FOUND = 404;
    static final int REQUEST_TIMEOUT = 408;
    static final int REQUEST_SERVER_ERROR = 500;
    static final int REQUEST_AUTHENTICATION_REQUIRED = 511;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        Gson json = new Gson();
        usuarios = new TablaHash(19);
        //e10adc3949ba59abbe56e057f20f883e
        //security.encryptMD5("12345")
        NodoUsuario u1 = new NodoUsuario(201503836,"Daniel","Lopez","Ciencias y Sistemas","12345");
        NodoUsuario u2 = new NodoUsuario(201503476,"Ricardo","Cutz","Ingenieria en istemas","123456");
        NodoUsuario u3 = new NodoUsuario(201503477,"Juanito","Hernandez","Ingenieria Civil","123456");
        NodoUsuario u4 = new NodoUsuario(202005878,"Antonio","Hernandez","Ingenieria Electrica","123456");
        NodoUsuario u5 = new NodoUsuario(201003866,"Pedrito","Lopez","Ciencias y Sistemas","12345");
        NodoUsuario u6 = new NodoUsuario(201103336,"Miguel","Lopez","Ciencias y Sistemas","12345");
        NodoUsuario u7 = new NodoUsuario(109803834,"Juan","Lopez","Ciencias y Sistemas","12345");
        NodoUsuario u8 = new NodoUsuario(201504876,"Juan","Lemus","Ciencias y Sistemas","12345");
        NodoUsuario u9 = new NodoUsuario(201506736,"Maria","Lopez","Ciencias y Sistemas","12345");
        usuarios.insertar(u1);
        usuarios.insertar(u2);
        usuarios.insertar(u3);
        usuarios.insertar(u4);
        usuarios.insertar(u5);
        usuarios.insertar(u6);
        usuarios.insertar(u7);
        usuarios.insertar(u8);
        usuarios.insertar(u9);
        
        //NodoUsuario temp;
        //temp = usuarios.buscar(201103336);
        //System.out.println(temp.getJSON());
        
        //usuarios.eliminar(109803834);
        
        String g = usuarios.getGraphviz("TABLA HASH");
        //System.out.println(security.encryptMD5("12345"));
        System.out.println(g);
    }    
}
