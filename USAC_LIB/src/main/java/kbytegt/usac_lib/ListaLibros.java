/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

/**
 *
 * @author KByteGt
 */
class NodoLL{
    private NodoLL siguiente;
    private Libro libro;

    public NodoLL(Libro libro) {
        this.libro = libro;
        this.siguiente = null;
    }

    public NodoLL getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLL siguiente) {
        this.siguiente = siguiente;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
    public void imprimir(){
        System.out.println(libro.getTitulo());
    }
}
public class ListaLibros {
    private NodoLL inicio;
    private int contador;
    
    public ListaLibros(){
        this.inicio = null;
        this.contador = 0;
    }

    public NodoLL getInicio() {
        return inicio;
    }

    public void setInicio(NodoLL inicio) {
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
    
    public void insertar(Libro libro){
        
        if(!estaVacio()){
            System.out.println(" insertando libro en la lista: "+libro.getTitulo());
            NodoLL temp = this.inicio;
            while(temp.getSiguiente() != null){
                System.out.println(" --> avanzando");
                temp = temp.getSiguiente();
            }
            System.out.println(" -- Insertando");
            temp.setSiguiente(new NodoLL(libro));
            contador++;
            System.out.println(" -- Contador: "+contador);
        } else {
            System.out.println(" insertando libro en la lista al inicio: "+libro.getTitulo());
            this.inicio = new NodoLL(libro);
            contador++;
        }
    }
    
    public void vaciar(){
        this.inicio = null;
        this.contador = 0;
    }
}
