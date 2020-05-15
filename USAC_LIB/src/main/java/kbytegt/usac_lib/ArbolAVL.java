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
public class ArbolAVL {
    private NodoCategoria raiz;
    private ListaCategorias lista;
    
    public ArbolAVL(){
        //Constructor
        this.raiz = null;
        this.lista = new ListaCategorias();
    }

    public NodoCategoria getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoCategoria raiz) {
        this.raiz = raiz;
    }
    
    public ListaCategorias getLista(){
        return lista;
    }
    
    //Funciones
    public int altura(NodoCategoria n){
        //Obtener altura del nodo
        if(n != null){
            return n.getAltura();
        } else {
            return 0;
        }
    }
    
    public int max(int a, int b){
        //Retornar maximo
        return (a > b)? a : b;
    }
    
    /**
     *          y
     *        /   \
     *       x     y
     *      / \   / \
     *     t1 t2 t3 t4
     */
    
    public NodoCategoria rotarDerecha(NodoCategoria y){
        NodoCategoria x = y.getIzquierda();
        NodoCategoria t2 = x.getDerecha();
        
        //Rotación
        x.setDerecha(y);
        y.setIzquierda(t2);
        
        //Actualizar alturas
        y.setAltura(max(altura(y.getIzquierda()),  altura(y.getDerecha())) +1);
        x.setAltura(max(altura(x.getIzquierda()),  altura(x.getDerecha())) +1);
        
        return x;
    }
    
    public NodoCategoria rotarIzquierda(NodoCategoria x){
        NodoCategoria y = x.getDerecha();
        NodoCategoria t2 = y.getIzquierda();
        
        //Rotación
        y.setIzquierda(x);
        x.setDerecha(t2);
        
        //Actualizar alturas
        x.setAltura(max(altura(x.getIzquierda()),  altura(x.getDerecha())) +1);
        y.setAltura(max(altura(y.getIzquierda()),  altura(y.getDerecha())) +1);
        
        return y;
    }
    
    int getBalance(NodoCategoria n){
        if(n != null){
            return altura(n.getIzquierda()) - altura(n.getDerecha());
        } else {
            return 0;
        }
    }
    
    //Insertar
//    public NodoCategoria insertar(String nombre, BigInteger carnet){
//        //return insertar(raiz,nombre,carnet);
//    }
    
    public void insertar(String nombre, int carnet){
        this.raiz = insertar(raiz, nombre, carnet);
    }
    
    private NodoCategoria insertar(NodoCategoria nodo, String nombre, int carnet){
        //1- Inserción normal
        if(nodo == null){
            return (new NodoCategoria(nombre,carnet));
        }
        
        if(nombre.compareTo(nodo.getNombre()) < 0){
            nodo.setIzquierda(insertar(nodo.getIzquierda(),nombre,carnet));
        } else if(nombre.compareTo(nodo.getNombre()) > 0){
            nodo.setDerecha(insertar(nodo.getDerecha(),nombre,carnet));
        } else {
            return nodo;
        }
        
        //2- Actualizar altura del padre
        nodo.setAltura(1+ max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));
        
        //3- Obtener el factor de balance
        int balance = getBalance(nodo);
        
        //caso: Izquierda Izquierda
        if(balance > 1 && nombre.compareTo(nodo.getIzquierda().getNombre()) < 0){
            return rotarDerecha(nodo);
        }
        
        //caso: Derecha Derecha
        if(balance < -1 && nombre.compareTo(nodo.getDerecha().getNombre()) > 0){
            return rotarIzquierda(nodo);
        }
        
        //caso: Izquierda Derecha
        if(balance > 1 && nombre.compareTo(nodo.getIzquierda().getNombre()) > 0){
            nodo.setIzquierda(rotarIzquierda(nodo.getIzquierda()));
            return rotarDerecha(nodo);
        }
        
        //caso: Derecha Izquierda
        if(balance < -1 && nombre.compareTo(nodo.getDerecha().getNombre()) < 0){
            nodo.setDerecha(rotarDerecha(nodo.getDerecha()));
            return rotarIzquierda(nodo);
        }
        
        //Retornar el nodo puntero
        return nodo;
    }
    
    //Eliminar
    public NodoCategoria valorMinimo(NodoCategoria nodo){
        NodoCategoria actual = nodo;
        
        //Bajar hasta encontrar la hoja mas a la izquierda
        while(actual.getIzquierda() != null){
            actual = actual.getIzquierda();
        }
        
        return actual;
    }
    public NodoCategoria eliminar(String nombre, int usuario){
        if(this.raiz != null){
            return eliminar(this.raiz,nombre,usuario);
        } else {
            return null;
        }
    }
    private NodoCategoria eliminar(NodoCategoria raiz, String nombre, int usuario){
        //1- Eliminación normal
        if(raiz == null){
            return raiz;
        }
        
        //Busqueda
        if(nombre.compareTo(raiz.getNombre()) < 0){
            //Si el nombre es menor que el nombre de la raiz
            raiz.setIzquierda(eliminar(raiz.getIzquierda(),nombre,usuario));
        }else if(nombre.compareTo(raiz.getNombre()) > 0){
            //El nombre es mayor que el nombre de la raiz
            raiz.setDerecha(eliminar(raiz.getDerecha(),nombre,usuario));
        } else {
            //Nodo igual, eliminar
            //Nodo con un hijo o sin hijos
            if(usuario == raiz.getUsuario()){
                //La categoria a eliminar pertenece al usuario
                if((raiz.getIzquierda() == null) || (raiz.getDerecha() == null)){
                    NodoCategoria temp = null;
                    if(temp == raiz.getIzquierda()){
                        temp = raiz.getDerecha();
                    } else {
                        temp = raiz.getIzquierda();
                    }

                    //Ningun hijo
                    if(temp == null){
                        temp = raiz;
                        raiz = null;
                    } else {
                        raiz = temp;
                    }
                } else {
                    //Nodo con dos hijos
                    NodoCategoria temp = valorMinimo(raiz.getDerecha());

                    raiz.setNombre(temp.getNombre());

                    //eliminar
                    raiz.setDerecha(eliminar(raiz.getDerecha(),temp.getNombre(), temp.getUsuario()));
                }
            }else {
                System.out.println(" **No tienes permiso de eliminar esta categoria...");
                return raiz;
            } 
        }
        
        //Si el árbol solo tiene un nodo retornar
        if(raiz == null){
            return raiz;
        }
        
        //2- Actualizar altura del nodo actual
        raiz.setAltura(max(altura(raiz.getIzquierda()), altura(raiz.getDerecha())) +1);
        
        //3- Obtener el factor de balance del nodo
        int balance = getBalance(raiz);
        
        //caso: Izquierda Izquierda
        if(balance > 1 && getBalance(raiz.getIzquierda()) >= 0){
            return rotarDerecha(raiz);
        }
        //caso: Izquierda Derecha
        if(balance > 1 && getBalance(raiz.getIzquierda()) < 0){
            raiz.setIzquierda(rotarIzquierda(raiz.getIzquierda()));
            return rotarDerecha(raiz);
        }
        //caso: Derecha Derecha
        if(balance < -1 && getBalance(raiz.getDerecha()) <= 0){
            return rotarIzquierda(raiz);
        }
        //caso: Derecha Izquierda
        if(balance < -1 && getBalance(raiz.getDerecha()) > 0){
            raiz.setDerecha(rotarDerecha(raiz.getDerecha()));
            return rotarIzquierda(raiz);
        }
        
        return raiz;
        
    }
    
    //Buscar
    public NodoCategoria buscar(String nombre){
        if(this.raiz != null){
            return buscar(this.raiz, nombre);
        } else {
            return null;
        }
    }
    private NodoCategoria buscar(NodoCategoria raiz, String nombre){
        NodoCategoria temp = null;
        
        while(raiz != null){
            //System.out.println("El nombre es: "+ raiz.getNombre());
            if(nombre.compareTo(raiz.getNombre()) < 0){
                raiz = raiz.getIzquierda();
            }else if(nombre.compareTo(raiz.getNombre()) > 0){
                raiz = raiz.getDerecha();
            } else {
                temp = raiz;
                return temp;
            }
            temp = buscar(raiz,nombre);
        }

//        if(raiz != null){
//            //Busqueda
//            if(nombre.compareTo(raiz.getNombre()) < 0){
//                //Si el nombre es menor que el nombre de la raiz
//                raiz.setIzquierda(buscar(raiz.getIzquierda(),nombre));
//            }else if(nombre.compareTo(raiz.getNombre()) > 0){
//                //El nombre es mayor que el nombre de la raiz
//                raiz.setDerecha(buscar(raiz.getDerecha(),nombre));
//            } else {
//                temp = raiz;
//                return temp;
//            }
//        }
        
        return temp;
    }
    
    //Función que busca una categoria
    public boolean existe(String nombre){
        return existe(raiz, nombre);
    }
    
    private boolean existe(NodoCategoria raiz, String nombre){
        boolean flag = false;
        while(raiz != null && !flag){
            if(nombre.compareTo(raiz.getNombre()) < 0){
                raiz = raiz.getIzquierda();
            }else if(nombre.compareTo(raiz.getNombre()) > 0){
                raiz = raiz.getDerecha();
            } else {
                flag = true;
                return true;
            }
            
        }
        return flag;
    }
    
    //Recorrer árbol AVL
    public void recorrer(){
        recorrer(raiz);
    }
    private void recorrer(NodoCategoria nodo){
        if(nodo != null){
//            //Pre orden
//            System.out.println(nodo.getNombre() + " ");
//            recorrer(nodo.getIzquierda());
//            recorrer(nodo.getDerecha());

            //En orden
            recorrer(nodo.getIzquierda());
            System.out.println(nodo.getNombre() + " ");
            recorrer(nodo.getDerecha());
        }
    }
    
    //Retornar lista con categorias 
    public ListaCategorias getListaCategorias(){
        if(raiz != null){
            this.lista.vaciar();
            llenarListaCategorias(raiz);
            return this.lista;
        } else {
            return null;
        }
        
    }
    
    private void llenarListaCategorias(NodoCategoria nodo){
        if(nodo != null){
            llenarListaCategorias(nodo.getIzquierda());
            lista.insertar(nodo.getNombre());
            llenarListaCategorias(nodo.getDerecha());
        }
    }
    
    public void insertarLibro(Libro lib){
        /*
         *  1- Verificar que exita la categoria
         *      - sin no exite: se crea la categoria y se inserta el libro
         *      - Si existe: paso 2
         *  2- Verificar que no exista un libro con el mismo ISBN
         *      - Si existe no se hace nada
         *      - Si no existe se crea
         */
        
        System.out.println(" -- Buscando categoria: " + lib.getCategoria());
        NodoCategoria categoria = buscar(lib.getCategoria());
        if(categoria != null){
            //Existe la categoria
            System.out.println(" -- Ya existe la categoria");
            if(categoria.buscarISBN(lib.getISBN()) == null){
                //No existe el libor
                categoria.insertarLibro(lib);
            } else {
                //Exite el libro 
                System.out.println(" **Ya existe el libro ["+lib.getISBN()+"] "+lib.getTitulo()+" en la categoria "+lib.getCategoria());
            }
        } else {
            //No existe la categoria
            //Crear categoria
            insertar(lib.getCategoria(),lib.getCarnet());
            //Insertar en categoria
            buscar(lib.getCategoria()).insertarLibro(lib);
        }
        
    }
}
