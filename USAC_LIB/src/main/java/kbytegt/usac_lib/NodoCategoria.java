/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import java.math.BigInteger;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_ERROR;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_NO_CONTENT;
import static kbytegt.usac_lib.USAC_LIBRARY.REQUEST_OK;
/**
 *
 * @author KByteGt
 */
public class NodoCategoria {
    private String nombre;
    private BigInteger usuario;
    private ArbolB libros;
    private int total, altura;
    
    private NodoCategoria izquierda, derecha;
    
    public NodoCategoria(String nombre, BigInteger carnet){
        this.nombre = nombre;
        this.usuario = carnet;
        libros = new ArbolB(2);
        this.total = 0;
        this.altura = 1;
        this.izquierda = null;
        this.derecha = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getUsuario() {
        return usuario;
    }

    public void setUsuario(BigInteger usuario) {
        this.usuario = usuario;
    }

    public ArbolB getLibros() {
        return libros;
    }

    public void setLibros(ArbolB libros) {
        this.libros = libros;
    }

    public NodoCategoria getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoCategoria izquierda) {
        this.izquierda = izquierda;
    }

    public NodoCategoria getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoCategoria derecha) {
        this.derecha = derecha;
    }

    public int getTotal() {
        int x = this.libros.contar();
        System.out.println("Total("+x+", "+this.total+")");
        if( x == this.total){
            return total;
        } else {
            return x;
        }
        
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    public void eliminarLibro(BigInteger isbn){
        int r = this.libros.eliminar(isbn);
        switch(r){
            case REQUEST_ERROR:
                System.out.println("Error al intentar eliminar el libro: "+isbn);
                break;
            case REQUEST_NO_CONTENT:
                System.out.println("No se encontro el libro: "+isbn);
                break;
            case REQUEST_OK:
                System.out.println("Libro ["+isbn+"] eliminado con exito.");
                this.total--;
                break;
            default:
                System.out.println("Codigo: "+r);
                break;
        }
    }
    
    public void insertarLibro(Libro lib){
        this.libros.insertar(lib);
        this.total++;
    }
    
    public String getGraphvizNodo(){
        String g = "";
        
        return g;
    }
    
    public String getGraphvizRuta(){
        String g = "";
        
        return g;
    }
}
