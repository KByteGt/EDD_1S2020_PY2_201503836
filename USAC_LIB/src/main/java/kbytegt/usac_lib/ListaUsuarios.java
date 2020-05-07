/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_DELETED;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_NO_CONTENT;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_OK;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_UPDATED;

/**
 *
 * @author KByteGt
 */
public class ListaUsuarios {
    private NodoUsuario inicio;

    public ListaUsuarios() {
        this.inicio = null;
    }

    public NodoUsuario getInicio() {
        return inicio;
    }

    public void setInicio(NodoUsuario inicio) {
        this.inicio = inicio;
    }
    
    //METODOS DE LA LISTA SIMPLE
    
    /**
     *
     * @param NodoUsuario
     * @return REQUEST_UPDATED, REQUEST_OK
     */
    public int insertar(NodoUsuario usuario){
        if(this.inicio != null){
            //Ya hay datos
            NodoUsuario temp;
            temp = this.inicio;

            while(temp.getSiguiente() != null && temp.getCarnet() != usuario.getCarnet()){
                temp.getSiguiente();
            }
            //VERIFICAR SI ES NULL O IGUAL
            if(temp.getCarnet() == usuario.getCarnet()){
                //EL TEMP.CARNET ES IGUAL A USUARIO.CARNET
                temp.setNombre(usuario.getNombre());
                temp.setApellido(usuario.getApellido());
                temp.setCarrera(usuario.getCarrera());
                temp.setPassword(usuario.getPassword());
                return REQUEST_UPDATED;
            } else {
                //Agregar al final
                temp.setSiguiente(usuario);
                return REQUEST_OK;
            }
        } else {
            //No hay nada en el inicio
            this.inicio = usuario;
            return REQUEST_OK;
        }
    }
    
    /**
     *
     * @param int carnet
     * @return NodoUsuario, null
     */
    public NodoUsuario buscar(int carnet){
        if(this.inicio != null){
            NodoUsuario temp;
            temp = this.inicio;

            while(temp.getSiguiente() != null && temp.getCarnet() != carnet){
                temp.getSiguiente();
            }

            if(temp.getCarnet() == carnet){
                return new NodoUsuario(temp.getCarnet(), temp.getNombre(), temp.getApellido(), temp.getCarrera(), temp.getPassword());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    /**
     *
     * @param int carnet
     * @return REQUEST_DELETED, REQUEST_NO_CONTENT
     */
    public int eliminar(int carnet){
        if(this.inicio != null){
            NodoUsuario temp, anterior;
            temp = this.inicio;
            anterior = this.inicio;

            while(temp.getSiguiente() != null && temp.getCarnet() != carnet){
                temp.getSiguiente();
                anterior = temp;
            }

            if(temp.getCarnet() == carnet){
                //ELIMINAR USUARIO
                if(temp == this.inicio){
                    //EL DATO A ELIMINAR ES INICIO
                    this.inicio = temp.getSiguiente();
                } else {
                    //ELIMINAR DATO
                    anterior.setSiguiente(temp.getSiguiente());
                }
                return REQUEST_DELETED;
            } else {
                return REQUEST_NO_CONTENT;
            }
        } else {
            return REQUEST_NO_CONTENT;
        }
    }
    
    String getGraphvizNodo(){
        String g = "";
        if(this.inicio != null){
            int i = 1;
            NodoUsuario temp = this.inicio;
            while(temp.getSiguiente() != null){
                g += temp.getGraphvizNodo(i);
                temp = temp.getSiguiente();
                i++;
            }
            g += temp.getGraphvizNodo(i);            
        }
        return g;
    }
    
    String getGraphvizRuta(){
        String g = "";
        if(this.inicio != null){
            NodoUsuario temp = this.inicio;
            while(temp.getSiguiente() != null){
                g += temp.getGraphvizRuta();
                temp = temp.getSiguiente();
            }
            g += temp.getGraphvizRuta()+";\n";
        }        
        return g;
    }
    
    String getGraphvizRank(){
        String g = "";
        if(this.inicio != null){
            NodoUsuario temp = this.inicio;
            while(temp.getSiguiente() != null){
                g += "U"+temp.getCarnet()+"; ";
                temp = temp.getSiguiente();
            }
            g += "U"+temp.getCarnet()+"; ";
        }
        return g;
    }
}
