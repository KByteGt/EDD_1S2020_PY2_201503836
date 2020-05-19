/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt;

import static kbytegt.usac_lib.REQUEST_DELETED;
import static kbytegt.usac_lib.REQUEST_NOT_FOUND;
import static kbytegt.usac_lib.REQUEST_OK;
import static kbytegt.usac_lib.REQUEST_UPDATED;

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
            NodoUsuario temp, auxiliar;
            boolean find = false;
            temp = this.inicio;

            System.out.println("Insertando Usuario en la lista...");
            while(temp.getSiguiente() != null && temp.getCarnet() != usuario.getCarnet()){
                if(temp.getCarnet() == usuario.getCarnet()){
                    auxiliar = temp;
                    find = true;
                }
                System.out.println(" |> Buscando: "+ temp.getCarnet() +" = "+ usuario.getCarnet());
                temp = temp.getSiguiente(); //<- Errores que dan gusto jajajaja
            } 
            //VERIFICAR SI ES NULL O IGUAL
            if(temp.getCarnet() == usuario.getCarnet()){
                System.out.println("Se encontro igual... solo se actualizará la info, pero no el carnet");
                //EL TEMP.CARNET ES IGUAL A USUARIO.CARNET
                temp.setNombre(usuario.getNombre());
                temp.setApellido(usuario.getApellido());
                temp.setCarrera(usuario.getCarrera());
                temp.setPassword(usuario.getPassword());
                return REQUEST_UPDATED;
            } else {
                //Agregar al final
                System.out.println("Insertando Usuario al final...");
                temp.setSiguiente(usuario);
                return REQUEST_OK;
            } 
        } else {
            //No hay nada en el inicio
            System.out.println("Insertanod Usuario por primera vez...");
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
        System.out.println(" [eliminar tabla hash] buscando carnet: " + carnet);
        if(this.inicio != null){
             NodoUsuario temp, aux;
            if (this.inicio.getCarnet() == carnet) {
                //Eliminar Inicio
                if(this.inicio.getSiguiente()!= null){
                    temp = this.inicio.getSiguiente();
                    this.inicio = temp;
                } else {
                    this.inicio = null;
                }
                return REQUEST_DELETED;
            } else {
                // No es Inicio
                temp = this.inicio;
                while(temp.getSiguiente() != null && temp.getSiguiente().getCarnet() != carnet){
                    System.out.println(" > Buscando"+carnet+": "+temp.getSiguiente().getCarnet());
                    temp.getSiguiente();
                }
                
                if (temp.getSiguiente().getCarnet() == carnet) {
                    //Eliminar nodo
                    aux = temp.getSiguiente().getSiguiente();
                    temp.setSiguiente(aux);
                    return REQUEST_DELETED;
                } else{
                    return REQUEST_NOT_FOUND;
                }
            }
        } else {
            return REQUEST_NOT_FOUND;
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
