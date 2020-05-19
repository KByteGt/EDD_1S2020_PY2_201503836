package kbytegt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KByteGt
 */
public class NodoUsuario {
    private final int carnet; //No se puede cambiar el ID
    private String nombre;
    private String apellido;
    private String carrera;
    private String password;
    
    private NodoUsuario siguiente;

    /**
     *  @param int carnet
     *  @param String nombre
     *  @param String apellido
     *  @param String carrera
     *  @param String password
     */
    public NodoUsuario(int carnet, String nombre, String apellido, String carrera, String password) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.password = password;
        this.siguiente = null;
    }

    public int getCarnet() {
        return carnet;
    }

//    public void setCarnet(int carnet) {
//        this.carnet = carnet;
//    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public NodoUsuario getSiguiente(){
        return siguiente;
    }
    
    public void setSiguiente(NodoUsuario nodo){
        this.siguiente = nodo;
    }
    
    public String getJSON(){
        String json = "\"Carnet\": " + this.carnet + ", \"Nombre\": " + this.nombre + ", \"Apellido\": " + this.apellido + ", \"Carrera\": " + this.carrera + ", \"Password\": " + this.password;
        return "{" + json + "}";
    }
    
    String getGraphvizNodo(int id){
        
        String g = "U"+this.carnet +" [label = \"["+this.carnet+"] "+this.nombre+" "+this.apellido+"\n"+this.password+"\" width = 1, group = "+id+" ];\n";
        return g;
    }
    
    String getGraphvizRuta(){
        String g = "";
        g = " -> U"+this.carnet;
        return g;
    }
}
