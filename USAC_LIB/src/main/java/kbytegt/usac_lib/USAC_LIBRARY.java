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
    //RESPUESTA
    static final int REQUEST_OK = 200;
    static final int REQUEST_CREATED = 201;
    static final int REQUEST_ACEPTED = 202;
    static final int REQUEST_UPDATED = 203;
    static final int REQUEST_DELETED = 204;
    static final int REQUEST_NO_CONTENT = 205;
    //ERROR
    static final int REQUEST_ERROR = 400;
    static final int REQUEST_UNAUTHORIZED = 401;
    static final int REQUEST_NOT_FOUND = 404;
    static final int REQUEST_TIMEOUT = 408;
    //SERVIDOR
    static final int REQUEST_SERVER_ERROR = 500;
    static final int REQUEST_AUTHENTICATION_REQUIRED = 511;
    
    /////////////////////////////////////////////////////////
    
    static Security security;
    static TablaHash usuarios;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        Gson json = new Gson();
        usuarios = new TablaHash(19);

        test();

    }    
    
    public String getMessage(int request){
        String r = "";
        switch(request){
            
        }
        return r;
    }
    
    public static void test(){
        NodoUsuario u1 = new NodoUsuario(201503836,"Daniel","Lopez","Ciencias y Sistemas",security.getMD5("123456"));
        NodoUsuario u2 = new NodoUsuario(201503476,"Ricardo","Cutz","Ingenieria en istemas",security.getMD5("12b456"));
        NodoUsuario u3 = new NodoUsuario(201503477,"Juanito","Hernandez","Ingenieria Civil",security.getMD5("abf34543"));
        NodoUsuario u4 = new NodoUsuario(202005878,"Antonio","Hernandez","Ingenieria Electrica",security.getMD5("asdfghjkl"));
        NodoUsuario u5 = new NodoUsuario(201003866,"Pedrito","Lopez","Ciencias y Sistemas",security.getMD5("eewe3434"));
        NodoUsuario u6 = new NodoUsuario(201103336,"Miguel","Lopez","Ciencias y Sistemas",security.getMD5("password"));
        NodoUsuario u7 = new NodoUsuario(109803834,"Juan","Lopez","Ciencias y Sistemas",security.getMD5("juan123"));
        NodoUsuario u8 = new NodoUsuario(201504876,"Juan","Lemus","Ciencias y Sistemas",security.getMD5("1sss3356"));
        
        usuarios.insertar(u1);
        usuarios.insertar(u2);
        usuarios.insertar(u3);
        usuarios.insertar(u4);
        usuarios.insertar(u5);
        usuarios.insertar(u6);
        usuarios.insertar(u7);
        usuarios.insertar(u8);
        
        
        NodoUsuario temp;
        temp = usuarios.buscar(201503836);
        if(temp != null){
            System.out.println(temp.getJSON());
        } else {
            System.out.println("No existe el usuario:");
        }
        
        NodoUsuario u9 = new NodoUsuario(201503836,"Daniel","Lopez","Ciencias y Sistemas",security.getMD5("5246"));
        usuarios.insertar(u9);
        
        temp = usuarios.buscar(201503836);
        if(temp != null){
            System.out.println(temp.getJSON());
        } else {
            System.out.println("No existe el usuario:");
        }
        
        int request = usuarios.eliminar(109803834);
        switch(request){
            case REQUEST_DELETED:
                System.out.println("Usuario: 109803834 eliminado exitosamente.");
                break;
            case REQUEST_NOT_FOUND:
                System.out.println("Usuario: 109803834 no encontrado.");
                break;
            default:
                System.out.println("Error al eliminar");
                break;
        }
        
        temp = usuarios.buscar(109803834);
        if(temp != null){
            System.out.println(temp.getJSON());
        } else {
            System.out.println("No existe el usuario:");
        }
 
        
//        String g = usuarios.getGraphviz("TABLA HASH");
//        System.out.println(g);
    }
}
