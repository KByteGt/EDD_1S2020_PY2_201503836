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
        FileInputStream is = null;
        InputStreamReader sr = null;
        BufferedReader br = null;

        try {
            archivo = new File (ruta);
            is = new FileInputStream(archivo);
            sr = new InputStreamReader(is, "UTF-8");
            br = new BufferedReader(sr, 8);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
               json += linea;
        }
        catch(IOException e){
        }finally{
            // Cerrar el fichero
            try{                    
                if( null != fr ){   
                   fr.close();     
                }                  
            }catch (IOException e2){ 
            }
        }
        
        return json;
    }
}
