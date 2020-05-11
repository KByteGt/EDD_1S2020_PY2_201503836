/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import java.math.BigInteger;

/**
 *
 * @author KByteGt
 */
public class ArbolB {
    private NodoB raiz;
    private final int grado;        //Grado máximo (número máximo de keys en el vector) 
    private final int criterio;     //Grado minimo (Número minimo de keys en el vector)

    public ArbolB(int grado) {
        this.grado = grado;
        this.raiz = null;
        this.criterio = grado * 2 / 3;
    }
    
    public void recorrer(){
        if(this.raiz != null)
            this.raiz.recorer();
        System.out.println("Arbol B vacio!!!");
    }
    
    public Libro buscarISBN(BigInteger k){
        return (this.raiz == null)? null : this.raiz.buscarISBN(k);
    }
    
    public Libro[] buscarPorNombre(String nombre){
        return null;
    }
}
