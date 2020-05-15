/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

/**
 *
 * @author KByteGt
 * Lista Simple ordenada obtenida del árbol AVL para 
 * llenar JTreeView de categorias
 */
class NodoLC{
    private NodoLC siguiente;
    private String categoria;

    public NodoLC(String categoria) {
        this.categoria = categoria;
        this.siguiente = null;
    }

    public NodoLC getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLC siguiente) {
        this.siguiente = siguiente;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public void imprimir(){
        System.out.println(categoria);
    }
    
}

public class ListaCategorias {
    private NodoLC inicio;
    private int contador;
    
    public ListaCategorias(){
        this.inicio = null;
        this.contador = 0;
    }

    public NodoLC getInicio() {
        return inicio;
    }

    public void setInicio(NodoLC inicio) {
        this.inicio = inicio;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public boolean estaVacio(){
        return this.inicio == null;
    }
    
    public void insertar(String categoria){
        if(!estaVacio()){
            NodoLC temp = this.inicio;
            while(temp.getSiguiente() != null){
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(new NodoLC(categoria));
            contador++;
        } else {
            this.inicio = new NodoLC(categoria);
            contador++;
        }
    }
    
    public void vaciar(){
        this.inicio = null;
        this.contador = 0;
    }
            
}
