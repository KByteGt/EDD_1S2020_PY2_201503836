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
public class Libro {
    private final BigInteger ISBN; //No se puede cambiar el ID
    private String titulo;
    private String autor;
    private String editorial;
    private int año;
    private String edicion;
    private String categoria;
    private String idioma;
    private int carnet;

    public Libro(BigInteger isbn, String titulo, String autor, String editorial, int año, String edicion, String categoria, String idioma, int carnet) {
        this.ISBN = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.año = año;
        this.edicion = edicion;
        this.categoria = categoria;
        this.idioma = idioma;
        this.carnet = carnet;
    }

    public BigInteger getISBN() {
        return ISBN;
    }

//    public void setIsbn(BigInteger isbn) {
//        this.isbn = isbn;
//    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }
    
}
