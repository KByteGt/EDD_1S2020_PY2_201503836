/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import java.math.BigInteger;
import java.util.HashSet;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_ERROR;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_NO_CONTENT;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_OK;

/**
 *
 * @author KByteGt
 */
public class NodoB {
    //Nodo del árbol B
    private Libro[] keys;           //vector de Keys (libros)
    //private final int grado;        //Grado máximo (número máximo de keys en el vector) 
    private final int t;     //Grado minimo (Número minimo de keys en el vector)
    private NodoB[] nodos;          //Vector de punteros hijos
    private int n;                  //Número actual de Keys (libros)
    private boolean hoja;           //True = hoja, False != hoja

    //Constructor
    public NodoB(int t, boolean esHoja) {
        this.hoja = esHoja;
        this.t = t;
        this.keys = new Libro[2*t - 1];
        this.nodos = new NodoB[2*t];
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

    public int getT() {
        return t;
    }
    
    //Otros metodos
    public void recorer(){
        //Imprimir en consola el recorrido del nodo
        int i;
//        System.out.println("");
//        System.out.println("Blcoque Nodo");
//        System.out.print("{");
        for ( i = 0; i < this.n; i++) {
            if (!hoja) {
                //Imprimir ramas internas
//                System.out.print(" - NR"+i);
                nodos[i].recorer();
            }
            System.out.print(" ["+keys[i].getISBN() + "] "+ keys[i].getTitulo());
            System.out.println("");
        }
        
        
        if (!hoja) {
            //Imprimir ultima rama
//            System.out.print(" - NR"+i);
            nodos[i].recorer();
        }
        
//        System.out.println("}");
    }
    
    public int contar(int x){
        //Imprimir en consola el recorrido del nodo
        
        int i;
        for ( i = 0; i < this.n; i++) {
            if (!hoja) {
                nodos[i].contar(x);
            }
            x++;
        }
        if (!hoja) {
            nodos[i].contar(x);
        }
        return x;
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
        int i = n-1;
        
        if(this.isHoja()){
            while(i >= 0 && keys[i].getISBN().compareTo(libro.getISBN()) > 0){
                keys[i+1] = keys[i];
                i--;
            }
            //Insertar libro en la nueva posición
            keys[i+1] = libro;
            n = n+1;
        } else {
             while(i >= 0 && keys[i].getISBN().compareTo(libro.getISBN()) > 0)
                 i--;
             
             if (nodos[i+1].getN() == 2*t-1) {
                 separarHijo(i+1,nodos[i+1]);
                 
                 if(keys[i+1].getISBN().compareTo(libro.getISBN()) < 0){
                     i++;
                 }
            }
             nodos[i+1].insertarNoLleno(libro);
        }
        return REQUEST_ERROR;
    }
    
    public void separarHijo(int i, NodoB y){
        //Nuevo nodo temp, almacena (criterio -1) de las keys
        NodoB temp = new NodoB(y.getT(),y.isHoja());
        temp.setN(t -1);
        System.out.println(" - SepararHijo("+i+")");
        //Copiar keys hasta (criterio -1) de y a temp
        for (int j = 0; j < t -1; j++) {
            System.out.println("t = "+t);
            System.out.println("j = " + j +", j+t = "+(j+t));
            temp.keys[j] = y.keys[j+t];
        }
        
        if(!y.isHoja()){
            for (int j = 0; j < t; j++) {
                temp.nodos[j] = y.nodos[j+t];
            }
        }
        
        //Reducir el número de keys en y
        y.setN(t -1);
        
        //Crear espacio para el nuevo hijo
        for (int j = n; j >= i+1; j--) {
            nodos[j+1] = nodos[j];
        }
        
        //Enlazar el nuevo hijo a este nodo
        nodos[i+1] = temp;
        
        for (int j = n-1; j >= i; j--) {
            keys[j+1] = keys[j];
        }
        
        keys[i] = y.keys[t -1];
        
        n = n +1;
    }
    
    //Funcion que devuelve el indice de la primera key mayor o igual a k
    public int findKey(BigInteger k){
        int id = 0;
        while(id < n && keys[id].getISBN().compareTo(k) < 0){
            ++id;
        }
        
        return id;
    }
    
    //Métodos para eliminar
    public int eliminar(BigInteger isbn){
        int id = findKey(isbn);
        
        //El id se encontro en el Nodo
        if(id < n && keys[id].getISBN().compareTo(isbn) == 0){
            
            if(hoja){
                eliminarDeHoja(id);
            } else {
                eliminarDeNoHoja(id);
            }
            return REQUEST_OK;
        } else {
            //El id no esta en el nodo
            //Si no es un nodo hoja
            if(!hoja){
                boolean flag = (id == n);
                
                if(nodos[id].getN() < t){
                    llenar(id);
                }
                
                if(flag && id > n){
                    return nodos[id -1].eliminar(isbn);
                } else {
                    return  nodos[id].eliminar(isbn);
                }
            } else {
                //Es un nodo hoja, no existe el libro a eliminar
                //System.out.println(" ["+isbn+"]El libro no existe...");
                return REQUEST_NO_CONTENT;
            }
        }
    }
    
    public void eliminarDeHoja(int id){
        //Correr todos los keys a la posición de id
        for(int i = id +1; i < n; ++i){
            keys[i-1] = keys[i];
        }
        
        //Reducir el contador de keys
        n--;
    }
    
    public void eliminarDeNoHoja(int id){
        Libro lib = keys[id];
        
        if(nodos[id].getN() >= t){
            Libro predecesor = getPredecesor(id);
            keys[id] = predecesor;
            nodos[id].eliminar(predecesor.getISBN());
        } else if(nodos[id +1].getN() >= t){
            Libro sucesor = getSucesor(id);
            keys[id] = sucesor;
            nodos[id +1].eliminar(sucesor.getISBN());
        } else {
            unir(id);
            nodos[id].eliminar(lib.getISBN());
        }
    }
    
    //Obtener predecesor de keys[id]
    public Libro getPredecesor(int id){
        NodoB temp = nodos[id];
        while(!temp.isHoja()){
            temp = temp.nodos[temp.getN()];
        }
        //retornar la ultima key de la hoja
        return temp.keys[temp.getN() -1];
    }
    
    public Libro getSucesor(int id){
        NodoB temp = nodos[id +1];
        while(!temp.isHoja()){
            temp = temp.nodos[0];
        }
        //Retornar la primera key de la hoja
        return temp.keys[0];
    }
    
    public void llenar(int id){
        if(id != 0 && nodos[id -1].getN() >= t){
            prestarDeAnterior(id);
        } else if(id != n && nodos[id +1].getN() >= t){
            prestarDeSiguiente(id);
        } else {
            if(id != n){
                unir(id);
            } else {
                unir(id -1);
            }
        }
    }
    
    public void prestarDeAnterior(int id){
        NodoB hijo = nodos[id];
        NodoB hermano = nodos[id -1];
        
        //Mover todas las key en nodos[id] una posición adelante
        for(int i = hijo.getN()-1; i >= 0; --i){
            hijo.keys[i+1] = hijo.keys[i];
        }
        
        //si nodos[id] no es una hoja, mover todos los punteros un paso adelante
        if(!hijo.isHoja()){
            for (int i = hijo.getN(); i >= 0; --i) {
                hijo.nodos[i+1] = hijo.nodos[i];
            }
        }
        
        //Establecer la primera key del hijo igual a la key[id-1] del nodo actual
        hijo.keys[0] = keys[id-1];
        
        //Mover el último hijo del hermano como primer hijo en nodos[id]
        if(!hijo.isHoja()){
            hijo.nodos[0] = hermano.nodos[hermano.getN()];
        }
        
        //Mover la key del hermano al padre
        //Reducir el numero de keys en el hermano
        keys[id -1] = hermano.keys[hermano.getN() -1];
        
        int n;
        n = hijo.getN();
        hijo.setN(n +1);
        n = hermano.getN();
        hermano.setN(n -1);
    }
    
    public void prestarDeSiguiente(int id){
        NodoB hijo = nodos[id];
        NodoB hermano = nodos[id +1];
        
        //keys[id] se inserta en el ultimo key en nodos[id]
        hijo.keys[hijo.getN()] = keys[id];
        
        //Insertar primer hijo del hermano en el ultimo hijo en nodos[id]
        if(!hijo.isHoja()){
            hijo.nodos[hijo.getN() +1] = hermano.nodos[0];
        }
        
        //El primer key del hermano se inserta en keys[id]
        keys[id] = hermano.keys[0];
        
        //Mover todas las keys en el hermano un paso antes
        for(int i = 1; i < hermano.getN(); ++i){
            hermano.keys[i -1] = hermano.keys[i];
        }
        
        //mover los punteros hijos una posición antes
        if(!hermano.isHoja()){
            for(int i = 1; i <= hermano.getN(); ++i){
                hermano.nodos[i -1] = hermano.nodos[i];
            }
        }
        
        //Incrementar y decrementar el contador de keys en nodos[id] y nodos[id +1]
        int n = 0;
        n = hijo.getN();
        hijo.setN(n +1);
        n = hermano.getN();
        hermano.setN(n -1);
    }
    
    public void unir(int id){
        NodoB hijo = nodos[id];
        NodoB hermano = nodos[id +1];
        
        //Extraer una key del nodo actual e insertar en la posición (t-1) en nodos[id]
        hijo.keys[t-1] = keys[id];
        
        //Copiar las keys de nodos[id +1] a nodos[id] al final
        for(int i = 0; i < hermano.getN(); ++i){
            hijo.keys[i+t] = hermano.keys[i];
        }
        
        //Copiar los punteros de c[id+1] a c[id]
        if(!hijo.isHoja()){
            for(int i = 0; i <= hermano.getN(); ++i){
                hijo.nodos[i+t] = hermano.nodos[i];
            }
        }
        
        //Mover todas las keys del nodo actual despues de id
        for(int i = id +1; i < n; ++i){
            keys[i-1] = keys[i];
        }
        //Mover todos los punteros del nodo actual
        for(int i = id +2; i <= n; ++i){
            nodos[i-1] = nodos[i];
        }
        
        //Actualizar el contador de keys
        n--;
        
    }
    
    //Graphviz
    public String getGraphviz(){
        String g = "";
        int i;
        int j;
        g += "\nnode"+keys[0].getISBN()+" [label=\"";
        for (j = 0; j < this.n; j++) {
            g += "<f"+j+"> |["+this.keys[j].getISBN()+"]\\n"+this.keys[j].getTitulo()+"|";
        }
        g += "<f"+j+">\"];\n";
        
        for(i = 0; i < this.n; i++){
            if(!this.isHoja()){
                g += "\n\"node"+keys[0].getISBN()+"\": f"+i+" -> \"node"+nodos[i].keys[0].getISBN()+"\"";
                g += nodos[i].getGraphviz();
            }
        }
        if(!isHoja()){
            g += "\n\"node"+keys[0].getISBN()+"\": f"+i+" -> \"node"+nodos[i].keys[0].getISBN()+"\"";
            g += nodos[i].getGraphviz();
        }
        
       

        return g;
    }
}
