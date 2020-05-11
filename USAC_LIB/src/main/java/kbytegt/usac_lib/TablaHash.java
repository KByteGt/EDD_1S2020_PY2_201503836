/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_ERROR;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_OK;

/**
 *
 * @author KByteGt
 */
public class TablaHash {
    private NodoHash[] tabla;
    private int size;
    
    TablaHash(int size){
        this.size = size;
        tabla = new NodoHash[size];
        iniciarTabla();
    }

    public NodoHash[] getTabla() {
        return tabla;
    }

    public void setTabla(NodoHash[] tabla) {
        this.tabla = tabla;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //FUNCIONES TABLA HASH
    
    private int funcion(int carnet){
        return carnet % this.size;
    }
    
    private int iniciarTabla(){
        try {
            System.out.println("Iniciando nodos en tabla hash");
            for (int i = 0; i < this.size; i++) {
                this.tabla[i] = new NodoHash(i);
            }
            return REQUEST_OK;
        } catch (Exception e) {
            System.out.println("Error alinicializar nodos en tabla hash");
            return REQUEST_ERROR;
        }
    }
    
    /**
     *
     * @param NodoUsuario
     * @return REQUEST_UPDATED, REQUEST_OK
     */
    public int insertar(NodoUsuario usuario){
        //Obtener indice
        int i = funcion(usuario.getCarnet());
        
        System.out.println(" -- Insertar usuario en la klave: "+i);
        int request = tabla[i].insertar(usuario);
        
        return request;
    }
    
    /**
     *
     * @param int carnet
     * @return NodoUsuario, null
     */
    public NodoUsuario buscar(int carnet){
        try {
            int i = funcion(carnet);
            NodoUsuario request = tabla[i].buscar(carnet);
            return request;
        } catch (Exception e) {
            return null;
        }
       
    }
    
    /**
     *
     * @param int carnet
     * @return REQUEST_DELETED, REQUEST_NO_CONTENT, REQUEST_NOT_FOUND, REQUEST_ERROR
     */
    public int eliminar(int carnet){
        try {
            int i = funcion(carnet);
            int request = tabla[i].eliminar(carnet);
            return request;
        } catch (Exception e) {
            return REQUEST_ERROR;
        }
        
    }
    
    String getGraphviz(String nombre){
        
        String g = "";
        String r = "";
        g += "digraph TablaHash {\n\tnode [shape=box]\n\tlabel = \".:: Usuarios registrados ::.\";\n" +
             "\tTH[ label = \""+nombre+"\", width = 1.5, style = filled, group = 0 ];";
        r += "TH -> ";
        for (int i = 0; i < this.size -1; i++) {
            r += "N"+i+" ->";
        }
        r += "N"+(this.size-1)+"; \n";
        
        for (int i = 0; i < this.size; i++) {
            g += tabla[i].getGraphvizNodo();
            r += tabla[i].getGraphvizRuta();
        }
        g += r+"\n}";
        return g;
    }
    
}
