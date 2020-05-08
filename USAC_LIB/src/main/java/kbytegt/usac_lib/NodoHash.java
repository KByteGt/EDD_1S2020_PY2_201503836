/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_NOT_FOUND;

/**
 *
 * @author KByteGt
 */
public class NodoHash {
    private ListaUsuarios lista;
    private int id;
    
    /**
     * @param int id
     */
    NodoHash(int id){
        this.lista = new ListaUsuarios();
        this.id = id;
    }

    public ListaUsuarios getLista() {
        return lista;
    }

    public void setLista(ListaUsuarios lista) {
        this.lista = lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    //FUNCIONES DEL NODO HASH
    
    /**
     *
     * @param NodoUsuario
     * @return REQUEST_UPDATED, REQUEST_OK
     */
    public int insertar(NodoUsuario usuario){
        return this.lista.insertar(usuario);
    }
    
    /**
     *
     * @param int carnet
     * @return NodoUsuario, null
     */
    public NodoUsuario buscar(int carnet){
        if(this.lista.getInicio() != null){
            return this.lista.buscar(carnet);
        } else{
            return null;
        }
            
    }
    
    /**
     *
     * @param int carnet
     * @return REQUEST_DELETED, REQUEST_NO_CONTENT, REQUEST_NOT_FOUND
     */
    public int eliminar(int carnet){
        if(this.lista.getInicio() != null){
            return this.lista.eliminar(carnet);
        } else{
            return REQUEST_NOT_FOUND;
        }
        
    }
    
    String getGraphvizNodo(){
        
        String g = "";
        g += "N"+this.id +"  [label = \""+this.id+"\"  width = 1.5 style = filled, group = 0 ];\n";
        g += this.lista.getGraphvizNodo();
        g += "{ rank = same; N"+this.id +"; "+this.lista.getGraphvizRank()+"}\n";
        return g;
    }
    
    String getGraphvizRuta(){
        String g = "";
        if(this.lista.getInicio() != null){
             g += "N"+this.id + this.lista.getGraphvizRuta();
        }
        return g;
    }
}
