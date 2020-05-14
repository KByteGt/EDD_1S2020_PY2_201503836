/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import java.math.BigInteger;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_ERROR;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_NO_CONTENT;

/**
 *
 * @author KByteGt
 */
public class ArbolB {
    private NodoB raiz;
    //private final int grado;          //Grado máximo (número máximo de keys en el vector) 
    private final int t;                //Grado minimo (Número minimo de keys en el vector)

    public ArbolB(int t) {
        this.raiz = null;
        this.t = t;
    }
    
    public void recorrer(){
        if(this.raiz != null){
            System.out.println(" Árbol B");
            this.raiz.recorer();
            System.out.println(" ----");
        } else {
            System.out.println("Arbol B vacio!!!");
        }
    }
    
    public int contar(){
        if(this.raiz != null){
            return this.raiz.contar(0);
        } else {
            return 0;
        }
    }
    
    public Libro buscarISBN(BigInteger k){
        return (this.raiz == null)? null : this.raiz.buscarISBN(k);
    }
    
    public Libro[] buscarPorNombre(String nombre){
        return null;
    }
    
    public void insertar(Libro libro){
        if(raiz == null){
            raiz = new NodoB(t,true);
            raiz.getKeys()[0] = libro;
            raiz.setN(1);
        } else {
            if(raiz.getN() == 2*t-1){
                NodoB s = new NodoB(t,false);
                s.getNodos()[0] = raiz;
                s.separarHijo(0, raiz);
                
                int i = 0;
                if(s.getKeys()[0].getISBN().compareTo(libro.getISBN()) < 0){
                    i++;
                }
                
                s.getNodos()[i].insertarNoLleno(libro);
                
                raiz = s;
            } else {
                raiz.insertarNoLleno(libro);
            }
        }
    }
    public int eliminar(BigInteger isbn){
        if (raiz != null) {
            int r;
            r = raiz.eliminar(isbn);
            
            if(raiz.getN() == 0){
                NodoB temp = raiz;
                if(raiz.isHoja()){
                    raiz = null;
                } else {
                    raiz = raiz.getNodos()[0];
                }
            }
            return r;
        } else {
            System.out.println(" Árbol B vacio");
            return REQUEST_ERROR;
        }
    }
    
    public String getGraphviz(String nombre){
        String g = "";
        g += "digraph g{\nnode [shape = record, height = .1];\nlabel = \".: Árbol B - Categoria "+nombre+" :.\";";
//        g += "\nnode0 [label=\"";
//        int j;
//        int i;
//        int nodo = 1;
//        for (j = 0; j < raiz.getN(); j++) {
//            g += "<f"+j+"> |["+raiz.getKeys()[j].getISBN()+"]\\n"+raiz.getKeys()[j].getTitulo()+"|";
//        }
//        g += "<f"+j+">\"];\n";
//            
//        for(i = 0; i < raiz.getN(); i++){
//            if(!raiz.isHoja()){
//                g += "\n\"node0\": f"+i+" -> \"node"+nodo+i+"\"";
//                g +=raiz.getNodos()[i].getGraphviz(10,i);
//            }
//        }
//        
//        if(!raiz.isHoja()){
//            g += "\n\"node0\": f"+i+" -> \"node"+nodo+i+"\"";
//            g += raiz.getNodos()[i].getGraphviz(10,i);
//        }
        g += raiz.getGraphviz();
        //g += raiz.getGraphviz(0);
        g += "\n}";
        return g;
    }
}
