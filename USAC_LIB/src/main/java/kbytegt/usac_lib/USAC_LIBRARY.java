/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import com.google.gson.Gson;


/**
 *
 * @author KByteGt
 */
public class USAC_LIBRARY {
    
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
    public static void main(String[] args) {
        // TODO code application logic here
        Gson json = new Gson();
    }
    
}
