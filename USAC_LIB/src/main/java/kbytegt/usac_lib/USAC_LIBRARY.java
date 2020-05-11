/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

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
    static Gson json;
    static Security security;
    static TablaHash usuarios;
    static UIlogin login = new UIlogin();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        //json = new GsonBuilder().serializeNulls().create();
        usuarios = new TablaHash(45);
        
        System.out.println("Preparando pruebas");
        test();
        
        //Cargar el LogIn
        try {
            System.out.println("Abriendo ventana LogIn");
            login.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error al abrir la ventana UIlogin");
        }
//        
        

    }    
    
    public static void ingresarUsuarios(String txt){
        try {
            JsonParser parser = new JsonParser();
            JsonObject json_usuarios = parser.parse(txt).getAsJsonObject();

            JsonArray user_array = json_usuarios.getAsJsonArray("Usuarios").getAsJsonArray();
            for(JsonElement user : user_array){
                JsonObject user_obj = user.getAsJsonObject();
                int carnet = user_obj.get("Carnet").getAsInt();
                String nombre = user_obj.get("Nombre").getAsString();
                String apellido = user_obj.get("Apellido").getAsString();
                String carrera = user_obj.get("Carrera").getAsString();
                String password = user_obj.get("Password").getAsString();

                NodoUsuario usuario = new NodoUsuario(carnet,nombre,apellido,carrera,security.getMD5(password));
                usuarios.insertar(usuario);
                
                System.out.println("["+carnet+"] "+nombre+" "+apellido+" - "+carrera+" > "+password);
            }  
        } catch (Exception e) {
            System.out.println("Error al leer JSON");
        }
                
    }
            
    public static String getMessage(int request, String txt){
        String r = "";
        switch(request){
            case REQUEST_OK:
                JOptionPane.showMessageDialog(null, "Operación realizada con éxito:\n"+txt);
                break;
            case REQUEST_CREATED:
                JOptionPane.showMessageDialog(null, "Operación creada con éxito:\n"+txt);
                break;
            case REQUEST_ACEPTED:
                JOptionPane.showMessageDialog(null, "Operación aceptada con éxito:\n"+txt);
                break;
            case REQUEST_UPDATED:
                JOptionPane.showMessageDialog(null, "Operación actualizada con éxito:\n"+txt);
                break;
            case REQUEST_DELETED:
                JOptionPane.showMessageDialog(null, "Operación eliminada con éxito:\n"+txt);
                break;
            case REQUEST_NO_CONTENT:
                JOptionPane.showMessageDialog(null, "No se pudo realizar la operación:\n"+txt);
                break;
                
            case REQUEST_ERROR:
                JOptionPane.showMessageDialog(null, "Error al efecutar:\n"+txt);
                break;
            case REQUEST_UNAUTHORIZED:
                JOptionPane.showMessageDialog(null, "Se necesita autorización para:\n"+txt);
                break;
            case REQUEST_NOT_FOUND:
                JOptionPane.showMessageDialog(null, "No se puedo realizar la operación:\n"+txt);
                break;
            case REQUEST_TIMEOUT:
                JOptionPane.showMessageDialog(null, "Tiempo de espera agotado:\n"+txt);
                break;
                
            case REQUEST_SERVER_ERROR:
                JOptionPane.showMessageDialog(null, "Error interno del servidor:\n"+txt);
                break;
            case REQUEST_AUTHENTICATION_REQUIRED:
                JOptionPane.showMessageDialog(null, "Se necesitan permisos para realizar la operación:\n"+txt);
                break;
            default:
                break;
        }
        return r;
    }
    
    public static void test(){
//        Gson gson = new GsonBuilder().serializeNulls().create();
//        String json = gson.toJson(new NodoUsuario(0,"","","",""));
//        System.out.println(json);
        
            
        NodoUsuario u1 = new NodoUsuario(201503836,"Daniel","Lopez","Ciencias y Sistemas",security.getMD5("123456"));
        NodoUsuario u2 = new NodoUsuario(201503476,"Ricardo","Cutz","Ingenieria en istemas",security.getMD5("12b456"));
        NodoUsuario u3 = new NodoUsuario(201503477,"Juanito","Hernandez","Ingenieria Civil",security.getMD5("abf34543"));
        NodoUsuario u4 = new NodoUsuario(202005878,"Antonio","Hernandez","Ingenieria Electrica",security.getMD5("asdfghjkl"));
        NodoUsuario u5 = new NodoUsuario(201003866,"Pedrito","Lopez","Ciencias y Sistemas",security.getMD5("eewe3434"));
        NodoUsuario u6 = new NodoUsuario(201103336,"Miguel","Lopez","Ciencias y Sistemas",security.getMD5("1234"));
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
        
        
//        NodoUsuario temp;
//        temp = usuarios.buscar(201503836);
//        if(temp != null){
//            String json_txt = json.toJson(temp);
//            System.out.println(json_txt);
//        } else {
//            
//            System.out.println("No existe el usuario:");
//        }
//        
//        NodoUsuario u9 = new NodoUsuario(201503836,"Daniel","Lopez","Ciencias y Sistemas",security.getMD5("5246"));
//        usuarios.insertar(u9);
//        
//        temp = usuarios.buscar(201503836);
//        if(temp != null){
//            //System.out.println(temp.getJSON());
//            //Obtene Json a parter de un objeto
//            String json_txt = json.toJson(temp);
//            System.out.println(json_txt);
//        } else {
//            System.out.println("No existe el usuario:");
//        }
//        
//        int request = usuarios.eliminar(109803834);
//        //getMessage(request,"Usuario "+ 109803834);
//
//        
//        temp = usuarios.buscar(109803834);
//        if(temp != null){
//            //System.out.println(temp.getJSON());
//            //Obtene Json a parter de un objeto
//            String json_txt = json.toJson(temp);
//            System.out.println(json_txt);
//        } else {
//            System.out.println("No existe el usuario:");
//        }
 
        System.out.println("");

        String g = usuarios.getGraphviz("TABLA HASH");
        System.out.println(g);
    }
}
