/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import java.math.BigInteger;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_ERROR;

/**
 *
 * @author KByteGt
 */
public class NodoB {
    //Nodo del árbol B
    private Libro[] keys;           //vector de Keys (libros)
    private final int grado;        //Grado máximo (número máximo de keys en el vector) 
    private final int criterio;     //Grado minimo (Número minimo de keys en el vector)
    private NodoB[] nodos;          //Vector de punteros hijos
    private int n;                  //Número actual de Keys (libros)
    private boolean hoja;           //True = hoja, False != hoja

    //Constructor
    public NodoB(int grado, int criterio, boolean esHoja) {
        this.grado = grado;
        this.criterio = criterio;
        this.hoja = esHoja;
        this.keys = new Libro[grado - 1];
        this.nodos = new NodoB[grado];
        this.n = 0;
    }
    
    //Get & Set
    
    public Libro[] getKeys() {
        return keys;
    }

    public void setKeys(Libro[] keys) {
        this.keys = keys;
    }

    public NodoB[] getNodos() {
        return nodos;
    }

    public void setNodos(NodoB[] nodos) {
        this.nodos = nodos;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean isHoja() {
        return hoja;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }

    public int getGrado() {
        return grado;
    }

    public int getCriterio() {
        return criterio;
    }
    
    //Otros metodos
    public void recorer(){
        //Imprimir en consola el recorrido del nodo
        int i;
        for ( i = 0; i < this.n; i++) {
            if (!hoja) {
                //Imprimir ramas internas
                nodos[i].recorer();
            }
            System.out.println(keys[i].getISBN() + " ");
        }
        
        if (!hoja) {
            //Imprimir ultima rama
            nodos[i].recorer();
        }
    }
    
    public Libro buscarISBN(BigInteger k){
        int i = 0;
        while(i < n && k.compareTo(keys[i].getISBN()) > 0){
            i++;
        }
        
        if(keys[i].getISBN().compareTo(k) == 0){
            return keys[i];
        }
        
        if(hoja){
            return null;
        }
        
        return nodos[i].buscarISBN(k);
    }
    
    public Libro[] buscarPorNombre(String nombre){
        return null;
    }
    
    //Métodos para insertar
    
    public int insertarNoLleno(Libro libro){
        return REQUEST_ERROR;
    }
    
    public void separarHijo(int i, NodoB y){
        //Nuevo nodo temp, almacena (criterio -1) de las keys
        NodoB temp = new NodoB(y.getGrado(),y.getCriterio(),y.isHoja());
        temp.setN(criterio -1);
        
        for (int j = 0; j < criterio -1; j++) {
            temp.keys[j] = y.keys[j+criterio];
        }
        
        if(!y.isHoja()){
            for (int j = 0; j < criterio; j++) {
                temp.nodos[j] = y.nodos[j+criterio];
            }
        }
        
        y.setN(criterio -1);
        
        for (int j = 0; j >= i+1; j--) {
            nodos[j+1] = nodos[j];
        }
        
        nodos[i+1] = temp;
        
        for (int j = n-1; j >= i; j--) {
            keys[j+1] = keys[j];
        }
        
        keys[i] = y.getKeys()[criterio -1];
        
        n = n +1;
    }
}
