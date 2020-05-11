/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;
import java.io.*;
/**
 *
 * @author KByteGt
 */
public class Archivo {
    
    public String getJson(String ruta){
        String json = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File (ruta);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
               json += linea;
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // Cerrar el fichero
            try{                    
                if( null != fr ){   
                   fr.close();     
                }                  
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
        
        return json;
    }
}
