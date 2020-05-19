/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt;
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
    
    public boolean crearDot(String dot, String nombre){
        String filePath = usac_lib.carpeta+"\\"+nombre+".dot";
        FileOutputStream os;
        OutputStreamWriter sw;
        BufferedWriter bw;
        try {
            os = new FileOutputStream(filePath);
            sw = new OutputStreamWriter(os,"utf-8");
            bw = new BufferedWriter(sw);
            
            bw.write(dot);
            
            bw.close();
            
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } finally{
            
        }
        
    }
    
    public boolean crearImagen(String nombreImg, String nombreDot){
        try {
            String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            
            String fileInputPath = usac_lib.carpeta+"\\"+nombreDot+".dot";
            String fileOutputPath = usac_lib.carpeta+"\\"+nombreImg+".jpg";
            
            String tParam = "-Tjpg";
            String tOParam = "-o";
            
            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;
            
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } finally{
            
        }
    }
}
